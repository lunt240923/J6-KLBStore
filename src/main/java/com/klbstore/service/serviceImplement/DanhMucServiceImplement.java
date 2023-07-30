package com.klbstore.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.DanhMucSanPhamDAO;
import com.klbstore.model.DanhMucSanPham;
import com.klbstore.service.DanhMucService;

@Service
public class DanhMucServiceImplement implements DanhMucService{
    @Autowired
    DanhMucSanPhamDAO danhMucSanPhamDAO;

    @Override
    public List<DanhMucSanPham> getAll() {
        return danhMucSanPhamDAO.findAll();
    }

    @Override
    public DanhMucSanPham getById(Integer danhMucSanPhamId) {
        return danhMucSanPhamDAO.findById(danhMucSanPhamId).get();
    }

    @Override
    public DanhMucSanPham create(DanhMucSanPham danhmucsanpham) {
        return danhMucSanPhamDAO.save(danhmucsanpham);
    }

    @Override
    public DanhMucSanPham update(DanhMucSanPham danhmucsanpham) {
        return danhMucSanPhamDAO.save(danhmucsanpham);
    }

    @Override
    public void delete(Integer danhmucsanphamId) {
        danhMucSanPhamDAO.deleteById(danhmucsanphamId);
    }
}
