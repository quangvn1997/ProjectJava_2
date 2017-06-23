package console.restaurant.models;

import console.restaurant.entities.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

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
                    food.setCategoryId(Integer.parseInt(rs.getString("category_id")));
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

    public boolean update(Food food) {
        try {
            PreparedStatement pstmt = DAO.getConnection().prepareStatement("UPDATE foods SET name=?, category_id=?,unit_price=?,img_url=?,description=?,updated_at=NOW() WHERE id = ?");
            pstmt.setString(1, food.getName());
            pstmt.setInt(2, food.getCategoryId());
            pstmt.setFloat(3, food.getUnitPrice());
            pstmt.setString(4, food.getImgUrl());
            pstmt.setString(5, food.getDescription());
            pstmt.setString(6, String.valueOf(food.getId()));
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

    public ArrayList<Food> getListFood(int page, int limit) {
        // limit = 2
        // page = 2
        ArrayList<Food> listFood = new ArrayList<>();
        try {
            String strQuery = "SELECT food_table.id, food_table.name, food_table.category_id, category_table.name as category_name, food_table.unit_price, food_table.img_url, food_table.description, food_table.created_at, food_table.updated_at, food_table.status ";
            strQuery += "FROM `foods` as food_table ";
            strQuery += "INNER join categories as category_table ";
            strQuery += "ON food_table.category_id = category_table.id ";
            strQuery += "WHERE food_table.status = 1 ORDER BY food_table.created_at DESC ";
            strQuery += "LIMIT " + limit + " OFFSET " + (page - 1) * limit;
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setCategoryId(Integer.valueOf(rs.getString("category_id")));
                food.setCategoryName(rs.getString("category_name"));
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

    public void delete(int id) {
        try {
            String sql = "DELETE FROM foods WHERE id =?";
            PreparedStatement prest = DAO.getConnection().prepareStatement(sql);
            prest.setInt(1, id);
            int val = prest.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi xóa dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }

    public ArrayList<Food> searchFood(String searchObj) {
        ArrayList<Food> listFood = new ArrayList<>();
        try {
            String strQuery = "SELECT food_table.id, food_table.name, food_table.category_id, category_table.name as category_name, food_table.unit_price, food_table.img_url, food_table.description, food_table.created_at, food_table.updated_at, food_table.status ";
            strQuery += "FROM `foods` as food_table ";
            strQuery += "INNER join categories as category_table ";
            strQuery += "ON food_table.category_id = category_table.id ";
            strQuery += "WHERE food_table.status = 1 AND food_table.name like '%" + searchObj + "%' " + " ORDER BY food_table.created_at DESC";
            ResultSet rs = DAO.getConnection().createStatement().executeQuery(strQuery);
            while (rs.next()) {
                Food food = new Food();
                food.setId(Integer.valueOf(rs.getString("id")));
                food.setName(rs.getString("name"));
                food.setCategoryId(Integer.valueOf(rs.getString("category_id")));
                food.setCategoryName(rs.getString("category_name"));
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

    public int countActive() {
        int count = 0;
        try {
            String strQuery = "select count(id) from foods where status = 1";
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
