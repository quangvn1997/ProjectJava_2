/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.seeds;

import java.sql.SQLException;
import java.sql.Statement;
import console.restaurant.models.DAO;

public class AdminSeed {

     public static void main(String[] args) throws SQLException {
	  // Database: online_shop
	  Statement stt = DAO.getConnection().createStatement();
	  // Thêm dữ liệu mẫu
	  stt.execute("INSERT INTO admins (username,password,name,created_at) VALUES ('admin','admin','admin',NOW());");
	  System.out.println("------ Chèn dữ liệu mẫu thành công! ------");
	  // Đăng nhập với tài khoản: admin/admin hoặc guest/guest
     }
}
