$(function () {
    //点击数据一览，直播观点切换

    $(".nav5").click(function () {
        $(".expert-message").hide()
        $(".data-view").show()
        $(this).css("color","white")
        $(".nav3").css("color","#666666")
        if($.cookie("user")!=null&&$.cookie("user")!="null"){
            var data = JSON.parse($.cookie("user"));}else {
            var data=""
        }
        // 加载目睹网页
        // changeMudu(false);
        var timer=setInterval(changeMudu(true),3600000);
        // 轮播图
        var imgURL=["http://mudu.tv/watch/3015736","http://mudu.tv/watch/3155170","http://mudu.tv/watch/2894288"]
        layui.use(['carousel', 'layer'], function () {
            var $ = layui.jquery;
            var carousel = layui.carousel;

            //建造实例
            var ins = carousel.render({
                elem: '.news',
                width: '100%' //设置容器宽度
                ,
                height: '30%',
                arrow: 'always' //始终显示箭头
                ,
                autoplay: true,
                interval: 10000
                //,anim: 'updown' //切换动画方式
                ,
                beforeSlide: function (elem, index) {

                }
            });
            // 移出鼠标移入就暂停切换的功能
            $('.news').unbind('mouseenter').unbind('mouseleave');

            $.post(api_url + "homeimage/homeimage/get_image", {
                // data:
            }, function (data) {
                var bannerImages = "";
                var bannerList = "";

                for (var i = 0; i < data.data.length; i++) {
                    // 异步处理banner图
                    var asynchronous_images = new Image();
                    asynchronous_images.onload = function () {
                        // alert(asynchronous_images);
                    };
                    asynchronous_images = data.data[i].image_url;


                    bannerImages += '<div><a target="_blank" href='+imgURL[i]+'><img width="100%" height="100%" src="' + asynchronous_images + '"></a></div>';
                }
                $("#bannerAppend").html(bannerImages);
                //重置轮播
                ins.reload();
            });

        });
        //获取热点聚焦，大数据云，操盘经典的内容
        // $.ajax({
        //     url:api_url+"ordinary/ordinary/information_arcite",
        //     type:"post",
        //     data:{"member_id":data.id},
        //     success:function (result) {
        //         console.log(result)
        //         // 热点聚焦改机构研报
        //         var viewPoint=result.data.viewpoint;
        //         // 大数据云
        //         var datum=result.data.datum;
        //         // 操盘经典
        //         var camp=result.data.camp;
        //         // 视频直播
        //         var mudu=result.data.mudu;
        //         var news=""
        //         function newsAppend(newsName,className) {
        //             var news=""
        //             for(var i=0;i<newsName.length;i++){
        //                 var date=newsName[i].created_at.slice(5,10);
        //                 news +="<div  style='height: 10%'>" +
        //                     "<div class='news-data-left'data-id="+newsName[i].id+" data-updated_name="+newsName[i].updated_name+"  data-total="+newsName[i].total+" data-type="+newsName[i].type+"  data-status="+newsName[i].status+">" +
        //                     "<div class='display-content' style='display: none'>"+newsName[i].content+"</div>" +
        //                     "<p >"+newsName[i].title+"</p></div>" +
        //                     "<div class='news-data-right'><p>"+date+"</p></div>" +
        //                     "</div>"
        //             }
        //             $("#"+className+">div").html(news)
        //         }
        //         newsAppend(viewPoint,"viewPoint")
        //         newsAppend(datum,"datum")
        //         newsAppend(mudu,"camp")
        //         //点击打开不同的news
        //         $(".news-data-left").click(function () {
        //             console.log(data)
        //             // 获取显示的内容content
        //             var content=$(this).find("div").html()
        //             // 热点聚焦和大数据云的type=1/2
        //
        //             // type=1 2为热点聚焦(机构研报).大数据云，不用购买，status=0为文本，status=1为链接
        //             if($(this).attr("data-type")==1||$(this).attr("data-type")==2){
        //                 if(user_information==""||user_information==null){
        //                     layer.msg("请先登入")
        //                 }else if(user_information.grade<1){
        //                     layer.msg("抱歉，该内容只有会员才能查看")
        //                 }
        //                 else if($(this).attr("data-status")==0){
        //                     layer.open({
        //                         type: 1,
        //                         title: "标题", //不显示标题栏
        //                         closeBtn: false,
        //                         area: ['44%', '60%'],
        //                         shade: 0.8,
        //                         resize :true,
        //                         id: 'LAY_layuipro', //设定一个id，防止重复弹出
        //                         btn: ['关闭当前窗口'],
        //                         btnAlign: 'c',
        //                         anim: 4,
        //                         moveType: 1, //拖拽模式，0或者1
        //                         content:"<div style='width: 100%;height: 100%;overflow: auto'>"+content+"</div>" ,
        //                         success: function (layero) {
        //
        //                         }
        //                     })
        //                 }
        //                 else if($(this).attr("data-status")==1){
        //                     window.open(content,"_target");
        //                 }
        //             }
        //             // type=3为操盘经典，status=1为文本，status=2为链接，status=0为未购买或者未订阅
        //             else if($(this).attr("data-type")==3){
        //                 if(user_information.grade<1||user_information.grade==null){
        //                     layer.msg("请激活后查看")
        //                 }
        //                 else if($(this).attr("data-status")==1){
        //                     layer.open({
        //                         type: 1,
        //                         title: "标题", //不显示标题栏
        //                         closeBtn: false,
        //                         area: ['44%', '60%'],
        //                         shade: 0.8,
        //                         resize :true,
        //                         id: 'LAY_layuipro', //设定一个id，防止重复弹出
        //                         btn: ['关闭当前窗口'],
        //                         btnAlign: 'c',
        //                         anim: 4,
        //                         moveType: 1, //拖拽模式，0或者1
        //                         content:"<div style='width: 100%;height: 100%;overflow: auto'>"+content+"</div>" ,
        //                         success: function (layero) {
        //
        //                         }
        //                     })
        //                 }
        //                 else if($(this).attr("data-status")==2){
        //                     window.open(content,"_target");
        //                 }
        //                 else {
        //                     var arciteId=$(this).attr("data-id")
        //                     layer.confirm('您未订阅分析师'+$(this).attr("data-updated_name")+",该文章需花费"+$(this).attr("data-total")+"风口积分才可以观看!", {
        //                         title:"已选中:"+$(this).text(),
        //                         btn: ['确定购买'] //按钮
        //                     }, function(){
        //                         // time: 3000;
        //                        $.ajax({
        //                            url:api_url+"rests/common/arcite_buy",
        //                            type:"post",
        //                            data:{"member_id":data.id,"arcite_id":arciteId},
        //                            success:function (result) {
        //                                console.log(result)
        //                                layer.msg(result.message)
        //                                if(result.statusCode==1){
        //                                    console.log("去刷新")
        //                                    login();
        //                                    $('.nav5').trigger("click");
        //
        //
        //                                }
        //                            }
        //                        })
        //
        //                     });
        //                 }
        //             }
        //             // type==4为视频直播
        //             else {
        //                 window.open(content,"_target");
        //             }
        //         })
        //     }
        // })

    })
    // 点击热点聚焦，大数据云，操盘经典：
    // 操盘经典：status 1（文本）和2（链接）:已经购买0（未购买或者未订阅）
    // 大数据云：不用购买 status 0（文本）1（链接）
    // 热点聚焦：不用购买 status 0（文本）1（链接）
    // 布局计划：纯文本 0(未购买）1（购买）


    $(".nav3").click(function () {
        $(".expert-message").show()
        $(".data-view").hide()
        $(this).css("color","white")
        $(".nav5").css("color","#666666")

    })
    // 00:01-12:00 今日聚焦  12:01-18:00 过把瘾儿  18:01-00:00量化交易

    function changeMudu(flag) {
        var now_date = new Date();
        var now_time =now_date.getHours();
        // if(flag==true||$(".iframe").attr("src")==""){
        if(now_time>0&&now_time<12){
            $(".iframe").attr("src","http://mudu.tv/watch/3155170")
        }else if(now_time>=12&&now_time<18){
            $(".iframe").attr("src","http://mudu.tv/watch/2894288")
        }else {
            $(".iframe").attr("src","http://mudu.tv/watch/3015736")
        }
        // }
    };


})
