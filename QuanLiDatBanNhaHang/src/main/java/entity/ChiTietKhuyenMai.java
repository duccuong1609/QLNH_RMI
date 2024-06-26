/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author Windows
 */
@Entity
@Table(name = "ChiTietKhuyenMai")

@NamedQueries({
    @NamedQuery(name = "ChiTietKhuyenMai.delete", query = "DELETE FROM ChiTietKhuyenMai ct WHERE ct.hoaDon = :hoaDon and ct.khuyenMai = :khuyenMai")
})
public class ChiTietKhuyenMai implements Serializable{

    private static final long serialVersionUID = -403822230181711492L;
	@Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaHoaDon", nullable = false)
    private HoaDon hoaDon;
    @Id
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "MaKhuyenMai", nullable = false)
    private KhuyenMai khuyenMai;
    @Column(name = "ThanhTien")
    private Double thanhTien;

    public ChiTietKhuyenMai(HoaDon hoaDon, KhuyenMai khuyenMai) {
        this.hoaDon = hoaDon;
        this.khuyenMai = khuyenMai;
        thanhTien();
    }

    private void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public void thanhTien() {
        double total = hoaDon.getTienPhaiThu() * khuyenMai.getChietKhau();
        setThanhTien(total);
    }

	public HoaDon getHoaDon() {
		return hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public KhuyenMai getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(KhuyenMai khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public Double getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public ChiTietKhuyenMai() {
	}
    
    
}
