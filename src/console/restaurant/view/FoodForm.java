/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Category;
import console.restaurant.entities.Food;
import console.restaurant.models.CategoryModel;
import console.restaurant.models.FoodsModel;
import console.restaurant.utilities.ValidateUtilities;
import static console.restaurant.view.ManagerFood.table;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Truong
 */
public class FoodForm extends JFrame {

    private JPanel foodPanel;
    private JLabel lblHeader;
    private JLabel lblName;
    private JLabel lblCategory;
    private JLabel lblDescription;
    private JLabel lblImage;
    private JLabel lblPrice;

    public JTextField txtName;
    public JComboBox cmbCategories;
    public JTextArea txtAreaDescription;
    public JTextField txtImage;
    public JTextField txtPrice;

    public JButton btnDelete;
    private JButton btnSubmit;
    public JButton btnReset;

    private FoodsModel foodModel;
    private CategoryModel categoryModel;

    private int action = 1;
    private int id = 0;

    public FoodForm() {
        initComponent();
    }

    public FoodForm(int action, int id) {
        this.foodModel = new FoodsModel();
        this.action = action;
        this.id = id;
        initComponent();
    }

    public void setLblHeader(JLabel lblHeader) {
        this.lblHeader = lblHeader;
    }

    private void initComponent() {
        this.foodModel = new FoodsModel();
        this.categoryModel = new CategoryModel();
        // Nếu là form edit.
        Food food = new Food();

        this.setTitle("Quản lý món ăn");
        this.setSize(450, 500);

        this.foodPanel = new JPanel();
        this.foodPanel.setBackground(Color.WHITE);
        this.foodPanel.setBounds(0, 0, 450, 550);
        this.lblHeader = new JLabel("Thêm món ăn");
        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblName = new JLabel("Tên");
        this.lblCategory = new JLabel("Danh mục");
        this.lblImage = new JLabel("Ảnh đại diện");
        this.lblDescription = new JLabel("Mô tả");
        this.lblPrice = new JLabel("Giá (VND)");
        this.btnDelete = new JButton("Xóa");
        this.btnDelete.setBounds(100, 370, 80, 34);

        if (action == 2) {
            // Lấy dữ liệu food từ db theo id.
            food = foodModel.getById(this.id);
            if (food == null) {
                JOptionPane.showMessageDialog(null, "Food không tồn tại hoặc đã bị xóa.");
                return;
            }
            this.lblHeader.setText("Sửa món ăn");
            this.add(this.btnDelete);
        }

        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");

        this.txtName = new JTextField();
        this.txtName.setText(food.getName());
        this.cmbCategories = new JComboBox();
        for (Category category : categoryModel.getListCategory()) {
            this.cmbCategories.addItem(category);
            if (category.getId() == food.getCategoryId()) {
                this.cmbCategories.setSelectedItem(category);
            }
        }
        this.txtAreaDescription = new JTextArea();
        this.txtAreaDescription.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.txtAreaDescription.setText(food.getDescription());
        this.txtImage = new JTextField();
        this.txtImage.setText(food.getImgUrl());
        this.txtPrice = new JTextField();

        this.txtPrice.setText(String.valueOf(food.getUnitPrice()));

        this.lblHeader.setBounds(180, 40, 200, 54);
        this.lblName.setBounds(50, 100, 100, 34);
        this.txtName.setBounds(160, 100, 200, 34);
        this.lblPrice.setBounds(50, 144, 100, 34);
        this.txtPrice.setBounds(160, 144, 140, 34);
        this.lblImage.setBounds(50, 188, 100, 34);
        this.txtImage.setBounds(160, 188, 200, 34);
        this.lblDescription.setBounds(50, 232, 100, 34);
        this.txtAreaDescription.setBounds(160, 232, 200, 64);
        this.lblCategory.setBounds(50, 306, 100, 34);
        this.cmbCategories.setBounds(160, 306, 100, 34);

        this.btnSubmit.setBounds(200, 370, 80, 34);
        this.btnReset.setBounds(300, 370, 80, 34);

        this.add(this.lblHeader);
        this.add(this.lblName);
        this.add(this.lblCategory);
        this.add(this.lblImage);
        this.add(this.lblDescription);
        this.add(this.lblPrice);
        this.add(this.txtName);
        this.add(this.cmbCategories);
        this.add(this.txtAreaDescription);
        this.add(this.txtImage);
        this.add(this.txtPrice);

        this.add(this.btnSubmit);
        this.add(this.btnReset);

        this.add(this.foodPanel);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(null);

        this.btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền tên dịch vụ !", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtAreaDescription.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền miêu tả dịch vụ!", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtImage.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền ảnh đại diện !", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (txtPrice.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng điền giá dịch vụ !", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (ValidateUtilities.checkExistanceAdmin(txtName.getText())) {
                    JOptionPane.showMessageDialog(null, "Tên món ăn đã tồn tại !", "Báo lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Food food = new Food();
                food.setName(txtName.getText());
                food.setDescription(txtAreaDescription.getText());
                food.setImgUrl(txtImage.getText());
                food.setUnitPrice(Float.parseFloat(txtPrice.getText()));
                Category selectedCate = (Category) cmbCategories.getSelectedItem();
                food.setCategoryId(selectedCate.getId());
                food.setId(id);
                if (action == 1) {
                    if (foodModel.insert(food)) {
                        JOptionPane.showMessageDialog(null, "Thêm mới dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi thêm dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (action == 2) {
                    if (foodModel.update(food)) {
                        JOptionPane.showMessageDialog(null, "Sửa dịch vụ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi sửa dịch vụ", "Thông báo", JOptionPane.ERROR_MESSAGE);
                    }
                }
//                Load trong database
                ManagerFood.page = 1;
                ManagerFood.loadFood();

//                
                txtName.setText("");
                txtPrice.setText("");
                txtAreaDescription.setText("");
                txtImage.setText("");
//                Đóng form
                SwingUtilities.windowForComponent(foodPanel).dispose();
            }
        });

        this.btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtAreaDescription.setText("");
                txtName.setText("");
                txtImage.setText("");
                txtPrice.setText("");
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
                        foodModel.delete(Integer.parseInt(tblModel.getValueAt(row, 0).toString()));
                        JOptionPane.showMessageDialog(null, "Xóa món ăn " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " thành công.");

                        ManagerFood.page = 1;
                        ManagerFood.loadFood();
                        SwingUtilities.windowForComponent(foodPanel).dispose();
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        FoodForm f = new FoodForm();
        f.setVisible(true);
    }

}
