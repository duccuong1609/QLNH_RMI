/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import utils.Enum.LoaiTheThanhVien;

/**
 *
 * @author dmx
 */
@Entity

public class TheThanhVien implements Serializable{
    private static final long serialVersionUID = 444600401799101675L;
	@Id
    @Column(name="MaThe",nullable = false)
    private String maThe;
    @Column(name="DiemTich",nullable = false)
    private Double diemTich;
    @OneToOne
    @JoinColumn(name="MaKhachHang",unique = true,nullable = false)
    private KhachHang khachHang;
    @Enumerated(EnumType.ORDINAL)
    @Column(name="LoaiThe")
    private LoaiTheThanhVien loaiThe;

    public TheThanhVien(Double diemTich, KhachHang khachHang, LoaiTheThanhVien loaiThe) {
        this.diemTich = diemTich;
        this.khachHang = khachHang;
        this.loaiThe = loaiThe;
    }

	public String getMaThe() {
		return maThe;
	}

	public void setMaThe(String maThe) {
		this.maThe = maThe;
	}

	public Double getDiemTich() {
		return diemTich;
	}

	public void setDiemTich(Double diemTich) {
		this.diemTich = diemTich;
	}

	public KhachHang getKhachHang() {
		return khachHang;
	}

	public void setKhachHang(KhachHang khachHang) {
		this.khachHang = khachHang;
	}

	public LoaiTheThanhVien getLoaiThe() {
		return loaiThe;
	}

	public void setLoaiThe(LoaiTheThanhVien loaiThe) {
		this.loaiThe = loaiThe;
	}

	public TheThanhVien() {
	}
    
}
