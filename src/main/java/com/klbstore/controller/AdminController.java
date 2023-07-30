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

import com.klbstore.dao.CauHinhDienThoaiDAO;
import com.klbstore.dao.ChiTietDonHangDAO;
import com.klbstore.dao.ChiTietSanPhamDAO;
import com.klbstore.dao.DanhGiaDAO;
import com.klbstore.dao.DanhMucSanPhamDAO;
import com.klbstore.dao.DonHangDAO;
import com.klbstore.dao.GiamGiaDAO;
import com.klbstore.dao.NguoiDungDAO;
import com.klbstore.dao.NhaCungCapDAO;
import com.klbstore.dao.PhieuNhapDAO;
import com.klbstore.dao.PhieuXuatDAO;
import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.CauHinhDienThoai;
import com.klbstore.model.DanhGia;
import com.klbstore.model.DanhMucSanPham;
import com.klbstore.model.NguoiDung;
import com.klbstore.model.PhieuXuat;
import com.klbstore.model.SanPham;
import com.klbstore.model.ThongKeTonKhoDanhMuc;
import com.klbstore.service.CookieService;
import com.klbstore.service.ParamService;
import com.klbstore.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class AdminController {

    //DAO
    @Autowired
    CauHinhDienThoaiDAO cauHinhDienThoaiDAO;
    @Autowired
    PhieuXuatDAO phieuXuatDAO;
    @Autowired
    PhieuNhapDAO phieuNhapDAO;
    @Autowired
    DanhGiaDAO danhGiaDAO;
    @Autowired
    DonHangDAO donHangDAO;
    @Autowired
    ChiTietSanPhamDAO chiTietSanPhamDAO;
    @Autowired
    SanPhamDAO sanPhamDAO;
    @Autowired
    NhaCungCapDAO nhaCungCapDAO;
    @Autowired
    GiamGiaDAO giamGiaDAO;
    @Autowired
    ChiTietDonHangDAO chiTietDonHangDAO;
    @Autowired
    DanhMucSanPhamDAO danhMucSanPhamDAO;
    @Autowired
    NguoiDungDAO nguoiDungDAO;
    @Autowired
    CookieService cookieService;
    @Autowired
    ParamService paramService;
    @Autowired
    SessionService sessionService;
    @Autowired
    HttpServletRequest request;

    public void checkAccount(NguoiDung username) {
        
    }

    

    //============================CHỨC NĂNG QL & DS ĐÁNH GIÁ=================================================
   

//============================Faq=================================================
    
    @GetMapping("admin/faq")
    public String faq() {

        return "admin/faq";
    }

    //============================Trang Chủ=================================================
    
    //Thống Kê
    @GetMapping({"/admin", "/admin/index"})
    public String Home(Model model) {
        
        return "redirect:/assets/admin/index.html";
    }
    //============================CHỨC NĂNG Đăng Nhập=================================================
    

    

}
