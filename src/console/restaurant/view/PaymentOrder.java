/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Food;
import console.restaurant.entities.SessionAdmin;
import static console.restaurant.view.ManagerAdmin.table;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
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
    private JLabel lblPayer;
    private JLabel txtPayer;
    private ManagerPayment manager;
    private Food food;
    private JLabel lblCreatedAt;
    private JLabel lblUpdatedAt;
    private JLabel lblCreatedDate;
    private JLabel lblUpdatedDate;
    public static HashMap<Integer, Food> foodsOrder;

    public PaymentOrder() {
        foodsOrder = new HashMap<>();
        this.setSize(900, 640);
        this.setTitle("Thông báo");

        this.panelPayment = new JPanel();
        this.panelPayment.setBackground(Color.white);
        this.lblHeader = new JLabel("HÓA ĐƠN THANH TOÁN : Bàn 1");
        this.lblTotal = new JLabel("Tổng tiền :");
        this.lblVND = new JLabel("VNĐ");
        this.txtTotal = new JLabel(manager.lblTotal.getText());
        this.lblSale = new JLabel("Đã giảm giá :");
        int discount = Integer.parseInt(String.valueOf(ManagerPayment.discount.getSelectedItem()).trim());
        this.txtSale = new JLabel(Integer.toString(discount));
        this.lblUnit = new JLabel("%");
        this.lblgachngang = new JLabel("______________________________________");

        this.lblthanhtoan = new JLabel("Thành tiền :");
        this.lblThanhtien = new JLabel(manager.lblRealPayment.getText());
        this.lbldonvi = new JLabel("VNĐ");
        this.btnExit = new JButton("Thoát");
        this.btnPrint = new JButton("In hóa đơn");
        this.lblPayer = new JLabel("Thu ngân :");
        this.lblPayer.setBounds(50, 535, 100, 50);
        this.txtPayer = new JLabel();
        this.txtPayer.setText(SessionAdmin.getName());
        this.lblCreatedAt = new JLabel("Giờ tạo : ");
        this.lblUpdatedAt = new JLabel("Giờ kết thúc : ");
        this.lblCreatedDate = new JLabel(" 12:12:12  22-10-2017");
        this.lblUpdatedDate = new JLabel(" 13:10:12  22-10-2017");

        this.lblHeader.setBounds(350, 0, 370, 50);
        this.panelPayment.setBounds(0, 0, 900, 720);
        this.lblTotal.setBounds(360, 405, 100, 50);
        this.txtTotal.setBounds(440, 413, 200, 35);
        this.lblVND.setBounds(540, 405, 100, 50);

        this.lblSale.setBounds(345, 450, 120, 35);
        this.txtSale.setBounds(460, 450, 30, 35);
        this.lblUnit.setBounds(480, 450, 100, 35);
        this.lblgachngang.setBounds(340, 460, 300, 35);

        this.lblthanhtoan.setBounds(352, 490, 100, 35);
        this.lblThanhtien.setBounds(440, 490, 200, 35);
        this.lbldonvi.setBounds(540, 490, 100, 35);

        this.lblCreatedAt.setBounds(70, 420, 200, 35);
        this.lblUpdatedAt.setBounds(45, 460, 200, 35);
        this.lblCreatedDate.setBounds(125, 420, 200, 35);
        this.lblUpdatedDate.setBounds(125, 460, 200, 35);

        this.btnExit.setBounds(600, 535, 100, 35);
        this.btnPrint.setBounds(720, 535, 100, 35);

        this.txtPayer.setBounds(115, 543, 120, 34);

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
        String[] columnNames = {"Stt", "Tên dịch vụ", "Đơn giá", "Số lượng", "Thành tiền", "Ghi chú"};
        Object[][] data = {};
        this.modelPayment = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelPayment);
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(80);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(133);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(40, 45, 800, 350);

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
        this.add(this.lblPayer);
        this.add(this.txtPayer);
        this.add(this.lblCreatedAt);
        this.add(this.lblUpdatedAt);
        this.add(this.lblCreatedDate);
        this.add(this.lblUpdatedDate);
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
