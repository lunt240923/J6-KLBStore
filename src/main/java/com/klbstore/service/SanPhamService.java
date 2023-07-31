package com.klbstore.service;

import java.util.List;

import com.klbstore.model.SanPham;

public interface SanPhamService {

    List<SanPham> getAll();

    SanPham getById(Integer sanPhamId);

    SanPham create(SanPham sanPham);

    SanPham update(SanPham sanPham);

    void delete(Integer sanPhamId);
    
}
