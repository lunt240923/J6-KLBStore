package com.klbstore.service;

import java.util.List;

import com.klbstore.model.MauSac;

public interface MauSacService {

    List<MauSac> getMauSacBySanPhamId(Integer sanphamid);
    
}
