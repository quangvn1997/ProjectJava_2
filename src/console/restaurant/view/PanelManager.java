/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.SessionAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class PanelManager extends JFrame {

    /**
     * Creates new form quanlyban
     */
//    Phần quản lý admin
    public ManagerAdmin quanlyadmin = new ManagerAdmin();

//  Quản lý món ăn    
    public final ListTable panelBan = new ListTable();
    public ManagerTable quanlyban = new ManagerTable();
    public ManagerFood quanlymonan = new ManagerFood();
    public ManagerStatistic quanlythongke = new ManagerStatistic();

    public PanelManager() {
        initComponents();

//        danh mục bàn
        panelBan.setBounds(350, 110, 1000, 500);
        panelBan.setBackground(Color.white);
        panel.add(panelBan);
        panelBan.setVisible(true);

//        Trạng thái
        JLabel trangthai = new JLabel();
        trangthai.setText("Trạng thái :");
        trangthai.setBounds(50, 425, 100, 70);
        trangthai.setFont(new Font("Serif", Font.PLAIN, 18));
        panelBan.add(trangthai);
        String[] bookTitles = new String[]{"Sẵn sàng", "Đang phục vụ"};
        JComboBox<String> bookList = new JComboBox<>(bookTitles);
        bookList.setFont(new Font("Serif", Font.PLAIN, 18));
        bookList.setBounds(150, 440, 150, 40);
        panelBan.add(bookList);
        // Phân trang

        JButton number = new JButton();
        number.setText("1");
        number.setBounds(450, 430, 50, 40);

        JButton next = new JButton();
        next.setText(">>");
        next.setBounds(515, 430, 50, 40);

        JButton previous = new JButton();
        previous.setText("<<");
        previous.setBounds(385, 430, 50, 40);

        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("called");
                panelBan.page += 1;
                panelBan.setVisible(false);
                panelBan.setVisible(true);
                number.setText(Integer.toString(panelBan.page));
                panelBan.loadTable();
//                if(){
//                next.setVisible(false);
//                }
                previous.setVisible(true);
            }
        });

        previous.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                panelBan.page -= 1;
                if (panelBan.page == 1) {
                    previous.setVisible(false);
                    next.setVisible(true);
                }
                panelBan.setVisible(false);
                panelBan.setVisible(true);
                number.setText(Integer.toString(panelBan.page));
                panelBan.loadTable();
            }
        });

        panelBan.add(number);
        panelBan.add(next);
        panelBan.add(previous);

//      Thêm giao diện quản lý admin
        panel.add(this.quanlyadmin);
//        this.quanlyban.setVisible(true);

//      Thêm giao diện quản lý bàn
        panel.add(this.quanlyban);
//        this.quanlyadmin.setVisible(false);

//      Thêm giao diện quản lý món ăn
        panel.add(this.quanlymonan);

//        Thêm giao diện quản lý thông kê
        panel.add(this.quanlythongke);

        quanlyadmin.setVisible(false);
        quanlyban.setVisible(false);
        quanlymonan.setVisible(false);
        quanlythongke.setVisible(false);
        clockBan();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void clockBan() {
        Thread clock = new Thread() {
            public void run() {
                Date today = new Date();
                try {
                    for (;;) {
                        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                        Date date1 = new Date();
//                      
                        date.setText("Hôm nay ngày: " + dateFormat.format(date1));
                        time.setText(timeFormat.format(date1));
//                        TimeZone.setDefault(TimeZone.getTimeZone("UTC+7"));
                        sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };
        clock.start();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnDatBan = new javax.swing.JButton();
        btnAdmin = new javax.swing.JButton();
        btnThongke = new javax.swing.JButton();
        btnMonan = new javax.swing.JButton();
        btnBan = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        btnLogOut = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuDatBan = new javax.swing.JMenuItem();
        menuAdmin = new javax.swing.JMenuItem();
        menuBan = new javax.swing.JMenuItem();
        menuMonAn = new javax.swing.JMenuItem();
        menuThongKe = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        panel.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnDatBan.setBackground(new java.awt.Color(255, 255, 255));
        btnDatBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnDatBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/listban.jpg"))); // NOI18N
        btnDatBan.setText("Đặt Bàn");
        btnDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatBanActionPerformed(evt);
            }
        });

        btnAdmin.setBackground(new java.awt.Color(255, 255, 255));
        btnAdmin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/admin.jpg"))); // NOI18N
        btnAdmin.setText("Quản Lý Admin");
        btnAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdminActionPerformed(evt);
            }
        });

        btnThongke.setBackground(new java.awt.Color(255, 255, 255));
        btnThongke.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/pay1.jpg"))); // NOI18N
        btnThongke.setText("Quản Lý Thống Kê");
        btnThongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongkeActionPerformed(evt);
            }
        });

        btnMonan.setBackground(new java.awt.Color(255, 255, 255));
        btnMonan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnMonan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/food.jpg"))); // NOI18N
        btnMonan.setText("Quản Lý Món Ăn");
        btnMonan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonanActionPerformed(evt);
            }
        });

        btnBan.setBackground(new java.awt.Color(255, 255, 255));
        btnBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnBan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/table.jpg"))); // NOI18N
        btnBan.setText("Quản Lý Bàn");
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnMonan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnBan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDatBan, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnThongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnDatBan, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16)
                .addComponent(btnAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMonan, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnThongke, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );

        jLabel1.setFont(new java.awt.Font("Brush Script MT", 2, 65)); // NOI18N
        jLabel1.setText("Furious Food & Drinks");

        date.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N

        time.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/exit.jpg"))); // NOI18N
        jButton3.setText("Thoát");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Thu Ngân : ");

        jTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        btnLogOut.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/login.jpg"))); // NOI18N
        btnLogOut.setText("Đăng Xuất");
        btnLogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogOutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(87, 87, 87)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(145, 145, 145)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelLayout.createSequentialGroup()
                                .addGap(203, 203, 203)
                                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(36, Short.MAX_VALUE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLogOut)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createSequentialGroup()
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(536, 536, 536)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLogOut, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jMenu1.setText("Quản Lý");

        menuDatBan.setText("Quản Lý Đặt Bàn");
        menuDatBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuDatBanActionPerformed(evt);
            }
        });
        jMenu1.add(menuDatBan);

        menuAdmin.setText("Quản Lý Admin");
        menuAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAdminActionPerformed(evt);
            }
        });
        jMenu1.add(menuAdmin);

        menuBan.setText("Quản Lý Bàn");
        menuBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuBanActionPerformed(evt);
            }
        });
        jMenu1.add(menuBan);

        menuMonAn.setText("Quản Lý Món Ăn");
        menuMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMonAnActionPerformed(evt);
            }
        });
        jMenu1.add(menuMonAn);

        menuThongKe.setText("Quản Lý Thông Kê");
        menuThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuThongKeActionPerformed(evt);
            }
        });
        jMenu1.add(menuThongKe);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Thoát");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatBanActionPerformed
//        Bắt sự kiện button đặt bàn        
        panelBan.setVisible(true);
//                monan.setVisible(false);
        quanlyadmin.setVisible(false);
        quanlyban.setVisible(false);
        quanlymonan.setVisible(false);
        quanlythongke.setVisible(false);

        btnDatBan.setBackground(new Color(74, 135, 178));
        btnAdmin.setBackground(Color.white);
        btnBan.setBackground(Color.white);
        btnMonan.setBackground(Color.white);
        btnThongke.setBackground(Color.white);
    }//GEN-LAST:event_btnDatBanActionPerformed

    private void btnAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdminActionPerformed
        //        Bắt sự kiện quản lý admin
        quanlyadmin.setVisible(true);

        panelBan.setVisible(false);
        quanlyban.setVisible(false);
        quanlymonan.setVisible(false);
        quanlythongke.setVisible(false);

        btnDatBan.setBackground(Color.white);
        btnAdmin.setBackground(new Color(74, 135, 178));
        btnBan.setBackground(Color.white);
        btnMonan.setBackground(Color.white);
        btnThongke.setBackground(Color.white);
    }//GEN-LAST:event_btnAdminActionPerformed

    private void btnThongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongkeActionPerformed
        // TODO add your handling code here:
        //       Bắt sự kiện quản lý thống kê
        quanlythongke.setVisible(true);
        
        quanlyadmin.setVisible(false);
        quanlyban.setVisible(false);
        panelBan.setVisible(false);
        quanlymonan.setVisible(false);
        quanlyban.setVisible(false);

        btnDatBan.setBackground(Color.white);
        btnAdmin.setBackground(Color.white);
        btnBan.setBackground(Color.white);
        btnMonan.setBackground(Color.white);

    }//GEN-LAST:event_btnThongkeActionPerformed

    private void btnMonanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonanActionPerformed
        // TODO add your handling code here:
        //        Bắt sự kiện quản lý món ăn
        quanlymonan.setVisible(true);

        quanlyadmin.setVisible(false);
        quanlyban.setVisible(false);
        panelBan.setVisible(false);
        quanlythongke.setVisible(false);
        quanlyban.setVisible(false);

        btnDatBan.setBackground(Color.white);
        btnAdmin.setBackground(Color.white);
        btnBan.setBackground(Color.white);
        btnMonan.setBackground(new Color(74, 135, 178));
        btnThongke.setBackground(Color.white);
    }//GEN-LAST:event_btnMonanActionPerformed

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        // TODO add your handling code here:
        //        Bắt sự kiện quản lý đặt bàn
        quanlyban.setVisible(true);
        quanlyadmin.setVisible(false);
        quanlythongke.setVisible(false);
        panelBan.setVisible(false);
        quanlymonan.setVisible(false);

        btnDatBan.setBackground(Color.white);
        btnAdmin.setBackground(Color.white);
        btnBan.setBackground(new Color(74, 135, 178));
        btnMonan.setBackground(Color.white);
        btnThongke.setBackground(Color.white);

    }//GEN-LAST:event_btnBanActionPerformed

    private void menuDatBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuDatBanActionPerformed
        // TODO add your handling code here:
        panelBan.setVisible(true);
//                monan.setVisible(false);
        quanlyadmin.setVisible(false);
        quanlyban.setVisible(false);
        quanlymonan.setVisible(false);
        quanlythongke.setVisible(false);

        btnDatBan.setBackground(new Color(74, 135, 178));
        btnAdmin.setBackground(Color.white);
        btnBan.setBackground(Color.white);
        btnMonan.setBackground(Color.white);
        btnThongke.setBackground(Color.white);
    }//GEN-LAST:event_menuDatBanActionPerformed

    private void menuAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAdminActionPerformed
        // TODO add your handling code here:
        quanlyadmin.setVisible(true);

        panelBan.setVisible(false);
        quanlyban.setVisible(false);
        quanlymonan.setVisible(false);
        quanlythongke.setVisible(false);

        btnDatBan.setBackground(Color.white);
        btnAdmin.setBackground(new Color(74, 135, 178));
        btnBan.setBackground(Color.white);
        btnMonan.setBackground(Color.white);
        btnThongke.setBackground(Color.white);

    }//GEN-LAST:event_menuAdminActionPerformed

    private void btnLogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogOutActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        Login login = new Login();
        login.setVisible(true);
    }//GEN-LAST:event_btnLogOutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        jTextField1.setText(SessionAdmin.getName());
    }//GEN-LAST:event_formWindowOpened

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void menuMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMonAnActionPerformed
        // TODO add your handling code here:
        menuMonAn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanlymonan.setVisible(true);

                quanlyadmin.setVisible(false);
                quanlyban.setVisible(false);
                panelBan.setVisible(false);
                quanlythongke.setVisible(false);
                quanlyban.setVisible(false);

                btnDatBan.setBackground(Color.white);
                btnAdmin.setBackground(Color.white);
                btnBan.setBackground(Color.white);
                btnMonan.setBackground(new Color(74, 135, 178));
                btnThongke.setBackground(Color.white);

            }
        });
    }//GEN-LAST:event_menuMonAnActionPerformed

    private void menuThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuThongKeActionPerformed
        // TODO add your handling code here:
        menuThongKe.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanlythongke.setVisible(true);
                quanlymonan.setVisible(false);
                quanlyadmin.setVisible(false);
                quanlyban.setVisible(false);

                btnDatBan.setBackground(Color.white);
                btnAdmin.setBackground(Color.white);
                btnBan.setBackground(Color.white);
                btnMonan.setBackground(Color.white);
                btnThongke.setBackground(new Color(74, 135, 178));
            }
        });
    }//GEN-LAST:event_menuThongKeActionPerformed

    private void menuBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuBanActionPerformed
        // TODO add your handling code here:
        menuBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                quanlyban.setVisible(true);
                quanlyadmin.setVisible(false);
                quanlythongke.setVisible(false);
                panelBan.setVisible(false);
                quanlymonan.setVisible(false);

                btnDatBan.setBackground(Color.white);
                btnAdmin.setBackground(Color.white);
                btnBan.setBackground(new Color(74, 135, 178));
                btnMonan.setBackground(Color.white);
                btnThongke.setBackground(Color.white);
            }
        });
    }//GEN-LAST:event_menuBanActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PanelManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PanelManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnDatBan;
    private javax.swing.JButton btnLogOut;
    private javax.swing.JButton btnMonan;
    private javax.swing.JButton btnThongke;
    private javax.swing.JLabel date;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuItem menuAdmin;
    private javax.swing.JMenuItem menuBan;
    private javax.swing.JMenuItem menuDatBan;
    private javax.swing.JMenuItem menuMonAn;
    private javax.swing.JMenuItem menuThongKe;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel time;
    // End of variables declaration//GEN-END:variables
}
