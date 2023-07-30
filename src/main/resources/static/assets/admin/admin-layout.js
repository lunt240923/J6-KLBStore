app = angular.module("admin-app", ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl: "/assets/admin/layout/ThongKe.html",
        controller: "thongke-ctrl"
    })
    .when("/nhacungcap", {
        templateUrl: "/assets/admin/layout/QLNhaCungCap.html",
        controller: "nhacungcap-ctrl"
    })
    .when("/danhgia", {
        templateUrl: "/assets/admin/layout/QLDanhGia.html",
        controller: "danhgia-ctrl"
    })
    .when("/danhmuc", {
        templateUrl: "/assets/admin/layout/QLDanhMuc.html",
        controller: "danhmuc-ctrl"
    })
    .when("/donhang", {
        templateUrl: "/assets/admin/layout/QLDonHang.html",
        controller: "donhang-ctrl"
    })
    .when("/magiamgia", {
        templateUrl: "/assets/admin/layout/QLMaGiamGia.html",
        controller: "magiamgia-ctrl"
    })
    .when("/nguoidung", {
        templateUrl: "/assets/admin/layout/QLNguoiDung.html",
        controller: "nguoidung-ctrl"
    })
    .when("/phieunhap", {
        templateUrl: "/assets/admin/layout/QLPhieuNhap.html",
        controller: "phieunhap-ctrl"
    })
    .when("/sanpham", {
        templateUrl: "/assets/admin/layout/QLSanPham.html",
        controller: "sanpham-ctrl"
    })
    .otherwise({ redirectTo: '/' });
})