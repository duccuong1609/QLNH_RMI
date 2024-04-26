/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import LIB.FadeEffect;
import com.formdev.flatlaf.FlatIntelliJLaf;
import component.Food;
import component.Loading;
import component.MyButton;
import component.OrderItem_forUIDatMon;
import component.RoundJTextField;
import component.ScrollBarCustom;
import component.WrapLayout;
import dao.IBanDAO;
import dao.IChiTietHoaDonDAO;
import dao.IHoaDonDAO;
import dao.IMonDAO;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Mon;
import entity.NhanVien;
import icon.FontAwesome;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;
import javax.swing.Timer;
import jiconfont.swing.IconFontSwing;
import raven.toast.Notifications;
import utils.AppUtils;
import utils.Enum.DatMon_ThemMon;
import utils.Enum.TypeDatMon_Branch;
import utils.ModelColor;
import static utils.AppUtils.*;

/**
 *
 * @author Laptop
 */
public class GD_DatMon extends javax.swing.JPanel {

    /**
     * Creates new form GD_DatMon
     */
    private JPanel main;
    private List<Mon> mons;
    private List<Mon> beverages;
    private List<Mon> popular;
    private List<Mon> others;
    private Ban ban;
    private ArrayList<Mon> orders;
    private ArrayList<Integer> list_quantity = new ArrayList<Integer>();
    private NhanVien nv;
    private String type_datMon;
    private DatMon_ThemMon loai;
    private TypeDatMon_Branch branch = TypeDatMon_Branch.THEMMON;//duccuong1609 : phân loại luồng đi vào (đặt món,sửa món)
    private DecimalFormat tien_format = new DecimalFormat("###,###.0 VNĐ");
    private HoaDon hoaDon;
    private GD_Ban gD_Ban;//duccuong1609 : load thẳng vào nếu có 
    private GD_DatBanTaiCho gd_qlDatMon;//load thẳng vào nếu có 
    private GD_DatBanTruoc gd_datBan;//load thẳng vào nếu có 
    private List<ChiTietHoaDon> details; //ds chi tiết hóa đơn (luồng thêm món)
    private GD_DatMon gd_mon = this;
    private List<OrderItem_forUIDatMon> listPreOrderItem;
    private List<Mon> list_CancelFood = new ArrayList<>();
//    NDK: Di chuyển lên đây nè
    private IHoaDonDAO hoaDonDAO = HOADONDAO;
    private IBanDAO banDAO = BANDAO;
    private IChiTietHoaDonDAO chitietDAO = CHITIETHOADONDAO;
    private IMonDAO monDAO = MONDAO;
    private boolean back_toUI_DatBan;
    private boolean guiBep;
    private boolean preLoad = false;
    private List<String> ghiChus;

    public GD_DatMon(JPanel main, Ban ban, utils.Enum.DatMon_ThemMon loai) throws RemoteException {
        this.nv = AppUtils.NHANVIEN;
        this.main = main;
        this.ban = ban;
        this.loai = loai;
        this.back_toUI_DatBan = false;
        this.guiBep = false;
        initComponents();
        setVisible(true);
        IconFontSwing.register(FontAwesome.getIconFont());
        btnSearch.setIcon(IconFontSwing.buildIcon(FontAwesome.SEARCH, 30, Color.WHITE));
        FoodList.setLayout(new WrapLayout(FlowLayout.CENTER, 20, 20));
        scrollFoodList.setVerticalScrollBar(new ScrollBarCustom());
        scrollFoodList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        Scroll_Order.setVerticalScrollBar(new ScrollBarCustom());
        Scroll_Order.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panelGradient1.addColor(new ModelColor(new Color(31, 29, 43), 0f), new ModelColor(new Color(31, 29, 43, 0), 0.5f), new ModelColor(new Color(31, 29, 43, 0), 1f));
        IconFontSwing.register(FontAwesome.getIconFont());
        btnDD.setIcon(IconFontSwing.buildIcon(FontAwesome.ANGLE_DOUBLE_DOWN, 20, Color.WHITE));
        btnDU.setIcon(IconFontSwing.buildIcon(FontAwesome.ANGLE_DOUBLE_UP, 20, Color.WHITE));
        btnUp.setIcon(IconFontSwing.buildIcon(FontAwesome.ANGLE_DOUBLE_DOWN, 20, Color.WHITE));
        btnHelpCaculator.setIcon(IconFontSwing.buildIcon(FontAwesome.QUESTION, 20, Color.WHITE));
        btnDown.setIcon(IconFontSwing.buildIcon(FontAwesome.ANGLE_DOUBLE_UP, 20, Color.WHITE));
        btnBack.setIcon(IconFontSwing.buildIcon(FontAwesome.ANGLE_LEFT, 20, Color.WHITE));
        btnNV.setIcon(IconFontSwing.buildIcon(FontAwesome.USER, 20, Color.WHITE));
        btnGhiChu.setIcon(IconFontSwing.buildIcon(FontAwesome.BOOK, 20, Color.WHITE));
        btnKhuyenMai.setIcon(IconFontSwing.buildIcon(FontAwesome.GIFT, 20, Color.WHITE));
        btnTime.setIcon(IconFontSwing.buildIcon(FontAwesome.CLOCK_O, 20, Color.WHITE));
        PanelOrder.setLayout(new WrapLayout(FlowLayout.CENTER, 0, 0));
        banTextField.setText(ban.getMaBan());
        banTextField.setEditable(false);
        labelTongTien.setText("0,0 VNĐ");
        nhanVienName.setText(nv.getHoTen());
        Notifications.getInstance();
        FlatIntelliJLaf.setup();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        myButton5 = new component.MyButton();
        list1 = new java.awt.List();
        panelMon = new component.PanelRound();
        btnHayDung = new component.MyButton();
        btnMonAn = new component.MyButton();
        btnDoUong = new component.MyButton();
        btnKhac = new component.MyButton();
        jTextFieldSearch = new RoundJTextField(10);
        btnSearch = new component.MyButton();
        panelMenuMon = new component.PanelRound();
        panelRound1 = new component.PanelRound();
        btnDD = new component.MyButton();
        btnDU = new component.MyButton();
        scrollFoodList = new javax.swing.JScrollPane();
        FoodList = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        panelOrder = new component.PanelRound();
        btnBack = new component.MyButton();
        banTextField = new RoundJTextField(10);
        btnNV = new component.MyButton();
        btnGhiChu = new component.MyButton();
        panelRound2 = new component.PanelRound();
        btnThem = new component.MyButton();
        btnKhuyenMai = new component.MyButton();
        btnTime = new component.MyButton();
        btnUp = new component.MyButton();
        btnDown = new component.MyButton();
        panelRound3 = new component.PanelRound();
        btnGuiBep = new component.MyButton();
        btnHuyBo = new component.MyButton();
        btnTinhTien = new component.MyButton();
        btnCat = new component.MyButton();
        jLabel1 = new javax.swing.JLabel();
        labelTongTien = new javax.swing.JLabel();
        panelRound4 = new component.PanelRound();
        panelRound5 = new component.PanelRound();
        panelGradient1 = new component.PanelGradient();
        nhanVienName = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btnHelpCaculator = new component.MyButton();
        TABLE_ORDER = new component.PanelRound();
        HEADER_ORDER = new component.PanelRound();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Scroll_Order = new javax.swing.JScrollPane();
        PanelOrder = new javax.swing.JPanel();

        myButton5.setText("myButton5");

        setBackground(new java.awt.Color(31, 29, 43));

        panelMon.setBackground(new java.awt.Color(31, 29, 43));

        btnHayDung.setForeground(new java.awt.Color(255, 255, 255));
        btnHayDung.setText("Hay Dùng");
        btnHayDung.setColor(new java.awt.Color(83, 86, 99));
        btnHayDung.setColorClick(new java.awt.Color(234, 124, 105));
        btnHayDung.setColorOver(new java.awt.Color(234, 124, 105));
        btnHayDung.setFont(utils.AppUtils.getFont(14f, _BOLD_)
        );
        btnHayDung.setRadius(10);
        btnHayDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHayDungActionPerformed(evt);
            }
        });

        btnMonAn.setForeground(new java.awt.Color(255, 255, 255));
        btnMonAn.setText("Món Ăn");
        btnMonAn.setColor(new java.awt.Color(83, 86, 99));
        btnMonAn.setColorClick(new java.awt.Color(234, 124, 105));
        btnMonAn.setColorOver(new java.awt.Color(234, 124, 105));
        btnMonAn.setFont(utils.AppUtils.getFont(14f, _BOLD_)
        );
        btnMonAn.setRadius(10);
        btnMonAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMonAnActionPerformed(evt);
            }
        });

        btnDoUong.setForeground(new java.awt.Color(255, 255, 255));
        btnDoUong.setText("Đồ Uống");
        btnDoUong.setColor(new java.awt.Color(83, 86, 99));
        btnDoUong.setColorClick(new java.awt.Color(234, 124, 105));
        btnDoUong.setColorOver(new java.awt.Color(234, 124, 105));
        btnDoUong.setFont(utils.AppUtils.getFont(14f, _BOLD_)
        );
        btnDoUong.setRadius(10);
        btnDoUong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDoUongActionPerformed(evt);
            }
        });

        btnKhac.setForeground(new java.awt.Color(255, 255, 255));
        btnKhac.setText("Khác");
        btnKhac.setColor(new java.awt.Color(83, 86, 99));
        btnKhac.setColorClick(new java.awt.Color(234, 124, 105));
        btnKhac.setColorOver(new java.awt.Color(234, 124, 105));
        btnKhac.setFont(utils.AppUtils.getFont(14f, _BOLD_)
        );
        btnKhac.setRadius(10);
        btnKhac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKhacActionPerformed(evt);
            }
        });

        jTextFieldSearch.setFont(utils.AppUtils.getFont(16f, _ITALIC_)
        );
        jTextFieldSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldSearchActionPerformed(evt);
            }
        });
        jTextFieldSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldSearchKeyReleased(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(83, 86, 99));
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setColor(new java.awt.Color(83, 86, 99));
        btnSearch.setColorClick(new java.awt.Color(234, 124, 105));
        btnSearch.setColorOver(new java.awt.Color(234, 124, 105));
        btnSearch.setFont(utils.AppUtils.getFont(16f, _NORMAL_)
        );
        btnSearch.setRadius(10);

        javax.swing.GroupLayout panelMonLayout = new javax.swing.GroupLayout(panelMon);
        panelMon.setLayout(panelMonLayout);
        panelMonLayout.setHorizontalGroup(
            panelMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMonLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMonLayout.createSequentialGroup()
                        .addComponent(btnHayDung, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(btnMonAn, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(btnDoUong, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                        .addGap(10, 10, 10)
                        .addComponent(btnKhac, javax.swing.GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE))
                    .addGroup(panelMonLayout.createSequentialGroup()
                        .addComponent(jTextFieldSearch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        panelMonLayout.setVerticalGroup(
            panelMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMonLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHayDung, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMonAn, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDoUong, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKhac, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(panelMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextFieldSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelMenuMon.setBackground(new java.awt.Color(31, 29, 43));

        panelRound1.setBackground(new java.awt.Color(83, 86, 99));

        btnDD.setBackground(new java.awt.Color(31, 29, 43));
        btnDD.setForeground(new java.awt.Color(255, 255, 255));
        btnDD.setColor(new java.awt.Color(31, 29, 43));
        btnDD.setColorClick(new java.awt.Color(234, 124, 105));
        btnDD.setColorOver(new java.awt.Color(234, 124, 105));
        btnDD.setRadius(50);
        btnDD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDDActionPerformed(evt);
            }
        });

        btnDU.setBackground(new java.awt.Color(31, 29, 43));
        btnDU.setForeground(new java.awt.Color(255, 255, 255));
        btnDU.setColor(new java.awt.Color(31, 29, 43));
        btnDU.setColorClick(new java.awt.Color(234, 124, 105));
        btnDU.setColorOver(new java.awt.Color(234, 124, 105));
        btnDU.setRadius(50);
        btnDU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDUActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addContainerGap(321, Short.MAX_VALUE)
                .addComponent(btnDD, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(btnDU, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(320, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDD, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
            .addComponent(btnDU, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        btnDU.getAccessibleContext().setAccessibleDescription("");

        scrollFoodList.setBackground(new java.awt.Color(83, 86, 99));

        FoodList.setBackground(new java.awt.Color(83, 86, 99));
        FoodList.setFont(utils.AppUtils.getFont(16f, _NORMAL_)
        );
        FoodList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                FoodListMouseEntered(evt);
            }
        });

        javax.swing.GroupLayout FoodListLayout = new javax.swing.GroupLayout(FoodList);
        FoodList.setLayout(FoodListLayout);
        FoodListLayout.setHorizontalGroup(
            FoodListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 919, Short.MAX_VALUE)
        );
        FoodListLayout.setVerticalGroup(
            FoodListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1121, Short.MAX_VALUE)
        );

        scrollFoodList.setViewportView(FoodList);

        javax.swing.GroupLayout panelMenuMonLayout = new javax.swing.GroupLayout(panelMenuMon);
        panelMenuMon.setLayout(panelMenuMonLayout);
        panelMenuMonLayout.setHorizontalGroup(
            panelMenuMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuMonLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelMenuMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollFoodList, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
        );
        panelMenuMonLayout.setVerticalGroup(
            panelMenuMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMenuMonLayout.createSequentialGroup()
                .addComponent(scrollFoodList)
                .addGap(0, 0, 0)
                .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        jPanel1.setBackground(new java.awt.Color(31, 29, 43));

        panelOrder.setBackground(new java.awt.Color(31, 29, 43));

        btnBack.setColor(new java.awt.Color(83, 86, 99));
        btnBack.setColorClick(new java.awt.Color(234, 124, 105));
        btnBack.setColorOver(new java.awt.Color(234, 124, 105));
        btnBack.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnBack.setRadius(10);
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        banTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        banTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        banTextField.setText("101");
        banTextField.setEditable(false);
        banTextField.setPreferredSize(new java.awt.Dimension(150, 22));
        banTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banTextFieldActionPerformed(evt);
            }
        });

        btnNV.setForeground(new java.awt.Color(255, 255, 255));
        btnNV.setText("1");
        btnNV.setColor(new java.awt.Color(83, 86, 99));
        btnNV.setColorClick(new java.awt.Color(234, 124, 105));
        btnNV.setColorOver(new java.awt.Color(234, 124, 105));
        btnNV.setFont(utils.AppUtils.getFont(15f, _BOLD_)
        );
        btnNV.setIconTextGap(15);
        btnNV.setRadius(10);
        btnNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNVActionPerformed(evt);
            }
        });

        btnGhiChu.setColor(new java.awt.Color(83, 86, 99));
        btnGhiChu.setColorClick(new java.awt.Color(234, 124, 105));
        btnGhiChu.setColorOver(new java.awt.Color(234, 124, 105));
        btnGhiChu.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnGhiChu.setRadius(10);

        javax.swing.GroupLayout panelOrderLayout = new javax.swing.GroupLayout(panelOrder);
        panelOrder.setLayout(panelOrderLayout);
        panelOrderLayout.setHorizontalGroup(
            panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrderLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnBack, javax.swing.GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(banTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnNV, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(btnGhiChu, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        panelOrderLayout.setVerticalGroup(
            panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOrderLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnNV, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addGroup(panelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(banTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGhiChu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelRound2.setBackground(new java.awt.Color(31, 29, 43));

        btnThem.setBackground(new java.awt.Color(83, 86, 99));
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm Món Khác");
        btnThem.setColor(new java.awt.Color(83, 86, 99));
        btnThem.setColorClick(new java.awt.Color(234, 124, 105));
        btnThem.setColorOver(new java.awt.Color(234, 124, 105));
        btnThem.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnThem.setRadius(10);
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnKhuyenMai.setBackground(new java.awt.Color(83, 86, 99));
        btnKhuyenMai.setForeground(new java.awt.Color(255, 255, 255));
        btnKhuyenMai.setColor(new java.awt.Color(83, 86, 99));
        btnKhuyenMai.setColorClick(new java.awt.Color(234, 124, 105));
        btnKhuyenMai.setColorOver(new java.awt.Color(234, 124, 105));
        btnKhuyenMai.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnKhuyenMai.setRadius(10);

        btnTime.setBackground(new java.awt.Color(83, 86, 99));
        btnTime.setForeground(new java.awt.Color(255, 255, 255));
        btnTime.setColor(new java.awt.Color(83, 86, 99));
        btnTime.setColorClick(new java.awt.Color(234, 124, 105));
        btnTime.setColorOver(new java.awt.Color(234, 124, 105));
        btnTime.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnTime.setRadius(10);
        btnTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimeActionPerformed(evt);
            }
        });

        btnUp.setBackground(new java.awt.Color(83, 86, 99));
        btnUp.setForeground(new java.awt.Color(255, 255, 255));
        btnUp.setColor(new java.awt.Color(83, 86, 99));
        btnUp.setColorClick(new java.awt.Color(234, 124, 105));
        btnUp.setColorOver(new java.awt.Color(234, 124, 105));
        btnUp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUp.setRadius(50);

        btnDown.setBackground(new java.awt.Color(83, 86, 99));
        btnDown.setForeground(new java.awt.Color(255, 255, 255));
        btnDown.setColor(new java.awt.Color(83, 86, 99));
        btnDown.setColorClick(new java.awt.Color(234, 124, 105));
        btnDown.setColorOver(new java.awt.Color(234, 124, 105));
        btnDown.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnDown.setRadius(50);

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(btnKhuyenMai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10)
                .addComponent(btnTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnUp, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(btnDown, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
            .addComponent(btnKhuyenMai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDown, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnUp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelRound3.setBackground(new java.awt.Color(83, 86, 99));
        panelRound3.setRoundBottomLeft(10);
        panelRound3.setRoundBottomRight(10);
        panelRound3.setRoundTopLeft(10);
        panelRound3.setRoundTopRight(10);

        btnGuiBep.setForeground(new java.awt.Color(255, 255, 255));
        btnGuiBep.setText("GỬI BẾP");
        btnGuiBep.setColor(new java.awt.Color(31, 29, 43));
        btnGuiBep.setColorClick(new java.awt.Color(234, 124, 105));
        btnGuiBep.setColorOver(new java.awt.Color(234, 124, 105));
        btnGuiBep.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnGuiBep.setRadius(20);
        btnGuiBep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuiBepActionPerformed(evt);
            }
        });

        btnHuyBo.setForeground(new java.awt.Color(255, 255, 255));
        btnHuyBo.setText("HỦY BỎ");
        btnHuyBo.setColor(new java.awt.Color(31, 29, 43));
        btnHuyBo.setColorClick(new java.awt.Color(234, 124, 105));
        btnHuyBo.setColorOver(new java.awt.Color(234, 124, 105));
        btnHuyBo.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnHuyBo.setRadius(20);
        btnHuyBo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyBoActionPerformed(evt);
            }
        });

        btnTinhTien.setForeground(new java.awt.Color(255, 255, 255));
        btnTinhTien.setText("TÍNH TIỀN");
        btnTinhTien.setColor(new java.awt.Color(31, 29, 43));
        btnTinhTien.setColorClick(new java.awt.Color(234, 124, 105));
        btnTinhTien.setColorOver(new java.awt.Color(234, 124, 105));
        btnTinhTien.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnTinhTien.setRadius(20);

        btnCat.setForeground(new java.awt.Color(255, 255, 255));
        btnCat.setText("CẤT VÀ THÊM");
        btnCat.setColor(new java.awt.Color(31, 29, 43));
        btnCat.setColorClick(new java.awt.Color(234, 124, 105));
        btnCat.setColorOver(new java.awt.Color(234, 124, 105));
        btnCat.setFont(utils.AppUtils.getFont(12f, _BOLD_)
        );
        btnCat.setRadius(20);
        btnCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatActionPerformed(evt);
            }
        });

        jLabel1.setFont(utils.AppUtils.getFont(12f, _NORMAL_)
        );
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TỔNG TIỀN");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        labelTongTien.setFont(utils.AppUtils.getFont(14f, _BOLD_)
        );
        labelTongTien.setForeground(new java.awt.Color(255, 255, 255));
        labelTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTongTien.setText("5000000 VND");

        panelRound4.setBackground(new java.awt.Color(83, 86, 99));

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );

        panelRound5.setBackground(new java.awt.Color(83, 86, 99));
        panelRound5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelGradient1.setBackground(new java.awt.Color(83, 86, 99));
        panelGradient1.setBorder(null);

        nhanVienName.setFont(utils.AppUtils.getFont(14f, _BOLD_)
        );
        nhanVienName.setForeground(new java.awt.Color(255, 255, 255));
        nhanVienName.setText("NGUYỄN ĐỨC CƯỜNG");

        jLabel8.setFont(utils.AppUtils.getFont(12f, _NORMAL_)
        );
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Nhân Viên Phục Vụ");

        javax.swing.GroupLayout panelGradient1Layout = new javax.swing.GroupLayout(panelGradient1);
        panelGradient1.setLayout(panelGradient1Layout);
        panelGradient1Layout.setHorizontalGroup(
            panelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradient1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nhanVienName, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelGradient1Layout.setVerticalGroup(
            panelGradient1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGradient1Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nhanVienName)
                .addGap(23, 23, 23))
        );

        panelRound5.add(panelGradient1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 250, 90));

        btnHelpCaculator.setBackground(new java.awt.Color(31, 29, 43));
        btnHelpCaculator.setForeground(new java.awt.Color(255, 255, 255));
        btnHelpCaculator.setColor(new java.awt.Color(31, 29, 43));
        btnHelpCaculator.setColorClick(new java.awt.Color(234, 124, 105));
        btnHelpCaculator.setColorOver(new java.awt.Color(234, 124, 105));
        btnHelpCaculator.setRadius(55);
        btnHelpCaculator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpCaculatorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(btnGuiBep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(btnHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(11, 11, 11))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addComponent(panelRound5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(btnTinhTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(15, 15, 15)
                        .addComponent(btnCat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, 0)
                        .addComponent(btnHelpCaculator, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelRound3Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnHelpCaculator, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelRound3Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelTongTien)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnCat, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTinhTien, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnGuiBep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuyBo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );

        TABLE_ORDER.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, null, new java.awt.Color(255, 255, 255), null, new java.awt.Color(255, 255, 255)));
        TABLE_ORDER.setRoundBottomLeft(10);
        TABLE_ORDER.setRoundBottomRight(10);
        TABLE_ORDER.setRoundTopLeft(10);
        TABLE_ORDER.setRoundTopRight(10);

        HEADER_ORDER.setBackground(new java.awt.Color(83, 86, 99));
        HEADER_ORDER.setRoundTopLeft(10);
        HEADER_ORDER.setRoundTopRight(10);
        HEADER_ORDER.setLayout(new java.awt.GridLayout(1, 4));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("  TÊN MÓN");
        HEADER_ORDER.add(jLabel4);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText(" SL");
        HEADER_ORDER.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("THÀNH TIỀN");
        HEADER_ORDER.add(jLabel6);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("GHI CHÚ");
        HEADER_ORDER.add(jLabel2);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("HỦY");
        HEADER_ORDER.add(jLabel7);

        Scroll_Order.setBorder(null);

        PanelOrder.setBackground(new java.awt.Color(83, 86, 99));
        PanelOrder.setFont(utils.AppUtils.getFont(16f, _NORMAL_)
        );

        javax.swing.GroupLayout PanelOrderLayout = new javax.swing.GroupLayout(PanelOrder);
        PanelOrder.setLayout(PanelOrderLayout);
        PanelOrderLayout.setHorizontalGroup(
            PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 597, Short.MAX_VALUE)
        );
        PanelOrderLayout.setVerticalGroup(
            PanelOrderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
        );

        Scroll_Order.setViewportView(PanelOrder);

        javax.swing.GroupLayout TABLE_ORDERLayout = new javax.swing.GroupLayout(TABLE_ORDER);
        TABLE_ORDER.setLayout(TABLE_ORDERLayout);
        TABLE_ORDERLayout.setHorizontalGroup(
            TABLE_ORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HEADER_ORDER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Scroll_Order, javax.swing.GroupLayout.DEFAULT_SIZE, 607, Short.MAX_VALUE)
        );
        TABLE_ORDERLayout.setVerticalGroup(
            TABLE_ORDERLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TABLE_ORDERLayout.createSequentialGroup()
                .addComponent(HEADER_ORDER, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Scroll_Order, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelOrder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(TABLE_ORDER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelOrder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(TABLE_ORDER, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelRound3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMenuMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panelMenuMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDoUongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDoUongActionPerformed
        // TODO add your handling code here:
        FoodList.removeAll();
        beverages = new ArrayList<Mon>();
        for (Mon mon : mons) {
            if (mon.getLoaiMon().getMaLoaiMon().equals("ML01")) {
                beverages.add(mon);
            }
        }
        for (Mon mon : beverages) {
            FoodList.add(new Food(this, mon, PanelOrder, mons, orders));
        }
        FoodList.revalidate();
    }//GEN-LAST:event_btnDoUongActionPerformed

    private void btnHayDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHayDungActionPerformed
        // TODO add your handling code here:
        GD_DatMon datmon = this;
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loading loading = new Loading();
                utils.AppUtils.setLoadingForTable(scrollFoodList, true, loading, FoodList);

                popular = new ArrayList<Mon>();

                try {
                    Map<Mon, Long> map = monDAO.findPopular();
                    for (Mon mon : map.keySet()) {
                        popular.add(mon);
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
                }

                FoodList.removeAll();
                for (Mon mon : popular) {
                    FoodList.add(new Food(datmon, mon, PanelOrder, mons, orders));
                }
                FoodList.revalidate();

                Timer hideTimer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        utils.AppUtils.setLoadingForTable(scrollFoodList, false, loading, FoodList);
                    }
                });
                hideTimer.setRepeats(false);
                hideTimer.start();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }//GEN-LAST:event_btnHayDungActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        // TODO add your handling code here:
        if (branch == TypeDatMon_Branch.DATMON) {
            gD_Ban.setGd_Datmon(this);
            AppUtils.setUI(main, () -> gD_Ban);
        }
        if (branch == TypeDatMon_Branch.THEMMON) {
            AppUtils.setUI(main, () -> gd_qlDatMon);
        }
        if (branch == TypeDatMon_Branch.DAT_TRUOC_MON) {
            if (back_toUI_DatBan == false) {
                AppUtils.setUI(main, () -> gd_qlDatMon);
            } else {
                AppUtils.setUI(main, () -> gd_datBan);
            }
        }
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNVActionPerformed
        // TODO add your handling code here:
        JFrame jFrame = new JFrame();
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Form_DoiSoLuongNguoi form_doisonguoi = new Form_DoiSoLuongNguoi(jFrame, this);
        jFrame.add(form_doisonguoi, BorderLayout.CENTER);
        jFrame.setBackground(new Color(0, 0, 0, 0));
        FadeEffect.fadeInFrame(jFrame, 8, 0.1f);
        jFrame.setVisible(true);
    }//GEN-LAST:event_btnNVActionPerformed

    private void banTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_banTextFieldActionPerformed

    private void btnDDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDDActionPerformed

    private void btnDUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDUActionPerformed

    private void btnTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTimeActionPerformed

    private void btnCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatActionPerformed
        try {
            // TODO add your handling code here:
            Create_OrUpdate_Order();
        } catch (RemoteException ex) {
            Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCatActionPerformed

    int index = 1;
    private void btnMonAnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMonAnActionPerformed
        // TODO add your handling code here:
        FoodList.removeAll();
        ArrayList<Mon> food = new ArrayList<Mon>();
        food.addAll(mons);
        if (beverages != null) {
            food.removeAll(beverages);
        } else {
            beverages = new ArrayList<Mon>();
            for (Mon mon : mons) {
                if (mon.getLoaiMon().getMaLoaiMon().equals("ML01")) {
                    beverages.add(mon);
                }
                if (mon.getLoaiMon().getMaLoaiMon().equals("ML06")) {
                    food.remove(mon);
                }
            }
            food.removeAll(beverages);
        }
        for (Mon mon : food) {
            FoodList.add(new Food(this, mon, PanelOrder, mons, orders));
        };
        FoodList.revalidate();
    }//GEN-LAST:event_btnMonAnActionPerformed

    private void btnHuyBoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyBoActionPerformed
        // TODO add your handling code here
        if (branch == TypeDatMon_Branch.DATMON) {
            gD_Ban.setGd_Datmon(this);
            AppUtils.setUI(main, () -> gD_Ban);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Hủy Thành Công");
        }
        if (branch == TypeDatMon_Branch.THEMMON) {
            AppUtils.setUI(main, () -> gd_qlDatMon);
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Hủy Thành Công");
        }
        if (branch == TypeDatMon_Branch.DAT_TRUOC_MON) {
            if (back_toUI_DatBan == false) {
                AppUtils.setUI(main, () -> gd_qlDatMon);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Hủy Thành Công");
            } else {
                AppUtils.setUI(main, () -> gd_datBan);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Hủy Thành Công");
            }
        }
    }//GEN-LAST:event_btnHuyBoActionPerformed

    private void btnHelpCaculatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpCaculatorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHelpCaculatorActionPerformed

    private void FoodListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FoodListMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_FoodListMouseEntered

    private void btnGuiBepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuiBepActionPerformed
        // TODO add your handling code here:
        if (!orders.isEmpty()) {
            if (branch.equals(TypeDatMon_Branch.DAT_TRUOC_MON)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "Món đặt trước sẽ được gửi bếp khi nhận bàn, Vui lòng chọn cất và Thêm !");
            } else {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Gửi Bếp Thành Công !");
                guiBep = true;
            }
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "Vui Lòng Chọn Ít Nhất 1 Món Ăn Để Gửi Bếp !");
        }

    }//GEN-LAST:event_btnGuiBepActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        JFrame jFrame = new JFrame();
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Form_ThemMonKhac form = new Form_ThemMonKhac(jFrame);
        jFrame.add(form, BorderLayout.CENTER);
        jFrame.setBackground(new Color(0, 0, 0, 0));
        FadeEffect.fadeInFrame(jFrame, 8, 0.1f);
        jFrame.setVisible(true);
    }//GEN-LAST:event_btnThemActionPerformed

    private void jTextFieldSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldSearchKeyReleased
        // TODO add your handling code here:
        GD_DatMon datMon = this;
        FoodList.removeAll();
        for (Mon mon : mons) {
            if (AppUtils.CheckContainsAbbreviation(mon.getTenMon(), jTextFieldSearch.getText().trim())) {
                FoodList.add(new Food(datMon, mon, PanelOrder, mons, orders));
            }
        };
        FoodList.repaint();
        FoodList.revalidate();
    }//GEN-LAST:event_jTextFieldSearchKeyReleased

    private void jTextFieldSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldSearchActionPerformed

    private void btnKhacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKhacActionPerformed
        // TODO add your handling code here:
        GD_DatMon datmon = this;
        Timer timer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loading loading = new Loading();
                utils.AppUtils.setLoadingForTable(scrollFoodList, true, loading, FoodList);

                others = new ArrayList<Mon>();
                try {
                    others = MONDAO.findOthers();
                } catch (RemoteException ex) {
                    Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
                }

                FoodList.removeAll();
                for (Mon mon : others) {
                    FoodList.add(new Food(datmon, mon, PanelOrder, mons, orders));
                }
                FoodList.revalidate();

                Timer hideTimer = new Timer(500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        utils.AppUtils.setLoadingForTable(scrollFoodList, false, loading, FoodList);
                    }
                });
                hideTimer.setRepeats(false);
                hideTimer.start();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }//GEN-LAST:event_btnKhacActionPerformed
    public void First_LoadData() {
        FoodList.removeAll();
        Loading loading = new Loading();
        utils.AppUtils.setLoadingForTable(scrollFoodList, true, loading, FoodList);
        FoodList.repaint();
        FoodList.revalidate();
        SwingWorker<List<Food>, Void> worker = new SwingWorker<List<Food>, Void>() {
            @Override
            protected List<Food> doInBackground() throws Exception {
                // Thực hiện công việc lâu dài ở đây
                List<Food> list = new ArrayList<>();
                orders = new ArrayList<Mon>();
                //lấy danh sách chi tiết hóa đơn từ luồng thêm món nhờ hóa đơn (từ ordercard --> đặt món)
                if (!branch.equals(TypeDatMon_Branch.DATMON)) {
                    details = chitietDAO.getListByHoaDon(hoaDon);
                    for (ChiTietHoaDon d : details) { //duccuong1609 : này load trước mấy chi tiết hóa đơn lên
                        orders.add(d.getMon());
                        list_quantity.add(d.getSoLuong());
                    }
                }
                mons = new ArrayList<Mon>();
                mons = monDAO.findService();
                for (Mon mon : mons) {
                    list.add(new Food(gd_mon, mon, PanelOrder, mons, orders));
                }
                displayPreOrder();
                return list;
            }

            @Override
            protected void done() {
                FoodList.removeAll();
                List<Food> list;

                try {
                    list = get();
                    utils.AppUtils.setLoadingForTable(scrollFoodList, false, loading, FoodList);
                    for (Food f : list) {
                        FoodList.add(f);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ExecutionException ex) {
                    Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        worker.execute();

    }

    //Hien thi danh sach mon da dat
    public void displayPreOrder() {
        if (!branch.equals(TypeDatMon_Branch.DATMON)) {
            Double total = 0.0;
            setSoLuong(hoaDon.getSoLuongNguoi());
            listPreOrderItem = new ArrayList<>();
            for (int i = 0; i < orders.size(); i++) {
                String[] title = new String[]{orders.get(i).getTenMon(), list_quantity.get(i).toString(), tien_format.format(orders.get(i).getGiaBan() * gd_mon.getList_quantity().get(i)), ""};
                OrderItem_forUIDatMon item = new OrderItem_forUIDatMon(gd_mon, orders.get(i), gd_mon.getPanelOrder().getWidth(), i + 1, title, orders);
                if (branch.equals(TypeDatMon_Branch.THEMMON)) {
                    item.setType_orderItem("PRELOAD");//có ở dưới data base load lên
                }
                listPreOrderItem.add(item);
                gd_mon.getPanelOrder().add(item);
                total += orders.get(i).getGiaBan() * list_quantity.get(i);
            }
            for (int i = 0; i < gd_mon.PanelOrder.getComponentCount(); i++) {
                OrderItem_forUIDatMon item = (OrderItem_forUIDatMon) gd_mon.PanelOrder.getComponent(i);
                item.setListPreOrder(listPreOrderItem);
            }
            labelTongTien.setText(tien_format.format(total));
        }
    }

    public void Create_OrUpdate_Order() throws RemoteException {
//  NDK: t di chuyển lên trên lúc tạo biến rồi
        if (!orders.isEmpty()) {
            if (guiBep == true) {
                using_for_DatMon();
                using_for_ThemMon();
            } else if (!branch.equals(TypeDatMon_Branch.DAT_TRUOC_MON)) {
                Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "Vui Lòng Chọn Gửi Bếp !");
            }
            using_for_DatTruocMon();
        } else {
            Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_RIGHT, 1500, "Vui Lòng Chọn Ít Nhất 1 Món Ăn Để Gửi Bếp !");
        }
    }

    public void using_for_DatMon() throws RemoteException {
        if (branch.equals(TypeDatMon_Branch.DATMON)) {
            Ban ban = (Ban) BANDAO.findById(this.ban.getMaBan(), Ban.class);
            hoaDon = new HoaDon(nv, LocalDateTime.now(), ban, utils.Enum.LoaiTrangThaiHoaDon.CHUA_THANH_TOAN);
            hoaDon.setSoLuongNguoi(getSoLuong());
            if (loai.equals(DatMon_ThemMon.DATMON)) {
                List<ChiTietHoaDon> list = new ArrayList<>();
                for (int i = 0; i < orders.size(); i++) {
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon(orders.get(i), hoaDon, list_quantity.get(i));
                    list.add(chiTiet);
                    if (ghiChus.size() != -1) {
                        chiTiet.setGhiChu(ghiChus.get(i));
                    }
                }
                hoaDon.setChiTietHoaDon(list);
                HOADONDAO.insertHoaDon(hoaDon);
            }
            ban.setTrangThai(utils.Enum.LoaiTrangThai.BAN_CO_KHACH);
            banDAO.update(ban);
            AppUtils.setUI(main, () -> {
                try {
                    return new GD_DatBanTaiCho(main, nv);
                } catch (RemoteException ex) {
                    Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
                }
                return null;
            });
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Cất Thành Công");
        }
    }

    public void using_for_ThemMon() throws RemoteException {
        if (branch.equals(TypeDatMon_Branch.THEMMON)) {
            List<Mon> pre_order = new ArrayList<>();
            List<ChiTietHoaDon> list_canceled = chitietDAO.getListBySoLuong(0);
            hoaDon.setSoLuongNguoi(getSoLuong());
            hoaDonDAO.update(hoaDon);
            for (int i = 0; i < orders.size(); i++) {
                int count = 0;
                for (int j = 0; j < orders.size(); j++) {
                    if (orders.get(i).getMaMon().equals(orders.get(j).getMaMon())) {
                        count++;
                        if (count >= 2) {
                            int tong = list_quantity.get(i) + list_quantity.get(j);
                            list_quantity.set(i, tong);
                            orders.remove(j);
                            list_quantity.remove(j);
                        }
                    }
                }
            }

            for (int i = 0; i < orders.size(); i++) {
                for (ChiTietHoaDon detail : details) {
                    if (orders.get(i).getTenMon().equals(detail.getMon().getTenMon())) {
                        detail.setSoLuong(list_quantity.get(i));

                    }
                }
            }
            for (ChiTietHoaDon detail : details) {
                pre_order.add(detail.getMon());
                chitietDAO.update(detail);
            }
            for (int i = 0; i < orders.size(); i++) {
                if (!pre_order.contains(orders.get(i))) {
                    ChiTietHoaDon chiTiet = new ChiTietHoaDon(orders.get(i), hoaDon, list_quantity.get(i));
                    for (ChiTietHoaDon cancel : list_canceled) {
                        if (orders.get(i).getTenMon().equals(cancel.getMon().getTenMon())) {
                            chitietDAO.deleteChiTiet(cancel);
                        }
                    }
                    chitietDAO.insert(chiTiet);
                }
            }
            AppUtils.setUI(main, () -> {
                try {
                    return new GD_DatBanTaiCho(main, nv);
                } catch (RemoteException ex) {
                    Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);

                }
                return null;
            });
            Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Thay Đổi Thành Công");
        }

    }

    public void using_for_DatTruocMon() throws RemoteException {
        if(ghiChus==null){
            ghiChus = new ArrayList<>();
        }
        if (branch.equals(TypeDatMon_Branch.DAT_TRUOC_MON)) {
            hoaDon.setSoLuongNguoi(getSoLuong());
            if(details.size()==-1)
                details = chitietDAO.getListByHoaDon(hoaDon);
            for (ChiTietHoaDon detail : details) {
                chitietDAO.deleteChiTiet(detail);
            }
            for (int i = 0; i < orders.size(); i++) {
                ChiTietHoaDon item = new ChiTietHoaDon(orders.get(i), hoaDon, list_quantity.get(i));
                if(ghiChus.size()<=i){
                    item.setGhiChu(ghiChus.get(i));
                }
                chitietDAO.insert(item);
            }

            List<ChiTietHoaDon> dsChiTietHoaDon = chitietDAO.getListByHoaDon(hoaDon);
            String yeuCauDatMon = "";
            for (ChiTietHoaDon chiTiet : dsChiTietHoaDon) {
                String isQuote = chiTiet.equals(dsChiTietHoaDon.get(dsChiTietHoaDon.size() - 1)) ? "" : ", ";
                yeuCauDatMon += chiTiet.getMon().getTenMon() + " (" + chiTiet.getSoLuong() + " Suất)" + isQuote;
            }

            hoaDon.setYeuCauDatMon(yeuCauDatMon);
            hoaDonDAO.update(hoaDon);
            if (back_toUI_DatBan == false) {
                AppUtils.setUI(main, () -> {
                    try {
                        return new GD_DatBanTaiCho(main, nv);
                    } catch (RemoteException ex) {
                        Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                });
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Thay Đổi Thành Công");
            } else {
                AppUtils.setUI(main, () -> {
                    try {
                        return new GD_DatBanTruoc(main);
                    } catch (RemoteException ex) {
                        Logger.getLogger(GD_DatMon.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                });
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Thay Đổi Thành Công");
            }
        }
    }

    public void setOrders(ArrayList<Mon> orders) {
        this.orders = orders;
    }

    public void setLabelTongTien(String text) {
        this.labelTongTien.setText(text);
    }

    public JPanel getPanelOrder() {
        return PanelOrder;
    }

    public void setList_quantity(ArrayList<Integer> list_quantity) {
        this.list_quantity = new ArrayList<Integer>();
        this.list_quantity = list_quantity;
    }

    public ArrayList<Integer> getList_quantity() {
        return list_quantity;
    }

    public NhanVien getNv() {
        return nv;
    }

    public TypeDatMon_Branch getBranch() {
        return branch;
    }

    public void setBranch(TypeDatMon_Branch branch) {
        this.branch = branch;
    }

    public JPanel getFoodList() {
        return FoodList;
    }

    public GD_Ban getgD_Ban() {
        return gD_Ban;
    }

    public void setgD_Ban(GD_Ban gD_Ban) {
        this.gD_Ban = gD_Ban;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
        preLoad = true;
        First_LoadData();
    }

    public void setBtnBack(MyButton btnBack) {
        this.btnBack = btnBack;
    }

    public MyButton getBtnBack() {
        return btnBack;
    }

    public void setGd_qlDatMon(GD_DatBanTaiCho gd_qlDatMon) {
        this.gd_qlDatMon = gd_qlDatMon;
    }

    public void setGd_datBan(GD_DatBanTruoc gd_datBan) {
        this.gd_datBan = gd_datBan;
    }

    public List<ChiTietHoaDon> getDetails() {
        return details;
    }

    public List<OrderItem_forUIDatMon> getListPreOrderItem() {
        return listPreOrderItem;
    }

    public void setListPreOrderItem(List<OrderItem_forUIDatMon> listPreOrderItem) {
        this.listPreOrderItem = listPreOrderItem;
    }

    public List<Mon> getList_CancelFood() {
        return list_CancelFood;
    }

    public void setDetails(List<ChiTietHoaDon> details) {
        this.details = details;
    }

    public ArrayList<Mon> getOrders() {
        return orders;
    }

    public GD_DatBanTaiCho getGd_qlDatMon() {
        return gd_qlDatMon;
    }

    public boolean isBack_toUI_DatBan() {
        return back_toUI_DatBan;
    }

    public void setBack_toUI_DatBan(boolean back_toUI_DatBan) {
        this.back_toUI_DatBan = back_toUI_DatBan;
    }

    public void setSoLuong(int soluong) {
        btnNV.setText(Integer.toString(soluong));
    }

    public int getSoLuong() {
        return Integer.parseInt(btnNV.getText());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel FoodList;
    private component.PanelRound HEADER_ORDER;
    private javax.swing.JPanel PanelOrder;
    private javax.swing.JScrollPane Scroll_Order;
    private component.PanelRound TABLE_ORDER;
    private javax.swing.JTextField banTextField;
    private component.MyButton btnBack;
    private component.MyButton btnCat;
    private component.MyButton btnDD;
    private component.MyButton btnDU;
    private component.MyButton btnDoUong;
    private component.MyButton btnDown;
    private component.MyButton btnGhiChu;
    private component.MyButton btnGuiBep;
    private component.MyButton btnHayDung;
    private component.MyButton btnHelpCaculator;
    private component.MyButton btnHuyBo;
    private component.MyButton btnKhac;
    private component.MyButton btnKhuyenMai;
    private component.MyButton btnMonAn;
    private component.MyButton btnNV;
    private component.MyButton btnSearch;
    private component.MyButton btnThem;
    private component.MyButton btnTime;
    private component.MyButton btnTinhTien;
    private component.MyButton btnUp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldSearch;
    private javax.swing.JLabel labelTongTien;
    private java.awt.List list1;
    private component.MyButton myButton5;
    private javax.swing.JLabel nhanVienName;
    private component.PanelGradient panelGradient1;
    private component.PanelRound panelMenuMon;
    private component.PanelRound panelMon;
    private component.PanelRound panelOrder;
    private component.PanelRound panelRound1;
    private component.PanelRound panelRound2;
    private component.PanelRound panelRound3;
    private component.PanelRound panelRound4;
    private component.PanelRound panelRound5;
    private javax.swing.JScrollPane scrollFoodList;
    // End of variables declaration//GEN-END:variables

    public void setUI() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<String> getGhiChus() {
        return ghiChus;
    }

    public void setGhiChus(List<String> ghiChus) {
        this.ghiChus = ghiChus;
    }
}
