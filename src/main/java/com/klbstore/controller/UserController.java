package com.klbstore.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LazyInitializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klbstore.dao.SanPhamDAO;
import com.klbstore.model.ChiTietGioHang;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.klbstore.dao.SanPhamDAO;
import com.klbstore.extensions.ContactService;
import com.klbstore.model.ChiTietGioHang;
import com.klbstore.model.ChiTietSanPham;
import com.klbstore.model.GiamGiaTrucTiep;
import com.klbstore.model.GioHang;
import com.klbstore.model.Login;
import com.klbstore.model.MauSac;
import com.klbstore.model.SanPham;
import com.klbstore.service.SessionService;
import com.klbstore.service.ShoppingCartService;

import jakarta.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private SanPhamDAO sanPhamDAO;

    @GetMapping("/user/404")
    public String error404() {
        return "user/404";
    }

    @GetMapping(value = { "/", "/user/index" })
    public String index(Model model) {
        List<SanPham> listSanPham = sanPhamDAO.findAll();
        List<SanPham> dienThoai = new ArrayList<>();
        List<SanPham> laptop = new ArrayList<>();
        List<SanPham> noiBat = new ArrayList<>();
        for (SanPham sp : listSanPham) {
            List<MauSac> mauSacList = new ArrayList<>(); // Danh sách các màu sắc còn hàng
            List<MauSac> spMauSacList = sp.getSanPhamMauSacs();
            for (MauSac mauSac : spMauSacList) {
                List<ChiTietSanPham> chiTietSanPhamList = mauSac.getMauSacChiTietSanPhams();
                boolean coSanPhamConHang = false; // Biến để kiểm tra nếu có ít nhất một sản phẩm trong màu sắc còn hàng
                for (ChiTietSanPham chiTietSanPham : chiTietSanPhamList) {
                    int soLuongTrongKho = chiTietSanPham.getSoLuongTrongKho();
                    if (soLuongTrongKho > 0) {
                        coSanPhamConHang = true;
                        break;
                    }
                }
                if (coSanPhamConHang) {
                    mauSacList.add(mauSac);
                }
            }
            sp.setSanPhamMauSacs(mauSacList);
            if (!mauSacList.isEmpty()) {
                if (sp.getDanhMucSanPham().getNhomSanPham().getNhomSanPhamId() == 1 && sp.getHienThi()) {
                    dienThoai.add(sp);
                }
                if (sp.getDanhMucSanPham().getNhomSanPham().getNhomSanPhamId() == 2 && sp.getHienThi()) {
                    laptop.add(sp);
                }
                if (sp.getNoiBat() && sp.getHienThi()) {
                    noiBat.add(sp);
                }
            }

        }

        GioHang cart = sessionService.get("cart");
        if (cart != null) {
            List<ChiTietGioHang> list = cart.getGioHangChiTietGioHangs();
            model.addAttribute("cart", list);
            model.addAttribute("total", shoppingCartService.getCartTotalAmount());
        }
        model.addAttribute("noiBat", noiBat);
        model.addAttribute("dienThoai", dienThoai);
        model.addAttribute("laptop", laptop);
        return "user/index";
    }

    @GetMapping("/user/checkout")
    public String checkout(Model model) {
        GioHang cart = sessionService.get("cart");
        if (cart != null) {
            List<ChiTietGioHang> list = cart.getGioHangChiTietGioHangs();
            model.addAttribute("cart", list);
            model.addAttribute("total", shoppingCartService.getCartTotalAmount());
        }
        return "user/checkout";
    }

    @GetMapping("/product/quick-view/{id}")
    public String quickView(@PathVariable(value = "id") int sanPhamId, Model model) {
        SanPham sanPham = sanPhamDAO.findById(sanPhamId).get();

        List<MauSac> mauSacList = new ArrayList<>();
        List<MauSac> spMauSacList = sanPham.getSanPhamMauSacs();
        for (MauSac mauSac : spMauSacList) {
            List<ChiTietSanPham> chiTietSanPhamList = mauSac.getMauSacChiTietSanPhams();
            boolean coSanPhamConHang = false;
            for (ChiTietSanPham chiTietSanPham : chiTietSanPhamList) {
                int soLuongTrongKho = chiTietSanPham.getSoLuongTrongKho();
                if (soLuongTrongKho > 0) {
                    coSanPhamConHang = true;
                    break;
                }
            }
            if (coSanPhamConHang) {
                mauSacList.add(mauSac);
            }
        }
        sanPham.setSanPhamMauSacs(mauSacList);

        if (!mauSacList.isEmpty()) {
            model.addAttribute("quickView", sanPham);
        }

        return "user/index";
    }

    @GetMapping("/user/contact")
    public String contact(Model model) {
        GioHang cart = sessionService.get("cart");
        if (cart != null) {
            List<ChiTietGioHang> list = cart.getGioHangChiTietGioHangs();
            model.addAttribute("cart", list);
            model.addAttribute("total", shoppingCartService.getCartTotalAmount());
        }
        return "user/contact";
    }

    @GetMapping("/user/login-register")
    public String loginRegister(Model model) {
        model.addAttribute("login", new Login());
        return "user/login-register";
    }

    @PostMapping("/user/login-register")
    public String processLoginForm(@Valid @ModelAttribute("login") Login Login, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/login-register";
        }
        return "redirect:/user/index";
    }

    @GetMapping("/user/product-details")
    public String getProductDetails(@RequestParam(name = "productId", required = false) Integer productId,
            @RequestParam(name = "productName", required = false) String productName,
            Model model) {
        if (productId == null || productName == null) {
            return "redirect:/user/404";
        }

        SanPham chiTietSanPham = sanPhamDAO.findById(productId).get();
        model.addAttribute("chiTietSanPham", chiTietSanPham);

        List<SanPham> listSanPham = sanPhamDAO.findAll();

        List<SanPham> sanPhamLienQuan = new ArrayList<>();
        int nhomSanPhamId = chiTietSanPham.getDanhMucSanPham().getNhomSanPham().getNhomSanPhamId();

        for (SanPham sp : listSanPham) {
            if (sp.getSanPhamId() != chiTietSanPham.getSanPhamId()
                    && sp.getDanhMucSanPham().getNhomSanPham().getNhomSanPhamId() == nhomSanPhamId) {
                sanPhamLienQuan.add(sp);
            }
        }
        GioHang cart = sessionService.get("cart");
        if (cart != null) {
            List<ChiTietGioHang> list = cart.getGioHangChiTietGioHangs();
            model.addAttribute("cart", list);
            model.addAttribute("total", shoppingCartService.getCartTotalAmount());
        }
        model.addAttribute("sanPhamLienQuan", sanPhamLienQuan);

        return "user/product-details";

    }

    @Autowired
    ShoppingCartService shoppingCartService;
    @Autowired
    com.klbstore.dao.MauSacDAO mauSacDAO;
    @Autowired
    SessionService sessionService;

    @PostMapping("/cart/add")
    public String addToCart(Model model, @RequestParam("colorId") int colorId,
            @RequestParam(value = "quantity", defaultValue = "1") int quantity) {
        MauSac mauSac = mauSacDAO.findById(colorId).orElse(null);
        if (mauSac != null) {
            ChiTietGioHang cartItem = new ChiTietGioHang();
            cartItem.setMauSac(mauSac);
            cartItem.setSanPham(mauSac.getSanPham());
            cartItem.setSoLuong(quantity);
            shoppingCartService.addToCart(cartItem);
        }
        GioHang cart = sessionService.get("cart");
        List<ChiTietGioHang> list = cart.getGioHangChiTietGioHangs();
        model.addAttribute("total", shoppingCartService.getCartTotalAmount());
        model.addAttribute("cart", list);
        return "user/header";
    }

    @PostMapping("/cart/update")
    @ResponseBody
    public Map<String, Object> updateCart(@RequestParam("colorId") int colorId,
            @RequestParam("quantity") int quantity) {
        Map<String, Object> response = new HashMap<>();

        ChiTietGioHang updateCart = shoppingCartService.updateCart(colorId, quantity);
        if (updateCart != null) {
            response.put("success", true);
            response.put("total", shoppingCartService.getCartTotalAmount());
            response.put("giaBan", updateCart.getSanPham().getGiaBan());

            List<GiamGiaTrucTiep> giamGiaTrucTieps = updateCart.getSanPham().getSanPhamGiamGiaTrucTieps();
            try {
                if (!giamGiaTrucTieps.isEmpty()) {
                    GiamGiaTrucTiep lastGiamGia = giamGiaTrucTieps.get(giamGiaTrucTieps.size() - 1);
                    if (lastGiamGia.getGiamGiaTrucTiep() != null) {
                        LocalDateTime currentDate = LocalDateTime.now();
                        LocalDateTime ngayBatDau = lastGiamGia.getNgayBatDau();
                        LocalDateTime ngayKetThuc = lastGiamGia.getNgayKetThuc();

                        if (currentDate.isAfter(ngayBatDau) && currentDate.isBefore(ngayKetThuc)) {
                            response.put("giamGiaTrucTiep", lastGiamGia.getGiamGiaTrucTiep());
                        }
                    }
                }
            } catch (LazyInitializationException e) {
            }

            response.put("soLuong", updateCart.getSoLuong());
        } else {
            response.put("success", false);
            response.put("message", "Không tìm thấy màu sản phẩm trong giỏ hàng.");
        }

        return response;
    }

    @GetMapping("/cart/remove/{id}")
    public String removeCart(Model model, @PathVariable(value = "id") int id) {
        shoppingCartService.removeCart(id);
        return "redirect:/user/shopping-cart";
    }

    @RequestMapping("/user/search")
    public String searchAndPage(Model model,
            @RequestParam(value = "keywords", defaultValue = "") String keywords,
            @RequestParam(value = "p", defaultValue = "1") int page,
            @RequestParam(value = "sortBy", defaultValue = "related") String sortBy) {
        Pageable pageable;

        if (sortBy.equals("nameAsc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("tenSanPham").ascending());
        } else if (sortBy.equals("nameDesc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("tenSanPham").descending());
        } else if (sortBy.equals("priceAsc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("giaBan").ascending());
        } else if (sortBy.equals("priceDesc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("giaBan").descending());
        } else if (sortBy.equals("ratingAsc")) {
            Page<SanPham> sanPham = sanPhamDAO.findAllByTenSanPhamOrderByAverageRatingAsc("%" + keywords + "%",
                    PageRequest.of(page - 1, 5));
            List<SanPham> sanPhamList = sanPham.getContent();
            model.addAttribute("sanPham", sanPhamList);
            model.addAttribute("page", sanPham);
            model.addAttribute("keywords", keywords);
            model.addAttribute("sortBy", sortBy);
            return "user/shop-list";
        } else if (sortBy.equals("ratingDesc")) {
            Page<SanPham> sanPham = sanPhamDAO.findAllByTenSanPhamOrderByAverageRatingDesc("%" + keywords + "%",
                    PageRequest.of(page - 1, 5));
            List<SanPham> sanPhamList = sanPham.getContent();
            model.addAttribute("sanPham", sanPhamList);
            model.addAttribute("page", sanPham);
            model.addAttribute("keywords", keywords);
            model.addAttribute("sortBy", sortBy);
            return "user/shop-list";
        } else {
            pageable = PageRequest.of(page - 1, 5);
        }
        Page<SanPham> sanPham = sanPhamDAO.findAllByTenSanPhamLikeAndHienThi("%" + keywords + "%", true,
                pageable);
        List<SanPham> sanPhamList = sanPham.getContent();
        model.addAttribute("sanPham", sanPhamList);
        model.addAttribute("page", sanPham);
        model.addAttribute("keywords", keywords);
        model.addAttribute("sortBy", sortBy);
        return "user/shop-list";
    }

    @Autowired
    ContactService contactService;

    @GetMapping("/user/email")
    @ResponseBody
    public String email() {
        contactService.receiveContactFormSubmission("Kiên", "kienltpc04639@fpt.edu.vn", "Xin ch\u00E0o,\r\n" + //
                "\r\n" + //
                "T\u00F4i l\u00E0 Nguy\u1EC5n V\u0103n A, t\u00F4i mu\u1ED1n li\u00EAn h\u1EC7 v\u1EDBi b\u1EA1n v\u1EC1 s\u1EA3n ph\u1EA9m c\u1EE7a c\u1EEDa h\u00E0ng KLBStore. D\u01B0\u1EDBi \u0111\u00E2y l\u00E0 th\u00F4ng tin c\u1EE7a t\u00F4i:\r\n"
                + //
                "\r\n" + //
                "- T\u00EAn: Nguy\u1EC5n V\u0103n A\r\n" + //
                "- Email: nguyenvana@gmail.com\r\n" + //
                "- N\u1ED9i dung: T\u00F4i quan t\u00E2m \u0111\u1EBFn s\u1EA3n ph\u1EA9m \u00E1o thun nam v\u00E0 mu\u1ED1n bi\u1EBFt th\u00EAm chi ti\u1EBFt v\u1EC1 gi\u00E1 c\u1EA3 v\u00E0 c\u00E1ch \u0111\u1EB7t h\u00E0ng.\r\n"
                + //
                "\r\n" + //
                "Xin vui l\u00F2ng li\u00EAn h\u1EC7 l\u1EA1i v\u1EDBi t\u00F4i qua \u0111\u1ECBa ch\u1EC9 email tr\u00EAn ho\u1EB7c s\u1ED1 \u0111i\u1EC7n tho\u1EA1i 0123456789.\r\n"
                + //
                "\r\n" + //
                "Tr\u00E2n tr\u1ECDng,\r\n" + //
                "Nguy\u1EC5n V\u0103n A\r\n" + //
                "");
        return "Email sent successfully";
    }

    @RequestMapping("/user/shop-list")
    public ModelAndView shopList(@RequestParam(value = "nhomSanPhamId") int id,
            @RequestParam(value = "p", defaultValue = "1") int page,
            @RequestParam(value = "sortBy", defaultValue = "related") String sortBy) {
        GioHang cart = sessionService.get("cart");
        ModelAndView modelAndView = new ModelAndView("user/shop-list");

        if (cart != null) {
            List<ChiTietGioHang> list = cart.getGioHangChiTietGioHangs();
            modelAndView.addObject("cart", list);
            modelAndView.addObject("total", shoppingCartService.getCartTotalAmount());
        }

        Pageable pageable;

        if (sortBy.equals("nameAsc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("tenSanPham").ascending());
        } else if (sortBy.equals("nameDesc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("tenSanPham").descending());
        } else if (sortBy.equals("priceAsc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("giaBan").ascending());
        } else if (sortBy.equals("priceDesc")) {
            pageable = PageRequest.of(page - 1, 5, Sort.by("giaBan").descending());
        } else if (sortBy.equals("ratingAsc")) {
            Page<SanPham> sanPhamPage = sanPhamDAO.findAllByNhomSanPhamIdOrderByAverageRatingAsc(id,
                    PageRequest.of(page - 1, 5));
            List<SanPham> sanPhamList = sanPhamPage.getContent();
            modelAndView.addObject("sanPham", sanPhamList);
            modelAndView.addObject("page", sanPhamPage);
            modelAndView.addObject("nhomSanPhamId", id);
            modelAndView.addObject("sortBy", sortBy);
            return modelAndView;
        } else if (sortBy.equals("ratingDesc")) {
            Page<SanPham> sanPhamPage = sanPhamDAO.findAllByNhomSanPhamIdOrderByAverageRatingDesc(id,
                    PageRequest.of(page - 1, 5));
            List<SanPham> sanPhamList = sanPhamPage.getContent();
            modelAndView.addObject("sanPham", sanPhamList);
            modelAndView.addObject("page", sanPhamPage);
            modelAndView.addObject("nhomSanPhamId", id);
            modelAndView.addObject("sortBy", sortBy);
            return modelAndView;
        } else {
            pageable = PageRequest.of(page - 1, 5);
        }

        Page<SanPham> sanPhamPage = sanPhamDAO.findAllByHienThiAndDanhMucSanPham_NhomSanPham_NhomSanPhamId(true, id,
                pageable);
        List<SanPham> sanPhamList = sanPhamPage.getContent();

        modelAndView.addObject("sanPham", sanPhamList);
        modelAndView.addObject("page", sanPhamPage);
        modelAndView.addObject("nhomSanPhamId", id);
        modelAndView.addObject("sortBy", sortBy);

        return modelAndView;
    }

    @GetMapping("/user/shopping-cart")
    public String shoppingCart(Model model) {
        GioHang cart = sessionService.get("cart");
        if (cart != null) {
            List<ChiTietGioHang> list = cart.getGioHangChiTietGioHangs();
            model.addAttribute("cart", list);
            model.addAttribute("total", shoppingCartService.getCartTotalAmount());
        }
        return "user/shopping-cart";
    }

    @GetMapping("/user/wishlist")
    public String wishlist() {
        return "user/wishlist";
    }
}