package console.restaurant.models;

import console.restaurant.entities.Food;
import console.restaurant.utilities.ScannerUtilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FoodsModel {
    public static void update(Food food) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE foods SET name=?,unitPrice=?,updatedAt=NOW() WHERE id = ?");
            pstmt.setString(1, food.getName());
            pstmt.setFloat(2, food.getUnitPrice());
            pstmt.setString(3, String.valueOf(food.getId()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                System.out.println("sua thanh cong");
            }
        } catch (Exception e) {
            System.err.println("Da xay ra loi !!!" + e);
        }
    }

    public static void insertFood(Food food) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into foods(name,unitPrice"
                    + ") values(?,?)");
            pstmt.setString(1, food.getName());
            pstmt.setFloat(2, food.getUnitPrice());
            int a = pstmt.executeUpdate();
            if (a > 0) {
                System.out.println("them thanh cong");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Loi them san pham.");
        }
    }

    public static List<Food> getAllFood() {
        List<Food> foodList = new ArrayList<>();
        ResultSet rs;
        int total;
        try {
            rs = DAO.getConnection().createStatement().executeQuery("select * from foods ");
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setUnitPrice(rs.getFloat("UnitPrice"));
                food.setCreatedAt(rs.getString("createdAt"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi! " + ex);
            return foodList;
        }
        return foodList;
    }

    public static void deleteFood(String id) {
        try {
            String sql = "DELETE FROM foods WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setString(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error!");
        }
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
                food.setUnitPrice(rs.getFloat("unitPrice"));
                food.setCreatedAt(rs.getString("createdAt"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            System.err.println("Có lỗi xảy ra! " + ex);
            return foodList;
        }
        return foodList;
    }

    public static void loadFood(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Food> listFood = FoodsModel.getAllFood();
        listFood.forEach((food) -> {
            model.addRow(new Object[]{String.valueOf(food.getId()),food.getName(),food.getUnitPrice(),food.getCreatedAt()});
        });
    }
    public static void loadFoodsSearch(JTable table,List<Food> listFood) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listFood.forEach((food) -> {
            model.addRow(new Object[]{String.valueOf(food.getId()),food.getName(),food.getUnitPrice(),food.getCreatedAt()});
        });
    }
}
