/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.models;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import console.restaurant.entities.SessionAdmin;
import console.restaurant.utilities.JUntilities;

/**
 *
 * @author Asus
 */
public class JModel {
    /**
      *
      *
      * @param email
      * @param password
      * @return
      */
     public static int loginAdmin(String username, String password) {
         System.out.println(password);
	  int count = 0;
	  try {
	       String checklogin = String.format("SELECT * FROM admins WHERE username = '%s' AND password = '%s'", username, password);
	       ResultSet rs = DAO.getConnection().createStatement().executeQuery(checklogin);
               
	       while (rs.next() && password.equals(rs.getString("password"))) {
		    ++count;
		    SessionAdmin.setId(rs.getInt("id"));
		    SessionAdmin.setUsername(rs.getString("username"));
                    SessionAdmin.setName(rs.getString("name"));
	       }
	       if (count > 0) {
		    System.out.println("");
	       } else {
		    return -1;
	       }
	  } catch (SQLException e) {
              e.printStackTrace();
	       System.out.println("Loi kiem tra Admin");
	  }
	  return count;
     }
    
}
