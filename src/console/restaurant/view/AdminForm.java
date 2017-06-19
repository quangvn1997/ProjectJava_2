/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.AdminsController;
import console.restaurant.entities.Admin;
import console.restaurant.entities.Food;
import console.restaurant.models.AdminsModel;
import console.restaurant.models.FoodsModel;
import console.restaurant.utilities.ValidateUtilities;
import static console.restaurant.view.ManagerFood.table;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
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
    public JTextField txtAcount;
    public JTextField txtPassword;

    public JButton btnDelete;
    private JButton btnSubmit;
    public JButton btnReset;

    private AdminsModel adminModel = new AdminsModel();
    private FoodsModel foodModel = new FoodsModel();

    private int action = 1;
    private int id = 0;

    public AdminForm() {
        initComponent();
    }

    public AdminForm(int action, int id) {
        this.action = action;
        this.id = id;
        initComponent();
    }

    public void setLblHeader(JLabel lblHeader) {
        this.lblHeader = lblHeader;
    }

    private void initComponent() {
        Admin admin = new Admin();
        if (this.action == 2) {
            // Lấy dữ liệu food từ db theo id.
            admin = adminModel.getById(this.id);
            if (admin == null) {
                JOptionPane.showMessageDialog(null, "Food không tồn tại hoặc đã bị xóa.");
                return;
            }
            this.lblHeader = new JLabel("Sửa thông tin tài khoản");
            this.btnDelete = new JButton("Xóa");
            this.btnDelete.setBounds(65, 320, 80, 34);
            this.add(this.btnDelete);

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
                            adminModel.deleteAdmin(tblModel.getValueAt(row, 0).toString());
                            JOptionPane.showMessageDialog(null, "Xóa tài khoản " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " thành công.");
//                            foodController.loadFood(table);

                            DefaultTableModel model = (DefaultTableModel) ManagerAdmin.table.getModel();
                            model.setRowCount(0);
                            List<Admin> listFood = AdminsModel.getAllAdmin();

                            listFood.forEach((f1) -> {
                                model.addRow(new Object[]{String.valueOf(f1.getId()), f1.getName(), f1.getUsername(), f1.getPassword(), f1.getCreatedAt(), f1.getUpdateAt()});
                            });
                            SwingUtilities.windowForComponent(adminPanel).dispose();
                        }
                    }
                }
            });
        }

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

        this.txtName = new JTextField();
        this.txtAcount = new JTextField();
        this.txtPassword = new JPasswordField();

        this.lblHeader.setBounds(180, 70, 200, 50);
        this.lblName.setBounds(50, 125, 100, 50);
        this.lblUsername.setBounds(50, 185, 100, 50);
        this.lblPassword.setBounds(50, 245, 100, 50);
        this.txtName.setBounds(170, 130, 150, 34);
        this.txtAcount.setBounds(170, 190, 150, 34);
        this.txtPassword.setBounds(170, 250, 150, 34);

        this.btnSubmit.setBounds(165, 320, 80, 34);
        this.btnReset.setBounds(265, 320, 80, 34);

        this.add(this.lblHeader);
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

        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền họ và tên !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (txtAcount.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền tài khoản !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (new String(txtPassword.getText()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền mật khẩu !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtAcount.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Admin admin = new Admin();
                admin.setUsername(txtAcount.getText());
                admin.setName(txtName.getText());
                admin.setPassword(new String(txtPassword.getText()));

                if (action == 1) {
                    if (adminModel.insertAdmin(admin)) {
                        JOptionPane.showMessageDialog(null, "Thêm mới dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi thêm dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }   
                if (action == 2) {
                    admin.setId(id);
                    if (adminModel.update(admin)) {
                        JOptionPane.showMessageDialog(null, "Sửa dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi sửa dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
                DefaultTableModel model = (DefaultTableModel) ManagerFood.table.getModel();
                model.setRowCount(0);
                List<Admin> listFood = AdminsModel.getAllAdmin();

                listFood.forEach((f1) -> {
                    model.addRow(new Object[]{String.valueOf(f1.getId()), f1.getName(), f1.getUsername(), f1.getPassword(), f1.getCreatedAt(), f1.getUpdateAt()});
                });
                SwingUtilities.windowForComponent(adminPanel).dispose();

                txtAcount.setText("");
                txtName.setText("");
                txtPassword.setText("");
            }
        });

        this.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAcount.setText("");
                txtName.setText("");
                txtPassword.setText("");
            }
        });
    }
}
