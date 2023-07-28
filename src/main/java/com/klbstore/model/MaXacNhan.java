package com.klbstore.model;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class MaXacNhan {
    @Id
    @Column(nullable = false, updatable = false, length = 100)
    private String maOtp;

    @Column
    private Boolean daXacNhan;

    @Column(columnDefinition = "datetime2")
    private OffsetDateTime ngayTaoOtp;

    @Column(columnDefinition = "datetime2")
    private OffsetDateTime hanHieuLucOtp;

    @ManyToOne
    @JoinColumn(name = "nguoiDungId")
    private NguoiDung nguoiDung;

}
