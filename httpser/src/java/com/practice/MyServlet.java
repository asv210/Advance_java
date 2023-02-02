/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.practice;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author APAR SOLANKI
 */
public class MyServlet extends HttpServlet { 
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
        System.out.println("this is get method");
        res.setContentType("text/html");
        PrintWriter out=res.getWriter();
        out.println("<h1>this is get method of my servlet </h1>");
        out.println(new Date().toString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //To change body of generated methods, choose Tools | Templates.
    }
    
}
