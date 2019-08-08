$(function () {
    $('#audio').load('music.html');
    //点击按钮加载模块
    $(".nav-btn").click(function () {
        var index=$(this).index();
        $(".nav-btn").css("color","#666666")
        $(this).css("color","white");
        console.log(index)
        if(index==0){
            $(".report>div").hide()
            $("#audio").show()
        }
        // index=1本来是加载刊物精选内容，后改成机构研报
        if(index==1){
            $(".report>div").hide();
            $("#research-report").show();
            $.ajax({
                url:api_url+"ordinary/ordinary/information_arcite",
                type:"post",
                data:{"member_id":user_information.id},
                success:function (result) {
                    var researchReport=result.data.viewpoint;
                    var news="<div class='report-title'>机构研报</div>"
                    for(var i=0;i<researchReport.length;i++){
                        var date=researchReport[i].created_at.slice(5,10);
                        news +="<div style='box-shadow: 9px 16px 44px #e3e5f1;height: 5%'>" +
                            "<div class='researchReport-news'data-id="+researchReport[i].id+" data-updated_name="+researchReport[i].updated_name+"  data-total="+researchReport[i].total+" data-type="+researchReport[i].type+"  data-status="+researchReport[i].status+">" +
                            "<div class='display-content' style='display: none'>"+researchReport[i].content+"</div>" +
                            "<p >"+researchReport[i].title+"</p></div>" +
                            "<span class='researchReport-news-date'>"+date+"</span>"+
                            "</div>"
                    }
                    $("#research-report").html(news)
                    $(".researchReport-news").click(function () {
                        var content=$(this).find("div").html()
                        if(user_information==""||user_information==null){
                            layer.msg("请先登入")
                        }else if(user_information.grade==0){
                            layer.msg("抱歉，该内容只有会员才能查看")}
                        else if($(this).attr("data-status")==0){
                            layer.open({
                                type: 1,
                                title: "标题", //不显示标题栏
                                closeBtn: false,
                                area: ['44%', '60%'],
                                shade: 0.8,
                                resize :true,
                                id: 'LAY_layuipro', //设定一个id，防止重复弹出
                                btn: ['关闭当前窗口'],
                                btnAlign: 'c',
                                anim: 4,
                                moveType: 1, //拖拽模式，0或者1
                                content:"<div style='width: 100%;height: 100%;overflow: auto'>"+content+"</div>" ,
                                success: function (layero) {
                                }
                            })
                        }
                        else if($(this).attr("data-status")==1){
                            window.open(content,"_target");
                        }
                    })
                }
            })
            // $.ajax({
            //     url:api_url+"rests/common/journal_list",
            //     type:"post",
            //     success:function (result) {
            //         console.log(result)
            //         var morning=result.journal_four;
            //         var evening=result.journal_five;
            //         var day=result.journal_one;
            //         var week=result.journal_two;
            //         var special=result.journal_three;
            //         function addNews(date,img,x) {
            //             for(var i=0;i<date.length;i++){
            //                 $("<div data-content="+date[i].url+"><img src="+img+"><p>"+date[i].title+"</p></div>").appendTo($(".publication-news:eq("+x+")"))
            //             }
            //         }
            //         addNews(evening,'./img/news/wan.jpg',1);
            //         addNews(morning,'./img/news/zao.jpg',0);
            //         addNews(day,'./img/news/type.jpg',2);
            //         addNews(week,'./img/news/zhou.jpg',3);
            //         addNews(special,'./img/news/te.jpg',4);
            //         $(".publication-news").find("div").click(function () {
            //             console.log($(this).attr("data-content"));
            //             window.open($(this).attr("data-content"),"_target");
            //         })
            //         $(".publication-btn").click(function () {
            //             var index=$(this).index();
            //             $(".publication-btn").removeClass("publication-btn-color");
            //             $(this).addClass("publication-btn-color");
            //             $(".publication-news").hide();
            //             $(".publication-news").eq(index).show();
            //         })
            //         // for(var i=0;i<=result.)
            //         // $("<div></div>")
            //     }
            // })
        }
        if(index==2){
            $(".report>div").hide()
            $("#mobile-app").show()
        }
        // index=3为尖儿货
        if(index==3){
            console.log("尖儿货")
            $(".report>div").hide()
            $("#Top-cargo").show()
            $.ajax({
                url:api_url+"rests/common/arcite_type_six",
                type: "post",
                data:{member_id:user_information.id},
                success:function (result) {
                    // console.log(result)
                    // $("#Top-cargo-content").empty();
                    // for(var i=0;i<result.length;i++){
                    //     $("<div data-status="+result[i].status+"  data-id="+result[i].id+" data-total="+result[i].total+" data-content="+result[i].content+"><img src='./img/jianhuo.jpg'><p style='font-size: 12px'>"+result[i].title+"</p><p>上传者:"+result[i].updated_name+"</p><p>上传时间:"+result[i].created_at+"</p></div>").appendTo($("#Top-cargo-content"))
                    // }
                    var news="<div class='report-title'>状元视角</div>";
                    for(var i=0;i<result.length;i++){
                        var date=result[i].created_at.slice(5,10);
                        news +="<div style='box-shadow: 9px 16px 44px #e3e5f1;height: 5%'>" +
                            "<div class='researchReport-news'data-id="+result[i].id+" data-updated_name="+result[i].updated_name+"  data-total="+result[i].total+" data-type="+result[i].type+"  data-status="+result[i].status+">" +
                            "<div class='display-content' style='display: none'>"+result[i].content+"</div>" +
                            "<p >"+result[i].title+"</p></div>" +
                            "<span class='researchReport-news-date'>"+date+"</span>"+
                            "</div>"
                    }
                    $("#Top-cargo-content").html(news)
                    $("#Top-cargo-content>div").click(function () {
                        var id=$(this).attr("data-id")

                        if(user_information==""||user_information==null){
                            layer.msg("请先登入")
                        }else if(user_information.grade==0){
                            layer.msg("抱歉，该内容只有会员才能查看")}
                        else if(user_information.grade==1){
                            layer.msg("抱歉,该内容只有白银会员以上才可以查看")
                        }
                        else {
                            // if($(this).attr("data-status")==0){
                            //     layer.confirm("该资讯需花费"+$(this).attr("data-total")+"状元豆，购买后才可以观看哦!", {
                            //         // title:"已选中:"+$(this+">.skPlayer-list-name").text(),
                            //         btn: ['确定购买'] //按钮
                            //     }, function(){
                            //         $.ajax({
                            //             url:api_url+"rests/common/arcite_buy",
                            //             type:"post",
                            //             data:{"member_id":user_information.id,"arcite_id":id},
                            //             success:function (result) {
                            //                 console.log(result)
                            //                 layer.msg(result.message)
                            //                 if(result.statusCode==1){
                            //                     // $('.nav-btn').eq(0).trigger("click");
                            //                     $(".nav-btn").eq(3).trigger("click");
                            //                     login();
                            //                 }
                            //             }
                            //         })
                            //     });
                            // }else{
                            var data=$(this).find(".display-content").html()
                            // var data=$(this).attr('data-content')
                            // console.log(data.search('https'))
                            if(data.search('https')!=-1){
                                window.open(data,"_target");
                            }else {
                                var arr=data.split(",");
                                var content="";
                                for(i=0;i<arr.length;i++){
                                    content+="<iframe height='100%'width='99%'src="+arr[i]+"></iframe>";
                                }
                                layer.open({
                                    type: 1,
                                    title: '付费内容：操盘内容可以最小化边听边看直播哦！', //不显示标题栏
                                    closeBtn: false,
                                    area: ['33%', '50%'],
                                    maxmin: true,
                                    resize:false,
                                    scrollbar: false,
                                    tipsMore: false,
                                    shade:0.2,
                                    id: 'LAY_layuipro_video', //设定一个id，防止重复弹出
                                    btn: ['关闭当前窗口'],
                                    btnAlign: 'c',
                                    anim: 4,
                                    moveType: 1, //拖拽模式，0或者1
                                    content: "<div style='overflow: auto;height: 100%'>"+content+"</div>",
                                    success: function (layero) {

                                    }
                                });
                            }

                            // }

                        }
                    })
                }
            })

        }
        // index=5为布局计划
        if(index==5){
            console.log("布局计划")
            $(".report>div").hide()
            $("#Layout-plan").show()
            $.ajax({
                url:api_url+"ordinary/ordinary/information_arcite",
                type: "post",
                data:{member_id:user_information.id},
                success:function (result) {
                    var result=result.data.overall;
                    $("#Layout-plan").empty();
                    for(var i=0;i<result.length;i++){
                        $("<div data-status="+result[i].status+"  data-id="+result[i].id+" data-total="+result[i].total+" ><span class='Layout-plan-display-content' style='display: none'>"+result[i].content+"</span><img style='width: 100%;height: 100%' src='./img/zhuan.jpg'><p style='font-size: 12px'>"+result[i].title+"</p><p>上传者:"+result[i].updated_name+"</p><p>上传时间:"+result[i].created_at+"</p></div>").appendTo($("#Layout-plan"))
                    }
                    $("#Layout-plan>div").click(function () {
                        var id=$(this).attr("data-id")
                        if(user_information==""||user_information==null){
                            layer.msg("请先登入")
                        }else if(user_information.grade<3){
                            layer.msg("抱歉，该内容需要黄金会员等级以上才可以查看")
                        }
                        else {
                            // if($(this).attr("data-status")==0){
                            //     layer.confirm("该资讯需花费"+$(this).attr("data-total")+"状元豆，购买后才可以观看哦!", {
                            //         // title:"已选中:"+$(this+">.skPlayer-list-name").text(),
                            //         btn: ['确定购买'] //按钮
                            //     }, function(){
                            //         $.ajax({
                            //             url:api_url+"rests/common/arcite_buy",
                            //             type:"post",
                            //             data:{"member_id":user_information.id,"arcite_id":id},
                            //             success:function (result) {
                            //                 layer.msg(result.message)
                            //                 if(result.statusCode==1){
                            //                     $(".nav-btn").eq(5).trigger("click");
                            //                     login();
                            //                 }
                            //             }
                            //         })
                            //     });
                            // }else{
                            var content=$(this).find("span").html()
                            layer.open({
                                type: 1,
                                title: "布局计划", //不显示标题栏
                                closeBtn: false,
                                area: ['44%', '60%'],
                                shade: 0.8,
                                resize :true,
                                id: 'LAY_layuipro', //设定一个id，防止重复弹出
                                btn: ['关闭当前窗口'],
                                btnAlign: 'c',
                                anim: 4,
                                moveType: 1, //拖拽模式，0或者1
                                content:"<div style='width: 96%;height: 100%;overflow: auto;padding-left: 4%'>"+content+"</div>" ,
                                success: function (layero) {

                                }
                            });
                            // }
                        }
                    })
                }
            })
        }
        // index=4为操盘经典
        if(index==4){
            $(".report>div").hide()
            $("#Traders-classic").show()
            $.ajax({
                url:api_url+"ordinary/ordinary/information_arcite",
                type:"post",
                data:{"member_id":user_information.id},
                success:function (result) {
                    var camp=result.data.camp;
                    var news="<div class='report-title'>操盘经典</div>"
                    for(var i=0;i<camp.length;i++){
                        var date=camp[i].created_at.slice(5,10);
                        news +="<div  style='height: 5%;box-shadow: 9px 16px 44px #e3e5f1'>" +
                            "<div class='report-news'data-id="+camp[i].id+" data-updated_name="+camp[i].updated_name+"  data-total="+camp[i].total+" data-type="+camp[i].type+"  data-status="+camp[i].status+">" +
                            "<div class='display-content' style='display: none'>"+camp[i].content+"</div>" +
                            "<p style='    text-overflow: ellipsis;\n" +
                            "    white-space: nowrap;\n" +
                            "    overflow: hidden;'>"+camp[i].title+"</p></div>" +
                            "<span class='report-news-date'>"+date+"</span>" +
                            "</div>"
                    }
                    $("#Traders-classic").html(news)
                    $(".report-news").click(function () {
                        var content=$(this).find("div").html()
                        if(user_information==""||user_information==null){
                            layer.msg("请先登入")
                        }else if(user_information.grade==0){
                            layer.msg("抱歉，该内容只有会员才能查看")}
                        else if(user_information.grade==1){
                            layer.msg("抱歉,该内容只有白银会员以上才可以查看")
                        }
                        else if($(this).attr("data-status")==1){
                            layer.open({
                                type: 1,
                                title: "标题", //不显示标题栏
                                closeBtn: false,
                                area: ['44%', '60%'],
                                shade: 0.8,
                                resize :true,
                                id: 'LAY_layuipro', //设定一个id，防止重复弹出
                                btn: ['关闭当前窗口'],
                                btnAlign: 'c',
                                anim: 4,
                                moveType: 1, //拖拽模式，0或者1
                                content:"<div style='width: 100%;height: 100%;overflow: auto'>"+content+"</div>" ,
                                success: function (layero) {

                                }
                            })
                        }
                        else if($(this).attr("data-status")==2){
                            window.open(content,"_target");
                        }
                        // else {
                        //     var arciteId=$(this).attr("data-id")
                        //     layer.confirm('您未订阅分析师'+$(this).attr("data-updated_name")+",该文章需花费"+$(this).attr("data-total")+"状元积分才可以观看!", {
                        //         title:"已选中:"+$(this).text(),
                        //         btn: ['确定购买'] //按钮
                        //     }, function(){
                        //         // time: 3000;
                        //         $.ajax({
                        //             url:api_url+"rests/common/arcite_buy",
                        //             type:"post",
                        //             data:{"member_id":user_information.id,"arcite_id":arciteId},
                        //             success:function (result) {
                        //                 console.log(result)
                        //                 layer.msg(result.message)
                        //                 if(result.statusCode==1){
                        //                     login();
                        //                     $(".report>div").eq(4).trigger("click");
                        //                 }
                        //             }
                        //         })
                        //
                        //     });
                        // }
                    })
                }
            })
        }
        // index=6为投资者教育
        if(index==6){
            $(".report>div").hide()
            $("#investor-education").show()
            $(".investor-btns").click(function () {
                console.log("投资者教育")
                var index=$(this).index();
                $(".investor-btns").removeClass("selected-btn-color");
                $(this).addClass("selected-btn-color");
                $(".investor-news").hide();
                $(".investor-news").eq(index).show();
            })
        }
        // index=7为大数据云
        if(index==7){
            $(".report>div").hide()
            $("#datum").show()
            $.ajax({
                url:api_url+"ordinary/ordinary/information_arcite",
                type:"post",
                data:{"member_id":user_information.id},
                success:function (result) {
                    var researchReport=result.data.datum;
                    var news="<div class='report-title'>大数据云</div>"
                    for(var i=0;i<researchReport.length;i++){
                        var date=researchReport[i].created_at.slice(5,10);
                        news +="<div style='line-height: 26px;height: 5%;box-shadow: 9px 16px 44px #e3e5f1'>" +
                            "<div class='researchReport-news'data-id="+researchReport[i].id+" data-updated_name="+researchReport[i].updated_name+"  data-total="+researchReport[i].total+" data-type="+researchReport[i].type+"  data-status="+researchReport[i].status+">" +
                            "<div class='display-content' style='display: none'>"+researchReport[i].content+"</div>" +
                            "<p >"+researchReport[i].title+"</p></div>" +
                            "<span class='researchReport-news-date'>"+date+"</span>"+
                            "</div>"
                    }
                    $("#datum").html(news)
                    $(".researchReport-news").click(function () {
                        var content=$(this).find("div").html()
                        if(user_information==""||user_information==null){
                            layer.msg("请先登入")
                        }else if(user_information.grade==0){
                            layer.msg("抱歉，该内容只有会员才能查看")}
                        else if($(this).attr("data-status")==0){
                            layer.open({
                                type: 1,
                                title: "标题", //不显示标题栏
                                closeBtn: false,
                                area: ['44%', '60%'],
                                shade: 0.8,
                                resize :true,
                                id: 'LAY_layuipro', //设定一个id，防止重复弹出
                                btn: ['关闭当前窗口'],
                                btnAlign: 'c',
                                anim: 4,
                                moveType: 1, //拖拽模式，0或者1
                                content:"<div style='width: 100%;height: 100%;overflow: auto'>"+content+"</div>" ,
                                success: function (layero) {
                                }
                            })
                        }
                        else if($(this).attr("data-status")==1){
                            window.open(content,"_target");
                        }
                    })
                }
            })
        }
    })

})

