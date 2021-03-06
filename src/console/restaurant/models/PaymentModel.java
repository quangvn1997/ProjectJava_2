/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Food;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Truong
 */
public class PaymentModel {

    public static List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from foods");
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setUnitPrice(rs.getFloat("unit_price"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return foodList;
        }
        return foodList;
    }

    public static List<Food> getAllIndex1() {
        List<Food> foodList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from foods where category_id = 4");
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setUnitPrice(rs.getFloat("unit_price"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return foodList;
        }
        return foodList;
    }

    public static List<Food> getAllIndex2() {
        List<Food> foodList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from foods where category_id = 5");
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setUnitPrice(rs.getFloat("unit_price"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return foodList;
        }
        return foodList;
    }

    public static List<Food> getAllIndex3() {
        List<Food> foodList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from foods where category_id = 6");
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setUnitPrice(rs.getFloat("unit_price"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return foodList;
        }
        return foodList;
    }

    public static List<Food> getAllIndex4() {
        List<Food> foodList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from foods where category_id = 7");
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setUnitPrice(rs.getFloat("unit_price"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return foodList;
        }
        return foodList;
    }
}
