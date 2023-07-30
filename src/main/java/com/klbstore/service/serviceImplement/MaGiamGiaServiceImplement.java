package com.klbstore.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.GiamGiaDAO;
import com.klbstore.model.GiamGia;
import com.klbstore.service.MaGiamGiaService;

@Service
public class MaGiamGiaServiceImplement implements MaGiamGiaService{
    @Autowired
    GiamGiaDAO giamGiaDAO;

    @Override
    public List<GiamGia> getAll() {
        return giamGiaDAO.findAll();
    }

    @Override
    public GiamGia getById(Integer giamgiaId) {
        return giamGiaDAO.findById(giamgiaId).get();
    }

    @Override
    public GiamGia create(GiamGia giamgia) {
        return giamGiaDAO.save(giamgia);
    }

    @Override
    public GiamGia update(GiamGia giamgia) {
        return giamGiaDAO.save(giamgia);
    }

    @Override
    public void delete(Integer giamgiaId) {
        giamGiaDAO.deleteById(giamgiaId);
    }
    
}
