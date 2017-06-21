/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.AdminsController;
import console.restaurant.entities.Admin;
import console.restaurant.models.AdminsModel;
import console.restaurant.models.FoodsModel;
import console.restaurant.utilities.ValidateUtilities;
import static console.restaurant.view.ManagerAdmin.table;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class AdminForm extends JFrame {

    private JPanel adminPanel;

    private JLabel lblHeader;
    private JLabel lblName;
    private JLabel lblUsername;
    private JLabel lblPassword;

    public JTextField txtName;
    public JTextField txtUsername;
    public JTextField txtPassword;

    public JButton btnDelete;
    private JButton btnSubmit;
    public JButton btnReset;

    private AdminsModel adminModel;
    private int action = 1;
    private int id = 0;

    public AdminForm() {
        initComponent();
    }

    public AdminForm(int action, int id) {
        this.adminModel = new AdminsModel();
        this.action = action;
        this.id = id;
        initComponent();
    }

    public void setLblHeader(JLabel lblHeader) {
        this.lblHeader = lblHeader;
    }

    private void initComponent() {
        this.adminModel = new AdminsModel();
        Admin admin1 = new Admin();

        this.setTitle("Quản lý tài khoản");
        this.setSize(450, 500);

        this.adminPanel = new JPanel();
        this.adminPanel.setBounds(0, 0, 450, 550);
        this.adminPanel.setBackground(Color.WHITE);

        this.lblHeader = new JLabel("Tạo mới tài khoản");
        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblName = new JLabel("Họ và tên");
        this.lblUsername = new JLabel("Tài khoản");
        this.lblPassword = new JLabel("Mật khẩu");
        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");
        this.btnDelete = new JButton("Xóa");
        admin1 = adminModel.getById(this.id);
        if (action == 2) {
            // Lấy dữ liệu food từ db theo id.           
            if (admin1 == null) {
                JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại hoặc đã bị xóa.");
                return;
            }
            this.lblHeader.setText("Sửa tài khoản");
            this.add(this.btnDelete);
        }

        this.txtName = new JTextField();
        this.txtName.setText(admin1.getName());
        this.txtUsername = new JTextField();
        this.txtUsername.setText(admin1.getUsername());
        this.txtPassword = new JPasswordField();
        this.txtPassword.setText(admin1.getPassword());

        this.lblHeader.setBounds(180, 70, 200, 50);
        this.lblName.setBounds(50, 125, 100, 50);
        this.lblUsername.setBounds(50, 185, 100, 50);
        this.lblPassword.setBounds(50, 245, 100, 50);
        this.txtName.setBounds(170, 130, 150, 34);
        this.txtUsername.setBounds(170, 190, 150, 34);
        this.txtPassword.setBounds(170, 250, 150, 34);

        this.btnDelete.setBounds(65, 320, 80, 34);
        this.btnSubmit.setBounds(165, 320, 80, 34);
        this.btnReset.setBounds(265, 320, 80, 34);

        this.add(this.lblHeader);
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

        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền họ và tên !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (txtUsername.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền tài khoản !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (new String(txtPassword.getText()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền mật khẩu !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtUsername.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Admin admin = new Admin();
                admin.setUsername(txtUsername.getText());
                admin.setName(txtName.getText());
                admin.setPassword(new String(txtPassword.getText()));
                admin.setId(id);
                if (action == 1) {
                    if (adminModel.insertAdmin(admin)) {
                        JOptionPane.showMessageDialog(null, "Thêm mới tài khoản thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi thêm tài khoản", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (action == 2) {
                    if (adminModel.update(admin)) {
                        JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi sửa tài khoản", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
                ManagerAdmin.page = 1;
                ManagerAdmin.loadAdmin();

                txtUsername.setText("");
                txtName.setText("");
                txtPassword.setText("");
//                Đóng form
                SwingUtilities.windowForComponent(adminPanel).dispose();
            }
        });

        this.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    txtUsername.setText("");
                    txtName.setText("");
                    txtPassword.setText("");
            }
        });

        this.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    TableModel tblModel = table.getModel();
                    Object[] options = {"Có", "Không"};
                    Component form = null;
                    int n = JOptionPane.showOptionDialog(form, "Bạn có muốn xóa tài khoản " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " không?  ", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
                    if (n == JOptionPane.YES_OPTION) {
//                        adminModel.delete((tblModel.getValueAt(row, 0).toString()));
                        adminModel.delete(Integer.parseInt(tblModel.getValueAt(row, 0).toString()));
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " thành công.");
//
                        ManagerAdmin.page = 1;
                        ManagerAdmin.loadAdmin();
                        SwingUtilities.windowForComponent(adminPanel).dispose();
                    }
                }
            }
        });
    }
}
