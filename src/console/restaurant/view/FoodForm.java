/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

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
    
    private JButton btnSubmit;
    private JButton btnReset;

    public FoodForm() {
        this.setTitle("Quản lý món ăn");
        this.setSize(450, 500);

        this.foodPanel = new JPanel();
        this.foodPanel.setBackground(Color.WHITE);
        this.foodPanel.setBounds(0, 0, 450, 550);

        this.lblHeader = new JLabel("Thêm mới món ăn");
        this.lblHeader.setFont(new Font("Serif", Font.BOLD, 18));
        this.lblName = new JLabel("Tên");
        this.lblCategory = new JLabel("Danh mục");
        this.lblImage = new JLabel("Ảnh đại diện");
        this.lblDescription = new JLabel("Mô tả");
        this.lblPrice = new JLabel("Giá");
        this.btnSubmit = new JButton("Lưu");
        this.btnReset = new JButton("Nhập lại");

        String[] petStrings = {"Đồ ăn", "Đồ uống"};

        this.txtName = new JTextField();
        this.cmbCategories = new JComboBox(petStrings);
        this.txtAreaDescription = new JTextArea();
        this.txtAreaDescription.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.txtImage = new JTextField();
        this.txtPrice = new JTextField();

        this.lblHeader.setBounds(150, 40, 200, 54);
        this.lblName.setBounds(50, 100, 100, 34);
        this.txtName.setBounds(160, 100, 200, 34);
        this.lblCategory.setBounds(50, 144, 100, 34);
        this.cmbCategories.setBounds(160, 144, 100, 34);
        this.lblImage.setBounds(50, 188, 100, 34);
        this.txtImage.setBounds(160, 188, 200, 34);
        this.lblDescription.setBounds(50, 232, 100, 34);
        this.txtAreaDescription.setBounds(160, 232, 200, 64);
        this.lblPrice.setBounds(50, 306, 100, 34);
        this.txtPrice.setBounds(160, 306, 100, 34);
        this.btnSubmit.setBounds(160, 350, 80, 34);
        this.btnReset.setBounds(250, 350, 80, 34);

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
    }

    public static void main(String[] args) {
        FoodForm f = new FoodForm();
        f.setVisible(true);
    }

}
