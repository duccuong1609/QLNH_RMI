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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author dmx
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoaiMon implements Serializable{
    @Id
    @Column(name = "MaLoaiMon",length = 4,nullable = false)
    private String maLoaiMon;
    @Column(name = "TenLoai",columnDefinition = "NVARCHAR(50)",nullable = false)
    private String tenLoai;
    @Column(name = "ChuThich",columnDefinition = "NVARCHAR(255)")
    private String chuThich;
    @OneToMany(mappedBy = "loaiMon",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Mon> mon;

    public LoaiMon(String tenLoai, String chuThich, List<Mon> mon) {
        this.tenLoai = tenLoai;
        this.chuThich = chuThich;
        this.mon = mon;
    }
}
