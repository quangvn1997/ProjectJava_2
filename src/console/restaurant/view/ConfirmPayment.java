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
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ConfirmPayment extends JFrame {

    private JPanel panelPayment;
    private JLabel lblHeader;
    private JButton btnConfirm;
    private JButton btnExit;
//    private BufferedImage btnIconExit;

    public ConfirmPayment() {
        this.setTitle("Thông báo");
        this.setSize(450, 270);
        this.panelPayment = new JPanel();
        this.lblHeader = new JLabel("Bạn có đồng ý thanh toán : Bàn " + " ?");
        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.btnConfirm = new JButton("Đồng ý");
        this.btnExit = new JButton("Bỏ qua");
        this.panelPayment.setBounds(0, 0, 450, 350);
        this.panelPayment.setBackground(Color.white);
        this.lblHeader.setBounds(90, 50, 300, 50);
        this.btnConfirm.setBounds(105, 120, 100, 40);
        this.btnExit.setBounds(240, 120, 100, 40);

//        btnIconExit = ImageIO.read(new File("../ProjectJava_2/src/console/restaurant/Image/add.jpg"));
//        btnConfirm = new JButton(new ImageIcon(btnIconExit));
        this.add(this.lblHeader);
        this.add(this.btnConfirm);
        this.add(this.btnExit);
        this.add(this.panelPayment);

        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PaymentOrder payment = new PaymentOrder();
                payment.setVisible(true);
                setVisible(false);
            }
        });

        this.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) throws Exception {
        ConfirmPayment confirm = new ConfirmPayment();
        confirm.setVisible(true);
    }
}
