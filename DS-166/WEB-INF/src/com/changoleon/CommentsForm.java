package com.changoleon;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import freemarker.template.*;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        
        String _first_name = ("".equals(request.getParameter("first_name"))) ? "" : request.getParameter("first_name");
        params.put("first_name", _first_name);
        
        String _last_name = ("".equals(request.getParameter("last_name"))) ? "" : request.getParameter("last_name");
        params.put("last_name", _last_name);
        
        String _comments = ("".equals(request.getParameter("comments"))) ? "" : request.getParameter("comments");
        params.put("comments", _comments);
        
        if (_first_name.length() != 0 && _last_name.length() != 0 && _comments.length() != 0) {
            Connection con = null;
            Statement st = null;
            
            String dbuser = "";
            String dbpass = "";
            String dbname = "";
            String dburl = "jdbc:mysql://localhost:3306/"+dbname;
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(dburl, dbuser, dbpass);
                
                st = con.createStatement();
                st.executeUpdate("INSERT INTO `comments` (`first_name`, `last_name`, `comments`) VALUES('"+_first_name+"', '"+_last_name+"', '"+_comments+"');");
                
                con.close();
                
            } catch (ClassNotFoundException e) {
                System.err.println(">>> Exception: "+e.getMessage());
                
            } catch(SQLException e) {
                System.err.println(">>> SQLException: "+e.getMessage());
            }
        }
        
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