package com.klbstore.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.PhieuNhapDAO;
import com.klbstore.model.PhieuNhap;
import com.klbstore.service.PhieuNhapService;

@Service
public class PhieuNhapServiceImplement implements PhieuNhapService{
    @Autowired
    PhieuNhapDAO phieuNhapDAO;

    @Override
    public List<PhieuNhap> getAll() {
        return phieuNhapDAO.findAll();
    }

    @Override
    public PhieuNhap getById(Integer phieunhapId) {
        return phieuNhapDAO.findById(phieunhapId).get();
    }

    @Override
    public PhieuNhap create(PhieuNhap phieunhap) {
        return phieuNhapDAO.save(phieunhap);
    }

    @Override
    public PhieuNhap update(PhieuNhap phieunhap) {
        return phieuNhapDAO.save(phieunhap);
    }

    @Override
    public void delete(Integer phieunhapId) {
        phieuNhapDAO.deleteById(phieunhapId);
    }
}
