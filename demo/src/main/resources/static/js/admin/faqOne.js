// 初始化样式
$(function () {
    $(".faqOnePage").addClass("active");
})
// admin/faqOne
myapp.controller("faqOneController",["$scope","$http",function ($scope, $http) {

    $http({
        method : 'post',
        url : ctx + "appJson/admin/getLibrabrys",
        params :{}
    }).success(function (data) {
        if(data){
            /* 成功*/
            $scope.librabries = data.result.librabries;
        }
    })

    // 查看
    $scope.getSet = function(id){
        clicked(ctx + "appPage/admin/feedbackSet?dfId="+id);
    }

    // 退出
    $scope.goCancel = function(url){
        clicked(url); // 跳url
    }
}]);

// faqOneEdit
myapp.controller("faqOneEditController",["$scope","$http",function ($scope, $http) {

    $scope.fl_id = GetUrlParam("fl_id")==""?0:GetUrlParam("fl_id");
    $scope.librabries = {}
    // 初始化显示
    $scope.info1 = function(){
        $scope.selectTest = selectTest($scope.langId);
        $http({
            method : 'post',
            url : ctx + "appJson/admin/getFaqOneEdit1",
            params:{"fl_id": $scope.fl_id}
        }).success(function (data) {
            /* 成功*/
            $scope.librabries = data.result.librabries;
            if($scope.librabries){
                $scope.editType ="< Edit < " + $scope.librabries.title;
            }else{
                $scope.editType = "< Add";
            }
        })
    }
    $scope.info1();


    var lock1 = false; //默认未锁定
    $scope.submitUpdate = function (ind) {
        //判断
        if(!chekFrom()){
            return;
        };
        if(!lock1) {
            var index =  layer.load(0, {shade: false});
            lock1 = true; // 锁定
            $http({
                method : 'post',
                url : ctx + 'appJson/admin/faqOne/faqOneUpdate',
                data : $scope.librabries
            }).success(function(resp){
                layer.alert( 'Success', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    var url = ctx + "appPage/admin/faqOne";
                    clicked(url);
                });
                layer.close(index);
            });
        }

    }


    // 判断title是否为空
    function chekFrom() {
        if( "" == $("#inputTitle").val().trim()){
            layer.alert( 'The title should not be empty.', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            })
            return false;
        }
        return true;
    }

    // 退出
    $scope.goCancel = function(url){
        clicked(url); // 跳url
    }
}]);