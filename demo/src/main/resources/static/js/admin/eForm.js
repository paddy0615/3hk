// 初始化样式
$(function () {
    $(".eFormPage").addClass("active");
})
// admin/eForm
myapp.controller("eFormController",["$scope","$http",function ($scope, $http) {
    $scope.languages = {}
    $scope.langId = 0;
    $scope.eformType = 0;
    $scope.searchTest = "";
    // 分页
    $scope.PageCount = 0; // 总数
    $scope.CurrentPage = 1; // 当前页
    $scope.PageSize = 10; // 显示页数

    // 初始化
    $scope.into = function(CurrentPage,PageSize){
        var start = $("#ladate1").val();
        var end = $("#ladate2").val();
        if(end != ""){
            end = end +" 23:59:59";
        }
        var dataMap = {
            langId : $scope.langId,
            type : $scope.eformType,
            startTime : start,
            endTime : end,
            searchTest : $scope.searchTest,
            CurrentPage : CurrentPage,
            PageSize : PageSize
        }
        $http({
            method : 'post',
            url : ctx + "appJson/admin/getEformPage",
            data : JSON.stringify(dataMap)
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.eforms = data.result.eforms;
                $scope.languages = data.result.languages;
                $scope.languages.unshift({'id':0,'title':'All'});
                $scope.eformTypes = data.result.eformTypes;
                $scope.eformTypes.unshift({'id':0,'en':'All'});
                $scope.PageCount = data.result.PageCount;
                if($scope.PageCount > 0){
                   $scope.Paginator($scope.PageCount,CurrentPage,PageSize);
                }else{
                   // 没有数据时不显示
                   $('#pagination').jqPaginator('destroy');
                }
            }
        })
    }
    $scope.into($scope.CurrentPage,$scope.PageSize);

    // type下拉
    $scope.clickLanguage = function () {
        $scope.CurrentPage = 1; // 当前页
        $scope.into($scope.CurrentPage,$scope.PageSize);
    }

    // 查看
    $scope.getSet = function(id){
        window.open(ctx + "appPage/admin/eFormSet?eId="+id);
    }

    // close
    $scope.updateFeedbackStatus = function(id,df_nay_status){
        var s = "Confirm to Close the case ？";
        if(df_nay_status == 0){
            s = "Confirm to Open the case ？";
        }
        var myconfirm = layer.confirm(s, {
            title:'Confirmation',
            btn: ['OK','Cancel'] //按钮
        }, function(){
            $http({
                method : 'post',
                url : ctx + "appJson/admin/updateFeedbackStatus",
                params : {"df_id":id,"df_nay_status":df_nay_status}
            }).success(function (data) {
                /* 成功*/
                var index = layer.alert( 'Success', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    $scope.into($scope.CurrentPage,$scope.PageSize);
                    layer.close(index);
                });

            })
            layer.close(myconfirm);
        }, function(){
            layer.close(myconfirm);
        });

    }

    // 分页
    $scope.Paginator = function(PageCount,CurrentPage,PageSize){
        if(PageCount == 0){
            return;
        }
        var myPageCount = PageCount;
        var myPageSize = PageSize;
        var countindex = myPageCount % myPageSize > 0 ? (myPageCount / myPageSize) + 1 : (myPageCount / myPageSize);
        $.jqPaginator('#pagination', {
            totalPages: parseInt(countindex),
            visiblePages: 7, //显示分页数
            currentPage: CurrentPage,
            first: '<li class="first"><a href="javascript:;">First</a></li>',
            prev: '<li class="prev"><a href="javascript:;"><i class="arrow arrow2"></i>Prev</a></li>',
            next: '<li class="next"><a href="javascript:;">Next<i class="arrow arrow3"></i></a></li>',
            last: '<li class="last"><a href="javascript:;">Last</a></li>',
            page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
            onPageChange: function (num, type) {
                if (type == "change") {
                    $scope.CurrentPage = num;
                    $scope.into($scope.CurrentPage,$scope.PageSize);
                }
            }
        });

    }

    // laydate国际版
    laydate.render({
        elem: '#ladate1'
        ,lang: 'en'
    });
    laydate.render({
        elem: '#ladate2'
        ,lang: 'en'
    });

    // search
    $scope.getLayDate = function(){
        $scope.into($scope.CurrentPage,$scope.PageSize);
    }


    // 退出
    $scope.goCancel = function(url){
        clicked(url); // 跳url
    }
}]);

// admin/eFormSet
myapp.controller("eFormSetController",["$scope","$http",function ($scope, $http) {
    $scope.eId = GetUrlParam("eId")==""?0:GetUrlParam("eId");
    $scope.eform = "";

    // 初始化
    $scope.into = function(id){
        $http({
            method : 'post',
            url : ctx + "appJson/admin/findEformById",
            params :{"eId": id}
        }).success(function (data) {
            if(data){
                /* 成功*/
                $scope.eform = data.result.eform;
                $scope.title = data.result.title;
                $scope.Certificate_Nature = data.result.Certificate_Nature;
            }

        })
    }
    $scope.into($scope.eId);


    // close
    $scope.updateFeedbackStatus = function(id,df_nay_status){
        var s = "Confirm to Close the case ？";
        if(df_nay_status == 0){
            s = "Confirm to Open the case ？";
        }
        var myconfirm = layer.confirm(s, {
            title:'Confirmation',
            btn: ['OK','Cancel'] //按钮
        }, function(){
            $http({
                method : 'post',
                url : ctx + "appJson/admin/updateFeedbackStatus",
                params : {"df_id":id,"df_nay_status":df_nay_status}
            }).success(function (data) {
                /* 成功*/
                var index = layer.alert( 'Success', {
                    title:'Information',
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                },function () {
                    $scope.into($scope.dfId);
                    layer.close(index);
                });

            })
            layer.close(myconfirm);
        }, function(){
            layer.close(myconfirm);
        });

    }



    // 退出
    $scope.goCancel = function(url){
        clicked(url); // 跳url
    }
}]);