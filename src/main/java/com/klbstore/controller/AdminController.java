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

    //DS btn thêm cấu hình
     @GetMapping("admin/sanpham/editCauHinh/{id}")
    public String editCauHinhQLSanPham(Model model, @PathVariable ("id") Integer id, @RequestParam("nhomSanPhamId") Integer nhomSanPhamId) {
        
        if(nhomSanPhamId ==  1){
            CauHinhDienThoai item1 = cauHinhDienThoaiDAO.getMaNguoiDung(id);
            CauHinhDienThoai item2 = new CauHinhDienThoai();
            if (item1 != null) {
                model.addAttribute("items", item1);
            }
            else {
                model.addAttribute("items", item2);
            }
            return "admin/QLCauHinhDienThoai";
        }
        else {
             return "admin/QLCauHinhLapTop";
        }
    }

    // Lưu cấu hình điện thoại
    @RequestMapping("admin/sanpham/saveCauHinhDienThoai")
    public String saveCauHinhDienThoai(@ModelAttribute("items") CauHinhDienThoai chdt) {
        cauHinhDienThoaiDAO.save(chdt);
        return "redirect:/admin/sanpham";
    }

    //============================CHỨC NĂNG QL & DS ĐÁNH GIÁ=================================================
    //DS & QL
    @GetMapping("admin/danhgia/edit")
    public String QLDanhGia(Model model) {
        List<DanhGia> items = danhGiaDAO.findAll();
        model.addAttribute("items", items);
        return "admin/QLDanhGia";
    }
    //DS btn xóa
    @GetMapping("admin/danhgia/delete/{id}")
    public String deleteQLDanhGia(Model model, @PathVariable ("id") Integer id) {
        
        danhGiaDAO.deleteById(id);
        
        return "redirect:/admin/danhgia/edit";
    }

//============================Faq=================================================
    
    @GetMapping("admin/faq")
    public String faq() {

        return "admin/faq";
    }

    //============================Trang Chủ=================================================
    
    //Thống Kê
    @GetMapping("admin/index")
    public String Home(Model model) {
        List<ThongKeTonKhoDanhMuc> tkTonKho = danhMucSanPhamDAO.thongKeTonKhoDanhMuc();
        // model.addAttribute("hoTen")
        // List<ThongKeDoanhThu> thDoanhThu = donHangDAO.getDoanhThu();
        model.addAttribute("fruits", tkTonKho);
        // model.addAttribute("doanhthu", thDoanhThu);
        return "admin/index";
    }
    //============================CHỨC NĂNG Đăng Nhập=================================================
    

    @GetMapping("admin/login")
    public String Login() {

        return "admin/login";
    }

    @GetMapping("admin/profile")
    public String AdminProfile() {

        return "admin/profile";
    }

    //============================CHỨC NĂNG QL & DS PHIẾU XUẤT=================================================
    //DS
    @GetMapping("admin/phieuxuat")
    public String DSPhieuXuat(Model model,@RequestParam("sortBy") Optional<String> sortBy, @RequestParam("p") Optional<Integer> p) {
        Sort sort = Sort.by(Direction.ASC, sortBy.orElse("phieuXuatId"));
        Pageable pageable = PageRequest.of(p.orElse(0), 5, sort);
        Page<PhieuXuat> items = phieuXuatDAO.findAll(pageable);
        var numberOfpages = items.getTotalPages();
        model.addAttribute("curr", p.orElse(0));
        model.addAttribute("numberOfpages",numberOfpages);
        model.addAttribute("items", items);
        return "admin/DSPhieuXuat";
    }
    //QL
    @GetMapping("admin/phieuxuat/edit")
    public String QLPhieuXuat(Model model) {
        PhieuXuat items = new PhieuXuat();
        model.addAttribute("items", items);
        return "admin/QLPhieuXuat";
    }
    //DS btn chỉnh sửa
    @GetMapping("admin/phieuxuat/edit/{id}")
    public String editQLPhieuXuat(Model model, @PathVariable("id")Integer id) {
        PhieuXuat items = phieuXuatDAO.getOne(id);
        model.addAttribute("items", items);
        return "admin/QLPhieuXuat";
    }
    //DS btn xóa
    @GetMapping("admin/phieuxuat/delete/{id}")
    public String deleteQLPhieuXuat(Model model, @PathVariable("id")Integer id) {
        phieuXuatDAO.deleteById(id);
        return "redirect:/admin/phieuxuat";
    }
    //QL btn Lưu
    @RequestMapping("admin/phieuxuat/save")
    public String saveQLPhieuXuat(@Validated @ModelAttribute("items")PhieuXuat phieuXuat, Errors errors) {
        if(errors.hasErrors()){
            return "admin/QLPhieuXuat";
        }
        else{
            // NguoiDung nd = nguoiDungDAO.getOne(phieuXuat.getNguoiDung().getNguoiDungId());
            // nd.setSdt(phieuXuat.getNguoiDung().getSdt());
            // nguoiDungDAO.save(nd);
            phieuXuatDAO.save(phieuXuat);
        return "redirect:/admin/phieuxuat";
        }
    }

    @GetMapping ("admin/thongke/tonkho")
    public String thongKeTonKho(Model model) {
        List<ThongKeTonKhoDanhMuc> items = danhMucSanPhamDAO.thongKeTonKhoDanhMuc();
        model.addAttribute("items", items);
        return "admin/ThongKeTonKho";
    }

    // @GetMapping("admin/thongke/doanhthu")
    // public String thongKeDoanhThu(Model model) {
    //     List<ThongKeDoanhThu> items = donHangDAO.getDoanhThu();
    //     // System.out.println(items.get(0).());
    //     // items.get(0).
        
    //     model.addAttribute("items", items);
    //     return "admin/ThongKeDoanhThu";
    // }


    //======================================================================
    @ModelAttribute("nguoiDungTenNguoiDung")
    public Map<Integer, String> getNguoiDungTenNguoiDungMap() {
        List<NguoiDung> nguoiDung = nguoiDungDAO.findAll();
        Map<Integer, String> map = new HashMap<>();
        for (NguoiDung x : nguoiDung) {
            map.put(x.getNguoiDungId(), x.getHoTen());
        }
        return map;
    }

    @ModelAttribute("danhMucSanPham")
    public Map<Integer, String> getTenDanhMuc() {
        List<DanhMucSanPham> tenDM = danhMucSanPhamDAO.findAll();
        Map<Integer, String> map = new HashMap<>();
        for (DanhMucSanPham x : tenDM) {
            map.put(x.getDanhMucSanPhamId(), x.getTenDanhMucSanPham());
        }
        return map;
    }

    @ModelAttribute("SanPhamTenSanPham")
    public Map<Long, String> getSanPhamTenSanPhamMap() {
        List<SanPham> sanPham = sanPhamDAO.findAll();
        Map<Long, String> map = new HashMap<>();
        for (SanPham x : sanPham) {
            map.put(x.getSanPhamId(), x.getTenSanPham());
        }
        return map;
    }

}
