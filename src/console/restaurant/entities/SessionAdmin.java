/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.entities;

/**
 *
 * @author Asus
 */
public class SessionAdmin {
     private static int id;
     private static int idToAction;
     private static String StrToAction;
     private static String username;
     private static String name;

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        SessionAdmin.name = name;
    }
     

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionAdmin.id = id;
    }

    public static int getIdToAction() {
        return idToAction;
    }

    public static void setIdToAction(int idToAction) {
        SessionAdmin.idToAction = idToAction;
    }

    public static String getStrToAction() {
        return StrToAction;
    }

    public static void setStrToAction(String StrToAction) {
        SessionAdmin.StrToAction = StrToAction;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        SessionAdmin.username = username;
    }
    
}
