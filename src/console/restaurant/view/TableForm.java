/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Admin;
import console.restaurant.entities.Table;
import console.restaurant.models.AdminsModel;
import console.restaurant.models.TablesModel;
import console.restaurant.utilities.ValidateUtilities;
import static console.restaurant.view.ManagerTable.table;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.TableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class TableForm extends JFrame {

    private JPanel tablePanel;
    private JLabel lblHeader;
    private JLabel lblStatus;
    public JComboBox cmbStatus;
    private JLabel lblName;
    public JTextField txtName;

    public JButton btnDelete;
    private JButton btnSubmit;
    public JButton btnReset;

    private TablesModel tableModel;
    private int action = 1;
    private int id = 0;

    public TableForm() {
        initComponent();
    }

    public TableForm(int action, int id) {
        this.tableModel = new TablesModel();
        this.action = action;
        this.id = id;
        initComponent();
    }

    public void setLblHeader(JLabel lblHeader) {
        this.lblHeader = lblHeader;
    }

    private void initComponent() {
        this.tableModel = new TablesModel();
        Table table1 = new Table();
        this.setTitle("Quản lý bàn");
        this.setSize(450, 450);

        this.tablePanel = new JPanel();
        this.tablePanel.setBounds(0, 0, 450, 550);
        this.tablePanel.setBackground(Color.WHITE);

        this.lblHeader = new JLabel("Thêm mới bàn");
        this.lblHeader.setBounds(150, 70, 200, 50);
        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 18));

        this.lblName = new JLabel("Tên bàn");
        this.txtName = new JTextField();

        this.lblStatus = new JLabel("Trạng thái");
        String[] status = new String[]{ " Sẵn sàng "," Đã hỏng "};
        this.cmbStatus = new JComboBox<String>(status);

        this.btnDelete = new JButton("Xóa");
        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");
        this.lblStatus.setBounds(60, 200, 100, 50);
        this.cmbStatus.setBounds(160, 200, 150, 34);
        this.lblName.setBounds(60, 135, 100, 50);
        this.txtName.setBounds(160, 135, 200, 34);

        this.btnDelete.setBounds(64, 265, 80, 34);
        this.btnSubmit.setBounds(164, 265, 80, 34);
        this.btnReset.setBounds(265, 265, 80, 34);

        if (action == 2) {
            // Lấy dữ liệu food từ db theo id.
            table1 = tableModel.getById(this.id);
            if (table1 == null) {
                JOptionPane.showMessageDialog(null, "Tài khoản không tồn tại hoặc đã bị xóa.");
                return;
            }
            this.lblHeader.setText("Sửa thông tin bàn");
            this.add(this.btnDelete);
        }

        this.add(this.lblHeader);
        this.add(this.lblStatus);
        this.add(this.lblName);
        this.add(this.txtName);
        this.add(this.cmbStatus);
        this.add(this.btnSubmit);
        this.add(this.btnReset);

        this.add(this.tablePanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);

        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền tên bàn !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtName.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên bàn đã tồn tại !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Table table = new Table();
                table.setName(txtName.getText());
                table.setStatus(cmbStatus.getSelectedIndex()== 0? 1:0);

                if (action == 1) {
                    if (tableModel.insertTable(table)) {
                        JOptionPane.showMessageDialog(null, "Thêm mới tài khoản thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi thêm tài khoản", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (action == 2) {
                    table.setId(id);
                    if (tableModel.update(table)) {
                        JOptionPane.showMessageDialog(null, "Sửa tài khoản thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi sửa tài khoản", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
                ManagerAdmin.page = 1;
                ManagerTable.loadTable();

                txtName.setText("");
                SwingUtilities.windowForComponent(tablePanel).dispose();
            }
        });
        this.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
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
                    int n = JOptionPane.showOptionDialog(form, "Bạn có muốn xóa bàn " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " không?  ", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
                    if (n == JOptionPane.YES_OPTION) {
//                        adminModel.delete((tblModel.getValueAt(row, 0).toString()));
                        tableModel.delete(Integer.parseInt(tblModel.getValueAt(row, 0).toString()));
                        JOptionPane.showMessageDialog(null, "Xóa tài khoản " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " thành công.");
//
                        ManagerTable.page = 1;
                        ManagerTable.loadTable();
                        SwingUtilities.windowForComponent(tablePanel).dispose();
                    }
                }
            }
        });
    }
}
