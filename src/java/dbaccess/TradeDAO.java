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
import model.Trade;

/**
 *
 * @author overw
 */
public class TradeDAO {

    public static ArrayList<Trade> getAllTrade() throws Exception {
        ArrayList<Trade> trades = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [author_id], [title], [content], [status], [category], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Trade]";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String author_id = rs.getString("author_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    int category = rs.getInt("category");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    trades.add(new Trade(id, author_id, title, content, status, category));
                }
            }
            cn.close();
        }
        return trades;
    }
    
    public static ArrayList<Trade> getAllTrade(String status) throws Exception {
        ArrayList<Trade> trades = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [author_id], [title], [content], [status], [category], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Trade]\n"
                    + "WHERE [status] = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, status);
            ResultSet rs = st.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String author_id = rs.getString("author_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    int category = rs.getInt("category");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    trades.add(new Trade(id, author_id, title, content, status, category));
                }
            }
            cn.close();
        }
        return trades;
    }
    
    public static ArrayList<Trade> getAllTradeByAuthor(String author_id) throws Exception {
        ArrayList<Trade> trades = new ArrayList<>();
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Trade]\n"
                    + "WHERE [author_id] = ?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, author_id);
            ResultSet rs = st.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String status = rs.getString("status");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String rejected_reason = rs.getString("rejected_reason");
                    int category = rs.getInt("category");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    trades.add(new Trade(id, author_id, title, content, status, category, rejected_reason));
                }
            }
            cn.close();
        }
        return trades;
    }
    
    public static int createTradePost(Trade trade) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT INTO [dbo].[Trade] ([author_id],[title],[content],[status],[category],[created_at])\n"
                    + "VALUES(?,?,?,'Created',?,GETDATE())";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, trade.getAuthor_id());
            pst.setString(2, trade.getTitle());
            pst.setString(3, trade.getContent());
            pst.setInt(4, trade.getCate_id());
            rs = pst.executeUpdate();
        }
        return rs;
    }
    
    
    
    public static int ApproveTradePost(int id) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Trade]\n"
                    + "SET [status] = 'Approved'\n"
                    + "WHERE [id] = ? AND [status] = 'Created'";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static int RejectTradePost(int id, String reason) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Trade]\n"
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
    
    public static Trade getTrade(int trade_id) throws Exception {
        Trade trade = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [author_id], [title], [content], [status], [category], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Trade]\n"
                    + "WHERE [id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, trade_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String author_id = rs.getString("author_id");
                    String title = rs.getString("title");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    int category = rs.getInt("category");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    trade = new Trade(id, author_id, title, content, status, category);
                }
            }
            cn.close();
        }
        return trade;
    }
    
    public static int getTradeId(String title, String author_id) throws Exception {
        Trade trade = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [author_id], [title], [content], [status], [category], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Trade]\n"
                    + "WHERE [title] = ? AND [author_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, title);
            pst.setString(2, author_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String content = rs.getString("content");
                    String status = rs.getString("status");
                    int category = rs.getInt("category");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    trade = new Trade(id, author_id, title, content, status, category);
                }
            }
            cn.close();
        }
        return trade.getId();
    }
    
    public static boolean hasTrades(String author) throws Exception {
        boolean rs = false;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT COUNT(*)\n"
                    + "FROM [dbo].[Trade]\n"
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
    
    public static int deleteTrade(String author) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Trade]\n"
                    + "WHERE [author_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, author);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static int deleteTrade(int id) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Trade]\n"
                    + "WHERE [id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
}
