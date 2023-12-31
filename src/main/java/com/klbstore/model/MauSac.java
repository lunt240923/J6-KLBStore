package com.klbstore.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class MauSac implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mauSacId;

    @Column(length = 100)
    private String tenMauSac;

    @Column(columnDefinition = "varchar(max)")
    private String duongDanAnh;

    @ManyToOne
    @JoinColumn(name = "sanPhamId")
    private SanPham sanPham;

    @JsonIgnore
    @OneToMany(mappedBy = "mauSac")
    private List<ChiTietSanPham> mauSacChiTietSanPhams;

}