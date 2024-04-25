/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package component;

import LIB.FadeEffect;
import com.formdev.flatlaf.FlatIntelliJLaf;
import entity.ChiTietHoaDon;
import entity.Mon;
import icon.FontAwesome;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jiconfont.swing.IconFontSwing;
import raven.toast.Notifications;
import utils.Enum.TypeDatMon_Branch;
import view.GD_DatMon;
import static utils.AppUtils.*;
import view.Form_GhiChu;

/**
 *
 * @author Laptop
 */
public class OrderItem_forUIDatMon extends javax.swing.JPanel {

    /**
     * Creates new form OrderItem
     */
//    Text or Button
    private String type = "TEXT";
    private String[] data;
    private DecimalFormat tien_format = new DecimalFormat("###,###.0 VNĐ");
    private Double tongTien = 0.0;
    private Double gia;
    private ArrayList<Mon> orders;
    private GD_DatMon datMon;
    private boolean initialized = false;
    private List<ChiTietHoaDon> details;
    private Mon mon;
    private String type_orderItem = "NONE";
    private ArrayList<Mon> list_MonHuy; //duccuong1609 : danh sách món đã gửi bếp nhưng lại hủy (case thêm món, case đặt trước)
    private List<OrderItem_forUIDatMon> listPreOrder; //duccuong1609 : cái này giữ lại cái danh sách cũ tại có món nó preload và có món mới thêm
    private List<Integer> list_quantity;

    public OrderItem_forUIDatMon(GD_DatMon datMon, Mon mon, int width, int index, String[] data, ArrayList<Mon> orders) {
        this.data = data;
        this.gia = mon.getGiaBan();
        this.orders = orders;
        this.datMon = datMon;
        this.mon = mon;
        this.details = datMon.getDetails();
        this.list_quantity = datMon.getList_quantity();
        initComponents();
        setBackground(index % 2 != 0 ? new Color(83, 86, 99) : new Color(31, 29, 43));
        setPreferredSize(new Dimension(width, 50));
        push(data);

        soLuong.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                checkTextField();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkTextField();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Sự kiện này không được sử dụng cho JTextField
                checkTextField();
            }
        });
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialized = true;
            }
        });
        timer.setRepeats(false);
        timer.start();
        Notifications.getInstance();
        FlatIntelliJLaf.setup();
    }

    public ArrayList<Integer> getListQuantity() {
        ArrayList<Integer> listQuantity = new ArrayList<Integer>();
        if (datMon.getPanelOrder().getComponentCount() != 0) {
            for (int i = 0; i < datMon.getPanelOrder().getComponentCount(); i++) {
                OrderItem_forUIDatMon item = (OrderItem_forUIDatMon) datMon.getPanelOrder().getComponent(i);
                JTextField quantity = (JTextField) item.panelRound1.getComponent(0);
                try {
                    int SL = Integer.parseInt(quantity.getText().trim());
                    listQuantity.add(SL);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return listQuantity;
    }

    ;
    
    public void updateTongTien() {
        double tong = 0.0;
        for (int i = 0; i < orders.size(); i++) {
            tong += orders.get(i).getGiaBan() * datMon.getList_quantity().get(i);
        }
        if (tong != 0) {
            datMon.setLabelTongTien(tien_format.format(tong));
        } else {
            datMon.setLabelTongTien("0,0 VNĐ");
        }
    }

    public void setType(String type) {
        this.type = type;
        push(data);
    }

    public void push(String[] data) {
        tenMon.setText(data[0]);
        soLuong.setText(data[1]);
        donGia.setText(data[2]);
        setLastItem(data[3]);
    }

    private void setLastItem(String data) {
        IconFontSwing.register(FontAwesome.getIconFont());
        huy.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH, 30, Color.white));
        if (type_orderItem.equals("PRE_LOAD")) {
            huy.setIcon(IconFontSwing.buildIcon(FontAwesome.SHOPPING_CART, 30, Color.white));
        }
        increase.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS, 15, Color.white));
        decrease.setIcon(IconFontSwing.buildIcon(FontAwesome.MINUS, 15, Color.white));
        ghi.setIcon(IconFontSwing.buildIcon(FontAwesome.PENCIL, 30, Color.white));
    }

//    public OrderItem(int index) {
//        initComponents();
//    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tenMon = new javax.swing.JLabel();
        panelRound1 = new component.PanelRound();
        soLuong = new javax.swing.JTextField();
        increase = new javax.swing.JLabel();
        decrease = new javax.swing.JLabel();
        donGia = new javax.swing.JLabel();
        ghi = new javax.swing.JLabel();
        huy = new javax.swing.JLabel();

        setLayout(new java.awt.GridLayout(1, 0));

        tenMon.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        tenMon.setForeground(new java.awt.Color(255, 255, 255));
        tenMon.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 10, 0, 0));
        add(tenMon);

        panelRound1.setBackground(new java.awt.Color(255, 255, 255,0));
        panelRound1.setLayout(new java.awt.GridLayout(1, 0));

        soLuong.setBackground(new java.awt.Color(204, 255, 255,0));
        soLuong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        soLuong.setForeground(new java.awt.Color(255, 255, 255));
        soLuong.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        soLuong.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 0, 0));
        soLuong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                soLuongMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                soLuongMouseExited(evt);
            }
        });
        soLuong.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                soLuongInputMethodTextChanged(evt);
            }
        });
        soLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                soLuongActionPerformed(evt);
            }
        });
        panelRound1.add(soLuong);

        increase.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        increase.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                increaseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                increaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                increaseMouseExited(evt);
            }
        });
        panelRound1.add(increase);

        decrease.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        decrease.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                decreaseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                decreaseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                decreaseMouseExited(evt);
            }
        });
        panelRound1.add(decrease);

        add(panelRound1);

        donGia.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        donGia.setForeground(new java.awt.Color(255, 255, 255));
        donGia.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 2, 0, 0));
        add(donGia);

        ghi.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ghi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ghiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                ghiMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                ghiMouseExited(evt);
            }
        });
        add(ghi);

        huy.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        huy.setForeground(new java.awt.Color(255, 255, 255));
        huy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        huy.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                huyMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                huyMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                huyMouseExited(evt);
            }
        });
        add(huy);
    }// </editor-fold>//GEN-END:initComponents
    public void checkTextField() {
        try {
            if (initialized) {
                if (!soLuong.getText().trim().equals("")) {
                    int quantity = Integer.parseInt(soLuong.getText().trim());
                    datMon.setList_quantity(getListQuantity());
                    donGia.setText(tien_format.format(gia * quantity));
                    updateTongTien();
                }
            }
        } catch (Exception e) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "SỐ LƯỢNG NHẬP VÀO PHẢI LÀ SỐ !");
            soLuong.requestFocus();
        }
    }

    ;
    private void huyMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_huyMouseExited
        // TODO add your handling code here:
        //        thanhTien.setFont(new Font("Jetbrains Mono", Font.BOLD, 14));
        IconFontSwing.register(FontAwesome.getIconFont());
        if (type_orderItem.equals("PRELOAD")) {
            huy.setIcon(IconFontSwing.buildIcon(FontAwesome.SHOPPING_CART, 30, Color.white));
        } else {
            huy.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH, 30, Color.white));
        }
    }//GEN-LAST:event_huyMouseExited

    private void huyMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_huyMouseEntered
        // TODO add your handling code here:
        //        thanhTien.setFont(new Font("Jetbrains Mono", Font.BOLD, 20));
        IconFontSwing.register(FontAwesome.getIconFont());
        if (type_orderItem.equals("PRELOAD")) {
            huy.setIcon(IconFontSwing.buildIcon(FontAwesome.SHOPPING_CART, 30, new Color(234, 124, 105)));
        } else {
            huy.setIcon(IconFontSwing.buildIcon(FontAwesome.TRASH, 30, new Color(234, 124, 105)));
        }
    }//GEN-LAST:event_huyMouseEntered

    private void huyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_huyMouseClicked
        // TODO add your handling code here:
        if (datMon.getBranch().equals(TypeDatMon_Branch.DAT_TRUOC_MON)) {
            datMon.getList_quantity().remove(datMon.getOrders().indexOf(mon));
            update_PanelOrder(true);
            if (type_orderItem.equals("PRELOAD")) {
                datMon.getList_CancelFood().add(mon);
            }
        }
        if (datMon.getBranch().equals(TypeDatMon_Branch.THEMMON)) {
            if (!type_orderItem.equals("PRELOAD")) {
                update_PanelOrder(true);
            } else {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "Bạn không thể thay đổi món đã đặt");
            }
        }
        if (datMon.getBranch().equals(TypeDatMon_Branch.DATMON)) {
            datMon.getList_quantity().remove(datMon.getOrders().indexOf(mon));
            listPreOrder = new ArrayList<>();
            update_PanelOrder(true);
        }
    }//GEN-LAST:event_huyMouseClicked

    private void decreaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseMouseExited
        // TODO add your handling code here:
        decrease.setIcon(IconFontSwing.buildIcon(FontAwesome.MINUS, 15, Color.white));
    }//GEN-LAST:event_decreaseMouseExited

    private void decreaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseMouseEntered
        // TODO add your handling code here:
        decrease.setIcon(IconFontSwing.buildIcon(FontAwesome.MINUS, 15, new Color(234, 124, 105)));
    }//GEN-LAST:event_decreaseMouseEntered

    private void decreaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_decreaseMouseClicked
        // TODO add your handling code here:
        decrease.setIcon(IconFontSwing.buildIcon(FontAwesome.MINUS, 15, new Color(234, 124, 105)));
        if (datMon.getBranch().equals(TypeDatMon_Branch.THEMMON) && type_orderItem.equals("PRELOAD")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "Bạn không thể thay đổi món đã đặt");
        } else {
            try {
                int quantity = Integer.parseInt(soLuong.getText().trim());
                if (quantity > 1) {
                    quantity--;
                }
                soLuong.setText(Integer.toString(quantity));
                tongTien = quantity * gia;
                donGia.setText(tien_format.format(tongTien));
                datMon.setList_quantity(getListQuantity());
                updateTongTien();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_decreaseMouseClicked

    private void increaseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseMouseExited
        // TODO add your handling code here:
        increase.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS, 15, Color.white));
    }//GEN-LAST:event_increaseMouseExited

    private void increaseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseMouseEntered
        // TODO add your handling code here:
        increase.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS, 15, new Color(234, 124, 105)));
    }//GEN-LAST:event_increaseMouseEntered

    private void increaseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_increaseMouseClicked
        // TODO add your handling code here:
        increase.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS, 15, new Color(234, 124, 105)));
        if (datMon.getBranch().equals(TypeDatMon_Branch.THEMMON) && type_orderItem.equals("PRELOAD")) {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "Bạn không thể thay đổi món đã đặt");
        } else {
            try {
                int quantity = Integer.parseInt(soLuong.getText().trim());
                quantity++;
                soLuong.setText(Integer.toString(quantity));
                tongTien = quantity * gia;
                donGia.setText(tien_format.format(tongTien));
                datMon.setList_quantity(getListQuantity());
                updateTongTien();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_increaseMouseClicked

    private void soLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_soLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_soLuongActionPerformed

    private void soLuongInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_soLuongInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_soLuongInputMethodTextChanged

    private void soLuongMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_soLuongMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_soLuongMouseExited

    private void soLuongMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_soLuongMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_soLuongMouseEntered

    private void ghiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ghiMouseClicked
        // TODO add your handling code here:
        JFrame jFrame = new JFrame();
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Form_GhiChu form_ghiChu = new Form_GhiChu(jFrame, this);
        jFrame.add(form_ghiChu, BorderLayout.CENTER);
        jFrame.setBackground(new Color(0, 0, 0, 0));
        FadeEffect.fadeInFrame(jFrame, 8, 0.1f);
        jFrame.setVisible(true);
    }//GEN-LAST:event_ghiMouseClicked

    private void ghiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ghiMouseEntered
        // TODO add your handling code here:
        
        if (type_orderItem.equals("PRELOAD")){
            ghi.setIcon(IconFontSwing.buildIcon(FontAwesome.BOOKMARK, 30, new Color(234, 124, 105)));
        }
        else{
            ghi.setIcon(IconFontSwing.buildIcon(FontAwesome.PENCIL, 30, new Color(234, 124, 105)));
        }
    }//GEN-LAST:event_ghiMouseEntered

    private void ghiMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ghiMouseExited
        // TODO add your handling code here:
        
        if (type_orderItem.equals("PRELOAD")){
            ghi.setIcon(IconFontSwing.buildIcon(FontAwesome.BOOKMARK, 30, Color.white));
        }else{
            ghi.setIcon(IconFontSwing.buildIcon(FontAwesome.PENCIL, 30, Color.white));
        }
    }//GEN-LAST:event_ghiMouseExited
    public void update_PanelOrder(boolean update) {
        if (update) {
            ArrayList<Mon> replace_orders = new ArrayList<Mon>();

            int size = listPreOrder.size();
            for (int i = 0; i < orders.size(); i++) {
                if (datMon.getBranch().equals(TypeDatMon_Branch.THEMMON)) {
                    if (i < size) {
                        OrderItem_forUIDatMon item = listPreOrder.get(i);
                        if (item.getType_orderItem().equals("PRELOAD")) {
                            replace_orders.add(orders.get(i));
                        } else {
                            if (!orders.get(i).getTenMon().equals(mon.getTenMon())) {
                                replace_orders.add(orders.get(i));
                            }
                        }
                    } else {
                        if (!orders.get(i).getTenMon().equals(mon.getTenMon())) {
                            replace_orders.add(orders.get(i));
                        }
                    }
                } else {
                    if (!orders.get(i).getTenMon().equals(mon.getTenMon())) {
                        replace_orders.add(orders.get(i));
                    }
                }
            }
            orders = replace_orders;
            datMon.setOrders(orders);
            datMon.getPanelOrder().removeAll();

            for (int i = 0; i < orders.size(); i++) {
                String[] title = new String[]{orders.get(i).getTenMon(), datMon.getList_quantity().get(i).toString(), tien_format.format(orders.get(i).getGiaBan() * datMon.getList_quantity().get(i)), ""};
                OrderItem_forUIDatMon item = new OrderItem_forUIDatMon(datMon, orders.get(i), datMon.getPanelOrder().getWidth(), i + 1, title, orders);
                for (OrderItem_forUIDatMon order_item : listPreOrder) {
                    if (item.getTenMon().getText().trim().equals(order_item.getTenMon().getText().trim())) {
                        if (datMon.getBranch().equals(TypeDatMon_Branch.THEMMON)) {
                            item.setType_orderItem("PRELOAD");
                        }
                    }
                }
                item.setListPreOrder(listPreOrder);
                datMon.getPanelOrder().add(item);
            }

            datMon.setList_quantity(getListQuantity());

            for (int i = 0; i < datMon.getFoodList().getComponentCount(); i++) {
                Food item = (Food) datMon.getFoodList().getComponent(i);
                item.setOrders(replace_orders);
                item.setList_Quantity(getListQuantity());
            }
            updateTongTien();
            datMon.getPanelOrder().revalidate();
            datMon.getPanelOrder().repaint();
        }
    }

    public void setType_orderItem(String type_orderItem) {
        this.type_orderItem = type_orderItem;
        if (type_orderItem.equals("PRELOAD")) {
            huy.setIcon(IconFontSwing.buildIcon(FontAwesome.SHOPPING_CART, 30, Color.white));
            ghi.setIcon(IconFontSwing.buildIcon(FontAwesome.BOOKMARK, 30, Color.white));
            soLuong.setEditable(false);
        }
    }

    public List<OrderItem_forUIDatMon> getListPreOrder() {
        return listPreOrder;
    }

    public void setListPreOrder(List<OrderItem_forUIDatMon> listPreOrder) {
        this.listPreOrder = listPreOrder;
    }

    public JLabel getTenMon() {
        return tenMon;
    }

    public String getType_orderItem() {
        return type_orderItem;
    }

    public Mon getMon() {
        return mon;
    }

    public GD_DatMon getDatMon() {
        return datMon;
    }
    
    public int getSoLuong(){
        return Integer.parseInt(soLuong.getText());
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel decrease;
    private javax.swing.JLabel donGia;
    private javax.swing.JLabel ghi;
    private javax.swing.JLabel huy;
    private javax.swing.JLabel increase;
    private component.PanelRound panelRound1;
    private javax.swing.JTextField soLuong;
    private javax.swing.JLabel tenMon;
    // End of variables declaration//GEN-END:variables

}
