package com.klbstore.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.klbstore.model.ChiTietDonHang;
import com.klbstore.model.DonHang;
import com.klbstore.service.DonHangService;

@CrossOrigin("*")
@RestController
public class DonHangRestController {
    @Autowired
    DonHangService donHangService;


    @GetMapping("/rest/donhang")
    public List<DonHang> getAll() {
        return donHangService.getAll();
    }

    @GetMapping("/rest/donhang/{donhangId}")
    public DonHang getOne(@PathVariable("donhangId") Integer donhangId) {
        return donHangService.getById(donhangId);
    }

    @PostMapping("/rest/donhang")
    public DonHang post(@RequestBody DonHang donhang) {
        donHangService.create(donhang);
        return donhang;
    }

    @PutMapping("/rest/donhang/{donhangId}")
    public DonHang put(@RequestBody DonHang donhang, @PathVariable("donhangId") Integer donhangId) {
        donHangService.update(donhang);
        return donhang;
    }

    @DeleteMapping("/rest/donhang/{donhangId}")
    public void delete(@PathVariable("donhangId") Integer donhangId) {
        donHangService.delete(donhangId);
    }

    // lấy chi tiết đơn hàng theo id đơn hàng
    @GetMapping("/rest/ctdonhang/{donhangid}")
    public ResponseEntity<List<ChiTietDonHang>> getCTDonhangByIdDonHang(@PathVariable("donhangid") Integer donhangid) {
        List<ChiTietDonHang> products = donHangService.getCTDonhangByIdDonHang(donhangid);
        return ResponseEntity.ok(products);
    }

    //lấy all chi tiết đơn hàng
    @GetMapping("/rest/ctdonhang")
    public List<ChiTietDonHang> getAllCT() {
        return donHangService.getAllChiTietDonHang();
    }
    //Xóa chi tiết đơn hang
    @DeleteMapping("/rest/ctdonhang/{chiTietDonHangId}")
    public void deleteCTDonHang(@PathVariable("chiTietDonHangId") Integer chiTietDonHangId) {
        donHangService.deleteCTDonHang(chiTietDonHangId);
    }
}
