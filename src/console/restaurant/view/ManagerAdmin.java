/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.AdminsController;
import console.restaurant.entities.Admin;
import console.restaurant.entities.SessionAdmin;
import console.restaurant.models.AdminsModel;
import console.restaurant.utilities.ValidateUtilities;
import static console.restaurant.view.ManagerFood.table;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class ManagerAdmin extends JPanel {

//    private JLabel title;
//    private JButton taomoiAdmin;
//    private JButton suaAdmin;
//    private JButton xoaAdmin;
//    private JButton btnReset;
//    private JLabel lblAcount;
//    private JLabel lblPassword;
//    private JLabel lblName;
//    private JTextField txtAcount;
//    private JPasswordField txtPassword;
//    private JTextField txtName;
    private JLabel lblSearch;
    private JButton btnSearch;
    private JTextField txtSearch;

    public static JTable table;
    private JButton btnCreate;
    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnPage;
    private JButton btnNext;
    private JButton btnLast;
    private int page = 1;

    private DefaultTableModel modelAdmin;
    private JScrollPane scrollPane;

    private AdminForm adminForm;

    private AdminsController adminController = new AdminsController();

    public ManagerAdmin() {

        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lblSearch = new JLabel("Nhập tài khoản");
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
        adminController.loadAdmins(table);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        //them su kien

        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adminForm = new AdminForm();
                adminForm.setVisible(true);
//                adminForm.Create();
//                adminController.loadAdmins(table);
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
}
