/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.seeds;

import console.restaurant.models.DAO;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Asus
 */
public class OrdersSeed {
     public static void main(String[] args) throws SQLException {
	  // Database: online_shop
	  Statement stt = DAO.getConnection().createStatement();
	  // Thêm dữ liệu mẫu'
	  stt.execute("INSERT INTO `orders`(`id`, `created_at`, `updated_at`, `total_price`, `discount`, `real_price`, `table_id`, `status`) VALUES ('1','2017-06-28','2017-06-28','1000','900','10','1','1')");
          stt.execute("INSERT INTO `orders`(`id`, `created_at`, `updated_at`, `total_price`, `discount`, `real_price`, `table_id`, `status`) VALUES ('2','2017-06-1','2017-06-1','1000','900','10','2','1')");
          stt.execute("INSERT INTO `orders`(`id`, `created_at`, `updated_at`, `total_price`, `discount`, `real_price`, `table_id`, `status`) VALUES ('3','2017-06-5','2017-06-5','1000','900','10','3','1')");
          stt.execute("INSERT INTO `orders`(`id`, `created_at`, `updated_at`, `total_price`, `discount`, `real_price`, `table_id`, `status`) VALUES ('4','2017-06-10','2017-06-10','1000','900','10','4','1')");
          stt.execute("INSERT INTO `orders`(`id`, `created_at`, `updated_at`, `total_price`, `discount`, `real_price`, `table_id`, `status`) VALUES ('5','2017-06-15','2017-06-15','1000','900','10','5','1')");
	  System.out.println("------ Chèn dữ liệu mẫu thành công! ------");
	  // Đăng nhập với tài khoản: admin/admin hoặc guest/guest
     }
}
