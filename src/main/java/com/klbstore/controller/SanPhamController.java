package com.klbstore.controller;

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

import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.SanPham;

@Controller
public class SanPhamController {
    @Autowired
    SanPhamDAO sanPhamDAO;
    //============================CHỨC NĂNG QL & DS SẢN PHẨM=================================================
    //DS
    @GetMapping("admin/sanpham")
    public String DSSanPham(Model model, @RequestParam("sortBy") Optional<String> sortBy, @RequestParam("p") Optional<Integer> p) {
        // List<SanPham> items = sanPhamDAO.findAll();
        Sort sort = Sort.by(Direction.ASC, sortBy.orElse("sanPhamId"));
        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
        Page<SanPham> items = sanPhamDAO.findAll(pageable);
        var numberOfpages = items.getTotalPages();
        model.addAttribute("curr", p.orElse(0));
        model.addAttribute("numberOfpages",numberOfpages);
        model.addAttribute("items", items);
        return "admin/DSSanPham";
    }

    //QL
    @GetMapping("admin/sanpham/edit")
    public String QLSanPham(Model model) {
        SanPham items = new SanPham();
        model.addAttribute("items", items);
        return "admin/QLSanPham";
    }

    //DS btn chỉnh sửa
     @GetMapping("admin/sanpham/edit/{id}")
    public String editQLSanPham(Model model, @PathVariable ("id") Integer id) {
        SanPham items = sanPhamDAO.getOne(id);
        model.addAttribute("items", items);
        return "admin/QLSanPham";
    }
    //DS btn xóa
    @GetMapping("admin/sanpham/delete/{id}")
    public String deleteQLSanPham(Model model, @PathVariable ("id") Integer id) {
        // SanPham sanPham = sanPhamDAO.getOne(id);
        sanPhamDAO.deleteById(id);
        
        return "redirect:/admin/sanpham";
    }
    //QL btn Lưu
    @RequestMapping("admin/sanpham/save")
    public String saveQLSanPham(@Validated @ModelAttribute("items") SanPham sanPham, Errors errors) {
        if (errors.hasErrors()) {
            return "admin/QLSanPham";
        } else {
            if (sanPham.getNgayTao() == null) {
                sanPham.setNgayTao(java.time.LocalDate.now());
            }
            sanPhamDAO.save(sanPham);
            return "redirect:/admin/sanpham";
        }
    }
}
