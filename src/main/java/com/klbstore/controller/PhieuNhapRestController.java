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

import com.klbstore.dao.PhieuNhapDAO;
import com.klbstore.model.PhieuNhap;

@CrossOrigin("*")
@RestController
public class PhieuNhapRestController {
    @Autowired
    PhieuNhapDAO phieuNhapDAO;

    @GetMapping("/rest/phieunhap")
    public List<PhieuNhap> getAll() {
        return phieuNhapDAO.findAll();
    }

    @GetMapping("/rest/phieunhap/{phieunhapId}")
    public PhieuNhap getOne(@PathVariable("phieunhapId") Integer phieunhapId) {
        return phieuNhapDAO.findById(phieunhapId).get();
    }

    @PostMapping("/rest/phieunhap")
    public PhieuNhap post(@RequestBody PhieuNhap phieunhap) {
        phieuNhapDAO.save(phieunhap);
        return phieunhap;
    }

    @PutMapping("/rest/phieunhap/{phieunhapId}")
    public PhieuNhap put(@RequestBody PhieuNhap phieunhap, @PathVariable("phieunhapId") Integer phieunhapId) {
        phieuNhapDAO.save(phieunhap);
        return phieunhap;
    }

    @DeleteMapping("/rest/phieunhap/{phieunhapId}")
    public void delete(@PathVariable("phieunhapId") Integer phieunhapId) {
        phieuNhapDAO.deleteById(phieunhapId);
    }
}
