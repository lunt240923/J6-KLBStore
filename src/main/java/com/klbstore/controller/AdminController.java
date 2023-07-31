package com.klbstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class AdminController {

 
    //============================Trang Chủ=================================================
    
    //Thống Kê
    @GetMapping({"/admin", "/admin/index"})
    public String Home(Model model) {
        
        return "redirect:/assets/admin/index.html";
    }

}
