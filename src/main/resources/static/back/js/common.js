
// 获取url中的参数
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return '';
}

function RequestParameter() {
    var url = window.location.search; //获取url中"?"符后的字串
    url = decodeURI(url);
    var theRequest = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);
        var strs = str.split("&");
        for (var i = 0; i < strs.length; i++) {
            theRequest[strs[i].split("=")[0]] = (strs[i].split("=")[1]);
        }
    }
    return theRequest
}


Date.prototype.Format = function (fmt) { // author: meizz
    var o = {
        "M+": this.getMonth() + 1, // 月份
        "d+": this.getDate(), // 日
        "H+": this.getHours(), // 小时
        "m+": this.getMinutes(), // 分
        "s+": this.getSeconds(), // 秒
        "q+": Math.floor((this.getMonth() + 3) / 3), // 季度
        "S": this.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
};


function ajaxPOST(url, data, func){
    // $.ajax({
    //     method: 'POST',
    //     cache: false,
    //     url:  url + '?timestamp=' + $.now(),
    //     dataType: 'json',
    //     contentType: "application/json; charset=utf-8",
    //     data: JSON.stringify(data),
    //     success: function (data) {
    //         if(data.code == 0){
    //             //执行传入的函数
    //             if(func){func();}
    //         }else if(data.code == -100){
    //             layer.msg(data.msg);
    //         }else{
    //             layer.msg('操作失败，请稍候重试');
    //         }
    //     }
    // });
    $.ajax({
        method: 'POST',
        cache: false,
        url:  url,
        dataType: 'json',
        // contentType: "application/json; charset=utf-8",
        data: data,
        headers:{token:$.cookie("token")},
        success: function (data) {
            if(data.code == 0){
                //执行传入的函数
                if(func){func(data);}
            }else if(data.code == -100){
                layer.msg(data.msg);
            }else{
                layer.msg('操作失败，请稍候重试');
            }
        }
    });
}


function ajaxPostWithData(url, data, func) {
    $.ajax({
        method: 'POST',
        cache: false,
        url:  url + '?timestamp=' + $.now(),
        dataType: 'json',
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (data) {
            if(data.code == 0){
                //执行传入的函数
                if(func){func(data);}
            }else if(data.code == -100){
                layer.msg(data.msg);
            }else{
                layer.msg('操作失败，请稍候重试');
            }
        }
    });
}

//判断浏览器大小方法
function screen() {
    //获取当前窗口的宽度
    var width = $(window).width();
    if (width > 1200) {
        return 3;   //大屏幕
    } else if (width > 992) {
        return 2;   //中屏幕
    } else if (width > 768) {
        return 1;   //小屏幕
    } else {
        return 0;   //超小屏幕
    }
}


function openModelPage(content, url) {
    var index = layer.open({
        title : content,
        type: 2,
        content: url,
        area: ['90%', '100%'],
        maxmin: true
    });
}