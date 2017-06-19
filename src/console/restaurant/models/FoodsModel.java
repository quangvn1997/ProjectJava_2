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

    public Food getById(int id) {
        Food food = null;
        if (id > 0) {
            try {
                String sql = "select * from foods where id = " + id;
                Statement stt = DAO.getConnection().createStatement();
                ResultSet rs = stt.executeQuery(sql);
                if (rs.next()) {
                    food = new Food();
                    food.setId(Integer.valueOf(rs.getString("id")));
                    food.setName(rs.getString("name"));
                    food.setUnitPrice(rs.getFloat("unit_price"));
                    food.setImgUrl(rs.getString("img_url"));
                    food.setDescription(rs.getString("description"));
                    food.setCreatedAt(rs.getString("created_at"));
                    food.setUpdateAt(rs.getString("updated_at"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return food;
    }

    public static void main(String[] args) {
        FoodsModel model = new FoodsModel();
        Food food = model.getById(26);
        if (food != null) {
            System.out.println(food.getName());
        } else {
            System.out.println("Không tồn tại.");
        }

    }

    public boolean update(Food food) {
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
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean insert(Food food) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement(""
                    + "Insert into foods(name, category_id, unit_price,img_url,description,created_at,updated_at"
                    + ") values(?,?, ?, ?,?,?,?)");
            pstmt.setString(1, food.getName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setFloat(3, food.getUnitPrice());
            pstmt.setString(4, food.getImgUrl());
            pstmt.setString(5, food.getDescription());
            pstmt.setDate(6, new java.sql.Date(new java.util.Date().getTime()));
            pstmt.setDate(7, new java.sql.Date(new java.util.Date().getTime()));
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

    public ArrayList<Food> getListFood() {
        ArrayList<Food> listFood = new ArrayList<>();        
        try {
            String strQuery = "select * from foods where status = 1";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setUnitPrice(rs.getFloat("unit_price"));
                food.setImgUrl(rs.getString("img_url"));
                food.setDescription(rs.getString("description"));
                food.setCreatedAt(rs.getString("created_at"));
                food.setUpdateAt(rs.getString("updated_at"));
                listFood.add(food);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listFood;
    }

    public static void deleteFood(String id) {
        try {
            String sql = "DELETE FROM foods WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setString(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
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