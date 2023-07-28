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

import com.klbstore.dao.CauHinhLaptopDAO;
import com.klbstore.model.CauHinhLaptop;

@CrossOrigin("*")
@RestController
public class CauHinhLTRestController {
    @Autowired
    CauHinhLaptopDAO cauHinhLaptopDAO;

    @GetMapping("/rest/cauhinhlaptop")
    public List<CauHinhLaptop> getAll() {
        return cauHinhLaptopDAO.findAll();
    }

    @GetMapping("/rest/cauhinhlaptop/{cauhinhlaptopId}")
    public CauHinhLaptop getOne(@PathVariable("cauhinhlaptopId") Integer cauhinhlaptopId) {
        return cauHinhLaptopDAO.findById(cauhinhlaptopId).get();
    }

    @PostMapping("/rest/cauhinhlaptop")
    public CauHinhLaptop post(@RequestBody CauHinhLaptop cauhinhlaptop) {
        cauHinhLaptopDAO.save(cauhinhlaptop);
        return cauhinhlaptop;
    }

    @PutMapping("/rest/cauhinhlaptop/{cauhinhlaptopId}")
    public CauHinhLaptop put(@RequestBody CauHinhLaptop cauhinhlaptop, @PathVariable("cauhinhlaptopId") Integer cauhinhlaptopId) {
        cauHinhLaptopDAO.save(cauhinhlaptop);
        return cauhinhlaptop;
    }

    @DeleteMapping("/rest/cauhinhlaptop/{cauhinhlaptopId}")
    public void delete(@PathVariable("cauhinhlaptopId") Integer cauhinhlaptopId) {
        cauHinhLaptopDAO.deleteById(cauhinhlaptopId);
    }
}
