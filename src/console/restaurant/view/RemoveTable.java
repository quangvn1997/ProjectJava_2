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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class RemoveTable extends JFrame {

    private JPanel panelRomoveTalbe;
    private JLabel lblHeader;
    private JLabel lblTitle;
    private JButton btnSubmit;
    private JButton btnExit;
    private JSpinner spNumber;

    public RemoveTable() {
        this.setSize(400, 370);

        this.panelRomoveTalbe = new JPanel();
        this.panelRomoveTalbe.setBounds(0, 0, 400, 400);
        this.panelRomoveTalbe.setBackground(Color.white);

        this.lblHeader = new JLabel("Chuyển Bàn 1 tới bàn :");
        this.lblTitle = new JLabel("Chọn bàn cần chuyển :");
        this.btnSubmit = new JButton("Đồng ý & Thoát");
        this.btnExit = new JButton("Thoát");
        this.spNumber = new JSpinner();

        this.lblHeader.setBounds(20, 10, 150, 30);
        this.lblTitle.setBounds(20, 50, 150, 50);
        this.spNumber.setBounds(140, 110, 100, 40);
        this.btnSubmit.setBounds(120, 180, 150, 40);
        this.btnExit.setBounds(140, 240, 120, 40);
        this.spNumber.setFont(new Font("Serif", Font.BOLD, 27));

        this.btnSubmit.setBackground(new Color(187, 189, 193));
        this.spNumber.setBackground(new Color(187, 189, 193));
        
        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Chuyển bàn thành công.");
                setVisible(false);
            }
        });
        this.btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        this.add(this.btnExit);
        this.add(this.btnSubmit);
        this.add(this.spNumber);
        this.add(this.lblHeader);
        this.add(this.lblTitle);
        this.add(this.panelRomoveTalbe);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        RemoveTable remoteTable = new RemoveTable();
        remoteTable.setVisible(true);
    }
}
