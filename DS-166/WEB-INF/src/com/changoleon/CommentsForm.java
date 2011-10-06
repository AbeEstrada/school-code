package com.changoleon;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import freemarker.template.*;

public class CommentsForm extends HttpServlet {
    private Configuration cfg; 
    
    public void init() {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map root = new HashMap();
        root.put("title", "Comentarios");
        
        Template t = cfg.getTemplate("commentsform.html");
        
        response.setContentType("text/html; charset=" + t.getEncoding());
        Writer out = response.getWriter();
        
        try {
            t.process(root, out);
        } catch (TemplateException e) {
            throw new ServletException("Error while processing template", e);
        }
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map root = new HashMap();
        root.put("title", "Resultados");
        
        Enumeration paramNames = request.getParameterNames();
        
        Map params = new HashMap();
        root.put("params", params);
        
        String _first_name = request.getParameter("first_name");
        params.put("first_name", _first_name);
        
        String _last_name = request.getParameter("last_name");
        params.put("last_name", _last_name);
        
        String _comments = request.getParameter("comments");
        params.put("comments", _comments);
        
        /*while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                    if (paramValue.length() == 0)
                        params.put(paramName, "");
                    else
                        params.put(paramName, paramValue);
            }
        }*/
        
        Template t = cfg.getTemplate("commentsresult.html");
        
        response.setContentType("text/html; charset=" + t.getEncoding());
        Writer out = response.getWriter();
        
        try {
            t.process(root, out);
        } catch (TemplateException e) {
            throw new ServletException("Error while processing template", e);
        }
    }
}