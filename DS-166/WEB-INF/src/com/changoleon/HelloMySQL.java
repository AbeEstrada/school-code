package com.changoleon;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HelloMySQL extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<h1>MySQL Example</h1>");
        
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        
        String dbuser = "user";
        String dbpass = "pass";
        String dbname = "escuela";
        String dburl = "jdbc:mysql://localhost:3306/"+dbname;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dburl, dbuser, dbpass);
            
            //st = con.createStatement();
            //st.executeUpdate("INSERT INTO `alumnos` VALUES(1, 'Abraham', 'Estrada', NULL, NULL);");
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM `alumnos`");
            while(rs.next()) {
                //System.out.println(rs.getString(1)+" "+rs.getString(2));
                out.println("<p>"+rs.getString(1)+" "+rs.getString(2)+"</p>");
            }
            
            con.close();
            
        } catch (ClassNotFoundException e) {
            System.err.println(">>> Exception: "+e.getMessage());
            
        } catch(SQLException e) {
            System.err.println(">>> SQLException: "+e.getMessage());
        }
    }
}