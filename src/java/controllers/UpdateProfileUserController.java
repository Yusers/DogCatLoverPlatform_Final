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
import jakarta.servlet.http.HttpSession;
import java.util.regex.Pattern;
import model.Account;

/**
 *
 * @author overw
 */
public class UpdateProfileUserController extends HttpServlet {

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
            String role = request.getParameter("role");
            String email = request.getParameter("email");
            String description = request.getParameter("bio");
            String current_password = request.getParameter("current_password");
            String new_password = request.getParameter("new_password");
            String confirm_password = request.getParameter("confirm_password");

            Account acc = AccountDAO.getAccount(username);

//            if(acc != null) {
//                out.print("userid: " + username + "<br/>");
//                out.print("name: " + name + "<br/>");
//                out.print("phone: " + phone + "<br/>");
//                out.print("role: " + role + "<br/>");
//                out.print("email: " + email + "<br/>");
//                out.print("bio: " + description + "<br/>");
//                out.print("current: " + current_password + "<br/>");
//                out.print("confirm: " + confirm_password.isEmpty() + "<br/>");
//                out.print("new_password: " + new_password.isEmpty() + "<br/>");
//            }
            if (acc != null) {
                // Check current password
                if (!current_password.isEmpty() || current_password != null) {
                    if (!current_password.equals(acc.getPassword())) {
                        request.setAttribute("ERR_PASS", "Current password is incorrect!!");
                        request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                    }
                }
                // Check valid fullname
                if (!Pattern.matches("^[\\p{L}\\p{M} ']+$", name)) {
                    request.setAttribute("ERR_FULLNAME", "Invalid fullname!!");
                    request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                } else if (!Pattern.matches("^[0-9]{10}$", phone)) {
                    request.setAttribute("ERR_PHONE", "Invalid phone number!!");
                    request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                } else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
                    request.setAttribute("ERR_EMAIL", "Invalid E-mail!!");
                    request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                } else if (new_password.isEmpty()) {
                    int rs = AccountDAO.updateProfileMember(username, name, email, description, current_password, phone, role);
                    if (rs > 0) {
                        HttpSession session = request.getSession();
                        session.setAttribute("USER", AccountDAO.getAccount(username));
                        response.sendRedirect("DispatcherController?action=manage");
                    } else {
                        request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                    }
                } else if (!new_password.isEmpty()) {
                    if (new_password.length() < 6) {
                        request.setAttribute("ERR_NEW_PASS", "Password must have at least 6 characters!!");
                        request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                    } else if (!new_password.equals(confirm_password)) {
                        request.setAttribute("ERR_CONFIRM_PASS", "Confirm password is not the same as new password!!");
                        request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                    } else {
                        int rs = AccountDAO.updateProfile(username, name, email, description, new_password, phone, role);
                        if (rs > 0) {
                            HttpSession session = request.getSession();
                            session.setAttribute("USER", AccountDAO.getAccount(username));
                            response.sendRedirect("DispatcherController?action=manage");
                        } else {
                            request.getRequestDispatcher("editprofileuser.jsp").forward(request, response);
                        }
                    }
                }
            }
        } catch (Exception ex) {
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
