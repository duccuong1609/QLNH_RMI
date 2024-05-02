/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author Laptop
 */

@Entity
public class TaiKhoan implements Serializable{
    private static final long serialVersionUID = -741578179816393464L;
	@Id
    @OneToOne
    @JoinColumn(name = "MaNhanVien", unique = true, nullable = false)
    private NhanVien nhanVien;
    @Column(name = "MatKhau", nullable = false)
    private String matKhau;
    @Column(name = "NgayTaoTaiKhoan", nullable = false)
    private LocalDateTime ngayTao;
    @Column(name = "NgayKetThuc", nullable = true)
    private LocalDateTime ngayKetThuc;
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public String getMatKhau() {
		return matKhau;
	}
	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}
	public LocalDateTime getNgayTao() {
		return ngayTao;
	}
	public void setNgayTao(LocalDateTime ngayTao) {
		this.ngayTao = ngayTao;
	}
	public LocalDateTime getNgayKetThuc() {
		return ngayKetThuc;
	}
	public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}
	public TaiKhoan() {
	}
    
    
}
