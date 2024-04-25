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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Laptop
 */

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
public class TaiKhoan implements Serializable{
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
}
