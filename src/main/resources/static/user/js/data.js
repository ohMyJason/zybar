$(function () {
    //封装点击按钮获取数据的函数
    function btn(name){
        $.ajax({
            url: "http://47.111.162.239/test2.php",
            TYPE:"GET",
            dataType:"json",
            data:{product:name},
            success:function (data) {
                $("#tb-contain").empty();
                if(data!=null){
                    for(var i=data.length-1;i>=0;i--){
                        // $("tbody").html("<tr><td>"+data[i].time.substring(5,16)+"</td><td>"+data[i].product+"</td><td>"+data[i].direction+"</td><td>"+data[i].price+"</td><td>"+data[i].type+"</td>\</tr>")
                        $("<tr><td>"+data[i].update_date+"</td><td>"+data[i].product+"</td><td>"+data[i].direction+"</td><td>"+data[i].price+"</td>\</tr>").appendTo($("#tb-contain"))
                    }
                }

            }
        })
    }
    //初始获取焦炭的数据
    $.ajax({
        url: "http://47.111.162.239/test2.php",
        TYPE:"GET",
        dataType:"json",
        data:{product:"焦炭"},
        success:function (result) {

            if(result!=null){
                var i;
                for(i=result.length-1;i>=0;i--){
                    // data=eval(data)
                    // $("tbody").html("<tr><td>"+data[0].time.substring(5,16)+"</td><td>"+data[0].product+"</td><td>"+data[0].direction+"</td><td>"+data[0].price+"</td><td>"+data[0].type+"</td>\</tr>")
                    // obj +="<tr><td>"+data[i].time.substring(5,16)+"</td><td>"+data[i].product+"</td><td>"+data[i].direction+"</td><td>"+data[i].price+"</td><td>"+data[i].type+"</td>\</tr>"
                    $("<tr><td>"+result[i].update_date+"</td><td>"+result[i].product+"</td><td>"+result[i].direction+"</td><td>"+result[i].price+"</td>\</tr>").appendTo($("#tb-contain"))
                }
            }

        }
    })
    //什么按钮都没点时自动刷新焦炭信息
    var timer;
    timer=setInterval(function () {
        $.ajax({
            url: "http://47.111.162.239/test2.php",
            TYPE:"GET",
            dataType:"json",
            data:{product:"焦炭"},
            success:function (data) {
                $("#tb-contain").empty();
                if(data!=null){
                    for(var i=data.length-1;i>=0;i--){
                        // $("tbody").html("<tr><td>"+data[i].time.substring(5,16)+"</td><td>"+data[i].product+"</td><td>"+data[i].direction+"</td><td>"+data[i].price+"</td><td>"+data[i].type+"</td>\</tr>")
                        $("<tr><td>"+data[i].update_date+"</td><td>"+data[i].product+"</td><td>"+data[i].direction+"</td><td>"+data[i].price+"</td>\</tr>").appendTo($("#tb-contain"))
                    }
                }
            }
        })
    },40000)
    //点击按钮获取数据
    $("#suspension button").click(function (even) {
        event.stopPropagation();
        $("#suspension button").css("color","white");
        $(this).css("color","#666666");
        var text=$(this).text();
        btn(text)
        clearInterval(timer)
        timer=setInterval(function () {
            $.ajax({
                url: "http://47.111.162.239/test2.php",
                TYPE:"GET",
                dataType:"json",
                data:{product:text},
                success:function (data) {
                    $("#tb-contain").empty();
                    if(data!=null){
                        for(var i=data.length-1;i>=0;i--){
                            // $("tbody").html("<tr><td>"+data[i].time.substring(5,16)+"</td><td>"+data[i].product+"</td><td>"+data[i].direction+"</td><td>"+data[i].price+"</td><td>"+data[i].type+"</td>\</tr>")
                            $("<tr><td>"+data[i].update_date+"</td><td>"+data[i].product+"</td><td>"+data[i].direction+"</td><td>"+data[i].price+"</td>\</tr>").appendTo($("#tb-contain"))
                        }
                    }
                }
            })
        },40000)
    })
})