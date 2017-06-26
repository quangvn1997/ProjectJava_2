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
import javax.swing.JOptionPane;
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
    private JLabel txtTotal;
    private JTable table;
    private DefaultTableModel modelPayment;
    private JScrollPane scrollPane;
    private JLabel lblSale;
    private JLabel txtSale;
    private JLabel lblUnit;
    private JLabel lblgachngang;
    private JLabel lblthanhtoan;
    private JLabel lblThanhtien;
    private JLabel lbldonvi;

    public PaymentOrder() {
        this.setSize(900, 720);
        this.setTitle("Thông báo");

        this.panelPayment = new JPanel();
        this.panelPayment.setBackground(Color.white);
        this.lblHeader = new JLabel("HÓA ĐƠN THANH TOÁN : Bàn 1");
        this.lblTotal = new JLabel("Tổng tiền :");
        this.lblVND = new JLabel("VNĐ");
        this.txtTotal = new JLabel("55,000,000,000");
        this.btnExit = new JButton("Thoát");
        this.btnPrint = new JButton("In hóa đơn");
        this.lblSale = new JLabel("Đã giảm giá :");
        this.txtSale = new JLabel("90");
        this.lblUnit = new JLabel("%");
        this.lblgachngang = new JLabel("______________________________________");

        this.lblthanhtoan = new JLabel("Thành tiền :");
        this.lblThanhtien = new JLabel("550,000,000");
        this.lbldonvi = new JLabel("VNĐ");

        this.lblHeader.setBounds(300, 15, 370, 50);
        this.panelPayment.setBounds(0, 0, 900, 720);
        this.lblTotal.setBounds(280, 455, 100, 50);
        this.txtTotal.setBounds(400, 460, 200, 35);
        this.lblVND.setBounds(560, 452, 100, 50);

        this.lblSale.setBounds(260, 505, 120, 35);
        this.txtSale.setBounds(420, 503, 30, 35);
        this.lblUnit.setBounds(450, 503, 100, 35);
        this.lblgachngang.setBounds(300, 530, 300, 35);

        this.lblthanhtoan.setBounds(270, 560, 100, 35);
        this.lblThanhtien.setBounds(390, 560, 200, 35);
        this.lbldonvi.setBounds(560, 560, 100, 35);

        this.btnExit.setBounds(600, 620, 100, 35);
        this.btnPrint.setBounds(720, 620, 100, 35);

        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 22));
        this.lblTotal.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtTotal.setFont(new Font("Serif", Font.BOLD, 22));
        this.lblVND.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblSale.setFont(new Font("Serif", Font.BOLD, 18));
        this.txtSale.setFont(new Font("Serif", Font.BOLD, 22));
        this.lblUnit.setFont(new Font("Serif", Font.BOLD, 22));
        this.lblthanhtoan.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblThanhtien.setFont(new Font("Serif", Font.BOLD, 22));
        this.lbldonvi.setFont(new Font("Serif", Font.BOLD, 18));

        this.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        this.btnPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton B1 = (JButton) e.getSource();
                B1.setBackground(new Color(250, 250, 250));
                JOptionPane.showMessageDialog(null, "In hóa đơn thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }
        });
        String[] columnNames = {"Stt", "Tên dịch vụ", "Đơn giá", "Số lượng", "Thành tiền", "Giờ tạo", "Giờ thanh toán"};
        Object[][] data = {};
        this.modelPayment = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelPayment);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(124);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(120);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(120);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(40, 70, 800, 380);

        this.add(this.lblthanhtoan);
        this.add(this.lblThanhtien);
        this.add(this.lbldonvi);
        this.add(this.lblgachngang);
        this.add(this.lblSale);
        this.add(this.txtSale);
        this.add(this.lblUnit);
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
