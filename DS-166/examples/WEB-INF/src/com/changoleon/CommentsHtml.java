package com.changoleon;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class CommentsHtml extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Formulario</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<form action=\".\" method=\"post\">");
        out.println("<p><label for=\"nombre\">Nombre</label><input type=\"text\" name=\"nombre\" value=\"\" id=\"nombre\" /></p>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration paramNames = request.getParameterNames();
        
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("<meta charset=\"utf-8\" />");
        out.println("<title>Formulario</title>");
        out.println("</head>");
        out.println("<body>");
        
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if (paramValues.length == 1) {
                String paramValue = paramValues[0];
                    if (paramValue.length() == 0)
                        out.print("<p><strong>"+paramName+"</strong>: Sin valor</p>");
                    else
                        out.print("<p><strong>"+paramName+"</strong>: "+paramValue+"</p>");
            } else {
                for(int i=0; i<paramValues.length; i++) {
                    out.println("<p><strong>"+paramName+" "+i+"</strong>: "+paramValues[i]+"</p>");
                }
            }
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}