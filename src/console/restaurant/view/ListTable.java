/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Table;
import console.restaurant.models.TablesModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ListTable extends javax.swing.JPanel {

    int length = 5;
    static int TONG_SO_BAN = 15;
    int banHienTai = 0;
    int sotrang = 1;
    public int page = 1;
    public static JButton[] listBan;
    JButton[] listtrang = new JButton[sotrang];
    private TablesModel model = new TablesModel();
    private Map<String, JButton> dynamicButtons;
    
    public ListTable() {

        initComponents();
        loadTable();
    }

    public void loadTable() {
        // Load danh sách bàn trong db ra, lấy theo tổng số bàn là 15.
        ArrayList<Table> list = model.getAvailableTable(page, TONG_SO_BAN);

        if (listBan != null && listBan.length > 0) {
            for (JButton jButton : listBan) {
                this.remove(jButton);
            }
        }
        listBan = new JButton[list.size()];
        // xử lí tự động chỉnh khi thêm bàn hoặc xóa bàn
        int x = 40;
        int y = 30;
        int count = 1;

        // Tạo danh sách button từ số bàn lấy ra.
        for (int i = 0; i < list.size(); i++) {
            // chỉnh vị trí bàn
            listBan[i] = new JButton(list.get(i).getName());
            listBan[i].setBounds(x, y, 150, 100);
            if (list.get(i).getStatus() == 1) {
                listBan[i].setBackground(new Color(142, 242, 144));
            } else if (list.get(i).getStatus() == 2) {
                listBan[i].setBackground(new Color(255, 26, 26));
            } else {
                listBan[i].setBackground(new Color(255, 255, 255));
            }
            int a = list.get(i).getId();
            listBan[i].setForeground(Color.black);
            listBan[i].setFont(new Font("Serif", Font.PLAIN, 24));
            banHienTai = i;
            listBan[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //JButton B1 = (JButton) e.getSource();
                    //B1.setBackground(new Color(255, 26, 26));
                    ManagerPayment jframePaymen = new ManagerPayment(a);
                    jframePaymen.setVisible(true);
                }
            });

            // xử lí khi thêm 1 bàn mới và add bàn vào danh sách list bàn
            x += 190;
            // chỉnh tọa độ danh sách bàn
            if (i + 1 == count * length) {
                count++;
                y += 130;
                x = 40;
            }
            this.add(listBan[i]);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        setForeground(new java.awt.Color(255, 255, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1036, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 569, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
