/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Table;
import console.restaurant.models.TablesModel;
import console.restaurant.utilities.ValidateUtilities;
import java.awt.Color;
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

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class TableForm extends JFrame {

    private JPanel tablePanel;
    private JLabel titleHeader;
    private JLabel lblNumber;
    private JLabel lblStatus;
    public JTextField txtNumber;
    private JComboBox cmbStatus;
    private JLabel lblName;
    public JTextField txtName;

    private JButton btnSubmit;
    private JButton btnReset;

    public TableForm() {
        this.setTitle("Quản lý bàn");
        this.setSize(450, 450);

        this.tablePanel = new JPanel();
        this.tablePanel.setBounds(0, 0, 450, 550);
        this.tablePanel.setBackground(Color.WHITE);

        this.titleHeader = new JLabel("Thêm mới bàn");
        this.titleHeader.setBounds(150, 70, 200, 50);
        this.titleHeader.setFont(new Font("Serif", Font.BOLD, 18));

        this.lblNumber = new JLabel("Số bàn");
        this.txtNumber = new JTextField();
        this.lblName = new JLabel("Tên bàn");
        this.txtName = new JTextField();

        this.lblStatus = new JLabel("Trạng thái");
        String[] status = new String[]{"Sẵn sàng", "Đã hỏng"};
        this.cmbStatus = new JComboBox<String>(status);

        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");

        this.lblNumber.setBounds(60, 135, 100, 50);
        this.txtNumber.setBounds(160, 135, 200, 34);
        this.lblStatus.setBounds(60, 255, 100, 50);
        this.cmbStatus.setBounds(160, 260, 150, 34);
        this.lblName.setBounds(60, 195, 100, 50);
        this.txtName.setBounds(160, 195, 200, 34);

        this.btnSubmit.setBounds(164, 335, 80, 34);
        this.btnReset.setBounds(265, 335, 80, 34);

        this.add(this.titleHeader);
        this.add(this.lblNumber);
        this.add(this.lblStatus);
        this.add(this.txtNumber);
        this.add(this.lblName);
        this.add(this.txtName);
        this.add(this.cmbStatus);
        this.add(this.btnSubmit);
        this.add(this.btnReset);

        this.add(this.tablePanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }

    public void Create() {

        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtNumber.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền số bàn thêm !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền tên bàn !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtName.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên bàn đã tồn tại !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Table table = new Table();
                table.setName(txtName.getText());
                table.setNumber(Integer.parseInt(txtNumber.getText()));
                TablesModel.insertTable(table);

                txtNumber.setText("");
                txtName.setText("");
            }
        });
        this.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNumber.setText("");
                txtName.setText("");
            }
        });

//    public static void main(String[] args) {
//        TableForm table = new TableForm();
//        table.setVisible(true);
//    }
    }
}
