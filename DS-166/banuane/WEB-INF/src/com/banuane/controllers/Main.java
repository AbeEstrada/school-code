package com.banuane.controllers;
import com.banuane.helpers.*;
import com.banuane.models.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import freemarker.template.*;

public class Main extends HttpServlet {
    private Configuration cfg; 
    
    public void init() {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> data = new HashMap<String, String>();
        String[] uri = request.getRequestURI().split("\\/");
        HttpSession session = request.getSession(true);
        if (session.getAttribute("account") != null) {
            data.put("account", session.getAttribute("account").toString());
        }
        if (uri.length > 2) {
            data.put("section", uri[2]);
        } else {
            data.put("section", "home");
        }
        
        data.put("today", new Today().getString());
        
        Template t = cfg.getTemplate("base.html");
        response.setContentType("text/html; charset=utf-8");
        
        Writer out = response.getWriter();
        try {
            t.process(data, out);
        } catch (TemplateException e) {
            throw new ServletException("Error while processing template", e);
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> data = new HashMap<String, String>();
        String[] uri = request.getRequestURI().split("\\/");
        String section = "";
        HttpSession session = request.getSession(true);
        if (session.getAttribute("account") != null) {
            data.put("account", session.getAttribute("account").toString());
        }
        if (uri.length > 2) {
            section = uri[2];
        } else {
            section = "home";
        }
        
        if (section.equals("apertura")) {
            String _first_name = request.getParameter("first_name").trim();
            String _last_name = request.getParameter("last_name").trim();
            String _telephone = request.getParameter("telephone").trim();
            String _address = request.getParameter("address").trim();
            String _password = request.getParameter("password").trim();
            String _password2 = request.getParameter("password2").trim();
            String _email = request.getParameter("email").trim();
            Integer _balance = Integer.parseInt(request.getParameter("balance"));
            String _b_day = request.getParameter("day").trim();
            String _b_month = request.getParameter("month").trim();
            String _b_year = request.getParameter("year").trim();            
            
            if (
                _first_name.length() == 0 ||
                _last_name.length() == 0 ||
                _telephone.length() == 0 ||
                _address.length() == 0 ||
                _email.length() == 0 ||
                (!_password.equals(_password2)) ||
                _password.length() == 0 ||
                _balance < 0
            ) {
                System.out.println(">>> ERROR: invalid form");
            } else {
                Map<String, String> clientData = new HashMap<String, String>();
                clientData.put("first_name", _first_name);
                clientData.put("last_name", _last_name);
                clientData.put("telephone", _telephone);
                clientData.put("address", _address);
                clientData.put("email", _email);
                clientData.put("password", _password);
                clientData.put("b_day", _b_day);
                clientData.put("b_month", _b_month);
                clientData.put("b_year", _b_year);
                clientData.put("balance", _balance.toString());

                Clients client = new Clients();
                Integer newClient = client.add(clientData);
                
                if (newClient > 0) {
                    session.setAttribute("account", newClient);
                    response.sendRedirect(response.encodeRedirectURL("/banuane/micuenta/"));
                }
            }
            
        } else if (section.equals("transacciones")) {
            try {
                Integer _type = Integer.parseInt(request.getParameter("type"));
                Integer _amount = Integer.parseInt(request.getParameter("amount"));
                Integer _account = Integer.parseInt(request.getParameter("account"));
                
                if (session.getAttribute("account") != null) {
                    Map client = new Clients().get(Integer.parseInt(session.getAttribute("account").toString()));
                    Map recipient = new Clients().get(_account);
                    Map transaction = new Transactions().get(_type);
                    
                    if (!client.get("account").toString().equals(recipient.get("account").toString())) {
                        if (client != null && recipient != null && transaction != null) {
                            if (Integer.parseInt(client.get("balance").toString()) >= _amount) {
                                
                                Map<String, String> recipientData = new HashMap<String, String>();
                                Integer recipientBalance = Integer.parseInt(recipient.get("balance").toString())+_amount;
                                recipientData.put("balance", recipientBalance.toString());
                                boolean recipientUpdate = new Clients().update(_account, recipientData);
                                if (recipientUpdate == true) {
                                    Map<String, String> clientData = new HashMap<String, String>();
                                    Integer clientBalance = Integer.parseInt(client.get("balance").toString())-_amount;
                                    clientData.put("balance", clientBalance.toString());
                                    boolean clientUpdate = new Clients().update(Integer.parseInt(session.getAttribute("account").toString()), clientData);
                                    
                                    boolean saveTransaction = new Transactions().save(Integer.parseInt(session.getAttribute("account").toString()), _type, _amount);
                                    response.sendRedirect(response.encodeRedirectURL("/banuane/micuenta/"));
                                }
                                
                            } else {
                                data.put("errors", "Saldo Insuficiente");
                            }
                        }
                    } else {
                        switch(Integer.parseInt(transaction.get("type").toString())) {
                            case 1:
                                data.put("errors", "No se puede enviar dinero a la misma cuenta");
                                break;
                            case 2:
                                data.put("errors", "No se puede transferir dinero a la misma cuenta");
                                break;
                            default:
                                data.put("errors", "Error");
                                break;
                        }
                    }
                }
            } catch (NumberFormatException e) {
                data.put("errors", "Datos Inv&aacute;lidos");
            }
            
        } else if (section.equals("servicios")) {
            try {
                Integer _type = 3;
                Integer _account = Integer.parseInt(session.getAttribute("account").toString());
                Integer _amount = Integer.parseInt(request.getParameter("amount"));
                
                Map recipient = new Clients().get(_account);
                Map transaction = new Transactions().get(_type);
                
                Map<String, String> recipientData = new HashMap<String, String>();
                Integer recipientBalance = Integer.parseInt(recipient.get("balance").toString())-_amount;
                recipientData.put("balance", recipientBalance.toString());
                boolean recipientUpdate = new Clients().update(_account, recipientData);
                if (recipientUpdate == true) {
                    boolean saveTransaction = new Transactions().save(Integer.parseInt(session.getAttribute("account").toString()), _type, _amount);
                    response.sendRedirect(response.encodeRedirectURL("/banuane/micuenta/"));
                }
            } catch (NumberFormatException e) {
                data.put("errors", "Datos Inv&aacute;lidos");
            }
            
        } else if (section.equals("deposito")) {
            try {
                Integer _type = 4;
                Integer _amount = Integer.parseInt(request.getParameter("amount"));
                Integer _account = Integer.parseInt(request.getParameter("account"));
                String _currency = request.getParameter("currency");
                
                if (_currency.equals("dolares")) {
                    _amount = _amount*13;
                }
                
                Map recipient = new Clients().get(_account);
                Map transaction = new Transactions().get(_type);
                
                Map<String, String> recipientData = new HashMap<String, String>();
                Integer recipientBalance = Integer.parseInt(recipient.get("balance").toString())+_amount;
                recipientData.put("balance", recipientBalance.toString());
                boolean recipientUpdate = new Clients().update(_account, recipientData);
                if (recipientUpdate == true) {
                    boolean saveTransaction = new Transactions().save(Integer.parseInt(session.getAttribute("account").toString()), _type, _amount);
                    response.sendRedirect(response.encodeRedirectURL("/banuane/micuenta/"));
                }
            } catch (NumberFormatException e) {
                data.put("errors", "Datos Inv&aacute;lidos");
            }
            
        } else if (section.equals("cajero")) {
            try {
                Integer _type = 5;
                Integer _account = Integer.parseInt(session.getAttribute("account").toString());
                Integer _amount = Integer.parseInt(request.getParameter("amount"));

                Map recipient = new Clients().get(_account);
                Map transaction = new Transactions().get(_type);

                Map<String, String> recipientData = new HashMap<String, String>();
                Integer recipientBalance = Integer.parseInt(recipient.get("balance").toString())-_amount;
                recipientData.put("balance", recipientBalance.toString());
                boolean recipientUpdate = new Clients().update(_account, recipientData);
                if (recipientUpdate == true) {
                    boolean saveTransaction = new Transactions().save(Integer.parseInt(session.getAttribute("account").toString()), _type, _amount);
                    response.sendRedirect(response.encodeRedirectURL("/banuane/micuenta/"));
                }
            } catch (NumberFormatException e) {
                data.put("errors", "Datos Inv&aacute;lidos");
            }
        }
        
        data.put("section", section);
        data.put("today", new Today().getString());
        
        Template t = cfg.getTemplate("base.html");
        response.setContentType("text/html; charset=utf-8");
        
        Writer out = response.getWriter();
        try {
            t.process(data, out);
        } catch (TemplateException e) {
            throw new ServletException("Error while processing template", e);
        }
    }
}