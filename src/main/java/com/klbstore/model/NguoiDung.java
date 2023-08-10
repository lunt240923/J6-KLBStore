package com.klbstore.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NguoiDung implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nguoiDungId;

    @Column(length = 50)
    private String tenDangNhap;

    @Column(length = 100)
    private String matKhau;

    @Column(length = 100)
    private String email;

    @Column(length = 100)
    private String hoTen;

    @Column
    @Temporal(TemporalType.DATE)
    private Date ngaySinh;

    @Column
    private Boolean gioiTinh;

    @Column(length = 200)
    private String diaChi;

    @Column(length = 20)
    private String sdt;

    @Column
    private Boolean quyenDangNhap;
    
    @Temporal(TemporalType.DATE)
    @Column
    private Date ngayDangKy = new Date();
    @Column
    private Boolean trangThaiKhoa;

    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<DonHang> nguoiDungDonHangs;
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<SanPham> nguoiDungSanPhams;
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<PhieuXuat> nguoiDungPhieuXuats;
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<DanhGia> nguoiDungDanhGias;
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<PhieuNhap> nguoiDungPhieuNhaps;
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<GioHang> nguoiDungGioHangs;
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung", fetch = FetchType.EAGER)
    private List<MaXacNhan> nguoiDungMaXacNhans;
    @OneToMany(mappedBy = "nguoiDung")
    private List<HoatDongDangNhap> nguoiDungHoatDongDangNhaps;
    @JsonIgnore
    @OneToMany(mappedBy = "nguoiDung")
    private List<HoatDongSaiMatKhau> nguoiDungHoatDongSaiMatKhaus;
}
