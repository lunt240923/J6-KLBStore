app.controller("donhang-ctrl", function ($scope, $http, $location) {

    $scope.form = {};
    $scope.items = [];
    $scope.ctDonHang = [];
    $scope.ctDonHangById = {};

    $scope.reset = function () {
        $scope.form = {
            ngayDangKy: new Date(),
            quyenDangNhap: false,
            gioiTinh: true
        };
    }

    $scope.initialize = function () {
        var url = `${host}/donhang`;
        $http.get(url).then((result) => {
            $scope.items = result.data;
            
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });

        var url = `${host}/ctdonhang`;
        $http.get(url).then((result) => {
            $scope.ctDonHang = result.data;
            $scope.ctDonHang.forEach(item => {
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

    // $scope.edit = function (id) {
    //     var url = `${host}/donhang/${id}`;
    //     $http.get(url).then((resp) => {
    //         $scope.form = resp.data;
    //         $scope.form.ngayDatHang = new Date($scope.form.ngayDatHang);
    //         $scope.form.ngayGiaoHang = new Date($scope.form.ngayGiaoHang);
    //         $scope.getctDonHangByDonHangId($scope.form.donhangId);
    //         $(".nav-tabs button:eq(0)").tab("show");
    //         console.log("Success", resp)
    //     }).catch((err) => {
    //         console.log("Error", err)
    //     });

    // }


    //lấy chi tiet don hang theo mã đơn hang
    $scope.edit = function (id) {
        var url = `${host}/${id}/ctdonhang`;
        $http.get(url).then((resp) => {
            $scope.form = resp.data;
            $(".nav-tabs button:eq(0)").tab("show"); //hiển thị chi tiết
            console.log("Id", resp)
        }).catch((err) => {
            console.log("Error", err)
        });
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/donhang`;
        if(!$scope.myform.$valid) {
            return;
        } 
        else {
            $http.post(url, item).then((resp) => {

                $scope.items.push(item);
                $scope.reset();
                $scope.initialize();  //reload lại items
                
                console.log("Success", resp)
                alert("Thêm thành công!")
            }).catch((err) => {
                alert("Lỗi thêm người dùng!")
                console.log("Error", err)
            });
        }
        
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/donhang/${$scope.form.donhangId}`;
        if(!$scope.myform.$valid) {
            return;
        } else {
            $http.put(url, item).then((resp) => {
                var index = $scope.items.findIndex(item => item.donhangId == $scope.form.donhangId);
                $scope.items[index] = resp.data;
                $scope.reset();
                $scope.initialize();  //reload lại items
                $(".nav-tabs button:eq(1)").tab("show"); //hiển thị bảng
                console.log("Success", resp)
                alert("Cập nhật thành công!")
            }).catch((err) => {
                console.log("Error", err)
                alert("Lỗi cập nhật!")
            });
        }
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