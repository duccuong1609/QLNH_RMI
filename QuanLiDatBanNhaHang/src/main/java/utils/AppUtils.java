/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Font;
import component.Loading;
import dao.IBanDAO;
import dao.IChiTietHoaDonDAO;
import dao.IChiTietKhuyenMaiDAO;
import dao.IHoaDonDAO;
import dao.IKhachHangDAO;
import dao.IKhuyenMaiDAO;
import dao.IMonDAO;
import dao.INhanVienDAO;
import dao.ITaiKhoanDAO;
import dao.ITheThanhVienDAO;
import dao.imlp.BanDAO;
import dao.imlp.ChiTietHoaDonDAO;
import dao.imlp.ChiTietKhuyenMaiDAO;
import dao.imlp.HoaDonDAO;
import dao.imlp.KhachHangDAO;
import dao.imlp.KhuyenMainDAO;
import dao.imlp.MonDAO;
import dao.imlp.NhanVienDAO;
import dao.imlp.TaiKhoanDAO;
import dao.imlp.TheThanhVienDAO;
import entity.NhanVien;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.InputStream;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.JScrollPane;

/**
 *
 * @author Laptop
 */
public class AppUtils {

    public static INhanVienDAO NHANVIENDAO;
    public static ITaiKhoanDAO TAIKHOANDAO;
    public static IBanDAO BANDAO;
    public static IChiTietHoaDonDAO CHITIETHOADONDAO;
    public static IHoaDonDAO HOADONDAO;
    public static IChiTietKhuyenMaiDAO CHITIETKHUYENMAIDAO;
    public static IKhachHangDAO KHACHHANGDAO;
    public static IKhuyenMaiDAO KHUYENMAIDAO;
    public static IMonDAO MONDAO;
    public static ITheThanhVienDAO THETHANHVIENDAO;
    public static NhanVien NHANVIEN = null;
    public static DecimalFormat tien_format = new DecimalFormat("###,### VNĐ");
    public static String _NORMAL_ = "/font/OpenSans-VariableFont_wdth,wght.ttf";
    public static String _BOLD_ = "/font/OpenSans_Condensed-ExtraBold.ttf";
    public static String _ITALIC_ = "/font/OpenSans-Italic-VariableFont_wdth,wght.ttf";

    public AppUtils() throws RemoteException {
        NHANVIENDAO = new NhanVienDAO();
        TAIKHOANDAO = new TaiKhoanDAO();
        BANDAO = new BanDAO();
        CHITIETHOADONDAO = new ChiTietHoaDonDAO();
        HOADONDAO = new HoaDonDAO();
        CHITIETKHUYENMAIDAO = new ChiTietKhuyenMaiDAO();
        KHACHHANGDAO = new KhachHangDAO();
        KHUYENMAIDAO = new KhuyenMainDAO();
        MONDAO = new MonDAO();
        THETHANHVIENDAO = new TheThanhVienDAO();
    }

    public static Font getFont(float a, String b) {
        try {
            InputStream inputStream = AppUtils.class.getResourceAsStream(b);
//            File fontStyle = new File(getClass().getResource("/icons/loading_final.gif"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(a);
            return font;
        } catch (Exception e) {
        }

        return null;
    }

    public static void setUI(JPanel mainJPanel, Supplier<JPanel> componentSupplier) {
        mainJPanel.removeAll();
        Loading loading = new Loading();
        mainJPanel.add(loading, BorderLayout.CENTER);
        mainJPanel.repaint();
        mainJPanel.revalidate();

        SwingWorker<JPanel, Void> worker = new SwingWorker<JPanel, Void>() {
            @Override
            protected JPanel doInBackground() throws Exception {
                // Thực hiện công việc lâu dài ở đây, trả về JPanel hoặc null
                return componentSupplier.get();
            }

            @Override
            protected void done() {
                try {
                    mainJPanel.removeAll();
                    JPanel resultPanel = get(); // Nhận kết quả từ công việc lâu dài
                    if (resultPanel != null) {
                        mainJPanel.add(resultPanel);
                    } else {
                        // Xử lý trường hợp không có kết quả
                    }
                    mainJPanel.repaint();
                    mainJPanel.revalidate();
                } catch (InterruptedException | ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        };
        worker.execute();
    }

    public static void setLoadingForTable(JScrollPane mainJPanel, boolean state, Loading loading, JPanel gD_Ban) {
        if (state) {
            loading.setPreferredSize(new Dimension(mainJPanel.getWidth(), mainJPanel.getHeight()));
            mainJPanel.setViewportView(loading);
            mainJPanel.repaint();
            mainJPanel.revalidate();
        } else {
            mainJPanel.setViewportView(gD_Ban);
        }
        mainJPanel.repaint();
        mainJPanel.revalidate();
    }

    public static void saveStorage(NhanVien nhanVien) {
        NHANVIEN = nhanVien;
    }

    public static boolean CheckContainsAbbreviation(String input, String abbreviation) {
        String replace = input.replaceAll("[^a-zA-Z ]", "");
        Pattern pattern = Pattern.compile("\\b(\\w)");
        Matcher matcher = pattern.matcher(replace);

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            result.append(matcher.group(1).toLowerCase());
        }
        if (result.toString().contains(abbreviation.toLowerCase()) || input.toLowerCase().contains(abbreviation.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    public static INhanVienDAO getNHANVIENDAO() {
        return NHANVIENDAO;
    }

    public static void setNHANVIENDAO(INhanVienDAO NHANVIENDAO) {
        AppUtils.NHANVIENDAO = NHANVIENDAO;
    }

    public static ITaiKhoanDAO getTAIKHOANDAO() {
        return TAIKHOANDAO;
    }

    public static void setTAIKHOANDAO(ITaiKhoanDAO TAIKHOANDAO) {
        AppUtils.TAIKHOANDAO = TAIKHOANDAO;
    }

    public static IBanDAO getBANDAO() {
        return BANDAO;
    }

    public static void setBANDAO(IBanDAO BANDAO) {
        AppUtils.BANDAO = BANDAO;
    }

    public static IChiTietHoaDonDAO getCHITIETHOADONDAO() {
        return CHITIETHOADONDAO;
    }

    public static void setCHITIETHOADONDAO(IChiTietHoaDonDAO CHITIETHOADONDAO) {
        AppUtils.CHITIETHOADONDAO = CHITIETHOADONDAO;
    }

    public static IHoaDonDAO getHOADONDAO() {
        return HOADONDAO;
    }

    public static void setHOADONDAO(IHoaDonDAO HOADONDAO) {
        AppUtils.HOADONDAO = HOADONDAO;
    }

    public static IChiTietKhuyenMaiDAO getCHITIETKHUYENMAIDAO() {
        return CHITIETKHUYENMAIDAO;
    }

    public static void setCHITIETKHUYENMAIDAO(IChiTietKhuyenMaiDAO CHITIETKHUYENMAIDAO) {
        AppUtils.CHITIETKHUYENMAIDAO = CHITIETKHUYENMAIDAO;
    }

    public static IKhachHangDAO getKHACHHANGDAO() {
        return KHACHHANGDAO;
    }

    public static void setKHACHHANGDAO(IKhachHangDAO KHACHHANGDAO) {
        AppUtils.KHACHHANGDAO = KHACHHANGDAO;
    }

    public static IKhuyenMaiDAO getKHUYENMAIDAO() {
        return KHUYENMAIDAO;
    }

    public static void setKHUYENMAIDAO(IKhuyenMaiDAO KHUYENMAIDAO) {
        AppUtils.KHUYENMAIDAO = KHUYENMAIDAO;
    }

    public static IMonDAO getMONDAO() {
        return MONDAO;
    }

    public static void setMONDAO(IMonDAO MONDAO) {
        AppUtils.MONDAO = MONDAO;
    }

    public static ITheThanhVienDAO getTHETHANHVIENDAO() {
        return THETHANHVIENDAO;
    }

    public static void setTHETHANHVIENDAO(ITheThanhVienDAO THETHANHVIENDAO) {
        AppUtils.THETHANHVIENDAO = THETHANHVIENDAO;
    }
}
