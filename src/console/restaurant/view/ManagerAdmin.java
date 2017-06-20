/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.AdminsController;
import console.restaurant.entities.Admin;
import console.restaurant.entities.Food;
import console.restaurant.entities.SessionAdmin;
import console.restaurant.models.AdminsModel;
import console.restaurant.utilities.ValidateUtilities;
import static console.restaurant.view.ManagerFood.limit;
import static console.restaurant.view.ManagerFood.page;
import static console.restaurant.view.ManagerFood.table;
import static console.restaurant.view.ManagerFood.totalPage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class ManagerAdmin extends JPanel {

    private JLabel lblSearch;
    private JTextField txtSearch;

    public static JTable table;
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

    private DefaultTableModel modelAdmin;
    private JScrollPane scrollPane;

    private AdminForm adminForm;

    private AdminsController adminController = new AdminsController();
    private static AdminsModel adminModel = new AdminsModel();

    public ManagerAdmin() {

        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lblSearch = new JLabel("Nhập tài khoản");
        this.txtSearch = new JTextField();
        this.btnCreate = new JButton("Tạo mới");

        this.lblSearch.setBounds(20, 20, 100, 34);
        this.txtSearch.setBounds(130, 20, 200, 34);
        this.btnCreate.setBounds(880, 20, 100, 34);

        this.add(this.lblSearch);
        this.add(this.txtSearch);
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
//
//        ImageIcon img = new ImageIcon("....\\src\\console\\restaurant\\Image\\add.jpg");
//        btnCreate.setIcon(img);

        this.add(this.btnFirst);
        this.add(this.btnPrevious);
        this.add(this.btnPage);
        this.add(this.btnNext);
        this.add(this.btnLast);

        // Table        
        String[] columnNames = {"ID", "Họ và tên", "Tài khoản", "Mật khẩu", "Ngày tạo", "Ngày cập nhật"};
        Object[][] data = {};
        this.modelAdmin = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelAdmin);
//        this.table.setFont(new Font("Serif", Font.PLAIN, 20));
        //chinh mau title column
//        this.table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(142);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(170);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(170);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        loadAdmin();
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        //them su kien

        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminForm = new AdminForm();
                adminForm.setVisible(true);
            }
        });
        this.btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page += 1;
                loadAdmin();
            }
        });

        this.btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = totalPage;
                loadAdmin();
            }
        });

        this.btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page -= 1;
                loadAdmin();
            }
        });

        this.btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = 1;
                loadAdmin();
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
                ArrayList<Admin> listAdmin = new ArrayList<>();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                if (txtSearch != null && txtSearch.getText().length() > 0) {
                    listAdmin = adminModel.searchAdmin(txtSearch.getText());
                } else {
                    listAdmin = adminModel.getListAdmin(page, limit);
                }
                listAdmin.forEach((food) -> {
                    model.addRow(new Object[]{String.valueOf(food.getId()), food.getName(), food.getUsername(), food.getPassword(), food.getCreatedAt(), food.getUpdateAt()});
                });
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
                        AdminForm adminForm = new AdminForm(2, id);
                        adminForm.setVisible(true);
                    }
                }
            }
        });

//        adminController.loadAdmins(table);
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);

    }

    public static void loadAdmin() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ArrayList<Admin> listAdmin = adminModel.getListAdmin(page, limit);
        listAdmin.forEach((admin) -> {
            model.addRow(new Object[]{String.valueOf(admin.getId()), admin.getName(), admin.getUsername(), admin.getPassword(), admin.getCreatedAt(), admin.getUpdateAt()});
        });
        count = adminModel.countActive();
        totalPage = count / limit + (count % limit > 0 ? 1 : 0);
        btnPage.setText(String.valueOf(page));
        handlePaginateButton();
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
