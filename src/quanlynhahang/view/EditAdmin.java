/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class EditAdmin extends JPanel {


    public EditAdmin() {
        setBackground(new Color(250, 250, 250));
        setBounds(0, 150, 1220, 440);

//        Tittle
        JLabel title = new JLabel();
        title.setText("SỬA THÔNG TIN ADMIN");
        title.setBounds(330, 0, 420, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));

//      Hàng tìm tài khoản
        JLabel lblTaikhoan = new JLabel();
        lblTaikhoan.setText("Tìm tài khoản :");
        lblTaikhoan.setBounds(230, 50, 150, 40);
        lblTaikhoan.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtTaikhoan = new JTextField();
        txtTaikhoan.setBounds(400, 50, 200, 40);
        JButton btnSearch = new JButton();
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBounds(630, 50, 130, 40);
        btnSearch.setFont(new Font("Serif", Font.PLAIN, 24));
        
//      Hàng mật khẩu cũ
        JLabel lblMatkhaucu = new JLabel();
        lblMatkhaucu.setText("Mật khẩu cũ :");
        lblMatkhaucu.setBounds(240, 100, 150, 50);
        lblMatkhaucu.setFont(new Font("Serif", Font.PLAIN, 24));
        JPasswordField txtMatkhaucu = new JPasswordField();
        txtMatkhaucu.setBounds(400, 100, 200, 40);
        
//      Hàng mật khẩu mới  
        JLabel lblMatkhaumoi = new JLabel();
        lblMatkhaumoi.setText("Mật khẩu mới :");
        lblMatkhaumoi.setBounds(225, 150, 200, 50);
        lblMatkhaumoi.setFont(new Font("Serif", Font.PLAIN, 24));
        JPasswordField txtMatkhaumoi = new JPasswordField();
        txtMatkhaumoi.setBounds(400, 150, 200, 40);

//      Hàng xác nhận mật khẩu
        JLabel lblConfirm = new JLabel();
        lblConfirm.setText("Xác nhận mật khẩu :");
        lblConfirm.setBounds(175, 200, 200, 50);
        lblConfirm.setFont(new Font("Serif", Font.PLAIN, 24));
        JPasswordField txtConfirm = new JPasswordField();
        txtConfirm.setBounds(400, 200, 200, 40);
        
//      Button Reset  
        JButton btnReset = new JButton();
        btnReset.setText("Nhập lại");
        btnReset.setBounds(370,250, 120,50);
        btnReset.setFont(new Font("Serif", Font.PLAIN, 24));
       
//       Button Cập nhật
        JButton btnCreate = new JButton();
        btnCreate.setText("Cập nhật");       
        btnCreate.setBounds(500,250, 120,50);    
        btnCreate.setFont(new Font("Serif", Font.PLAIN, 24));

        add(title);
        add(lblTaikhoan);
        add(txtTaikhoan);
        add(btnSearch);
        add(lblMatkhaucu);
        add(txtMatkhaucu);
        add(lblMatkhaumoi);
        add(lblMatkhaumoi);
        add(txtMatkhaumoi);
        add(lblConfirm);
        add(txtConfirm);
        add(btnReset);
        add(btnCreate);
        setLayout(null);
    }

}
