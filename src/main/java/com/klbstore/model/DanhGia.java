package com.klbstore.model;

import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DanhGia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer danhGiaSanPhamId;

    @Column(length = 200)
    private String tieuDe;

    @Column
    @Temporal(TemporalType.DATE)
    private Date ngayDanhGia;

    @Column
    private Boolean hienThi;

    @Column
    private Integer sao;

    @ManyToOne
    @JoinColumn(name = "sanPhamId", insertable = false, updatable = false)
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;
}
