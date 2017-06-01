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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class CreateAdmin extends JPanel {

    public CreateAdmin() {
        setBackground(new Color(250, 250, 250));
        setBounds(0, 180, 1220, 380);
        
//        Tittle 
        JLabel title = new JLabel();
        title.setText("TẠO MỚI ADMIN");
        title.setBounds(490, 0, 300, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));
        
//        Hàng tài khoản
        JLabel lblTaikhoan = new JLabel();
        lblTaikhoan.setText("Tài khoản :");
        lblTaikhoan.setBounds(410,70, 150,50);
        lblTaikhoan.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtTaikhoan = new JTextField();
        txtTaikhoan.setBounds(540, 70, 200, 40);
        
//        Hàng mật khẩu
        JLabel lblMatkhau = new JLabel();
        lblMatkhau.setText("Mật khẩu :");
        lblMatkhau.setBounds(410,130, 150,50);
        lblMatkhau.setFont(new Font("Serif", Font.PLAIN, 24));
        JPasswordField txtMatkhau = new JPasswordField();
        txtMatkhau.setBounds(540, 130, 200, 40);
        
        
//       Button Reset 
        JButton btnReset = new JButton();
        btnReset.setText("Nhập lại");
        btnReset.setBounds(510,190, 120,50);
        btnReset.setFont(new Font("Serif", Font.PLAIN, 24));
       
//        Button Đăng Kí
        JButton btnCreate = new JButton();
        btnCreate.setText("Đăng Kí");       
        btnCreate.setBounds(640,190, 120,50);    
        btnCreate.setFont(new Font("Serif", Font.PLAIN, 24));
//       tao su kien cho created admin      
        
        
        add(title);
        add(lblTaikhoan);
        add(txtTaikhoan);
        add(lblMatkhau);
        add(txtMatkhau);
        add(btnReset);
        add(btnCreate);
        setLayout(null);
    }

}
