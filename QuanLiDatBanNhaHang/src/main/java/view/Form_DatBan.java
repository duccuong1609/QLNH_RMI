/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import LIB.FadeEffect;
import com.formdev.flatlaf.FlatIntelliJLaf;
import component.MenuItem;
import component.ScrollBarCustom;
import dao.IBanDAO;
import dao.IChiTietHoaDonDAO;
import dao.IHoaDonDAO;
import dao.IKhachHangDAO;
import datechooser.EventDateChooser;
import datechooser.SelectedAction;
import datechooser.SelectedDate;
import entity.Ban;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import icon.FontAwesome;
import java.awt.Color;
import static java.awt.Frame.MAXIMIZED_BOTH;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import jiconfont.swing.IconFontSwing;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import raven.toast.Notifications;
import static utils.AppUtils.*;

/**
 *
 * @author Laptop
 */
public class Form_DatBan extends javax.swing.JPanel {

    /**
     * Creates new form Form_DatBan
     */
    private JFrame jFrame;
    private NhanVien nv;
    private JPanel mainJpanel;
    private Ban ban;
    private HoaDon hoaDon = null;
    private List<MenuItem> dsMon = new ArrayList<MenuItem>();
    private static final Color TRANSPERANT = new Color(0, 0, 0, 0);
    private List<String> items = new ArrayList<String>();
    private List<KhachHang> khachHangs = new ArrayList<KhachHang>();
    private IBanDAO banDAO = BANDAO;
    private IHoaDonDAO hoaDonDAO = HOADONDAO;
    private IKhachHangDAO khachHangDAO = KHACHHANGDAO;
    private IChiTietHoaDonDAO chiTietHoaDonDAO = CHITIETHOADONDAO;
    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private String type = "INSERT";

    public Form_DatBan(JFrame jFrame, Ban ban) throws RemoteException {
        this.jFrame = jFrame;
        this.ban = ban;;
        initComponents();
        Notifications.getInstance().setJFrame(jFrame);
        FlatIntelliJLaf.setup();
        IconFontSwing.register(FontAwesome.getIconFont());
        this.setBackground(new Color(0, 0, 0, 0.6f));
        btnPlus.setIcon(IconFontSwing.buildIcon(FontAwesome.PLUS, 20, Color.WHITE));
        btnMinus.setIcon(IconFontSwing.buildIcon(FontAwesome.MINUS, 20, Color.WHITE));
        txtSoNguoi.setBackground(TRANSPERANT);
        txtYeuCau.setBackground(TRANSPERANT);
        txtYeuCauDatMon.setBackground(Color.WHITE);
        txtDate.setBackground(TRANSPERANT);
        txtKhachHang.setBackground(TRANSPERANT);
        txtSoDienThoai.setBackground(TRANSPERANT);
        scrollYCMD.setVerticalScrollBar(new ScrollBarCustom());
        scrollYCMD.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        calendar.setIcon(IconFontSwing.buildIcon(FontAwesome.CALENDAR, 24, new Color(31, 29, 43)));
        iconClock.setIcon(IconFontSwing.buildIcon(FontAwesome.CLOCK_O, 24, new Color(31, 29, 43)));
        dateChooser.addEventDateChooser(new EventDateChooser() {
            public void dateSelected(SelectedAction action, SelectedDate date) {
                LocalDate localDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
                if (localDate.isBefore(LocalDate.now())) {
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, 1500, "Ngày không được trước ngày hiện tại");
                    dateChooser.toDay();
                }
                if (action.getAction() == com.raven.datechooser.SelectedAction.DAY_SELECTED) {
                    dateChooser.hidePopup();
                }
            }

        });
        autoComplete();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new datechooser.DateChooser();
        wrapper = new javax.swing.JPanel();
        header = new component.PanelRound();
        jLabel1 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        footer = new component.PanelRound();
        btnCat = new component.MyButton();
        btnHuy = new component.MyButton();
        jLabel2 = new javax.swing.JLabel();
        panelRound1 = new component.PanelRound();
        calendar = new javax.swing.JLabel();
        txtDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        panelRound2 = new component.PanelRound();
        iconClock = new javax.swing.JLabel();
        txtGioDen = new component.ComboBoxSuggestion();
        panelRound3 = new component.PanelRound();
        txtKhachHang = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        panelRound4 = new component.PanelRound();
        txtSoDienThoai = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        yeuCauDatMon = new component.PanelRound();
        scrollYCMD = new javax.swing.JScrollPane();
        txtYeuCauDatMon = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        yeuCauKhac = new component.PanelRound();
        txtYeuCau = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        panelRound7 = new component.PanelRound();
        txtSoNguoi = new javax.swing.JTextField();
        btnPlus = new component.MyButton();
        btnXemThucDon = new component.MyButton();
        btnMinus = new component.MyButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        errorSDT = new javax.swing.JLabel();
        errorKH = new javax.swing.JLabel();

        dateChooser.setDateFormat("dd/MM/yyyy");
        dateChooser.setTextRefernce(txtDate);

        setForeground(new java.awt.Color(255, 255, 255));

        wrapper.setBackground(new java.awt.Color(83, 86, 99));

        header.setBackground(new java.awt.Color(31, 29, 43));

        jLabel1.setFont(utils.AppUtils.getFont(16f, _BOLD_));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Đặt chỗ");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/icon_close.png"))); // NOI18N
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addContainerGap())
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addGroup(headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(headerLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(headerLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        footer.setBackground(new java.awt.Color(31, 29, 43));

        btnCat.setForeground(new java.awt.Color(255, 255, 255));
        btnCat.setText("CẤT");
        btnCat.setColor(new java.awt.Color(83, 86, 99));
        btnCat.setColorClick(new java.awt.Color(234, 124, 105));
        btnCat.setColorOver(new java.awt.Color(234, 124, 105));
        btnCat.setRadius(8);
        btnCat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCatActionPerformed(evt);
            }
        });

        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("HỦY BỎ");
        btnHuy.setColor(new java.awt.Color(83, 86, 99));
        btnHuy.setColorClick(new java.awt.Color(234, 124, 105));
        btnHuy.setColorOver(new java.awt.Color(234, 124, 105));
        btnHuy.setRadius(8);
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout footerLayout = new javax.swing.GroupLayout(footer);
        footer.setLayout(footerLayout);
        footerLayout.setHorizontalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCat, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        footerLayout.setVerticalGroup(
            footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(footerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCat, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel2.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Ngày");

        panelRound1.setBackground(java.awt.Color.white);
        panelRound1.setRoundBottomLeft(8);
        panelRound1.setRoundBottomRight(8);
        panelRound1.setRoundTopLeft(8);
        panelRound1.setRoundTopRight(8);

        calendar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        calendar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calendarMouseClicked(evt);
            }
        });

        txtDate.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        txtDate.setBorder(null);

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(calendar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtDate)
        );

        jLabel3.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Giờ đến");

        panelRound2.setBackground(java.awt.Color.white);
        panelRound2.setRoundBottomLeft(8);
        panelRound2.setRoundBottomRight(8);
        panelRound2.setRoundTopLeft(8);
        panelRound2.setRoundTopRight(8);

        iconClock.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        txtGioDen.setBorder(null);
        txtGioDen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "12:00 AM", "12:15 AM", "12:30 AM", "12:45 AM", "1:00 AM", "1:15 AM", "1:30 AM", "1:45 AM", "2:00 AM", "2:15 AM", "2:30 AM", "2:45 AM", "3:00 AM", "3:15 AM", "3:30 AM", "3:45 AM", "4:00 AM", "4:15 AM", "4:30 AM", "4:45 AM", "5:00 AM", "5:15 AM", "5:30 AM", "5:45 AM", "6:00 AM", "6:15 AM", "6:30 AM", "6:45 AM", "7:00 AM", "7:15 AM", "7:30 AM", "7:45 AM", "8:00 AM", "8:15 AM", "8:30 AM", "8:45 AM", "9:00 AM", "9:15 AM", "9:30 AM", "9:45 AM", "10:00 AM", "10:15 AM", "10:30 AM", "10:45 AM", "11:00 AM", "11:15 AM", "11:30 AM", "11:45 AM", "12:00 PM", "12:15 PM", "12:30 PM", "12:45 PM", "1:00 PM", "1:15 PM", "1:30 PM", "1:45 PM", "2:00 PM", "2:15 PM", "2:30 PM", "2:45 PM", "3:00 PM", "3:15 PM", "3:30 PM", "3:45 PM", "4:00 PM", "4:15 PM", "4:30 PM", "4:45 PM", "5:00 PM", "5:15 PM", "5:30 PM", "5:45 PM", "6:00 PM", "6:15 PM", "6:30 PM", "6:45 PM", "7:00 PM", "7:15 PM", "7:30 PM", "7:45 PM", "8:00 PM", "8:15 PM", "8:30 PM", "8:45 PM", "9:00 PM", "9:15 PM", "9:30 PM", "9:45 PM", "10:00 PM", "10:15 PM", "10:30 PM", "10:45 PM", "11:00 PM", "11:15 PM", "11:30 PM", "11:45 PM" }));
        txtGioDen.setFont(utils.AppUtils.getFont(16f, _NORMAL_));

        javax.swing.GroupLayout panelRound2Layout = new javax.swing.GroupLayout(panelRound2);
        panelRound2.setLayout(panelRound2Layout);
        panelRound2Layout.setHorizontalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtGioDen, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconClock, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelRound2Layout.setVerticalGroup(
            panelRound2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(iconClock, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(txtGioDen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        panelRound3.setBackground(java.awt.Color.white);
        panelRound3.setRoundBottomLeft(8);
        panelRound3.setRoundBottomRight(8);
        panelRound3.setRoundTopLeft(8);
        panelRound3.setRoundTopRight(8);

        txtKhachHang.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        txtKhachHang.setBorder(null);
        txtKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKhachHangKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelRound3Layout = new javax.swing.GroupLayout(panelRound3);
        panelRound3.setLayout(panelRound3Layout);
        panelRound3Layout.setHorizontalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtKhachHang))
        );
        panelRound3Layout.setVerticalGroup(
            panelRound3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtKhachHang)
        );

        jLabel4.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Khách hàng");

        jLabel5.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Số điện thoại");

        panelRound4.setBackground(java.awt.Color.white);
        panelRound4.setRoundBottomLeft(8);
        panelRound4.setRoundBottomRight(8);
        panelRound4.setRoundTopLeft(8);
        panelRound4.setRoundTopRight(8);

        txtSoDienThoai.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        txtSoDienThoai.setBorder(null);

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtSoDienThoai))
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSoDienThoai)
        );

        jLabel6.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Yêu cầu đặt món");

        yeuCauDatMon.setBackground(java.awt.Color.white);
        yeuCauDatMon.setRoundBottomLeft(8);
        yeuCauDatMon.setRoundBottomRight(8);
        yeuCauDatMon.setRoundTopLeft(8);
        yeuCauDatMon.setRoundTopRight(8);

        scrollYCMD.setBackground(new java.awt.Color(255, 255, 255));
        scrollYCMD.setBorder(null);

        txtYeuCauDatMon.setEditable(false);
        txtYeuCauDatMon.setBackground(java.awt.Color.white);
        txtYeuCauDatMon.setColumns(4);
        txtYeuCauDatMon.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        txtYeuCauDatMon.setLineWrap(true);
        txtYeuCauDatMon.setRows(5);
        txtYeuCauDatMon.setBorder(null);
        txtYeuCauDatMon.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtYeuCauDatMon.setOpaque(false);
        scrollYCMD.setViewportView(txtYeuCauDatMon);

        javax.swing.GroupLayout yeuCauDatMonLayout = new javax.swing.GroupLayout(yeuCauDatMon);
        yeuCauDatMon.setLayout(yeuCauDatMonLayout);
        yeuCauDatMonLayout.setHorizontalGroup(
            yeuCauDatMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yeuCauDatMonLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollYCMD)
                .addContainerGap())
        );
        yeuCauDatMonLayout.setVerticalGroup(
            yeuCauDatMonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, yeuCauDatMonLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollYCMD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel7.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Yêu cầu khác");

        yeuCauKhac.setBackground(java.awt.Color.white);
        yeuCauKhac.setRoundBottomLeft(8);
        yeuCauKhac.setRoundBottomRight(8);
        yeuCauKhac.setRoundTopLeft(8);
        yeuCauKhac.setRoundTopRight(8);

        txtYeuCau.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        txtYeuCau.setBorder(null);
        txtYeuCau.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        javax.swing.GroupLayout yeuCauKhacLayout = new javax.swing.GroupLayout(yeuCauKhac);
        yeuCauKhac.setLayout(yeuCauKhacLayout);
        yeuCauKhacLayout.setHorizontalGroup(
            yeuCauKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(yeuCauKhacLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtYeuCau)
                .addContainerGap())
        );
        yeuCauKhacLayout.setVerticalGroup(
            yeuCauKhacLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtYeuCau)
        );

        jLabel8.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Số người");

        panelRound7.setBackground(new java.awt.Color(255, 255, 255));
        panelRound7.setRoundBottomLeft(8);
        panelRound7.setRoundBottomRight(8);
        panelRound7.setRoundTopLeft(8);
        panelRound7.setRoundTopRight(8);

        txtSoNguoi.setFont(utils.AppUtils.getFont(16f, _NORMAL_));
        txtSoNguoi.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtSoNguoi.setText("1");
        txtSoNguoi.setBorder(null);
        txtSoNguoi.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtSoNguoi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSoNguoiKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSoNguoi, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSoNguoi)
        );

        btnPlus.setBackground(new java.awt.Color(31, 29, 43));
        btnPlus.setForeground(new java.awt.Color(255, 255, 255));
        btnPlus.setColor(new java.awt.Color(31, 29, 43));
        btnPlus.setColorClick(new java.awt.Color(234, 124, 105));
        btnPlus.setColorOver(new java.awt.Color(234, 124, 105));
        btnPlus.setRadius(8);
        btnPlus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPlusActionPerformed(evt);
            }
        });

        btnXemThucDon.setBackground(new java.awt.Color(31, 29, 43));
        btnXemThucDon.setForeground(new java.awt.Color(255, 255, 255));
        btnXemThucDon.setText("Xem thực đơn");
        btnXemThucDon.setColor(new java.awt.Color(31, 29, 43));
        btnXemThucDon.setColorClick(new java.awt.Color(234, 124, 105));
        btnXemThucDon.setColorOver(new java.awt.Color(234, 124, 105));
        btnXemThucDon.setFont(utils.AppUtils.getFont(14f, _NORMAL_));
        btnXemThucDon.setRadius(8);
        btnXemThucDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemThucDonActionPerformed(evt);
            }
        });

        btnMinus.setBackground(new java.awt.Color(31, 29, 43));
        btnMinus.setForeground(new java.awt.Color(255, 255, 255));
        btnMinus.setColor(new java.awt.Color(31, 29, 43));
        btnMinus.setColorClick(new java.awt.Color(234, 124, 105));
        btnMinus.setColorOver(new java.awt.Color(234, 124, 105));
        btnMinus.setRadius(8);
        btnMinus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinusActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 51, 51));
        jLabel9.setText("*");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 51, 51));
        jLabel10.setText("*");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 51, 51));
        jLabel12.setText("*");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 51, 51));
        jLabel13.setText("*");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 51, 51));
        jLabel14.setText("*");

        errorSDT.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        errorSDT.setForeground(new java.awt.Color(255, 51, 51));

        errorKH.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        errorKH.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout wrapperLayout = new javax.swing.GroupLayout(wrapper);
        wrapper.setLayout(wrapperLayout);
        wrapperLayout.setHorizontalGroup(
            wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(footer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(wrapperLayout.createSequentialGroup()
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(errorKH, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(wrapperLayout.createSequentialGroup()
                            .addGap(158, 158, 158)
                            .addComponent(errorSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, wrapperLayout.createSequentialGroup()
                            .addGap(24, 24, 24)
                            .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addGroup(wrapperLayout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(wrapperLayout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(wrapperLayout.createSequentialGroup()
                                            .addGap(2, 2, 2)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(wrapperLayout.createSequentialGroup()
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(wrapperLayout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel7)
                                .addComponent(jLabel8))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(wrapperLayout.createSequentialGroup()
                                    .addComponent(panelRound1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(44, 44, 44)
                                    .addComponent(jLabel3)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(2, 2, 2)
                                    .addComponent(panelRound2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(yeuCauDatMon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(yeuCauKhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(wrapperLayout.createSequentialGroup()
                                    .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btnPlus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, 0)
                                    .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXemThucDon, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        wrapperLayout.setVerticalGroup(
            wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(wrapperLayout.createSequentialGroup()
                .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(12, 12, 12)
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errorKH, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(wrapperLayout.createSequentialGroup()
                        .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnXemThucDon, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                            .addComponent(btnMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPlus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(16, 16, 16)
                        .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(yeuCauDatMon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(wrapperLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(yeuCauKhac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(footer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(wrapperLayout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(wrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(181, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(wrapper, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        jFrame.setVisible(false);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed
        // TODO add your handling code here:
        jFrame.setVisible(false);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnPlusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPlusActionPerformed
        // TODO add your handling code here:
        int sl = Integer.parseInt(txtSoNguoi.getText());
        txtSoNguoi.setText(++sl + "");
    }//GEN-LAST:event_btnPlusActionPerformed

    private void btnXemThucDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemThucDonActionPerformed
        // TODO add your handling code here:
        JFrame jFrame = new JFrame();
        jFrame.setUndecorated(true);
        jFrame.setExtendedState(MAXIMIZED_BOTH);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Form_XemThucDon form_XemThucDon;
        try {
            form_XemThucDon = new Form_XemThucDon(jFrame, txtYeuCauDatMon, hoaDon);
            form_XemThucDon.setFormDB(this);
            jFrame.add(form_XemThucDon);
            jFrame.setBackground(new Color(0, 0, 0, 0));
            FadeEffect.fadeInFrame(jFrame, 8, 0.1f);
            jFrame.setVisible(true);
        } catch (RemoteException ex) {
            Logger.getLogger(Form_DatBan.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnXemThucDonActionPerformed
    private void btnCatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCatActionPerformed
        if (type.equals("INSERT")) {
            try {
                insertPhieuDatBan();
            } catch (RemoteException ex) {
                Logger.getLogger(Form_DatBan.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type.equals("UPDATE")) {
            try {
                updatePhieuDatBan();
            } catch (RemoteException ex) {
                Logger.getLogger(Form_DatBan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnCatActionPerformed

    public void setMonDaDat(List<MenuItem> ds) {
        this.dsMon = ds;
    }

    private boolean isValidate() {
        String khachHang = txtKhachHang.getText();
        String sdt = txtSoDienThoai.getText();
        if (khachHang.isEmpty()) {
            errorKH.setText("Không được rỗng");
            return false;
        }
        if (sdt.isEmpty()) {
            errorSDT.setText("Không được rỗng");
        }
        if (!sdt.matches("(84|0[3|5|7|8|9])+([0-9]{8})\\b")) {
            errorSDT.setText("Số điện thoại không hợp lệ");
            return false;
        }
        return true;
    }

    private KhachHang createKhachHang() {
        String hoTen = txtKhachHang.getText();
        String sdt = txtSoDienThoai.getText();
        KhachHang kh = new KhachHang(hoTen, sdt, null);
        sdt = txtSoDienThoai.getText().substring(1, txtSoDienThoai.getText().length());
        kh.setMaKhachHang("KH" + sdt);
        return kh;
    }

    private void updateMenu(HoaDon phieuDatBan) throws RemoteException {
        if (!dsMon.isEmpty()) {
            List<ChiTietHoaDon> details = chiTietHoaDonDAO.getListByHoaDon(phieuDatBan);
            for (ChiTietHoaDon detail : details) {
                chiTietHoaDonDAO.deleteChiTiet(detail);
            }
            phieuDatBan.setChiTietHoaDon(getListDetails(phieuDatBan));
        }
    }

    private List<ChiTietHoaDon> getListDetails(HoaDon hoaDon) {
        List<ChiTietHoaDon> details = new ArrayList<>();
        for (MenuItem item : dsMon) {
            ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
            chiTietHoaDon.setHoaDon(hoaDon);
            chiTietHoaDon.setMon(item.getMon());
            chiTietHoaDon.setSoLuong(item.getSoLuong());
            chiTietHoaDon.thanhTien();
            details.add(chiTietHoaDon);
        }
        return details;
    }

    private void txtSoNguoiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSoNguoiKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c))
            evt.consume();
    }//GEN-LAST:event_txtSoNguoiKeyTyped

    private void btnMinusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinusActionPerformed
        // TODO add your handling code here:
        int sl = Integer.parseInt(txtSoNguoi.getText());
        txtSoNguoi.setText(sl - 1 > 0 ? --sl + "" : sl + "");
    }//GEN-LAST:event_btnMinusActionPerformed

    private void calendarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calendarMouseClicked
        // TODO add your handling code here:
        dateChooser.showPopup();
    }//GEN-LAST:event_calendarMouseClicked

    private void autoComplete() throws RemoteException {
        khachHangs = khachHangDAO.findAll(KhachHang.class);
        for (KhachHang khachHang : khachHangs) {
            items.add((khachHang).getHoTen());
        }
        AutoCompleteDecorator.decorate(txtKhachHang, items, false);

    }

    private void txtKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKhachHangKeyReleased
        String value = txtKhachHang.getText();
        if (items.contains(value)) {
            for (KhachHang kh : khachHangs) {
                if (kh.getHoTen().equals(value)) {
                    txtSoDienThoai.setText(kh.getSoDienThoai());
                    break;
                }

            }
        }
    }//GEN-LAST:event_txtKhachHangKeyReleased

    private void updatePhieuDatBan() throws RemoteException {
        if (isValidate()) {
            HoaDon hoaDon = createHoaDon();
            hoaDon.setMaHoaDon(this.hoaDon.getMaHoaDon());
            updateMenu(hoaDon);
            boolean isSuccess = hoaDonDAO.update(hoaDon);
            if (isSuccess) {
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Thay đổi bàn thành công");
                this.jFrame.setVisible(false);
                this.jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                utils.AppUtils.setUI(this.mainJpanel, () -> {
                    try {
                        return new GD_DatBanTruoc(this.mainJpanel);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Form_DatBan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                });
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, 1500, "Thay đổi bàn không thành công");
            }
        }
    }

    private void insertPhieuDatBan() throws RemoteException {
        if (isValidate()) {
            HoaDon phieuDatBan = createHoaDon();
            phieuDatBan.setChiTietHoaDon(getListDetails(phieuDatBan));
            boolean isSuccess = hoaDonDAO.insertHoaDon(phieuDatBan);
            if (isSuccess) {
                banDAO.updateStateById(ban.getMaBan(), utils.Enum.LoaiTrangThai.BAN_DA_DUOC_DAT);
                Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_RIGHT, 1500, "Đặt bàn thành công");
                jFrame.setVisible(false);
                jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                utils.AppUtils.setUI(mainJpanel, () -> {
                    try {
                        return new GD_DatBanTruoc(mainJpanel);
                    } catch (RemoteException ex) {
                        Logger.getLogger(Form_DatBan.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    return null;
                });
            } else {
                Notifications.getInstance().show(Notifications.Type.WARNING, Notifications.Location.TOP_RIGHT, 1500, "Đặt bàn không thành công");
            }
        }
    }

    private HoaDon createHoaDon() throws RemoteException {
        HoaDon hoaDon = null;
        KhachHang kh = khachHangDAO.findByPhoneNumber(txtSoDienThoai.getText());
        if (kh == null) {
            khachHangDAO.insert(createKhachHang());
        }
        SelectedDate date = dateChooser.getSelectedDate();
        String gioDenString = txtGioDen.getSelectedItem() + "";
        int soLuong = Integer.parseInt(txtSoNguoi.getText());
        String yeuCauKhac = txtYeuCau.getText();
        String yeuCauDatMon = txtYeuCauDatMon.getText();
        LocalDate ngayString = LocalDate.of(date.getYear(), date.getMonth(), date.getDay());
        LocalDate ngay = LocalDate.parse(FORMATTER.format(ngayString), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime gioDen = LocalTime.parse(gioDenString, DateTimeFormatter.ofPattern("h:mm a"));
        LocalDateTime ngayGio = LocalDateTime.of(ngay, gioDen);
        hoaDon = new HoaDon(utils.AppUtils.NHANVIEN, kh, LocalDateTime.now());
        hoaDon.setTrangThai(utils.Enum.LoaiTrangThaiHoaDon.DAT_TRUOC);
        hoaDon.setBan(ban);
        hoaDon.setYeuCauKhac(yeuCauKhac);
        hoaDon.setSoLuongNguoi(soLuong);
        hoaDon.setNgayDatBan(ngayGio);
        hoaDon.setYeuCauDatMon(yeuCauDatMon);
        return hoaDon;
    }

    void setData(HoaDon hoaDon) throws RemoteException {
        this.hoaDon = hoaDon;
        LocalDateTime date = hoaDon.getNgayDatBan();
        String yeuCauDatMon = "";
        List<ChiTietHoaDon> dsChiTietHoaDon = chiTietHoaDonDAO.getListByHoaDon(hoaDon);
        for (ChiTietHoaDon chiTiet : dsChiTietHoaDon) {
            String isQuote = chiTiet.equals(dsChiTietHoaDon.get(dsChiTietHoaDon.size() - 1)) ? "" : ", ";
            yeuCauDatMon += chiTiet.getMon().getTenMon() + " (" + chiTiet.getSoLuong() + " Suất)" + isQuote;
        }
        txtKhachHang.setText(hoaDon.getKhachHang().getHoTen());
        txtSoDienThoai.setText(hoaDon.getKhachHang().getSoDienThoai());
        txtYeuCau.setText(hoaDon.getYeuCauKhac());
        txtSoNguoi.setText(hoaDon.getSoLuongNguoi() + "");
        dateChooser.setSelectedDate(new SelectedDate(date.getDayOfMonth(), date.getMonthValue(), date.getYear()));
        txtGioDen.setSelectedItem(forrmater(hoaDon.getNgayDatBan().toString()));
        txtYeuCauDatMon.setText(yeuCauDatMon);
    }

    private String forrmater(String date) {
        LocalDateTime inputDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String formattedDateTime = inputDateTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        return formattedDateTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setMainJpanel(JPanel main) {
        this.mainJpanel = main;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.MyButton btnCat;
    private component.MyButton btnHuy;
    private component.MyButton btnMinus;
    private component.MyButton btnPlus;
    private component.MyButton btnXemThucDon;
    private javax.swing.JLabel calendar;
    private datechooser.DateChooser dateChooser;
    private javax.swing.JLabel errorKH;
    private javax.swing.JLabel errorSDT;
    private component.PanelRound footer;
    private component.PanelRound header;
    private javax.swing.JLabel iconClock;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private component.PanelRound panelRound1;
    private component.PanelRound panelRound2;
    private component.PanelRound panelRound3;
    private component.PanelRound panelRound4;
    private component.PanelRound panelRound7;
    private javax.swing.JScrollPane scrollYCMD;
    private javax.swing.JTextField txtDate;
    private component.ComboBoxSuggestion txtGioDen;
    private javax.swing.JTextField txtKhachHang;
    private javax.swing.JTextField txtSoDienThoai;
    private javax.swing.JTextField txtSoNguoi;
    private javax.swing.JTextField txtYeuCau;
    private javax.swing.JTextArea txtYeuCauDatMon;
    private javax.swing.JPanel wrapper;
    private component.PanelRound yeuCauDatMon;
    private component.PanelRound yeuCauKhac;
    // End of variables declaration//GEN-END:variables

}
