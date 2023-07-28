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

import com.klbstore.dao.DanhMucSanPhamDAO;
import com.klbstore.model.DanhMucSanPham;

@CrossOrigin("*")
@RestController
public class DanhMucSanPhamRestController {
    @Autowired
    DanhMucSanPhamDAO danhMucSanPhamDAO;

    @GetMapping("/rest/danhmucsanpham")
    public List<DanhMucSanPham> getAll() {
        return danhMucSanPhamDAO.findAll();
    }

    @GetMapping("/rest/danhmucsanpham/{danhMucSanPhamId}")
    public DanhMucSanPham getOne(@PathVariable("danhMucSanPhamId") Integer danhMucSanPhamId) {
        return danhMucSanPhamDAO.findById(danhMucSanPhamId).get();
    }

    @PostMapping("/rest/danhmucsanpham")
    public DanhMucSanPham post(@RequestBody DanhMucSanPham danhmucsanpham) {
        danhMucSanPhamDAO.save(danhmucsanpham);
        return danhmucsanpham;
    }

    @PutMapping("/rest/danhmucsanpham/{danhmucsanphamId}")
    public DanhMucSanPham put(@RequestBody DanhMucSanPham danhmucsanpham, @PathVariable("danhmucsanphamId") Integer danhmucsanphamId) {
        danhMucSanPhamDAO.save(danhmucsanpham);
        return danhmucsanpham;
    }

    @DeleteMapping("/rest/danhmucsanpham/{danhmucsanphamId}")
    public void delete(@PathVariable("danhmucsanphamId") Integer danhmucsanphamId) {
        danhMucSanPhamDAO.deleteById(danhmucsanphamId);
    }
}
