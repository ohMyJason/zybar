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
    var role = data.role;
    var content = data.content;
    var username = data.username;
    var photoUrl = data.photoUrl;
    var selectOnline = data.selectOnline;
    var sentTime = data.sentTime;
    var replay = data.replay;
    if (replay == "无选择回复") {
        replay = "";
    } else if (replay == null) {
        replay = "";
    } else {
        replay += "<br>"
    }

    var msgModel = "<div class=\"layui-card\">\n" +
        "                        <div class=\"layui-card-header\">" + username + " " + sentTime + "</div>\n" +
        "                        <div class=\"layui-card-body\">\n" +
        replay + "<b>" + content + "</b>" +
        "                        </div>\n" +
        "                    </div>"
    var userMsgModel = "<div class=\"layui-card\">\n" +
        "                        <div class=\"layui-card-header\"><button type=\"button\" style='margin-right: 5px' class=\"layui-btn layui-btn-sm layui-btn-warm\">回复@</button>" + username + " " + sentTime + "</div>\n" +
        "                        <div class=\"layui-card-body\">\n" +
        content +
        "                        </div>\n" +
        "                    </div>"

    if (selectOnline == 0) {
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
    } else if (selectOnline == 1) {
        if (role == 2) {
            $("#hjTeacherChat").append(msgModel);
            scrollDown("hjTeacherChat");
        } else if (role == 1) {
            $("#hjUserChat").append(userMsgModel);
            scrollDown("hjUserChat");
        }
    } else if (selectOnline == 2) {
        if (role == 2) {
            $("#jjTeacherChat").append(msgModel);
            scrollDown("jjTeacherChat");
        } else if (role == 1) {
            $("#jjUserChat").append(userMsgModel);
            scrollDown("jjUserChat");
        }
    } else if (selectOnline == 3) {
        if (role == 2) {
            $("#fwTeacherChat").append(msgModel);
            scrollDown("fwTeacherChat");
        } else if (role == 1) {
            $("#fwUserChat").append(userMsgModel);
            scrollDown("fwUserChat");
        }
    } else if (selectOnline == 4) {
        ajaxPOST('/getProductById', {productId: data.productId}, function (res) {
            data.productName = res.data.productName;
            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    shadeClose: false,
                    skin: 'yourclass',
                    content: `<div role="alert" class="el-notification left" style="top: 16px; z-index: 2000;"><!----><div class="el-notification__group"><h2 class="el-notification__title"></h2><div class="el-notification__content"><p><div class="notification" style="">
                            <p style="font-size: 16px;margin-bottom:0.5em;color:white;margin-left: 40%">操作分享</p>
                            <p style="font-size: 16px;margin-bottom:0.5em;color:white">` + new Date().toLocaleDateString() + `</p>
                            <p style="font-size: 16px;margin-bottom:0.5em;color:white">` + new Date().toLocaleTimeString() + `</p>
                            <p style="font-size: 16px;margin-bottom:0.5em;color:white">品种: ` + data.productName + `(` + data.contract + `)` + `</p>
                            <div style="background-color: #008001;border-radius: 7px;text-align: center;font-size:20px;margin-bottom:1em;color:white" id="opera">` + data.opera + `</div>
                            <p style="margin-bottom:1em;color:white"><span style="font-size: 40px;color: red" id="point">` + data.point + `</span>&nbsp&nbsp附近</p>
                            <p style="margin-bottom:0.5em;color:white">目标1:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.targetOne + `</span></p>
                            <p style="margin-bottom:0.5em;color:white">目标2:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.targetTwo + `</span></p>
                            <p style="margin-bottom:0.5em;color:white">仓位:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.position + `%</span></p>
                            <p style="margin-bottom:0.5em;color:white">止损:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.stopLoss + `</span></p>
                            <p style="margin-bottom:0.5em;color:white">委托类型:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.delegateType + `</span></p>
                            <p style="margin-bottom:0.5em;color:white">备注:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.remarks + `</span></p>
                            <p style="font-size: 16px;;color:white">` + data.operaType + `</p>
                            <p style="margin-left:0px;margin-top:50px;font-size: 8px;line-height:12px;color:#b2792d">特别提醒:实际的操作当中希望大家能够结合自己的操作风格和风险承担能力来选择合适的仓位
                            风险提示:投资有风险，入市需谨慎。本建议仅供参考，不作为操作依据。据此交易，风险自担。
                            </p>
                               </div></p></div><div class="el-notification__closeBtn el-icon-close"></div></div></div>`
                })

                if (data.opera=="空单建仓"){
                    $("#opera").css("background-color","green");
                    $("#point").css("color","green");
                } else if (data.opera=="多单建仓") {
                    $("#opera").css("background-color","red");
                    $("#point").css("color","red");
                }
            })
        })

    }else if(selectOnline == 5){
        setTimeout(function () {


            layui.use('layer', function () {
                var layer = layui.layer;
                layer.open({
                    type: 1,
                    title: false,
                    closeBtn: 1,
                    shadeClose: false,
                    skin: 'yourclass',
                    content: `<div role="alert" class="el-notification left" style="top: 16px; z-index: 2000;"><!----><div class="el-notification__group"><h2 class="el-notification__title"></h2><div class="el-notification__content"><p><div class="notification" style="">
                            <p style="margin-left:20%;font-size: 16px;margin-bottom:0.5em;color:white;margin-left: 40%">操作分享</p>
                            <p style="font-size: 16px;margin-bottom:0.5em;color:white">` + new Date().toLocaleDateString() + `</p>
                            <p style="font-size: 16px;margin-bottom:0.5em;color:white">` + new Date().toLocaleTimeString() + `</p>
                            <p style="font-size: 16px;margin-bottom:0.5em;color:white">品种: ` + data.productName + `</p>
                            <div style="background-color: green;border-radius: 7px;text-align: center;font-size:20px;margin-bottom:1em;color:white" id="closeOpera">` + data.closeOpera + `</div>
                            <p style="margin-bottom:1em;color:white"><span style="font-size: 40px;color: red" id="closePoint" >` + data.closePoint + `</span>&nbsp&nbsp附近</p>
                            <p style="margin-bottom:0.5em;color:white">委托类型:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.closeDelegateType + `</span></p>
                            <p style="margin-bottom:0.5em;color:white">操作类型:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.operaType + `</span></p>
                            <p style="margin-bottom:0.5em;color:white">备注:&nbsp&nbsp<span style="font-size: 20px;color: white">` + data.remark + `</span></p>
                            <p style="font-size: 16px;color:white">` + data.operaType + `</p>
                            <p style="margin-left:0px;margin-top:50px;nt-size: 8px;line-height:12px;color:#b2792d">特别提醒:实际的操作当中希望大家能够结合自己的操作风格和风险承担能力来选择合适的仓位
                            风险提示:投资有风险，入市需谨慎。本建议仅供参考，不作为操作依据。据此交易，风险自担。
                            </p>
                               </div></p></div><div class="el-notification__closeBtn el-icon-close"></div></div></div>`
                })

                if (data.closeOpera=="多单平仓") {
                    $("#closeOpera").css("background-color","green");
                    $("#closeOpera").text("卖出平仓")
                    $("#closePoint").css("color","green")

                }else if(data.closeOpera=='空单平仓'){
                    $("#closeOpera").css("background-color","red");
                    $("#closeOpera").text("买入平仓")
                    $("#closePoint").css("color","red")

                }


            })


        }, 5000);



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
    if (content != null) {

        content.scrollTop = content.scrollHeight;
    }


}

function getHistoryMsg(selectOnline) {
    ajaxPOST("/getMessageList", {selectOnline: selectOnline}, function (res) {
        if (res.code == 0) {
            for (let i = 0; i < res.count; i++) {
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
