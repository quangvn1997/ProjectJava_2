/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.FoodsController;
import console.restaurant.entities.Food;
import console.restaurant.models.FoodsModel;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
public class ManagerFood extends JPanel {

    private static JButton btnFirst;
    private static JButton btnPrevious;
    private static JButton btnNext;
    private static JButton btnLast;
    private static JButton btnPage;

    private JLabel lblSearch;
    private JTextField txtSearch;

//    private JButton btnDelete;
//    private JButton btnUpdate;
    private JButton btnCreate;

    public static int page = 1;
    public static int limit = 2;
    public static int count = 0;
    public static int totalPage = 0;

    private FoodForm foodForm;
    public static JTable table;
    private DefaultTableModel modelFood;
    private JScrollPane scrollPane;
    private FoodsController foodController = new FoodsController();
    private static FoodsModel foodModel = new FoodsModel();

    public ManagerFood() {
        this.setBackground(new Color(250, 250, 250));
        this.setBounds(350, 90, 1000, 520);
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        this.lblSearch = new JLabel("Tìm theo tên");
        this.txtSearch = new JTextField();
        this.btnCreate = new JButton("Tạo mới");

        this.lblSearch.setBounds(20, 20, 70, 34);
        this.txtSearch.setBounds(100, 20, 200, 34);
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

        this.add(this.btnFirst);
        this.add(this.btnPrevious);
        this.add(this.btnPage);
        this.add(this.btnNext);
        this.add(this.btnLast);

        String[] columnNames = {"ID", "Tên", "Danh mục", "Miêu tả", "Ảnh", "Giá (VND)", "Ngày tạo", "Ngày cập nhật"};
        Object[][] data = {};
        this.modelFood = new DefaultTableModel(data, columnNames);
        this.table = new JTable(modelFood);
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
        this.scrollPane = new JScrollPane(table);
        this.scrollPane.setBounds(0, 70, 1000, 380);
        loadFood();

        // Click bảng hiển thị FoodForm
        this.btnCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                foodForm = new FoodForm();
                foodForm.setVisible(true);
            }
        });

        this.btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page += 1;
                loadFood();
            }
        });

        this.btnLast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = totalPage;
                loadFood();
            }
        });

        this.btnPrevious.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page -= 1;
                loadFood();
            }
        });

        this.btnFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                page = 1;
                loadFood();
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
                ArrayList<Food> listFood = new ArrayList<>();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                if (txtSearch != null && txtSearch.getText().length() > 0) {
                    listFood = foodModel.searchFood(txtSearch.getText());
                } else {
                    listFood = foodModel.getListFood(page, limit);
                }
                listFood.forEach((food) -> {
                    model.addRow(new Object[]{String.valueOf(food.getId()), food.getName(), food.getCategoryName(), food.getDescription(), food.getImgUrl(), food.getUnitPrice(), food.getCreatedAt(), food.getUpdateAt()});
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
                        FoodForm foodForm = new FoodForm(2, id);
                        foodForm.setVisible(true);
                    }
                }
            }
        });
        this.add(scrollPane);
        this.setLayout(null);
        this.setVisible(false);
    }

    public static void loadFood() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        ArrayList<Food> listFood = foodModel.getListFood(page, limit);
        listFood.forEach((food) -> {
            model.addRow(new Object[]{String.valueOf(food.getId()), food.getName(), food.getCategoryName(), food.getDescription(), food.getImgUrl(), food.getUnitPrice(), food.getCreatedAt(), food.getUpdateAt()});
        });
        count = foodModel.countActive();
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
