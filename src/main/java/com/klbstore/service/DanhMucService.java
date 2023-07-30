package com.klbstore.service;

import java.util.List;

import com.klbstore.model.DanhMucSanPham;

public interface DanhMucService {

    List<DanhMucSanPham> getAll();

    DanhMucSanPham getById(Integer danhMucSanPhamId);

    DanhMucSanPham create(DanhMucSanPham danhmucsanpham);

    DanhMucSanPham update(DanhMucSanPham danhmucsanpham);

    void delete(Integer danhmucsanphamId);
    
}
