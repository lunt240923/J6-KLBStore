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
import com.klbstore.model.CauHinhDienThoai;

@CrossOrigin("*")
@RestController
public class CauHinhDTRestController {
    @Autowired
    CauHinhDienThoaiDAO cauHinhDienThoaiDAO;

    @GetMapping("/rest/cauhinhdienthoai")
    public List<CauHinhDienThoai> getAll() {
        return cauHinhDienThoaiDAO.findAll();
    }

    @GetMapping("/rest/cauhinhdienthoai/{cauhinhdienthoaiId}")
    public CauHinhDienThoai getOne(@PathVariable("cauhinhdienthoaiId") Long cauhinhdienthoaiId) {
        return cauHinhDienThoaiDAO.findById(cauhinhdienthoaiId).get();
    }

    @PostMapping("/rest/cauhinhdienthoai")
    public CauHinhDienThoai post(@RequestBody CauHinhDienThoai cauhinhdienthoai) {
        cauHinhDienThoaiDAO.save(cauhinhdienthoai);
        return cauhinhdienthoai;
    }

    @PutMapping("/rest/cauhinhdienthoai/{cauhinhdienthoaiId}")
    public CauHinhDienThoai put(@RequestBody CauHinhDienThoai cauhinhdienthoai, @PathVariable("cauhinhdienthoaiId") Integer cauhinhdienthoaiId) {
        cauHinhDienThoaiDAO.save(cauhinhdienthoai);
        return cauhinhdienthoai;
    }

    @DeleteMapping("/rest/cauhinhdienthoai/{cauhinhdienthoaiId}")
    public void delete(@PathVariable("cauhinhdienthoaiId") Long cauhinhdienthoaiId) {
        cauHinhDienThoaiDAO.deleteById(cauhinhdienthoaiId);
    }
}
