/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlynhahang.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class QuanLyBan extends JPanel {

    public QuanLyBan() {
        setBackground(new Color(48, 193, 204));
        setBounds(350, 110, 1220, 560);

        JLabel tittle = new JLabel();
        tittle.setText("QUẢN LÝ Bàn");
        tittle.setBounds(490, 0, 280, 70);
        tittle.setFont(new Font("Serif", Font.PLAIN, 34));
        
        
        
        add(tittle);
        setVisible(true);
        setLayout(null);
    }

}
