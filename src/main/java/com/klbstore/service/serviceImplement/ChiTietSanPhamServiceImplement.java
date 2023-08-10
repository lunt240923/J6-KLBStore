package com.klbstore.service.serviceImplement;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.ChiTietSanPhamDAO;
import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.ChiTietDonHang;
import com.klbstore.model.ChiTietSanPham;
import com.klbstore.model.DonHang;
import com.klbstore.model.SanPham;
import com.klbstore.service.ChiTietSanPhamService;

@Service
public class ChiTietSanPhamServiceImplement implements ChiTietSanPhamService{
    @Autowired
    ChiTietSanPhamDAO chiTietSanPhamDAO;

    @Autowired
    SanPhamDAO sanPhamDAO;
    
    @Override
    public List<ChiTietSanPham> getAllCTSanPham() {
        return chiTietSanPhamDAO.findAll();
    }

    @Override
    public List<ChiTietSanPham> getCTSanPhamBySanPhamId(Integer id) {
        SanPham donHang = sanPhamDAO.findById(id).orElse(null);
        if (donHang != null) {
          // Lấy danh sách các sản phẩm từ trường có quan hệ @OneToMany với @JsonIgnore chú thích
          return donHang.getSanPhamChiTietSanPhams();
        }
        return Collections.emptyList();
    }
}
