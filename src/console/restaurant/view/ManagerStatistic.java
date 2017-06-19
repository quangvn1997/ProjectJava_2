/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import static console.restaurant.view.ManagerFood.table;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ManagerStatistic extends JPanel {

    private JLabel lblStartD;
    private JLabel lblEndD;

    private JXDatePicker startPicker;
    private JXDatePicker endPicker;
    
    private JButton btnSubmit;
    
    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnLast;
    private JButton btnPage;
    
    public static JTable table;
    private DefaultTableModel modelFood;
    private JScrollPane scrollPane;

    private int page = 1;
    
    public ManagerStatistic() {
        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        //Title.
        this.lblStartD = new JLabel("Chọn Ngày Bắt Đầu:");
        this.lblStartD.setBounds(20, 20, 140, 34);

        this.lblEndD = new JLabel("Chọn Ngày Cuối Cùng:");
        this.lblEndD.setBounds(400, 20, 140, 34);
        //Button.
        this.btnSubmit = new JButton("Hiển thị");
        this.btnSubmit.setBounds(820, 20, 100, 34);
        
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
        //Start.
        this.startPicker = new JXDatePicker();
        this.startPicker.setBounds(160, 20, 200, 34);
        this.startPicker.setDate(Calendar.getInstance().getTime());
        this.startPicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        //End.
        this.endPicker = new JXDatePicker();
        this.endPicker.setBounds(550, 20, 200, 34);
        this.endPicker.setDate(Calendar.getInstance().getTime());
        this.endPicker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        
        //Add Item.
        this.add(lblStartD);
        this.add(lblEndD);
        this.add(startPicker);
        this.add(endPicker);
        this.add(btnSubmit);
        this.add(btnFirst);
        this.add(btnPrevious);
        this.add(btnPage);
        this.add(btnNext);
        this.add(btnLast);
        
        //Table.
        String[] columnNames = {"ID", "Tên", "category_id", "Miêu tả", "Ảnh", "Giá", "Ngày tạo", "Ngày cập nhật"};
        Object[][] data = {};
        this.modelFood = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelFood);
        // this.table.setFont(new Font("Serif", Font.PLAIN, 20));      
        // this.table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        //chinh with column
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(42);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(7).setPreferredWidth(150);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        
        this.add(scrollPane);
        
        this.setLayout(null);
        this.setVisible(false);
    }

}
