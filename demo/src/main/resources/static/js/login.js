var myapp = angular.module("myapp",[]);
myapp.controller("loginController",["$scope","$http","$location",function ($scope, $http, $location) {
    $scope.user = {};
    $scope.loginAction = function () {
        var index =  layer.load(0, {shade: false});
        $http({
            method : 'post',
            url : ctx + "appJson/admin/userlogin",
            data : $scope.user
        }).success(function (data) {
            if(data){
                /* 登录成功*/
                clicked("language");
            }else{
                /* 登录失败*/
                layer.alert( 'Login failed, please verify account password.', {
                    title:'Information',
                    skin: 'layui-layer-lan',
                    closeBtn: 0
                });
            }
            layer.close(index);
        }).error(function () {
            layer.alert( 'Abnormal error, please contact the administrator.', {
                title:'Information',
                skin: 'layui-layer-lan',
                closeBtn: 0
            });
            layer.close(index);
        })
    }
}]);