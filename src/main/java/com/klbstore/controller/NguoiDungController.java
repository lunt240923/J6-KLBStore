// package com.klbstore.controller;

// import java.util.HashMap;
// import java.util.Map;
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

// import com.klbstore.dao.NguoiDungDAO;
// import com.klbstore.model.NguoiDung;

// @Controller
// public class NguoiDungController {
//     @Autowired
//     NguoiDungDAO nguoiDungDAO;
//     //============================CHỨC NĂNG QL & DS NGƯỜI DÙNG=================================================
//     //DS
//     @GetMapping("admin/nguoidung")
//     public String DSNguoiDung(Model model, @RequestParam("sortBy") Optional<String> sortBy, @RequestParam("p") Optional<Integer> p) {
//         Sort sort = Sort.by(Direction.ASC, sortBy.orElse("nguoiDungId"));
//         Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
//         Page<NguoiDung> accounts = nguoiDungDAO.findAll(pageable);
//         var numberOfpages = accounts.getTotalPages();
//         model.addAttribute("curr", p.orElse(0));
//         model.addAttribute("numberOfpages",numberOfpages);
//         model.addAttribute("items", accounts);
//         // paramService.checkDangNhapString("admin/DSNguoiDung", "redirect:/user/index");
//         return "admin/DSNguoiDung";
//     }
//     //QL
//     @GetMapping("admin/nguoidung/edit")
//     public String QLNguoiDung(Model model) {
//         NguoiDung items = new NguoiDung();
//         model.addAttribute("items", items);
//         return "admin/QLNguoiDung";
//     }

//     //QL btn chỉnh sửa
//     @GetMapping("/admin/nguoidung/edit/{nguoidungid}")
//     public String updateNguoiDung(@PathVariable ("nguoidungid") Integer nguoidungid, Model model) {
//         NguoiDung items = nguoiDungDAO.getReferenceById(nguoidungid);
//         model.addAttribute("items", items);
//         return "admin/QLNguoiDung";
//     }

//     @ModelAttribute("genders")
//     public Map<Boolean, String> getGender() {
//         Map<Boolean, String> map = new HashMap<>();
//         map.put(true, "Nam");
//         map.put(false, "Nữ");
//         return map;
//     }

//     //QL btn Lưu
//     @RequestMapping("admin/nguoidung/save")
//     public String QLNguoiDungUpdate(@Validated @ModelAttribute("items") NguoiDung nguoiDung, Errors errors) {
//         if (errors.hasErrors()) {
//             return "admin/QLNguoiDung";
//         }else {
//             if (nguoiDung.getNgayDangKy() == null) {
//                 nguoiDung.setNgayDangKy(java.time.LocalDate.now());
//             }
//             nguoiDungDAO.save(nguoiDung);
//         return "redirect:/admin/nguoidung";
//         }
        
//     }
//     //QL btn xóa
//     @GetMapping("admin/nguoidung/delete/{nguoidungid}")
//     public String QLNguoiDungDelete(@PathVariable("nguoidungid") Integer id) {
        
//         nguoiDungDAO.deleteById(id);
//         return "redirect:/admin/nguoidung";
//     }
// }
