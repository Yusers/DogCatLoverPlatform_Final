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
