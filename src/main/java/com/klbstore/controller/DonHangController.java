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

// import com.klbstore.dao.DonHangDAO;
// import com.klbstore.model.DonHang;

// @Controller
// public class DonHangController {
//     @Autowired
//     DonHangDAO donHangDAO;
//     //============================CHỨC NĂNG QL & DS ĐƠN HÀNG=================================================
//     //DS
//     @GetMapping("admin/donhang")
//     public String DSDonHang(Model model, @RequestParam("sortBy") Optional<String> sortBy, @RequestParam("p") Optional<Integer> p) {
//         Sort sort = Sort.by(Direction.ASC, sortBy.orElse("donHangId"));
//         Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
//         Page<DonHang> donHang = donHangDAO.findAll(pageable);
//         var numberOfpages = donHang.getTotalPages();
//         model.addAttribute("curr", p.orElse(0));
//         model.addAttribute("numberOfpages",numberOfpages);
//         // List<DonHang> donHang = donHangDAO.findAll(sort);
//         model.addAttribute("items", donHang);
//         return "admin/DSDonHang";
//     }
//     //QL
//     @GetMapping("admin/donhang/edit")
//     public String QLDonHang(Model model) {
//         DonHang items = new DonHang();
//         model.addAttribute("items", items);
//         return "admin/QLDonHang";
//     }

//     // DS btn xóa
//     @GetMapping("admin/donhang/delete/{id}")
//     public String editQLDonHang(Model model, @PathVariable("id") Integer id) {
//         donHangDAO.deleteById(id);
//         return "redirect:/admin/donhang";
//     }
//     // DS btn chỉnh sửa
//     @GetMapping("admin/donhang/edit/{id}")
//     public String deleteQLDonHang(Model model, @PathVariable("id") Integer id) {
//         DonHang items = donHangDAO.getById(id);
//         model.addAttribute("items", items);
//         return "admin/QLDonHang";
//     }
    
//     // //QL btn Lưu
//     // @RequestMapping("admin/donhang/save")
//     // public String saveDonHang(@Validated @ModelAttribute("items") DonHang donHang, Errors errors) {
//     //     if(errors.hasErrors()) {
//     //         return "admin/QLDonHang";
//     //     } else {
//     //         if (donHang.getNgayDatHang() == null) {
//     //             donHang.setNgayDatHang(java.time.LocalDate.now());
//     //         }
//     //         donHangDAO.save(donHang);
//     //         return "redirect:/admin/donhang";
//     //     }
//     // }
// }
