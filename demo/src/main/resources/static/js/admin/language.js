// 初始化样式
$(function () {
    $(".languagePage").addClass("active");
})
// admin/category
myapp.controller("languageController",["$scope","$http","$location",function ($scope, $http, $location) {
    // 初始化
    $scope.languages = {};
    $scope.languagesTemp = {}; //用于空的搜索

    function into(){
        $http({
            method : 'post',
            url : ctx + "appJson/getLanguageAll",
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.languages = data;
                $scope.languagesTemp = data;
            }
        })
    }

    // 编辑
    $scope.getEdit = function(id){
        clicked(ctx + "appPage/admin/languageEdit?langId="+id);
    }

    // 删除
    $scope.getDelete = function(id){
        $http({
            method : 'post',
            url : ctx + "appJson/admin/category/countCatByLangId",
            params:{"langId": id}
        }).success(function (data) {
            if(data){
                /* 没数据*/
                var lock = false; //默认未锁定
                var myconfirm = layer.confirm("Are you sure you want to delete it?", {
                    title:'Information',
                    btn: ['OK','Cancel'] //按钮
                }, function(){
                    if(!lock) {
                        lock = true; // 锁定
                        Candelete(id);
                    }
                    layer.close(myconfirm);
                }, function(){
                    layer.close(myconfirm);
                });
            }else{
                /* 有数据*/
                Nodelete();
            }
        })
    }
    // 能删除
    function  Candelete(id) {
        $http({
            method : 'post',
            url : ctx + "appJson/language/delete",
            params:{"langId": id}
        }).success(function (data) {
            reloadRoute();
        })

    }
    // 不能删除
    function  Nodelete() {
        layer.alert( 'There is information correlation and cannot be deleted. I suggest you modify the release status.', {
            title:'Information',
            skin: 'layui-layer-lan'
            ,closeBtn: 0
        });
    }
    /* 搜索框  开始*/
    $scope.getSearchTitleq = function () {
        $http({
            method : 'post',
            url : ctx + "appJson/language/getSearchTitle",
            params:{"serach": $scope.searchTest}
        }).success(function (data) {
            /* 成功*/
            $scope.languages = data.result.languages;
        })
    }
    $scope.searchTest = "";
    $scope.getSearchTitle = function () {
        var q = escape($scope.searchTest);
        var url = ctx + "appPage/admin/language?q="+q;
        clicked(encodeURI(url));
    }
    $scope.onKeyup = function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键
            $scope.getSearchTitle();
        }
    }
    var sq = $location.search().q;
    if(undefined != sq && "" != sq){
        $scope.searchTest = unescape(sq);
        $scope.getSearchTitleq();
    }else{
        into();
    }
    /* 搜索框  结束*/

    // 退出
    $scope.goCancel = function(url){
        clicked(url); // 跳url
    }
}]);

myapp.controller("languageEditController",["$scope","$http",function ($scope, $http) {
    $scope.langId = GetUrlParam("langId")==""?1:GetUrlParam("langId");
    $scope.language = {};
    $scope.languageCheck = {};
    var person = "";
    $scope.editType = "";
    if($scope.langId != 0){
        $scope.addType = false;
        var url = ctx + "appJson/getLanguage";
        $http({
            method : 'post',
            url : url,
            params:{"langId": $scope.langId}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.language = data;
                person = JSON.stringify(data);
                $scope.editType = "< Edit < " + $scope.language.title;
            }
        })
    }else{
        $scope.addType =true;
        $scope.editType ="< Add";
    }

    // update
    $scope.submitUpdate = function () {
        $http({
            method : 'post',
            url : ctx + 'appJson/language/update',
            data : $scope.language
        }).then(function(resp){
            layer.alert( 'Success', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            },function () {
                var url = ctx + "appPage/admin/language";
                clicked(url);
            });
        },function(resp){
            layer.alert( 'Abnormal error, please contact the administrator or refresh page', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            },function () {
                location.reload();
            });
        });
    }

    // add
    $scope.submitAdd = function () {
        $http({
            method : 'post',
            url : ctx + 'appJson/language/add',
            params : {"title" : $scope.language.title,"problem" : $scope.language.problem}
        }).then(function(resp){
            layer.alert( 'Success', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            },function () {
                var url = ctx + "appPage/admin/language";
                clicked(url);
            });
        },function(resp){
            layer.alert( 'Abnormal error, please contact the administrator or refresh page', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            },function () {
                location.reload();
            });
        });
    }

    // 退出，校验是否有修改
    $scope.goCancel = function(url){
        if("" != person && person != JSON.stringify($scope.language)){
            comGoCancel(url);
        }else if("" != url){
            clicked(url); // 跳url
        }else{
            goBack(); // 返回上一页
        }
    }
}]);