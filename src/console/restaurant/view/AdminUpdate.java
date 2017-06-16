/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.AdminsController;
import console.restaurant.entities.Admin;
import console.restaurant.models.AdminsModel;
import console.restaurant.utilities.ValidateUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.TableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class AdminUpdate extends JFrame {

    private JPanel adminPanel;

    private JLabel titleHeader;
    private JLabel lblName;
    private JLabel lblUsername;
    private JLabel lblPassword;

    public JTextField txtName;
    public JTextField txtAcount;
    public JTextField txtPassword;

    private JButton btnSubmit;
    private JButton btnReset;
    private String idAdminUpdate;

    public String getIdAdminUpdate() {
        return idAdminUpdate;
    }

    public void setIdAdminUpdate(String idAdminUpdate) {
        this.idAdminUpdate = idAdminUpdate;
    }

    public AdminUpdate() {
        this.setTitle("Quản lý tài khoản");
        this.setSize(450, 500);

        this.adminPanel = new JPanel();
        this.adminPanel.setBounds(0, 0, 450, 550);
        this.adminPanel.setBackground(Color.WHITE);

        this.titleHeader = new JLabel("Sửa thông tin tài khoản");
        this.titleHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblName = new JLabel("Họ và tên");
        this.lblUsername = new JLabel("Tài khoản");
        this.lblPassword = new JLabel("Mật khẩu");
        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");

        this.txtName = new JTextField();
        this.txtAcount = new JTextField();
        this.txtPassword = new JPasswordField();

        this.titleHeader.setBounds(180, 70, 200, 50);
        this.lblName.setBounds(50, 125, 100, 50);
        this.lblUsername.setBounds(50, 185, 100, 50);
        this.lblPassword.setBounds(50, 245, 100, 50);
        this.txtName.setBounds(170, 130, 150, 34);
        this.txtAcount.setBounds(170, 190, 150, 34);
        this.txtPassword.setBounds(170, 250, 150, 34);

        this.btnSubmit.setBounds(140, 320, 80, 34);
        this.btnReset.setBounds(240, 320, 80, 34);

        this.add(this.titleHeader);
        this.add(this.lblName);
        this.add(this.lblUsername);
        this.add(this.lblPassword);
        this.add(this.btnSubmit);
        this.add(this.btnReset);
        this.add(this.txtName);
        this.add(this.txtAcount);
        this.add(this.txtPassword);

        this.add(this.adminPanel);
        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

    }

    public void update() {
        
        this.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAcount.setText("");
                txtName.setText("");
                txtPassword.setText("");
            }
        });
        
        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {               
                if (txtAcount.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền tài khoản !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (new String(txtPassword.getText()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền mật khẩu !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
//                if (ValidateUtilities.checkExistanceAdmin(txtAcount.getText()) && !txtAcount.getText().equals(tblModel.getValueAt(row, 1).toString())) {
//                    JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại ! Vui lòng chọn tên khác.", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
                Admin admin = new Admin();
                admin.setId(Integer.valueOf(idAdminUpdate));
                admin.setUsername(txtAcount.getText());
                admin.setName(txtName.getText());
                admin.setPassword(new String(txtPassword.getText()));
                AdminsModel.update(admin);
            }
        });
    }

    public static void main(String[] args) {
        AdminUpdate admin = new AdminUpdate();
        admin.setVisible(true);
    }
}
