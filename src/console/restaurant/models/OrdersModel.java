/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Order;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class OrdersModel {

    public static void main(String[] args) {
        OrdersModel model = new OrdersModel();
        Order order = new Order();
        order.setTotalPrice(10000);
        order.setDiscount(10);
        order.setRealPrice(9000);
        order.setTableId(1);
        model.insert(order);
    }

    public Order getTableCurrentOrder(int tableId) {
        Order orders = null;
        {
            try {
                String sql = "select * from orders where table_id = " + tableId + " AND status = 2";
                System.out.println(sql);
                Statement stt = DAO.getConnection().createStatement();
                ResultSet rs = stt.executeQuery(sql);
                if (rs.next()) {
                    orders = new Order();
                    orders.setId(rs.getInt("id"));
                    orders.setStatus(rs.getInt("status"));
                    orders.setDiscount(rs.getInt("discount"));
                    orders.setTotalPrice(rs.getInt("total_price"));
                    orders.setRealPrice(rs.getInt("real_price"));
                    orders.setTableId(rs.getInt("table_id"));
                    orders.setCreatedAt(rs.getDate("created_at"));
                    orders.setUpdatedAt(rs.getDate("updated_at"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

    public int insert(Order order) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into orders(total_price,discount,real_price,table_id,status,created_at,updated_at"
                    + ") values(?,?,?,?,?,NOW(),NOW())", Statement.RETURN_GENERATED_KEYS);
            pstmt.setFloat(1, order.getTotalPrice());
            pstmt.setInt(2, order.getDiscount());
            pstmt.setFloat(3, order.getRealPrice());
            pstmt.setInt(4, order.getTableId());
            pstmt.setInt(5, order.getStatus());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean update(Order orders) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE orders SET total_price=?,discount=?,real_price=?,table_id=?,status=?,updated_at=NOW() WHERE id = ?");
            pstmt.setFloat(1, orders.getTotalPrice());
            pstmt.setInt(2, orders.getDiscount());
            pstmt.setFloat(3, orders.getRealPrice());
            pstmt.setInt(4, orders.getTableId());
            pstmt.setInt(5, orders.getStatus());
            pstmt.setInt(6, orders.getId());
            int a = pstmt.executeUpdate();
            if (a > 0) {
//                System.out.println("sua thanh cong");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void delete(int id) {
        try {
            String sql = "DELETE FROM orders WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setInt(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }
    //trả về id của orders vừa tạo mới nhất cho bàn;

    public static int getOrdersNew() {
        int id_max = 0;
        ResultSet rs;
        String column;
        String strQuery = "SELECT * FROM orders ORDER by id DESC";
        try {
            rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                id_max = rs.getInt("id");
                break;
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi xảy ra! " + ex);
            return 0;
        }
        System.out.println(id_max);
        return id_max;
    }

    public Order getById(int id) {
        Order orders = null;
        if (id > 0) {
            try {
                String sql = "select * from orders where id = " + id;
                Statement stt = DAO.getConnection().createStatement();
                ResultSet rs = stt.executeQuery(sql);
                if (rs.next()) {
                    orders = new Order();
                    orders.setId(rs.getInt("id"));
                    orders.setCreatedAt(rs.getDate("created_at"));
                    orders.setStatus(rs.getInt("status"));
                    orders.setDiscount(rs.getInt("discount"));
                    orders.setRealPrice(rs.getInt("real_price"));
                    orders.setTableId(rs.getInt("table_id"));
                    orders.setCreatedAt(rs.getDate("created_at"));
                    orders.setUpdatedAt(rs.getDate("updated_at"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return orders;
    }

}
