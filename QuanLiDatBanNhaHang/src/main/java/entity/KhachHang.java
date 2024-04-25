/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Laptop
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "KhachHang.findByPhoneNumber", query = "SELECT k FROM KhachHang k WHERE k.soDienThoai = :sdt")
})
public class KhachHang implements Serializable{
    @Id
    @Column(name="MaKhachHang",nullable = false)
    private String maKhachHang;
    @Column(name="TenKhachHang",columnDefinition = "NVARCHAR(50)",nullable = false)
    private String hoTen;
    @Column(name="SoDienThoai",nullable = false)
    private String soDienThoai;
    @Column(name="DiaChi",columnDefinition = "NVARCHAR(255)",nullable = true)
    private String diaChi;
    @OneToMany(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private List<HoaDon> hoaDon;
    @OneToOne(mappedBy = "khachHang", cascade = CascadeType.ALL)
    private TheThanhVien theThanhVien;

    public KhachHang(String tenKhachHang, String sdt, String diaChi) {
        this.hoTen = tenKhachHang;
        this.soDienThoai = sdt;
        this.diaChi = diaChi;
    }
}
