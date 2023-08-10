package com.klbstore.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ThongKeDoanhThu implements Serializable{
    @Id
     String tenSanPham;
     Date ngayDatHang;
    Double giaBan;
    Integer soLuong;
}
