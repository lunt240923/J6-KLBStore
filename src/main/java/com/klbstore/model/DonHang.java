package com.klbstore.model;

import java.time.LocalDate;
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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DonHang implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer donHangId;

    @Column
    private Double giamGiaTrucTiep = 0.0;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate ngayDatHang;

    @Column(length = 200)
    private String diaChiGiaoHang;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate ngayGiaoHang;

    @Column(columnDefinition = "varchar(max)")
    private String ghiChu;

    @Column
    private Boolean tinhTrangThanhToan;

    @Column
    private Boolean tinhTrangGiaoHang;

    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;

    @ManyToOne
    @JoinColumn(name = "hinhThucThanhToan")
    private HinhThucThanhToan hinhThucThanhToan;
    
    @JsonIgnore
    @OneToMany(mappedBy = "donHang")
    private List<ChiTietDonHang> donHangChiTietDonHangs;

    @JsonIgnore
    @OneToMany(mappedBy = "donHang")
    private List<ChiTietPhieuXuat> donHangChiTietPhieuXuats;

}
