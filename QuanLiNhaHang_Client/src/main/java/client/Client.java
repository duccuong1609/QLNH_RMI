/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package client;

/**
 *
 * @author Windows
 */
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
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import view.GD_DangNhap;

public class Client {
    private static final String URL = "rmi://LAPTOP-6J8TDF9B:4361/";

    public static void main(String[] args) {
        try {
            utils.AppUtils.setNHANVIENDAO((INhanVienDAO) Naming.lookup(URL + "nhanVienDAO"));
            utils.AppUtils.setTAIKHOANDAO((ITaiKhoanDAO) Naming.lookup(URL + "taiKhoanDAO"));
            utils.AppUtils.setBANDAO((IBanDAO) Naming.lookup(URL + "banDAO"));
            utils.AppUtils.setCHITIETHOADONDAO((IChiTietHoaDonDAO) Naming.lookup(URL + "chiTietHoaDonDAO"));
            utils.AppUtils.setCHITIETKHUYENMAIDAO((IChiTietKhuyenMaiDAO) Naming.lookup(URL + "chiTietKhuyenMaiDAO"));
            utils.AppUtils.setHOADONDAO((IHoaDonDAO) Naming.lookup(URL + "hoaDonDAO"));
            utils.AppUtils.setKHACHHANGDAO((IKhachHangDAO) Naming.lookup(URL + "khachHangDAO"));
            utils.AppUtils.setKHUYENMAIDAO((IKhuyenMaiDAO) Naming.lookup(URL + "khuyenMainDAO"));
            utils.AppUtils.setMONDAO((IMonDAO) Naming.lookup(URL + "monDAO"));
            utils.AppUtils.setTHETHANHVIENDAO((ITheThanhVienDAO) Naming.lookup(URL + "theThanhVienDAO"));
            new GD_DangNhap().setVisible(true);
        } catch (MalformedURLException | RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
