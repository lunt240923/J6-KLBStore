// package com.klbstore.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.validation.Errors;
// import org.springframework.validation.annotation.Validated;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestMapping;

// import com.klbstore.dao.NhaCungCapDAO;
// import com.klbstore.model.NhaCungCap;

// @Controller
// public class NhaCungCapController {
//     @Autowired
//     NhaCungCapDAO nhaCungCapDAO;

//     //============================CHỨC NĂNG QL & DS NHÀ CUNG CẤP=================================================
//     //DS
//     @GetMapping("admin/nhacungcap")
//     public String DSNhaCungCap(Model model) {
//         return "admin/DSNhaCungCap";
//     }



//     //QL
//     @GetMapping("admin/nhacungcap/edit")
//     public String QLNhaCungCap(Model model) {
//         return "admin/QLNhaCungCap";
//     }
//     //QL btn chỉnh sửa
//     @GetMapping("admin/nhacungcap/edit/{id}")
//     public String editQLNhaCungCap(Model model, @PathVariable ("id") Integer id) {
//         NhaCungCap nhaCungCap = nhaCungCapDAO.getOne(id);
//         model.addAttribute("nhaCungCap", nhaCungCap);
//         return "admin/QLNhaCungCap";
//     }

//     //QL btn xóa
//     @GetMapping("admin/nhacungcap/delete/{id}")
//     public String deleteQLNhaCungCap(Model model, @PathVariable ("id") Integer id) {
//        nhaCungCapDAO.deleteById(id);
//         return "redirect:/admin/nhacungcap";
//     }

//     //QL btn Lưu
//     // @ResponseBody
//     @RequestMapping ("admin/nhacungcap/save")
//     public String saveNhaCungCap(@Validated @ModelAttribute("nhaCungCap") NhaCungCap nhaCungCap, Errors errors){
//         if (errors.hasErrors()) {
//             return "admin/QLNhaCungCap";
//         }else {
//             nhaCungCapDAO.save(nhaCungCap);
//             return "redirect:/admin/nhacungcap";
//         }
        
        
//     }
// }
