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
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import utils.Enum.LoaiTrangThaiMon;

/**
 *
 * @author Laptop
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@NamedQueries({
    @NamedQuery(name = "Mon.Service", query = "SELECT m FROM Mon m inner join LoaiMon l on m.loaiMon = l WHERE m.trangThai = :trangThai"),//duccuong1609 : cai nay dung theo trang thai cung duoc
    @NamedQuery(name = "Mon.Popular5",query = "SELECT SUM(c.soLuong) as SoLuong, c.mon as Mon FROM ChiTietHoaDon c inner join HoaDon h on c.hoaDon = h where h.trangThai = 0 group by c.mon ORDER BY SoLuong DESC LIMIT 5"),
    @NamedQuery(name = "Mon.Other",query = "SELECT m FROM Mon m inner join LoaiMon l on m.loaiMon = l WHERE m.trangThai = 0 AND l.maLoaiMon = 'ML06'")
})
public class Mon implements Serializable{

    @Id
    @Column(name = "MaMon", length = 12, nullable = false)
    private String maMon;
    @Column(name = "TenMon", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenMon;
    @Column(name = "GiaBan", nullable = false)
    private Double giaBan;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaLoaiMon", nullable = false)
    private LoaiMon loaiMon;
    @Column(name = "HinhAnh", length = 255, nullable = true)
    private String hinhAnh;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TrangThai", nullable = false)
    private LoaiTrangThaiMon trangThai;
    @Column(name = "GiaGoc", nullable = false)
    private Double giaGoc;
    @OneToMany(mappedBy = "mon", cascade = CascadeType.ALL)
    private List<ChiTietHoaDon> chiTietHoaDon;
    @Column(name = "DonVi", nullable = false)
    private String donVi;

    public Mon(String tenMon, Double giaGoc, LoaiMon loaiMon, String hinhAnh, LoaiTrangThaiMon trangThai, List<ChiTietHoaDon> chiTietHoaDon) {
        this.tenMon = tenMon;
        this.giaGoc = giaGoc;
        this.loaiMon = loaiMon;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
        this.chiTietHoaDon = chiTietHoaDon;
        this.giaBan = giaGoc;
    }
}
