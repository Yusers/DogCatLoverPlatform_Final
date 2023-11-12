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
import model.Post_Category;

/**
 *
 * @author overw
 */
public class Post_CategoryDAO {

    public static ArrayList<Post_Category> getAllPostCategory() throws Exception {
        ArrayList<Post_Category> list = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [name], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Post_category]";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    list.add(new Post_Category(id, name, created_at, updated_at));
                }
            }
            cn.close();
        }

        return list;
    }
    
    public static Post_Category getPostCategory(int cate_id) throws Exception {
        Post_Category cate = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Post_category]\n"
                    + "WHERE id = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, cate_id);
            ResultSet rs = pst.executeQuery();
            if(rs != null) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    cate = new Post_Category(id, name, created_at, updated_at);
                }
            }
            cn.close();
        }

        return cate;
    }
    
    public static Post_Category getPostCategory(String cate_name) throws Exception {
        Post_Category cate = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Post_category]\n"
                    + "WHERE name = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, cate_name);
            ResultSet rs = pst.executeQuery();
            if(rs != null) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    cate = new Post_Category(id, name, created_at, updated_at);
                }
            }
            cn.close();
        }

        return cate;
    }
    
    public static int createPostCategory(String cate_name) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if(cn!=null) {
            String sql = "INSERT INTO [dbo].[Post_category] ([name]) VALUES (?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, cate_name);
            rs = pst.executeUpdate();
            cn.close();
        }
        
        return rs;
    }
}
