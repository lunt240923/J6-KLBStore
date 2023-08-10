package com.klbstore.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

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
public class DanhMucSanPham implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer danhMucSanPhamId;

    @Column(length = 100)
    private String tenDanhMucSanPham;

    @ManyToOne
    @JoinColumn(name = "nhomSanPhamId")
    private NhomSanPham nhomSanPham;

    @JsonIgnore
    @OneToMany(mappedBy = "danhMucSanPham", fetch = FetchType.EAGER)
    private List<DanhMucCon> danhMucSanPhamDanhMucCons;
    @JsonIgnore
    @OneToMany(mappedBy = "danhMucSanPham", fetch = FetchType.EAGER)
    private List<SanPham> danhMucSanPhamSanPhams;
    @JsonIgnore
    @OneToMany(mappedBy = "danhMucSanPham", fetch = FetchType.EAGER)
    private List<GiamGiaDanhMuc> danhMucSanPhamGiamGiaDanhMucs;

}
