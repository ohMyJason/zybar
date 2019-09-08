var websocket = null;


$(function () {
    $("body").keydown(function () {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('#send').click();
        }
    });

    $("#send").click(function () {
        if ($("#message-aera").val().replace(/\s*/g,"") =="") {
            layer.msg("请输入消息");
        } else {
            var message = $("#message-aera").val();
            //通过判断$("#selectOnline").val()是1还是2，来判断是用户发的还是老师发的
            websocket.send(1 + "&*&" + message + "&*&" + $.cookie("username") + "&*&" + $.cookie("userPhotoUrl") + "&*&" + $("#selectOnline").val() + "&*&#");
            $("#message-aera").val(" ");
            if (websocket.readyState != 1) {
                layer.alert("直播间连接错误，请刷新页面重试。");
            }
        }
    })
})

// 保持滚动条在最下面
function scrollDown(divId) {
    var content = document.getElementById(divId);
    if (content != null) {
        content.scrollTop = content.scrollHeight;
    }
}

function setMessage(event) {
    console.log(event.data);
    var role = event.data.split("&*&")[0]; //判断角色
    var message = event.data.split("&*&")[1]; //存储消息
    var username = event.data.split("&*&")[2]; //存储用户名
    var photoUrl = event.data.split("&*&")[3]; //存储用户头像
    var selectOnline = event.data.split("&*&")[4]; //判断直播间
    var reply = event.data.split("&*&")[5]; //回复消息
    if (reply == "无选择回复") {
        reply = "";
    } else if (reply == null) {
        reply = "";
    } else {
        reply += "<br>";
    }

    if (photoUrl==null||photoUrl=="null"){
        photoUrl="http://image.find37.com/150950657159f93e0b53113.png-live";
    }

    var userMsgModel = "<div class=\"chat-item\" id=\"hallUsers\" style=\"display: block;\">\n" +
        "                    <div class=\"chat-left\"><img src=\""+photoUrl+"\" +=\"\" -live=\"\">\n" +
        "                    </div>\n" +
        "                    <div class=\"chat-right\"><p class=\"time\">" + new Date().toLocaleTimeString() + "</p>\n" +
        "                        <div class=\"chat-user\"><img class=\"imgLogo\" src=\"http://image.find37.com/grade1.png\" +=\"\"\n" +
        "                                                    -live=\"\">" + username + "\n" +
        "                        </div>\n" +
        "                        <div class=\"chat-message\">" + message + "</div>\n" +
        "                    </div>\n" +
        "                </div>\n";
    var teacherMsgModel = "<div class=\"expert-chat-item\" style=\"display: block\" >\n" +
        "                    <div class=\"expert-chat-left\"><img src=\"" + photoUrl + "\" +=\"\"\n" +
        "                                                       -live=\"\"></div>\n" +
        "                    <div class=\"expert-chat-right\"><p class=\"expert-time\"><span style=\"color: #666666\">" + username + "</span>&nbsp;&nbsp;" + new Date().toLocaleTimeString() + "\n" +
        "                    </p>\n" +
        "                        <div class=\"expert-chat-message\">" + reply + "<b>" + message + "</b></div>\n" +
        "                    </div>\n" +
        "                </div>";
    //先判断哪个直播间，再判断角色
    if (selectOnline == 0) {
        if (role == 1) {
            $("#order-content").append(userMsgModel);

            scrollDown("order-content");
        } else if (role == 2) {
            $("#teacherOnline").append(teacherMsgModel);
            scrollDown("teacherOnline");
        }

    } else if (selectOnline == 1) {
        if (role == 1) {
            $("#hj-order-content").append(userMsgModel);
            scrollDown("hj-order-content");
        } else if (role == 2) {
            $("#hjOnline").append(teacherMsgModel);
            scrollDown("hj-order-content");
        }
    } else if (selectOnline == 2) {
        if (role == 1) {
            $("#jj-order-content").append(userMsgModel);
            scrollDown("jj-order-content-content");
        } else if (role == 2) {
            $("#jjOnline").append(teacherMsgModel);
            scrollDown("jjOnline");
        }

    } else if (selectOnline == 3) {
        if (role == 1) {
            $("#fw-order-content").append(userMsgModel);
            scrollDown("fw-order-content");
        } else if (role == 2) {
            $("#fwOnline").append(teacherMsgModel);
            scrollDown("fwOnline");
        }

    }

}


//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://120.79.30.62:8080/websocket");
} else {
    alert('Not support websocket');
}


//连接发生错误的回调方法
websocket.onerror = function () {
    // setMessageInnerHTML("error");
    // layer.alert("连接发生错误,请刷新");
    layer.alert("直播间连接失败");

};

//连接成功建立的回调方法
websocket.onopen = function (event) {
    // setMessageInnerHTML("open");
    // alert("连接直播间成功");
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

//将消息显示在网页上
function setMessageInnerHTML(innerHTML) {
    // document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//关闭连接
function closeWebSocket() {
    websocket.close();
}

