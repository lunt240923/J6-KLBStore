package com.klbstore.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klbstore.dao.NguoiDungDAO;
import com.klbstore.model.NguoiDung;

@CrossOrigin("*")
@RestController
public class NguoiDungRestController {
    @Autowired
    NguoiDungDAO nguoiDungDAO;

    @GetMapping("/rest/nguoidung")
    public List<NguoiDung> getAll(Model model) {
        return nguoiDungDAO.findAll();
    }

    @GetMapping("/rest/nguoidung/{nguoiDungId}")
    public NguoiDung getOne(@PathVariable ("nguoiDungId") Integer nguoiDungId) {
        return nguoiDungDAO.findById(nguoiDungId).get();
    }

    @PostMapping ("/rest/nguoidung")
    public NguoiDung post(@RequestBody NguoiDung nguoiDung){
        nguoiDungDAO.save(nguoiDung);
        return nguoiDung;
    }

    @PutMapping ("/rest/nguoidung/{nguoiDungId}")
    public NguoiDung put(@RequestBody NguoiDung nguoiDung, @PathVariable ("nguoiDungId") Integer id){
        nguoiDungDAO.save(nguoiDung);
        return nguoiDung;
    }

    @DeleteMapping("/rest/nguoidung/{nguoiDungId}")
	public void delete(@PathVariable("nguoiDungId") Integer nguoiDungId) {
		nguoiDungDAO.deleteById(nguoiDungId);
	}
    
}
