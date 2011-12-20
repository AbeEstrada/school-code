package com.banuane.controllers;
import com.banuane.helpers.*;
import com.banuane.models.*;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import freemarker.template.*;

public class Account extends HttpServlet {
    private Configuration cfg;
    
    public void init() {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map data = new HashMap();
        String[] uri = request.getRequestURI().split("\\/");
        HttpSession session = request.getSession(true);
        
        if (session.getAttribute("account") != null) {
            Integer _account = Integer.parseInt(session.getAttribute("account").toString());
            Map client = new Clients().get(_account);
            data.put("account", _account.toString());
            
            data.put("first_name", client.get("first_name").toString());
            data.put("last_name", client.get("last_name").toString());
            data.put("balance", client.get("balance").toString());
            
            ArrayList transactions = new Clients().transactions(_account);
            data.put("transactions", transactions);
            
            /*Iterator<Map> transaction = transactions.iterator();
            while (transaction.hasNext()) {
                Map t = transaction.next();
                
                Iterator entries = t.entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry entry = (Map.Entry) entries.next();
                    String key = (String)entry.getKey();
                    String value = (String)entry.getValue();
                    System.out.println("Key = " + key + ", Value = " + value);
                }
                System.out.println("---");
            }*/
        }
        
        data.put("section", "micuenta");
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
        HttpSession session = request.getSession(true);
        
        Integer _account = Integer.parseInt(request.getParameter("account").trim());
        String _password = request.getParameter("password").trim();
        
        boolean login = new Clients().login(_account, _password);
        if (login) {
            session.setAttribute("account", _account);
            response.sendRedirect(response.encodeRedirectURL("/banuane/micuenta/"));
        }
        
        data.put("section", "micuenta");
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