/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Food;
import console.restaurant.models.FoodsModel;
import console.restaurant.utilities.ValidateUtilities;
import static console.restaurant.view.ManagerFood.table;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
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
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Truong
 */
public class FoodForm extends JFrame {

    private JPanel foodPanel;
    private JLabel lblId;
    private JLabel lblIdValue;
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

    private FoodsModel foodModel = new FoodsModel();

    private int action = 1;
    private int id = 0;
    private String idFoodUpdate;

    public void setIdFoodUpdate(String idFoodUpdate) {
        this.idFoodUpdate = idFoodUpdate;
    }
    
    
    public FoodForm() {
        initComponent();
    }

    public FoodForm(int action, int id) {
        this.action = action;
        this.id = id;
        initComponent();
    }

    public void setLblHeader(JLabel lblHeader) {
        this.lblHeader = lblHeader;
    }

    private void initComponent() {
        // Nếu là form edit.
        Food food = new Food();
        if (this.action == 2) {
            // Lấy dữ liệu food từ db theo id.
            food = foodModel.getById(this.id);
            if (food == null) {
                JOptionPane.showMessageDialog(null, "Food không tồn tại hoặc đã bị xóa.");
                return;
            }
            this.lblHeader = new JLabel("Sửa món ăn");
            this.btnDelete = new JButton("Xóa");
            this.btnDelete.setBounds(100, 370, 80, 34);
            this.add(this.btnDelete);

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
//                            foodController.loadFood(table);

                            DefaultTableModel model = (DefaultTableModel) ManagerFood.table.getModel();
                            model.setRowCount(0);
                            List<Food> listFood = FoodsModel.getAllFood();

                            listFood.forEach((f1) -> {
                                model.addRow(new Object[]{String.valueOf(f1.getId()), f1.getName(), f1.getType(), f1.getDescription(), f1.getImgUrl(), f1.getUnitPrice(), f1.getCreatedAt(), f1.getUpdateAt()});
                            });
                            SwingUtilities.windowForComponent(foodPanel).dispose();
                        }
                    }
                }
            });
        }

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
        this.lblPrice = new JLabel("Giá");

        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");

        String[] petStrings = {"Đồ ăn", "Đồ uống", "Món ăn đặc trưng"};

        this.txtName = new JTextField();
        this.txtName.setText(food.getName());
        this.cmbCategories = new JComboBox(petStrings);
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

        //this.lblName.setBounds(20, 50, 100, 34);
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
                food.setUnitPrice(Integer.parseInt(txtPrice.getText()));
                
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
                DefaultTableModel model = (DefaultTableModel) ManagerFood.table.getModel();
                model.setRowCount(0);
                List<Food> listFood = FoodsModel.getAllFood();
                
                listFood.forEach((f1) -> {
                    model.addRow(new Object[]{String.valueOf(f1.getId()), f1.getName(), f1.getType(), f1.getDescription(), f1.getImgUrl(), f1.getUnitPrice(), f1.getCreatedAt(), f1.getUpdateAt()});
                });

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

//        this.btnDelete.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                int row = table.getSelectedRow();
//                if (row != -1) {
//                    TableModel tblModel = table.getModel();
//                    Object[] options = {"Có", "Không"};
//                    Component form = null;
//                    int n = JOptionPane.showOptionDialog(form, "Bạn có muốn xóa món " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " không?  ", "Xác nhận", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options);
//                    if (n == JOptionPane.YES_OPTION) {
//                        FoodsModel.deleteFood(tblModel.getValueAt(row, 0).toString());
//                        JOptionPane.showMessageDialog(null, "Xóa món ăn " + "' " + tblModel.getValueAt(row, 1).toString() + " '" + " thành công.");
////                        foodController.loadFood(table);
//                    }
//                }
//            }
//        });
    }

    public static void main(String[] args) {
        FoodForm f = new FoodForm();
        f.setVisible(true);
    }

}
