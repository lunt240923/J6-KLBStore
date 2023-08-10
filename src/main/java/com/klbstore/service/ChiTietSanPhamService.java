package com.klbstore.service;

import java.util.List;

import com.klbstore.model.ChiTietSanPham;

public interface ChiTietSanPhamService {
    
    List<ChiTietSanPham> getAllCTSanPham();

    List<ChiTietSanPham> getCTSanPhamBySanPhamId(Integer id);
}
