/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import java.util.ArrayList;
import model.Media;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import myutils.DBUtils;

/**
 *
 * @author overw
 */
public class MediaDAO {

    public static ArrayList<Media> getAllMedia(int trade_id) throws Exception {
        ArrayList<Media> listMedia = new ArrayList<>();

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [dbo].[Media].[id], [dbo].[Media].[url], [dbo].[Media].[file_name]\n"
                    + "FROM [dbo].[Trade_media]\n"
                    + "JOIN [dbo].[Media] ON [dbo].[Trade_media].media_id = [dbo].[Media].id\n"
                    + "WHERE [dbo].[Trade_media].trade_id = ?;";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, trade_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String url = rs.getString("url");
                    String fileName = rs.getString("file_name");
                    listMedia.add(new Media(id, url, fileName));
                }
                rs.close(); // Close the ResultSet
            }
            cn.close();
        }
        return listMedia;
    }
    
    public static ArrayList<String> getAllMediaId(int trade_id) throws Exception {
        ArrayList<String> listMediaId = new ArrayList<>();

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [dbo].[Media].[id]\n"
                    + "FROM [dbo].[Trade_media]\n"
                    + "JOIN [dbo].[Media] ON [dbo].[Trade_media].media_id = [dbo].[Media].id\n"
                    + "WHERE [dbo].[Trade_media].trade_id = ?;";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, trade_id);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    listMediaId.add(String.valueOf(id));
                }
                rs.close(); // Close the ResultSet
            }
            cn.close();
        }
        return listMediaId;
    }

    public static Media getMediaByName(String file_name) throws Exception {
        Media media = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT [id], [url], [file_name]\n"
                    + "FROM [dbo].[Media]\n"
                    + "WHERE [file_name] = ?";

            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, file_name);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String url = rs.getString("url");
                    media = new Media(id, url, file_name);
                }
            }
        }

        return media;
    }

    public static Media getFirstMedia(int tradeId) throws Exception {
        Media media = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT TOP 1 [dbo].[Media].[url], [dbo].[Media].[file_name]\n"
                    + "FROM [dbo].[Trade_media]\n"
                    + "JOIN [dbo].[Media] ON [dbo].[Trade_media].media_id = [dbo].[Media].id\n"
                    + "WHERE [dbo].[Trade_media].trade_id = ?;";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, tradeId);
            ResultSet rs = pst.executeQuery();
            if (rs != null && rs.next()) {
                String url = rs.getString("url");
                String fileName = rs.getString("file_name");
                media = new Media(url, fileName);
                rs.close(); // Close the ResultSet
            }
            cn.close();
        }
        return media;
    }

    public static int deleteMedia() throws Exception {
        Connection cn = DBUtils.makeConnection();
        int rs = 0;
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Media]\n"
                    + "WHERE id NOT IN (SELECT media_id FROM [dbo].[Trade_media]);";
            Statement st = cn.createStatement();
            rs = st.executeUpdate(sql);
            cn.close();
        }
        return rs;
    }

    public static void insertMedia(String url, String file_name) throws Exception {
        Media media = null;
        Connection cn = DBUtils.makeConnection();

        if (cn != null) {
            String sql = "INSERT INTO [dbo].[Media] ([url], [file_name]) VALUES (?, ?)";
            PreparedStatement statement = cn.prepareStatement(sql);
            statement.setString(1, url);
            statement.setString(2, file_name);
            statement.executeUpdate();
        }
    }
    
    public static void updateMedia(int mediaId, String newUrl, String newFileName) throws Exception {
        Connection cn = DBUtils.makeConnection();

        if (cn != null) {
            String sql = "UPDATE [dbo].[Media] SET [url] = ?, [file_name] = ? WHERE [id] = ?";
            
            try (PreparedStatement statement = cn.prepareStatement(sql)) {
                statement.setString(1, newUrl);
                statement.setString(2, newFileName);
                statement.setInt(3, mediaId);

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected == 0) {
                    throw new IllegalArgumentException("No media record found with media_id: " + mediaId);
                }
            }
        }
    }

}
