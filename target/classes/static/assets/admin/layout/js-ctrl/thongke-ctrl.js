app.controller("thongke-ctrl", function ($scope, $http, $location) {

    $scope.form = {};
    $scope.items = [];
    $scope.ctDonHang = [];
    $scope.ctDonHangbyId = {};

    $scope.tongTien = 0.0;

    $scope.reset = function () {
        $scope.form = null;
    }

    $scope.initialize = function () {

        var url = `${host}/ctdonhang`;
        $http.get(url).then((result) => {
            $scope.ctDonHang = result.data;
            $scope.ctDonHang.forEach(item => {
                $scope.tongTien = $scope.tongTien + (item.giaBan * item.soLuong);
                item.donHang.ngayDatHang = new Date(item.donHang.ngayDatHang);
                item.donHang.ngayGiaoHang = new Date(item.donHang.ngayGiaoHang);
            })
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });
    }

    // load toàn bộ sv từ DB
    $scope.initialize();


    

})