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
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class QuanlityOrder extends JFrame {

    private JLabel lblHeader;
    private JLabel NameOrder;
    private JPanel panelQuanlity;
    private JLabel lblNumber;
    private JSpinner txtNumber;
    private JButton btnAddOrder;
    private Food food;
    private JTextArea txtNote;
    private JLabel lblNote;

    public QuanlityOrder(Food foodPara) {
        this.food = foodPara;
        this.setTitle("Nhập số lượng");
        this.setSize(450, 400);

        this.panelQuanlity = new JPanel();
        this.panelQuanlity.setBackground(Color.white);
        this.lblHeader = new JLabel("Thêm số lượng cho");
//        Fix cứng tên món ăn test
        this.NameOrder = new JLabel(food.getName());
        this.lblNumber = new JLabel("Nhập số lượng :");
        this.txtNumber = new JSpinner();
        this.txtNumber.setValue(1);
        this.lblNote = new JLabel("Ghi chú :");
        this.txtNote = new JTextArea();
        this.txtNote.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        this.btnAddOrder = new JButton("Thêm vào hóa đơn");

        this.btnAddOrder.setBackground(new Color(187, 189, 193));
        this.panelQuanlity.setBounds(0, 0, 600, 500);
        this.lblHeader.setBounds(20, 10, 150, 50);
        this.NameOrder.setBounds(20, 45, 400, 40);
        this.NameOrder.setFont(new Font("Serif", Font.BOLD, 22));
        this.txtNumber.setFont(new Font("Serif", Font.BOLD, 24));
        this.lblNumber.setBounds(20, 105, 150, 50);
        this.txtNumber.setBounds(150, 110, 150, 45);
        this.lblNote.setBounds(65, 180, 70, 50);
        this.txtNote.setBounds(150, 190, 150, 50);
        this.btnAddOrder.setBounds(150, 280, 150, 40);

        this.add(this.btnAddOrder);
        this.add(this.txtNumber);
        this.add(this.lblNumber);
        this.add(this.NameOrder);
        this.add(this.lblHeader);
        this.add(this.lblNote);
        this.add(this.txtNote);
        this.add(this.panelQuanlity);

        this.setLayout(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.btnAddOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int number = Integer.parseInt(txtNumber.getValue().toString());
                    DefaultTableModel model = (DefaultTableModel) ManagerPayment.tableOrder.getModel();
                    model.setRowCount(0);
                    food.setNote(txtNote.getText());
                    food.setOrderQuantity(number);
                    ManagerPayment.foodsOrder.put(food.getId(), food);

//                  Format thành giá tiền VN
                    Locale format = new Locale("vi","VN");
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(format);

                    for (Map.Entry<Integer, Food> entry : ManagerPayment.foodsOrder.entrySet()) {
                        Food f1 = entry.getValue();
                        model.addRow(new Object[]{String.valueOf(f1.getId()), f1.getName(), formatter.format(f1.getUnitPrice()), f1.getOrderQuantity(), formatter.format(f1.getUnitPrice() * f1.getOrderQuantity()), f1.getNote(), ""});
                    }

                    setVisible(false);
                } catch (NumberFormatException exception) {
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
