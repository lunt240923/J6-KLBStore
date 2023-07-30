package com.klbstore.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.NguoiDungDAO;
import com.klbstore.model.NguoiDung;
import com.klbstore.service.NguoiDungService;

@Service
public class NguoiDungServiceImplement implements NguoiDungService{
    @Autowired
    NguoiDungDAO nguoiDungDAO;

    @Override
    public List<NguoiDung> getAll() {
        return nguoiDungDAO.findAll();
    }

    @Override
    public NguoiDung getById(Integer nguoiDungId) {
        return nguoiDungDAO.findById(nguoiDungId).get();
    }

    @Override
    public NguoiDung create(NguoiDung nguoiDung) {
        return nguoiDungDAO.save(nguoiDung);
    }

    @Override
    public NguoiDung update(NguoiDung nguoiDung) {
        return nguoiDungDAO.save(nguoiDung);
    }

    @Override
    public void delete(Integer nguoiDungId) {
        nguoiDungDAO.deleteById(nguoiDungId);
    }
    
}
