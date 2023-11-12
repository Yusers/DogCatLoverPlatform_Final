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
import model.Trade_Category;

/**
 *
 * @author overw
 */
public class Trade_CategoryDAO {

    public static Trade_Category getTradeCategory(int id) throws Exception {
        Trade_Category tr = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [name], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Trade_category]\n"
                    + "WHERE [id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    tr = new Trade_Category(id, name, created_at, updated_at);
                }
            }
            cn.close();
        }

        return tr;
    }
    
    public static int createTradeCategory(String cate_name) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if(cn!=null) {
            String sql = "INSERT INTO [dbo].[Trade_category] ([name]) VALUES (?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, cate_name);
            rs = pst.executeUpdate();
            cn.close();
        }
        
        return rs;
    }
    
    public static Trade_Category getTradeCategory(String name) throws Exception {
        Trade_Category tr = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [name], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Trade_category]\n"
                    + "WHERE [name] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, name);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    tr = new Trade_Category(id, name, created_at, updated_at);
                }
            }
            cn.close();
        }

        return tr;
    }

    public static ArrayList<Trade_Category> getAllTradeCate() throws Exception {
        ArrayList<Trade_Category> list = new ArrayList<>();

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [name], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Trade_category]";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if(rs != null) {
                while(rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    list.add(new Trade_Category(id, name, created_at, updated_at));
                }
            }
            cn.close();
        }

        return list;
    }
    
    public static String getTradeCateName(int id) throws Exception {
        String catename = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [name]"
                    + "FROM [dbo].[Trade_category]\n"
                    + "WHERE [id] = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if(rs != null) {
                while(rs.next()) {
                    catename = rs.getString("name");
                }
            }
            cn.close();
        }

        return catename;
    }
}
