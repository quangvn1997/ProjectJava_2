/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class CreateOrder extends JFrame {

    private JPanel orderPanel;
    private JLabel lblHeader;
    private JButton btnCreateOrder;
    private JButton btnExit;

    private ManagerPayment jframePaymen = new ManagerPayment();

    public CreateOrder() {
        this.setSize(450, 350);
        this.setTitle("Tạo mới");

        this.orderPanel = new JPanel();
        this.orderPanel.setBounds(0, 0, 450, 400);
        this.orderPanel.setBackground(Color.WHITE);

        this.lblHeader = new JLabel("Thao tác với bàn số : ");
        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblHeader.setBounds(125, 30, 220, 50);

        this.btnCreateOrder = new JButton("Tạo mới hóa đơn");
        this.btnExit = new JButton("Thoát");
//
        this.btnCreateOrder.setBounds(125, 110, 200, 40);
        this.btnExit.setBounds(125, 180, 200, 40);

        this.btnCreateOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jframePaymen.setVisible(true);
                setVisible(false);
            }
        });

        this.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        this.add(this.lblHeader);
        this.add(this.btnCreateOrder);
        this.add(this.btnExit);

        this.add(this.orderPanel);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
    }
    
    public JButton getBtnCreateOrder() {
        return btnCreateOrder;
    }

    public JLabel getLblHeader() {
        return lblHeader;
    }

    public ManagerPayment getJframePaymen() {
        return jframePaymen;
    }

    public static void main(String[] args) {
        CreateOrder create = new CreateOrder();
        create.setVisible(true);
    }
}
