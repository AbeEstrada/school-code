package com.changoleon;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import freemarker.template.*;

public class HelloTemplate extends HttpServlet {
    private Configuration cfg; 
    
    public void init() {
        cfg = new Configuration();
        cfg.setServletContextForTemplateLoading(getServletContext(), "WEB-INF/templates");
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map root = new HashMap();
        root.put("message", "Hello World!");
        
        Template t = cfg.getTemplate("test.ftl");
        
        resp.setContentType("text/html; charset=" + t.getEncoding());
        Writer out = resp.getWriter();
        
        try {
            t.process(root, out);
        } catch (TemplateException e) {
            throw new ServletException("Error while processing template", e);
        }
    }
}