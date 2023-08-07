
package com.klbstore.service;

import java.util.List;

import com.klbstore.model.ChiTietDonHang;
import com.klbstore.model.DonHang;

public interface DonHangService {

    List<DonHang> getAll();

    DonHang getById(Integer donhangId);

    DonHang create(DonHang donhang);

    DonHang update(DonHang donhang);

    void delete(Integer donhangId);

    List<ChiTietDonHang> getCTDonhangByIdDonHang(Integer categoryId);

    List<ChiTietDonHang> getAllChiTietDonHang();

    void deleteCTDonHang(Integer chiTietDonHangId);
    
}
