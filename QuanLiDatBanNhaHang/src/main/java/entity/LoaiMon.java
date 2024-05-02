/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author dmx
 */
@Entity
public class LoaiMon implements Serializable{
    private static final long serialVersionUID = 5460837009749072578L;
	@Id
    @Column(name = "MaLoaiMon",length = 4,nullable = false)
    private String maLoaiMon;
    @Column(name = "TenLoai",columnDefinition = "NVARCHAR(50)",nullable = false)
    private String tenLoai;
    @Column(name = "ChuThich",columnDefinition = "NVARCHAR(255)")
    private String chuThich;
    @OneToMany(mappedBy = "loaiMon",cascade = CascadeType.ALL)
    private List<Mon> mon;

    public LoaiMon(String tenLoai, String chuThich, List<Mon> mon) {
        this.tenLoai = tenLoai;
        this.chuThich = chuThich;
        this.mon = mon;
    }

	public String getMaLoaiMon() {
		return maLoaiMon;
	}

	public void setMaLoaiMon(String maLoaiMon) {
		this.maLoaiMon = maLoaiMon;
	}

	public String getTenLoai() {
		return tenLoai;
	}

	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}

	public String getChuThich() {
		return chuThich;
	}

	public void setChuThich(String chuThich) {
		this.chuThich = chuThich;
	}

	public List<Mon> getMon() {
		return mon;
	}

	public void setMon(List<Mon> mon) {
		this.mon = mon;
	}

	public LoaiMon() {
	}
}
