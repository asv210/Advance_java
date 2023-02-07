/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.form;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 *
 * @author APAR SOLANKI
 */
public class Registration extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    Connection cn = null;
 ResultSet rs = null;
 String driver = "org.apache.derby.jdbc.ClientDriver";
 String url = "jdbc:derby://localhost:1527/exp7";
 String query = "insert into registration values (?,?,?,?,?,?,?,?)";
 PreparedStatement pst = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String userid;
        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Registration</title>");            
            out.println("</head>");
            
            out.println("<body style=\"background-color:powderblue\">");
            try {
                Class.forName(driver);
                cn = DriverManager.getConnection(url, "exp7", "exp7");
                } 
            catch (Exception e) {
                    e.printStackTrace();
                }
            int flag = 0;
            
            try {
            pst = cn.prepareStatement(query);
            PreparedStatement pst1 = cn.prepareStatement("select * from registration where userid = ?");
            pst1.setString(1, request.getParameter("uid"));
            ResultSet rs1 = pst1.executeQuery();
            if ((request.getParameter("uid") == "") || (request.getParameter("pass") == "") || 
                (request.getParameter("cpass") == "") || (request.getParameter("fname") == "") || 
                request.getParameter("sem") == "" || request.getParameter("rno") == "" || 
                request.getParameter("email") == "" || request.getParameter("contact") == "") {
                out.println("Enter whole data!!!!!");
            } 
            else if (rs1.next() == true) {
                out.println("<p>UserID Is Already Exist</p>");
                out.println("<p>Please, Insert Correct Data: </p>");
            }
            else {
            if (request.getParameter("pass").equals(request.getParameter("cpass"))) {
                pst.setString(1, request.getParameter("uid"));
                pst.setString(2, request.getParameter("pass"));
                pst.setString(3, request.getParameter("cpass"));
                pst.setString(4, request.getParameter("fname"));
                pst.setInt(5, Integer.parseInt(request.getParameter("sem")));
                pst.setInt(6, Integer.parseInt(request.getParameter("rno")));
                pst.setString(7, request.getParameter("email"));
                pst.setString(8, request.getParameter("contact"));
                int ix = pst.executeUpdate();
                out.println(ix + " records inserted");
                userid = request.getParameter("uid");
//                session.setAttribute("akshar", userid);
                cn.close();
                flag = 1;
                }
            else {
                out.println("<p>Conform Password dosen't Matched</p>");
                out.println("<p>Please, Insert Correct Data: </p>");
                }
                }  
                } catch (Exception e) {
                }  
         if (flag == 1) {
            out.println("<p>our Data Is Inserted Successfully</p>");
            out.println("<a href=\"page.jsp\">Edit Details</a>");
         } else {
            out.println("<a href=\"index.html\">Back TO Registration</a>");
            out.println("<a href=\"page.jsp\">Edit Details</a>");
         }
          
        out.println("</body>");
        out.println("</html>");
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
    