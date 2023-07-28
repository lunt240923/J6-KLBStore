package com.klbstore.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.klbstore.model.DonHang;
import com.klbstore.model.ThongKeDoanhThu;



public interface DonHangDAO extends JpaRepository<DonHang, Integer> {
   
}
