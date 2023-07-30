// let host = "http://localhost:8080/rest";

app.controller("nguoidung-ctrl", function ($scope, $http, $location) {

    $scope.form = {};
    $scope.items = [];

    $scope.reset = function () {
        $scope.form = {
            ngayDangKy: new Date(),
            quyenDangNhap: false,
            gioiTinh: true
        };
    }

    $scope.initialize = function () {
        var url = `${host}/nguoidung`;
        $http.get(url).then((result) => {
            $scope.items = result.data;
            $scope.items.forEach(item => {
                item.ngaySinh = new Date(item.ngaySinh);
                item.ngayDangKy = new Date(item.ngayDangKy);
            })
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });
    }

    // load toàn bộ sv từ DB
    $scope.initialize();
    $scope.items.matKhau = '\u2602';

    $scope.edit = function (id) {
        var url = `${host}/nguoidung/${id}`;
        $http.get(url).then((resp) => {
            $scope.form = resp.data;
            $scope.form.ngaySinh = new Date($scope.form.ngaySinh);
            $scope.form.ngayDangKy = new Date($scope.form.ngayDangKy);
            $(".nav-tabs button:eq(0)").tab("show");
            console.log("Success", resp)
        }).catch((err) => {
            console.log("Error", err)
        });

    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/nguoidung`;
        $http.post(url, item).then((resp) => {

            $scope.items.push(item);
            $scope.reset();
            console.log("Success", resp)
            alert("Thêm thành công!")
        }).catch((err) => {
            alert("Lỗi thêm người dùng!")
            console.log("Error", err)
        });
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/nguoidung/${$scope.form.nguoiDungId}`;
        $http.put(url, item).then((resp) => {
            var index = $scope.items.findIndex(item => item.nguoiDungId == $scope.form.nguoiDungId);
            $scope.items[index] = resp.data;
            $(".nav-tabs button:eq(1)").tab("show"); //hiển thị bảng
            console.log("Success", resp)
            alert("Cập nhật thành công!")
        }).catch((err) => {
            console.log("Error", err)
            alert("Lỗi cập nhật!")
        });
    }

    $scope.delete = function (nguoiDungId) {
        var url = `${host}/nguoidung/${nguoiDungId}`;
        $http.delete(url).then((resp) => {
            var index = $scope.items.findIndex(item => item.nguoiDungId == nguoiDungId);
            $scope.items.splice(index, 1);
            $scope.reset();
            console.log("Success", resp)
            alert("Xóa thành công!")
        }).catch((err) => {
            console.log("Error", err)
            alert("Lỗi xóa người dùng!")
        });
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

