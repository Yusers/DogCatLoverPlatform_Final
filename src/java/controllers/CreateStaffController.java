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
public class CreateStaffController extends HttpServlet {

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
            String fullname = request.getParameter("fullname");
            String email = request.getParameter("email");
            String phone = request.getParameter("phonenumber");
            String username = email.split("@")[0];
            String url = "DispatcherController?action=manage";
            //Check valid fullname
            if (!Pattern.matches("^[\\p{L}\\p{M} ']+$", fullname)) {
                request.setAttribute("ERR_FULLNAME", "Họ và tên không hợp lệ !");
                url = "DispatcherController?action=create-staff";
            } //Check valid phone number
            else if (!Pattern.matches("^[0-9]{10}$", phone)) {
                request.setAttribute("ERR_PHONE", "Số điện thoại không hợp lệ !");
                url = "DispatcherController?action=create-staff";
            } //Check valid email
            else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
                request.setAttribute("ERR_EMAIL", "Email không hợp lệ !");
                url = "DispatcherController?action=create-staff";
            } else {
                Account account = new Account(username.trim(), fullname.trim(), email.trim(), phone.trim());
                int rs = AccountDAO.createNewStaff(account);
                if (rs < 0) {
                    url = "DispatcherController?action=create-staff";
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
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
