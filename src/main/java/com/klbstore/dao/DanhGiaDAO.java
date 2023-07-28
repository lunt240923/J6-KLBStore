package com.klbstore.dao;
import org.springframework.data.jpa.repository.JpaRepository;

import com.klbstore.model.DanhGia;


public interface DanhGiaDAO extends JpaRepository<DanhGia, Integer> {
}
