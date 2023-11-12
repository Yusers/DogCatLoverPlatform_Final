/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dbaccess.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author overw
 */
public class RegisterController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String userid = request.getParameter("userid");
            String password = request.getParameter("password");
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phonenumber");

            Account existed = AccountDAO.getAccount(userid);
            // Check duplicate User name
            if (existed != null) {
                request.setAttribute("ERR_USERNAME", "Username already exists!!");
                request.getRequestDispatcher("DispatcherController?action=register-page").forward(request, response);
            } 
            //Check valid fullname
            else if (!Pattern.matches("^[\\p{L}\\p{M} ']+$", fullname)) {
                request.setAttribute("ERR_FULLNAME", "Invalid fullname!!");
                request.getRequestDispatcher("DispatcherController?action=register-page").forward(request, response);
            } 
            //Check valid phone number
            else if (!Pattern.matches("^[0-9]{10}$", phone)) {
                request.setAttribute("ERR_PHONE", "Invalid phone number!!");
                request.getRequestDispatcher("DispatcherController?action=register-page").forward(request, response);
            } 
            //Check valid email
            else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
                request.setAttribute("ERR_EMAIL", "Invalid E-mail!!");
                request.getRequestDispatcher("DispatcherController?action=register-page").forward(request, response);
            } 
            //Check valid password
            else if (password.length() < 6) {
                request.setAttribute("ERR_PASSWORD", "Password must have at least 6 characters!!");
                request.getRequestDispatcher("DispatcherController?action=register-page").forward(request, response);
            } else {
                Account account = new Account(userid.trim(), fullname.trim(), email.trim(), password.trim(), phone.trim());
                int rs = AccountDAO.createAccount(account);
                if (rs > 0) {
                    response.sendRedirect("login.jsp");
                } else {
                    request.getRequestDispatcher("DispatcherController?action=register-page").forward(request, response);
                }
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
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
