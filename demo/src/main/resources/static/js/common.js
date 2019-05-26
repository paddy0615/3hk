(function(w){
    w.clicked = function(url){
        w.location.href = url;
    }

    w.goBack = function () {
        w.history.back(-1);
    }

    w.reloadRoute = function () {
        w.location.reload();
    };

})(window);

// 对字符串;转义,解义escape 和 unescape; encodeURI 和 decodeURI

// 获取url参数
function GetUrlParam(paraName) {
    var url = document.location.toString();
    var arrObj = url.split("?");
    if (arrObj.length > 1) {
        var arrPara = arrObj[1].split("&");
        var arr;
        for (var i = 0; i < arrPara.length; i++) {
            arr = arrPara[i].split("=");
            if (arr != null && arr[0] == paraName) {
                return arr[1];
            }
        }
        return "";
    }
    else {
        return "";
    }
}


// www.hkexpress.com
function getHKE(langId){
    var suffix = "";
    if(langId == 1){
        // 香港 (繁體)
        suffix = "zh-hk";
    }else if(langId == 2){
        // 中国 简体
        suffix = "zh-cn";
    }else if(langId == 3){
        // 台灣 (繁體)
        suffix = "zh-tw";
    }else if(langId == 4){
        // 日本 (日本語))
        suffix = "ja";
    }else if(langId == 5){
        // 대한민국(한국어)
        suffix = "ko";
    }else if(langId == 6){
        // Hong Kong (EN)
        suffix = "en-hk";
    }
    clicked("https://www.hkexpress.com/"+suffix);
}

// 编辑页，判断是否有修改
function comGoCancel(url) {
    var myconfirm = layer.confirm("Has the content been modified, is it determined to leave?", {
        title:'Information',
        btn: ['OK','Cancel'] //按钮
    }, function(){
        if("" != url){
            clicked(url); // 跳url
        }else{
            goBack(); // 返回上一页
        }
        layer.close(myconfirm);
    }, function(){
        layer.close(myconfirm);
    });
}

// 搜索框文字
function selectTest(langId){
    var t = "Please enter keyword(s) to search separated by space";
    if(langId == 1){
        t = "請輸入關鍵字搜索，如多於一個關鍵字，請用空格鍵隔開";
    }else if(langId == 2){
        t = "请输入关键字搜索，如多于一个关键字，请用空格键隔开";
    }else if(langId == 3){
        t = "請輸入關鍵字搜索，如多於一個關鍵字，請用空格鍵隔開";
    }else if(langId == 4){
        t = "キーワードを入力し、検索くださいませ、もし一つ以上あれば、スペイスで分けてくださいませ";
    }else if(langId == 5){
        t = "키 워드를 입력하시고 검색하시기 바랍니다.  만약 키워드는 한 글자 이상되면 중간에서  스페이스 바로 입력하시기 바랍니다";
    }
    return t+".";
}
function selectTest1(langId){
    var t = "Search";
    if(langId == 1){
        t = "搜索";
    }else if(langId == 2){
        t = "搜索";
    }else if(langId == 3){
        t = "搜索";
    }else if(langId == 4){
        t = "検索";
    }else if(langId == 5){
        t = "검색";
    }
    return t+"...";
}
function selectTestUnll(langId){
    var t = "There is no result found for such keyword. Please try to enter another word or put a space between the keywords.";
    if(langId == 1){
        t = "抱歉未能找到相關結果，請試試輸入其他關鍵字再進行搜索或嘗試用空白鍵隔開關鍵字，例如查詢更改航班，試試搜索更改 航班";
    }else if(langId == 2){
        t = "抱歉未能找到相关结果，请试试输入其他关键词再进行搜索或尝试用空格键隔开关键词，例如查询更改航班，试试搜索更改 航班";
    }else if(langId == 3){
        t = "抱歉未能找到相關結果，請試試輸入其他關鍵字再進行搜索或嘗試用空白鍵隔開關鍵字，例如查詢更改航班，試試搜索更改 航班";
    }else if(langId == 4){
        t = "申し訳ございません、これに关しての検査结果がございません、もし良ければ他のキーワードで検索し、もしくはスペース键でご検索くださいませ、例えば、航空便を変更する时には、“変更、便”で検索をお试しくださいませ。";
    }else if(langId == 5){
        t = "죄송합니다. 검색 결과가 없습니다 .일반적인 검색어로 다시 검색해 보세요.";
    }
    return t;
}

// 热点laber
function hotspotTest(langId){
    var t = "Frequently Asked Questions";
    if(langId == 1){
        t = "常見問題";
    }else if(langId == 2){
        t = "常见问题";
    }else if(langId == 3){
        t = "常見問題";
    }else if(langId == 4){
        t = "よくあるご質問";
    }else if(langId == 5){
        t = "자주묻는 질문";
    }
    return t;
}

// 反馈laber
function feedbackTest(langId){
    var t = "Does it help?";
    if(langId == 1){
        t = "這有幫助嗎?";
    }else if(langId == 2){
        t = "这有帮助吗?";
    }else if(langId == 3){
        t = "這有幫助嗎?";
    }else if(langId == 4){
        t = "お力になれましたか。";
    }else if(langId == 5){
        t = "이것이 도움이 될겁니까";
    }
    return t;
}

// laber1 提交
function commonLabel1(langId) {
    var t = "submit";
    if(langId == 1){
        t = "提交";
    }else if(langId == 2){
        t = "提交";
    }else if(langId == 3){
        t = "提交";
    }else if(langId == 4){
        t = "確認";
    }else if(langId == 5){
        t = "제출";
    }
    return t;
}
// laber2
function commonLabel2(langId) {
    var t = "Please give us your comment so that we can do better in the future";
    if(langId == 1){
        t = "請留下您的意見或建議讓我們將來不斷改善";
    }else if(langId == 2){
        t = "请留下您的意见或建议让我们将来不断改善";
    }else if(langId == 3){
        t = "請留下您的意見或建議讓我們將來不斷改善";
    }else if(langId == 4){
        t = "今后もっと质が良いサービスのため、贵重なご意见を教えてください";
    }else if(langId == 5){
        t = "나중에 잘할 수 있도록 고객님의 의견을 남겨주세요";
    }
    return t;
}
// laber3
function commonLabel3(langId) {
    var t = "Please leave your contact information so that we can further assist on your inquiry";
    if(langId == 1){
        t = "請留下您的聯繫方式以便我們進一步協助您的查詢";
    }else if(langId == 2){
        t = "请留下您的联系方式以便我们进一步协助您的查询";
    }else if(langId == 3){
        t = "請留下您的聯繫方式以便我們進一步協助您的查詢";
    }else if(langId == 4){
        t = "お客様のお问い合わせもっと详しく说明できるため、连络先をお愿いいたします";
    }else if(langId == 5){
        t = "문의하는 것을 잘 해결드리기  위해서 연락처를 남겨주세요";
    }
    return t;
}
// laber4
function commonLabel4(langId) {
    var t = "Your Contact Email";
    if(langId == 1){
        t = "您的聯絡郵箱";
    }else if(langId == 2){
        t = "您的联络邮箱";
    }else if(langId == 3){
        t = "您的聯絡郵箱";
    }else if(langId == 4){
        t = "おメールアドレス";
    }else if(langId == 5){
        t = "당신의 연락 이메일 주소";
    }
    return t;
}
// laber5
function commonLabel5(langId) {
    var t = "Your Contact Number";
    if(langId == 1){
        t = "您的聯絡電話";
    }else if(langId == 2){
        t = "您的联络电话";
    }else if(langId == 3){
        t = "您的聯絡電話";
    }else if(langId == 4){
        t = "お電話番号";
    }else if(langId == 5){
        t = "당신의 연락처 번호";
    }
    return t;
}
// laber6
function commonLabel6(langId) {
    var t = "Your Comments";
    if(langId == 1){
        t = "您的建議:";
    }else if(langId == 2){
        t = "您的建议:";
    }else if(langId == 3){
        t = "您的建議:";
    }else if(langId == 4){
        t = "ご意見";
    }else if(langId == 5){
        t = "당신의 건의";
    }
    return t;
}
// laber7
function commonLabel7(langId) {
    var t = "Or";
    if(langId == 1){
        t = "或";
    }else if(langId == 2){
        t = "或";
    }else if(langId == 3){
        t = "或";
    }else if(langId == 4){
        t = "または";
    }else if(langId == 5){
        t = "또는";
    }
    return t;
}
// laber8
function commonLabel8(langId) {
    var t = "Please input the correct email address";
    if(langId == 1){
        t = "請填寫正確的電郵地址";
    }else if(langId == 2){
        t = "请填写正确的邮箱地址";
    }else if(langId == 3){
        t = "請填寫正確的電郵地址";
    }else if(langId == 4){
        t = "正しいメールアドレスをご記入ください";
    }else if(langId == 5){
        t = "정확한 메일박스 주소를 기입해 주십시오";
    }
    return t;
}
// laber9
function commonLabel9(langId) {
    var t = "Please input the correct contact number";
    if(langId == 1){
        t = "請填寫正確的電話號碼";
    }else if(langId == 2){
        t = "请填写正确的电话号码";
    }else if(langId == 3){
        t = "請填寫正確的電話號碼";
    }else if(langId == 4){
        t = "正しい電話番号を記入してください";
    }else if(langId == 5){
        t = "정확한 전화번호를 기입해 주세요";
    }
    return t;
}
// laber10
function commonLabel10(langId) {
    var t = "You may also want to check below questions for reference";
    if(langId == 1){
        t = "您可能會想要查詢以下問題";
    }else if(langId == 2){
        t = "您可能会想要查询以下问题";
    }else if(langId == 3){
        t = "您可能會想要查詢以下問題";
    }else if(langId == 4){
        t = "また、下記のFAQをご参考ください";
    }else if(langId == 5){
        t = "아래의 같은 문제를 참고할 수 있습니다";
    }
    return t;
}