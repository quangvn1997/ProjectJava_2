/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.seeds;

import java.sql.SQLException;
import java.sql.Statement;
import quanlynhahang.models.DAO;

/**
 *
 * @author DongHo
 */
public class AdminSeed {

     public static void main(String[] args) throws SQLException {
	  // Database: online_shop
	  Statement stt = DAO.getConnection().createStatement();
	  // Xóa dữ liệu cũ
	  stt.execute("TRUNCATE TABLE admins;");
	  // Thêm dữ liệu mẫu
	  stt.execute("INSERT INTO admins (username,password) VALUES ('admin','admin');");
	  System.out.println("------ Chèn dữ liệu mẫu thành công! ------");
	  // Đăng nhập với tài khoản: admin/admin hoặc guest/guest
     }
}
