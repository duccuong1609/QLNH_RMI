/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import java.io.Serializable;

/**
 *
 * @author dmx
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "ChiTietHoaDon.HoaDon", query = "SELECT c FROM ChiTietHoaDon c WHERE c.hoaDon = :hoaDon"),
    @NamedQuery(name = "ChiTietHoaDon.DS_SoLuong", query = "SELECT c FROM ChiTietHoaDon c WHERE c.soLuong = :soLuong"),
    @NamedQuery(name = "ChiTietHoaDon.DS_MonThinhHanh", query = "SELECT c.mon, SUM(c.soLuong) FROM ChiTietHoaDon c JOIN c.hoaDon h WHERE h.trangThai = 0 GROUP BY c.mon"),})
public class ChiTietHoaDon implements Serializable{

    private static final long serialVersionUID = -7484458893950116657L;
	@Id
    @ManyToOne
    @JoinColumn(name = "MaMon", nullable = false)
    private Mon mon;
    @Id
    @ManyToOne
    @JoinColumn(name = "MaHoaDon", nullable = false)
    private HoaDon hoaDon;
    @Column(name = "SoLuong", nullable = false)
    private int soLuong;
    @Column(name = "GhiChu", columnDefinition = "NVARCHAR(255)")
    private String ghiChu;
    @Column(name = "ThanhTien")
    private Double thanhTien;
    
    public Mon getMon() {
		return mon;
	}

	public void setMon(Mon mon) {
		this.mon = mon;
	}

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public Double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public int getSoLuong() {
		return soLuong;
	}
	

	public ChiTietHoaDon() {
	}

	public ChiTietHoaDon(Mon mon, HoaDon hoaDon, int soLuong) {
        this.mon = mon;
        this.hoaDon = hoaDon;
        this.soLuong = soLuong;
        thanhTien();
    }

    public ChiTietHoaDon(Mon mon, HoaDon hoaDon, String ghiChu, int soLuong) {
        this.mon = mon;
        this.hoaDon = hoaDon;
        this.soLuong = soLuong;
        this.ghiChu = ghiChu;
        thanhTien();
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
        thanhTien();
    }

    public void thanhTien() {
        Double tong = this.mon.getGiaBan() * this.soLuong;
        setThanhTien(tong);
    }
}
