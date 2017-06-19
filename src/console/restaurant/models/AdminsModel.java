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
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE admins SET name=?,username=?,password=?,updated_at=NOW() WHERE id = ?");
            pstmt.setString(1, admin.getName());
            pstmt.setString(2, admin.getUsername());
            pstmt.setString(3, admin.getPassword());
            pstmt.setString(4, String.valueOf(admin.getId()));
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

    public static void deleteAdmin(String id) {
        try {
            String sql = "DELETE FROM admins WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setString(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa tài khoản", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void loadAdminsSearch(JTable table, List<Admin> listAdmin) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listAdmin.forEach((admin) -> {
            model.addRow(new Object[]{String.valueOf(admin.getId()), admin.getName(), admin.getUsername(), admin.getPassword(), admin.getCreatedAt()});
        });
    }
}
