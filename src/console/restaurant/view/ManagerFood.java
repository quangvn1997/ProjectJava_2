/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.FoodsController;
import console.restaurant.entities.Admin;
import console.restaurant.entities.Food;
import console.restaurant.entities.SessionAdmin;
import console.restaurant.models.AdminsModel;
import console.restaurant.models.FoodsModel;
import console.restaurant.utilities.ValidateUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import static javax.print.attribute.Size2DSyntax.MM;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class ManagerFood extends JPanel {

    private JLabel lbltitle;
    private JButton btncreateFood;
    private JButton btnfixFood;
    private JButton btndeleteFood;
    private JButton btnSearch;
    private JButton btnNhaplai;
    private JTextField txtSearch;
    private JLabel lblName;
    private JLabel lblValue;
    private JTextField txtName;
    private JTextField txtValue;
    private JLabel lblDescription;
    private JTextArea txtDescription;
    private JLabel lblLinkImage;
    private JTextField txtLinkImage;
    private JTable table;
    private DefaultTableModel model1aTable;
    private JScrollPane scrollPane;

    public ManagerFood() {
        this.setBackground(new Color(250, 250, 250));
        this.setBounds(300, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lbltitle = new JLabel();
        this.lbltitle.setText("QUẢN LÝ MÓN ĂN");
        this.lbltitle.setBounds(360, 0, 280, 70);
        this.lbltitle.setFont(new Font("Serif", Font.PLAIN, 30));
        // button Search
        this.btnSearch = new JButton("Search");
        this.btnSearch.setBounds(560, 60, 100, 34);
        this.btnSearch.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtSearch = new JTextField();
        this.txtSearch.setBounds(50, 60, 500, 34);
        this.txtSearch.setFont(new Font("Serif", Font.PLAIN, 18));
        //acount and password
        this.lblName = new JLabel("Tên dịch vụ :");
        this.lblName.setBounds(50, 380, 150, 34);
        this.lblValue = new JLabel("Giá dịch vụ : ");
        this.lblValue.setBounds(50, 430, 150, 34);
        this.txtName = new JTextField();
        this.txtName.setBounds(160, 380, 200, 34);
        this.txtValue = new JTextField();
        this.txtValue.setBounds(160, 430, 200, 34);

        this.lblLinkImage = new JLabel();
        this.lblLinkImage.setText("Link ảnh : ");
        this.lblLinkImage.setBounds(50, 480, 100, 34);
        this.lblLinkImage.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtLinkImage = new JTextField();
        this.txtLinkImage.setBounds(160, 480, 200, 34);
        this.txtLinkImage.setFont(new Font("Serif", Font.PLAIN, 18));

        this.lblDescription = new JLabel();
        this.lblDescription.setText("Miêu tả :");
        this.lblDescription.setBounds(400, 380, 100, 35);
        this.lblDescription.setFont(new Font("Serif", Font.PLAIN, 18));

        this.txtDescription = new JTextArea();
        this.txtDescription.setBounds(500, 380, 200, 70);
        this.txtDescription.setFont(new Font("Serif", Font.PLAIN, 18));

        this.lblName.setFont(new Font("Serif", Font.PLAIN, 18));
        this.lblValue.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtName.setFont(new Font("Serif", Font.PLAIN, 18));
        this.txtValue.setFont(new Font("Serif", Font.PLAIN, 18));

        // Button tạo mới food
        this.btncreateFood = new JButton();
        this.btncreateFood.setText("Tạo mới");
        this.btncreateFood.setBounds(410, 470, 120, 34);
        this.btncreateFood.setFont(new Font("Serif", Font.PLAIN, 18));
        // Button sửa food
        this.btnfixFood = new JButton();
        this.btnfixFood.setText("Cập nhật");
        this.btnfixFood.setBounds(560, 470, 120, 34);
        this.btnfixFood.setFont(new Font("Serif", Font.PLAIN, 18));
        // Button nhập lại
        this.btnNhaplai = new JButton("Nhập lại");
        this.btnNhaplai.setBounds(680, 60, 100, 34);
        this.btnNhaplai.setFont(new Font("Serif", Font.PLAIN, 18));
        // Button xóa food
        this.btndeleteFood = new JButton();
        this.btndeleteFood.setText("Xóa");
        this.btndeleteFood.setBounds(700, 470, 120, 34);
        this.btndeleteFood.setFont(new Font("Serif", Font.PLAIN, 18));
        // Table        
        String[] columnNames = {"ID", "tên món", "giá", "Link ảnh", "miêu tả", "Ngày tạo", "Ngày cập nhật"};
        Object[][] data = {};
        this.model1aTable = new DefaultTableModel(data, columnNames);
        this.table = new JTable(model1aTable);
        this.table.setFont(new Font("Serif", Font.PLAIN, 20));
        //chinh mau title column
        this.table.getTableHeader().setFont(new Font("Serif", Font.BOLD, 20));
        //chinh with column
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.getColumnModel().getColumn(0).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(1).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(2).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(3).setPreferredWidth(100);
        this.table.getColumnModel().getColumn(4).setPreferredWidth(200);
        this.table.getColumnModel().getColumn(5).setPreferredWidth(150);
        this.table.getColumnModel().getColumn(6).setPreferredWidth(150);
        this.table.setRowHeight(24);
        this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(50, 100, 900, 260);
        //them su kien
        //table action
        TableModel tblModel = table.getModel();
        this.table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        SessionAdmin.setStrToAction(txtName.getText());
                        TableModel tblModel = table.getModel();
                        String checkName = tblModel.getValueAt(row, 1).toString();
                        String value = tblModel.getValueAt(row, 2).toString();
                        String img = tblModel.getValueAt(row, 3).toString();
                        String descr = tblModel.getValueAt(row, 4).toString();
                        // thêm vào textField
                        txtName.setText(checkName);
                        txtValue.setText(value);
                        txtLinkImage.setText(img);
                        txtDescription.setText(descr);
                    }
                }
            }
        });
        //delete action
        this.btndeleteFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    TableModel tblModel = table.getModel();
                    Object[] options = {"Yes", "No"};
                    Component form = null;
                    int n = JOptionPane.showOptionDialog(form, "Do you like to delete the record for ID:  " + tblModel.getValueAt(row, 1).toString() + " ?", "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
                    if (n == JOptionPane.YES_OPTION) {
                        FoodsModel.deleteFood(tblModel.getValueAt(row, 0).toString());
                        JOptionPane.showMessageDialog(null, "The record has been deleted successfully.");
                        txtName.setText("");
                        txtValue.setText("");
                        txtLinkImage.setText("");
                        txtDescription.setText("");
                        FoodsController.loadFood(table);
                    }
                }
            }
        });
        //insert action
        this.btncreateFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền tên dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (new String(txtValue.getText()).isEmpty() && ValidateUtilities.validateNumberUpdate(new String(txtValue.getText()))) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền giá dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (txtLinkImage.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền link ảnh dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (txtDescription.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền miêu tả dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtName.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên dịch vụ đã tồn tại !, Vui lòng chọn tên khác.", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtLinkImage.getText())) {
                    JOptionPane.showMessageDialog(null, "Link ảnh đã được sử dụng !, Vui lòng chọn ảnh khác.", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                Food food = new Food();
                food.setName(txtName.getText());
                food.setUnitPrice(Float.valueOf(txtValue.getText()));
                food.setImgUrl(txtLinkImage.getText());
                food.setDescription(txtDescription.getText());
                FoodsModel.insertFood(food);
                FoodsController.loadFood(table);
                txtName.setText("");
                txtValue.setText("");
                txtLinkImage.setText("");
                txtDescription.setText("");
            }
        });
        this.btnfixFood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Food food = new Food();
                int row = table.getSelectedRow();
                if (row != -1) {
                    String checkid = tblModel.getValueAt(row, 0).toString();
                    food.setId(Integer.valueOf(checkid));
                }
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "vui lòng điền.");
                    return;
                }
                if (new String(txtValue.getText()).isEmpty()) {
                    JOptionPane.showMessageDialog(null, "vui lòng điền số.");
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtName.getText()) && !txtName.getText().equals(tblModel.getValueAt(row, 1).toString())) {
                    JOptionPane.showMessageDialog(null, "tên đã tồn tại, vui lòng điền tên khác");
                    return;
                }
                food.setName(txtName.getText());
                food.setUnitPrice(Float.valueOf(txtValue.getText()));
                txtLinkImage.getText();
                food.setImgUrl(txtLinkImage.getText());
                food.setDescription(txtDescription.getText());
                FoodsModel.update(food);
                FoodsController.loadFood(table);

            }
        });
        this.btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtSearch.getText().trim().matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$") || txtSearch.getText().trim().matches("[a-zA-Z]+")) {
                    FoodsModel.loadFoodsSearch(table, FoodsController.searchFood(txtSearch.getText(), 2));
                } else if (txtSearch.getText().matches("^-?\\d+$")) {
                    FoodsModel.loadFoodsSearch(table, FoodsController.searchFood(txtSearch.getText(), 1));
                } else if (txtSearch.getText().isEmpty()) {
                    FoodsController.loadFood(table);
                    JOptionPane.showMessageDialog(null, " vui lòng điền id hoac name");
                } else {
                    JOptionPane.showMessageDialog(null, "không tồn tại");
                }

            }
        });
        this.btnNhaplai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                txtSearch.setText("");
                txtName.setText("");
                txtValue.setText("");
                txtLinkImage.setText("");
                txtDescription.setText("");
                FoodsController.loadFood(table);
            }
        });
        FoodsController.loadFood(table);
        //add element
        this.add(this.btnfixFood);
        this.add(this.btndeleteFood);
        this.add(this.btncreateFood);
        this.add(this.lblName);
        this.add(this.lblValue);
        this.add(this.txtName);
        this.add(this.txtValue);
        this.add(this.lbltitle);
        this.add(this.lblDescription);
        this.add(this.txtDescription);
        this.add(this.lblLinkImage);
        this.add(this.txtLinkImage);
        this.add(this.btnSearch);
        this.add(this.txtSearch);
        this.add(this.btnNhaplai);
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);
    }

}
