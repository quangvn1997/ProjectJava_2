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
        setBounds(0, 180, 1220, 380);

//        Tittle 
        JLabel title = new JLabel();
        title.setText("TẠO MỚI MÓN ĂN");
        title.setBounds(490, 0, 300, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));

//        Hàng tài khoản
        JLabel lblTenMon = new JLabel();
        lblTenMon.setText("Tên món ăn :");
        lblTenMon.setBounds(410, 70, 150, 50);
        lblTenMon.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtTenMon = new JTextField();
        txtTenMon.setBounds(540, 70, 200, 40);

//        Hàng mật khẩu
        JLabel lblGia = new JLabel();
        lblGia.setText("Giá món ăn :");
        lblGia.setBounds(410, 130, 150, 50);
        lblGia.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtGia = new JTextField();
        txtGia.setBounds(540, 130, 200, 40);
        JLabel lblVND = new JLabel();
        lblVND.setText("VNĐ");
        lblVND.setBounds(750, 130, 100, 40);
        lblVND.setFont(new Font("Serif", Font.PLAIN, 24));
        

        JLabel lblMota = new JLabel();
        lblMota.setText("Mô tả :");
        lblMota.setBounds(410, 180, 150, 50);
        lblMota.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextArea txtMota = new JTextArea();
        txtMota.setBounds(540, 180, 200, 70);

        JLabel lblLinkAnh = new JLabel();
        lblLinkAnh.setText("Link ảnh :");
        lblLinkAnh.setBounds(410, 270, 150, 50);
        lblLinkAnh.setFont(new Font("Serif", Font.PLAIN, 24));
        JPasswordField txtLinkAnh = new JPasswordField();
        txtLinkAnh.setBounds(540, 270, 200, 40);

//       Button Reset 
        JButton btnReset = new JButton();
        btnReset.setText("Nhập lại");
        btnReset.setBounds(510, 320, 120, 50);
        btnReset.setFont(new Font("Serif", Font.PLAIN, 24));

//        Button Đăng Kí
        JButton btnCreate = new JButton();
        btnCreate.setText("Thêm mới");
        btnCreate.setBounds(640, 320, 150, 50);
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
