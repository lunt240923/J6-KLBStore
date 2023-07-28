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

import com.klbstore.dao.GiamGiaDAO;
import com.klbstore.model.GiamGia;

@CrossOrigin("*")
@RestController
public class MaGiamGiaRestController {
    @Autowired
    GiamGiaDAO giamGiaDAO;

    @GetMapping("/rest/giamgia")
    public List<GiamGia> getAll() {
        return giamGiaDAO.findAll();
    }

    @GetMapping("/rest/giamgia/{giamgiaId}")
    public GiamGia getOne(@PathVariable("giamgiaId") Integer giamgiaId) {
        return giamGiaDAO.findById(giamgiaId).get();
    }

    @PostMapping("/rest/giamgia")
    public GiamGia post(@RequestBody GiamGia giamgia) {
        giamGiaDAO.save(giamgia);
        return giamgia;
    }

    @PutMapping("/rest/giamgia/{giamgiaId}")
    public GiamGia put(@RequestBody GiamGia giamgia, @PathVariable("giamgiaId") Integer giamgiaId) {
        giamGiaDAO.save(giamgia);
        return giamgia;
    }

    @DeleteMapping("/rest/giamgia/{giamgiaId}")
    public void delete(@PathVariable("giamgiaId") Integer giamgiaId) {
        giamGiaDAO.deleteById(giamgiaId);
    }
}
