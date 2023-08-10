package com.klbstore.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;

import java.io.Serializable;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GiamGia implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer giamGiaId;

    @Column(length = 100)
    private String tenGiamGia;

    @Column(columnDefinition = "varchar(max)")
    private String moTa;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate ngayBatDau;

    @Temporal(TemporalType.DATE)
    @Column
    private LocalDate ngayKetThuc;

    @Column(length = 100)
    private String mucGiamGia;

    @Column
    private Boolean hienThi;

    @JsonIgnore
    @OneToMany(mappedBy = "giamGia", fetch = FetchType.EAGER)
    private List<GiamGiaSanPham> giamGiaGiamGiaSanPhams;

    @JsonIgnore
    @OneToMany(mappedBy = "giamGia", fetch = FetchType.EAGER)
    private List<GiamGiaDanhMuc> giamGiaGiamGiaDanhMucs;

    @JsonIgnore
    @OneToMany(mappedBy = "giamGia", fetch = FetchType.EAGER)
    private List<GiamGiaDanhMucCon> giamGiaGiamGiaDanhMucCons;

}
