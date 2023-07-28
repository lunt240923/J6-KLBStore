package com.klbstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klbstore.dao.CauHinhDienThoaiDAO;
import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.CauHinhDienThoai;
import com.klbstore.model.SanPham;

@CrossOrigin("*")
@RestController
public class SanPhamRestController {
    @Autowired
    SanPhamDAO sanPhamDAO;

    @GetMapping("/rest/sanpham")
    public List<SanPham> getAll() {
        return sanPhamDAO.findAll();
    }

    @GetMapping("/rest/sanpham/{sanPhamId}")
    public SanPham getOne(@PathVariable("sanPhamId") Integer sanPhamId) {
        return sanPhamDAO.findById(sanPhamId).get();
    }

    @PostMapping("/rest/sanpham")
    public SanPham post(@RequestBody SanPham sanPham) {
        sanPhamDAO.save(sanPham);
        return sanPham;
    }

    @PutMapping("/rest/sanpham/{sanPhamId}")
    public SanPham put(@RequestBody SanPham sanPham, @PathVariable("sanPhamId") Integer sanPhamId) {
        sanPhamDAO.save(sanPham);
        return sanPham;
    }

    @DeleteMapping("/rest/sanpham/{sanPhamId}")
    public void delete(@PathVariable("sanPhamId") Integer sanPhamId) {
        sanPhamDAO.deleteById(sanPhamId);
    }
}
