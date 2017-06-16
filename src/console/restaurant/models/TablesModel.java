/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Admin;
import console.restaurant.entities.Food;
import console.restaurant.entities.Table;
import java.sql.Connection;
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
 * @author Anh Tiến ơi.Có Trộm!
 */
public class TablesModel {

    public ArrayList<Table> getAvailableTable(int page, int limit) {
        ArrayList<Table> listAvailable = new ArrayList<>();
        try {
            Connection cnn = DAO
                    .getConnection();
            Statement stt = cnn.createStatement();
            String sqlQuery = "select * from tables where status = 1 limit " + limit + " offset " + (page - 1) * limit;
            System.out.println(sqlQuery);
            ResultSet rs = stt.executeQuery(sqlQuery);
            while (rs.next()) {
                //Assuming you have a user object
                Table table = new Table();
                table.setId(rs.getInt("id"));
                table.setName(rs.getString("name"));
                table.setStatus(rs.getInt("status"));
                listAvailable.add(table);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAvailable;
    }

    public int getCountTable() {
        try {
            Connection cnn = DAO
                    .getConnection();
            Statement stt = cnn.createStatement();
            String sqlQuery = "select count from tables";
            System.out.println(sqlQuery);
            ResultSet rs = stt.executeQuery(sqlQuery);

        } catch (Exception e) {
        }
        return 0;

    }

    public static void deleteAdmin(String id) {
        try {
            String sql = "DELETE FROM tables WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setString(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa bàn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        TablesModel model = new TablesModel();
        ArrayList<Table> list = model.getAvailableTable(0, 0);
        for (Table tbl : list) {
            System.out.println(tbl.getId());
            System.out.println(tbl.getName());
        }
    }

    public static void insertTable(Table table) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into tables(name,created_at,updated_at"
                    + ") values(?,?,?)");
            pstmt.setString(1, table.getName());
            pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                JOptionPane.showMessageDialog(null, "Thêm mới bàn thành công !", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm bàn", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

//    public void insertTable(Table[] tables) {
//        int a = 0;
//        for (int i = 0; i < tables.length; i++) {
//            try {
//                PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
//                        + "Insert into table_control(id,name,status"
//                        + ") values(?,?,0)");
//                pstmt.setString(2, tables[i].getName());
//                pstmt.setInt(1, tables[i].getId());
//                a = pstmt.executeUpdate();
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                System.err.println("Loi them san pham.");
//            }
//        }
//
//        if (a > 0) {
//            System.out.println("them thanh cong");
//        }
//
//    }
    public static List<Table> getAllTable() {
        List<Table> tableList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from tables ");
            while (rs.next()) {
                Table table = new Table();
                table.setId(Integer.valueOf(rs.getString("id")));
                table.setName(rs.getString("name"));
                table.setStatus(rs.getInt("status"));
                table.setCreatedAt(rs.getString("created_at"));
                table.setUpdateAt(rs.getString("updated_at"));
                tableList.add(table);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return tableList;
        }
        return tableList;
    }

    public int getTableMax() {

        int id_max = 0;
        ResultSet rs;
        String column;
        String strQuery = "SELECT * FROM `table_control` ORDER by id DESC";
        try {
            rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                id_max = Integer.valueOf(rs.getString("id"));
                break;
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi xảy ra! " + ex);
            return 0;
        }
        System.out.println(id_max);
        return id_max;
    }

}
