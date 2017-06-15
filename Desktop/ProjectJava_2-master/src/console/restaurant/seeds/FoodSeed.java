/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.seeds;

import console.restaurant.models.DAO;
import java.sql.SQLException;
import java.sql.Statement;

public class FoodSeed {
    public static void main(String[] args) throws SQLException {
	  // Database: online_shop
	  Statement stt = DAO.getConnection().createStatement();
	  // Thêm dữ liệu mẫu
          stt.execute("INSERT INTO foods (name, category_id, description,img_url, unit_price, status) VALUES ('Cow Attack', '1','Cow Spicy','lol','100.000','1');");
	  System.out.println("------ Chèn dữ liệu mẫu thành công! ------");
	  // Đăng nhập với tài khoản: admin/admin hoặc guest/guest
     }
}
