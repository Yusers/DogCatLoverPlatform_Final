/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dbaccess.AccountDAO;
import dbaccess.TradeDAO;
import dbaccess.Trade_CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Trade;
import model.Trade_Category;

/**
 *
 * @author overw
 */
public class LoadTradeController extends HttpServlet {

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
            int id = Integer.parseInt(request.getParameter("id").trim());
            String edit = request.getParameter("edit");
            String url = "DispatcherController?action=trade-detail-page";
            Trade trade = TradeDAO.getTrade(id);
            Trade_Category cate = Trade_CategoryDAO.getTradeCategory(trade.getCate_id());
            request.setAttribute("AUTHOR", AccountDAO.getAccount(trade.getAuthor_id()));
            if (edit == null || edit.isEmpty()) {
                request.setAttribute("TRADE", trade);
                request.setAttribute("CATE", cate);
            } else if (edit.equals("staff")) {
                request.setAttribute("TRADE", trade);
                request.setAttribute("CATE", cate);
                url = "DispatcherController?action=trade-page-manage";
            } else if (edit.equals("true")) {
                request.setAttribute("TRADE", trade);
                request.setAttribute("CATE", cate);
                url = "DispatcherController?action=trade-edit-page";
            }
            request.getRequestDispatcher(url).forward(request, response);
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
