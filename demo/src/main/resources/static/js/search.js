var myapp = angular.module("myapp",[]);

myapp.controller("serachController",["$scope","$http",function ($scope, $http) {
    $scope.cat_title = "";

    /* 搜索框  */
    $scope.searchText = "";
    $scope.onKeyup = function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        var s = $scope.searchText;
        if(e && e.keyCode==13){ // enter 键
            $scope.getSearch();
        }
    }
    $scope.getSearch = function () {
        if($scope.searchText == "") return;
        $http({
            method : 'post',
            url : ctx + "appJson/getSerDetaileds",
            params:{"serach" : $scope.searchText}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.result = data.result;
            }
        })
    }

    // 初始化
    info();
    function info(){
        $http({
            method : 'post',
            url : ctx + "appJson/getHotspot"
        }).success(function (data) {
            if(data){
                $scope.hotspots = data.result.detaileds;
            }
        })
    }

    // 详情事件
    var lock = false; //默认未锁定
    $scope.getDetailed = function (dlId) {
        if(!lock) {
            lock = true; // 锁定
            $http({
                method : 'post',
                url : ctx + "appJson/addHotspot",
                params:{"dlId" : dlId}
            }).success(function (data) {
                if(data){
                    $scope.addip(0,dlId);
                    var url = ctx + "appPage/indexDetailed?dlId="+dlId+"&serch=true";
                    clicked(url);
                }else{
                    layer.alert( 'Abnormal error, please contact the administrator or refresh page', {
                        title:'Information',
                        skin: 'layui-layer-lan'
                        ,closeBtn: 0
                    });
                }
            })
        }
    }

    // 添加流量数
    $scope.addip = function (catId,dlId) {
        $http({
            method : "post",
            url : ctx + "appJson/addip",
            params : {"catId":catId,"dlId": dlId}
        }).success(function (data) {

        })
    }


}]);