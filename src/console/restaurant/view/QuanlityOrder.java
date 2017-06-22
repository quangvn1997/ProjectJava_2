/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class QuanlityOrder extends JFrame {

    private JLabel lblHeader;
    private JLabel NameOrder;
    private JPanel panelQuanlity;
    private JLabel lblNumber;
    private JSpinner txtNumber;
    private JButton btnAddOrder;

    private JLabel lblInputFast;
    private JButton btnInput1;
    private JButton btnInput2;
    private JButton btnInput3;
    private JButton btnInput4;
    private JButton btnInput5;

    public QuanlityOrder() {
        this.setTitle("Nhập số lượng");
        this.setSize(450, 440);

        this.panelQuanlity = new JPanel();
        this.panelQuanlity.setBackground(Color.white);
        this.lblHeader = new JLabel("Thêm số lượng cho");
//        Fix cứng tên món ăn test
        this.NameOrder = new JLabel("Gà quay");
        this.lblNumber = new JLabel("Nhập số lượng :");
        this.txtNumber = new JSpinner();
        this.btnAddOrder = new JButton("Thêm vào hóa đơn");
        this.btnAddOrder.setBackground(new Color(187, 189, 193));
        this.lblInputFast = new JLabel("Thêm nhanh vào hóa đơn ");
        this.btnInput1 = new JButton("SL = 1");
        this.btnInput1.setBackground(new Color(187, 189, 193));
        this.btnInput2 = new JButton("SL = 2");
        this.btnInput2.setBackground(new Color(187, 189, 193));
        this.btnInput3 = new JButton("SL = 3");
        this.btnInput3.setBackground(new Color(187, 189, 193));
        this.btnInput4 = new JButton("SL = 4");
        this.btnInput4.setBackground(new Color(187, 189, 193));
        this.btnInput5 = new JButton("SL = 5");
        this.btnInput5.setBackground(new Color(187, 189, 193));

        this.panelQuanlity.setBounds(0, 0, 600, 500);
        this.lblHeader.setBounds(20, 10, 150, 50);
        this.NameOrder.setBounds(20, 45, 400, 40);
        this.NameOrder.setFont(new Font("Serif", Font.BOLD, 22));
        this.txtNumber.setFont(new Font("Serif", Font.BOLD, 22));
        this.lblNumber.setBounds(20, 80, 150, 50);
        this.txtNumber.setBounds(150, 130, 150, 40);
        this.btnAddOrder.setBounds(150, 190, 150, 40);
        this.lblInputFast.setBounds(20, 240, 150, 40);

        this.btnInput1.setBounds(50, 285, 100, 35);
        this.btnInput2.setBounds(170, 285, 100, 35);
        this.btnInput3.setBounds(290, 285, 100, 35);
        this.btnInput4.setBounds(100, 340, 100, 35);
        this.btnInput5.setBounds(220, 340, 100, 35);

        this.add(this.btnInput1);
        this.add(this.btnInput2);
        this.add(this.btnInput3);
        this.add(this.btnInput4);
        this.add(this.btnInput5);
        this.add(this.lblInputFast);
        this.add(this.btnAddOrder);
        this.add(this.txtNumber);
        this.add(this.lblNumber);
        this.add(this.NameOrder);
        this.add(this.lblHeader);
        this.add(this.panelQuanlity);

        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        QuanlityOrder quanlity = new QuanlityOrder();
        quanlity.setVisible(true);
    }

}
