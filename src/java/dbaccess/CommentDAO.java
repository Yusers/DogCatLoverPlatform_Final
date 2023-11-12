/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import myutils.DBUtils;
import model.Comment;

/**
 *
 * @author overw
 */
public class CommentDAO {

    public static ArrayList<Comment> getAllComment(int posts_id) throws Exception {
        ArrayList<Comment> list = new ArrayList<>();

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT * FROM [dbo].[Comment] WHERE [post_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, posts_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String author_id = rs.getString("author_id");
                    int post_id = rs.getInt("post_id");
                    String content = rs.getString("content");
                    int parent_id = rs.getInt("parent_id");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    list.add(new Comment(id, author_id, post_id, content, parent_id, created_at, updated_at));
                }
            }
            cn.close();
        }

        return list;
    }
    
    public static ArrayList<Comment> getSubComment(int post_id, int parent_id) throws Exception {
        ArrayList<Comment> list = new ArrayList<>();
        
        Connection cn = DBUtils.makeConnection();
        if(cn!=null) {
            String sql = "SELECT * FROM [dbo].[Comment] WHERE [post_id] = ? and [parent_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, post_id);
            pst.setInt(2, parent_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String author_id = rs.getString("author_id");
                    String content = rs.getString("content");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    list.add(new Comment(id, author_id, post_id, content, parent_id, created_at, updated_at));
                }
            }
            cn.close();
        }
        
        return list;
    }

    public static int createComment(Comment comment) throws Exception {
        int rs = 0;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT INTO [dbo].[Comment] ([author_id], [post_id], [content], [parent_id])\n"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, comment.getAuthor_id());
            pst.setInt(2, comment.getPost_id());
            pst.setString(3, comment.getContent());
            pst.setInt(4, comment.getParent_id());
            rs = pst.executeUpdate();
        }

        return rs;
    }
    
    public static boolean hasComments(String author) throws Exception {
        boolean rs = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM [dbo].[Comment]\n"
                    + "WHERE author_id = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, author);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                int postCount = resultSet.getInt(1);
                rs = (postCount > 0);
            }
            cn.close();
        }
        return rs;
    }
    
    public static int deleteComment(int post_id) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Comment]\n"
                    + "WHERE [post_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, post_id);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static int deleteComment(String author) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Comment]\n"
                    + "WHERE [author_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, author);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
}
