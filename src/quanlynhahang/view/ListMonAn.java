/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ListMonAn extends JPanel {

    JTable table;

    public ListMonAn() {
        setBackground(new Color(250,250,250));
        setBounds(0, 180, 1220, 440);

        JLabel title = new JLabel();
        title.setText("THÔNG TIN MÓN ĂN");
        title.setBounds(490, 0, 300, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));

//        Table
        String[] columnNames = {"Tên món ăn","Giá", "Mô tả", "Link ảnh"};
        Object[][] data = {{"Cá kho tộ", "65", "cá basa kho miền tây","123.jpg"}, {"Bò lăn", "210", "Lăn bò nướng","4.jpg"}, {"Canh cá", "25","bún,chả cá", "ca.jpg"}};
        table = new JTable(data, columnNames);
//        table.setPreferredScrollableViewportSize(new Dimension(200,100));
        table.setBounds(400, 550, 200, 100);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        add(title);
        setVisible(true);
    }

}
