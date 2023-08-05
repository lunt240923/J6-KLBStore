// let host = "http://localhost:8080/rest";

app.controller("magiamgia-ctrl", function ($scope, $http, $location) {

    $scope.form = {};
    $scope.items = [];

    $scope.reset = function () {
        $scope.form = null;
    }

    $scope.initialize = function () {
        var url = `${host}/giamgia`;
        $http.get(url).then((result) => {
            $scope.items = result.data;
            $scope.items.forEach(item => {
                item.ngayBatDau = new Date(item.ngayBatDau);
                item.ngayKetThuc = new Date(item.ngayKetThuc);
            })
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });
    }

    // load toàn bộ sv từ DB
    $scope.initialize();


    $scope.edit = function (id) {
        var url = `${host}/giamgia/${id}`;
        $http.get(url).then((resp) => {
            $scope.form = resp.data;
            $scope.form.ngayBatDau = new Date($scope.form.ngayBatDau);
            $scope.form.ngayKetThuc = new Date($scope.form.ngayKetThuc);
            $(".nav-tabs button:eq(0)").tab("show");
            console.log("Success", resp)
        }).catch((err) => {
            console.log("Error", err)
        });

    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/giamgia`;
        if (!$scope.myform.$valid) {
            return;
        } else {
            $http.post(url, item).then((resp) => {

                $scope.items.push(item);
                $scope.reset();
                $scope.initialize();
                console.log("Success", resp)
                alert("Thêm thành công!")
            }).catch((err) => {
                alert("Lỗi thêm mã giảm giá!")
                console.log("Error", err)
            });
        }
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/giamgia/${$scope.form.giamGiaId}`;
        if (!$scope.myform.$valid) {
            return;
        } else {
            $http.put(url, item).then((resp) => {
                var index = $scope.items.findIndex(item => item.giamGiaId == $scope.form.giamGiaId);
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

    $scope.delete = function (giamGiaId) {
        var url = `${host}/giamgia/${giamGiaId}`;
        if (confirm('Xác nhận xóa?')) {
            $http.delete(url).then((resp) => {
                var index = $scope.items.findIndex(item => item.giamGiaId == giamGiaId);
                $scope.items.splice(index, 1);
                $scope.reset();
                console.log("Success", resp)
                alert("Xóa thành công!")
            }).catch((err) => {
                console.log("Error", err)
                alert("Lỗi xóa mã giảm giá!")
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

