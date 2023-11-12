/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dbaccess.AccountDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author Kuuga
 */
public class EditProfileController extends HttpServlet {

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
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String description = request.getParameter("description");
            String current_password = request.getParameter("current_password");
            String new_password = request.getParameter("new_password");
            String confirm_password = request.getParameter("confirm_password");

            Account account = AccountDAO.getAccount(username);
            if (account != null) {
                //Check current password
                if (!current_password.equals(account.getPassword())) {
                    request.setAttribute("ERR_PASS", "Current password is incorrect!!");
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                }
                //Check valid fullname
                if (!Pattern.matches("^[\\p{L}\\p{M} ']+$", name)) {
                    request.setAttribute("ERR_FULLNAME", "Invalid fullname!!");
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                } //Check valid phone number
                else if (!Pattern.matches("^[0-9]{10}$", phone)) {
                    request.setAttribute("ERR_PHONE", "Invalid phone number!!");
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                } //Check valid email
                else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
                    request.setAttribute("ERR_EMAIL", "Invalid E-mail!!");
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                } //Check new password null
                else if (new_password.isEmpty()) {
                    int rs = AccountDAO.updateProfile(username, name, email, description, current_password, phone);
                    if (rs > 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", AccountDAO.getAccount(username));
                        response.sendRedirect("viewprofile.jsp");
                    } else {
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                    }
                } //Check new password null
                else if (!new_password.isEmpty()) {
                    if (new_password.length() < 6) {
                        request.setAttribute("ERR_NEW_PASS", "Password must have at least 6 characters!!");
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                    } else if (!new_password.equals(confirm_password)) {
                        request.setAttribute("ERR_CONFIRM_PASS", "Confirm password is not the same as new password!!");
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                    } else {
                        int rs = AccountDAO.updateProfile(username, name, email, description, new_password, phone);
                        if (rs > 0) {
                            HttpSession session = request.getSession();
                            session.setAttribute("USER", AccountDAO.getAccount(username));
                            response.sendRedirect("viewprofile.jsp");
                        } else {
                            request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
