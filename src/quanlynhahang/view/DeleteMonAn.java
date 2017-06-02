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
public class DeleteMonAn extends JPanel {

    public DeleteMonAn() {
        setBackground(new Color(250,250,250));
        setBounds(0, 150, 1220, 380);
        
//          Tittle
        JLabel title = new JLabel();
        title.setText("XÓA THÔNG TIN MÓN ĂN");
        title.setBounds(280, 0, 420, 50);
        title.setFont(new Font("Serif", Font.PLAIN, 28));

//      Hàng tìm món ăn
        JLabel lblTimKiem = new JLabel();
        lblTimKiem.setText("Tìm món ăn :");
        lblTimKiem.setBounds(220, 50, 150, 50);
        lblTimKiem.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtTimKiem = new JTextField();
        txtTimKiem.setBounds(400, 50, 200, 40);
        JButton btnTimKiem = new JButton();
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.setBounds(630, 50, 130, 40);
        btnTimKiem.setFont(new Font("Serif", Font.PLAIN, 24));

//         Hàng hiển thị tên món ăn
        JLabel lblTenMon = new JLabel();
        lblTenMon.setText("Tên món ăn :");
        lblTenMon.setBounds(220, 100, 200, 50);
        lblTenMon.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtTenMon = new JTextField();
        txtTenMon.setBounds(400, 100, 200, 40);

//        Hàng hiển thị giá món ăn
        JLabel lblGia = new JLabel();
        lblGia.setText("Giá món ăn :");
        lblGia.setBounds(233, 150, 200, 50);
        lblGia.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txGia = new JTextField();
        txGia.setBounds(400, 150, 200, 40);
        JLabel lblVND = new JLabel();
        lblVND.setText("VNĐ");
        lblVND.setBounds(620, 150, 100, 40);
        lblVND.setFont(new Font("Serif", Font.PLAIN, 24));
        
////      Hàng hiển thị miêu tả món ăn
        JLabel lblMieuTa = new JLabel();
        lblMieuTa.setText("Miêu tả :");
        lblMieuTa.setBounds(270, 200, 150, 50);
        lblMieuTa.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextArea txtMieuTa = new JTextArea();
        txtMieuTa.setBounds(400, 200, 200, 70);
//
//      Hàng hiển thị đường link ảnh
        JLabel lblLinkAnh = new JLabel();
        lblLinkAnh.setText("Link ảnh :");
        lblLinkAnh.setBounds(260, 280, 200, 50);
        lblLinkAnh.setFont(new Font("Serif", Font.PLAIN, 24));
        JTextField txtLinkAnh = new JTextField();
        txtLinkAnh.setBounds(400, 280, 200, 40);

//
////       Button Cập nhật
        JButton btnUpdate = new JButton();
        btnUpdate.setText("XÓA");
        btnUpdate.setBounds(630, 280, 90, 40);
        btnUpdate.setFont(new Font("Serif", Font.PLAIN, 24));
        
        add(title);
        add(lblTimKiem);
        add(txtTimKiem);
        add(btnTimKiem);
        add(lblTenMon);
        add(txtTenMon);
        add(lblGia);
        add(txGia);
        add(lblVND);
        add(lblMieuTa);
        add(txtMieuTa);
        add(lblLinkAnh);
        add(txtLinkAnh);
        add(btnUpdate);
        setVisible(false);
        setLayout(null);
    }

}
