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
import lombok.ToString;
import utils.Enum.LoaiTrangThai;

/**
 *
 * @author Laptop
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@NamedQueries({
    @NamedQuery(name = "Ban.groupByMaTang", query = "SELECT b.tang, COUNT(b.tang) AS soBan FROM Ban b GROUP BY b.tang"),
    @NamedQuery(name = "Ban.findByFloor", query = "SELECT b FROM Ban b WHERE b.tang = :maTang"),
    @NamedQuery(name = "Ban.findTableByStateAndFloor", query = "SELECT b FROM Ban b WHERE b.trangThai = :trangThai AND b.tang = :maTang"),
    @NamedQuery(name = "Ban.findTableByState", query = "SELECT b FROM Ban b WHERE b.trangThai = :trangThai"),
    @NamedQuery(name = "Ban.updateStateById", query = "UPDATE Ban SET trangThai = :trangThai WHERE maBan = :maBan")
})
public class Ban implements Serializable{
    @Id
    @Column(name = "MaBan", nullable = false)
    private String maBan;
    @Column(name = "SoGhe", nullable = false)
    private int soGhe;
    @Column(name = "TrangThai", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private LoaiTrangThai trangThai;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MaBanGop", nullable = true)
    private Ban banGop;
    @Column(name = "Tang", nullable = false)
    private String tang;
    @OneToMany(mappedBy = "ban",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HoaDon> hoaDon;
}
