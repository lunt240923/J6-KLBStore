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

    

    //lấy chi tiet don hang theo đơn hang
    $scope.getctDonHangByDonHangId = function (id) {
        var url = `${host}/donhang/${id}/ctdonhang`;
        $http.get(url).then((resp) => {
            $scope.ctDonHangbyId = resp.data;
            
            console.log("Success", resp)
        }).catch((err) => {
            console.log("Error", err)
        });
    }

    

    //Xóa ChiTietDonHang
    $scope.deleteCTDonHang = function (donhangId) {
        var url = `${host}/ctdonhang/${donhangId}`;
        if(confirm('Xác nhận xóa?')) {
            $http.delete(url).then((resp) => {
                var index = $scope.items.findIndex(item => item.donhangId == donhangId);
                $scope.items.splice(index, 1);
                $scope.initialize();
                console.log("Success", resp)
                alert("Xóa thành công!")
            }).catch((err) => {
                console.log("Error", err)
                alert("Lỗi xóa đơn hàng!")
            });
        }
    }


    //Phân trang
    $scope.pager = {
        page: 0,
        size: 5,
        get items() {
            let start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil($scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        previous() {
            this.page--;
            if (this.page < 0) {
                this.page = this.count - 1;
            }
        },
        next() {
            this.page++;
            if (this.page > this.count - 1) {
                this.page = 0;
            }
        },
        last() {
            this.page = this.count - 1;
        }
    };

})