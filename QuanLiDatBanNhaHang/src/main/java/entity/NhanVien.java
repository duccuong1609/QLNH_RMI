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
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;
import utils.Enum.LoaiVaiTro;
import java.util.List;

/**
 *
 * @author Laptop
 */
@Entity
public class NhanVien implements Serializable{

    private static final long serialVersionUID = -5563164641455405164L;
	@Id
    @Column(name = "MaNhanVien", nullable = false)
    private String maNV;
    @Column(name = "HoTen", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String hoTen;
    @Column(name = "DiaChi", columnDefinition = "NVARCHAR(100)", nullable = false)
    private String diaChi;
    @Column(name = "TrangThai", nullable = false)
    private boolean trangThai;
    @Column(name = "Tuoi", nullable = false)
    private int tuoi;
    @Column(name = "SoDienThoai", nullable = false)
    private String soDienThoai;
    @Column(name = "NgayBatDauLam", nullable = false)
    private LocalDateTime ngayBatDauLam;
    @Column(name = "VaiTro", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LoaiVaiTro vaiTro;
    @Column(name = "GioiTinh", nullable = false)
    private boolean gioiTinh;
    @OneToMany(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private List<HoaDon> hoaDon;
    @OneToOne(mappedBy = "nhanVien", cascade = CascadeType.ALL)
    private TaiKhoan taiKhoan;

    public NhanVien(String hoTen, String diaChi, boolean trangThai, int tuoi, String soDienThoai, LocalDateTime ngayBatDauLam, LoaiVaiTro vaiTro, boolean gioiTinh) {
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.tuoi = tuoi;
        this.soDienThoai = soDienThoai;
        this.ngayBatDauLam = ngayBatDauLam;
        this.vaiTro = vaiTro;
        this.gioiTinh = gioiTinh;
    }

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public LocalDateTime getNgayBatDauLam() {
		return ngayBatDauLam;
	}

	public void setNgayBatDauLam(LocalDateTime ngayBatDauLam) {
		this.ngayBatDauLam = ngayBatDauLam;
	}

	public LoaiVaiTro getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(LoaiVaiTro vaiTro) {
		this.vaiTro = vaiTro;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public List<HoaDon> getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(List<HoaDon> hoaDon) {
		this.hoaDon = hoaDon;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public NhanVien() {
	}

}