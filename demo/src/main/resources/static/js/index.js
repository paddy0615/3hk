var myapp = angular.module("myapp",['pascalprecht.translate']);
// online chat
function onlineChat(langId) {
    if(langId == 1){
        // 香港 (繁體)
        var se = document.createElement('script'); se.type = 'text/javascript'; se.async = true;
        se.src = 'https://storage.googleapis.com/sonic-teleservices/js/8168ca61-a381-422e-9803-44e04dabc878.js';
        var done = false;
        se.onload = se.onreadystatechange = function() {
            if (!done&&(!this.readyState||this.readyState==='loaded'||this.readyState==='complete')) {
                done = true;
                SonicTeleservices.setTitle("聯絡我們!"); // for the pre-chat form
                SonicTeleservicesChat.setTitle("透過線上客服與我們聯絡"); // for the chat-form
                SonicTeleservices.setProactiveAutocloseDelay(0.5);// delay proactive auto close after 30 sec
            }
        };
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(se, s);
    }else if(langId == 2){
        // 中国 简体
        var se = document.createElement('script'); se.type = 'text/javascript'; se.async = true;
        se.src = 'https://storage.googleapis.com/sonic-teleservices/js/cd66238e-805c-4fd2-b3e1-ccabddaf8a12.js';
        var done = false;
        se.onload = se.onreadystatechange = function() {
            if (!done&&(!this.readyState||this.readyState==='loaded'||this.readyState==='complete')) {
                done = true;
                SonicTeleservices.setTitle("联络我们!"); // for the pre-chat form
                SonicTeleservicesChat.setTitle("透过在线客服与我们联络"); // for the chat-form
                SonicTeleservices.setProactiveAutocloseDelay(0.5);// delay proactive auto close after 30 sec
            }
        };
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(se, s);
    }else if(langId == 3){
        // 台灣 (繁體)
        var se = document.createElement('script'); se.type = 'text/javascript'; se.async = true;
        se.src = 'https://storage.googleapis.com/sonic-teleservices/js/8168ca61-a381-422e-9803-44e04dabc878.js';
        var done = false;
        se.onload = se.onreadystatechange = function() {
            if (!done&&(!this.readyState||this.readyState==='loaded'||this.readyState==='complete')) {
                done = true;
                SonicTeleservices.setTitle("聯絡我們!"); // for the pre-chat form
                SonicTeleservicesChat.setTitle("透過線上客服與我們聯絡"); // for the chat-form
                SonicTeleservices.setProactiveAutocloseDelay(0.5);// delay proactive auto close after 30 sec
            }
        };
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(se, s);
    }else if(langId == 4){
        // 日本 (日本語))
        var se = document.createElement('script'); se.type = 'text/javascript'; se.async = true;
        se.src = 'https://storage.googleapis.com/sonic-teleservices/js/d1fa9911-1e41-4df1-a090-a1fd9454a030.js';
        var done = false;
        se.onload = se.onreadystatechange = function() {
            if (!done&&(!this.readyState||this.readyState==='loaded'||this.readyState==='complete')) {
                done = true;
                SonicTeleservices.setTitle("連絡先情報!"); // for the pre-chat form
                SonicTeleservicesChat.setTitle("私たちとチャットしましょう"); // for the chat-form
                SonicTeleservices.setProactiveAutocloseDelay(0.5);// delay proactive auto close after 30 sec
            }
        };
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(se, s);
    }else if(langId == 5){
        // 대한민국(한국어)
        var se = document.createElement('script'); se.type = 'text/javascript'; se.async = true;
        se.src = 'https://storage.googleapis.com/sonic-teleservices/js/ed84ba43-860a-43fc-87ac-29e1fb8ce6ab.js';
        var done = false;
        se.onload = se.onreadystatechange = function() {
            if (!done&&(!this.readyState||this.readyState==='loaded'||this.readyState==='complete')) {
                done = true;
                SonicTeleservices.setTitle("연락처!"); // for the pre-chat form
                SonicTeleservicesChat.setTitle("연락해 주십시오."); // for the chat-form
                SonicTeleservices.setProactiveAutocloseDelay(0.5);// delay proactive auto close after 30 sec
            }
        };
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(se, s);
    }else{
        // Hong Kong (EN)
        var se = document.createElement('script'); se.type = 'text/javascript'; se.async = true;
        se.src = 'https://storage.googleapis.com/sonic-teleservices/js/ff4b2b7a-dfbb-47d8-9e51-766c426eb3cb.js';
        var done = false;
        se.onload = se.onreadystatechange = function() {
            if (!done&&(!this.readyState||this.readyState==='loaded'||this.readyState==='complete')) {
                done = true;
                SonicTeleservices.setTitle("Contact us!"); // for the pre-chat form
                SonicTeleservicesChat.setTitle("Chat with us!"); // for the chat-form
                SonicTeleservices.setProactiveAutocloseDelay(0.5);// delay proactive auto close after 30 sec
            }
        };
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(se, s);
        <!-- begin SonicTeleservices code -->
        /*var se = document.createElement('script'); se.type = 'text/javascript'; se.async = true;
        se.src = 'https://storage.googleapis.com/sonic-teleservices/js/09bcc7b8-3035-4460-8c0f-a080d4815c17.js';
        var done = false;
        se.onload = se.onreadystatechange = function() {
            if (!done&&(!this.readyState||this.readyState==='loaded'||this.readyState==='complete')) {
                done = true;
                /!* Place your SonicTeleservices JS API code below *!/
                /!* SonicTeleservices.allowChatSound(true); Example JS API: Enable sounds for Visitors. *!/
            }
        };
        var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(se, s);*/
        <!-- end SonicTeleservices code -->


    }
}

myapp.config(['$translateProvider',function($translateProvider){
    var lang = window.localStorage['lang'] || '6';
    $translateProvider
        .preferredLanguage(lang)
        .useStaticFilesLoader({
            prefix: ctx + 'js/i18n/',
            suffix: '.json'
        })
        .useSanitizeValueStrategy('escapeParameters');
}])
myapp.factory('T', ['$translate', function($translate) {
    var T = {
        T:function(key) {
            if(key){
                return $translate.instant(key);
            }
            return key;
        }
    }
    return T;
}]);

myapp.directive('onFinishRenderFilters', ['$timeout', function ($timeout) {
    return {
        restrict: 'A',
        link: function(scope,element,attr) {
            if (scope.$last === true) {
                var finishFunc=scope.$parent[attr.onFinishRenderFilters];
                if(finishFunc)
                {
                    finishFunc();
                }
            }
        }
    };
}])

// 路由
myapp.config(['$locationProvider', function($locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}]);

// index
myapp.controller("indexController",["$scope","$http","$location","$translate",function ($scope, $http,$location,$translate) {
    // 设置默认,langId==6语言，英文;catId = 0默认选第二个
    $scope.langId = GetUrlParam("langId")==""?6:GetUrlParam("langId");
    $scope.catId =  GetUrlParam("catId")==""?0:GetUrlParam("catId");
    $scope.lang_cout = 5;
    $scope.isGetUrl = false;
    $scope.cat_title = "";
    //indexShow 显示
    $scope.indexShow = true;
    $scope.searchShow = false;
    // 初始化
    into($scope.langId,$scope.catId);
    onlineChat($scope.langId);
    function into(langID,catId){
        $http({
            method : 'post',
            url : ctx + "appJson/getIndex",
            params:{"langId": langID,"catId" : catId}
        }).success(function (data) {
            $scope.isGetUrl = true;
            if(data){
                /* 成功*/
                $scope.result = data.result;
                $scope.langId = data.result.langId;
                $scope.selectTest = selectTest($scope.langId);
                $scope.selectTestUnll = selectTestUnll($scope.langId);
                $scope.hotspotTest = hotspotTest($scope.langId);
                angular.forEach($scope.result.languages,function (each) {
                    if($scope.langId == each.id){
                        $scope.problem = each.problem;
                        return;
                    }
                })
                angular.forEach(data.result.categories,function (each) {
                    if(catId == each.id){
                        $scope.cat_title = each.title;
                        return;
                    }
                })
                $scope.lang_cout = data.result.categories.length;
                if($scope.cat_title == ""){
                    $scope.cat_title = data.result.categories[1].title;
                }
            }else{
                /* 失败*/
                layer.alert( 'Abnormal error, please contact the administrator.', {
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                });
            }
        })
    }
    // 改导航宽
    $scope.completeRepeat= function(){
        if($scope.lang_cout > 5 ){
            $(".nav-li-text").width(Math.round(960/$scope.lang_cout)-12);
        }
    }
    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            var url = ctx + "appPage/index?langId="+$scope.langId+"&catId="+0;
            clicked(url);
        }
        // 强制更新  $scope.apply();
    }

    // 类别事件,idnex当前下标，id:类别ID
    $scope.clickCategory = function (idnex,cat) {
        if(idnex==0){
            getHKE($scope.langId);
        }else{
            if($scope.isGetUrl){
                var url = ctx + "appPage/index?langId="+cat.langId+"&catId="+cat.id;
                clicked(url);
            }
        }
    }

    /* 搜索框 开始*/
    $scope.checkSearchTags = function(){
        $http({
            method : "post",
            url : ctx + "appJson/checkSearchTags",
            params : {"search": $scope.searchTest,"langId" : $scope.langId}
        }).success(function (data) {
        })
    }
    $scope.getSearchTags = function(){
        $http({
            method : "post",
            url : ctx + "appJson/getSearchTags",
            params : {"search": $scope.searchTest,"langId" : $scope.langId}
        }).success(function (data) {
            $scope.searchShow = true;
            $scope.indexShow = false;
            $scope.detaileds =  data.result.detaileds;
        })
    }
    $scope.searchTest = "";
    $scope.getSearch = function (){
        if($scope.searchTest == ""){
            $scope.detaileds =  {};
            return;
        };
        $scope.checkSearchTags();
        var q = escape($scope.searchTest);
        var url = ctx + "appPage/index?langId="+$scope.langId+"&catId="+0+"&q="+q;
        clicked(encodeURI(url));
    }
    $scope.onKeyup = function(event){
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键
            $scope.getSearch();
        }
    }
    var sq = $location.search().q;
    if(undefined != sq && "" != sq){
        $scope.searchTest = unescape(sq);
        $scope.getSearchTags();
    }
    /* 搜索框 结束*/


    function info1(){
        // 热点数
        $http({
            method : 'post',
            url : ctx + "appJson/getHotspot",
            params:{"langId": $scope.langId}
        }).success(function (data) {
            if(data){
                $scope.hotspots = data.result.detaileds;
            }
        })
    }
    // 热点初始化
    info1();

}]);

// indexDetailed
myapp.controller("indexDetailedController",["$scope","$http","$sce","$location","$translate",function ($scope, $http, $sce,$location,$translate) {
    // 设置默认,langId==6语言，英文;catId = 0默认选第二个
    $scope.dlId = GetUrlParam("dlId");
    $scope.langId = GetUrlParam("langId")==""?6:GetUrlParam("langId");
    $scope.catId = 0;
    $scope.lang_cout = 5 ;
    $scope.isGetUrl = false;
    $scope.detailed ={};
    $scope.searchShow = false;
    // 初始化
    into($scope.dlId);
    function into(dlId){
        $http({
            method : 'post',
            url : ctx + "appJson/getByDetailed",
            params:{"dlId": dlId}
        }).success(function (data) {
            $scope.isGetUrl = true;
            if(data){
                /* 成功*/
                $scope.result = data.result;
                $scope.langId = data.result.langId;
                $translate.use($scope.langId.toString());
                $scope.dfcount = data.result.dfcount;
                $scope.selectTest = selectTest($scope.langId);
                $scope.selectTestUnll = selectTestUnll($scope.langId);
                $scope.hotspotTest = hotspotTest($scope.langId);
                $scope.feedbackTest = feedbackTest($scope.langId);
                $scope.commonLabel1 = commonLabel1($scope.langId);
                $scope.commonLabel2 = commonLabel2($scope.langId);
                $scope.commonLabel3 = commonLabel3($scope.langId);
                $scope.commonLabel4 = commonLabel4($scope.langId);
                $scope.commonLabel5 = commonLabel5($scope.langId);
                $scope.commonLabel6 = commonLabel6($scope.langId);
                $scope.commonLabel7 = commonLabel7($scope.langId);
                $scope.commonLabel10 = commonLabel10($scope.langId);

                onlineChat($scope.langId);
                $scope.detailed = data.result.detailed;
                // 显示内容
                $scope.content = $sce.trustAsHtml($scope.detailed.content);
                angular.forEach($scope.result.languages,function (each) {
                    if($scope.langId == each.id){
                        $scope.problem = each.problem;
                        return;
                    }
                })
                $scope.lang_cout = data.result.categories.length;
                $scope.eFormTypes = data.result.eFormTypes;
            }else{
                /* 失败*/
                layer.alert( 'Abnormal error.', {
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                });
            }
        })
    }

    // 改导航宽
    $scope.completeRepeat= function(){
        if($scope.lang_cout > 5 ){
            $(".nav-li-text").width(Math.round(960/$scope.lang_cout)-12);
        }
    }

    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            var url = ctx + "appPage/index?langId="+$scope.langId+"&catId="+0;
            clicked(url);
        }
        // 强制更新  $scope.apply();
    }

    // 类别事件,idnex当前下标，id:类别ID
    $scope.clickCategory = function (idnex,cat) {
        if(idnex==0){
            getHKE($scope.langId);
        }else{
            if($scope.isGetUrl){
                var url = ctx + "appPage/index?langId="+cat.langId+"&catId="+cat.id;
                clicked(url);
            }
        }
    }

    /* 搜索框  开始*/
    $scope.checkSearchTags = function(){
        $http({
            method : "post",
            url : ctx + "appJson/checkSearchTags",
            params : {"search": $scope.searchTest,"langId" : $scope.langId}
        }).success(function (data) {
        })
    }
    $scope.getSearchTags = function(){
        $http({
            method : "post",
            url : ctx + "appJson/getSearchTags",
            params : {"search": $scope.searchTest,"langId" : $scope.langId}
        }).success(function (data) {
            $scope.searchShow = true;
            $scope.detaileds =  data.result.detaileds;
        })
    }
    $scope.searchTest = "";
    $scope.getSearch = function (){
        if($scope.searchTest == ""){
            $scope.detaileds =  {};
            return;
        };
        $scope.checkSearchTags();
        var q = escape($scope.searchTest);
        var url = ctx + "appPage/indexDetailed?dlId="+$scope.dlId+"&langId="+$scope.langId+"&q="+q;
        clicked(encodeURI(url));
    }
    $scope.onKeyup = function(event){
        // $scope.arr1=$filter("filter")(arr,document.getElementById("wei").value);
        var e = event || window.event || arguments.callee.caller.arguments[0];
        if(e && e.keyCode==13){ // enter 键
            $scope.getSearch();
        }

    }
    var sq = $location.search().q;
    if(undefined != sq && "" != sq){
        $scope.searchTest = unescape(sq);
        $scope.getSearchTags();
    }
    /* 搜索框  结束*/

    function info1(){
        $http({
            method : 'post',
            url : ctx + "appJson/getHotspot",
            params:{"langId": $scope.langId}
        }).success(function (data) {
            if(data){
                $scope.hotspots = data.result.detaileds;
            }
        })
        // 智能向导
        $http({
            method : 'post',
            url : ctx + "appJson/getSmartGuide",
            params:{"dlId": $scope.dlId}
        }).success(function (data) {
            if(data){
                $scope.smartGuide = data.result.detaileds;
                if($scope.smartGuide.length > 0){
                    $scope.smartGuideType = true;
                }else{
                    $scope.smartGuideType = false;
                }
            }
        })
    }
    // 热点初始化
    info1();

    $scope.praiseNayTest = 0;
    $scope.dfId1 = 0;
    $scope.dfId2 = 0;
    <!-- 反馈:支持-->
    $("#praise").click(function(){
        var praise_img = $("#praise-img");
        var text_box = $("#add-num");
        var praise_txt = $("#praise-txt");
        var num=parseInt(praise_txt.text());
        if(praise_img.attr("src") == (ctx + "img/zan1.png")){
            $(this).html("<img src='"+ctx + "img/hui1.png' id='praise-img' class='animation' />");
            praise_txt.removeClass("hover");
            text_box.show().html("<em class='add-animation'>-1</em>");
            $(".add-animation").removeClass("hover");
            num -=1;
            praise_txt.text(num);
            $("#praiseNayTest").hide();
            $scope.praiseNayTest = 0;
            delFeedback($scope.dfId1);
            $scope.dfId1 = 0;
        }else{
            $(this).html("<img src='"+ctx + "img/zan1.png' id='praise-img' class='animation' />");
            praise_txt.addClass("hover");
            text_box.show().html("<em class='add-animation'>+1</em>");
            $(".add-animation").addClass("hover");
            num +=1;
            praise_txt.text(num);
            $("#praiseNayTest").show();
            $scope.praiseNayTest = 1;
            addFeedback(1,"");
        }
    });
    <!-- 反馈:反对-->
    $("#praise1").click(function(){
        var praise_img = $("#praise-img1");
        var text_box = $("#add-num1");
        var praise_txt = $("#praise-txt1");
        var num=parseInt(praise_txt.text());
        if(praise_img.attr("src") == (ctx + "img/zan2.png")){
            $(this).html("<img src='"+ctx + "img/hui2.png' id='praise-img1' class='animation' />");
            text_box.show().html("<em class='add-animation'>-1</em>");
            $(".add-animation").removeClass("hover");
            $("#praiseNayTest").hide();
            $scope.praiseNayTest = 0;
            delFeedback($scope.dfId2);
            $scope.dfId2 = 0;
        }else{
            $(this).html("<img src='"+ctx + "img/zan2.png' id='praise-img1' class='animation' />");
            text_box.show().html("<em class='add-animation'>+1</em>");
            $(".add-animation").addClass("hover");
            $("#praiseNayTest").show();
            $scope.praiseNayTest = 2;
            addFeedback(2,"");
        }
    });
    <!-- 反馈:add异步-->
    function addFeedback(type,content) {
        $http({
            method : "post",
            url : ctx + "appJson/addFeedback",
            data : {"type" : type,"dlId" : $scope.dlId,"content" : content}
        }).success(function (data) {
            if(data.result.type == 1){
                $scope.dfId1 = data.result.id;
            }else{
                $scope.dfId2 = data.result.id;
            }
        })
    }
    function delFeedback(dfId) {
        if(dfId > 0){
            $http({
                method : "post",
                url : ctx + "appJson/delFeedback",
                data : {"id" : dfId}
            }).success(function (data) {
            })
        }

    }
    $scope.dfContent = "";
    $scope.dfContentEmail = "";
    $scope.dfContentNumber = "";
    $scope.addDfContent = function () {
        if(!check1()){
            return;
        }
        var id = 0;
        if($scope.praiseNayTest == 1){
            id = $scope.dfId1
        }else if($scope.praiseNayTest == 2){
            id = $scope.dfId2
        }
        var index = layer.load(0, {shade: false});
        $http({
            method : "post",
            url : ctx + "appJson/updateFeedback",
            data : {"id":id,"type" : $scope.praiseNayTest,"dlId" : $scope.dlId,"content" : $scope.dfContent,"email":$scope.dfContentEmail,"number":$scope.dfContentNumber}
        }).success(function (data) {
            layer.close(index);
            if(data.code == 200){
                layer.msg('OK', {icon: 1});
                $scope.dfContent = "";
                $scope.dfContentEmail = "";
                $scope.dfContentNumber = "";
                setTimeout(function(){  //使用  setTimeout（）方法设定定时2000毫秒
                    window.location.reload();//页面刷新
                },1000);

            }else{
                layer.msg("Error", {icon: 5});
            }
        })
    }



    function check1() {
        var reg1 = /^(\w-*\.*)+@(\w-?)+(\.\w{2,})+$/
        var reg2 =  /^\d*$/
        if("" != $("#dfContentEmail").val() && !reg1.test($("#dfContentEmail").val())){ //正则验证不通过，格式不对
            alert(commonLabel8($scope.langId));
            return false;
        }
        if("" != $("#dfContentNumber").val() && !reg2.test($("#dfContentNumber").val())){ //正则验证不通过，格式不对
            alert(commonLabel9($scope.langId));
            return false;
        }
        return true;
    }


    // 跳转E-form
    $scope.getEform = function(id){
        window.open(ctx + "appPage/eForm"+id+"?langId="+$scope.langId+"&dlId="+$scope.dlId);
    }

}]);