// 初始化样式
$(function () {
    $(".categoryPage").addClass("active");
})
// admin/category
myapp.controller("categoryController",["$scope","$http","$location",function ($scope, $http, $location) {
    // 设置默认,langId==1语言，第一个
    $scope.langId = GetUrlParam("langId")==""?1:GetUrlParam("langId");
    $scope.selLangId = Number(GetUrlParam("selLangId")==""?0:GetUrlParam("selLangId")); // 做跳转准备
    $scope.isGetUrl = false;
    $scope.categories = {};
    $scope.categoriesTemp = {}; //用于空的搜索
    var url = ctx + "appJson/admin/getCategoryPage";
    // 初始化
    if($scope.selLangId != 0){
        $scope.langId = $scope.selLangId;
    }
    into($scope.langId);
    function into(langID){
        $http({
            method : 'post',
            url : url,
            params:{"langId": langID}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.result = data.result;
                $scope.categories = data.result.categories;
                $scope.categoriesTemp = data.result.categories;
                $scope.isGetUrl =true;
                $scope.searchTest = "";
                $scope.searchType = false;
                $("[name='checkboxAll']:checkbox").prop("checked", false);
            }
        })
        $scope.selectTest = selectTest1(langID);
    }
    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            into($scope.langId);
            $scope.selected = [];
            $("[name='checkboxAll']:checkbox").prop("checked", false);
        }
    }

    // 编辑
    $scope.getEdit = function(catId){
        clicked(ctx + "appPage/admin/categoryEdit?catId="+catId+"&selLangId="+$scope.langId);
    }

    // 删除
    $scope.getDelete = function(id){
        var delId = id;
        var txt = "Are you sure you want to delete it?";
        if(id == 0){
            delId = $scope.selected.join("-");
            txt = "Do you want to delete the following checks?";
            var i = 0;
            angular.forEach($("[name='checkboxClien']:checkbox"), function (each) {
                if(each.checked){
                    i++;
                }
            })
            if(i==0){
                layer.alert("Please check out");
                return;
            }
        }
        var lock = false; //默认未锁定
        var myconfirm = layer.confirm(txt, {
            title:'Information',
            btn: ['OK','Cancel'] //按钮
        }, function(){
            if(!lock) {
                lock = true; // 锁定
                Candelete(delId);
            }
            layer.close(myconfirm);
        }, function(){
            layer.close(myconfirm);
        });

    }
    // 能删除
    function  Candelete(dlIds) {
        $http({
            method : 'post',
            url : ctx + "appJson/admin/category/delete",
            params:{"catIds": dlIds}
        }).success(function (data) {
            into($scope.langId);
        })
    }

    // 修改状态
    var lock2 = false; //默认未锁定
    $scope.editStatus = function (id,status,type) {
        if(!lock2){
            lock2 = true;  // 锁定
            var delId = id;
            if(id == 0){// 全部
                var txt = "Do you want to show all of the following?";
                if(type){
                    txt = "Do you want to hide all of the following?";
                }
                delId = $scope.selected.join("-");
                var i = 0;
                angular.forEach($("[name='checkboxClien']:checkbox"), function (each) {
                    if(each.checked){
                        i++;
                    }
                })
                if(i==0){
                    layer.alert("Please check out");
                    lock2 = false;
                    return;
                }
                var myconfirm = layer.confirm(txt, {
                    title:'Information',
                    btn: ['OK','Cancel'] //按钮
                }, function(){
                    CanEditStatus(delId,status);
                    layer.close(myconfirm);
                }, function(){
                    lock2 = false;
                    layer.close(myconfirm);
                });
            }else{ // 单个
                CanEditStatus(delId,status);
            }
        };
    }
    // 能编辑状态
    function  CanEditStatus(catIds,status) {
        $http({
            method : 'post',
            url : ctx + "appJson/admin/category/editStatus",
            params:{"catIds": catIds ,"status" : status}
        }).success(function (data) {
            /* 成功*/
            var index = layer.alert( 'Success', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            },function () {
                lock2 = false;
                into($scope.langId);
                $scope.selected = [];
                $("[name='checkboxAll']:checkbox").prop("checked", false);
                layer.close(index);
            });
        })
    }


    // 复选框
    $scope.selected = [];
    $scope.updateSelection = function($event, id){
        var checkbox = $event.target;
        var action = (checkbox.checked?'add':'remove');
        if(action == 'add' && $scope.selected.indexOf(id) == -1){
            $scope.selected.push(id);
            var flag = true;
            angular.forEach($("[name='checkboxClien']:checkbox"), function (each) {
                if(!each.checked){
                    flag = false;
                }
            })
            $("[name='checkboxAll']:checkbox").prop("checked", flag);
        }
        if(action == 'remove' && $scope.selected.indexOf(id)!=-1){
            var idx = $scope.selected.indexOf(id);
            $scope.selected.splice(idx,1);
            $("[name='checkboxAll']:checkbox").prop("checked", false);
        }
    }
    $scope.updateSelectionAll = function($event){
        var checkbox = $event.target;
        var action = (checkbox.checked?'add':'remove');
        if(action == 'add'){
            $("[name='checkboxClien']:checkbox").prop("checked", true);
            angular.forEach($("[name='checkboxClien']:checkbox"), function (each) {
                $scope.selected.push(Number(each.value));
            })
        }
        if(action == 'remove'){
            $("[name='checkboxClien']:checkbox").prop("checked", false);
            $scope.selected = [];
        }
    }

    // 置顶
    var lock2 = false; //默认未锁定
    $scope.editTop = function (id) {
        if(!lock2) {
            lock2 = true;  // 锁定
            $http({
                method : 'post',
                url : ctx + "appJson/admin/category/editTop",
                params:{"firstId" : $scope.categories[0].id , "catId" : id}
            }).success(function (data) {
                lock2 = false;
                into($scope.langId);
            })
        }
    }

    /* 搜索框  开始*/
    $scope.searchTest = "";
    $scope.searchType = false;
    $scope.getSearchTitle = function () {
        $scope.selected = [];
        $("[name='checkboxAll']:checkbox").prop("checked", false);
        if($scope.searchTest == ""){
            $scope.categories = $scope.categoriesTemp;
            return;
        };
        $http({
            method : 'post',
            url : ctx + "appJson/admin/category/getSearchTitle",
            params:{"langId":$scope.langId,"serach": $scope.searchTest}
        }).success(function (data) {
            /* 成功*/
            $scope.categories = data.result.categories;
            $scope.searchType = true;
        })
    }
    $scope.onKeyup = function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        var s = $scope.searchText;
        if(e && e.keyCode==13){ // enter 键
            $scope.getSearchTitle();
        }
    }
    /* 搜索框  结束*/

    // 退出，校验是否有修改
    $scope.goCancel = function(url){
        clicked(url); // 跳url
    }
}]);

// admin/categoryEdit
myapp.controller("categoryEditController",["$scope","$http",function ($scope, $http) {
    $scope.selLangId = Number(GetUrlParam("selLangId")==""?0:GetUrlParam("selLangId")); // 做跳转准备
    $scope.catId = GetUrlParam("catId")==""?0:GetUrlParam("catId");
    $scope.langId = GetUrlParam("langId")==""?1:GetUrlParam("langId");
    $scope.editType = "";
    $scope.language = {};
    $scope.categorie = {};
    var person = "";
    if($scope.catId != 0){
        $scope.addType = false;
        var url = ctx + "appJson/admin/getCategory";
        $http({
            method : 'post',
            url : url,
            params:{"catId": $scope.catId}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.categorie = data.result.categorie;
                person = JSON.stringify(data.result.categorie);
                $scope.language = data.result.language;
                $scope.editType ="< Edit < "+data.result.categorie.title;
            }
        })
    }else{
        $scope.addType =true;
        var url = ctx + "appJson/getLanguageAll";
        $http({
            method : 'post',
            url : url,
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.languages = data;
                if($scope.selLangId != 0){
                    $scope.langId = $scope.selLangId;
                }
                $scope.editType ="< Add";
            }
        })


    }

    // update
    var lock1 = false; //默认未锁定
    $scope.submitUpdate = function () {
        //判断
        if(!chekFrom()){
            return;
        };
        if(!lock1) {
            var index =  layer.load(0, {shade: false});
            lock1 = true; // 锁定
            $http({
                method : 'post',
                url : ctx + 'appJson/admin/category/update',
                data : $scope.categorie
            }).then(function(resp){
                layer.alert( 'Success', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    var url = ctx + "appPage/admin/category?selLangId="+$scope.categorie.langId;
                    clicked(url);
                });
                layer.close(index);
            },function(resp){
                layer.alert( 'Abnormal error, please contact the administrator or refresh page', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    reloadRoute();
                });
                layer.close(index);
            });
        }

    }

    // add
    var lock = false; //默认未锁定
    $scope.submitAdd = function () {
        //判断
        if(!chekFrom()){
            return;
        };
        if(!lock) {
            var index =  layer.load(0, {shade: false});
            lock = true; // 锁定
            $http({
                method : 'post',
                url : ctx + 'appJson/admin/category/add',
                params : {"langId" :$scope.langId ,"categorieTitle" : $scope.categorie.title}
            }).then(function(resp){
                layer.alert( 'Success', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    var url = ctx + "appPage/admin/category?selLangId="+$scope.langId;
                    clicked(url);
                });
                layer.close(index);
            },function(resp){
                layer.alert( 'Abnormal error, please contact the administrator or refresh page', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    reloadRoute();
                });
                layer.close(index);
            });
        }
    }

    // 判断title是否为空
    function chekFrom() {
        if(undefined == $scope.categorie.title || $scope.categorie.title == ""){
            layer.alert( 'The title should not be empty.', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            })
            return false;
        }
        return true;
    }

    // 退出，校验是否有修改
    $scope.goCancel = function(url){
        if("" != person && person != JSON.stringify($scope.categorie)){
            comGoCancel(url);
        }else if("" != url){
            clicked(url); // 跳url
        }else{
            goBack(); // 返回上一页
        }
    }
}]);