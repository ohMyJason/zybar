var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/websocket");
} else {
    alert('Not support websocket');
}


//将接收到的html代码（消息）贴到版面上
function setMessage(event) {
    var data = JSON.parse(event.data);
    console.log(data)
    var role = data.role;
    var content = data.content;
    var username = data.username;
    var photoUrl  = data.photoUrl;
    var selectOnline = data.selectOnline;
    var sentTime = data.sentTime;
    var replay = data.replay;
    if (replay=="无选择回复"){
        replay = "";
    }else if(replay==null){
        replay = "";
    }else {
        replay+="<br>"
    }

    var msgModel =  "<div class=\"layui-card\">\n" +
        "                        <div class=\"layui-card-header\">" + username + " "+sentTime+"</div>\n" +
        "                        <div class=\"layui-card-body\">\n" +
        replay+"<b>"+content +"</b>" +
        "                        </div>\n" +
        "                    </div>"
    var userMsgModel = "<div class=\"layui-card\">\n" +
        "                        <div class=\"layui-card-header\"><button type=\"button\" style='margin-right: 5px' class=\"layui-btn layui-btn-sm layui-btn-warm\">回复@</button>" + username + " "+sentTime+"</div>\n" +
        "                        <div class=\"layui-card-body\">\n" +
         content +
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
        layer.msg("连接成功");
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


// 保持滚动条在最下面
function scrollDown(divId) {
    var content = document.getElementById(divId);
    if (content!=null){

        content.scrollTop = content.scrollHeight;
    }


}

function getHistoryMsg(selectOnline) {
    ajaxPOST("/getMessageList",{selectOnline:selectOnline},function (res) {
        if (res.code ==0){
            for (let i = 0; i <res.count ; i++) {
                var msg = res.data[i];
                console.log(msg);
                var event = new Object();
                event.data = msg;
                setMessage(event);

            }
        } else {
            layer.alert("历史消息拉取失败")
        }
    })
}
