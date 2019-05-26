var myapp = angular.module("myapp",['pascalprecht.translate']);

myapp.config(['$translateProvider',function($translateProvider){
    var lang = window.localStorage.lang || '6';
    $translateProvider
        .preferredLanguage(lang)
        .useStaticFilesLoader({
            prefix: ctx + 'js/i18n/',
            suffix: '.json'
        })
        //sanitize escape escapeParameters
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
}])

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
    }
}

// 路由
myapp.config(['$locationProvider', function($locationProvider) {
    // $locationProvider.html5Mode(true);
    $locationProvider.html5Mode({
        enabled: true,
        requireBase: false
    });
}]);

// eForm1Controller
myapp.controller("eForm1Controller",["$scope","$http","$location","$translate","T",function ($scope, $http,$location,$translate,T) {
    // 设置默认,langId==6语言，英文;
    $scope.langId = parseInt(GetUrlParam("langId")==""?6:GetUrlParam("langId"));
    function into(langID){
        $http({
            method : 'post',
            url : ctx + "appJson/getLanguageAll",
            params:{"langId": langID}
        }).success(function (data) {
            $scope.isGetUrl = true;
            $scope.languages = data;
        })

        $http({
            method : 'post',
            url : ctx + "appJson/E/getAreaNames"
        }).success(function (data) {
            $scope.e_area_names = data;
        })
    }
    // 初始化
    into($scope.langId);
    onlineChat($scope.langId);
    // 返HKE官网
    $scope.clickCategory = function (idnex) {
        getHKE($scope.langId);
    }
    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            $translate.use($scope.langId.toString());
            onlineChat($scope.langId);
        }
        // 强制更新  $scope.apply();
    }

    // laydate国际版
    laydate.render({
        elem: '#ladate1'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate1').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate1', 'NOT_VALIDATED',null)
                .validateField('ladate1');
        }
    });
    laydate.render({
        elem: '#ladate2'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate2').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate2', 'NOT_VALIDATED',null)
                .validateField('ladate2');
        }
    });
    laydate.render({
        elem: '#ladate3'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate3').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate3', 'NOT_VALIDATED',null)
                .validateField('ladate3');
        }
    });
    laydate.render({
        elem: '#ladate4'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate4').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate4', 'NOT_VALIDATED',null)
                .validateField('ladate4');
        }
    });
    laydate.render({
        elem: '#ladate5'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate5').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate5', 'NOT_VALIDATED',null)
                .validateField('ladate5');
        }
    });


    // 样式初始化
    $("#progressbarLi2").removeClass().addClass('progressbarLi');
    $scope.myTabContent = false;
    $scope.PNR = true
    $scope.checkPNR = function(){
        if(!$scope.PNR){
            $('.pnr-div').hide();
            $scope.myTabContent = true;
        }else{
            $('.pnr-div').show();
            $scope.myTabContent = false;
        }
    }
    $scope.eFormContent = true;
    $scope.eFormError = false;
    $scope.eFormSuccess = false;
    $scope.e = {};
    $scope.e.type = "1";
    // 获取那个dlId进入
    $scope.e.dlId = GetUrlParam("dlId")==""?0:GetUrlParam("dlId");

    /**
     * 提交
     */
    var lock1 = false; //默认未锁定
    $scope.eFormContentSubmit = function () {
        $scope.e.langId =  $scope.langId;
        if(!$scope.PNR){
            $scope.pnr = "";
            $scope.e.triptype = parseInt($(".nav-tabs .active").attr("data"));
            if($scope.e.triptype == 1){
                $scope.e.departing =  $scope.tab1_departing;
                $scope.e.going =  $scope.tab1_going;
                $scope.e.departingdate =   $("#ladate1").val();
                $scope.e.goingdate =   $("#ladate2").val();
                $scope.e.departingnew =  "";
                $scope.e.goingnew =  "";
            }else if($scope.e.triptype == 2){
                $scope.e.departing =  $scope.tab2_departing;
                $scope.e.going =  $scope.tab2_going;
                $scope.e.departingdate =  $("#ladate3").val();
                $scope.e.goingdate = "";
                $scope.e.departingnew =  "";
                $scope.e.goingnew =  "";
            }else if($scope.e.triptype == 3){
                $scope.e.departing =  $scope.e_area_names[0].key;
                $scope.e.going =  $scope.tab3_going;
                $scope.e.departingdate =  $("#ladate4").val();
                $scope.e.goingdate = $("#ladate5").val();
                $scope.e.departingnew =  $scope.tab3_departingnew;
                $scope.e.goingnew =  $scope.e_area_names[0].key;
            }
        }
        var bootstrapValidator = $(".eForm-div1").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            // 验证成功
            var index =  layer.load(0, {shade: false});
            if(!lock1) {
                lock1 = true; // 锁定
                $http({
                    method : 'post',
                    url : ctx + 'appJson/E/add',
                    data : $scope.e
                }).then(function(resp){
                    $scope.data = resp.data;
                    $("#progressbarLi1").removeClass().addClass('progressbarLi');
                    $("#progressbarLi2").removeClass();
                    $scope.eFormContent = false;
                    if( $scope.data.code == 200 ){
                        $scope.eFormSuccess = true;
                    }else{
                        $scope.eFormError = true;
                    }
                    layer.close(index);
                });
            }
        }else{
            return;
        }
    }

    /**
     * error返回
     */
    $scope.getEformContent = function () {
        $("#progressbarLi2").removeClass().addClass('progressbarLi');
        $("#progressbarLi1").removeClass();
        $scope.eFormError = false;
        $scope.eFormSuccess = false;
        $scope.eFormContent = true;
        lock1 = false; //解锁
    }

    /**
     * success返回
     */
    $scope.getEformContentDone = function () {
        location.reload();
    }

    /**
     * 内容验证
     */
    $(function () {
        $translate.use($scope.langId.toString());
        window.onload = function(){
            $('.eForm-div1').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    firstName: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    lastName: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    pnr : {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            },
                            emailAddress: {
                                message: T.T('error3')
                            }
                        }
                    },
                    tab1_departing: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    tab1_going: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    ladate1: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    ladate2: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    ladate3: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    ladate4: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    ladate5: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    tab2_departing: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    tab2_going: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    tab3_going: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    tab3_departingnew: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    }



                }
            });
        }
    });

}]);


// eForm2Controller
myapp.controller("eForm2Controller",["$scope","$http","$location","$translate",function ($scope, $http,$location,$translate) {
    // 设置默认,langId==6语言，英文;
    $scope.langId = parseInt(GetUrlParam("langId")==""?6:GetUrlParam("langId"));
    function into(langID){
        $http({
            method : 'post',
            url : ctx + "appJson/getLanguageAll",
            params:{"langId": langID}
        }).success(function (data) {
            $scope.languages = data;
        })
    }
    // 初始化
    $translate.use($scope.langId.toString());
    into($scope.langId);
    onlineChat($scope.langId);
    // 返HKE官网
    $scope.clickCategory = function (idnex) {
        getHKE($scope.langId);
    }
    // 语言事件
    $scope.clickLanguage = function() {
        $translate.use($scope.langId.toString());
        onlineChat($scope.langId);
        // 强制更新  $scope.apply();
    }

    $scope.eFormContent1 = true;
    $("#progressbarLi2").removeClass().addClass('progressbarLi');

    /**
     * 第一次提交
     */
    $scope.eFormContentSubmit1 = function () {
        $scope.eFormContent1 = false;
        $scope.eFormContent2 = true;
    }

    /**
     * 第二次提交
     */
    $scope.eFormContentSubmit2 = function () {
        $scope.eFormContent1 = false;
        $scope.eFormContent2 = false;
        $scope.eFormError = true;
        $scope.eFormSuccess = true;
        $("#progressbarLi1").removeClass().addClass('progressbarLi');
        $("#progressbarLi2").removeClass();
    }

    /**
     * 返回上一页
     */
    $scope.getEformContent1 = function () {
        $scope.eFormContent1 = true;
        $scope.eFormContent2 = false;
    }

    /**
     * 返回
     */
    $scope.getEformContent = function () {
        $("#progressbarLi2").removeClass().addClass('progressbarLi');
        $("#progressbarLi1").removeClass();
        $scope.eFormContent1 = true;
        $scope.eFormError = false;
        $scope.eFormSuccess = false;
    }

}]);


// eForm3Controller
myapp.controller("eForm3Controller",["$scope","$http","$location","$translate","T",function ($scope, $http,$location,$translate,T) {
    // 设置默认,langId==6语言，英文;
    $scope.langId = parseInt(GetUrlParam("langId")==""?6:GetUrlParam("langId"));
    function into(){
        $http({
            method : 'post',
            url : ctx + "appJson/E/getEform3"
        }).success(function (data) {
            $scope.languages = data.result.languages;
            $scope.e_area_names = data.result.e_area_names;
            $scope.e_certificates = data.result.e_certificates;
        })
    }
    // 初始化
    $translate.use($scope.langId.toString());
    into();
    onlineChat($scope.langId);
    // 返HKE官网
    $scope.clickCategory = function (idnex) {
        getHKE($scope.langId);
    }
    // 语言事件
    $scope.clickLanguage = function() {
        $translate.use($scope.langId.toString());
        onlineChat($scope.langId);
        // 强制更新  $scope.apply();
    }

    $scope.e = {};
    $scope.e.type = "3";
    // 获取那个dlId进入
    $scope.e.dlId = GetUrlParam("dlId")==""?0:GetUrlParam("dlId");
    // 默认航班编号为: 航班延误证明
    $scope.e.ecertificatetype = 1;

    $scope.eFormContent = true;
    $("#progressbarLi2").removeClass().addClass('progressbarLi');

    /**
     * 提交
     */
    var lock1 = false; //默认未锁定
    $scope.eFormContentSubmit = function () {
        $scope.e.langId =  $scope.langId;
        console.log( $scope.e)
        var bootstrapValidator = $(".eForm-div1").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()) {
            // 验证成功
            var index =  layer.load(0, {shade: false});
            if(!lock1) {
                lock1 = true; // 锁定
                $http({
                    method : 'post',
                    url : ctx + 'appJson/E/add',
                    data : $scope.e
                }).then(function(resp){
                    $scope.data = resp.data;
                    $("#progressbarLi1").removeClass().addClass('progressbarLi');
                    $("#progressbarLi2").removeClass();
                    $scope.eFormContent = false;
                    if( $scope.data.code == 200 ){
                        $scope.eFormSuccess = true;
                    }else{
                        $scope.eFormError = true;
                    }
                    layer.close(index);
                });
            }

        }else{
            return;
        }
    }

    /**
     * error返回
     */
    $scope.getEformContent = function () {
        $("#progressbarLi2").removeClass().addClass('progressbarLi');
        $("#progressbarLi1").removeClass();
        $scope.eFormError = false;
        $scope.eFormSuccess = false;
        $scope.eFormContent = true;
        lock1 = false; //解锁
    }

    /**
     * success返回
     */
    $scope.getEformContentDone = function () {
        location.reload();
    }


    /**
     * 内容验证
     */
    $(function () {
        $translate.use($scope.langId.toString());
        window.onload = function(){
            $('.eForm-div1').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    firstName: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    lastName: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    pnr : {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            }
                        }
                    },
                    email: {
                        validators: {
                            notEmpty: {
                                message: T.T('error2')
                            },
                            emailAddress: {
                                message: T.T('error3')
                            }
                        }
                    },
                    departing: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    },
                    going: {
                        validators: {
                            notEmpty: {
                                message: T.T('error4')
                            },
                            callback :{
                                message: T.T('error4'),
                                callback: function(value, validator) {
                                    if (value == "?") {
                                        return false;
                                    } else {
                                        return true;
                                    }
                                }
                            }
                        }
                    }



                }
            });
        }
    });

}]);


// eForm4Controller
myapp.controller("eForm4Controller",["$scope","$http","$location","$translate",function ($scope, $http,$location,$translate) {
    // 设置默认,langId==6语言，英文;
    $scope.langId = parseInt(GetUrlParam("langId")==""?6:GetUrlParam("langId"));
    function into(langID){
        $http({
            method : 'post',
            url : ctx + "appJson/getLanguageAll",
            params:{"langId": langID}
        }).success(function (data) {
            $scope.isGetUrl = true;
            if(data){
                /* 成功*/
                $scope.languages = data;
            }else{
                /* 失败*/
                layer.alert( 'Abnormal error, please contact the administrator.', {
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                });
            }
        })
    }
    // 初始化
    $translate.use($scope.langId.toString());
    into($scope.langId);
    onlineChat($scope.langId);
    // 返HKE官网
    $scope.clickCategory = function (idnex) {
        getHKE($scope.langId);
    }
    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            $translate.use($scope.langId.toString());
            onlineChat($scope.langId);
        }
        // 强制更新  $scope.apply();
    }

    $scope.eFormContent = true;
    $("#progressbarLi2").removeClass().addClass('progressbarLi');

    /**
     * 提交
     */
    $scope.eFormContentSubmit = function () {
        $("#progressbarLi1").removeClass().addClass('progressbarLi');
        $("#progressbarLi2").removeClass();
        $scope.eFormContent = false;
        $scope.eFormError = true;
        $scope.eFormSuccess = true;
    }

    /**
     * 返回
     */
    $scope.getEformContent = function () {
        $("#progressbarLi2").removeClass().addClass('progressbarLi');
        $("#progressbarLi1").removeClass();
        $scope.eFormContent = true;
        $scope.eFormError = false;
        $scope.eFormSuccess = false;
    }

}]);


// eForm5Controller
myapp.controller("eForm5Controller",["$scope","$http","$location","$translate",function ($scope, $http,$location,$translate) {
    // 设置默认,langId==6语言，英文;
    $scope.langId = parseInt(GetUrlParam("langId")==""?6:GetUrlParam("langId"));
    function into(langID){
        $http({
            method : 'post',
            url : ctx + "appJson/getLanguageAll",
            params:{"langId": langID}
        }).success(function (data) {
            $scope.isGetUrl = true;
            if(data){
                /* 成功*/
                $scope.languages = data;
            }else{
                /* 失败*/
                layer.alert( 'Abnormal error, please contact the administrator.', {
                    skin: 'layui-layer-lan'
                    ,closeBtn: 0
                });
            }
        })
        $http({
            method : 'post',
            url : ctx + "appJson/E/getAreaNames"
        }).success(function (data) {
            $scope.e_area_names = data;
        })
    }
    // 初始化
    $translate.use($scope.langId.toString());
    into($scope.langId);
    onlineChat($scope.langId);
    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            $translate.use($scope.langId.toString());
            onlineChat($scope.langId);
        }
        // 强制更新  $scope.apply();
    }
    // 类别事件,idnex当前下标，id:类别ID
    $scope.clickCategory = function (idnex) {
        getHKE($scope.langId);
    }

    // laydate国际版
    laydate.render({
        elem: '#ladate1'
        ,lang: 'en'
    });
    laydate.render({
        elem: '#ladate2'
    });
    laydate.render({
        elem: '#ladate3'
    });
    laydate.render({
        elem: '#ladate4'
    });
    laydate.render({
        elem: '#ladate5'
    });

    $scope.eFormContent = true;
    $("#progressbarLi2").removeClass().addClass('progressbarLi');
    /**
     * 第一次提交
     */
    $scope.eFormContentSubmit = function () {
        $scope.eFormContent = false;
        $scope.eFormContentPay = true;
    }
    /**
     * 第二次提交
     */
    $scope.eFormContentPaySubmit = function () {
        $("#progressbarLi1").removeClass().addClass('progressbarLi');
        $("#progressbarLi2").removeClass();
        $scope.eFormContentPay = false;
        $scope.eFormError = true;
        $scope.eFormSuccess = true;
    }
    /**
     * 完成,返回
     */
    $scope.getEformContent = function () {
        $("#progressbarLi2").removeClass().addClass('progressbarLi');
        $("#progressbarLi1").removeClass();
        $scope.eFormError = false;
        $scope.eFormSuccess = false;
        $scope.eFormContent = true;
    }
}]);


// eForm6Controller
myapp.controller("eForm6Controller",["$scope","$http","$location","$translate","T",function ($scope, $http,$location,$translate,T) {
    // 设置默认,langId==6语言，英文;
    $scope.langId = parseInt(GetUrlParam("langId")==""?6:GetUrlParam("langId"));

    function into(langID){
        $http({
            method : 'post',
            url : ctx + "appJson/getLanguageAll",
            params:{"langId": langID}
        }).success(function (data) {
            $scope.isGetUrl = true;
            $scope.languages = data;
        })

        $http({
            method : 'post',
            url : ctx + "appJson/E/getAreaNames"
        }).success(function (data) {
            $scope.e_area_names = data;
        })
    }

    // 初始化
    into($scope.langId);
    onlineChat($scope.langId);
    // 返HKE官网
    $scope.clickCategory = function (idnex) {
        getHKE($scope.langId);
    }
    // 语言事件
    $scope.clickLanguage = function() {
        if($scope.isGetUrl){
            $translate.use($scope.langId.toString());
            onlineChat($scope.langId);
        }
        // 强制更新  $scope.apply();
    }

    // laydate国际版
    laydate.render({
        elem: '#ladate1'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate1').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate1', 'NOT_VALIDATED',null)
                .validateField('ladate1');
        }
    });
    laydate.render({
        elem: '#ladate2'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate2').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate2', 'NOT_VALIDATED',null)
                .validateField('ladate2');
        }
    });
    laydate.render({
        elem: '#ladate3'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate3').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate3', 'NOT_VALIDATED',null)
                .validateField('ladate3');
        }
    });
    laydate.render({
        elem: '#ladate4'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate4').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate4', 'NOT_VALIDATED',null)
                .validateField('ladate4');
        }
    });
    laydate.render({
        elem: '#ladate5'
        ,lang: 'en'
        ,done:function(value,dates,edate){
            $('#ladate5').val(value);
            $('.eForm-div1').data('bootstrapValidator')
                .updateStatus('ladate5', 'NOT_VALIDATED',null)
                .validateField('ladate5');
        }
    });


    // 样式初始化
    $("#progressbarLi2").removeClass().addClass('progressbarLi');
    $scope.myTabContent = false;
    $scope.PNR = true
    $scope.checkPNR = function(){
        if(!$scope.PNR){
            $('.pnr-div').hide();
            $scope.myTabContent = true;
        }else{
            $('.pnr-div').show();
            $scope.myTabContent = false;
        }
    }
    $scope.eFormContent = true;
    $scope.eFormError = false;
    $scope.eFormSuccess = false;
    $scope.e = {};
    $scope.e.type = "6";
    // 获取那个dlId进入
    $scope.e.dlId = GetUrlParam("dlId")==""?0:GetUrlParam("dlId");


    /**
     * 提交
     */
    var lock1 = false; //默认未锁定
    $scope.eFormContentSubmit = function () {
        $scope.e.langId =  $scope.langId;
        if(!$scope.PNR){
            $scope.pnr = "";
            $scope.e.triptype = parseInt($(".nav-tabs .active").attr("data"));
            if($scope.e.triptype == 1){
                $scope.e.departing =  $scope.tab1_departing;
                $scope.e.going =  $scope.tab1_going;
                $scope.e.departingdate =   $("#ladate1").val();
                $scope.e.goingdate =   $("#ladate2").val();
                $scope.e.departingnew =  "";
                $scope.e.goingnew =  "";
            }else if($scope.e.triptype == 2){
                $scope.e.departing =  $scope.tab2_departing;
                $scope.e.going =  $scope.tab2_going;
                $scope.e.departingdate =  $("#ladate3").val();
                $scope.e.goingdate = "";
                $scope.e.departingnew =  "";
                $scope.e.goingnew =  "";
            }else if($scope.e.triptype == 3){
                $scope.e.departing =  $scope.e_area_names[0].key;
                $scope.e.going =  $scope.tab3_going;
                $scope.e.departingdate =  $("#ladate4").val();
                $scope.e.goingdate = $("#ladate5").val();
                $scope.e.departingnew =  $scope.tab3_departingnew;
                $scope.e.goingnew =  $scope.e_area_names[0].key;
            }
        }
        var bootstrapValidator = $(".eForm-div1").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(bootstrapValidator.isValid()){
            // 验证成功
            var index =  layer.load(0, {shade: false});
            if(!lock1) {
                lock1 = true; // 锁定
                $http({
                    method : 'post',
                    url : ctx + 'appJson/E/add',
                    data : $scope.e
                }).then(function(resp){
                    $scope.data = resp.data;
                    $("#progressbarLi1").removeClass().addClass('progressbarLi');
                    $("#progressbarLi2").removeClass();
                    $scope.eFormContent = false;
                    if( $scope.data.code == 200 ){
                        $scope.eFormSuccess = true;
                    }else{
                        $scope.eFormError = true;
                    }
                    layer.close(index);
                });
            }
        }else{
            return;
        }
    }

    /**
     * error返回
     */
    $scope.getEformContent = function () {
        $("#progressbarLi2").removeClass().addClass('progressbarLi');
        $("#progressbarLi1").removeClass();
        $scope.eFormError = false;
        $scope.eFormSuccess = false;
        $scope.eFormContent = true;
        lock1 = false; //解锁
    }

    /**
     * success返回
     */
    $scope.getEformContentDone = function () {
        location.reload();
    }

    /**
     * 内容验证
     */
    $(function () {
        $translate.use($scope.langId.toString());
        window.onload = function(){
              $('.eForm-div1').bootstrapValidator({
                  message: 'This value is not valid',
                  feedbackIcons: {
                      valid: 'glyphicon glyphicon-ok',
                      invalid: 'glyphicon glyphicon-remove',
                      validating: 'glyphicon glyphicon-refresh'
                  },
                  fields: {
                      firstName: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      lastName: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      pnr : {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      email: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              },
                              emailAddress: {
                                  message: T.T('error3')
                              }
                          }
                      },
                      tab1_departing: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error4')
                              },
                              callback :{
                                  message: T.T('error4'),
                                  callback: function(value, validator) {
                                      if (value == "?") {
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }
                              }
                          }
                      },
                      tab1_going: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error4')
                              },
                              callback :{
                                  message: T.T('error4'),
                                  callback: function(value, validator) {
                                      if (value == "?") {
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }
                              }
                          }
                      },
                      ladate1: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      ladate2: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      ladate3: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      ladate4: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      ladate5: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error2')
                              }
                          }
                      },
                      tab2_departing: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error4')
                              },
                              callback :{
                                  message: T.T('error4'),
                                  callback: function(value, validator) {
                                      if (value == "?") {
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }
                              }
                          }
                      },
                      tab2_going: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error4')
                              },
                              callback :{
                                  message: T.T('error4'),
                                  callback: function(value, validator) {
                                      if (value == "?") {
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }
                              }
                          }
                      },
                      tab3_going: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error4')
                              },
                              callback :{
                                  message: T.T('error4'),
                                  callback: function(value, validator) {
                                      if (value == "?") {
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }
                              }
                          }
                      },
                      tab3_departingnew: {
                          validators: {
                              notEmpty: {
                                  message: T.T('error4')
                              },
                              callback :{
                                  message: T.T('error4'),
                                  callback: function(value, validator) {
                                      if (value == "?") {
                                          return false;
                                      } else {
                                          return true;
                                      }
                                  }
                              }
                          }
                      }



                  }
              });
        }
    });


}]);
