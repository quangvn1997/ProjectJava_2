package console.restaurant.models;

import console.restaurant.entities.Food;
import console.restaurant.utilities.ScannerUtilities;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class FoodsModel {

    public static void update(Food food) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE foods SET name=?,unit_price=?,img_url=?,description=?,updated_at=NOW() WHERE id = ?");
            pstmt.setString(1, food.getName());
            pstmt.setFloat(2, food.getUnitPrice());
            pstmt.setString(3, food.getImgUrl());
            pstmt.setString(4, food.getDescription());
            pstmt.setString(5, String.valueOf(food.getId()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
//                System.out.println("sua thanh cong");
                JOptionPane.showMessageDialog(null, "Sửa dịch vụ thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi sửa dịch vụ !!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void insertFood(Food food) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into foods(name,unit_price,img_url,description,created_at,updated_at"
                    + ") values(?,?,?,?,?,?)");
            pstmt.setString(1, food.getName());
            pstmt.setFloat(2, food.getUnitPrice());
            pstmt.setString(3, food.getImgUrl());
            pstmt.setString(4, food.getDescription());
            pstmt.setDate(5, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
            int a = pstmt.executeUpdate();
            if (a > 0) {
                //System.out.println("them thanh cong");
                JOptionPane.showMessageDialog(null, "Thêm mới dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi thêm dịch vụ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
                food.setImgUrl(rs.getString("img_url"));
                food.setDescription(rs.getString("description"));
                food.setCreatedAt(rs.getString("created_at"));
                food.setUpdateAt(rs.getString("updated_at"));
                foodList.add(food);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi lấy dịch vụ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null, "Lỗi xóa dịch vụ", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void loadFoodsSearch(JTable table, List<Food> listFood) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        listFood.forEach((food) -> {
            model.addRow(new Object[]{String.valueOf(food.getId()), food.getName(), food.getUnitPrice(), food.getImgUrl(), food.getDescription(), food.getCreatedAt()});
        });
    }
}
