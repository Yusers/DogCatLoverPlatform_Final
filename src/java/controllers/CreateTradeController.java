/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controllers;

import dbaccess.MediaDAO;
import dbaccess.TradeDAO;
import dbaccess.Trade_CategoryDAO;
import dbaccess.Trade_MediaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.util.ArrayList;
import java.util.Collection;
import model.Trade_Category;
import model.Trade;
import model.Media;

/**
 *
 * @author overw
 */
public class CreateTradeController extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String title = request.getParameter("title");
        String category = request.getParameter("category");
        String author_id = request.getParameter("author_id");
        String content = request.getParameter("content");
        String page = "create-trade.jsp";

        try {
            Trade_Category existed = Trade_CategoryDAO.getTradeCategory(category);
            Trade trade = null;
            int rs = 0;
            if (title.length() > 10) {
                if (content.length() >= 20) {
                    if (existed != null) {
                        trade = new Trade(author_id, title, content, existed.getId());
                        rs = TradeDAO.createTradePost(trade);
                    } else {
                        rs = Trade_CategoryDAO.createTradeCategory(category);
                        if (rs > 0) {
                            trade = new Trade(author_id, title, content, Trade_CategoryDAO.getTradeCategory(category).getId());
                            rs = TradeDAO.createTradePost(trade);
                        }
                    }
                    ArrayList<String> urls = new ArrayList<>();
                    ArrayList<String> fileNames = new ArrayList<>();
                    Collection<Part> parts = request.getParts();
                    for (Part part : parts) {
                        String fileName = getFileName(part);

                        if (!fileName.isEmpty()) {
                            String filePath = getServletContext().getRealPath("/") + "/img/" + fileName;
                            part.write(filePath);

                            String contextPath = request.getContextPath();
                            String url = "http://localhost:8080" + contextPath + "/img/" + fileName;

                            urls.add(url);
                            fileNames.add(fileName);
                            out.print("File uploaded successfully!<br>");
                            out.print("URL: " + url + "<br>");
                        }
                    }
                    rs = 0;
                    ArrayList<Media> listMedia = new ArrayList<>();
                    int trade_id = TradeDAO.getTradeId(title, author_id);
                    for (int i = 0; i < urls.size(); i++) {
                        String url = urls.get(i);
                        String fileName = fileNames.get(i);
                        MediaDAO.insertMedia(url, fileName);
                        listMedia.add(MediaDAO.getMediaByName(fileName));
                    }
                    for (Media media : listMedia) {
                        rs = Trade_MediaDAO.insertTradeMedia(trade_id, media.getId());
                    }
                    if (rs > 0) {
                        request.setAttribute("ERR_CONTENT", "Tạo thành công!");
                    } else {
                        request.setAttribute("ERR_CONTENT", "Tạo thất bại!");
                    }
                } else {
                    request.setAttribute("ERR_CONTENT", "Bài viết phải có ít nhất 20 kí tự!!!");
                }
            } else {
                request.setAttribute("ERR_CONTENT", "Bài viết phải có ít nhất 10 kí tự!!!");
            }
            request.getRequestDispatcher(page).forward(request, response);
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
