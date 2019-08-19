$(function () {
    // $('#audio').load('music.html');
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
        }
        // index=5为布局计划
        if(index==5){
            console.log("布局计划")
            $(".report>div").hide()
            $("#Layout-plan").show()
        }
        // index=4为操盘经典
        if(index==4){
            $(".report>div").hide()
            $("#Traders-classic").show()
        }
        // index=6为投资者教育
        if(index==6){
            $(".report>div").hide()
            $("#investor-education").show()
        }
        // index=7为大数据云
        if(index==7){
            $(".report>div").hide()
            $("#datum").show()
        }
    })

})

