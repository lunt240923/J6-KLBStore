package com.klbstore.service.serviceImplement;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.ChiTietDonHangDAO;
import com.klbstore.dao.DonHangDAO;
import com.klbstore.model.ChiTietDonHang;
import com.klbstore.model.DonHang;
import com.klbstore.service.DonHangService;

@Service
public class DonHangServiceImplement implements DonHangService {

    @Autowired
    DonHangDAO donHangDAO;

    @Autowired
    ChiTietDonHangDAO chiTietDonHangDAO;

    @Override
    public List<DonHang> getAll() {
        return donHangDAO.findAll();
    }

    @Override
    public DonHang getById(Integer donhangId) {
        return donHangDAO.findById(donhangId).get();
    }

    @Override
    public DonHang create(DonHang donhang) {
        return donHangDAO.save(donhang);
    }

    @Override
    public DonHang update(DonHang donhang) {
        return donHangDAO.save(donhang);
    }

    @Override
    public void delete(Integer donhangId) {
        donHangDAO.deleteById(donhangId);
    }


    @Override
    public List<ChiTietDonHang> getAllChiTietDonHang() {
        return chiTietDonHangDAO.findAll();
    }


    @Override
    public void deleteCTDonHang(Integer chiTietDonHangId) {
        chiTietDonHangDAO.deleteById(chiTietDonHangId);
    }

    @Override
    public List<ChiTietDonHang> getCTDonhangByIdDonHang(Integer donHangId) {
        DonHang donHang = donHangDAO.findById(donHangId).orElse(null);
        if (donHang != null) {
          // Lấy danh sách các sản phẩm từ trường có quan hệ @OneToMany với @JsonIgnore chú thích
          return donHang.getDonHangChiTietDonHangs();
        }
        return Collections.emptyList();
    }


    
    
}
