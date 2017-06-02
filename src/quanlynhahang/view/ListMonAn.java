/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
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
        this.setBackground(new Color(250, 250, 250));
        this.setBounds(0, 150, 1220, 380);

//        Tittle 
        JLabel title = new JLabel();
        title.setText("THÔNG TIN MÓN ĂN");
        title.setBounds(350, 0, 300, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        
//        Table
        String[] columnNames = {"ID","Tên món", "Giá", "Ngày tạo"};
        Object[][] data = {{"1","cá kho tộ", "65000", "22-10-2012"}, {"2","canh chua", "30000", "22-10-2012"}, {"3","sườn nướng", "120000", "22-10-2012"}};
        this.table = new JTable(data, columnNames);

//      Hiển thị kích thước bảng
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(170, 50, 550, 200);
        
        JButton btnShow = new JButton();
        btnShow.setText("Hiển Thị");
        btnShow.setBounds(420, 270, 100, 50);
        this.add(scrollPane);
        add(title);
        add(btnShow);
        this.setLayout(null);
        this.setVisible(true);
    }

}
