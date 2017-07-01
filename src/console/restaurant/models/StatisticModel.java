/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Order;
import console.restaurant.view.ManagerStatistic;
import java.sql.Date;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Truong
 */
public class StatisticModel {

    public ArrayList<Order> getListOrder(int page, int limit, Date day1, Date day2) {
        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            String strQuery = "select orders.*,tables.name as name from orders ";
            strQuery += "LEFT JOIN tables ";
            strQuery += "ON orders.table_id = tables.id ";
            strQuery += "WHERE orders.created_at BETWEEN '" + day1 + "' AND '" + day2 + "'AND orders.status = 1";
            strQuery += " LIMIT " + limit + " OFFSET " + (page - 1) * limit;
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Order order = new Order();
                order.setId(Integer.valueOf(rs.getString("id")));
                order.setCreatedAt(rs.getDate("created_at"));
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setRealPrice(rs.getFloat("real_price"));
                order.setDiscount(rs.getInt("discount"));
                order.setTableId(rs.getInt("table_id"));
                order.setTableName(rs.getString("name"));
                listOrder.add(order);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listOrder;
    }

    public ArrayList<Order> getListOrder(Date day1, Date day2) {

        ArrayList<Order> listOrder = new ArrayList<>();
        try {
            String strQuery = "select * from orders WHERE created_at BETWEEN '" + day1 + "' AND '" + day2 + "'";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Order order = new Order();
                order.setId(Integer.valueOf(rs.getString("id")));
                order.setCreatedAt(rs.getDate("created_at"));
                order.setTotalPrice(rs.getFloat("total_price"));
                order.setRealPrice(rs.getFloat("real_price"));
                order.setDiscount(rs.getInt("discount"));
                order.setTableId(rs.getInt("table_id"));
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
