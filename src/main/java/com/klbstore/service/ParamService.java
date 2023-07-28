package com.klbstore.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.klbstore.dao.NguoiDungDAO;
import com.klbstore.model.NguoiDung;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class ParamService {
    @Autowired
    HttpServletRequest request;
    @Autowired
    CookieService cookieService;
    @Autowired
    SessionService sessionService;
    @Autowired
    NguoiDungDAO nguoidungDAO;

    // Check người dùng đăng nhập
    public NguoiDung checkDangNhap() {
        // check login
        if (cookieService.get("user") != null) {
            for (NguoiDung i :  nguoidungDAO.findAll()) {
                if (cookieService.getValue("user").equalsIgnoreCase(i.getTenDangNhap())) {
                    return i;
                }
            }
        } else if (sessionService.get("username") != null && cookieService.get("user") == null) {
            for (NguoiDung i :  nguoidungDAO.findAll()) {
                if (sessionService.get("username").equals(i.getTenDangNhap())) {
                    return i;
                }
            }
        }
        return null;
    }

    //Check đăng nhập
    public String checkDangNhapString(String defaultAdmin, String defaultUser) {
        if (checkDangNhap() != null) {
            if (checkDangNhap().getQuyenDangNhap()) {
                return defaultAdmin;
            } else if (!checkDangNhap().getQuyenDangNhap()) {
                return defaultUser;
            }  
        }
        return "redirect:/user/login-register";
    }

    // /**
    // * Đọc chuỗi giá trị của tham số
    // * @param name tên tham số
    // * @param defaultValue giá trị mặc định
    // * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    // */
    public String getString(String name, String defaultValue){
        if (request.getParameter(name) != null)
        return name;
        else
        return null;
    }

    // /**
    // * Đọc số nguyên giá trị của tham số
    // * @param name tên tham số
    // * @param defaultValue giá trị mặc định
    // * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    // */
    public int getInt(String name, int defaultValue){
        if (request.getParameter(name) != null)
        return 0;
        else
        return defaultValue;
    }

    // /**
    // * Đọc số thực giá trị của tham số
    // * @param name tên tham số
    // * @param defaultValue giá trị mặc định
    // * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    // */
    public double getDouble(String name, double defaultValue){
        if (request.getParameter(name) != null)
        return 0;
        else
        return defaultValue;
    }

    // /**
    // * Đọc giá trị boolean của tham số
    // * @param name tên tham số
    // * @param defaultValue giá trị mặc định
    // * @return giá trị tham số hoặc giá trị mặc định nếu không tồn tại
    // */
    public boolean getBoolean(String name, boolean defaultValue){
        if (request.getParameter(name) != null)
        return true;
        else
        return defaultValue;
    }

    // /**
    // * Đọc giá trị thời gian của tham số
    // * @param name tên tham số
    // * @param pattern là định dạng thời gian
    // * @return giá trị tham số hoặc null nếu không tồn tại
    // * @throws RuntimeException lỗi sai định dạng
    // */
    public Date getDate(String name, String pattern){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.ENGLISH);

            String dateInString = "7-Jun-2013";
            Date date = formatter.parse(dateInString);
            
            if (!request.getParameter(name).isEmpty())
            return date;
            else
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // /**
    // * Lưu file upload vào thư mục
    // * @param file chứa file upload từ client
    // * @param path đường dẫn tính từ webroot
    // * @return đối tượng chứa file đã lưu hoặc null nếu không có file upload
    // * @throws RuntimeException lỗi lưu file
    // */
    ServletContext app;
    public File save(MultipartFile file, String path) {
        File dir = new File(app.getRealPath(path));
        if(!dir.exists())
        dir.mkdirs();
        try {
            File saveFile = new File(dir, file.getOriginalFilename());
            file.transferTo(saveFile);
            return saveFile;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
