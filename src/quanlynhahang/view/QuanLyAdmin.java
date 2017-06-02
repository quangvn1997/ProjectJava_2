/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class QuanLyAdmin extends JPanel {

    private JLabel title;
    private JButton thongtinAdmin;
    private JButton taomoiAdmin;
    private JButton suaAdmin;
    private JButton xoaAdmin;
    public ListAdmin listadmin = new ListAdmin();
    public CreateAdmin createAdmin = new CreateAdmin();
    public EditAdmin editadmin = new EditAdmin();
    public DeleteAdmin deleteadmin = new DeleteAdmin();

    public QuanLyAdmin() {
        setBackground(new Color(250,250,250));
        setBounds(300, 110, 1000, 480);

        this.title = new JLabel();
        this.title.setText("QUẢN LÝ ADMIN");
        this.title.setBounds(360, 0, 280, 70);
        this.title.setFont(new Font("Serif", Font.PLAIN, 30));

//      Button Thông tin Admin
        this.thongtinAdmin = new JButton();
        this.thongtinAdmin.setText("Danh sách admin");
        this.thongtinAdmin.setBounds(80, 70, 170, 70);
        this.thongtinAdmin.setFont(new Font("Serif", Font.PLAIN, 18));
//      Bắt sự kiện danh sách admin
        thongtinAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAdmin.setVisible(false);
                listadmin.setVisible(true);
                editadmin.setVisible(false);
                deleteadmin.setVisible(false);
            }
        });

        // Button tạo mới admin
        this.taomoiAdmin = new JButton();
        this.taomoiAdmin.setText("Tạo mới admin");
        this.taomoiAdmin.setBounds(280, 70, 170, 70);
        this.taomoiAdmin.setFont(new Font("Serif", Font.PLAIN, 18));
        // Bắt sự kiện tạo mới admin
        taomoiAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAdmin.setVisible(true);
                listadmin.setVisible(false);
                editadmin.setVisible(false);
                deleteadmin.setVisible(false);
                add(createAdmin);
            }
        });

        // Button sửa admin
        this.suaAdmin = new JButton();
        this.suaAdmin.setText("Sửa admin");
        this.suaAdmin.setBounds(480, 70, 170, 70);
        this.suaAdmin.setFont(new Font("Serif", Font.PLAIN, 18));
        // Bắt sự kiện sửa admin
        suaAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAdmin.setVisible(false);
                editadmin.setVisible(true);
                listadmin.setVisible(false);
                deleteadmin.setVisible(false);
                add(editadmin);
            }
        });

        // Button xóa admin
        this.xoaAdmin = new JButton();
        this.xoaAdmin.setText("Xóa admin");
        this.xoaAdmin.setBounds(680, 70, 170, 70);
        this.xoaAdmin.setFont(new Font("Serif", Font.PLAIN, 18));
        xoaAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAdmin.setVisible(false);
                editadmin.setVisible(false);
                listadmin.setVisible(false);
                deleteadmin.setVisible(true);
                add(deleteadmin);
            }
        });

        this.add(this.listadmin);
        this.add(this.title);
        this.add(this.thongtinAdmin);
        this.add(this.taomoiAdmin);
        this.add(this.suaAdmin);
        this.add(this.xoaAdmin);
        setLayout(null);
    }

}
