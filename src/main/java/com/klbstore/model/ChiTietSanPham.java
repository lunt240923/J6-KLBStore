package com.klbstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ChiTietSanPham implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer chiTietSanPhamId;

    @Column
    private Integer soLuongTrongKho;

    @Column(length = 200)
    private String viTriLuuTru;

    @ManyToOne
    @JoinColumn(name = "sanPhamId")
    private SanPham sanPham;

    @ManyToOne
    @JoinColumn(name = "mauSacId")
    private MauSac mauSac;
}
