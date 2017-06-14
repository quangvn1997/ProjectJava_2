/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

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
import console.restaurant.models.TableModel;
import console.restaurant.entities.Table;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ManagerTable extends JPanel {

//    private JLabel title;
//    private JButton btnnewTb;
//    private JButton btnupdateTb;
//    private JButton btndeleteTb;
//    private JButton btnSearch;
//    private JTextField txtSearch;
//    private JLabel lblAddTable;
//    private JLabel lblStatus;
//    private JTextField txtAddTable;
//    private JComboBox txtStatus;
//    private JLabel lblNameTable;
//    private JTextField txtNameTable;
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

    ;

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

        this.add(this.lblSearch);
        this.add(this.txtSearch);
        this.add(this.btnSearch);
        this.add(this.btnCreate);

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

        this.add(this.btnFirst);
        this.add(this.btnPrevious);
        this.add(this.btnPage);
        this.add(this.btnNext);
        this.add(this.btnLast);

        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableForm table = new TableForm();
                table.setVisible(true);
            }
        });

//        this.title = new JLabel();
//        this.title.setText("QUẢN LÝ Bàn");
//        this.title.setBounds(360, 0, 280, 70);
//        this.title.setFont(new Font("Serif", Font.PLAIN, 30));
//        // button tìm kiếm
//        this.btnSearch = new JButton("Search");
//        this.btnSearch.setBounds(560, 60, 100, 34);
//        this.btnSearch.setFont(new Font("Serif", Font.PLAIN, 18));
//        this.txtSearch = new JTextField();
//        this.txtSearch.setBounds(50, 60, 500, 34);
//        this.txtSearch.setFont(new Font("Serif", Font.PLAIN, 18));
//        //Bàn và trạng thái
//        this.lblAddTable = new JLabel("Số bàn");
//        this.lblAddTable.setBounds(50, 350, 100, 34);
//        this.txtAddTable = new JTextField();
//        this.txtAddTable.setBounds(160, 350, 200, 34);
//
//        this.lblNameTable = new JLabel("Tên bàn");
//        this.lblNameTable.setBounds(50, 400, 100, 34);
//        this.txtNameTable = new JTextField();
//        this.txtNameTable.setBounds(160, 400, 200, 34);
//        this.lblStatus = new JLabel("Trạng thái");
//        this.lblStatus.setBounds(50, 450, 100, 34);
//
//        String[] languages = new String[]{"Sẵn sàng", "Đã hỏng"};
//        this.txtStatus = new JComboBox<String>(languages);
//        this.txtStatus.setBounds(160, 450, 200, 34);
//        this.lblNameTable.setFont(new Font("Serif", Font.PLAIN, 18));
//        this.txtNameTable.setFont(new Font("Serif", Font.PLAIN, 18));
//        this.lblAddTable.setFont(new Font("Serif", Font.PLAIN, 18));
//        this.lblStatus.setFont(new Font("Serif", Font.PLAIN, 18));
//        this.txtAddTable.setFont(new Font("Serif", Font.PLAIN, 18));
//        this.txtStatus.setFont(new Font("Serif", Font.PLAIN, 18));
//        // Button tạo mới bàn
//        this.btnnewTb = new JButton();
//        this.btnnewTb.setText("Thêm");
//        this.btnnewTb.setBounds(380, 400, 120, 34);
//        this.btnnewTb.setFont(new Font("Serif", Font.PLAIN, 18));
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
//        // Button sửa bàn
//        this.btnupdateTb = new JButton();
//        this.btnupdateTb.setText("Cập nhật");
//        this.btnupdateTb.setBounds(380, 450, 120, 34);
//        this.btnupdateTb.setFont(new Font("Serif", Font.PLAIN, 18));
//        // Button xóa bàn
//        this.btndeleteTb = new JButton();
//        this.btndeleteTb.setText("Xóa");
//        this.btndeleteTb.setBounds(700, 60, 120, 34);
//        this.btndeleteTb.setFont(new Font("Serif", Font.PLAIN, 18));
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
        TablesController.loadTables(table);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        //them su kien
        //table action
        javax.swing.table.TableModel tblModel = table.getModel();

        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {

                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        TableForm tableForm = new TableForm();
                        tableForm.setVisible(true);

                        javax.swing.table.TableModel tblModel = table.getModel();
                        String name = tblModel.getValueAt(row, 1).toString();
//                     thêm vào textField
                        tableForm.txtName.setText(name);

                    }
                }
            }
        });

//        this.add(this.txtStatus);
//        this.add(this.btndeleteTb);
//        this.add(this.btnupdateTb);
//        this.add(this.btnnewTb);
//        this.add(this.lblAddTable);
//        this.add(this.lblStatus);
//        this.add(this.txtAddTable);
//        this.add(this.txtStatus);
//        this.add(this.lblNameTable);
//        this.add(this.txtNameTable);
//        this.add(this.title);
//        this.add(this.btnSearch);
//        this.add(this.txtSearch);
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);

    }

}
