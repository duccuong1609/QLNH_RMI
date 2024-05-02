/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import entity.Ban;
import entity.HoaDon;
import entity.NhanVien;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author dmx
 */
public interface IHoaDonDAO<T> extends GenericDAO<T>, Remote {

    HoaDon findLast() throws RemoteException;

    boolean insertHoaDon(HoaDon hoadon) throws RemoteException;

    List<HoaDon> findOnOrder() throws RemoteException;

//    find by state (NDK)
    List<HoaDon> findByState(Enum state) throws RemoteException;

    boolean updateStateById(String id, Enum state) throws RemoteException;

//    use to move from this table to other table
    boolean updateBanById(String id, Ban ban) throws RemoteException;

    void createInvoice(String hoaDon, double tienKhachTra, double tienThua) throws RemoteException;

    double getTongDoanhThu(NhanVien nv) throws RemoteException;

    List<HoaDon> findHoaDonTuNgayDenNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) throws RemoteException;

    int getTongHoaDonTheoNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) throws RemoteException;

    double getTongTienHoaDonTheoNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) throws RemoteException;

    int getTongSoLuongMonTheoNgay(LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc) throws RemoteException;

    List<HoaDon> filterByDate(LocalDate date) throws RemoteException;

    public int getTongHoaDon(NhanVien nv) throws RemoteException;

    public DecimalFormat getFormatter() throws RemoteException;
}
