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
import model.Chat;
import myutils.DBUtils;
import model.Conversation;

/**
 *
 * @author overw
 */
public class ConversationDAO {

    public static Conversation getConversation(String topic) throws Exception {
        Conversation conversation = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [topic], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Conversation]\n"
                    + "WHERE [topic] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, topic);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Date created_at = rs.getDate("created_at");
                    conversation = new Conversation(id, topic, created_at);
                }
            }
            cn.close();
        }

        return conversation;
    }
    
    public static Conversation getConversation(int id) throws Exception {
        Conversation conversation = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [topic], [created_at], [updated_at]\n"
                    + "FROM [dbo].[Conversation]\n"
                    + "WHERE [id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String topic = rs.getString("topic");
                    Date created_at = rs.getDate("created_at");
                    conversation = new Conversation(id, topic, created_at);
                }
            }
            cn.close();
        }

        return conversation;
    }

    public static ArrayList<Chat> getChatConversation(String user_id) throws Exception {
        ArrayList<Chat> conversation = new ArrayList<>();

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT DISTINCT [dbo].[Message].sender_id, [dbo].[Message].receiver_id,[dbo].[Conversation].[id], [dbo].[Conversation].[topic], [dbo].[Conversation].[created_at], [dbo].[Conversation].[updated_at]\n"
                    + "FROM [dbo].[Conversation]\n"
                    + "JOIN [dbo].[Message] ON [dbo].[Conversation].[id] = [dbo].[Message].[conversation_id]\n"
                    + "WHERE sender_id = ? or receiver_id = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, user_id);
            pst.setString(2, user_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String sender_id = rs.getString("sender_id");
                    String receiver_id = rs.getString("receiver_id");
                    int conver_id = rs.getInt("id");
                    String topic = rs.getString("topic");
                    Date created_at = rs.getDate("created_at");
                    Date updated_at = rs.getDate("updated_at");
                    conversation.add(new Chat(sender_id, receiver_id, conver_id, topic, created_at, updated_at));
                }
            }
            cn.close();
        }

        return conversation;
    }

    public static int getConversationID(String topic) throws Exception {
        Conversation conversation = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [created_at]"
                    + "FROM [dbo].[Conversation]\n"
                    + "WHERE [topic] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, topic);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    Date created_at = rs.getDate("created_at");
                    conversation = new Conversation(id, topic, created_at);
                }
            }
            cn.close();
        }

        return conversation.getId();
    }

    public static String getConversationTopic(int id) throws Exception {
        Conversation conversation = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [topic], [created_at]"
                    + "FROM [dbo].[Conversation]\n"
                    + "WHERE [id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String topic = rs.getString("topic");
                    Date created_at = rs.getDate("created_at");
                    conversation = new Conversation(id, topic, created_at);
                }
            }
            cn.close();
        }

        return conversation.getTopic();
    }

    public static int createConversation(String topic) throws Exception {
        int rs = 0;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT INTO [dbo].[Conversation] ([topic]) VALUES (?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, topic);
            rs = pst.executeUpdate();
        }

        return rs;
    }
}
