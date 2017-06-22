/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import static console.restaurant.view.ManagerAdmin.table;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class PaymentOrder extends JFrame {

    private JPanel panelPayment;
    private JLabel lblHeader;
    private JButton btnPrint;
    private JButton btnExit;
    private JLabel lblTotal;
    private JLabel lblVND;
    private JTextField txtTotal;
    private JTable table;
    private DefaultTableModel modelPayment;
    private JScrollPane scrollPane;

    public PaymentOrder() {
        this.setSize(900, 600);
        this.setTitle("Thông báo");

        this.panelPayment = new JPanel();
        this.panelPayment.setBackground(Color.white);
        this.lblHeader = new JLabel("HÓA ĐƠN THANH TOÁN");
        this.lblTotal = new JLabel("Tổng tiền :");
        this.lblVND = new JLabel("VNĐ");
        this.txtTotal = new JTextField();
        this.btnExit = new JButton("Thoát");
        this.btnPrint = new JButton("In hóa đơn");

        this.lblHeader.setBounds(350, 15, 270, 50);
        this.panelPayment.setBounds(0, 0, 900, 600);
        this.lblTotal.setBounds(300, 455, 100, 50);
        this.txtTotal.setBounds(400, 460, 160, 35);
        this.lblVND.setBounds(580, 455, 100, 50);
        this.btnExit.setBounds(600, 510, 100, 35);
        this.btnPrint.setBounds(720, 510, 100, 35);

        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblTotal.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtTotal.setFont(new Font("Serif", Font.BOLD, 22));
        this.lblVND.setFont(new Font("Serif", Font.BOLD, 18));

        this.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        String[] columnNames = {"Stt", "Tên dịch vụ", "Đơn giá", "Số lượng","Giảm giá", "Thành tiền", "Ngày tạo", "Trạng thái"};
        Object[][] data = {};
        this.modelPayment = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelPayment);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(110);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(75);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(107);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(105);
        this.table.getColumnModel().getColumn(7).setPreferredWidth(100);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(40, 70, 800, 380);

        this.add(this.lblVND);
        this.add(this.lblTotal);
        this.add(this.txtTotal);
        this.add(this.btnExit);
        this.add(this.btnPrint);
        this.add(scrollPane);
        this.add(this.lblHeader);
        this.add(this.panelPayment);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        PaymentOrder payment = new PaymentOrder();
        payment.setVisible(true);
    }
}
