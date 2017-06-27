/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Truong
 */
public class StatisticModel {
    public ArrayList<Order> getListFood(int page, int limit, String dateStart, String dateEnd) {

        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            String strQuery = "select * from orders WHERE created_at BETWEEN '"+ dateStart +"' AND '"+ dateEnd +"'";
            strQuery += " LIMIT " + limit + " OFFSET " + (page - 1) * limit;
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Order order = new Order();
                order.setId(Integer.valueOf(rs.getString("id")));
                order.setCreatedAt(rs.getDate("created_at"));
                order.setTotalPrice(rs.getFloat("total_price"));
                listOrder.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }
    public ArrayList<Order> getListOrder(int page, int limit, String dateStart, String dateEnd) {

        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            String strQuery = "select * from orders WHERE created_at BETWEEN '"+ dateStart +"' AND '"+ dateEnd +"'";
            strQuery += " LIMIT " + limit + " OFFSET " + (page - 1) * limit;
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Order order = new Order();
                order.setId(Integer.valueOf(rs.getString("id")));
                order.setCreatedAt(rs.getDate("created_at"));
                order.setTotalPrice(rs.getFloat("total_price"));
                listOrder.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }
    public ArrayList<Order> getListSatistic(int page, int limit) {

        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            String strQuery = "select * from orders WHERE status = 1 ";
            strQuery += " LIMIT " + limit + " OFFSET " + (page - 1) * limit;
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Order order = new Order();
                order.setId(Integer.valueOf(rs.getString("id")));
                order.setCreatedAt(rs.getDate("created_at"));
                order.setTotalPrice(rs.getFloat("total_price"));
                listOrder.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }
    
    public int countActive() {
        int count = 0;
        try {
            String strQuery = "select count(id) from orders where status = 1";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            if (rs.next()) {
                count = rs.getInt("count(id)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
