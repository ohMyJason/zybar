var webSocketNewLive = "wss://ws.find37.com:7272";
// var webSocketNewLive = "ws://192.168.2.243:7272";
ws=new WebSocket(webSocketNewLive);
// 提示音播放函数
$(function() {
    $("#jplayer1").jPlayer({
        swfPath: "http://www.jplayer.org/latest/js/Jplayer.swf",
        ready: function () {
            $(this).jPlayer("setMedia", {
                mp3: "https://audio.qzy77.com/clyfb.mp3"
            });
        },
        supplied: "mp3"
    });
    $("#jplayer2").jPlayer({
        swfPath: "http://www.jplayer.org/latest/js/Jplayer.swf",
        ready: function () {
            $(this).jPlayer("setMedia", {
                mp3: "https://audio.qzy77.com/zs.mp3"
            });
        },
        supplied: "mp3"
    });
    $("#jplayer3").jPlayer({
        swfPath: "http://www.jplayer.org/latest/js/Jplayer.swf",
        ready: function () {
            $(this).jPlayer("setMedia", {
                mp3: "https://audio.qzy77.com/zy.mp3"
            });
        },
        supplied: "mp3"
    });
})
//
// 即时通讯内容
ws.onmessage = function (e) {

    var arr=e.data.split(',/,/,');
    console.log(e)
    var type=arr[0];
    if($.cookie("user")!=null&&$.cookie("user")!="null") {
        var data = JSON.parse($.cookie("user"));
    }else {
        data="";
    }
    if (type=="login") {
        var messages='{"type":"login","uid":"'+data.id+'","group":"'+$(".btn-selected").attr("data-room")+'"}';
        // console.log(messages);
        ws.send(messages);
    }
    if (type==1){
        var name=arr[1];
        var content=arr[2];
        var time = arr[3];
        var image = arr[4];
        var hallID = arr[5];
        var  acceptMembershipInformation=""
        acceptMembershipInformation += '<div class="expert-chat-item" style="display: block" id="hallUsersID_'+hallID+'">';
        acceptMembershipInformation += '                    <div class="expert-chat-left">';
        acceptMembershipInformation += '                        <img src="'+image+'"+ -live>';

        acceptMembershipInformation += '                    </div>';

        acceptMembershipInformation += '                    <div class="expert-chat-right">';
        acceptMembershipInformation += '                           <p class="expert-time"><span style="color: #666666">'+name+"</span>&nbsp&nbsp"+time+'</p>';

        // acceptMembershipInformation += '                            <p class="expert-teacher-name">'+name+'：</p>';
        // acceptMembershipInformation += '                        <div class="chat-user"><img class="imgLogo" src="'+data.data[i].grade_image+'"+ -live>'+data.data[i].expertname+'';
        // <span class="liveGrade">'+data.data[i].district+'<span>
        acceptMembershipInformation += '                        <div class="expert-chat-message">'+content+'';
        acceptMembershipInformation += '                        </div>';
        acceptMembershipInformation += '                        </div>';

        acceptMembershipInformation += '                    </div>';
        acceptMembershipInformation += '                </div>';
        $(".expert-content").append(acceptMembershipInformation);
        $('.expert-content').scrollTop(1000000000);
    }
    var info;
    if($(".btn-selected").attr("data-room")==0){
        if (type==2 || type==6) {
            info = true;
        }else {
            info =false;
        }
    }else{
        if (type==2){
            info=true;
        } else {
            info =false;
        }
    }
    //网友互动
    if (info){
        var name=arr[1];
        var content=arr[2];
        var time = arr[3];
        var image = arr[4];
        var hallID = arr[5];
        var status = arr[6];
        var grade_image = arr[8];
        var area = arr[9];
        if (status == 1) {
            var  acceptMembershipInformation=""
            acceptMembershipInformation += '<div class="chat-item" id="hallUsersID_'+hallID+'">';
            acceptMembershipInformation += '                    <div class="chat-left">';
            acceptMembershipInformation += '                        <img src="'+image+'"+ -live>';

            acceptMembershipInformation += '                    </div>';

            acceptMembershipInformation += '                    <div class="chat-right">';
            acceptMembershipInformation += '                            <p class="time">'+time+'</p>';
            acceptMembershipInformation += '                        <div class="chat-user"><img class="imgLogo" src="'+grade_image+'"+ -live>'+name+'';
            // <span class="liveGrade">'+data.data[i].district+'<span>
            acceptMembershipInformation += '                        </div>';
            acceptMembershipInformation += '                        <div class="chat-message">'+content+'';
            acceptMembershipInformation += '                        </div>';
            acceptMembershipInformation += '                    </div>';
            acceptMembershipInformation += '                </div>';
            $(".order-content").append(acceptMembershipInformation);
            $(".chat-item").fadeIn(2000);
            $('.order-content').scrollTop(1000000000)
            console.log("接收消息")
        }
    }
    //type=3:建仓
    //    下标:1：房间号，2：分析师id ,3:建仓分析师姓名,4:品种ID ,5:品种,6:合约，7：建仓类型，8：委托类型，9：操作类型，
    //    10：仓位，11：建仓点位，12：目标，13：，14：止损点位，15：建仓描述，16：建仓时间
    if(type==3){
        $("#jplayer1").jPlayer('play');
        if($(".btn-selected").attr("data-room")!=0){
            getStrategy();
            var roomID=arr[1];
            var analystsID=arr[2];
            var analystsName=arr[3];
            var varietiesID=arr[4];
            var varieties=arr[5];
            var contract=arr[6];
            if(arr[7]==1){
                var PositionType="多单建仓";
            }else{
                var PositionType="空单建仓";
            }
            if(arr[9]==1){
                var OperationType="日内操作";
            }else{
                var OperationType="趋势操作";
            }
            var positions=arr[10];
            var BuyingPoint=arr[11];
            var target=arr[12];
            var StopPoint=arr[14];
            var PositionDescription=arr[15];
            var BuyingTime=arr[16];
            layer.open({
                title:false,
                type: 1,
                shadeClose:true,
                closeBtn:1,
                // skin: 'layui-layer-rim', //加上边框
                area: ['300px', '378px'], //宽高
                content: "<div ><div class='bground'>" +

                    "<div class='jc'style='margin-top: 100px' >"+analystsName+"</div>" +
                    "<p class='jc'>品种:"+varieties+"("+contract+")</p>" +
                    "<p class='jc'>时间:"+BuyingTime+"</p>" +
                    "<div>" +
                    "<div class='jc2'><div  style='margin-left: 30px'>"+PositionType+"</div><div style='margin-left: 30px'>"+OperationType+"</div></div>" +
                    "<div style='float: right;margin-right: 50px'>" +
                    "<div class='jc'><span style='font-size:22px;margin-right: 5px;color: red '>"+BuyingPoint+"</span>附近</div>" +
                    "<div class='jc'>目标:<span style='font-size:22px;margin-right: 5px;color: red '>"+target+"</span></div>" +
                    "<div class='jc'>止损:<span style='font-size:22px;margin-right: 5px;color: red '>"+StopPoint+"</span></div>" +
                    "</div>"+
                    "</div>"+
                    // "<div class='jc'><span>"+OperationType+"</span></div>" +
                    "<div class='jc'style='margin-top: 103px'>"+PositionDescription+"</div>" +
                    "<div style='position: absolute;bottom: 3px'>" +
                    "<div style='color: red;font-size: 12px;margin: 0 6px'>特别提醒：实际的操作当中希望大家能够结合自己的操作风格和风险承受能力来选择合适的仓位。</div>" +
                    "<div style='color: red;font-size: 12px;margin: 0 6px'>风险提示：投资有风险，入市需谨慎，本建议仅供参考，不作为操作依据。据此交易，风险自担。</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>",

            });
            $(".jc2>div:contains(空单建仓)").css("background","rgb(64, 211, 64)");
        }
    }
    // type=4:平仓 下标:1:分析师ID 2：分析师名字 3：平仓点位，4：平仓类型 5：平仓描述 6：止盈（1）或者止损（2）7：平仓时间 8：品种 9：合约
    if(type==4){

        if($(".btn-selected").attr("data-room")!=0){
            getStrategy();
            var analystsID=arr[1];
            var analystsName=arr[2];
            var UnwindingPoint=arr[3];
            if(arr[4]==1){
                var OpenType="卖出平仓";
            }else if(arr[4]==2){
                var OpenType="买入平仓";
            }
            var UnwindDescription=arr[5];
            if(arr[6]==1){
                // var profit_or_loss="日内操作";
                $("#jplayer3").jPlayer('play');
            }else {
                // var profit_or_loss="趋势操作";
                $("#jplayer2").jPlayer('play');
            }
            var OpenTime=arr[7];
            var varieties=arr[8];
            layer.open({
                title:false,
                type: 1,
                shadeClose:true,
                closeBtn:1,
                // skin: 'layui-layer-rim', //加上边框
                area: ['300px', '378px'], //宽高
                content: "<div ><div class='bground'>" +

                    "<div class='jc'style='margin-top: 100px' >"+analystsName+"</div>" +
                    "<p class='jc'>品种:"+varieties+"</p>" +
                    "<p class='jc'>时间:"+OpenTime+"</p>" +
                    "<div class='jc3'>" + OpenType+"</div>"+
                    // "<div class='jc3'>" + profit_or_loss+"</div>"+
                    "<div class='jc' style='margin-top: 7px'><span style='font-size:22px;margin-right: 5px;color: red'>"+UnwindingPoint+"</span>附近</div>" +
                    // "<div class='jc'><span>"+OperationType+"</span></div>" +
                    "<div class='jc'style='margin-top: 7px'>"+UnwindDescription+"</div>" +
                    "<div style='position: absolute;bottom: 3px'>" +
                    "<div style='color: red;font-size: 12px;margin: 0 6px'>特别提醒：实际的操作当中希望大家能够结合自己的操作风格和风险承受能力来选择合适的仓位。</div>" +
                    "<div style='color: red;font-size: 12px;margin: 0 6px'>风险提示：投资有风险，入市需谨慎，本建议仅供参考，不作为操作依据。据此交易，风险自担。</div>" +
                    "</div>" +
                    "</div>" +
                    "</div>"
            });
            $(".jc3:contains(卖出平仓)").css("background","rgb(64, 211, 64)");
        }
    }
    if(type==7){
        console.log("7")
        $("[id=hallUsersID_"+arr[1]+"]").remove()
    }
    // 老师消息撤回
    if(type==8){
        $("[id=hallUsersID_"+arr[1]+"]").remove()
    }
}
//发送信息
$("#has-message~button").click(function () {
    var text=$(".text-area").val()
    if(user_information==""||user_information==null){
        layer.msg("请登入后再发言")
    }
    else if (text =="") {
        layer.msg('请输入正确内容！');
    }
    else {
        var roomId=$(".btn-selected").attr("data-room")
        var messages;
        if(roomId==0){
            messages = '{"type":"6","content":"' + text + '","group":"' +roomId+ '"}'
        }else {
            messages = '{"type":"2","content":"' + text + '","group":"' +roomId+ '"}'
        }
        ws.send(messages);
        $(".text-area").val("")
    }
})