/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.controller;

import console.restaurant.entities.Admin;
import console.restaurant.models.AdminsModel;
import console.restaurant.models.DAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Truong
 */
public class AdminsController {
    public static void loadAdmins(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Admin> listAdmin = AdminsModel.getAllAdmin();
        listAdmin.forEach((admin) -> {
            model.addRow(new Object[]{String.valueOf(admin.getId()),admin.getName(),admin.getUsername(),admin.getPassword(),admin.getCreatedAt()});
        });
    }
    public static List<Admin> searchAdmin(String keyword, int option) {
        List<Admin> adminList = new ArrayList<>();
        ResultSet rs;
        String column;
        switch (option) {
            case 1:
                column = "id";
                break;
            case 2:
                column = "username";
                break;
            case 3:
                column = "name";
                break;
            default:
                column = "";
                break;
        }
        
        String strQuery = "SELECT * FROM admins WHERE " + column + " LIKE '%"
                + keyword + "%';";
        try {
            rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Admin admin = new Admin();
                admin.setId(Integer.valueOf(rs.getString("id")));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admin.setCreatedAt(rs.getString("created_at"));
                adminList.add(admin);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi xảy ra! " + ex);
            return adminList;
        }
        return adminList;
    }
}
