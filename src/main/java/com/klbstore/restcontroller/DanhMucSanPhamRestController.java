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

import com.klbstore.dao.NhomSanPhamDAO;
import com.klbstore.model.DanhMucSanPham;
import com.klbstore.model.NhomSanPham;
import com.klbstore.service.DanhMucService;

@CrossOrigin("*")
@RestController
public class DanhMucSanPhamRestController {
    @Autowired
    DanhMucService danhMucService;

    @Autowired
    NhomSanPhamDAO nhomSanPhamDAO;

    //get all nhóm sản phẩm
    @GetMapping("/rest/nhomsanpham")
    public List<NhomSanPham> getAllNhomSanPham() {
        return nhomSanPhamDAO.findAll();
    }

    @GetMapping("/rest/danhmucsanpham")
    public List<DanhMucSanPham> getAll() {
        return danhMucService.getAll();
    }

    @GetMapping("/rest/danhmucsanpham/{danhMucSanPhamId}")
    public DanhMucSanPham getOne(@PathVariable("danhMucSanPhamId") Integer danhMucSanPhamId) {
        return danhMucService.getById(danhMucSanPhamId);
    }

    @PostMapping("/rest/danhmucsanpham")
    public DanhMucSanPham post(@RequestBody DanhMucSanPham danhmucsanpham) {
        danhMucService.create(danhmucsanpham);
        return danhmucsanpham;
    }

    @PutMapping("/rest/danhmucsanpham/{danhmucsanphamId}")
    public DanhMucSanPham put(@RequestBody DanhMucSanPham danhmucsanpham, @PathVariable("danhmucsanphamId") Integer danhmucsanphamId) {
        danhMucService.update(danhmucsanpham);
        return danhmucsanpham;
    }

    @DeleteMapping("/rest/danhmucsanpham/{danhmucsanphamId}")
    public void delete(@PathVariable("danhmucsanphamId") Integer danhmucsanphamId) {
        danhMucService.delete(danhmucsanphamId);
    }
}
