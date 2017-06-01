/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ListAdmin extends JPanel {

    JTable table;

    public ListAdmin() {
        setBackground(new Color(250,250,250));
        setBounds(0, 180, 1220, 440);

//        Tittle 
        JLabel title = new JLabel();
        title.setText("THÔNG TIN ADMIN");
        title.setBounds(490, 0, 300, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));

//        Table
        String[] columnNames = {"Tài khoản", "Mật khẩu", "Ngày tạo"};
        Object[][] data = {{"tien", "tien123", "22-10-2012"}, {"tuan", "1", "22-10-2012"}, {"hoang", "2", "22-10-2012"}};
        table = new JTable(data, columnNames);
//        table.setPreferredScrollableViewportSize(new Dimension(200,100));
        table.setBounds(400, 550, 200, 100);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        add(title);
        setVisible(true);
    }
}
