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
import jakarta.servlet.http.Part;
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String name = request.getParameter("name");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            String description = request.getParameter("bio");
            String current_password = request.getParameter("current_password");
            String new_password = request.getParameter("new_password");
            String confirm_password = request.getParameter("confirm_password");
            HttpSession session = request.getSession();

            Account account = AccountDAO.getAccount(username);
//            if(account != null) {
//                out.print("userid: " + username + "<br/>");
//                out.print("name: " + name + "<br/>");
//                out.print("phone: " + phone + "<br/>");
//                out.print("email: " + email + "<br/>");
//                out.print("bio: " + description + "<br/>");
//                out.print("current: " + current_password + "<br/>");
//                out.print("confirm: " + confirm_password.isEmpty() + "<br/>");
//                out.print("new_password: " + new_password.isEmpty() + "<br/>");
//            }
            if (account != null) {
                Part part = request.getPart("image");
                String imageUrl;
                if (part != null && part.getSize() > 0) {
                    // User has uploaded a file
                    String fileName = getFileName(part);
                    String filePath = getServletContext().getRealPath("/") + "/img/" + fileName;
                    part.write(filePath);
                    String contextPath = request.getContextPath();
                    imageUrl = "http://localhost:8080" + contextPath + "/img/" + fileName;
                    AccountDAO.updateProfile(username, name, email, description, current_password, phone, imageUrl);
                    session.setAttribute("USER", AccountDAO.getAccount(username));
                } else {
                    // User did not upload a file
                    imageUrl = "NULL"; // or imageUrl = ""; // Set to a default value
                }
                //Check current password
                if (!current_password.isEmpty() || current_password != null) {
                    if (!current_password.equals(account.getPassword())) {
                        request.setAttribute("ERR_PASS", "Current password is incorrect!!");
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                    }
                }
                //Check valid fullname
                if (!Pattern.matches("^[\\p{L}\\p{M} ']+$", name)) {
                    request.setAttribute("ERR_FULLNAME", "Invalid fullname!!");
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                } else if (!Pattern.matches("^[0-9]{10}$", phone)) {
                    request.setAttribute("ERR_PHONE", "Invalid phone number!!");
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                } else if (!Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email)) {
                    request.setAttribute("ERR_EMAIL", "Invalid E-mail!!");
                    request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                } else if (new_password.isEmpty()) {
                    int rs = AccountDAO.updateProfile(username, name, email, description, current_password, phone, imageUrl);
                    if (rs > 0) {
                        session.setAttribute("USER", AccountDAO.getAccount(username));
                        response.sendRedirect("viewprofile.jsp");
                    } else {
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                    }
                } else if (!new_password.isEmpty()) {
                    if (new_password.length() < 6) {
                        request.setAttribute("ERR_NEW_PASS", "Password must have at least 6 characters!!");
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                    } else if (!new_password.equals(confirm_password)) {
                        request.setAttribute("ERR_CONFIRM_PASS", "Confirm password is not the same as new password!!");
                        request.getRequestDispatcher("editprofile.jsp").forward(request, response);
                    } else {
                        int rs = AccountDAO.updateProfile(username, name, email, description, new_password, phone, imageUrl);
                        if (rs > 0) {
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

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 2, token.length() - 1);
            }
        }
        return "";
    }

}
