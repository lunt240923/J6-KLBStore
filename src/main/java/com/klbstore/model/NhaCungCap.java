package com.klbstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.io.Serializable;



import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class NhaCungCap implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nhaCungCapId;

    @Column(length = 100)
    private String tenNhaCungCap;

    @Column(length = 100)
    private String tenGiaoDich;

    @Column(length = 20)
    private String sdt;

    @Column(length = 100)
    private String email;

    @Column
    private Boolean hienThi;

    @JsonIgnore
    @OneToMany(mappedBy = "nhaCungCap")
    private Set<PhieuNhap> phieuNhaps;

}
