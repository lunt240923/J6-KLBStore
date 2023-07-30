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

import com.klbstore.model.PhieuNhap;
import com.klbstore.service.PhieuNhapService;

@CrossOrigin("*")
@RestController
public class PhieuNhapRestController {
    @Autowired
    PhieuNhapService phieuNhapService;

    @GetMapping("/rest/phieunhap")
    public List<PhieuNhap> getAll() {
        return phieuNhapService.getAll();
    }

    @GetMapping("/rest/phieunhap/{phieunhapId}")
    public PhieuNhap getOne(@PathVariable("phieunhapId") Integer phieunhapId) {
        return phieuNhapService.getById(phieunhapId);
    }

    @PostMapping("/rest/phieunhap")
    public PhieuNhap post(@RequestBody PhieuNhap phieunhap) {
        phieuNhapService.create(phieunhap);
        return phieunhap;
    }

    @PutMapping("/rest/phieunhap/{phieunhapId}")
    public PhieuNhap put(@RequestBody PhieuNhap phieunhap, @PathVariable("phieunhapId") Integer phieunhapId) {
        phieuNhapService.update(phieunhap);
        return phieunhap;
    }

    @DeleteMapping("/rest/phieunhap/{phieunhapId}")
    public void delete(@PathVariable("phieunhapId") Integer phieunhapId) {
        phieuNhapService.delete(phieunhapId);
    }
}
