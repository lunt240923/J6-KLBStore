package com.klbstore.restcontroller;

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

import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.ChiTietSanPham;
import com.klbstore.model.SanPham;
import com.klbstore.service.SanPhamService;

@CrossOrigin("*")
@RestController
public class SanPhamRestController {
    @Autowired
    SanPhamService sanPhamService;

    @GetMapping("/rest/sanpham")
    public List<SanPham> getAll() {
        return sanPhamService.getAll();
    }

    @GetMapping("/rest/sanpham/{sanPhamId}")
    public SanPham getOne(@PathVariable("sanPhamId") Integer sanPhamId) {
        return sanPhamService.getById(sanPhamId);
    }

    @PostMapping("/rest/sanpham")
    public SanPham post(@RequestBody SanPham sanPham) {
        sanPhamService.create(sanPham);
        return sanPham;
    }

    @PutMapping("/rest/sanpham/{sanPhamId}")
    public SanPham put(@RequestBody SanPham sanPham, @PathVariable("sanPhamId") Integer sanPhamId) {
        sanPhamService.update(sanPham);
        return sanPham;
    }

    @DeleteMapping("/rest/sanpham/{sanPhamId}")
    public void delete(@PathVariable("sanPhamId") Integer sanPhamId) {
        sanPhamService.delete(sanPhamId);
    }
}
