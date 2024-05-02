/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

/**
 *
 * @author Windows
 */
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
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Server {

    private static final String URL = "rmi://DESKTOP-B2M7ONV:4361/";
    public static void main(String[] args) {
        try {
            Context context = new InitialContext();
            TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
            NhanVienDAO nhanVienDAO = new NhanVienDAO();
            BanDAO banDAO = new BanDAO();
            ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAO();
            ChiTietKhuyenMaiDAO chiTietKhuyenMaiDAO = new ChiTietKhuyenMaiDAO();
            HoaDonDAO hoaDonDAO = new HoaDonDAO();
            KhachHangDAO khachHangDAO = new KhachHangDAO();
            KhuyenMainDAO khuyenMainDAO = new KhuyenMainDAO();
            MonDAO monDAO = new MonDAO();
            TheThanhVienDAO theThanhVienDAO = new TheThanhVienDAO();
            LocateRegistry.createRegistry(4361);
            context.bind(URL + "taiKhoanDAO", taiKhoanDAO);
            context.bind(URL + "nhanVienDAO", nhanVienDAO);
            context.bind(URL + "banDAO", banDAO);
            context.bind(URL + "chiTietHoaDonDAO", chiTietHoaDonDAO);
            context.bind(URL + "chiTietKhuyenMaiDAO", chiTietKhuyenMaiDAO);
            context.bind(URL + "hoaDonDAO", hoaDonDAO);
            context.bind(URL + "khachHangDAO", khachHangDAO);
            context.bind(URL + "khuyenMainDAO", khuyenMainDAO);
            context.bind(URL + "monDAO", monDAO);
            context.bind(URL + "theThanhVienDAO", theThanhVienDAO);
            System.out.println("Server is running...");
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
        }

    }

}
