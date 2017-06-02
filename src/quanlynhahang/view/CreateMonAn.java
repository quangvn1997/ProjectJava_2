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
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class CreateMonAn extends JPanel {

    public CreateMonAn() {
        setBackground(new Color(250,250,250));
        setBounds(0, 150, 1220, 380);

//        Tittle 
        JLabel title = new JLabel();
        title.setText("TẠO MỚI MÓN ĂN");
        title.setBounds(350, 0, 300, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));

//        Hàng tài khoản
        JLabel lblTenMon = new JLabel();
        lblTenMon.setText("Tên món ăn :");
        lblTenMon.setBounds(250, 60, 150, 50);
        lblTenMon.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtTenMon = new JTextField();
        txtTenMon.setBounds(400, 60, 200, 40);

//        Hàng mật khẩu
        JLabel lblGia = new JLabel();
        lblGia.setText("Giá món ăn :");
        lblGia.setBounds(250, 115, 150, 50);
        lblGia.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtGia = new JTextField();
        txtGia.setBounds(400, 115, 200, 40);
        JLabel lblVND = new JLabel();
        lblVND.setText("VNĐ");
        lblVND.setBounds(610, 115, 100, 40);
        lblVND.setFont(new Font("Serif", Font.PLAIN, 24));
        

        JLabel lblMota = new JLabel();
        lblMota.setText("Mô tả :");
        lblMota.setBounds(305, 165, 150, 50);
        lblMota.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextArea txtMota = new JTextArea();
        txtMota.setBounds(400, 165, 200, 50);

        JLabel lblLinkAnh = new JLabel();
        lblLinkAnh.setText("Link ảnh :");
        lblLinkAnh.setBounds(275, 230, 150, 50);
        lblLinkAnh.setFont(new Font("Serif", Font.PLAIN, 24));
        JPasswordField txtLinkAnh = new JPasswordField();
        txtLinkAnh.setBounds(400, 230, 200, 40);

//       Button Reset 
        JButton btnReset = new JButton();
        btnReset.setText("Nhập lại");
        btnReset.setBounds(360, 290, 120, 40);
        btnReset.setFont(new Font("Serif", Font.PLAIN, 24));

//        Button Đăng Kí
        JButton btnCreate = new JButton();
        btnCreate.setText("Thêm mới");
        btnCreate.setBounds(500, 290, 130, 40);
        btnCreate.setFont(new Font("Serif", Font.PLAIN, 24));

        add(title);
        add(lblTenMon);
        add(txtTenMon);
        add(lblGia);
        add(lblVND);
        add(txtGia);
        add(lblMota);
        add(txtMota);
        add(lblLinkAnh);
        add(txtLinkAnh);
        add(btnReset);
        add(btnCreate);
        setVisible(false);
        setLayout(null);
    }

}
