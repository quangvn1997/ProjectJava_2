/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package console.restaurant.view;

import console.restaurant.controller.PaymentController;
import console.restaurant.entities.Food;
import console.restaurant.entities.Order;
import console.restaurant.entities.OrderDetail;
import console.restaurant.entities.SessionAdmin;
import console.restaurant.entities.Table;
import console.restaurant.models.FoodsModel;
import console.restaurant.models.OrderDetailModel;
import console.restaurant.models.OrdersModel;
import console.restaurant.models.TablesModel;
import static console.restaurant.view.ManagerFood.table;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Anh Tiến ơi.Có Trộm!
 */
public class ManagerPayment extends javax.swing.JFrame {

    /**
     * Creates new form QuanLyThanhToan
     */
    private static FoodsModel foodModel = new FoodsModel();
    private OrdersModel orderModel = new OrdersModel();
    private OrderDetailModel orderDetailModel = new OrderDetailModel();
    private TablesModel tableModel = new TablesModel();
    public static HashMap<Integer, Food> foodsOrder;
    public static long totalPrice;
    public static int disc;
    public static long realPrice;
    private Table currentTable;
    private Order currentOrder = null;
    private ArrayList<OrderDetail> currentOrderDetails;
    private static Locale format;
    private static NumberFormat formatter;
    private QuanlityOrder foodQuanlityChooser;

    public ManagerPayment(int id) {
        // Kiểm tra tồn tại của bàn.
        currentTable = tableModel.getById(id);
        if (currentTable == null) {
            JOptionPane.showMessageDialog(null, "Bàn không tồn tại hoặc đã bị xoá!");
            return;
        }
        System.out.println("Bắt đầu tạo hoá đơn cho " + currentTable.getName());
        foodsOrder = new HashMap<>();
        System.out.println("Bắt đầu khởi tạo các component.");
        initComponents();
        initAnotherComponent();
        // Kiểm tra trạng thái của bàn, nếu là trong trạng thái đang sử dụng
        // thì tìm kiếm, load thông tin order và order detail.
        if (currentTable.getStatus() == 2) {
            System.out.println("Bàn đang được sử dụng, tìm thông tin hoá đơn...");
            // Load thông tin order.
            currentOrder = orderModel.getTableCurrentOrder(currentTable.getId());
            System.out.println("Discount: " + currentOrder.getDiscount());
            // Bàn chỉ ở trạng thái đang sử dụng khi hoá đơn đã được lưu,
            // trong trường hợp không tồn tại order, thông báo lỗi.
            if (currentOrder == null) {
                System.err.println("Sai trạng thái của bàn, bàn ở trạng thái sử dụng mà không tồn tại hoá đơn.");
                // Bàn ở trong trạng thái đang sử dụng bắt buộc phải có thông tin order.                
                return;
            }
            ManagerPayment.discount.setSelectedItem(String.valueOf(currentOrder.getDiscount()));
            ManagerPayment.disc = currentOrder.getDiscount();
            // Kiểm tra và lấy thông tin order detail nếu tồn tại.
            currentOrderDetails = orderDetailModel.byOrderID(currentOrder.getId());

            for (OrderDetail currentOrderDetail : currentOrderDetails) {
                // Lấy thông tin thức ăn từ food id trong order detail.
                Food orderedFood = foodModel.getById(currentOrderDetail.getFoodId());
                // Set số lượng order cho món theo order detail.
                orderedFood.setOrderQuantity(currentOrderDetail.getQuantity());
                // Đẩy thông tin order detail vào hash map của food.
                ManagerPayment.addFood(orderedFood, orderedFood.getOrderQuantity());
            }
        }        

        // Load thông tin order và order detail ra form nếu đã tồn tại sau khi khởi tạo các component.
        if (currentOrder != null) {
            // Tính toán tiền từ hoá đơn cũ.
            ManagerPayment.fetchFoodMapToTable();
        }

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // Mở form chọn số lương món ăn khi click menu.
        this.tableMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    if (row != -1) {
                        TableModel tblModel = tableMenu.getModel();
                        int id = Integer.parseInt(tblModel.getValueAt(row, 0).toString());
                        Food food = foodModel.getById(id);
                        if (food == null) {
                            JOptionPane.showMessageDialog(null, "Món ăn không tồn tại hoặc đã bị xóa.");
                            return;
                        }
                        foodQuanlityChooser = new QuanlityOrder(food);
                        foodQuanlityChooser.setVisible(true);
                    }
                }
            }
        });

        this.tableOrder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    JTable target = (JTable) e.getSource();
                    int row = target.getSelectedRow();
                    int colum = target.getSelectedColumn();
                    if (row != -1) {
                        TableModel tblModel = tableOrder.getModel();
                        int id = Integer.parseInt(tblModel.getValueAt(row, 0).toString());
                        Food food = foodModel.getById(id);
                        if (food == null) {
                            JOptionPane.showMessageDialog(null, "Món ăn không tồn tại hoặc đã bị xóa.");
                            return;
                        }

                        QuanlityOrder quanlityForm = new QuanlityOrder(food);
                        quanlityForm.setVisible(true);
                    }
                }
            }
        });

        this.txtSearchFood.getDocument().addDocumentListener(new DocumentListener() {
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
                ArrayList<Food> listFood = new ArrayList<>();
                DefaultTableModel model = (DefaultTableModel) tableMenu.getModel();
                model.setRowCount(0);
                if (txtSearchFood != null && txtSearchFood.getText().length() > 0) {
                    listFood = foodModel.searchFood(txtSearchFood.getText());
                } else {
                    listFood = foodModel.getListFoodMenu();
                }
                for (Food food : listFood) {
                    model.addRow(new Object[]{String.valueOf(food.getId()), food.getName(), formatter.format(food.getUnitPrice())});
                }
            }
        });
    }

    // Thêm món ăn vào order
    public static void addFood(Food food, int quantity) {
        if (ManagerPayment.foodsOrder.containsKey(food.getId())) {
            Food existFood = ManagerPayment.foodsOrder.get(food.getId());
            food.setOrderQuantity(food.getOrderQuantity() + existFood.getOrderQuantity());
        }
        ManagerPayment.foodsOrder.put(food.getId(), food);
    }

    public static void fetchFoodMapToTable() {
        DefaultTableModel model = (DefaultTableModel) ManagerPayment.tableOrder.getModel();
        model.setRowCount(0);
        long totalPayment = 0;
        for (Map.Entry<Integer, Food> entry : ManagerPayment.foodsOrder.entrySet()) {
            Food f1 = entry.getValue();
            totalPayment += f1.getOrderQuantity() * f1.getUnitPrice();
            model.addRow(new Object[]{String.valueOf(f1.getId()), f1.getName(), formatter.format(f1.getUnitPrice()), f1.getOrderQuantity(), formatter.format(f1.getUnitPrice() * f1.getOrderQuantity()), f1.getNote(), ""});
        }
        int discountNumber = Integer.parseInt(String.valueOf(ManagerPayment.discount.getSelectedItem()).trim());
        ManagerPayment.totalPrice = totalPayment;
        ManagerPayment.realPrice = totalPayment - (totalPayment * discountNumber / 100);
        ManagerPayment.lblTotal.setText(formatter.format(ManagerPayment.totalPrice));
        ManagerPayment.lblRealPayment.setText(formatter.format(ManagerPayment.realPrice));
    }

    public void initAnotherComponent() {
        format = new Locale("vi", "VN");
        formatter = NumberFormat.getCurrencyInstance(format);
        banso.setText("Hoá đơn " + currentTable.getName());
        Thread clock = new Thread() {
            public void run() {
                try {
                    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
                    while (true) {
                        Date date1 = new Date();
                        date.setText("Hôm nay ngày: " + dateFormat.format(date1));
                        time.setText(timeFormat.format(date1));
                        sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        };
        clock.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        txtSearchFood = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnReturn = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        discount = new javax.swing.JComboBox<>();
        banso = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableOrder = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMenu = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        saveOrder = new javax.swing.JButton();
        time = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblRealPayment = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(250, 250, 250));

        txtSearchFood.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchFoodActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Brush Script MT", 2, 65)); // NOI18N
        jLabel4.setText("Furious Food & Drink");

        btnReturn.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnReturn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/return.jpg.png"))); // NOI18N
        btnReturn.setText("QUAY LẠI");
        btnReturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReturnActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel5.setText("Thu ngân :");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel12.setText("TỔNG TIỀN :");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        discount.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        discount.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "10", "20", "30", "40", "50", "60", "70", "80", "90" }));
        discount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountActionPerformed(evt);
            }
        });

        banso.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        banso.setText("1");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel13.setText("   GIẢM GIÁ (%) :");

        date.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N

        tableOrder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {"", null, null, null, null, null},
                {null, null, null, null, null, ""},
                {null, null, null, null, null, null},
                {null, null, null, null, "", null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, "", null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, "", null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "            Mã món ăn", "           Tên Dịch Vụ", "                 Đơn Giá", "     Số Lượng", "             Thành Tiền", "              Ghi Chú"
            }
        ));
        jScrollPane2.setViewportView(tableOrder);
        if (tableOrder.getColumnModel().getColumnCount() > 0) {
            tableOrder.getColumnModel().getColumn(0).setPreferredWidth(15);
            tableOrder.getColumnModel().getColumn(3).setPreferredWidth(15);
        }

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 199, Short.MAX_VALUE)
        );

        tableMenu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Tên dịch vụ", "Đơn giá"
            }
        ));
        jScrollPane1.setViewportView(tableMenu);
        if (tableMenu.getColumnModel().getColumnCount() > 0) {
            tableMenu.getColumnModel().getColumn(0).setPreferredWidth(20);
            tableMenu.getColumnModel().getColumn(1).setPreferredWidth(120);
        }

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Tìm kiếm :");

        saveOrder.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        saveOrder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/datban.jpg"))); // NOI18N
        saveOrder.setText("LƯU HÓA ĐƠN");
        saveOrder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveOrderActionPerformed(evt);
            }
        });

        time.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel6.setText(" VNĐ");

        jButton11.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/pay.jpg"))); // NOI18N
        jButton11.setText("THANH TOÁN");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/console/restaurant/Image/delete.jpg"))); // NOI18N
        jButton6.setText("HỦY HÓA ĐƠN");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Danh sách thực đơn :");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel15.setText("THÀNH TIỀN :");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel7.setText(" VNĐ");

        lblRealPayment.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        lblTotal.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setText("Mọi thắc mắc xin liên hệ Mr.Tiến : 01636986950");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSearchFood, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(540, 540, 540)
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lblRealPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 796, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(648, 648, 648)
                                                .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 1, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13)
                                            .addComponent(jLabel12))
                                        .addGap(18, 18, 18)
                                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(saveOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                    .addComponent(btnReturn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap(40, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(banso, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(146, 146, 146)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(date, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(time, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(banso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchFood, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(saveOrder, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnReturn, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(discount, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblRealPayment, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(39, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(36, 36, 36))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        //Load dữ liệu khi form mở lên.
        PaymentController.loadFood(tableMenu);
        jTextField2.setText(SessionAdmin.getName());
    }//GEN-LAST:event_formWindowOpened

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        ConfirmPayment confirmPayment = new ConfirmPayment();
        confirmPayment.setVisible(true);
    }//GEN-LAST:event_jButton11ActionPerformed

    private void saveOrderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveOrderActionPerformed
        boolean isUpdate = currentOrder != null;
        Order order = new Order();
        if (isUpdate) {
            order = currentOrder;
        }
        ArrayList<OrderDetail> listOrderDetail = new ArrayList<>();
        for (Map.Entry<Integer, Food> entry : ManagerPayment.foodsOrder.entrySet()) {
            Food f = entry.getValue();
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setFoodId(f.getId());
            orderDetail.setUnitPrice(f.getUnitPrice());
            orderDetail.setQuantity(f.getOrderQuantity());
            orderDetail.setTotalPrice(f.getOrderQuantity() * f.getUnitPrice());
            orderDetail.setStatus(2);
            listOrderDetail.add(orderDetail);
        }
        order.setTotalPrice(ManagerPayment.totalPrice);
        order.setDiscount(disc);
        order.setRealPrice(ManagerPayment.realPrice);
        order.setTableId(currentTable.getId());
        order.setStatus(2);
        int orderId = 0;
        System.out.println("Trạng thái " + isUpdate);
        if (isUpdate) {
            orderId = order.getId();
            orderModel.update(order);
        } else {
            orderId = orderModel.insert(order);
            currentOrder = order;
        }
        System.out.println("Lưu thành công hoá đơn với id: " + orderId);
        for (OrderDetail orderDetail : listOrderDetail) {
            orderDetail.setOrderId(orderId);
            OrderDetail existOrderDetail = orderDetailModel.getByOrderIdAndFoodId(orderId, orderDetail.getFoodId());
            if (existOrderDetail != null) {
                orderDetail.setId(existOrderDetail.getId());
            } else {
                orderDetail.setId(0);
            }
            if (orderDetail.getId() > 0) {
                orderDetailModel.update(orderDetail);
            } else {
                orderDetailModel.insert(orderDetail);
            }
        }
        if (!isUpdate) {
            currentTable.setStatus(2);
            tableModel.update(currentTable);
        }
        JOptionPane.showMessageDialog(null, "Lưu hoá đơn thành công!");
    }//GEN-LAST:event_saveOrderActionPerformed

    private void btnReturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReturnActionPerformed
        // jPanel1.setVisible(false);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(false);
    }//GEN-LAST:event_btnReturnActionPerformed

    private void txtSearchFoodActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchFoodActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchFoodActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void discountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountActionPerformed

        int discount = Integer.parseInt(String.valueOf(ManagerPayment.discount.getSelectedItem()).trim());
        ManagerPayment.disc = discount;
        ManagerPayment.realPrice = ManagerPayment.totalPrice - (ManagerPayment.totalPrice * discount / 100);
        ManagerPayment.lblTotal.setText(ManagerPayment.formatter.format(ManagerPayment.totalPrice));
        ManagerPayment.lblRealPayment.setText(ManagerPayment.formatter.format(ManagerPayment.realPrice));
    }//GEN-LAST:event_discountActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel banso;
    private javax.swing.JButton btnReturn;
    private javax.swing.JLabel date;
    public static javax.swing.JComboBox<String> discount;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JLabel lblRealPayment;
    public static javax.swing.JLabel lblTotal;
    public static javax.swing.JButton saveOrder;
    private javax.swing.JTable tableMenu;
    public static javax.swing.JTable tableOrder;
    private javax.swing.JLabel time;
    private javax.swing.JTextField txtSearchFood;
    // End of variables declaration//GEN-END:variables
}
