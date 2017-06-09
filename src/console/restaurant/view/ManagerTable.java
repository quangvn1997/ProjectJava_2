/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.SessionAdmin;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ManagerTable extends JPanel {

    private JLabel title;
    private JButton btnnewTb;
    private JButton btnupdateTb;
    private JButton btndeleteTb;
    private JButton btnSearch;
    private JTextField txtSearch;
    private JLabel lblAddTable;
    private JLabel lblStatus;
    private JTextField txtAddTable;
    private JTextField txtStatus;
    private JTable table;
    private DefaultTableModel model1aTable;
    private JScrollPane scrollPane;

    ;

    public ManagerTable() {

        this.setBackground(new Color(250, 250, 250));
        this.setBounds(300, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.title = new JLabel();
        this.title.setText("QUẢN LÝ BÀN");
        this.title.setBounds(360, 0, 280, 70);
        this.title.setFont(new Font("Serif", Font.PLAIN, 30));
        // button tìm kiếm
        this.btnSearch = new JButton("Search");
        this.btnSearch.setBounds(560, 60, 100, 34);
        this.btnSearch.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtSearch = new JTextField();
        this.txtSearch.setBounds(50, 60, 500, 34);
        this.txtSearch.setFont(new Font("Serif", Font.PLAIN, 18));
        //Bàn và trạng thái
        this.lblAddTable = new JLabel("Số bàn thêm mới");
        this.lblAddTable.setBounds(50, 400, 200, 34);
        this.lblStatus = new JLabel("Trạng thái");
        this.lblStatus.setBounds(50, 450, 100, 34);
        this.txtAddTable = new JTextField();
        this.txtAddTable.setBounds(190, 400, 200, 34);
        this.txtStatus = new JTextField();
        this.txtStatus.setBounds(190, 450, 200, 34);
        this.lblAddTable.setFont(new Font("Serif", Font.PLAIN, 18));
        this.lblStatus.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtAddTable.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtStatus.setFont(new Font("Serif", Font.PLAIN, 18));

        // Button tạo mới bàn
        this.btnnewTb = new JButton();
        this.btnnewTb.setText("Thêm");
        this.btnnewTb.setBounds(410, 400, 120, 34);
        this.btnnewTb.setFont(new Font("Serif", Font.PLAIN, 18));

//       Bắt sự kiện tạo mới bàn
        this.btnnewTb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = Integer.parseInt(txtAddTable.getText());

                TableModel tablemodel = new TableModel();

                int id_max = tablemodel.getTableMax();

                Table[] tables = new Table[a];

                for (int i = 0; i < a; i++) {
                    tables[i] = new Table();
                    tables[i].setName("Bàn " + (i + 1 + id_max));
                    tables[i].setId(i + 1 + id_max);
                }

                tablemodel.insertTable(tables);

            }
        });

        // Button sửa bàn
        this.btnupdateTb = new JButton();
        this.btnupdateTb.setText("Cập nhật");
        this.btnupdateTb.setBounds(410, 450, 120, 34);
        this.btnupdateTb.setFont(new Font("Serif", Font.PLAIN, 18));
        // Button xóa bàn
        this.btndeleteTb = new JButton();
        this.btndeleteTb.setText("Xóa");
        this.btndeleteTb.setBounds(680, 60, 120, 34);
        this.btndeleteTb.setFont(new Font("Serif", Font.PLAIN, 18));
        // Table        
        String[] columnNames = {"ID", "tên bàn", "trạng thái"};
        Object[][] data = {{"1", "b1", "1"}, {"2", "b2", "0"}, {"3", "b3", "2"}};
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
        //them su kien
        //table action
        javax.swing.table.TableModel tblModel = table.getModel();
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        SessionAdmin.setStrToAction(txtAddTable.getText());
                        javax.swing.table.TableModel tblModel = table.getModel();
                        String checkName = tblModel.getValueAt(row, 1).toString();
                        String pass = tblModel.getValueAt(row, 2).toString();
                        // thêm vào textField
                        txtAddTable.setText(checkName);
                        txtStatus.setText(pass);
                    }
                }
            }
        });
        this.add(this.btndeleteTb);
        this.add(this.btnupdateTb);
        this.add(this.btnnewTb);
        this.add(this.lblAddTable);
        this.add(this.lblStatus);
        this.add(this.txtAddTable);
        this.add(this.txtStatus);
        this.add(this.title);
        this.add(this.btnSearch);
        this.add(this.txtSearch);
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);

    }

}
