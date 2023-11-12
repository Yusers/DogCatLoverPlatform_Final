/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dbaccess.AccountDAO;
import dbaccess.CommentDAO;
import dbaccess.MessageDAO;
import dbaccess.PostDAO;
import dbaccess.TradeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Account;

/**
 *
 * @author Kuuga
 */
public class ActionController extends HttpServlet {

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
            String action = request.getParameter("action");
            String username = request.getParameter("username");
            Account account = AccountDAO.getAccount(username);

            if ("delete".equals(action)) {
                boolean hasPosts = PostDAO.hasPosts(username);
                boolean hasTrade = TradeDAO.hasTrades(username);
                boolean hasMessage = MessageDAO.hasMessages(username);
                boolean hasComment = CommentDAO.hasComments(username);
                if (hasPosts || hasTrade || hasMessage || hasComment) {
                    int deleteComment = CommentDAO.deleteComment(username);
                    int deleteMessage = MessageDAO.deleteMessage(username);
                    int deletePost = PostDAO.deletePost(username);
                    int deleteTrade = TradeDAO.deleteTrade(username);
                }
                int deleteAccount = AccountDAO.deleteAccount(username);
                if (deleteAccount > 0) {
                    response.sendRedirect("DispatcherController?action=staff-manage");
                }
            } else if ("ban".equals(action)) {
                int banAccount = AccountDAO.banAccount(username);
                if (banAccount > 0) {
                    response.sendRedirect("DispatcherController?action=staff-manage");
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
