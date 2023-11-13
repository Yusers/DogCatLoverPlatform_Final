/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import java.util.ArrayList;
import model.Trade_Media;
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
public class Trade_MediaDAO {
    
    public static Trade_Media getTradeMediaByTradeIdAndMediaId(int tradeId, int mediaId) throws Exception {
        Trade_Media trade_media = null;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT * FROM [dbo].[Trade_media] WHERE [trade_id] = ? AND [media_id] = ?";
            try (PreparedStatement pst = cn.prepareStatement(sql)) {
                pst.setInt(1, tradeId);
                pst.setInt(2, mediaId);

                try (ResultSet rs = pst.executeQuery()) {
                    while (rs.next()) {
                        int tradeMediaId = rs.getInt("trade_id");
                        int retrievedTradeId = rs.getInt("trade_id");

                        // Assuming you have a TradeMedia class to represent the entity
                        trade_media = new Trade_Media(tradeMediaId, retrievedTradeId, mediaId);
                    }
                }
            }
        }

        return trade_media;
    }

    public static int insertTradeMedia(int trade_id, int media_id) throws Exception {
        int rs = 0;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT INTO [dbo].[Trade_media] (trade_id, media_id)\n"
                    + "VALUES (?, ?)";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, trade_id);
            pst.setInt(2, media_id);
            rs = pst.executeUpdate();
        }
        return rs;
    }
    
     public static int updateTradeMedia(int tradeMediaId, int newTradeId, int newMediaId) throws Exception {
        int rowsAffected = 0;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Trade_media] SET [trade_id] = ?, [media_id] = ? WHERE [id] = ?";
            try (PreparedStatement pst = cn.prepareStatement(sql)) {
                pst.setInt(1, newTradeId);
                pst.setInt(2, newMediaId);
                pst.setInt(3, tradeMediaId);

                rowsAffected = pst.executeUpdate();

                if (rowsAffected == 0) {
                    throw new IllegalArgumentException("No Trade_media record found with trade_media_id: " + tradeMediaId);
                }
            }
        }
        return rowsAffected;
    }

    public static int deleteTradeMedia(int trade_id) throws Exception {
        int rs = 0;

        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "DELETE FROM [dbo].[Trade_media]\n"
                    + "WHERE trade_id = ?;";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, trade_id);
            rs = pst.executeUpdate();
        }
        return rs;
    }
}
