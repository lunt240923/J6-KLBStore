package com.klbstore.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
@Table(name = "SanPham")
public class SanPham implements Serializable {

    @Id
    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sanPhamId;

    @Column
    private String tenSanPham;

    @Column
    private String moTa;

    @Column
    private String xuatSu;

    @Column
    private Double giaBan;

    @Column
    private Integer soLuotXem = 0;

    @Column
    private Boolean conHang;

    @Column
    private Boolean noiBat;

    @Column
    private Boolean hienThi;

    @Column
    private LocalDate ngayTao;

    @ManyToOne
    @JoinColumn(name = "danhMucSanPhamId")
    private DanhMucSanPham danhMucSanPham;

    @ManyToOne
    @JoinColumn(name = "danhMucConId")
    private DanhMucCon danhMucCon;

    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<MauSac> sanPhamMauSacs;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham" , fetch = FetchType.EAGER)
    private List<ChiTietSanPham> sanPhamChiTietSanPhams;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<CauHinhLaptop> sanPhamCauHinhLaptops;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<CauHinhDienThoai> sanPhamCauHinhDienThoais;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<ChiTietDonHang> sanPhamChiTietDonHangs;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<ChiTietPhieuXuat> sanPhamChiTietPhieuXuats;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<DanhGia> sanPhamDanhGias;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<AnhSanPham> sanPhamAnhSanPhams;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<ChiTietPhieuNhap> sanPhamChiTietPhieuNhaps;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<ChiTietGioHang> sanPhamChiTietGioHangs;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<GiamGiaSanPham> sanPhamGiamGiaSanPhams;

    @JsonIgnore
    @OneToMany(mappedBy = "sanPham", fetch = FetchType.EAGER)
    private List<GiamGiaTrucTiep> sanPhamGiamGiaTrucTieps;

}

