/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.controller;

import console.restaurant.entities.Food;
import console.restaurant.entities.Table;
import console.restaurant.models.TableModel;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class TablesController {

    public static void loadTables(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Table> lisTable = TableModel.getAllTable();
        lisTable.forEach((table1) -> {
            model.addRow(new Object[]{String.valueOf(table1.getId()), table1.getName(), table1.getStatus()});
        });
    }
}
