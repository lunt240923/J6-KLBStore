let host = "http://localhost:8080/rest";

app.controller("nhacungcap-ctrl", function ($scope, $http, $location) {
    $scope.form = {};
    $scope.nhacungcaps = [];
    $scope.reset = function () {
        $scope.form = null;
    }

    $scope.getAll = function () {
        var url = `${host}/nhacungcap`;
        $http.get(url).then((result) => {
            $scope.nhacungcaps = result.data;
            console.log("Success",resp);
        }).catch((err) => {
            console.log("Error",err)
        });
    }

    $scope.create = function () {
        var item = angular.copy($scope.form);
        var url = `${host}/nhacungcap`;
        $http.post(url, item).then((resp) => {
            
            $scope.items.push(item);
            $scope.reset();
            console.log("Success",resp)
        }).catch((err) => {
            console.log("Error",err)
        });
    }

    $scope.edit = function (id) {
        var url = `${host}/nhacungcap/edit/${id}`;
        $http.get(url).then((resp) => {
            $scope.form = resp.data;
            
            console.log("Success",resp)
        }).catch((err) => {
            console.log("Error",err)
        });
    }
    // load toàn bộ sv từ DB
    $scope.getAll();
})