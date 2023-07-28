package com.klbstore.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.klbstore.model.NhaCungCap;


public interface NhaCungCapDAO extends JpaRepository<NhaCungCap, Integer> {
    @Query("SELECT n FROM NhaCungCap n WHERE "+
    "(:nhaCungCapId IS NULL OR n.nhaCungCapId = :nhaCungCapId) " +
    "AND n.tenNhaCungCap LIKE %:tenNhaCungCap% " +
    "AND n.tenGiaoDich LIKE %:tenGiaoDich% " +
    "AND n.sdt LIKE %:sdt% " +
    "AND n.email LIKE %:email% " +
    "AND (:hienThi IS NULL OR n.hienThi = :hienThi)"
    )
    List<NhaCungCap> getAllNhaCungCap(
        @Param("nhaCungCapId") Integer nhaCungCapId,
        @Param("tenNhaCungCap") String tenNhaCungCap,
        @Param("tenGiaoDich") String tenGiaoDich,
        @Param("sdt") String sdt,
        @Param("email") String email,
        @Param("hienThi") Boolean hienThi
    );
    // @Query("SELECT u FROM NhaCungCap u")
    // List<NhaCungCap> getAllNhaCungCap();
}
