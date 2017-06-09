/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Admin;
import console.restaurant.utilities.ScannerUtilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Thang
 */
public class AdminsModel {

    public static void update(Admin admin) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE admins SET username=?,password=?,updatedAt=NOW() WHERE id = ?");
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            pstmt.setString(3, String.valueOf(admin.getId()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                System.out.println("sua thanh cong");
            }
        } catch (Exception e) {
            System.err.println("Da xay ra loi !!!" + e);
        }
    }

    public static void insertAdmin(Admin admin) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into admins(username,password"
                    + ") values(?,?)");
            pstmt.setString(1, admin.getUsername());
            pstmt.setString(2, admin.getPassword());
            int a = pstmt.executeUpdate();
            if (a > 0) {
                System.out.println("them thanh cong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Loi them san pham.");
        }
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
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setCreatedAt(rs.getString("createdAt"));
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
            System.out.println("Error!");
        }
    }

    public static void loadAdminsSearch(JTable table,List<Admin> listAdmin) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listAdmin.forEach((admin) -> {
            model.addRow(new Object[]{String.valueOf(admin.getId()),admin.getUsername(),admin.getPassword(),admin.getCreatedAt()});
        });
    }
}
