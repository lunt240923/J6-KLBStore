package com.klbstore.service;

import java.util.List;

import com.klbstore.model.GiamGia;

public interface MaGiamGiaService {

    List<GiamGia> getAll();

    GiamGia getById(Integer giamgiaId);

    GiamGia create(GiamGia giamgia);

    GiamGia update(GiamGia giamgia);

    void delete(Integer giamgiaId);
    
}
