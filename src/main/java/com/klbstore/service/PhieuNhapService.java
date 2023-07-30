package com.klbstore.service;

import java.util.List;

import com.klbstore.model.PhieuNhap;

public interface PhieuNhapService {

    List<PhieuNhap> getAll();

    PhieuNhap getById(Integer phieunhapId);

    PhieuNhap create(PhieuNhap phieunhap);

    PhieuNhap update(PhieuNhap phieunhap);

    void delete(Integer phieunhapId);
    
}
