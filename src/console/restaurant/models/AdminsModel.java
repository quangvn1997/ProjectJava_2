/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.controller.AdminsController;
import console.restaurant.entities.Admin;
import console.restaurant.entities.Food;
import console.restaurant.utilities.ScannerUtilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thang
 */
public class AdminsModel {

    public Admin getById(int id) {
        Admin admin = null;
        if (id > 0) {
            try {
                String sql = "select * from admins where id = " + id;
                Statement stt = DAO.getConnection().createStatement();
                ResultSet rs = stt.executeQuery(sql);
                if (rs.next()) {
                    admin = new Admin();
                    admin.setId(Integer.valueOf(rs.getString("id")));
                    admin.setName(rs.getString("name"));
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(rs.getString("password"));
                    admin.setCreatedAt(rs.getString("created_at"));
                    admin.setUpdateAt(rs.getString("updated_at"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return admin;
    }

    public boolean update(Admin admin) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE admins SET name=?,password=?,updated_at=NOW() WHERE id = ?");
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, String.valueOf(admin.getId()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertAdmin(Admin admin) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into admins(name,username,password,created_at,updated_at"
                    + ") values(?,?,?,?,?)");
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getUsername());
            pstmt.setString(3, admin.getPassword());
            pstmt.setDate(4, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<Admin> getAllAdmin() {
        List<Admin> adminList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from admins ");
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(Integer.valueOf(rs.getString("id")));
                admin.setName(rs.getString("name"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setCreatedAt(rs.getString("created_at"));
                admin.setUpdateAt(rs.getString("updated_at"));
                adminList.add(admin);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return adminList;
        }
        return adminList;
    }

    public ArrayList<Admin> getListAdmin(int page, int limit) {
        // limit = 2
        // page = 2
        ArrayList<Admin> listAdmin = new ArrayList<>();
        try {
            String strQuery = "select * from admins ";
//            strQuery += "FROM `foods` as food_table ";
//            strQuery += "INNER join categories as category_table ";
//            strQuery += "ON food_table.category_id = category_table.id ";
//            strQuery += "WHERE admins.status = 1 ORDER BY admins.created_at DESC ";
            strQuery += "LIMIT " + limit + " OFFSET " + (page - 1) * limit;
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(Integer.valueOf(rs.getString("id")));
                admin.setName(rs.getString("name"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setCreatedAt(rs.getString("created_at"));
                admin.setUpdateAt(rs.getString("updated_at"));
                listAdmin.add(admin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listAdmin;
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM admins WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setInt(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa tài khoản", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void loadAdminsSearch(JTable table, List<Admin> listAdmin) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listAdmin.forEach((admin) -> {
            model.addRow(new Object[]{String.valueOf(admin.getId()), admin.getName(), admin.getUsername(), admin.getPassword(), admin.getCreatedAt()});
        });
    }

    public int countActive() {
        int count = 0;
        try {
            String strQuery = "select count(id) from admins where status = 1";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            if (rs.next()) {
                count = rs.getInt("count(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public ArrayList<Admin> searchAdmin(String searchObj) {
        ArrayList<Admin> listAdmin = new ArrayList<>();
        try {
            String strQuery = "select * ";
            strQuery += "FROM `admins` as food_table ";
//            strQuery += "INNER join categories as category_table ";
//            strQuery += "ON food_table.category_id = category_table.id ";
            strQuery += "WHERE food_table.status = 1 AND food_table.name like '%" + searchObj + "%' " + " ORDER BY food_table.created_at DESC";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(Integer.valueOf(rs.getString("id")));
                admin.setName(rs.getString("name"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setCreatedAt(rs.getString("created_at"));
                admin.setUpdateAt(rs.getString("updated_at"));
                listAdmin.add(admin);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listAdmin;
    }
}
