$(function () {



$("#window-header").append("<div class=\"layui-logo\">风口交易吧后台</div>\n" +
    "        <!-- 头部区域（可配合layui已有的水平导航） -->\n" +
    "        <ul class=\"layui-nav layui-layout-left\">\n" +
    "            <li class=\"layui-nav-item\"><a href=\"\"></a></li>\n" +
    // "            <li class=\"layui-nav-item\"><a href=\"\">用户</a></li>\n" +
    "            <li class=\"layui-nav-item\">\n" +
    "                <a href=\"javascript:;\">其它系统</a>\n" +
    "                <dl class=\"layui-nav-child\">\n" +
    "                    <dd><a href=\"/user/page.html\">前台</a></dd>\n" +
    "                </dl>\n" +
    "            </li>\n" +
    "        </ul>\n" +
    "        <ul class=\"layui-nav layui-layout-right\">\n" +
    "            <li class=\"layui-nav-item\">\n" +
    "                <a href=\"javascript:;\">\n" +
    "                    <img src=\""+$.cookie("photoUrl")+"\" class=\"layui-nav-img\">\n" +
    "                    "+ $.cookie("username")+"\n" +
    "                </a>\n" +
    "                <dl class=\"layui-nav-child\">\n" +
    "                    <dd><a href=\"#\" id='alertUserInfo'>基本资料</a></dd>\n" +
    // "                    <dd><a href=\"\">安全设置</a></dd>\n" +
    "                </dl>\n" +
    "            </li>\n" +
    "            <li class=\"layui-nav-item\"><a href=\"/back/adminLogin.html\">退了</a></li>\n" +
    "        </ul>")


$("#window-left").append("<div class=\"layui-side-scroll\">\n" +
    "            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->\n" +
    "            <ul class=\"layui-nav layui-nav-tree\"  lay-filter=\"test\">\n" +
    "                <li class=\"layui-nav-item\" id=\"super\">\n" +
    "                    <a class=\"\" href=\"javascript:;\">金融超市</a>\n" +
    "                    <dl class=\"layui-nav-child\">\n" +
    "                        <dd><a href=\"/back/model/zyVoice.html\">风口之声管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/paper.html\">机构研报管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/app.html\">手机App管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/zyView.html\">风口视角管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/caoPan.html\">操盘经典管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/plan.html\">布局计划管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/inveEdu.html\">投资者教育管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/bigData.html\">大数据云管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/popularRanking.html\">人气排名管理</a></dd>\n" +
    "                        <dd><a href=\"/back/model/winRanking.html\">胜率排名管理</a></dd>\n" +
    "                    </dl>\n" +
    "                </li>\n" +
    "                <li class=\"layui-nav-item\" id=\"multi\">\n" +
    "                    <a href=\"javascript:;\">多维量化</a>\n" +
    "                    <dl class=\"layui-nav-child\">\n" +
    "                        <dd><a href=\"/back/model/coke.html\">焦炭</a></dd>\n" +
    "                        <dd><a href=\"/back/model/formaldehyde.html\">甲醛</a></dd>\n" +
    "                        <dd><a href=\"/back/model/rebar.html\">螺纹钢</a></dd>\n" +
    "                        <dd><a href=\"/back/model/asphalt.html\">沥青</a></dd>\n" +
    "                        <dd><a href=\"/back/model/hotRoll.html\">热卷</a></dd>\n" +
    "                        <dd><a href=\"/back/model/cokingCoal.html\">焦煤</a></dd>\n" +
    "                    </dl>\n" +
    "                </li>\n" +
    "                <li class=\"layui-nav-item\" id=\"viewOnline\">\n" +
    "                    <a href=\"javascript:;\">观点在线</a>\n" +
    "                    <dl class=\"layui-nav-child\">\n" +
    "                        <dd><a href=\"/back/model/viewOnline.html\">我要直播</a></dd>\n" +
    "                        <dd><a href=\"/back/model/hjStrategy.html\">黑金战队策略</a></dd>\n" +
    "                        <dd><a href=\"/back/model/jjStrategy.html\">狙击战队策略</a></dd>\n" +
    "                        <dd><a href=\"/back/model/fyStrategy.html\">风云战队策略</a></dd>\n" +
    "                    </dl>\n" +
    "                </li>\n" +
    "                <li class=\"layui-nav-item\" id=\"userAdmin\">\n" +
    "                    <a href=\"javascript:;\">账号管理</a>\n" +
    "                    <dl class=\"layui-nav-child\">\n" +
    "                        <dd><a href=\"javascript:;\">用户管理</a></dd>\n" +
    "                    </dl>\n" +
    "                </li>\n" +
    "\n" +
    "\n" +
    "            </ul>\n" +
    "        </div>")

    
    $("#alertUserInfo").click(function () {
        layer.open({
            type: 2,
            area : ['800px' , '580px'],
            content: '/back/content/userInfo.html' //这里content是一个普通的String
        });
    })

})