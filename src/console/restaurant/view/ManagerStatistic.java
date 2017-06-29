/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Order;
import console.restaurant.models.StatisticModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;



/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */


import com.toedter.calendar.JDateChooser;
import console.restaurant.models.TablesModel;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Date;

import java.text.DateFormat;

public class ManagerStatistic extends JPanel {

    public static int page = 1;
    public static int limit = 14;
    public static int count = 0;
    public static int totalPage = 0;
    private static float fullPrice = 0;
    private static StatisticModel statisticModel = new StatisticModel();
    private JLabel lblStartD;
    private JLabel lblEndD;
    private static JLabel lblFullPrice;
    private static JDateChooser startPicker;
    private static JDateChooser endPicker;
    private static JComboBox cbList;
    private static JButton btnFirst;
    private static JButton btnPrevious;
    private static JButton btnNext;
    private static JButton btnLast;
    private static JButton btnPage;

    public static JTable table;
    private static DefaultTableModel modelStatistic;
    private static JScrollPane scrollPane;

    public ManagerStatistic() {
        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Title.
        this.lblStartD = new JLabel("Chọn Ngày Bắt Đầu:");
        this.lblStartD.setBounds(20, 20, 140, 34);

        this.lblEndD = new JLabel("Chọn Ngày Cuối Cùng:");
        this.lblEndD.setBounds(400, 20, 140, 34);

        this.lblFullPrice = new JLabel("Tổng Giá : " + fullPrice);
        this.lblFullPrice.setBounds(700, 470, 200, 40);
        this.lblFullPrice.setFont(new Font("Serif", Font.PLAIN, 25));
        //Button.
        String[] listStrings = {"Theo hóa đơn", "Theo món"};
        this.cbList = new JComboBox(listStrings);
        this.cbList.setBounds(820, 20, 150, 34);
        this.btnFirst = new JButton("<<");
        this.btnPrevious = new JButton("<");
        this.btnPage = new JButton(String.valueOf(page));
        this.btnNext = new JButton(">");
        this.btnLast = new JButton(">>");

        this.btnFirst.setBounds(340, 470, 50, 34);
        this.btnPrevious.setBounds(400, 470, 50, 34);
        this.btnPage.setBounds(460, 470, 50, 34);
        this.btnNext.setBounds(520, 470, 50, 34);
        this.btnLast.setBounds(580, 470, 50, 34);
        //Date.
        Calendar c = Calendar.getInstance();   // this takes current date
        c.set(Calendar.DATE, Calendar.getInstance().getActualMinimum(Calendar.DATE));
        //Start.
        this.startPicker = new JDateChooser();
        this.startPicker.setBounds(160, 20, 200, 34);
        this.startPicker.setDateFormatString("dd/MM/yyyy");
        this.startPicker.setDate(c.getTime());

        //End.
        this.endPicker = new JDateChooser();
        this.endPicker.setBounds(550, 20, 200, 34);
        this.endPicker.setDateFormatString("dd/MM/yyyy");
        c.set(Calendar.DATE, Calendar.getInstance().getActualMaximum(Calendar.DATE));
        this.endPicker.setDate(c.getTime());

        //Add Item.
        this.add(lblStartD);
        this.add(lblEndD);
        this.add(startPicker);
        this.add(endPicker);
        this.add(cbList);
        this.add(btnFirst);
        this.add(btnPrevious);
        this.add(btnPage);
        this.add(btnNext);
        this.add(btnLast);
        this.add(this.lblFullPrice);

        //Table.
        String[] columnNames1 = {"ID", "Giá Trước Giảm", "Giá Sau Giảm", "Giảm Giá", "Bàn Thanh Toán", "Ngày Tạo"};
        Object[][] data1 = {};
        this.modelStatistic = new DefaultTableModel(data1, columnNames1);
        this.table = new JTable(modelStatistic);
        //chinh with column
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(50);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(142);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        this.add(scrollPane);
        this.scrollPane.setVisible(true);
        this.setLayout(null);
        this.setVisible(true);
        loadStatisticOrder();

        this.endPicker.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    endPicker.setDate((java.util.Date) e.getNewValue());
                    loadStatisticOrder();
                }
            }
        });
         this.startPicker.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if ("date".equals(e.getPropertyName())) {
                    startPicker.setDate((java.util.Date) e.getNewValue());
                    loadStatisticOrder();
                }
            }
        });


        this.btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page += 1;
                loadStatisticOrder();
            }
        });

        this.btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = totalPage;
                loadStatisticOrder();
            }
        });

        this.btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page -= 1;
                loadStatisticOrder();
            }
        });

        this.btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = 1;
                loadStatisticOrder();
            }
        });

    }

    // Lấy dữ liệu hiển thị ra bảng.
    public static void loadStatisticOrder() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        TablesModel tableModel = new TablesModel();
        Date day1 = new Date(startPicker.getDate().getTime());
        Date day2 = new Date(endPicker.getDate().getTime());
        ArrayList<Order> listOrder = statisticModel.getListOrder(page, limit, day1, day2);
        ArrayList<Order> listOrder1 = statisticModel.getListOrder(day1, day2);
        for (Order order : listOrder1) {
            fullPrice += order.getRealPrice();
        }
        listOrder.forEach((order) -> {
            model.addRow(new Object[]{order.getId(),  order.getTotalPrice(),order.getRealPrice(), order.getDiscount() + "%", order.getTableName(), order.getCreatedAt()});
        });
        count = statisticModel.countActive();
        totalPage = count / limit + (count % limit > 0 ? 1 : 0);
        btnPage.setText(String.valueOf(page));
        handlePaginateButton();
        ManagerStatistic.lblFullPrice.setText("tổng giá : " + fullPrice);
        fullPrice = 0;
    }

    // Xử lý việc hiển thị các nút phân trang.
    private static void handlePaginateButton() {
        if (page <= 1) {
            btnFirst.setEnabled(false);
            btnPrevious.setEnabled(false);
        } else {
            btnFirst.setEnabled(true);
            btnPrevious.setEnabled(true);
        }
        if (page == totalPage) {
            btnNext.setEnabled(false);
            btnLast.setEnabled(false);
        } else {
            btnNext.setEnabled(true);
            btnLast.setEnabled(true);
        }
    }
}
