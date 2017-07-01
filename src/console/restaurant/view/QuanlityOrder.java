/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.entities.Food;
import console.restaurant.models.FoodsModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.table.DefaultTableModel;

public class QuanlityOrder extends JFrame {

    private JLabel lblHeader;
    private JLabel NameOrder;
    private JPanel panelQuanlity;
    private JLabel lblNumber;
    private JSpinner txtNumber;
    private JButton btnAddOrder;
    private Food food;    

    public QuanlityOrder(Food foodPara) {
        this.food = foodPara;
        this.setTitle("Chọn số lượng món ăn");
        this.setSize(400, 300);

        this.panelQuanlity = new JPanel();
        this.panelQuanlity.setBackground(Color.white);
        this.lblHeader = new JLabel("Món: ");
//        Fix cứng tên món ăn test
        this.NameOrder = new JLabel(food.getName());
        this.lblNumber = new JLabel("Số lượng :");
        this.txtNumber = new JSpinner();
        this.txtNumber.setValue(1);        
        this.btnAddOrder = new JButton("Thêm vào hóa đơn");

        this.btnAddOrder.setBackground(new Color(187, 189, 193));
        this.panelQuanlity.setBounds(0, 0, 600, 500);
        this.lblHeader.setBounds(20, 10, 200, 50);
        this.NameOrder.setBounds(20, 45, 400, 40);
        this.NameOrder.setFont(new Font("Serif", Font.BOLD, 22));
        this.txtNumber.setFont(new Font("Serif", Font.BOLD, 24));
        this.lblNumber.setBounds(20, 105, 150, 50);
        this.txtNumber.setBounds(150, 110, 150, 45);        
        this.btnAddOrder.setBounds(150, 180, 150, 40);

        this.add(this.btnAddOrder);
        this.add(this.txtNumber);
        this.add(this.lblNumber);
        this.add(this.NameOrder);
        this.add(this.lblHeader);        
        this.add(this.panelQuanlity);

        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.btnAddOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Parse số lượng món ăn.
                    int number = Integer.parseInt(txtNumber.getValue().toString());
                    food.setOrderQuantity(number);
                    // Add món ăn vào table.
                    DefaultTableModel model = (DefaultTableModel) ManagerPayment.tableOrder.getModel();
                    model.setRowCount(0);                                        
                    ManagerPayment.addFood(food, number);
                    ManagerPayment.fetchFoodMapToTable();
                    dispose();
                } catch (NumberFormatException exception) {
                    exception.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số.");
                    return;
                }
            }
        });
    }

    public static void main(String[] args) {
        Food food = new FoodsModel().getById(1);
        QuanlityOrder quanlity = new QuanlityOrder(food);
        quanlity.setVisible(true);
    }

}
