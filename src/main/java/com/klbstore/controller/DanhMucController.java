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
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import com.klbstore.dao.DanhMucSanPhamDAO;
// import com.klbstore.model.DanhMucSanPham;

// @Controller
// public class DanhMucController {
//     @Autowired
//     DanhMucSanPhamDAO danhMucSanPhamDAO;
//     //============================CHỨC NĂNG QL & DS DANH MỤC=================================================
//     //DS
//     @GetMapping("admin/danhmuc")
//     public String DSDanhMuc(Model model, @RequestParam("sortBy") Optional<String> sortBy, @RequestParam("p") Optional<Integer> p) {
//         Sort sort = Sort.by(Direction.ASC, sortBy.orElse("danhMucSanPhamId"));
//         Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
//         Page<DanhMucSanPham> danhMucSanPham = danhMucSanPhamDAO.findAll(pageable);
//         var numberOfpages = danhMucSanPham.getTotalPages();
//         model.addAttribute("curr", p.orElse(0));
//         model.addAttribute("numberOfpages",numberOfpages);
//         // List<DanhMucSanPham> danhMucSanPham = danhMucSanPhamDAO.findAll(sort);
//         model.addAttribute("items", danhMucSanPham);
//         return "admin/DSDanhMuc";
//     }
//     // QL
//     @GetMapping("admin/danhmuc/edit")
//     public String QLDanhMuc(Model model) {
//         DanhMucSanPham items = new DanhMucSanPham();
//         model.addAttribute("items", items);
//         return "admin/QLDanhMuc";
//     }
//     // DS btn chỉnh sửa
//     @GetMapping("admin/danhmuc/edit/{id}")
//     public String EditQLDanhMuc(Model model, @PathVariable("id") Integer id) {
//         DanhMucSanPham items = danhMucSanPhamDAO.getById(id);
//         model.addAttribute("items", items);
//         return "admin/QLDanhMuc";
//     }
//     // DS btn xóa
//     @GetMapping("admin/danhmuc/delete/{id}")
//     public String DeleteQLDanhMuc(Model model, @PathVariable("id") Integer id) {
//         danhMucSanPhamDAO.deleteById(id);
//         return "redirect:/admin/danhmuc";
//     }
    
//     //QL btn Lưu
//     @RequestMapping("admin/danhmuc/save")
//     public String saveDanhMuc(@Validated @ModelAttribute("items") DanhMucSanPham danhMucSanPham) {
        
        
//             danhMucSanPhamDAO.save(danhMucSanPham);
//             return "redirect:/admin/danhmuc";
        
//     }
// }
