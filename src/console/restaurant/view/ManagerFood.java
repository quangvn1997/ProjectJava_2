/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.FoodsController;
import console.restaurant.entities.Food;
import console.restaurant.entities.SessionAdmin;
import console.restaurant.models.FoodsModel;
import console.restaurant.utilities.ValidateUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Asus
 */
public class ManagerFood extends JPanel {

    private JButton btnFirst;
    private JButton btnPrevious;
    private JButton btnNext;
    private JButton btnLast;
    private JButton btnPage;

    private JLabel lblSearch;
    private JTextField txtSearch;
    private JButton btnSearch;

    private JButton btnDelete;
    private JButton btnUpdate;
    private JButton btnCreate;

    private int page = 1;

    private FoodForm foodForm;
    private JTable table;
    private DefaultTableModel modelFood;
    private JScrollPane scrollPane;
    private FoodsController foodController = new FoodsController();

    public ManagerFood() {
        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lblSearch = new JLabel("Nhập tên");
        this.txtSearch = new JTextField();
        this.btnSearch = new JButton("Tìm");
        this.btnUpdate = new JButton("Sửa");
        this.btnDelete = new JButton("Xóa");
        this.btnCreate = new JButton("Tạo mới");

        this.lblSearch.setBounds(20, 20, 70, 34);
        this.txtSearch.setBounds(100, 20, 200, 34);
        this.btnSearch.setBounds(310, 20, 100, 34);
        this.btnUpdate.setBounds(640, 20, 100, 34);
        this.btnDelete.setBounds(760, 20, 100, 34);
        this.btnCreate.setBounds(880, 20, 100, 34);

        this.add(this.lblSearch);
        this.add(this.txtSearch);
        this.add(this.btnSearch);
        this.add(this.btnUpdate);
        this.add(this.btnDelete);
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

//        //them su kien
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
        foodController.loadFood(table);
        //Hiển thị kích thước bảng
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);

        // Click bảng hiển thị FoodForm
        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foodForm = new FoodForm();
                foodForm.setVisible(true);
                foodForm.Create();
                foodController.loadFood(table);
//                foodForm
            }
        });
        this.btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    FoodUpdate foodUpdate = new FoodUpdate();
//                    Admin admin = new Admin();
                    foodUpdate.setVisible(true);
                    TableModel tblModel = table.getModel();
                    String name = tblModel.getValueAt(row, 1).toString();
                    String description = tblModel.getValueAt(row, 3).toString();
                    String urlImage = tblModel.getValueAt(row, 4).toString();
                    String price = tblModel.getValueAt(row, 5).toString();

//                     thêm vào textField
                    foodUpdate.txtName.setText(name);
                    foodUpdate.txtAreaDescription.setText(description);
                    foodUpdate.txtImage.setText(urlImage);
                    foodUpdate.txtPrice.setText(price);

                }
            }
        });
        this.btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    TableModel tblModel = table.getModel();
                    Object[] options = {"Có", "Không"};
                    Component form = null;
                    int n = JOptionPane.showOptionDialog(form, "Bạn có muốn xóa món " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " không?  ", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
                    if (n == JOptionPane.YES_OPTION) {
                        FoodsModel.deleteFood(tblModel.getValueAt(row, 0).toString());
                        JOptionPane.showMessageDialog(null, "Xóa món ăn " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " thành công.");
                        foodController.loadFood(table);
                    }
                }
            }
        });
//        this.table.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                if (e.getClickCount() == 1) {
//
//                    JTable target = (JTable) e.getSource();
//                    int row = target.getSelectedRow();
//                    if (row != -1) {
//                        FoodForm foodForm = new FoodForm();
//                        foodForm.setVisible(true);
//
//                        TableModel tblModel = table.getModel();
//                        String name = tblModel.getValueAt(row, 1).toString();
//                        String price = tblModel.getValueAt(row, 3).toString();
//                        String urlImage = tblModel.getValueAt(row, 4).toString();
//                        String description = tblModel.getValueAt(row, 5).toString();
////                     thêm vào textField
//                        foodForm.txtName.setText(name);
//                        foodForm.txtPrice.setText(price);
//                        foodForm.txtAreaDescription.setText(description);
//                        foodForm.txtImage.setText(urlImage);
//                    }
//                }
//            }
//        });
//        //delete action
//        this.btndeleteFood.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int row = table.getSelectedRow();
//                if (row != -1) {
//                    TableModel tblModel = table.getModel();
//                    Object[] options = {"Yes", "No"};
//                    Component form = null;
//                    int n = JOptionPane.showOptionDialog(form, "Do you like to delete the record for ID:  " + tblModel.getValueAt(row, 1).toString() + " ?", "Exit Confirmation", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
//                    if (n == JOptionPane.YES_OPTION) {
//                        FoodsModel.deleteFood(tblModel.getValueAt(row, 0).toString());
//                        JOptionPane.showMessageDialog(null, "The record has been deleted successfully.");
//                        txtName.setText("");
//                        txtValue.setText("");
//                        txtLinkImage.setText("");
//                        txtDescription.setText("");
//                        FoodsController.loadFood(table);
//                    }
//                }
//            }
//        });
//        //insert action
//        this.btncreateFood.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (txtName.getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng điền tên dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
//                if (new String(txtValue.getText()).isEmpty() && ValidateUtilities.validateNumberUpdate(new String(txtValue.getText()))) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng điền giá dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
//                if (txtLinkImage.getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng điền link ảnh dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
//                if (txtDescription.getText().isEmpty()) {
//                    JOptionPane.showMessageDialog(null, "Vui lòng điền miêu tả dịch vụ !", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
//                if (ValidateUtilities.checkExistanceAdmin(txtName.getText())) {
//                    JOptionPane.showMessageDialog(null, "Tên dịch vụ đã tồn tại !, Vui lòng chọn tên khác.", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
//                if (ValidateUtilities.checkExistanceAdmin(txtLinkImage.getText())) {
//                    JOptionPane.showMessageDialog(null, "Link ảnh đã được sử dụng !, Vui lòng chọn ảnh khác.", "Báo lỗi", JOptionPane.INFORMATION_MESSAGE);
//                    return;
//                }
//                Food food = new Food();
//                food.setName(txtName.getText());
//                food.setUnitPrice(Float.valueOf(txtValue.getText()));
//                food.setImgUrl(txtLinkImage.getText());
//                food.setDescription(txtDescription.getText());
//                FoodsModel.insertFood(food);
//                FoodsController.loadFood(table);
//                txtName.setText("");
//                txtValue.setText("");
//                txtLinkImage.setText("");
//                txtDescription.setText("");
//            }
//        });
//       this.btnUpdate.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int row = table.getSelectedRow();
//                if (row != -1) {
//                    AdminUpdate adminUpdate = new AdminUpdate();
////                    Admin admin = new Admin();
//                    adminUpdate.setVisible(true);
//                    TableModel tblModel = table.getModel();
//                    String name = tblModel.getValueAt(row, 1).toString();
//                    String username = tblModel.getValueAt(row, 2).toString();
//                    String pass = tblModel.getValueAt(row, 3).toString();
//
////                     thêm vào textField
//                    adminUpdate.txtName.setText(name);
//                    adminUpdate.txtAcount.setText(username);
//                    adminUpdate.txtPassword.setText(pass);
//
////                    admin.setUsername(adminUpdate.txtAcount.getText());
////                    admin.setName(adminUpdate.txtName.getText());
////                    admin.setPassword(new String(adminUpdate.txtPassword.getText()));
////                    AdminsModel.update(admin);
//                }
//            }
//        });
//        this.btnSearch.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (txtSearch.getText().trim().matches("^(?=.*[a-zA-Z])(?=.*[0-9])[a-zA-Z0-9]+$") || txtSearch.getText().trim().matches("[a-zA-Z]+")) {
//                    FoodsModel.loadFoodsSearch(table, FoodsController.searchFood(txtSearch.getText(), 2));
//                } else if (txtSearch.getText().matches("^-?\\d+$")) {
//                    FoodsModel.loadFoodsSearch(table, FoodsController.searchFood(txtSearch.getText(), 1));
//                } else if (txtSearch.getText().isEmpty()) {
//                    FoodsController.loadFood(table);
//                    JOptionPane.showMessageDialog(null, " vui lòng điền id hoac name");
//                } else {
//                    JOptionPane.showMessageDialog(null, "không tồn tại");
//                }
//
//            }
//        });
//        this.btnNhaplai.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent ae) {
//                txtSearch.setText("");
//                txtName.setText("");
//                txtValue.setText("");
//                txtLinkImage.setText("");
//                txtDescription.setText("");
//                FoodsController.loadFood(table);
//            }
//        });
//        
        // Table        
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);
    }

}
