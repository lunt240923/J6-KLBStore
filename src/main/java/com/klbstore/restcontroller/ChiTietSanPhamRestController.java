package com.klbstore.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klbstore.model.ChiTietDonHang;
import com.klbstore.model.ChiTietSanPham;
import com.klbstore.service.ChiTietSanPhamService;
@RestController
@CrossOrigin("*")
public class ChiTietSanPhamRestController {
    
    @Autowired
    ChiTietSanPhamService chiTietSanPhamService;

    @GetMapping("/rest/ctsanpham")
    public List<ChiTietSanPham> getAllCTSanPham() {
        return chiTietSanPhamService.getAllCTSanPham();
    }

    @GetMapping("/rest/ctsanpham/{id}")
    public ResponseEntity<List<ChiTietSanPham>> getCTSanPhamBySanPhamId(@PathVariable("id") Integer id) {
        List<ChiTietSanPham> products = chiTietSanPhamService.getCTSanPhamBySanPhamId(id);
        return ResponseEntity.ok(products);
    }
}
