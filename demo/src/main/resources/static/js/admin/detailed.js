// 初始化样式
$(function () {
    $(".detailedPage").addClass("active");
})
// admin/detailed
myapp.controller("detailedController",["$scope","$http",function ($scope, $http) {
    // 设置默认,langId==1语言，第一个
    $scope.langId = GetUrlParam("langId")==""?1:GetUrlParam("langId");
    $scope.catId = GetUrlParam("catId")==""?0:GetUrlParam("catId");
    $scope.selLangId = Number(GetUrlParam("selLangId")==""?0:GetUrlParam("selLangId")); // 做跳转准备
    $scope.selCatId = Number(GetUrlParam("selCatId")==""?0:GetUrlParam("selCatId")); // 做跳转准备
    $scope.isGetUrl = false;
    $scope.categories = {};
    $scope.languages = {};
    $scope.detaileds = {};
    $scope.detailedsTemp = {};
    if($scope.selLangId != 0 && $scope.selCatId != 0 ){
        $scope.langId = $scope.selLangId;
        $scope.catId = $scope.selCatId;
    }
    $scope.into = function(langID,catId) {
        $http({
            method : 'post',
            url : ctx + "appJson/admin/getDetailedPage",
            params:{"langId": langID,"catId": catId}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.languages = data.result.languages;
                $scope.categories = data.result.categories;
                $scope.detaileds = data.result.detaileds;
                $scope.detailedsTemp = data.result.detaileds;
                $scope.isGetUrl =true;
                $scope.catId = data.result.selectCatId;
                if($scope.selCatId != 0){
                    $scope.catId = $scope.selCatId;
                }
                $scope.searchTest = "";
                $scope.selectTest = selectTest($scope.langId);
                $("[name='checkboxAll']:checkbox").prop("checked", false);
            }
        })
    }

    // 初始化
    $scope.into($scope.langId,$scope.catId);

    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            $scope.detaileds = {};
            $scope.categories = {};
            $scope.selCatId = 0;
            $scope.into($scope.langId,0);
            $scope.selected = [];
            $scope.searchTest = "";
            $("[name='checkboxAll']:checkbox").prop("checked", false);
        }
    }

    // 类别事件
    $scope.clickCategory = function() {
        if(null == $scope.catId){
            return; // 语言切换，避免数据为空
        }
        if($scope.isGetUrl){
            $scope.searchTest = "";
            $scope.selected = [];
            $("[name='checkboxAll']:checkbox").prop("checked", false);
            $scope.detaileds = {};
            $http({
                method : 'post',
                url : ctx + "appJson/admin/getDetaileds",
                params:{"langId": $scope.langId,"catId": $scope.catId}
            }).success(function (data) {
                if(data){
                    /* 成功*/
                    $scope.detaileds = data.result.detaileds;
                    $scope.isGetUrl =true;
                }
            })
        }
    }

    // 编辑url
    $scope.getEdit = function(dlId){
        clicked(ctx + "appPage/admin/detailedEdit?dlId="+dlId+"&selLangId="+$scope.langId+"&selCatId="+$scope.catId);
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
            url : ctx + "appJson/admin/detailed/delete",
            params:{"dlIds": dlIds}
        }).success(function (data) {
            $scope.into($scope.langId,$scope.catId);
        })
    }

    /* 搜索框  */
    $scope.searchTest = "";
    $scope.getSearchTitle = function () {
        $scope.selected = [];
        $("[name='checkboxAll']:checkbox").prop("checked", false);
        if($scope.searchTest == ""){
            $scope.detaileds = $scope.detailedsTemp;
            return;
        };
        $http({
            method : 'post',
            url : ctx + "appJson/admin/detailed/getSearchTags",
            params:{"langId":$scope.langId,"catId":$scope.catId,"serach": $scope.searchTest}
        }).success(function (data) {
            /* 成功*/
            $scope.detaileds = data.result.detaileds;
        })
    }
    $scope.onKeyup = function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        var s = $scope.searchText;
        if(e && e.keyCode==13){ // enter 键
            $scope.getSearchTitle();
        }
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
    function  CanEditStatus(dlIds,status) {
        $http({
            method : 'post',
            url : ctx + "appJson/admin/detailed/editStatus",
            params:{"dlIds": dlIds ,"status" : status}
        }).success(function (data) {
            /* 成功*/
            var index = layer.alert( 'Success', {
                title:'Information',
                skin: 'layui-layer-lan'
                ,closeBtn: 0
            },function () {
                lock2 = false;
                $scope.into($scope.langId,$scope.catId);
                $scope.selected = [];
                $("[name='checkboxAll']:checkbox").prop("checked", false);
                layer.close(index);
            });
        })
    }

    // 退出
    $scope.goCancel = function(url){
        clicked(url); // 跳url
    }
}]);



// admin/detailedEdit
myapp.controller("detailedEditController",["$scope","$http",function ($scope, $http) {
    $scope.dlId = GetUrlParam("dlId")==""?0:GetUrlParam("dlId");
    $scope.catId = GetUrlParam("catId")==""?0:GetUrlParam("catId");
    $scope.langId = GetUrlParam("langId")==""?1:GetUrlParam("langId");
    $scope.selLangId = Number(GetUrlParam("selLangId")==""?0:GetUrlParam("selLangId")); // 做跳转准备
    $scope.selCatId = Number(GetUrlParam("selCatId")==""?0:GetUrlParam("selCatId")); // 做跳转准备
    $scope.editType = "";
    $scope.detailed = {};
    var person = "";
    $scope.categories = {};
    $scope.language = {};
    $scope.languages = {};
    // ---------------------方便迁移数据,添加父级
    $scope.info5 = function(){
        $http({
            method : 'post',
            url : ctx + "appJson/admin/getLibrabryInfo",
            params:{"dlId": $scope.dlId}
        }).success(function (data) {
            /* 成功*/
            $scope.librabries = data.result.librabries;
        })
    }
    $scope.info5();
    $scope.fl_id = 1 ;


    // 初始化
    if($scope.dlId != 0){
        $scope.addType = false;
        var url = ctx + "appJson/admin/getDetailedUpdate";
        $http({
            method : 'post',
            url : url,
            params:{"dlId": $scope.dlId}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.detailed = data.result.detailed;
                person = JSON.stringify(data.result.detailed);
                $scope.language = data.result.language;
                $scope.categories = data.result.categories;
                $scope.editType ="< Edit < " + data.result.category.title+" < " + $scope.detailed.title;
                $scope.fl_id = $scope.detailed.flId==0?1:$scope.detailed.flId;
                console.log($scope.fl_id )
                showTags(2,data.result.tags);
                into2();
            }
        })
    }else{
        $scope.addType =true;
        $scope.detailed.title = "";
        var url = ctx + "appJson/admin/getDetailedAdd";
        if($scope.selLangId != 0){
            url += "?langId="+$scope.selLangId;
        }
        $http({
            method : 'post',
            url : url,
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.languages = data.result.languages;
                $scope.categories = data.result.categories;
                $scope.catId = data.result.selectCatId;
                if($scope.selLangId != 0 && $scope.selCatId != 0){
                    $scope.langId = $scope.selLangId;
                    $scope.catId = $scope.selCatId
                }
                $scope.editType ="< Add";
                showTags(1);
                into2();
            }
        })
    }

    function into2() {
        // 实例化富文本
        if($scope.addType){
            var ue = UE.getEditor('editorAdd');
        }else{
            var editor = UE.getEditor('editorUpdate',{initialFrameWidth: null});
            editor.ready(function() {
                if($scope.detailed.content){
                    editor.setContent($scope.detailed.content);
                }
            })
        }
    }

    // 语言事件
    $scope.clickLanguage = function() {
        var url = ctx + "appJson/admin/getCategoryByLangId";
        $scope.categories ={};
            $http({
            method : 'post',
            url : url,
            params:{"removeIndex": 1,"langId":$scope.langId}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.categories = data.result.categories;
                $scope.catId = data.result.selectCatId;
            }
        })
    }

    // update
    var lock1 = false; //默认未锁定
    $scope.submitUpdate = function (ind) {
        //判断
        if(!chekFrom()){
            return;
        };
        if(!lock1) {
            var tags = $('.demo2').tagEditor('getTags')[0].tags;
            var index =  layer.load(0, {shade: false});
            lock1 = true; // 锁定
            $scope.detailed.flId = $scope.fl_id;
            $scope.detailed.content = UE.getEditor('editorUpdate').getContent();
            $scope.detailed.contentTxt = UE.getEditor('editorUpdate').getContentTxt();
            $http({
                method : 'post',
                url : ctx + 'appJson/admin/detailed/update',
                data : JSON.stringify({'detailed':$scope.detailed,'tagsArr':tags})
            }).then(function(resp){
                layer.alert( 'Success', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    if(ind == 1){
                        location.reload();
                    }else{
                        var url = ctx + "appPage/admin/detailed?selLangId="+$scope.detailed.langId+"&selCatId="+$scope.detailed.catId;
                        clicked(url);
                    }
                });
                layer.close(index);
            },function(resp){
                layer.alert( 'Abnormal error, please contact the administrator or refresh page', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    location.reload();
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
            var tags = $('.demo1').tagEditor('getTags')[0].tags;
            var index =  layer.load(0, {shade: false});
            lock = true; // 锁定
            $scope.detailed.catId = $scope.catId;
            $scope.detailed.langId = $scope.langId;
            $scope.detailed.content = UE.getEditor('editorAdd').getContent();
            $scope.detailed.contentTxt = UE.getEditor('editorAdd').getContentTxt();
            $http({
                method : 'post',
                url : ctx + 'appJson/admin/detailed/add',
                data : JSON.stringify({'detailed':$scope.detailed,'tagsArr':tags})
            }).then(function(resp){
                layer.alert( 'Success', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    var url = ctx + "appPage/admin/detailed?selLangId="+$scope.langId+"&selCatId="+$scope.catId;
                    clicked(url);
                });
                layer.close(index);
            },function(resp){
                layer.alert( 'Abnormal error, please contact the administrator or refresh page', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    location.reload();
                });
                layer.close(index);
            });
        }

    }

    // 判断title是否为空
    function chekFrom() {
       if($scope.detailed.title == ""){
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
    $scope.goCancel = function(url,type){
        if($scope.dlId != 0){
            $scope.detailed.content = UE.getEditor('editorUpdate').getContent();
        }else{
            $scope.detailed.content = UE.getEditor('editorAdd').getContent();
        }
        if("" != person && person != JSON.stringify($scope.detailed)){
            comGoCancel(url);
        }else if("" != url){
            clicked(url); // 跳url
        }else{
            goBack(); // 返回上一页
        }
    }
    $scope.goCancel1 = function(url){
        clicked(url); // 跳url
    }



    // 标签
    function showTags(demo,tags) {
        $('.demo'+demo).tagEditor({
            initialTags: tags,
            delimiter: ', ', /* space and comma */
            placeholder: 'Enter tags ...'
        });
    }

}]);