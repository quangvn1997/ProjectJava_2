/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.AdminsController;
import console.restaurant.entities.Admin;
import console.restaurant.entities.SessionAdmin;
import console.restaurant.models.AdminsModel;
import console.restaurant.utilities.ValidateUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class ManagerAdmin extends JPanel {
//    private JTextField txtName;

    private JLabel lblSearch;
    private JButton btnSearch;
    private JTextField txtSearch;

    private JTable table;
    private JButton btnUpdate;
    private JButton btnDelete;
    private JButton btnCreate;
    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnPage;
    private JButton btnNext;
    private JButton btnLast;
    private int page = 1;

    public JTable getTable() {
        return table;
    }

    private DefaultTableModel modelAdmin;
    private JScrollPane scrollPane;

    private AdminForm adminForm;

    private AdminsController adminController = new AdminsController();

public ManagerAdmin() {

        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lblSearch = new JLabel("Nhập tài khoản");
        this.txtSearch = new JTextField();
        this.btnSearch = new JButton("Tìm");
        this.btnDelete = new JButton("Xóa");
        this.btnUpdate = new JButton("Sửa");
        this.btnCreate = new JButton("Tạo mới");

        this.lblSearch.setBounds(20, 20, 100, 34);
        this.txtSearch.setBounds(130, 20, 200, 34);
        this.btnSearch.setBounds(350, 20, 100, 34);
        this.btnUpdate.setBounds(640, 20, 100, 34);
        this.add(this.btnUpdate);
        this.btnDelete.setBounds(760, 20, 100, 34);
        this.add(this.btnDelete);
        this.btnCreate.setBounds(880, 20, 100, 34);

        this.add(this.lblSearch);
        this.add(this.txtSearch);
        this.add(this.btnSearch);
        this.add(this.btnCreate);

        this.btnFirst = new JButton("<<");
        this.btnPrevious = new JButton("<");
        this.btnPage = new JButton(String.valueOf(page));
        this.btnNext = new JButton(">");
        this.btnLast = new JButton(">>");

        this.btnFirst.setBounds(340, 470, 50, 34);
        this.btnPrevious.setBounds(400, 470, 50, 34);
        this.btnPage.setBounds(460, 470, 50, 34);
        this.btnNext.setBounds(520, 470, 50, 34);
        this.btnLast.setBounds(580, 470, 50, 34);

        this.add(this.btnFirst);
        this.add(this.btnPrevious);
        this.add(this.btnPage);
        this.add(this.btnNext);
        this.add(this.btnLast);

        // Table        
        String[] columnNames = {"ID", "Họ và tên", "Tài khoản", "Mật khẩu", "Ngày tạo", "Ngày cập nhật"};
        Object[][] data = {};
        this.modelAdmin = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelAdmin);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(142);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(170);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminForm = new AdminForm();
                adminForm.setVisible(true);
                adminForm.Create();
                adminController.loadAdmins(table);
            }
        });
        this.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    AdminUpdate adminUpdate = new AdminUpdate();
                    adminUpdate.setVisible(true);
//                  thêm vào textField
                    adminUpdate.setIdAdminUpdate(table.getModel().getValueAt(row, 0).toString());
                    adminUpdate.txtName.setText(table.getModel().getValueAt(row, 1).toString());
                    adminUpdate.txtAcount.setText(table.getModel().getValueAt(row, 2).toString());
                    adminUpdate.txtPassword.setText(table.getModel().getValueAt(row, 3).toString());
                    adminUpdate.update();
                    adminController.loadAdmins(table);                    
                }
            }
        });
        //delete action
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
                        AdminsModel.deleteAdmin(tblModel.getValueAt(row, 0).toString());
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " thành công.");
                        adminController.loadAdmins(table);
                    }
                }
            }
        });
        adminController.loadAdmins(table);
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);

    }
}
