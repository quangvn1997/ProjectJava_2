/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.controller;

import console.restaurant.entities.Food;
import console.restaurant.models.DAO;
import console.restaurant.models.FoodsModel;
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
public class FoodsController {
    public static void loadFood(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Food> listFood = FoodsModel.getAllFood();
        
        listFood.forEach((food) -> {
            model.addRow(new Object[]{String.valueOf(food.getId()), food.getName(), food.getUnitPrice(), food.getImgUrl(), food.getDescription(), food.getCreatedAt(), food.getUpdateAt()});
        });
    }
    public static List<Food> searchFood(String keyword, int option) {
        List<Food> foodList = new ArrayList<>();
        ResultSet rs;
        String column;
        switch (option) {
            case 1:
                column = "id";
                break;
            case 2:
                column = "name";
                break;
            default:
                column = "";
                break;
        }
        
        String strQuery = "SELECT * FROM foods WHERE " + column + " LIKE '%"
                + keyword + "%';";
        try {
            rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setImgUrl(rs.getString("img_url"));
                food.setUnitPrice(rs.getFloat("unit_price"));
                food.setCreatedAt(rs.getString("created_at"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi xảy ra! " + ex);
            return foodList;
        }
        return foodList;
    }
}
