/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dbaccess.PostDAO;
import dbaccess.Post_CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Post;
import model.Post_Category;

/**
 *
 * @author overw
 */
public class UpdatePostController extends HttpServlet {

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
            String id = request.getParameter("id");
            int post_id = Integer.parseInt(id.trim());
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String content = request.getParameter("content");
            String url = "DispatcherController?action=my-post";
            Post_Category existed = Post_CategoryDAO.getPostCategory(category);
            int rs = 0;
            boolean flag = false;
            request.setAttribute("ACTION", "Update");
            if (content.length() >= 20) {
                Part part = request.getPart("image");
                String imageUrl;
                if (part != null && part.getSize() > 0) {
                    // User has uploaded a file
                    String fileName = getFileName(part);
                    String filePath = getServletContext().getRealPath("/") + "/img/" + fileName;
                    part.write(filePath);
                    String contextPath = request.getContextPath();
                    imageUrl = "http://localhost:8080" + contextPath + "/img/" + fileName;
                } else {
                    // User did not upload a file
                    imageUrl = ""; // or imageUrl = ""; // Set to a default value
                }
                if (existed != null) {
                    PostDAO.updatePost(post_id, title, content, existed.getId(), imageUrl);
                    flag = true;
                } else {
                    rs = Post_CategoryDAO.createPostCategory(category);
                    if (rs > 0) {
                        PostDAO.updatePost(post_id, title, content, Post_CategoryDAO.getPostCategory(category).getId(), imageUrl);
                        flag = true;
                    } else {
                        flag = false;
                    }
                }
            } else {
                flag = false;
            }
            if (flag) {
                request.setAttribute("STATUS", true);
                request.setAttribute("MSG", "update successfully!");
            } else {
                request.setAttribute("STATUS", false);
                request.setAttribute("MSG", "update successfully!");
            }
            request.getRequestDispatcher(url).forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
