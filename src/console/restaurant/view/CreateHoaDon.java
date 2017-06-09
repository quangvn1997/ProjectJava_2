/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class CreateHoaDon extends JFrame {

    private JButton create;
    private JButton exit;
    public ManagerPay quanlythanhtoan = new ManagerPay();

    public CreateHoaDon() {
        this.setBackground(Color.white);
        this.setBounds(200, 500, 400, 300);

//        Button tạo mới hóa đơn
        this.create = new JButton();
        this.create.setText("Tạo mới hóa đơn");
        this.create.setBounds(100, 50, 150, 50);
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanlythanhtoan.setVisible(true);
                setVisible(false);
            }
        });

//        Button thoát
        this.exit = new JButton();
        this.exit.setText("Thoát");
        this.exit.setBounds(100, 120, 150, 50);

        this.add(this.create);
        this.add(this.exit);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }

}
