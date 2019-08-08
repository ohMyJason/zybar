$(function () {
    login();
})
if($.cookie("user")!=null&&$.cookie("user")!="null"){
    var user_information = JSON.parse($.cookie("user"));}else {
    var user_information=""
}
if($.cookie("color")!=null){
    $(".data-body-nav-content").css("color",$.cookie("color"))
    $(".content-top-title,#user,.nav-btn,.nav,#suspension button,#has-message,#live-nav button,.clbg").css("background-color",$.cookie("color"))
}
var api_url = "http://ws.find37.com/index.php?s=";//线上接口
// var api_url = "http://192.168.2.243/lily/public/index.php/";//本地测试接口

//获取房间信息(房间名字，房间订阅状态.....)
function getRoomMessage(){
    $.ajax({
        url:api_url+"expert/expertuser/expert_user",
        type:"post",
        data:{id:user_information.id},
        success:function (data) {
            for(var i=0;i<data.data.length;i++){
                var j=data.data.length-i;
                $("#live-nav>button:eq("+j+")").text(data.data[i].nickname).attr("data-room",data.data[i].room).attr("data-status",data.data[i].status).attr("data-id",data.data[i].id).attr("data-total",data.data[i].total)
                // if(data.data[i].status==0){
                //     $("#live-nav>button:eq("+j+")").css("background-color","#f5eded");
                // }
            }
        }
    })
}
// 获取策略的函数
function getStrategy(){
    $.ajax({
        url:api_url+"product/product/home_prposal_list",
        type:"post",
        data:{room:$(".btn-selected").attr("data-room")},
        success:function (result) {
            $(".expert-strategy-div").remove();
            $(".strategy-message").remove()
            $(".expert-strategy-overflow").fadeIn();
            if(result.statusCode==0){
                $(".strategy-message").remove();
                $("<div class='strategy-message'>暂无最新策略，点击下拉按钮查看历史策略</div>").appendTo($(".expert-strategy"))
            }else {
                $(".expert-strategy-overflow").animate({height:"19%"})
                $(".shangla,.huifu").hide();
                $(".xiala,.yincang").show();
                if(result.data[0].source==null||result.data[0].source=="null"||result.data[0].source==""){
                    result.data[0].source="暂无来源"
                }

                // $(".expert-strategy").attr("data-url",result.data[0].source_url);
                if(result.data[0].is_halt==1){
                    $("<div class='expert-strategy-div'><div class='exert-strategy-div-l' ><div class='expert-strategy-div-left'><img src="+result.data[0].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[0].product+"<span>"+result.data[0].contract+"</span></p><p>"+result.data[0].created_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[0].build_point+"</span>&nbsp附近</p><p><span>目标：</span>"+result.data[0].target_first+"</p><p><span>止损：</span>"+result.data[0].loss_point+"</p><p><span>策略来源：</span><a target='_blank' href="+result.data[0].source_url+">"+result.data[0].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[0].buildTypeName+"</div></div><div class='exert-strategy-div-l'style='border: none'><div class='expert-strategy-div-left'><img src="+result.data[0].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[0].product+"<span>"+result.data[0].contract+"</span></p><p>"+result.data[0].updated_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[0].halt_point+"</span>&nbsp附近</p><p><span>策略来源：</span><a target='_blank' href="+result.data[0].source_url+">"+result.data[0].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[0].haltTypeName+"</div></div></div>").appendTo($(".expert-strategy"))
                }else {
                    $("<div class='expert-strategy-div'><div class='exert-strategy-div-l' ><div class='expert-strategy-div-left'><img src="+result.data[0].build_portrait+"></div><div class='expert-strategy-div-center'><p>"+result.data[0].product+"<span>"+result.data[0].contract+"</span></p><p>"+result.data[0].created_at+"</p><p><span>点位：</span><span  style='font-size: 16px;color: red'>"+result.data[0].build_point+"</span>&nbsp附近</p><p><span>目标：</span>"+result.data[0].target_first+"</p><p><span>止损：</span>"+result.data[0].loss_point+"</p><p><span>策略来源：</span><a target='_blank' href="+result.data[0].source_url+">"+result.data[0].source+"</a></p></div><div class='expert-strategy-div-right'>"+result.data[0].buildTypeName+"</div></div></div>").appendTo($(".expert-strategy"))

                }
                // 如果is_half=1，表示有平仓消息，要显示出来

                // 没有策略来源则取消a标签的默认跳转行为

            }

            $(".expert-strategy a").click(function () {
                if($(this).attr("href")=="null"){
                    return false;
                }
            })
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
}
// 刷新信息的函数
function login() {
    if (window.ActiveXObject || "ActiveXObject" in window)
    {
        $(".expert-content-white,.order-content-white,.suspension-white").show()
        $(".expert-content-white,.order-content-white").css("z-index","100");
    }

    $.ajax({
        url:api_url+"ordinary/ordinary/ordinarystatus",
        type:"post",
        data:{id:user_information.id},
        success:function (data) {
            console.log(data)
            user_information.grade=data.data.grade;
            user_information.headimg=data.data.headimg;
            user_information.nickname=data.data.nickname;
            user_information.total=data.data.total;
            user_information.user_type=data.data.user_type;
            if(data.statusCode==1){
                $.cookie("user",JSON.stringify(user_information),{ expires: 30 })
                console.log(user_information)
                $("#login").hide();
                $("#quit").show();
                // $("#user-information li:eq(0)").find("p").text(data.data.nickname)
                $("#user-information li:eq(0)").text(data.data.nickname)
                // if(data.data.over_time!=""){
                //     $("#user-information li:eq(1)").text(data.data.over_time+"到期").css("margin-left","-40%");
                // }else {
                $("#user-information li:eq(1)").text("等级:"+data.data.grade)
                // }

                // $("#user-information li:eq(2)").text("等级:"+data.data.grade)
                // $("<span>积分:"+data.data.total+"</span>").prependTo($("#user"))
                // $("<img width='50%'height='50%' src="+data.data.headimg+">").appendTo($("#user"))
                // $("<span>v"+data.data.grade+"</span>").prependTo($("#user"))
            }
            else if(data.statusCode==2){
                $(".expert-content-white,.order-content-white,#getqx").show()//未登入状态
            }else if(data.statusCode==0){
                layer.confirm(data.message, {
                    btn: ['确定',] //按钮
                }, function(){
                    window.location.href="land.html"
                });
            }
            else {
                layer.confirm(data.message, {
                    btn: ['确定',] //按钮
                }, function(){
                    layer.closeAll();
                });

            }

// 判断模糊效果
//等级为0或者未登入状态，设置白底显示
            if(user_information.grade==0||user_information==""||user_information==null){
                $(".text-area").attr("disabled","disabled");//禁止发言
                $(".expert-content-white,.order-content-white,#getqx").show()
                $(".text-area").attr("disabled","disabled");
            }
// 如果等级大于0，直播窗口可见
            else if(user_information.grade>0){
                $(".expert-content-white,.order-content-white,#getqx").hide()
                $(".expert-content,.order-content,.expert-strategy-overflow").css("filter","blur(0px)");
            }
            if(user_information.grade>1){
                $("#suspension-mohu").css("filter","blur(0px)");
                $(".suspension-white").hide();
            }
        }
    })
}