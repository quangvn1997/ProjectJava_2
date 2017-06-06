/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Admin;
import console.restaurant.entities.Table;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class TableModel {

    public static void insertTable(Table[] tables) {
        int a = 0;
        for (int i = 0; i < tables.length; i++) {
            try {
                PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                        + "Insert into table_control(id,name,status"
                        + ") values(?,?,0)");
                pstmt.setString(2, tables[i].getName());
                pstmt.setInt(1, tables[i].getId());
                a = pstmt.executeUpdate();

            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Loi them san pham.");
            }
        }

        if (a > 0) {
            System.out.println("them thanh cong");
        }

    }

    public static int getTableMax() {

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
