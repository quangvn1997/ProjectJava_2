/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class TableUpdate extends JFrame {

    private JPanel tablePanel;
    private JLabel titleHeader;
    private JLabel lblStatus;
    private JComboBox cmbStatus;
    private JLabel lblName;
    public JTextField txtName;

    private JButton btnSubmit;
    private JButton btnReset;

    public TableUpdate() {
        this.setTitle("Quản lý bàn");
        this.setSize(450, 450);

        this.tablePanel = new JPanel();
        this.tablePanel.setBounds(0, 0, 450, 550);
        this.tablePanel.setBackground(Color.WHITE);

        this.titleHeader = new JLabel("Sửa thông tin bàn");
        this.titleHeader.setBounds(150, 70, 200, 50);
        this.titleHeader.setFont(new Font("Serif", Font.BOLD, 18));

        this.lblName = new JLabel("Tên bàn");
        this.txtName = new JTextField();

        this.lblStatus = new JLabel("Trạng thái");
        String[] status = new String[]{"Sẵn sàng", "Đã hỏng"};
        this.cmbStatus = new JComboBox<String>(status);

        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");

        this.lblStatus.setBounds(60, 205, 100, 50);
        this.cmbStatus.setBounds(160, 210, 150, 34);
        this.lblName.setBounds(60, 140, 100, 50);
        this.txtName.setBounds(160, 145, 200, 34);

        this.btnSubmit.setBounds(165, 285, 80, 34);
        this.btnReset.setBounds(265, 285, 80, 34);

        this.add(this.titleHeader);
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
    }
}
