function exeData(num, type) {
    loadpage();
}
function loadpage(PageCount,PageSize) {
    var myPageCount = PageCount;
    var myPageSize = PageSize;
    var countindex = myPageCount % myPageSize > 0 ? (myPageCount / myPageSize) + 1 : (myPageCount / myPageSize);

    $.jqPaginator('#pagination', {
        totalPages: parseInt(countindex),
        visiblePages: 7, //显示分页数
        currentPage: 1,
        first: '<li class="first"><a href="javascript:;">First</a></li>',
        prev: '<li class="prev"><a href="javascript:;"><i class="arrow arrow2"></i>Prev</a></li>',
        next: '<li class="next"><a href="javascript:;">Next<i class="arrow arrow3"></i></a></li>',
        last: '<li class="last"><a href="javascript:;">Last</a></li>',
        page: '<li class="page"><a href="javascript:;">{{page}}</a></li>',
        onPageChange: function (num, type) {
            $('#text').html('当前第' + num + '页');
         /*   if (type == "change") {
                exeData(num, type);
            }*/
        }
    });
}


$(function () {
    //loadpage(1,10);
});