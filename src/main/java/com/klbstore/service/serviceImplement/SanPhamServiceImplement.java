package com.klbstore.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.SanPham;
import com.klbstore.service.SanPhamService;

@Service
public class SanPhamServiceImplement implements SanPhamService{
    @Autowired
    SanPhamDAO sanPhamDAO;

    @Override
    public List<SanPham> getAll() {
        return sanPhamDAO.findAll();
    }

    @Override
    public SanPham getById(Integer sanPhamId) {
        return sanPhamDAO.findById(sanPhamId).get();
    }

    @Override
    public SanPham create(SanPham sanPham) {
        return sanPhamDAO.save(sanPham);
    }

    @Override
    public SanPham update(SanPham sanPham) {
        return sanPhamDAO.save(sanPham);
    }

    @Override
    public void delete(Integer sanPhamId) {
        sanPhamDAO.deleteById(sanPhamId);
    }
}
