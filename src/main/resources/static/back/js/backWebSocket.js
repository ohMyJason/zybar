var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://120.79.30.62:8080/websocket");
} else {
    alert('Not support websocket');
}


//将接收到的html代码（消息）贴到版面上
function setMessage(event) {
    console.log(event.data);
    var role = event.data.split("&*&")[0];  //老师是2 用户1
    var message = event.data.split("&*&")[1];  //消息
    var username = event.data.split("&*&")[2];    //用户名
    var photoUrl = event.data.split("&*&")[3];  // 头像
    var selectOnline = event.data.split("&*&")[4];  //选择是哪个直播间 0是大厅 1是黑金 2狙击 3风云
    var replay = event.data.split("&*&")[5];  //这个回复消息
    if (replay=="无选择回复") {
        replay = "";
    }else if(replay==null){
        replay = "";
    }else {
        replay+="<br>"
    }

    var msgModel =  "<div class=\"layui-card\">\n" +
        "                        <div class=\"layui-card-header\">" + username + "</div>\n" +
        "                        <div class=\"layui-card-body\">\n" +
        replay+"<b>"+message +"</b>" +
        "                        </div>\n" +
        "                    </div>"
    var userMsgModel = "<div class=\"layui-card\">\n" +
        "                        <div class=\"layui-card-header\"><button type=\"button\" style='margin-right: 5px' class=\"layui-btn layui-btn-sm layui-btn-warm\">回复@</button>" + username + "</div>\n" +
        "                        <div class=\"layui-card-body\">\n" +
         message +
        "                        </div>\n" +
        "                    </div>"

    if (selectOnline == 0){
        if (role == 2) {
            $("#teacherChat").append(msgModel);
            scrollDown("teacherChat");
        } else if (role == 1) {
            $("#userChat").append(userMsgModel);
            // $(".layui-card").click(function () {
            //     console.log($(this).html());
            // })
            scrollDown("userChat");
        }
    } else if (selectOnline==1){
        if (role == 2) {
            $("#hjTeacherChat").append(msgModel);
            scrollDown("hjTeacherChat");
        } else if (role == 1) {
            $("#hjUserChat").append(userMsgModel);
            scrollDown("hjUserChat");
        }
    } else if (selectOnline==2){
        if (role == 2) {
            $("#jjTeacherChat").append(msgModel);
            scrollDown("jjTeacherChat");
        } else if (role == 1) {
            $("#jjUserChat").append(userMsgModel);
            scrollDown("jjUserChat");
        }
    } else if (selectOnline==3){
        if (role == 2) {
            $("#fwTeacherChat").append(msgModel);
            scrollDown("fwTeacherChat");
        } else if (role == 1) {
            $("#fwUserChat").append(userMsgModel);
            scrollDown("fwUserChat");
        }
    }

    // ResizeImages();
}


//连接发生错误的回调方法
websocket.onerror = function () {
    // setMessageInnerHTML("error");
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.alert("连接出现错误，请重新连接");
    })
};

//连接成功建立的回调方法
websocket.onopen = function (event) {
    // setMessageInnerHTML("open");

    layui.use('layer', function () {
        var layer = layui.layer;
        layer.alert("连接成功");
    })
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
    setMessage(event);
}

//连接关闭的回调方法
websocket.onclose = function () {
    // setMessageInnerHTML("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    websocket.close();
}


//缩放图片到合适大小
// function ResizeImages() {
//     var myimg, oldwidth;
//     var maxwidth = $("#teacherChat").width() - 60;
//     // oldwidth=$("#teacherChat").find("img").width();
//     var imgs = document.getElementById('teacherChat').getElementsByTagName('img');   //如果你定义的id不是article，请修改此处
//
//     console.log($("#teacherChat").width());
//
//
//     for (i = 0; i < imgs.length; i++) {
//         myimg = imgs[i];
//         // 过滤掉表情
//         if (myimg.src.charAt(myimg.src.length - 3) != "g") {
//             // oldwidth = myimg.width;
//             myimg.width = maxwidth;
//             // 注释掉的代码本来想等比例放大的，没办法，获取不到原图片的高
//             // myimg.height=myimg.height*(myimg.width/oldwidth);
//         }
//     }
// }

// 保持滚动条在最下面
function scrollDown(divId) {
    var content = document.getElementById(divId);
    if (content!=null){
        content.scrollTop = content.scrollHeight;
    }
}

layui.use('layedit', function () {
    var layedit = layui.layedit;
    layedit.set({
        uploadImage: {
            url: '/uploadChatImg' //接口url
            , type: 'post' //默认post
        }
    });

    var index = layedit.build('edit'); //建立编辑器


    //发送消息
    // $("#send").click(function () {
    //     alert("sss");
    //     var content = layedit.getContent(index);
    //
    //     console.log(content);
    //     if (websocket.readyState != 1) {
    //         layui.use('layer', function () {
    //             var layer = layui.layer;
    //             layer.alert("直播间连接出现错误,请刷新页面重新连接");
    //         })
    //     }
    //     var message = "2&*&" + content + "&*&" + $.cookie("username")+"&*&"+$("#selectOnline").val()
    //     websocket.send(message);
    //     // parent.window.location.reload();
    //
    // })

});


