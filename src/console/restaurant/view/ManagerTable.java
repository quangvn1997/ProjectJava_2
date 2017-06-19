/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.AdminsController;
import console.restaurant.controller.FoodsController;
import console.restaurant.controller.TablesController;
import console.restaurant.entities.SessionAdmin;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import console.restaurant.models.TablesModel;
import console.restaurant.entities.Table;
import console.restaurant.models.AdminsModel;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ManagerTable extends JPanel {

    private JLabel lblSearch;
    private JTextField txtSearch;

    private JButton btnSearch;
    private JButton btnCreate;

    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnPage;
    private JButton btnNext;
    private JButton btnLast;
    private int page = 1;
    private JTable table;
    private DefaultTableModel modelTable;
    private JScrollPane scrollPane;
    private TablesController tableController = new TablesController();

    public ManagerTable() {

        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lblSearch = new JLabel("Nhập trạng thái");
        this.txtSearch = new JTextField();
        this.btnSearch = new JButton("Tìm");
        this.btnCreate = new JButton("Tạo mới");

        this.lblSearch.setBounds(20, 20, 100, 34);
        this.txtSearch.setBounds(130, 20, 200, 34);
        this.btnSearch.setBounds(350, 20, 100, 34);
        this.btnCreate.setBounds(880, 20, 100, 34);

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

        this.add(this.lblSearch);
        this.add(this.txtSearch);
        this.add(this.btnSearch);
        this.add(this.btnCreate);
        this.add(this.btnFirst);
        this.add(this.btnPrevious);
        this.add(this.btnPage);
        this.add(this.btnNext);
        this.add(this.btnLast);

        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableForm tableForm = new TableForm();
                tableForm.setVisible(true);
                tableForm.Create();
                tableController.loadTables(table);
            }
        });

//        
//        //delete action
//        this.btnnewTb.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int a = Integer.parseInt(txtAddTable.getText());
//
//                TableModel tablemodel = new TableModel();
//
//                int id_max = tablemodel.getTableMax();
//
//                Table[] tables = new Table[a];
//
//                for (int i = 0; i < a; i++) {
//                    tables[i] = new Table();
//                    tables[i].setName("Bàn " + (i + 1 + id_max));
//                    tables[i].setId(i + 1 + id_max);
//                }
//
//                tablemodel.insertTable(tables);
//
//            }
//        });
        // Table        
        String[] columnNames = {"ID", "Tên bàn", "Trạng thái", "Ngày tạo", "Ngày cập nhật"};
        Object[][] data = {};
        this.modelTable = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelTable);
//        this.table.setFont(new Font("Serif", Font.PLAIN, 20));
        //chinh mau title column
//        this.table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(220);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(220);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(200);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableController.loadTables(table);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        //them su kien
        //table action
        javax.swing.table.TableModel tblModel = table.getModel();

//        this.table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 1) {
//
//                    JTable target = (JTable) e.getSource();
//                    int row = target.getSelectedRow();
//                    if (row != -1) {
//                        TableForm tableForm = new TableForm();
//                        tableForm.setVisible(true);
//
//                        javax.swing.table.TableModel tblModel = table.getModel();
//                        String name = tblModel.getValueAt(row, 1).toString();
////                     thêm vào textField
//                        tableForm.txtName.setText(name);
//
//                    }
//                }
//            }
//        });
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);

    }

}
