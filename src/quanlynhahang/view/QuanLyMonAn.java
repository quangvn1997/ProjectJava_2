/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class QuanLyMonAn extends JPanel {

    public ListMonAn listMonAn = new ListMonAn();
    public CreateMonAn createMonAn = new CreateMonAn();
    public EditMonAn editMonAn = new EditMonAn();
    public DeleteMonAn deleteMonAn = new DeleteMonAn();

    public QuanLyMonAn() {
        setBackground(new Color(250, 250, 250));
        setBounds(350, 110, 1220, 560);

        JLabel tittle = new JLabel();
        tittle.setText("QUẢN LÝ MÓN ĂN");
        tittle.setBounds(450, 10, 300, 60);
        tittle.setFont(new Font("Serif", Font.PLAIN, 34));

        //Button Danh sách món ăn
        JButton danhsachMonAn = new JButton();
        danhsachMonAn.setText("DANH SÁCH MÓN ĂN");
        danhsachMonAn.setBounds(150, 70, 220, 100);
        danhsachMonAn.setFont(new Font("Serif", Font.PLAIN, 18));

//      Bắt sự kiện danh sách món ăn
        danhsachMonAn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listMonAn.setVisible(true);
                createMonAn.setVisible(false);
                editMonAn.setVisible(false);
                deleteMonAn.setVisible(false);
            }
        });

        // Button tạo mới món ăn
        JButton taomoiMonAn = new JButton();
        taomoiMonAn.setText("THÊM MÓN ĂN");
        taomoiMonAn.setBounds(390, 70, 200, 100);
        taomoiMonAn.setFont(new Font("Serif", Font.PLAIN, 18));

        // Bắt sự kiện tạo mới món ăn
        taomoiMonAn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createMonAn.setVisible(true);
                listMonAn.setVisible(false);
                editMonAn.setVisible(false);
                deleteMonAn.setVisible(false);
            }
        }
        );

        // Button sửa món ăn
        JButton suaMonan = new JButton();
        suaMonan.setText("SỬA MÓN ĂN");
        suaMonan.setBounds(630, 70, 200, 100);
        suaMonan.setFont(new Font("Serif", Font.PLAIN, 18));

//      Bắt sự kiện sửa admin
        suaMonan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listMonAn.setVisible(false);
                createMonAn.setVisible(false);
                editMonAn.setVisible(true);
                deleteMonAn.setVisible(false);
            }
        });

        // Button xóa món ăn
        JButton xoaMonAn = new JButton();
        xoaMonAn.setText("XÓA MÓN ĂN");
        xoaMonAn.setBounds(870, 70, 200, 100);
        xoaMonAn.setFont(new Font("Serif", Font.PLAIN, 18));

        xoaMonAn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listMonAn.setVisible(false);
                createMonAn.setVisible(false);
                editMonAn.setVisible(false);
                deleteMonAn.setVisible(true);
            }
        });
        
        add(tittle);
        add(danhsachMonAn);
        add(taomoiMonAn);
        add(suaMonan);
        add(xoaMonAn);
        add(listMonAn);
        add(createMonAn);
        add(editMonAn);
        add(deleteMonAn);
        setLayout(null);
    }

}
