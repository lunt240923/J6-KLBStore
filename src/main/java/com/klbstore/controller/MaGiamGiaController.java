// package com.klbstore.controller;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.data.domain.Page;
// import org.springframework.data.domain.PageRequest;
// import org.springframework.data.domain.Pageable;
// import org.springframework.data.domain.Sort;
// import org.springframework.data.domain.Sort.Direction;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.Errors;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.klbstore.dao.GiamGiaDAO;
// import com.klbstore.model.GiamGia;

// @Controller
// public class MaGiamGiaController {
//     @Autowired
//     GiamGiaDAO giamGiaDAO;
//     //============================CHỨC NĂNG QL & DS MÃ GIẢM GIÁ=================================================
//     //DS
//     @GetMapping("admin/magiamgia")
//     public String DSMaGiamGia(Model model, @RequestParam("sortBy") Optional<String> sortBy, @RequestParam("p") Optional<Integer> p) {
//         Sort sort = Sort.by(Direction.ASC, sortBy.orElse("giamGiaId"));
//         Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
//         Page<GiamGia> items = giamGiaDAO.findAll(pageable);
//         var numberOfpages = items.getTotalPages();
//         model.addAttribute("curr", p.orElse(0));
//         model.addAttribute("numberOfpages",numberOfpages);
//         model.addAttribute("items", items);
//         return "admin/DSMaGiamGia";
//     }
//     //QL
//     @GetMapping("admin/magiamgia/edit")
//     public String QLMaGiamGia(Model model) {
//         GiamGia items = new GiamGia();
//         model.addAttribute("items", items);
//         return "admin/QLMaGiamGia";
//     }
//     //DS btn Chỉnh Sửa
//     @GetMapping("admin/magiamgia/edit/{id}")
//     public String editQLMaGiamGia(Model model, @PathVariable("id") Integer id) {
//         GiamGia items = giamGiaDAO.getOne(id);
//         model.addAttribute("items", items);
//         return "admin/QLMaGiamGia";
//     }

//     //DS btn xóa
//     @GetMapping("admin/magiamgia/delete/{id}")
//     public String deleteQLMaGiamGia(Model model, @PathVariable("id") Integer id) {
//         giamGiaDAO.deleteById(id);
//         return "redirect:/admin/magiamgia";
//     }

//     //QL btn Lưu
//     @RequestMapping("admin/magiamgia/save")
//     public String deleteQLMaGiamGia(@Validated @ModelAttribute("items") GiamGia giamGia, Errors errors) {
//         if(errors.hasErrors()) {
//             return "admin/QLMaGiamGia";
//         } else {
//             giamGiaDAO.save(giamGia);
//             return "redirect:/admin/magiamgia";
//         }
//     }
// }
