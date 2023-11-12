package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Kuuga
 */
public class DispatcherController extends HttpServlet {

    final String HOME_PAGE = "index.jsp";
    final String ADMIN_PAGE = "admin.jsp";
    final String CREATE_STAFF = "create-staff.jsp";
    final String STAFF_PAGE = "staffpage.jsp";
    final String STAFF_PAGE_MANAGE_POST = "staff-manage-post.jsp";
    final String STAFF_PAGE_MANAGE_TRADE = "staff-manage-trade.jsp";
    final String MY_POST_PAGE = "manage-post.jsp";
    final String LOGIN_PAGE = "login.jsp";
    final String REGISTER_PAGE = "signup.jsp";
    final String FORUMS_PAGE = "forums.jsp";
    final String THREAD_PAGE = "thread.jsp";
    final String THREAD_DELETE = "DeletePostController";
    final String TRADE_DELETE = "DeleteTradeController";
    final String THREAD_PAGE_MANAGE = "manage-thread.jsp";
    final String THREAD_EDIT_PAGE = "edit-thread.jsp";
    final String TRADE_EDIT_PAGE = "edit-trade.jsp";
    final String TRADE_PAGE = "tradepage.jsp";
    final String TRADE_PAGE_MANAGE = "manage-trade.jsp";
    final String TRADE_CHAT = "chat.jsp";
    final String TRADE_DETAIL_PAGE = "tradingpage.jsp";

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
            String url = HOME_PAGE;
            String action = request.getParameter("action");
            if (action == null || action.isEmpty()) {
                url = HOME_PAGE;
            } else if (action.equals("login")) {
                url = "LoginController";
            } else if (action.equals("login-page")) {
                url = LOGIN_PAGE;
            } else if (action.equals("logout")) {
                url = "LogoutController";
            } else if (action.equals("register")) {
                url = "RegisterController";
            } else if (action.equals("register-page")) {
                url = REGISTER_PAGE;
            } else if (action.equals("manage")) {
                String usid = request.getParameter("usname");
                if (usid == null || usid.isEmpty()) {
                    url = "LoadAllUserController?actions=getAll";
                } else {
                    url = "LoadAllUserController";
                }
            } else if (action.equals("admin-page")) {
                url = ADMIN_PAGE;
            } else if (action.equals("create-staff")) {
                url = CREATE_STAFF;
            } else if (action.equals("forums")) {
                url = "LoadAllPostController";
            } else if (action.equals("forums-page")) {
                url = FORUMS_PAGE;
            } else if (action.equals("thread")) {
                url = "LoadPostController";
            } else if (action.equals("thread-page")) {
                url = THREAD_PAGE;
            } else if (action.equals("thread-page-manage")) {
                url = THREAD_PAGE_MANAGE;
            } else if (action.equals("thread-edit-page")) {
                url = THREAD_EDIT_PAGE;
            } else if (action.equals("thread-edit")) {
                url = "UpdatePostController";
            } else if (action.equals("thread-delete")) {
                url = THREAD_DELETE;
            } else if (action.equals("trade-delete")) {
                url = TRADE_DELETE;
            } else if (action.equals("trade-edit-page")) {
                url = TRADE_EDIT_PAGE;
            } else if (action.equals("create-post")) {
                url = "CreatePostController";
            } else if (action.equals("create-trade")) {
                url = "CreateTradeController";
            } else if (action.equals("handle-post")) {
                url = "HandlePostController";
            } else if (action.equals("handle-trade")) {
                url = "HandleTradeController";
            } else if (action.equals("trade")) {
                url = "LoadAllTradeController";
            } else if (action.equals("trade-details")) {
                url = "LoadTradeController";
            } else if (action.equals("trade-page")) {
                url = TRADE_PAGE;
            } else if (action.equals("my-trade")) {
                url = "LoadAllUserTradeController";
            } else if (action.equals("trade-detail-page")) {
                url = TRADE_DETAIL_PAGE;
            } else if (action.equals("trade-chat")) {
                url = TRADE_CHAT;
            } else if (action.equals("trade-page-manage")) {
                url = TRADE_PAGE_MANAGE;
            } else if (action.equals("comment")) {
                url = "CreateCommentController";
            } else if (action.equals("create-conversation")) {
                url = "CreateConversationController";
            } else if (action.equals("create-message")) {
                url = "CreateMessageController";
            } else if (action.equals("my-post")) {
                String us = request.getParameter("us");
                if (us == null || us.isEmpty()) {
                    url = "LoadAllPostUserController";
                } else {
                    url = "LoadAllPostUserController?us=" + us.trim();
                }
            } else if (action.equals("view-post")) {
                url = MY_POST_PAGE;
            } else if (action.equals("staff-manage")) {
                url = "LoadDataForStaffController";
            } else if (action.equals("staff-page")) {
                url = STAFF_PAGE;
            } else if (action.equals("staff-manage-post")) {
                url = STAFF_PAGE_MANAGE_POST;
            } else if (action.equals("staff-manage-trade")) {
                url = STAFF_PAGE_MANAGE_TRADE;
            }
            // Chuyển hướng đến trang tương ứng với chức năng đã chọn
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
