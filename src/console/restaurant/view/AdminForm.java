/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
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
public class AdminForm extends JFrame {

    private JPanel adminPanel;

    private JLabel titleHeader;

    private JLabel lblName;
    private JLabel lblUsername;
    private JLabel lblPassword;

    private JTextField txtName;
    private JTextField txtUsername;
    private JTextField txtPassword;

    private JButton btnSubmit;
    private JButton btnReset;

    public AdminForm() {
        this.setTitle("Quản lý tài khoản");
        this.setSize(450, 500);

        this.adminPanel = new JPanel();
        this.adminPanel.setBounds(0, 0, 450, 550);
        this.adminPanel.setBackground(Color.WHITE);

        this.titleHeader = new JLabel("Thêm mới tài khoản");
        this.titleHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblName = new JLabel("Họ và tên");
        this.lblUsername = new JLabel("Tài khoản");
        this.lblPassword = new JLabel("Mật khẩu");
        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");
        this.txtName = new JTextField();
        this.txtUsername = new JTextField();
        this.txtPassword = new JPasswordField();

        this.titleHeader.setBounds(150, 70, 200, 50);
        this.lblName.setBounds(50, 130, 100, 50);
        this.lblUsername.setBounds(50, 190, 100, 50);
        this.lblPassword.setBounds(50, 250, 100, 50);
        this.txtName.setBounds(170, 130, 150, 34);
        this.txtUsername.setBounds(170, 190, 150, 34);
        this.txtPassword.setBounds(170, 250, 150, 34);
        this.btnSubmit.setBounds(150, 320, 80, 34);
        this.btnReset.setBounds(250, 320, 80, 34);

        this.add(this.titleHeader);
        this.add(this.lblName);
        this.add(this.lblUsername);
        this.add(this.lblPassword);
        this.add(this.btnSubmit);
        this.add(this.btnReset);
        this.add(this.txtName);
        this.add(this.txtUsername);
        this.add(this.txtPassword);

        this.add(this.adminPanel);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

//    public static void main(String[] args) {
//        AdminForm admin = new AdminForm();
//        admin.setVisible(true);
//    }

}
