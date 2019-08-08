//
// var h=$(document).height();
// var w=$(document).width();

// console.log(w)
// console.log(h)
// $(".content").css("height",h)
// $(".content").css("width",w)
var tip;
// 没权限的用户点击领取
// $(function () {
//     $("#getqx").click(function () {
//         if(user_information==""||user_information==null){
//             layer.msg("请先登入");
//         }else {
//             layer.open({
//                 type: 1,
//                 shadeClose:1,
//                 title:"",
//                 skin: 'layui-layer-rim', //加上边框
//                 area: ['258px', '300px'], //宽高
//                 content: "<img src='img/erweima.png'><p style='text-align:center;font-size: 0.3rem;color: red'>扫描上方二维码，获取内部资讯！</p>"
//             });
//         }
//     })
// })

// 点击个人中心按钮
$(function () {
    $("#user").click(function () {
        if(user_information==""||user_information==null){
            layer.msg("请先登入");
        }else {
            layer.open({
                title:'',
                type: 2,
                // skin: 'layui-layer-rim', //加上边框
                area: ['357px', '600px'], //宽高
                content:'user_information.html',
                end:function () {
                    console.log("关闭")
                    login();
                }
            });
        }

    })
})
//
//登录接口
$(function () {

    $("#login").click(function () {
        window.location.href="land.html";
        console.log("登录")
    })
    $("#quit").click(function () {
        window.location.href="land.html";
        $.cookie("user",null);
    })
})
//直播大厅 观点在线 expert
$(function () {

    getRoomMessage();
    //默认获取直播大厅的信息，room=0
    $.ajax({
        url:api_url+"expert/expertuser/message",
        type:"post",
        data:{room:$(".btn-selected").attr("data-room")},
        success:function (data) {
            var acceptMembershipInformation=""
            for (var i = 0; i <data.data.length; i++) {
                acceptMembershipInformation += '<div class="expert-chat-item" style="display: block" id="hallUsersID_'+data.data[i].id+'">';
                acceptMembershipInformation += '                    <div class="expert-chat-left">';
                acceptMembershipInformation += '                        <img src="'+data.data[i].image+'"+ -live>';

                acceptMembershipInformation += '                    </div>';

                acceptMembershipInformation += '                    <div class="expert-chat-right">';
                acceptMembershipInformation += '                           <p class="expert-time"><span style="color: #666666">'+data.data[i].expertname+"</span>&nbsp&nbsp"+data.data[i].created_at+'</p>';
                // acceptMembershipInformation += '                            <p class="expert-teacher-name">'+data.data[i].expertname+'：</p>';
                // acceptMembershipInformation += '                        <div class="chat-user"><img class="imgLogo" src="'+data.data[i].grade_image+'"+ -live>'+data.data[i].expertname+'';
                // <span class="liveGrade">'+data.data[i].district+'<span>
                acceptMembershipInformation += '                        <div class="expert-chat-message">'+data.data[i].message+'';
                acceptMembershipInformation += '                        </div>';
                acceptMembershipInformation += '                        </div>';

                acceptMembershipInformation += '                    </div>';
                acceptMembershipInformation += '                </div>';
                // $(".expert-content").append($(acceptMembershipInformation));
                // $(".chat-item").fadeIn(2000);
                // acceptMembershipInformation="";
            }
            $(".expert-content").html(acceptMembershipInformation)
            $('.expert-content').scrollTop(1000000000)
        }
    })
    //默认获取网友互动的信息
    $.ajax({
        url:api_url+"ordinary/ordinary/message",
        type:"post",
        data:{room:$(".btn-selected").attr("data-room")},
        success:function (data) {
            var acceptMembershipInformation = "";
            if (data.statusCode == 0) {
                // $("#ordinaryMessageToday .obtain-data-loading").html(data.message);
            }else {
                for (var i = data.data.length - 1; i >= 0; i--) {
                    if(data.data[i].status==1){
                        acceptMembershipInformation += '<div class="chat-item" id="hallUsersID_'+data.data[i].id+'">';
                        acceptMembershipInformation += '                    <div class="chat-left">';
                        acceptMembershipInformation += '                        <img src="'+data.data[i].headimg+'"+ -live>';

                        acceptMembershipInformation += '                    </div>';

                        acceptMembershipInformation += '                    <div class="chat-right">';
                        acceptMembershipInformation += '                            <p class="time">'+data.data[i].created_at+'</p>';
                        acceptMembershipInformation += '                        <div class="chat-user"><img class="imgLogo" src="'+data.data[i].grade_image+'"+ -live>'+data.data[i].ordinaryname+'';
                        // <span class="liveGrade">'+data.data[i].district+'<span>
                        acceptMembershipInformation += '                        </div>';
                        acceptMembershipInformation += '                        <div class="chat-message">'+data.data[i].message+'';
                        acceptMembershipInformation += '                        </div>';
                        acceptMembershipInformation += '                    </div>';
                        acceptMembershipInformation += '                </div>';
                        $(".order-content").append($(acceptMembershipInformation));
                        $(".chat-item").fadeIn(2000);
                        acceptMembershipInformation="";
                    }
                }
                $('.order-content').scrollTop(1000000000)
            }
        }
    })
})

//点击按钮切换房间信息的内容
$("#live-nav>button").click(function () {
    if(user_information==""||user_information==null){
        layer.msg("您还未登入，请先登入")
        console.log($.cookie("user"))
    }
    // else if(user_information.grade==0&&$(this).attr("data-status")==0){
    //     layer.msg("账号未激活，请激活后订阅")
    // }
    else if($(this).attr("data-status")==0){
        // btn用来传递点击了第几个按钮给订阅中心
        var id=$(this).attr("data-id")
        $.cookie("btn",$(this).index())
        var open=layer.open({
            title:"",
            type: 2,
            btn:['确认订阅'],
            yes:function(){
                layer.confirm("每个会员只允许订阅一个房间，是否订阅", {
                    btn: ['确定订阅','再想想'] //按钮
                }, function(){
                    var i=$.cookie("btn");
                    $.ajax({
                        url:api_url+"expert/expertuser/user_room",
                        type: "post",
                        data:{"member_id":user_information.id,"expert_id":$("#live-nav>button:eq("+i+")").attr("data-id")},
                        success:function (result) {
                            console.log(result)
                            if(result.statusCode==1){
                                console.log("去刷新")
                                login();
                                getRoomMessage();
                            }
                            layer.msg(result.message);
                            layer.close(open);

                        }
                    })
                }, function(){
                    layer.close(open);
                });
            },
            // skin: 'layui-layer-rim', //加上边框
            area: ['570px', '390px'], //宽高
            content: 'Contact.html'
        });
        // console.log($.cookie("btn"))
        //  layer.confirm('您未订阅该房间，是否前往订阅？', {
        //     btn: ['去订阅','再想想'] //按钮
        // }, function(index){
        //     layer.close(index)
        //
        // });
    }
    // 用户订阅了该房间，获取老师，网友消息，清除之前房间的及时通讯，打开新的即时通讯，获取该房间的策略，点击下拉按钮显示所有策略
    else {
        $("#xf").css("filter","opacity(0.7)");//今日观点变透明
        tip=null;//关闭今日观点的弹窗
        layer.closeAll();
        var messages='{"type":10,"group":"'+$(".btn-selected").attr("data-room")+'"}';
        ws.send(messages);
        $("#live-nav>button").removeClass("btn-selected");
        $(this).addClass("btn-selected");
        if($(".btn-selected").attr("data-room")==0){
            $(".expert-strategy-overflow").slideUp();
            $("#xf").hide();
        }else{
            getStrategy();
            $("#xf").show();
        }
        // $.cookie("user","0")
        // console.log(user_information)
        messages='{"type":"login","uid":"'+user_information.id+'","group":"'+$(".btn-selected").attr("data-room")+'"}';
        ws.send(messages);
        $.ajax({
            url:api_url+"expert/expertuser/message",
            type:"post",
            data:{room:$(this).attr("data-room")},
            success:function (data) {
                var acceptMembershipInformation=""
                for (var i = 0; i <data.data.length; i++) {
                    acceptMembershipInformation += '<div class="expert-chat-item" style="display: block" id="hallUsersID_'+data.data[i].id+'">';
                    acceptMembershipInformation += '                    <div class="expert-chat-left">';
                    acceptMembershipInformation += '                        <img src="'+data.data[i].image+'"+ -live>';

                    acceptMembershipInformation += '                    </div>';

                    acceptMembershipInformation += '                    <div class="expert-chat-right">';
                    acceptMembershipInformation += '                            <p class="expert-time"><span style="color: #666666">'+data.data[i].expertname+"</span>&nbsp&nbsp"+data.data[i].created_at+'</p>';
                    // acceptMembershipInformation += '                            <p class="expert-teacher-name">'+data.data[i].expertname+'</p>';
                    // acceptMembershipInformation += '                        <div class="chat-user"><img class="imgLogo" src="'+data.data[i].grade_image+'"+ -live>'+data.data[i].expertname+'';
                    // <span class="liveGrade">'+data.data[i].district+'<span>
                    acceptMembershipInformation += '                        <div class="expert-chat-message">'+data.data[i].message+'';
                    acceptMembershipInformation += '                        </div>';
                    acceptMembershipInformation += '                        </div>';

                    acceptMembershipInformation += '                    </div>';
                    acceptMembershipInformation += '                </div>';
                }
                $(".expert-content").html(acceptMembershipInformation)
                $('.expert-content').scrollTop(1000000000)
            }
        })

        //网友互动
        $.ajax({
            url:api_url+"ordinary/ordinary/message",
            type:"post",
            data:{room:$(this).attr("data-room")},
            success:function (data) {
                $(".order-content").empty();
                var acceptMembershipInformation = "";
                if (data.statusCode == 0) {
                    // $("#ordinaryMessageToday .obtain-data-loading").html(data.message);
                }else {
                    for (var i = data.data.length - 1; i >= 0; i--) {
                        if(data.data[i].status==1) {
                            acceptMembershipInformation += '<div class="chat-item" id="hallUsersID_' + data.data[i].id + '">';
                            acceptMembershipInformation += '                    <div class="chat-left">';
                            acceptMembershipInformation += '                        <img src="' + data.data[i].headimg + '"+ -live>';

                            acceptMembershipInformation += '                    </div>';

                            acceptMembershipInformation += '                    <div class="chat-right">';
                            acceptMembershipInformation += '                            <p class="time">' + data.data[i].created_at + '</p>';
                            acceptMembershipInformation += '                        <div class="chat-user"><img class="imgLogo" src="' + data.data[i].grade_image + '"+ -live>' + data.data[i].ordinaryname + '';
                            // <span class="liveGrade">'+data.data[i].district+'<span>
                            acceptMembershipInformation += '                        </div>';
                            acceptMembershipInformation += '                        <div class="chat-message">' + data.data[i].message + '';
                            acceptMembershipInformation += '                        </div>';
                            acceptMembershipInformation += '                    </div>';
                            acceptMembershipInformation += '                </div>';
                            $(".order-content").append($(acceptMembershipInformation));
                            $(".chat-item").fadeIn(2000);
                            acceptMembershipInformation = "";
                        }
                    }
                    $('.order-content').scrollTop(1000000000)
                }
            }
        })
    }
    console.log($.cookie("user"))
})
// 点击悬浮窗口显示今日观点
$("#xf").click(function () {
    if(tip!=null){
        $("#xf").css("filter","opacity(0.7)");
        layer.close(tip);
        tip=null;
    }else {
        $("#xf").css("filter","opacity(1)");
        $.ajax({
            url:api_url+"product/product/product_source_list",
            type:"post",
            data:{"room":$(".btn-selected").attr("data-room")},
            success:function (result) {
                console.log(result)
                var todayInformation=""
                if(result.data.length==0){
                    todayInformation="暂无观点";
                }else {
                    for(i=0;i<result.data.length;i++){
                        todayInformation+="<div style=' border-bottom: 1px #666666 dashed'><p>"+result.data[i].product+"("+result.data[i].product+")--方向："+result.data[i].build_type+"</p><p>点位："+result.data[i].build_point+"~"+result.data[i].end_point+"——<a href="+result.data[i].source_url+">"+result.data[i].source+"</a></p></div>"
                    }
                }
                layer.closeAll();
                parent.tip= layer.tips(todayInformation, '#xf', {
                    tips: [2, 'rgb(128, 204, 255)'],
                    time: 400000,
                    shadeClose:"true"
                });
            }
        })
    }


})
// 点击下拉按钮显示其余策略
$(".xiala").click(function (even) {
    // event.stopPropagation();

    $(".strategy-message").remove();
    $(".xiala,.yincang").hide();
    $(".expert-strategy-overflow").animate({height:"90%"})
    $(".shangla").show();
    $(".shangla").css("top","95.4%")
    $(".expert-strategy-div").css("height","19%");
    $(".expert-strategy").css("overflow","scroll");
    // $(".expert-strategy-div").empty();
    $.ajax({
        url:api_url+"product/product/prposal_list",
        type:"post",
        data:{room:$(".btn-selected").attr("data-room")},
        success:function (result) {
            console.log(result)
            $(".expert-strategy-div").remove();


            for(i=0;i<result.data.length;i++){
                if(result.data[i].source==null||result.data[i].source=="null"||result.data[i].source==""){
                    result.data[i].source="暂无来源"
                }
                if(result.data[i].is_halt==1){

                    $("<div class='expert-strategy-div' style='height: 17%'><div class='exert-strategy-div-l' ><div class='expert-strategy-div-left'><img src="+result.data[i].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[i].product+"<span>"+result.data[i].contract+"</span></p><p>"+result.data[i].created_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[i].build_point+"</span>&nbsp附近</p><p><span>目标：</span>"+result.data[i].target_first+"</p><p><span>止损：</span>"+result.data[i].loss_point+"</p><p><span>策略来源：</span><a target='_blank' href="+result.data[i].source_url+">"+result.data[i].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[i].buildTypeName+"</div></div><div class='exert-strategy-div-l' style='border: none'><div class='expert-strategy-div-left'><img src="+result.data[i].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[i].product+"<span>"+result.data[i].contract+"</span></p><p>"+result.data[i].updated_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[i].halt_point+"</span>&nbsp附近</p><p><span>策略来源：</span><a target='_blank' href="+result.data[i].source_url+">"+result.data[i].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[i].haltTypeName+"</div></div></div>").appendTo($(".expert-strategy"))
                }else {
                    $("<div class='expert-strategy-div' style='height: 17%'><div class='exert-strategy-div-l' ><div class='expert-strategy-div-left'><img src="+result.data[i].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[i].product+"<span>"+result.data[i].contract+"</span></p><p>"+result.data[i].created_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[i].build_point+"</span>&nbsp附近</p><p><span>目标：</span>"+result.data[i].target_first+"</p><p><span>止损：</span>"+result.data[i].loss_point+"</p><p><span>策略来源：</span><a target='_blank' href="+result.data[i].source_url+">"+result.data[i].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[i].buildTypeName+"</div></div></div>").appendTo($(".expert-strategy"))
                }
            }
            var arr=$(".expert-strategy-div-right");
            for(var i=0;i<arr.length;i++){
                if($(".expert-strategy-div-right").eq(i).text().slice(0,2)=="买入"){
                    $(".expert-strategy-div-right").eq(i).css("background-color","#fc2a2b");
                }else {
                    $(".expert-strategy-div-right").eq(i).css("background-color","#40d340");
                }
            }
        }
    })


})
$(".shangla").click(function (even) {
    // event.stopPropagation();
    // 点击上拉时再次请求最新策略的接口
    $(".yincang").show();
    $.ajax({
        url: api_url + "product/product/home_prposal_list",
        type: "post",
        data: {room: $(".btn-selected").attr("data-room")},
        success: function (result) {
            $(".expert-strategy-div").remove();
            if(result.statusCode==0){
                $(".strategy-message").remove();
                $("<div class='strategy-message'>暂无最新策略，点击下拉按钮查看历史策略</div>").appendTo($(".expert-strategy"))
            }else {
                if(result.data[0].source==null||result.data[0].source=="null"||result.data[0].source==""){
                    result.data[0].source="暂无来源"
                }
                if(result.data[0].is_halt==1){
                    $("<div class='expert-strategy-div'><div class='exert-strategy-div-l' ><div class='expert-strategy-div-left'><img src="+result.data[0].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[0].product+"<span>"+result.data[0].contract+"</span></p><p>"+result.data[0].created_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[0].build_point+"</span>&nbsp附近</p><p><span>目标：</span>"+result.data[0].target_first+"</p><p><span>止损：</span>"+result.data[0].loss_point+"</p><p><span>策略来源：</span><a target='_blank' href="+result.data[0].source_url+">"+result.data[0].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[0].buildTypeName+"</div></div><div class='exert-strategy-div-l'style='border: none'><div class='expert-strategy-div-left'><img src="+result.data[0].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[0].product+"<span>"+result.data[0].contract+"</span></p><p>"+result.data[0].updated_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[0].halt_point+"</span>&nbsp附近</p><p><span>策略来源：</span><a target='_blank' href="+result.data[0].source_url+">"+result.data[0].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[0].haltTypeName+"</div></div></div>").appendTo($(".expert-strategy"))
                }else {
                    $("<div class='expert-strategy-div'><div class='exert-strategy-div-l' ><div class='expert-strategy-div-left'><img src="+result.data[0].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[0].product+"<span>"+result.data[0].contract+"</span></p><p>"+result.data[0].created_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[0].build_point+"</span>&nbsp附近</p><p><span>目标：</span>"+result.data[0].target_first+"</p><p><span>止损：</span>"+result.data[0].loss_point+"</p><p><span>策略来源：</span><a target='_blank' href="+result.data[0].source_url+">"+result.data[0].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[0].buildTypeName+"</div></div></div>").appendTo($(".expert-strategy"))
                }
            }
            var arr=$(".expert-strategy-div-right");
            for(var i=0;i<arr.length;i++){
                if($(".expert-strategy-div-right").eq(i).text().slice(0,2)=="买入"){
                    $(".expert-strategy-div-right").eq(i).css("background-color","#fc2a2b");
                }else {
                    $(".expert-strategy-div-right").eq(i).css("background-color","#40d340");
                }
            }
        }
    })
    $(".shangla").hide();
    $(".xiala").show();
    $(".xiala").css("top","79%")
    $(".expert-strategy-overflow").animate({height:"19%"})

    $(".expert-strategy-div").css("height","75%");
})
$(".yincang").click(function () {
    $(".expert-strategy-overflow").css("height","3%");
    $(".xiala,.yincang").hide();
    $(".huifu").show();
})
$(".huifu").click(function () {
    $(".huifu").hide();
    $(".yincang,.xiala").show();
    $(".expert-strategy-overflow").css("height","19%");
})
// 点击图片弹出
$(document).ready(function(){
    $(".expert-content").on("dblclick",".expert-chat-message","img",function(e){ //.chat-message直播信息的父级
        var initialization =e.currentTarget.innerHTML;
        var alert_blank = $(initialization).children("img");                // children 返回所有子节点  P标签下就一个img 直接取到img的src
        console.log(alert_blank[0].currentSrc);
        if (alert_blank[0].currentSrc != "") {                              // 这里有有一个坑 图片未加载 弹出的会是一个空  so + 一个判断
            window.open(alert_blank[0].currentSrc, '_blank');
        }
    });
});
