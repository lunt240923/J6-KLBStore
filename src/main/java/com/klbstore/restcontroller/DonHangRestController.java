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

import com.klbstore.dao.DonHangDAO;
import com.klbstore.model.DonHang;

@CrossOrigin("*")
@RestController
public class DonHangRestController {
    @Autowired
    DonHangDAO donHangDAO;

    @GetMapping("/rest/donhang")
    public List<DonHang> getAll() {
        return donHangDAO.findAll();
    }

    @GetMapping("/rest/donhang/{donhangId}")
    public DonHang getOne(@PathVariable("donhangId") Integer donhangId) {
        return donHangDAO.findById(donhangId).get();
    }

    @PostMapping("/rest/donhang")
    public DonHang post(@RequestBody DonHang donhang) {
        donHangDAO.save(donhang);
        return donhang;
    }

    @PutMapping("/rest/donhang/{donhangId}")
    public DonHang put(@RequestBody DonHang donhang, @PathVariable("donhangId") Integer donhangId) {
        donHangDAO.save(donhang);
        return donhang;
    }

    @DeleteMapping("/rest/donhang/{donhangId}")
    public void delete(@PathVariable("donhangId") Integer donhangId) {
        donHangDAO.deleteById(donhangId);
    }
}
