/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dbaccess.AccountDAO;
import dbaccess.PostDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;
import java.util.Map;
import model.Account;
import model.Post;

/**
 *
 * @author overw
 */
public class LoadAllUserController extends HttpServlet {

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
            String actions = request.getParameter("actions");
            String usname = request.getParameter("usname");
            String url = "DispatcherController?action=admin-page";
            Account acc = new Account();
            if (actions.equals("getAll")) {
                request.setAttribute("MEMBERS", AccountDAO.getAllMember());
                request.setAttribute("STAFFS", AccountDAO.getAllStaff());
                ArrayList<Post> posts = PostDAO.getAllPostinForum();
                request.setAttribute("TOTAL", posts.size());
                // Map to store post IDs for each month
                Map<Integer, List<Integer>> monthlyPostIds = new HashMap<>();

                // Process the data for the chart
                for (Post post : posts) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(post.getCreated_at());
                    int month = calendar.get(Calendar.MONTH);

                    // Get or create a list for the current month
                    List<Integer> postIds = monthlyPostIds.getOrDefault(month, new ArrayList<>());

                    // Add the post ID to the list
                    postIds.add(post.getId());

                    // Update the map
                    monthlyPostIds.put(month, postIds);
                    System.out.println("month: " + post.getCreated_at() + "\n");
                }

                // Count the number of unique post IDs in each month
                int[] monthlyCounts = new int[12];
                for (int month : monthlyPostIds.keySet()) {
                    List<Integer> postIds = monthlyPostIds.get(month);
                    int uniquePostCount = (int) postIds.stream().distinct().count();
                    monthlyCounts[month] = uniquePostCount;
                }
                
                        System.out.println("Monthly Counts: " + Arrays.toString(monthlyCounts));

                // Convert the monthlyCounts array to a JSON-like string for values
                StringBuilder jsonBuilderValues = new StringBuilder("[");
                for (int i = 0; i < monthlyCounts.length; i++) {
                    jsonBuilderValues.append(monthlyCounts[i]);
                    if (i < monthlyCounts.length - 1) {
                        jsonBuilderValues.append(", ");
                    }
                }
                jsonBuilderValues.append("]");

                // Convert the month labels array to a JSON-like string for labels
                String[] monthLabels = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
                StringBuilder jsonBuilderLabels = new StringBuilder("[");
                for (int i = 0; i < monthLabels.length; i++) {
                    jsonBuilderLabels.append("\"").append(monthLabels[i]).append("\"");
                    if (i < monthLabels.length - 1) {
                        jsonBuilderLabels.append(", ");
                    }
                }
                jsonBuilderLabels.append("]");

                // Set attributes for JSP
                request.setAttribute("MONTHLY_COUNTS", jsonBuilderValues.toString());
                request.setAttribute("MONTHLY_COUNTS_LABELS", jsonBuilderLabels.toString());
                request.setAttribute("MONTHLY_COUNTS_VALUES", jsonBuilderValues.toString());
            } else {
                if (usname != null || !usname.isEmpty()) {
                    acc = AccountDAO.getAccount(usname.trim());
                    if (acc.getFullname() != null) {
                        request.setAttribute("MEMBER", acc);
                        if (actions.equals("viewprofile")) {
                            url = "view-profile-user.jsp";
                        } else if (actions.equals("editprofileuser")) {
                            url = "editprofileuser.jsp";
                        }
                    }
                }
            }
            request.getRequestDispatcher(url).forward(request, response);
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
