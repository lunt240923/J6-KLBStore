// let host = "http://localhost:8080/rest";

app.controller("sanpham-ctrl", function ($scope, $http, $location) {
    $scope.form = {};
    $scope.items = [];
    $scope.danhMucSP = [];


    $scope.reset = function () {
        $scope.form = null;
    }

    $scope.initialize = function () {
        var url = `${host}/sanpham`;
        $http.get(url).then((result) => {
            $scope.items = result.data;
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });

        var urll = `${host}/danhmucsanpham`;
        $http.get(urll).then((result) => {
            $scope.danhMucSP = result.data;
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });
    }


    // load toàn bộ sv từ DB
    $scope.initialize();


    $scope.edit = function (id) {
        var url = `${host}/sanpham/${id}`;
        $http.get(url).then((resp) => {
            $scope.form = resp.data;
            $scope.form.ngayTao = new Date($scope.form.ngayTao);
            $(".nav-tabs button:eq(0)").tab("show");
            console.log("Success", resp)
        }).catch((err) => {
            console.log("Error", err)
        });

    }

    

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/sanpham`;
        //validation form
        if(!$scope.myform.$valid) {
            return;
        }
        else {
            $http.post(url, item).then((resp) => {
                $scope.items.push(item);
                $scope.reset();
                $scope.initialize();  //reload lại items
                $(".nav-tabs button:eq(1)").tab("show");
                console.log("Success", resp)
                alert("Thêm thành công!")
            }).catch((err) => {
                alert("Lỗi thêm nhà cung cấp!")
                console.log("Error", err)
            });
        }
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/sanpham/${$scope.form.sanPhamId}`;
        //validation form
        if(!$scope.myform.$valid) {
            return;
        } else {
            $http.put(url, item).then((resp) => {
                var index = $scope.items.findIndex(item => item.sanPhamId == $scope.form.sanPhamId);
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

    $scope.delete = function (sanPhamId) {
        var url = `${host}/sanpham/${sanPhamId}`;
        //xác nhận xóa
        if(confirm('Xác nhận xóa?') == true) {
            $http.delete(url).then((resp) => {
                var index = $scope.items.findIndex(item => item.sanPhamId == sanPhamId);
                $scope.items.splice(index, 1);
                $scope.reset();
                console.log("Success", resp)
                alert("Xóa thành công!")
            }).catch((err) => {
                console.log("Error", err)
                alert("Lỗi xóa nhà cung cấp!")
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