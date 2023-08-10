package com.klbstore.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.klbstore.model.MauSac;
import com.klbstore.service.MauSacService;

@CrossOrigin("*")
@RestController
public class MauSacRestController {
    @Autowired
    MauSacService mauSacService;

   
     @GetMapping("/rest/mausac/{sanphamid}")
    public ResponseEntity<List<MauSac>> getMauSacBySanPhamId(@PathVariable("sanphamid") Integer sanphamid) {
        List<MauSac> products = mauSacService.getMauSacBySanPhamId(sanphamid);
        return ResponseEntity.ok(products);
    }
}
