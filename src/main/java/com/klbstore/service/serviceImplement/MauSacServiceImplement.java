package com.klbstore.service.serviceImplement;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.MauSacDAO;
import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.MauSac;
import com.klbstore.model.SanPham;
import com.klbstore.service.MauSacService;

@Service
public class MauSacServiceImplement implements MauSacService{

    @Autowired
    MauSacDAO mauSacDAO;

    @Autowired
    SanPhamDAO sanPhamDAO;

    @Override
    public List<MauSac> getMauSacBySanPhamId(Integer sanphamid) {
        SanPham sanPham = sanPhamDAO.findById(sanphamid).orElse(null);
        if (sanPham != null) {
          // Lấy danh sách các sản phẩm từ trường có quan hệ @OneToMany với @JsonIgnore chú thích
          return sanPham.getSanPhamMauSacs();
        }
        return Collections.emptyList();
    }
    
}
