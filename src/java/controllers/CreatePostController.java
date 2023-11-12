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
public class CreatePostController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String title = request.getParameter("title");
            String category = request.getParameter("category");
            String author_id = request.getParameter("author_id");
            String content = request.getParameter("content");
            Post_Category existed = Post_CategoryDAO.getPostCategory(category);

            Post post = null;
            int rs = 0;
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
                    imageUrl = "NULL"; // or imageUrl = ""; // Set to a default value
                }
                if (existed != null) {
                    post = new Post(title, existed.getId(), author_id, content, "Created", imageUrl);
                    rs = PostDAO.createPost(post);
                } else {
                    rs = Post_CategoryDAO.createPostCategory(category);
                    if (rs > 0) {
                        post = new Post(title, Post_CategoryDAO.getPostCategory(category).getId(), author_id, content, "Created", imageUrl);
                        PostDAO.createPost(post);
                    }
                }
                request.setAttribute("ERR_CONTENT", "Tạo Thành Công!");
            } else {
                request.setAttribute("ERR_CONTENT", "Bài viết phải có ít nhất 20 kí tự!!!");
            }
            request.getRequestDispatcher("create-post.jsp").forward(request, response);
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
