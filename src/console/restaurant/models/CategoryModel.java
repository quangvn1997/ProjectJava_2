/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import console.restaurant.entities.Category;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author tuana
 */
public class CategoryModel {

    // Lấy danh sách categories đang hoạt động ra (có status = 1).
    public ArrayList<Category> getListCategory() {
        ArrayList<Category> listCategory = new ArrayList<>();
        try {
            String sqlQuery = "select * from categories where status = 1";
            Statement stt = DAO.getConnection().createStatement();
            ResultSet rs = stt.executeQuery(sqlQuery);
            while (rs.next()) {
                Category cate = new Category();
                cate.setId(Integer.parseInt(rs.getString("id")));
                cate.setName(rs.getString("name"));
                listCategory.add(cate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCategory;
    }
    
    public static void main(String[] args) {
        CategoryModel model = new CategoryModel();
        ArrayList<Category> list = model.getListCategory();
        for (Category category : list) {
            System.out.println(category.getName());
        }
    }
}
