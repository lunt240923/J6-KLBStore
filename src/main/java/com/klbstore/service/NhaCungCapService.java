package com.klbstore.service;

import java.util.List;

import com.klbstore.model.NhaCungCap;

public interface NhaCungCapService {

    List<NhaCungCap> getAll();

    NhaCungCap getById(Integer nhaCungCapId);

    NhaCungCap create(NhaCungCap nhaCungCap);

    NhaCungCap update(NhaCungCap nhaCungCap);

    void delete(Integer nhaCungCapId);
    
}
