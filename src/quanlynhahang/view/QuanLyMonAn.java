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
        setBackground(new Color(250,250,250));
        setBounds(310, 110, 1000, 485);

        JLabel tittle = new JLabel();
        tittle.setText("QUẢN LÝ MÓN ĂN");
        tittle.setBounds(350, 10, 300, 60);
        tittle.setFont(new Font("Serif", Font.PLAIN, 34));

        //Button Danh sách món ăn
        JButton danhsachMonAn = new JButton();
        danhsachMonAn.setText("Danh sách món ăn");
        danhsachMonAn.setBounds(70, 70, 170, 70);
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
        taomoiMonAn.setText("Thêm món ăn");
        taomoiMonAn.setBounds(260, 70, 170, 70);
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
        suaMonan.setText("Sửa món ăn");
        suaMonan.setBounds(450, 70, 170, 70);
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
        xoaMonAn.setText("Xóa món ăn");
        xoaMonAn.setBounds(650, 70, 170, 70);
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
