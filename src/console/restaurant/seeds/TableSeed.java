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
public class TableSeed {
    public static void main(String[] args) throws SQLException {
	  // Database: online_shop
	  Statement stt = DAO.getConnection().createStatement();
	  // Thêm dữ liệu mẫu
	  stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban1','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban2','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban3','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban4','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban5','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban6','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban7','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban8','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban9','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban10','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban11','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban12','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban13','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban14','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban15','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban16','1',NOW());");
          stt.execute("INSERT INTO tables (name,status,created_at) VALUES ('ban17','1',NOW());");
          
	  System.out.println("------ Chèn dữ liệu mẫu thành công! ------");
	  // Đăng nhập với tài khoản: admin/admin hoặc guest/guest
     }
}
