package com.changoleon;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class HelloWorld extends HttpServlet {
    //int contador = 0;
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<h1>Hello</h1>");
        out.println("<p>"+new Date().toString()+"</p>");
        //contador = contador + 1 ;
        //out.println("<p>"+contador+"</p>");
    }
}