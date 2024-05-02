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
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import utils.Enum.LoaiKhuyenMai;

/**
 *
 * @author Laptop
 */
@Entity
public class KhuyenMai implements Serializable{

    private static final long serialVersionUID = -4485768801821870796L;
	@Id
    @Column(name = "MaKhuyenMai", length = 21, nullable = false)
    private String maKhuyenMai;
    @Column(name = "TenKhuyenMai", columnDefinition = "NVARCHAR(100)", nullable = false)
    private String tenKhuyenMai;
    @Column(name = "NgayBatDau", nullable = false)
    private Date ngayBatDau;
    @Column(name = "NgayKetThuc", nullable = true)
    private Date ngayKetThuc;
    @Column(name = "ChietKhau", nullable = false)
    private Double chietKhau;
    @Column(name = "LoaiKhuyenMai", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LoaiKhuyenMai loaiKhuyenMai;
    @Column(name = "GhiChu", nullable = true, columnDefinition = "NVARCHAR(255)")
    private String ghiChu;
    @OneToMany(mappedBy = "khuyenMai",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChiTietKhuyenMai> chiTietKhuyenMai;

    public KhuyenMai(String tenKhuyenMai, Date ngayBatDau, Date ngayKetThuc, Double chietKhau, LoaiKhuyenMai loaiKhuyenMai, String ghiChu) {
        this.tenKhuyenMai = tenKhuyenMai;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.chietKhau = chietKhau;
        this.loaiKhuyenMai = loaiKhuyenMai;
        this.ghiChu = ghiChu;
    }

	public String getMaKhuyenMai() {
		return maKhuyenMai;
	}

	public void setMaKhuyenMai(String maKhuyenMai) {
		this.maKhuyenMai = maKhuyenMai;
	}

	public String getTenKhuyenMai() {
		return tenKhuyenMai;
	}

	public void setTenKhuyenMai(String tenKhuyenMai) {
		this.tenKhuyenMai = tenKhuyenMai;
	}

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public Double getChietKhau() {
		return chietKhau;
	}

	public void setChietKhau(Double chietKhau) {
		this.chietKhau = chietKhau;
	}

	public LoaiKhuyenMai getLoaiKhuyenMai() {
		return loaiKhuyenMai;
	}

	public void setLoaiKhuyenMai(LoaiKhuyenMai loaiKhuyenMai) {
		this.loaiKhuyenMai = loaiKhuyenMai;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public List<ChiTietKhuyenMai> getChiTietKhuyenMai() {
		return chiTietKhuyenMai;
	}

	public void setChiTietKhuyenMai(List<ChiTietKhuyenMai> chiTietKhuyenMai) {
		this.chiTietKhuyenMai = chiTietKhuyenMai;
	}

	public KhuyenMai() {
	}
}
