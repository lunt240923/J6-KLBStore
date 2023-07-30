package com.klbstore.service;

import java.util.List;

import com.klbstore.model.NguoiDung;

public interface NguoiDungService {

    List<NguoiDung> getAll();

    NguoiDung getById(Integer nguoiDungId);

    NguoiDung create(NguoiDung nguoiDung);

    NguoiDung update(NguoiDung nguoiDung);

    void delete(Integer nguoiDungId);
    
}
