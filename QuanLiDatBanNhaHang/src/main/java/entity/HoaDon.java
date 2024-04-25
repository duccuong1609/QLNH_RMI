/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Enum.LoaiTrangThaiHoaDon;

/**
 *
 * @author Laptop
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@NamedQueries({
    @NamedQuery(name = "HoaDon.Last", query = "SELECT h FROM HoaDon h ORDER BY h.maHoaDon DESC"),
    @NamedQuery(name = "HoaDon.OnOrdering", query = "SELECT h FROM HoaDon h WHERE h.trangThai = :trangThai"),
    @NamedQuery(name = "HoaDon.updateStateById", query = "UPDATE HoaDon SET trangThai = :trangThai WHERE maHoaDon = :maHoaDon"),
    @NamedQuery(name = "HoaDon.updateBanById", query = "UPDATE HoaDon SET ban = :ban WHERE maHoaDon = :maHoaDon"),
    @NamedQuery(name = "HoaDon.findHoaDonTuNgayDenNgay", query = "SELECT h FROM HoaDon h WHERE CAST(h.ngayLapHoaDon AS date) >= CAST(:ngayBatDau AS date) AND CAST(h.ngayLapHoaDon AS date) <= CAST(:ngayKetThuc AS date)"),
    @NamedQuery(name = "HoaDon.filterByDate", query = "SELECT h FROM HoaDon h WHERE CAST(h.ngayDatBan AS date) = CAST(:date AS date)")
})
public class HoaDon implements Serializable{

    @Id
    @Column(name = "MaHoaDon", nullable = false)
    private String maHoaDon;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaNhanVien", nullable = false)
    private NhanVien nhanVien;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaKhachHang", nullable = true)
    private KhachHang khachHang;
    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChiTietKhuyenMai> chiTietKhuyenMai;
    @Column(name = "NgayLapHoaDon", nullable = false)
    private LocalDateTime ngayLapHoaDon;
    @ManyToOne
    @JoinColumn(name = "MaBan", nullable = false)
    private Ban ban;
    @Column(name = "TrangThai", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LoaiTrangThaiHoaDon trangThai;
    @Setter(AccessLevel.NONE)
    @OneToMany(mappedBy = "hoaDon", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDon;
    @Column(name = "NgayDatBan", nullable = true)
    private LocalDateTime ngayDatBan;
    @Column(name = "SoLuongNguoi", nullable = true)
    private int soLuongNguoi;
    @Column(name = "YeuCauDatMon", nullable = true, columnDefinition = "NVARCHAR(255)")
    private String yeuCauDatMon;
    @Column(name = "YeuCauKhac", nullable = true, columnDefinition = "NVARCHAR(255)")
    private String yeuCauKhac;
    @Column(name = "NgayGioNhanBan", nullable = true)
    private LocalDateTime ngayGioNhanBan;
    @Column(name = "TongThanhToan", nullable = true)
    @Setter(AccessLevel.NONE)
    private Double tongThanhToan;
    @Column(name = "TienPhaiThu", nullable = true)
    @Setter(AccessLevel.NONE)
    private Double tienPhaiThu;

    public HoaDon(NhanVien nhanVien, LocalDateTime ngayLapHoaDon, Ban ban, LoaiTrangThaiHoaDon trangThai) {
        this.nhanVien = nhanVien;
        this.ngayLapHoaDon = ngayLapHoaDon;
        this.ban = ban;
        this.trangThai = trangThai;
    }

    public HoaDon(NhanVien nhanVien, KhachHang khachHang, LocalDateTime ngayLapHoaDon) {
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    private void setTongThanhToan(double tongThanhToan) {
        this.tongThanhToan = tongThanhToan;
    }

    private void setTienPhaiThu(double tienPhaiThu) {
        this.tienPhaiThu = tienPhaiThu;
    }

    public void setChiTietHoaDon(List<ChiTietHoaDon> chiTietHoaDon) {
        this.chiTietHoaDon = chiTietHoaDon;
        tongThanhToan();
    }

    public Double getTongThanhToan() {
        return this.tongThanhToan;
    }

    public void tienPhaiThu() {
        double total = tongThanhToan;
        for (ChiTietKhuyenMai chiTiet : chiTietKhuyenMai) {
            total -= chiTiet.getThanhTien();
        }
        setTienPhaiThu(total);
    }

    public void tongThanhToan() {
        Double total = 0.0;
        for (ChiTietHoaDon detail : chiTietHoaDon) {
            total += detail.getThanhTien();
        }
        setTongThanhToan(total);
        this.tienPhaiThu = this.tongThanhToan;
    }
}
