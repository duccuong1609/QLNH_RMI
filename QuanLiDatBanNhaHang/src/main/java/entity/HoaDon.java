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
import utils.Enum.LoaiTrangThaiHoaDon;

/**
 *
 * @author Laptop
 */
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

    private static final long serialVersionUID = 1665117497841176559L;
	@Id
    @Column(name = "MaHoaDon", nullable = false)
    private String maHoaDon;
    @ManyToOne(fetch = FetchType.EAGER)
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
    private Double tongThanhToan;
    @Column(name = "TienPhaiThu", nullable = true)
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

    public void setTongThanhToan(double tongThanhToan) {
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

    public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public List<ChiTietKhuyenMai> getChiTietKhuyenMai() {
		return chiTietKhuyenMai;
	}

	public void setChiTietKhuyenMai(List<ChiTietKhuyenMai> chiTietKhuyenMai) {
		this.chiTietKhuyenMai = chiTietKhuyenMai;
	}

	public LocalDateTime getNgayLapHoaDon() {
		return ngayLapHoaDon;
	}

	public void setNgayLapHoaDon(LocalDateTime ngayLapHoaDon) {
		this.ngayLapHoaDon = ngayLapHoaDon;
	}

	public Ban getBan() {
		return ban;
	}

	public void setBan(Ban ban) {
		this.ban = ban;
	}

	public LoaiTrangThaiHoaDon getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(LoaiTrangThaiHoaDon trangThai) {
		this.trangThai = trangThai;
	}

	public LocalDateTime getNgayDatBan() {
		return ngayDatBan;
	}

	public void setNgayDatBan(LocalDateTime ngayDatBan) {
		this.ngayDatBan = ngayDatBan;
	}

	public int getSoLuongNguoi() {
		return soLuongNguoi;
	}

	public void setSoLuongNguoi(int soLuongNguoi) {
		this.soLuongNguoi = soLuongNguoi;
	}

	public String getYeuCauDatMon() {
		return yeuCauDatMon;
	}

	public void setYeuCauDatMon(String yeuCauDatMon) {
		this.yeuCauDatMon = yeuCauDatMon;
	}

	public String getYeuCauKhac() {
		return yeuCauKhac;
	}

	public void setYeuCauKhac(String yeuCauKhac) {
		this.yeuCauKhac = yeuCauKhac;
	}

	public LocalDateTime getNgayGioNhanBan() {
		return ngayGioNhanBan;
	}

	public void setNgayGioNhanBan(LocalDateTime ngayGioNhanBan) {
		this.ngayGioNhanBan = ngayGioNhanBan;
	}

	public Double getTienPhaiThu() {
		return tienPhaiThu;
	}

	public void setTienPhaiThu(Double tienPhaiThu) {
		this.tienPhaiThu = tienPhaiThu;
	}

	public List<ChiTietHoaDon> getChiTietHoaDon() {
		return chiTietHoaDon;
	}

	public void setTongThanhToan(Double tongThanhToan) {
		this.tongThanhToan = tongThanhToan;
	}

	public void tongThanhToan() {
        Double total = 0.0;
        for (ChiTietHoaDon detail : chiTietHoaDon) {
            total += detail.getThanhTien();
        }
        setTongThanhToan(total);
        this.tienPhaiThu = this.tongThanhToan;
    }

	public HoaDon() {
	}
}
