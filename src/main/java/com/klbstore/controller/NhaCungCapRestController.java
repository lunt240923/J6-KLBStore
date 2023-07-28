package com.klbstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klbstore.dao.NhaCungCapDAO;
import com.klbstore.model.NhaCungCap;

@CrossOrigin("*")
@RestController
public class NhaCungCapRestController {
    @Autowired
    NhaCungCapDAO nhaCungCapDAO;

    @GetMapping("/rest/nhacungcap")
    public List<NhaCungCap> getAll(
        @RequestParam (name = "nhaCungCapId") Optional<Integer> nhaCungCapId,
        @RequestParam (name = "tenNhaCungCap") Optional<String> tenNhaCungCap,
        @RequestParam (name = "tenGiaoDich") Optional<String> tenGiaoDich,
        @RequestParam (name = "sdt") Optional<String>  sdt,
        @RequestParam (name = "email") Optional<String> email,
        @RequestParam (name = "hienThi") Optional<Boolean> hienThi
    ) {
        return nhaCungCapDAO.getAllNhaCungCap(nhaCungCapId.orElse(null),
         tenNhaCungCap.orElse(""), tenGiaoDich.orElse(""), sdt.orElse(""),
          email.orElse(""), hienThi.orElse(true));
    }

    // @GetMapping("/rest/nhacungcap" )
    // public List<NhaCungCap> getAll() {
    //     return nhaCungCapDAO.getAllNhaCungCap();
    // }

    @GetMapping("/rest/nhacungcap/{nhaCungCapId}" )
    public NhaCungCap getOne(@PathVariable("nhaCungCapId") Integer nhaCungCapId) {
        return nhaCungCapDAO.findById(nhaCungCapId).get();
    }

    @PostMapping("/rest/nhacungcap")
    public NhaCungCap post(@RequestBody NhaCungCap nhaCungCap) {
        nhaCungCapDAO.save(nhaCungCap);
        return nhaCungCap;
    }

    @PutMapping("/rest/nhacungcap/{nhaCungCapId}")
    public NhaCungCap put(@RequestBody NhaCungCap nhaCungCap, @PathVariable("nhaCungCapId") Integer nhaCungCapId) {
        nhaCungCapDAO.save(nhaCungCap);
        return nhaCungCap;
    }

    @DeleteMapping("/rest/nhacungcap/{nhaCungCapId}")
    public void delete(@PathVariable("nhaCungCapId") Integer nhaCungCapId) {
        nhaCungCapDAO.deleteById(nhaCungCapId);
    }
}
