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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class PhieuNhap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int phieuNhapId;

    @Column
    @NotNull
    private Double tongTien;

    @Column
    private LocalDate ngayNhap;

    @ManyToOne
    @JoinColumn(name = "nhaCungCapId")
    private NhaCungCap nhaCungCap;

    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;

    @JsonIgnore
    @OneToMany(mappedBy = "phieuNhap", fetch = FetchType.EAGER)
    private List<ChiTietPhieuNhap> chiTietPhieuNhaps;

}
