/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import console.restaurant.entities.Admin;

/**
 *
 * @author DongHo
 */
public class JUntilities {

     public static void alert(String str) {
	  JOptionPane.showMessageDialog(null, str);
     }

     public static Admin getAdminFromResults(ResultSet results) throws SQLException {
	  Admin admin = new Admin();
	  admin.setId(Integer.valueOf(results.getString("id")));
	  admin.setUsername(results.getString("username"));
	  admin.setPassword(results.getString("password"));
	  
	  return admin;
     }

}
