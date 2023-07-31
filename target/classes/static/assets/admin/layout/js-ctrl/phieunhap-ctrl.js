// let host = "http://localhost:8080/rest";

app.controller("phieunhap-ctrl", function ($scope, $http, $location) {
    $scope.form = {};
    $scope.items = [];


    $scope.reset = function () {
        $scope.form = null;
    }

    $scope.initialize = function () {
        //load danh mục
        var url = `${host}/phieunhap`;
        $http.get(url).then((result) => {
            $scope.items = result.data;
            $scope.items.forEach(item => {
                item.ngayNhap = new Date(item.ngayNhap);
            })
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });

        //load nguoi dung
        var url = `${host}/nguoidung`;
        $http.get(url).then((result) => {
            $scope.nguoiDungs = result.data;
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });

        //load nguoi dung
        var url = `${host}/nhacungcap`;
        $http.get(url).then((result) => {
            $scope.nhaCungCaps = result.data;
            console.log("Success", result);
        }).catch((err) => {
            console.log("Error", err)
        });
    }


    // load toàn bộ sv từ DB
    $scope.initialize();


    $scope.edit = function (id) {
        var url = `${host}/phieunhap/${id}`;
        $http.get(url).then((resp) => {
            $scope.form = resp.data;
            $scope.form.ngayNhap = new Date($scope.form.ngayNhap);
            $(".nav-tabs button:eq(0)").tab("show");
            console.log("Success", resp)
        }).catch((err) => {
            console.log("Error", err)
        });

    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/phieunhap`;
        $http.post(url, item).then((resp) => {

            $scope.items.push(item);
            $scope.reset();
            $scope.initialize(); //reload lại items
            console.log("Success", resp)
            alert("Thêm thành công!")
        }).catch((err) => {
            alert("Lỗi thêm phiếu nhập!")
            console.log("Error", err)
        });
    }

    $scope.update = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/phieunhap/${$scope.form.phieuNhapId}`;
        $http.put(url, item).then((resp) => {
            var index = $scope.items.findIndex(item => item.phieuNhapId == $scope.form.phieuNhapId);
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

    $scope.delete = function (phieuNhapId) {
        var url = `${host}/phieunhap/${phieuNhapId}`;
        $http.delete(url).then((resp) => {
            var index = $scope.items.findIndex(item => item.phieuNhapId == phieuNhapId);
            $scope.items.splice(index, 1);
            $scope.reset();
            console.log("Success", resp)
            alert("Xóa thành công!")
        }).catch((err) => {
            console.log("Error", err)
            alert("Lỗi xóa danh mục!")
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