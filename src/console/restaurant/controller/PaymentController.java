/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.controller;

import console.restaurant.entities.Food;
import console.restaurant.models.PaymentModel;
import java.text.NumberFormat;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Truong
 */
public class PaymentController {

    public static void loadFood(JTable jTable1) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        List<Food> listFood = PaymentModel.getAllFood();
        listFood.forEach((food) -> {
            NumberFormat formatter = NumberFormat.getCurrencyInstance();
            model.addRow(new Object[]{String.valueOf(food.getId()), food.getName(), formatter.format(food.getUnitPrice())});
        });
    }
}
