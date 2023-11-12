/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import myutils.DBUtils;
import model.Post;

/**
 *
 * @author overw
 */
public class PostDAO {
// Hiển thị tất cả bài viết có trạng thái là Created (Trong trang quản lý bài viết của staff)
    public static ArrayList<Post> getAllPost() throws Exception {
        ArrayList<Post> posts = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [title], [category_id], [author_id], [content], [status], [rejected_reason], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Post]\n"
                    + "WHERE [status] = 'Created'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int cate_id = rs.getInt("category_id");
                    String author_id = rs.getString("author_id");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    String rejected_reason = rs.getString("rejected_reason");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    String image = rs.getString("image");
                    posts.add(new Post(id, title, cate_id, author_id, content, status, rejected_reason, created_at, updated_at, image));
                }
            }
            cn.close();
        }
        return posts;
    }
   
// Hiển thị tất cả các bài viết có trạng thái là Approved (Các bài viết đã được staff duyệt)
    public static ArrayList<Post> getAllPostinForum() throws Exception {
        ArrayList<Post> posts = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Post]\n"
                    + "WHERE [status] = 'Approved'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int cate_id = rs.getInt("category_id");
                    String author_id = rs.getString("author_id");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    String rejected_reason = rs.getString("rejected_reason");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    String image = rs.getString("image");
                    posts.add(new Post(id, title, cate_id, author_id, content, status, rejected_reason, created_at, updated_at, image));
                }
            }
            cn.close();
        }
        return posts;
    }
    
    public static ArrayList<Post> getAllPost(String status) throws Exception {
        ArrayList<Post> posts = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Post]\n"
                    + "WHERE [status] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, status);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int cate_id = rs.getInt("category_id");
                    String author_id = rs.getString("author_id");
                    String content = rs.getString("content");
                    String rejected_reason = rs.getString("rejected_reason");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    String image = rs.getString("image");
                    posts.add(new Post(id, title, cate_id, author_id, content, status, rejected_reason, created_at, updated_at, image));
                }
            }
            cn.close();
        }
        return posts;
    }
    
    public static int updatePost(int id, String title, String content, int cate_id, String imgUrl) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Post]\n"
                    + "SET [title] = ?, [content] = ?, status = 'Created', [image] = ?, [category_id] = ?, [updated_at] = GETDATE()\n"
                    + "WHERE [id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, content);
            pst.setString(3, imgUrl);
            pst.setInt(4, cate_id);
            pst.setInt(5, id);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static int ApprovePost(int id) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Post]\n"
                    + "SET [status] = 'Approved' \n"
                    + "WHERE [id] = ? AND [status] = 'Created'";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static int RejectPost(int id, String reason) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Post]\n"
                    + "SET [status] = 'Rejected', [rejected_reason] = ? \n"
                    + "WHERE [id] = ? AND [status] = 'Created'";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, reason);
            pst.setInt(2, id);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static ArrayList<Post> getAllPostByAuthor(String author_id) throws Exception {
        ArrayList<Post> posts = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Post]\n"
                    + "WHERE [author_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, author_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int cate_id = rs.getInt("category_id");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    String rejected_reason = rs.getString("rejected_reason");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    String image = rs.getString("image");
                    posts.add(new Post(id, title, cate_id, author_id, content, status, rejected_reason, created_at, updated_at, image));
                }
            }
            cn.close();
        }
        return posts;
    }

    public static Post getPost(int id) throws Exception {
        Post post = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Post]\n"
                    + "WHERE id = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    int cate_id = rs.getInt("category_id");
                    String author_id = rs.getString("author_id");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    String rejected_reason = rs.getString("rejected_reason");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    String image = rs.getString("image");
                    post = new Post(id, title, cate_id, author_id, content, status, rejected_reason, created_at, updated_at, image);
                }
            }
        }

        return post;
    }
    
    public static int createPost(Post post) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if(cn != null) {
            String sql = "INSERT INTO [dbo].[Post] (title, [category_id], author_id, [status], content, [image]) VALUES (?, ?, ?, 'Created', ?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, post.getTitle());
            pst.setInt(2, post.getCate_id());
            pst.setString(3, post.getAuthor_id());
            pst.setString(4, post.getContent());
            pst.setString(5, post.getImage());
            rs = pst.executeUpdate();
        }
        return rs;
    }

    public static int deletePost(String author) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Post]\n"
                    + "WHERE [author_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, author);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static int deletePost(int id) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Post]\n"
                    + "WHERE [id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    public static boolean hasPosts(String author) throws Exception {
        boolean rs = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM [dbo].[Post]\n"
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

    public static ArrayList<Post> getAllUserPosts(String author) throws Exception {
        ArrayList<Post> posts = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Post] \n"
                    + "WHERE [author_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, author);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int cate_id = rs.getInt("category_id");
                    String author_id = rs.getString("author_id");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    String rejected_reason = rs.getString("rejected_reason");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    String image = rs.getString("image");
                    posts.add(new Post(id, title, cate_id, author_id, content, status, rejected_reason, created_at, updated_at, image));
                }
            }
            cn.close();
        }
        return posts;
    }
}
