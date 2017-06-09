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
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE foods SET name=?,unit_price=?,updated_at=NOW() WHERE id = ?");
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
                    + "Insert into foods(name,unit_price"
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
                food.setUnitPrice(rs.getFloat("unit_price"));
                food.setCreatedAt(rs.getString("created_at"));
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

    public static void loadFoodsSearch(JTable table,List<Food> listFood) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listFood.forEach((food) -> {
            model.addRow(new Object[]{String.valueOf(food.getId()),food.getName(),food.getUnitPrice(),food.getCreatedAt()});
        });
    }
}
