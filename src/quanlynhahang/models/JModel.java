/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.models;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import quanlynhahang.entities.SessionAdmin;
import quanlynhahang.entities.Table;
import quanlynhahang.utilities.JUntilities;

/**
 *
 * @author Asus
 */
public class JModel {
    /**
      * Hàm này để đăng nhập vào chương trình.
      *
      * @param email
      * @param password
      * @return
      */
     public static int loginAdmin(String username, String password) {
	  int count = 0;
	  try {
	       String checklogin = String.format("SELECT * FROM admins WHERE username = '%s' AND password = '%s'", username, password);
	       ResultSet rs = DAO.getConnection().createStatement().executeQuery(checklogin);
	       while (rs.next()) {
		    ++count;
		    SessionAdmin.setId(rs.getInt("id"));
		    SessionAdmin.setUsername(rs.getString("username"));
	       }
	       if (count > 0) {
		    System.out.println("Đăng nhập thành công.");
	       } else {
		    System.out.println("Thông tin không chính xác!");
		    return -1;
	       }
	  } catch (SQLException e) {
	       System.out.println("Loi kiem tra Admin");
	  }
	  return count;
     }
     
     /**
      * Model xịn, đa năng và chuyên dụng...
      *
      * @param <T>
      * @param obj
      * @throws SQLException
      */
     public static <T> void insert(T obj, int id) throws SQLException {
	  StringBuilder columns = new StringBuilder();
	  StringBuilder values = new StringBuilder();
	  StringBuilder joins = new StringBuilder();
	  try {
	       Field[] fields = obj.getClass().getDeclaredFields();
	       String pre1 = "";
	       String pre2 = "'";
	       for (Field f : fields) {
		    f.setAccessible(true);
		    if (f.getName().equals("id")) {
			 continue;
		    }
		    columns.append(pre1).append(f.getName());
		    joins.append(pre1).append(f.getName()).append(" = '").append(f.get(obj)).append("'");
		    if (f.getType().toString().equals("boolean")) {
			 if (f.get(obj).toString().equals("true")) {
			      values.append(pre2).append("1");
			 } else {
			      values.append(pre2).append("0");
			 }
		    } else {
			 values.append(pre2).append(f.get(obj));
		    }
		    pre1 = ", ";
		    pre2 = "', '";
	       }
	  } catch (IllegalAccessException | IllegalArgumentException | SecurityException e) {
	       System.err.println("Lỗi: " + e);
	  }
	  values.append("'");
	  StringBuilder querry = new StringBuilder();
	  if ((obj instanceof Table) && (columns.length() != 0) && (values.length() != 0)) {
	       Table tb = (Table) obj;
	       String table = tb.getTable();
//	       int id = SessionAdmin.getIdToAction();
	       String str = SessionAdmin.getStrToAction();
	       String unique = tb.getUnique();
	       if (id < 0) {
		    querry.append("INSERT INTO ").append(table).append(" (").append(columns).append(") VALUES(").append(values).append(");");
	       } else if (id == 0) {
		    querry.append("UPDATE ").append(table).append(" SET ").append(joins).append(" WHERE ").append(unique).append(" = '").append(str).append("';");
	       } else {
		    querry.append("UPDATE ").append(table).append(" SET ").append(joins).append(" WHERE ").append(unique).append(" = '").append(id).append("';");
	       }
	       Statement stt = DAO.getConnection().createStatement();
	       stt.execute(querry.toString());
	       System.out.println("Thao tác thành công!");
	       JUntilities.alert("Thao tác thành công!");
	  } else {
	       System.err.println("Lỗi!...");
	       JUntilities.alert("Lỗi!...");
	  }
     }

     /**
      * Model xịn xóa bất cứ một đối tượng theo id
      *
      * @param <T>
      * @param obj
      * @param id
      * @throws SQLException
      */
     public static <T> void delete(T obj, int id) throws SQLException {
	  StringBuilder querry = new StringBuilder();
	  if (obj instanceof Table) {
	       Table tb = (Table) obj;
	       String table = tb.getTable();
	       if (id > 0) {
		    querry.append("DELETE FROM ").append(table).append(" WHERE ").append(tb.getUnique()).append("=").append(id);
	       } else {
		    querry.append("DELETE FROM ").append(table).append(" WHERE ").append(tb.getUnique()).append("='").append(SessionAdmin.getStrToAction()).append("';");
	       }
	  } else {
	       System.err.println("Lỗi!...");
	       JUntilities.alert("Lỗi!...");
	  }
	  Statement stt = DAO.getConnection().createStatement();
	  stt.execute(querry.toString());
	  JUntilities.alert("Xóa thành công!");
     }
     
         //Ham kiem tra trong
    public static boolean checkBlank(String str) {
        if (str.isEmpty()) {        
            return false;
        }
        return true;
    }
    
}
