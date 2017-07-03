/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Food;
import console.restaurant.entities.Food;
import console.restaurant.entities.OrderDetail;
import console.restaurant.entities.Table;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class OrderDetailModel {

    public boolean insert(OrderDetail ordersDetail) {
        try {
            StringBuilder queryStringBuilder = new StringBuilder();
            queryStringBuilder.append("INSERT INTO orderdetail ");
            queryStringBuilder.append("(order_id,food_id,unit_price,quantity,total_price,status,created_at,updated_at) ");
            queryStringBuilder.append("VALUES ");
            queryStringBuilder.append("(?,?,?,?,?,?,NOW(),NOW())");
            System.out.println(queryStringBuilder.toString());
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(queryStringBuilder.toString());
            pstmt.setInt(1, ordersDetail.getOrderId());
            pstmt.setInt(2, ordersDetail.getFoodId());
            pstmt.setFloat(3, ordersDetail.getUnitPrice());
            pstmt.setFloat(4, ordersDetail.getQuantity());
            pstmt.setFloat(5, ordersDetail.getTotalPrice());
            pstmt.setFloat(6, ordersDetail.getStatus());
            System.out.println(pstmt.toString());
            int a = pstmt.executeUpdate();
            if (a > 0) {
                //System.out.println("them thanh cong");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(OrderDetail ordersDetail) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE orderdetail SET total_price=?,quantity=?,status=?,updated_at=NOW() WHERE id = ?");
            pstmt.setFloat(1, ordersDetail.getTotalPrice());
            pstmt.setInt(2, ordersDetail.getQuantity());
            pstmt.setInt(3, ordersDetail.getStatus());
            pstmt.setInt(4, ordersDetail.getId());
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
            String sql = "DELETE FROM orderdetail WHERE food_id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setInt(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<OrderDetail> byOrderID(int odersID) {
        ArrayList<OrderDetail> listAvailable = new ArrayList<>();
        try {
            Connection cnn = DAO.getConnection();
            Statement stt = cnn.createStatement();
            String sqlQuery = "select * from orderdetail where order_id =" + odersID + " AND (status = 1 OR status = 2)";
            ResultSet rs = stt.executeQuery(sqlQuery);
            while (rs.next()) {
                //Assuming you have a user object
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                orderDetail.setOrderId(rs.getInt("order_id"));
                orderDetail.setFoodId(rs.getInt("food_id"));
                orderDetail.setQuantity(rs.getInt("quantity"));
                orderDetail.setTotalPrice(rs.getInt("total_price"));
                orderDetail.setUnitPrice(rs.getInt("unit_price"));
                orderDetail.setStatus(rs.getInt("status"));
                listAvailable.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listAvailable;
    }

    public OrderDetail getByOrderIdAndFoodId(int orderId, int foodId) {
        OrderDetail orderDetail = null;
        try {
            Connection cnn = DAO.getConnection();
            Statement stt = cnn.createStatement();
            String sqlQuery = "select id from orderdetail where order_id =" + orderId + " AND food_id = " + foodId;
            ResultSet rs = stt.executeQuery(sqlQuery);
            if (rs.next()) {
                //Assuming you have a user object
                orderDetail = new OrderDetail();
                orderDetail.setId(rs.getInt("id"));
                return orderDetail;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetail;
    }
}
