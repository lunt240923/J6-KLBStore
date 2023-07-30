package com.klbstore.service.serviceImplement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klbstore.dao.NhaCungCapDAO;
import com.klbstore.model.NhaCungCap;
import com.klbstore.service.NhaCungCapService;

@Service
public class NhaCungCapServiceInplement implements NhaCungCapService{
    @Autowired
    NhaCungCapDAO nhaCungCapDAO;

    @Override
    public List<NhaCungCap> getAll() {
        // TODO Auto-generated method stub
        return nhaCungCapDAO.findAll();
    }

    @Override
    public NhaCungCap getById(Integer nhaCungCapId) {
        // TODO Auto-generated method stub
        return nhaCungCapDAO.findById(nhaCungCapId).get();
    }

    @Override
    public NhaCungCap create(NhaCungCap nhaCungCap) {
        // TODO Auto-generated method stub
        return nhaCungCapDAO.save(nhaCungCap);
    }

    @Override
    public NhaCungCap update(NhaCungCap nhaCungCap) {
        // TODO Auto-generated method stub
        return nhaCungCapDAO.save(nhaCungCap);
    }

    @Override
    public void delete(Integer nhaCungCapId) {
        nhaCungCapDAO.deleteById(nhaCungCapId);
    }
}
