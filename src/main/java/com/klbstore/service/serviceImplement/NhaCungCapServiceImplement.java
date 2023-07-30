package com.klbstore.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.NhaCungCapDAO;
import com.klbstore.model.NhaCungCap;
import com.klbstore.service.NhaCungCapService;

@Service
public class NhaCungCapServiceImplement implements NhaCungCapService{
    @Autowired
    NhaCungCapDAO nhaCungCapDAO;

    @Override
    public List<NhaCungCap> getAll() {
        return nhaCungCapDAO.findAll();
    }

    @Override
    public NhaCungCap getById(Integer nhaCungCapId) {
        return nhaCungCapDAO.findById(nhaCungCapId).get();
    }

    @Override
    public NhaCungCap create(NhaCungCap nhaCungCap) {
        return nhaCungCapDAO.save(nhaCungCap);
    }

    @Override
    public NhaCungCap update(NhaCungCap nhaCungCap) {
        return nhaCungCapDAO.save(nhaCungCap);
    }

    @Override
    public void delete(Integer nhaCungCapId) {
        nhaCungCapDAO.deleteById(nhaCungCapId);
    }
}
