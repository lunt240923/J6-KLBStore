package com.klbstore.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klbstore.model.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, Integer> {
    Page<SanPham> findAllByTenSanPhamLikeAndHienThi(String keywords, boolean hienThi, Pageable pageable);

    Page<SanPham> findAllByHienThiAndDanhMucSanPham_NhomSanPham_NhomSanPhamId(boolean hienThi, int nhomSanPhamId,
            Pageable pageable);

    @Query("SELECT s FROM SanPham s "
            + "LEFT JOIN s.sanPhamDanhGias d "
            + "WHERE s.danhMucSanPham.nhomSanPham.nhomSanPhamId = :nhomSanPhamId "
            + "AND s.hienThi = true "
            + "GROUP BY s "
            + "ORDER BY COALESCE(AVG(d.sao), 0) ASC")
    Page<SanPham> findAllByNhomSanPhamIdOrderByAverageRatingAsc(
            int nhomSanPhamId,
            Pageable pageable);
    @Query("SELECT s FROM SanPham s "
            + "LEFT JOIN s.sanPhamDanhGias d "
            + "WHERE s.danhMucSanPham.nhomSanPham.nhomSanPhamId = :nhomSanPhamId "
            + "AND s.hienThi = true "
            + "GROUP BY s "
            + "ORDER BY COALESCE(AVG(d.sao), 0) DESC")
    Page<SanPham> findAllByNhomSanPhamIdOrderByAverageRatingDesc(
            int nhomSanPhamId,
            Pageable pageable);
@Query("SELECT s FROM SanPham s "
        + "LEFT JOIN s.sanPhamDanhGias d "
        + "WHERE s.hienThi = true "
        + "AND s.tenSanPham LIKE %:keywords% "
        + "GROUP BY s "
        + "ORDER BY COALESCE(AVG(d.sao), 0) ASC")
Page<SanPham> findAllByTenSanPhamOrderByAverageRatingAsc(
        String keywords,
        Pageable pageable);
@Query("SELECT s FROM SanPham s "
        + "LEFT JOIN s.sanPhamDanhGias d "
        + "WHERE s.hienThi = true "
        + "AND s.tenSanPham LIKE %:keywords% "
        + "GROUP BY s "
        + "ORDER BY COALESCE(AVG(d.sao), 0) DESC")
Page<SanPham> findAllByTenSanPhamOrderByAverageRatingDesc(
        String keywords,
        Pageable pageable);

}
