/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.models.AdminsModel;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import console.restaurant.models.TableModel;
import console.restaurant.entities.Table;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class QuanLyBan extends JPanel {
    
    private JLabel title;
    private JButton taomoiAdmin;
    private JButton suaAdmin;
    private JButton xoaAdmin;
    private JButton btnSearch;
    private JTextField txtSearch;
    private JLabel lblAcount;
    private JLabel lblPassword;
    private JTextField txtAcount;
    private JPasswordField txtPassword;
    private JTable table;
    private DefaultTableModel model1aTable;
    private JScrollPane scrollPane;
    private JButton btnReset;
    
    ;

    public QuanLyBan() {
        
        this.setBackground(new Color(250, 250, 250));
        this.setBounds(300, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        this.title = new JLabel();
        this.title.setText("QUẢN LÝ Bàn");
        this.title.setBounds(360, 0, 280, 70);
        this.title.setFont(new Font("Serif", Font.PLAIN, 30));
        // button Search
        this.btnSearch = new JButton("Search");
        this.btnSearch.setBounds(560, 60, 100, 34);
        this.btnSearch.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtSearch = new JTextField();
        this.txtSearch.setBounds(50, 60, 500, 34);
        this.txtSearch.setFont(new Font("Serif", Font.PLAIN, 18));
        //acount and password
        this.lblAcount = new JLabel("Số bàn thêm mới");
        this.lblAcount.setBounds(50, 400, 100, 34);
        this.lblPassword = new JLabel("Trạng thái");
        this.lblPassword.setBounds(50, 450, 100, 34);
        this.txtAcount = new JTextField();
        this.txtAcount.setBounds(160, 400, 200, 34);
        this.txtPassword = new JPasswordField();
        this.txtPassword.setBounds(160, 450, 200, 34);
        this.lblAcount.setFont(new Font("Serif", Font.PLAIN, 18));
        this.lblPassword.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtAcount.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtPassword.setFont(new Font("Serif", Font.PLAIN, 18));

        // Button tạo mới admin
        this.taomoiAdmin = new JButton();
        this.taomoiAdmin.setText("Thêm");
        this.taomoiAdmin.setBounds(380, 400, 120, 34);
        this.taomoiAdmin.setFont(new Font("Serif", Font.PLAIN, 18));

        //delete action
        this.taomoiAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(txtAcount.getText());
                
                TableModel tablemodel = new TableModel();
                
                int id_max = tablemodel.getTableMax();
                                
                Table[] tables = new Table[a];
                
                for (int i = 0; i < a; i++) {
                    tables[i] = new Table();
                    tables[i].setName("Bàn "+(i+1+id_max));
                    tables[i].setId(i+1+id_max);
                }
                
                tablemodel.insertTable(tables);
                
            }
        });

        // Button sửa admin
        this.suaAdmin = new JButton();
        this.suaAdmin.setText("Cập nhật");
        this.suaAdmin.setBounds(380, 450, 120, 34);
        this.suaAdmin.setFont(new Font("Serif", Font.PLAIN, 18));
        // Button xóa admin
        this.xoaAdmin = new JButton();
        this.xoaAdmin.setText("Xóa");
        this.xoaAdmin.setBounds(700, 60, 120, 34);
        this.xoaAdmin.setFont(new Font("Serif", Font.PLAIN, 18));
        // Table        
        String[] columnNames = {"ID", "tên bàn", "trạng thái"};
        Object[][] data = {{"1", "bàn 1", "1"}, {"2", "bàn số 2", "0"}, {"3", "bàn 3", "2"}};
        this.model1aTable = new DefaultTableModel(data, columnNames);
        this.table = new JTable(model1aTable);
        this.table.setFont(new Font("Serif", Font.PLAIN, 20));
        //chinh mau title column
        this.table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(264);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(264);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(50, 100, 900, 280);
        //set title table
        this.scrollPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "danh sách bàn", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Serif", Font.PLAIN, 20)));
        //them su kien
        //table action
        this.add(this.xoaAdmin);
        this.add(this.suaAdmin);
        this.add(this.taomoiAdmin);
        this.add(this.lblAcount);
        this.add(this.lblPassword);
        this.add(this.txtAcount);
        this.add(this.txtPassword);
        this.add(this.title);
        this.add(this.btnSearch);
        this.add(this.txtSearch);
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);
        
    }
    
}
