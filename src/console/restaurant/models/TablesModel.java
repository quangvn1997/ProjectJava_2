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

    public Table getById(int id) {
        Table table = null;
        if (id > 0) {
            try {
                String sql = "select * from tables where id = " + id;
                Statement stt = DAO.getConnection().createStatement();
                ResultSet rs = stt.executeQuery(sql);
                if (rs.next()) {
                    table = new Table();
                    table.setId(Integer.valueOf(rs.getString("id")));
                    table.setName(rs.getString("name"));
                    table.setCreatedAt(rs.getString("created_at"));
                    table.setUpdateAt(rs.getString("updated_at"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return table;
    }

    public ArrayList<Table> getListTable(int page, int limit) {
        ArrayList<Table> listTable = new ArrayList<>();
        try {
            String strQuery = "select * from tables ";
            strQuery += "LIMIT " + limit + " OFFSET " + (page - 1) * limit;
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Table table = new Table();
                table.setId(Integer.valueOf(rs.getString("id")));
                table.setName(rs.getString("name"));
                table.setCreatedAt(rs.getString("created_at"));
                table.setUpdateAt(rs.getString("updated_at"));
                table.setStatus(rs.getInt("status"));
                listTable.add(table);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTable;
    }

    public ArrayList<Table> getAvailableTable(int page, int limit) {
        ArrayList<Table> listAvailable = new ArrayList<>();
        try {
            Connection cnn = DAO
                    .getConnection();
            Statement stt = cnn.createStatement();
            String sqlQuery = "select * from tables where status = 1 OR status = 2 limit " + limit + " offset " + (page - 1) * limit;
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

    public int countActive() {
        int count = 0;
        try {
            String strQuery = "select count(id) from tables where status = 1";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            if (rs.next()) {
                count = rs.getInt("count(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM tables WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setInt(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa bàn", "Thông báo", JOptionPane.ERROR_MESSAGE);
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

    public boolean update(Table table) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE tables SET name=?,status=?,updated_at=NOW() WHERE id = ?");
            pstmt.setString(1, table.getName());
            pstmt.setInt(2, table.getStatus());
            pstmt.setString(3, String.valueOf(table.getId()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

//    public boolean update(int status,int id) {
//        try {
//            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE tables SET status=? WHERE id = ?");
//            pstmt.setInt(1, status);
//            pstmt.setInt(2,id);
//            int a = pstmt.executeUpdate();
//            if (a > 0) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
    public boolean insertTable(Table table) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into tables(name,created_at,updated_at"
                    + ") values(?,?,?)");
            pstmt.setString(1, table.getName());
            pstmt.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
//

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

    public ArrayList<Table> searchAdmin(String searchObj) {
        ArrayList<Table> listTable = new ArrayList<>();
        try {
            String strQuery = "select * ";
            strQuery += "FROM `tables` as table_load ";
//            strQuery += "INNER join categories as category_table ";
//            strQuery += "ON food_table.category_id = category_table.id ";
            strQuery += "WHERE table_load.status = 1 AND table_load.name like '%" + searchObj + "%' " + " ORDER BY table_load.created_at DESC";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Table table = new Table();
                table.setId(Integer.valueOf(rs.getString("id")));
                table.setName(rs.getString("name"));
                table.setStatus(rs.getInt("status"));
                table.setCreatedAt(rs.getString("created_at"));
                table.setUpdateAt(rs.getString("updated_at"));
                listTable.add(table);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listTable;
    }
//    public int getTableMax() {
//
//        int id_max = 0;
//        ResultSet rs;
//        String column;
//        String strQuery = "SELECT * FROM `table_control` ORDER by id DESC";
//        try {
//            rs = DAO.getConnection().createStatement().executeQuery(strQuery);
//            while (rs.next()) {
//                id_max = Integer.valueOf(rs.getString("id"));
//                break;
//            }
//        } catch (SQLException ex) {
//            System.err.println("Có lỗi xảy ra! " + ex);
//            return 0;
//        }
//        System.out.println(id_max);
//        return id_max;
//    }
}
