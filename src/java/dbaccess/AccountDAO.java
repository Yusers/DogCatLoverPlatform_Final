/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dbaccess;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import myutils.DBUtils;

/**
 *
 * @author Kuuga
 */
public class AccountDAO {

    public static Account checkLogin(String id, String password) throws Exception {
        Connection cn = DBUtils.makeConnection();
        Account account = null;
        if (cn != null) {
            //b2: viet sql va exec
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Account]\n"
                    + "WHERE [user_id] = ? AND [password] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, id);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String user_id = rs.getString("user_id");
                    String name = rs.getString("fullname");
                    String email = rs.getString("email");
                    String pass = rs.getString("password");
                    String phonenumber = rs.getString("phone_number");
                    String des = rs.getString("description");
                    String roleID = rs.getString("role");
                    String status = rs.getString("status");
                    String avatar = rs.getString("avatar");
                    Date createdat = rs.getDate("created_at");
                    Date updatedat = rs.getDate("updated_at");
                    account = new Account(user_id, name, email, pass, phonenumber, des, roleID, status, createdat, updatedat, avatar);
                }
            }
            cn.close();
        }
        return account;
    }
    
    public static int createNewStaff(Account acc) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT INTO Account ( user_id,fullname, email, password, phone_number, description, role, status,created_at)\n"
                    + "VALUES \n"
                    + "( ?, ?, ?, ?, ?, ?, ?, ?, GETDATE())";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, acc.getUser_id());
            pst.setString(2, acc.getFullname());
            pst.setString(3, acc.getEmail());
            pst.setString(4, "staff123");
            pst.setString(5, acc.getPhone_number());
            pst.setString(6, "Không có mô tả");
            pst.setString(7, "STAFF");
            pst.setString(8, "Active");
            rs = pst.executeUpdate();
            cn.close();
        }

        return rs;
    }

    public static int createAccount(Account acc) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "INSERT INTO Account ( user_id,fullname, email, password, phone_number, description, role, status)\n"
                    + "VALUES \n"
                    + "    ( ?, ?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, acc.getUser_id());
            pst.setString(2, acc.getFullname());
            pst.setString(3, acc.getEmail());
            pst.setString(4, acc.getPassword());
            pst.setString(5, acc.getPhone_number());
            pst.setString(6, "Không có mô tả");
            pst.setString(7, "MEMBER");
            pst.setString(8, "Active");
            rs = pst.executeUpdate();
            cn.close();
        }

        return rs;
    }

    public static ArrayList<Account> getAllMember() throws Exception {
        Connection cn = DBUtils.makeConnection();
        ArrayList<Account> list = new ArrayList<>();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Account]\n"
                    + "WHERE [role] LIKE 'MEMBER'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("user_id");
                    String name = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone_number = rs.getString("phone_number");
                    String des = rs.getString("description");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    String avatar = rs.getString("avatar");
                    Date createdat = rs.getDate("created_at");
                    Account account = new Account(id, name, email, phone_number, des, role, status, createdat, avatar);
                    list.add(account);
                }
            }
            cn.close();
        }
        return list;
    }

    public static ArrayList<Account> getAllStaff() throws Exception {
        Connection cn = DBUtils.makeConnection();
        ArrayList<Account> list = new ArrayList<>();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Account]\n"
                    + "WHERE [role] LIKE 'STAFF'";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("user_id");
                    String name = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone_number = rs.getString("phone_number");
                    String des = rs.getString("description");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    String avatar = rs.getString("avatar");
                    Date createdat = rs.getDate("created_at");
                    Account account = new Account(id, name, email, phone_number, des, role, status, createdat, avatar);
                    list.add(account);
                }
            }
            cn.close();
        }
        return list;
    }

    public static Account getAccount(String userid) throws Exception {
        Account account = null;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Account]\n"
                    + "WHERE [user_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, userid);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("user_id");
                    String name = rs.getString("fullname");
                    String email = rs.getString("email");
                    String pass = rs.getString("password");
                    String phone_number = rs.getString("phone_number");
                    String des = rs.getString("description");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    String avatar = rs.getString("avatar");
                    Date createdat = rs.getDate("created_at");
                    Date updatedat = rs.getDate("updated_at");
                    account = new Account(id, name, email, pass, phone_number, des, role, status, createdat, updatedat, avatar);
                }
            }
            cn.close();
        }
        return account;
    }

    public static int banAccount(String username) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Account]\n"
                    + "SET [status] = CASE\n"
                    + "    WHEN [status] = 'Active' THEN 'Inactive'\n"
                    + "    ELSE 'Active'\n"
                    + "END\n"
                    + "WHERE [user_id] LIKE ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    public static int deleteAccount(String username) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "delete from [dbo].[Account] \n"
                    + "where [user_id] = ?";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, username);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }

    public static ArrayList<Account> searchStaff(String search) throws Exception {
        Connection cn = DBUtils.makeConnection();
        ArrayList<Account> list = new ArrayList<>();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Account]\n"
                    + "WHERE [user_id] LIKE ? OR [fullname] LIKE ? AND [role] LIKE 'STAFF'";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, search);
            pst.setString(2, "%" + search + "%");
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("user_id");
                    String name = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone_number = rs.getString("phone_number");
                    String des = rs.getString("description");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    String avatar = rs.getString("avatar");
                    Date createdat = rs.getDate("created_at");
                    Account account = new Account(id, name, email, phone_number, des, role, status, createdat, avatar);
                    list.add(account);
                }
            }
            cn.close();
        }
        return list;
    }
    
    public static ArrayList<Account> searchMember(String search) throws Exception {
        Connection cn = DBUtils.makeConnection();
        ArrayList<Account> list = new ArrayList<>();
        if (cn != null) {
            String sql = "SELECT *\n"
                    + "FROM [dbo].[Account]\n"
                    + "WHERE ([fullname] LIKE ?) AND [role] = 'MEMBER'";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1,"%" + search);
            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    String id = rs.getString("user_id");
                    String name = rs.getString("fullname");
                    String email = rs.getString("email");
                    String phone_number = rs.getString("phone_number");
                    String des = rs.getString("description");
                    String role = rs.getString("role");
                    String status = rs.getString("status");
                    String avatar = rs.getString("avatar");
                    Date createdat = rs.getDate("created_at");
                    Account account = new Account(id, name, email, phone_number, des, role, status, createdat, avatar);
                    list.add(account);
                }
            }
            cn.close();
        }
        return list;
    }
    
    public static int updateProfile(String user_name, String fullname, String email, String description, String password, String phone, String img) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Account]\n"
                    + "SET [user_id] = ?, [fullname] = ?, [email] = ?, [description] = ?, [password] = ?, [phone_number] = ?, [updated_at] = GETDATE(), [avatar] = ?\n"
                    + "WHERE [user_id] = ? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, user_name);
            pst.setString(2, fullname);
            pst.setString(3, email);
            pst.setString(4, description);
            pst.setString(5, password);
            pst.setString(6, phone);
            pst.setString(7, img);
            pst.setString(8, user_name);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
    
    public static int updateProfileMember(String user_name, String fullname, String email, String description, String password, String phone, String role) throws Exception {
        int rs = 0;
        Connection cn = DBUtils.makeConnection();
        if (cn != null) {
            String sql = "UPDATE [dbo].[Account]\n"
                    + "SET [user_id] = ?, [fullname] = ?, [email] = ?, [description] = ?, [password] = ?, [phone_number] = ?, [updated_at] = GETDATE(), role = ?\n"
                    + "WHERE [user_id] = ? ";
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, user_name);
            pst.setString(2, fullname);
            pst.setString(3, email);
            pst.setString(4, description);
            pst.setString(5, password);
            pst.setString(6, phone);
            pst.setString(7, role);
            pst.setString(8, user_name);
            rs = pst.executeUpdate();
            cn.close();
        }
        return rs;
    }
}
