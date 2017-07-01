/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.TablesController;
import console.restaurant.entities.Table;
import console.restaurant.models.TablesModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ManagerTable extends JPanel {

    private JLabel lblSearch;
    private JTextField txtSearch;

    private JButton btnCreate;

    private static JButton btnFirst;
    private static JButton btnPrevious;
    private static JButton btnPage;
    private static JButton btnNext;
    private static JButton btnLast;

    public static int page = 1;
    public static int limit = 14;
    public static int count = 0;
    public static int totalPage = 0;

    public static JTable table;
    private DefaultTableModel modelTable;
    private JScrollPane scrollPane;
    private TablesController tableController = new TablesController();

    private static TablesModel tableModel = new TablesModel();

    public ManagerTable() {

        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lblSearch = new JLabel("Nhập tên bàn");
        this.txtSearch = new JTextField();
        this.btnCreate = new JButton("Tạo mới");

        this.lblSearch.setBounds(20, 20, 100, 34);
        this.txtSearch.setBounds(130, 20, 200, 34);
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
        this.add(this.btnCreate);
        this.add(this.btnFirst);
        this.add(this.btnPrevious);
        this.add(this.btnPage);
        this.add(this.btnNext);
        this.add(this.btnLast);

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
        loadTable();
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);

        javax.swing.table.TableModel tblModel = table.getModel();

        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TableForm tableForm = new TableForm();
                tableForm.setVisible(true);
            }
        });
        this.btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page += 1;
                loadTable();
            }
        });

        this.btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = totalPage;
                loadTable();
            }
        });

        this.btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page -= 1;
                loadTable();
            }
        });

        this.btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = 1;
                loadTable();
            }
        });
        this.table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        TableModel tblModel = table.getModel();
                        int id = Integer.parseInt(tblModel.getValueAt(row, 0).toString());
                        TableForm tableForm = new TableForm(2, id);
                        tableForm.setVisible(true);
                        String name = tblModel.getValueAt(row, 1).toString();
//                     thêm vào textFieldaadmin
                        tableForm.txtName.setText(name);
                        tableForm.cmbStatus.setSelectedItem(tblModel.getValueAt(row, 2).toString());
                    }
                }
            }
        });

        this.txtSearch.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) {
                process();
            }

            public void removeUpdate(DocumentEvent e) {
                process();
            }

            public void insertUpdate(DocumentEvent e) {
                process();
            }

            public void process() {
                page = 1;
                ArrayList<Table> listTable = new ArrayList<>();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                if (txtSearch != null && txtSearch.getText().length() > 0) {
                    listTable = tableModel.searchAdmin(txtSearch.getText());
                } else {
                    listTable = tableModel.getListTable(page, limit);
                }
                for (Table table :
                        listTable) {
                    model.addRow(new Object[]{String.valueOf(table.getId()), table.getName(), table.getStatus() == 0 ? " Đã hỏng " : "  Sẵn sàng ", table.getCreatedAt(), table.getUpdateAt()});
                }
            }
        });
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);
    }

    public static void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ArrayList<Table> listAdmin = tableModel.getListTable(page, limit);
        for (Table tableload :
                listAdmin) {
            model.addRow(new Object[]{String.valueOf(tableload.getId()), tableload.getName(), tableload.getStatus() == 0 ? " Đã hỏng " : "  Sẵn sàng ", tableload.getCreatedAt(), tableload.getUpdateAt()});
        }
        count = tableModel.countActive();
        totalPage = count / limit + (count % limit > 0 ? 1 : 0);
        btnPage.setText(String.valueOf(page));
        handlePaginateButton();
    }

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
