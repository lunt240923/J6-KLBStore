package com.klbstore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.klbstore.dao.NhaCungCapDAO;
import com.klbstore.dao.PhieuNhapDAO;
import com.klbstore.model.NhaCungCap;
import com.klbstore.model.PhieuNhap;

@Controller
public class PhieuNhapController {
    @Autowired
    PhieuNhapDAO phieuNhapDAO;
    @Autowired
    NhaCungCapDAO nhaCungCapDAO;
    //============================CHỨC NĂNG QL & DS PHIẾU NHẬP=================================================
    //DS
    @GetMapping("admin/phieunhap")
    public String DSPhieuNhap(Model model,@RequestParam("sortBy") Optional<String> sortBy, @RequestParam("p") Optional<Integer> p) {
        Sort sort = Sort.by(Direction.ASC, sortBy.orElse("phieuNhapId"));
        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
        Page<PhieuNhap> items = phieuNhapDAO.findAll(pageable);
        var numberOfpages = items.getTotalPages();
        model.addAttribute("curr", p.orElse(0));
        model.addAttribute("numberOfpages",numberOfpages);
        model.addAttribute("items", items);
        return "admin/DSPhieuNhap";
    }
    //QL
    @GetMapping("admin/phieunhap/edit")
    public String QLPhieuNhap(Model model) {
        PhieuNhap tblPhieuNhap = new PhieuNhap();
        model.addAttribute("items", tblPhieuNhap);
        return "admin/QLPhieuNhap";
    }
    //QL btn chỉnh sửa
    @GetMapping("admin/phieunhap/edit/{id}")
    public String editQLPhieuNhap(Model model, @PathVariable("id") Integer id) {
        PhieuNhap formPhieuNhap = phieuNhapDAO.getOne(id);
        model.addAttribute("items", formPhieuNhap);
        
        return "admin/QLPhieuNhap";
    }

    //QL btn xóa
    @GetMapping("admin/phieunhap/delete/{id}")
    public String deleteQLPhieuNhap(Model model, @PathVariable("id") Integer id) {
        phieuNhapDAO.deleteById(id);
        return "redirect:/admin/phieunhap";
    }

    @ModelAttribute("nhaCungCapModel")
    public Map<Integer, String> getNhacungCap() {
        List<NhaCungCap> ncc = nhaCungCapDAO.findAll();
        Map<Integer, String> map = new HashMap<>();
        for (NhaCungCap x : ncc) {
            map.put(x.getNhaCungCapId(), x.getTenNhaCungCap());
        }
        
        return map;
    }
    
    //QL btn Lưu
    @RequestMapping("admin/phieunhap/save")
    public String saveQLPhieuNhap(@Validated @ModelAttribute("items") PhieuNhap phieuNhap, Errors errors) {
        if (errors.hasErrors()) {
            return "admin/QLPhieuNhap";
        }else {
            phieuNhapDAO.save(phieuNhap);
            return "redirect:/admin/phieunhap";
        }
    }
}
