$(function () {
    $.ajax({
        url:api_url+"expert/expertuser/ranking",
        type:"post",
        success:function (data) {
            var odds1="";
            var odds2="";
            $(".data-body-nav-content").eq(1).text(data.type+"胜率排名")
            var expert_popularity=data.data.expert_popularity;
            var profit_probability=data.data.profit_probability;
            for (var i = 0; i < expert_popularity.length; i++) {
                odds1+="<div style='margin-top: 8%'><div class='odds-left' ><span  class=\"layui-badge layui-bg-blue\">"+(i+1)+"</span><span style='margin-left: 4%;color: #666666' >"+expert_popularity[i].nickname+"</span></div>" +
                    "<div class='odds-right'style='color: #666666'><span>"+expert_popularity[i].popularity+"人</span></div></div>"
            }
            for (var i = 0; i < profit_probability.length; i++) {
                odds2+="<div style='margin-top: 8%'><div class='odds-left' ><span  class=\"layui-badge layui-bg-blue\">"+(i+1)+"</span><span style='margin-left: 4%;color: #666666'>"+profit_probability[i].nickname+"</span></div>" +
                    "<div class='odds-right' style='color: #666666'><span>"+profit_probability[i].probability+"</span></div></div>"
            }
            $(".data-body-odds").eq(0).html(odds1)
            $(".data-body-odds").eq(1).html(odds2)

        }
    })
})