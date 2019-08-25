var websocket = null;


$(function () {

    $("#send").click(function () {
        var message = $("#message-aera").val();
        //通过判断1还是2，来判断是用户发的还是老师发的
        websocket.send(1+"&*&"+message+"&*&"+$.cookie("username"));
    })
})

// 保持滚动条在最下面
function scrollDown(divId) {
    var content = document.getElementById(divId);
    content.scrollTop = content.scrollHeight;
}

function setMessage(event) {
    console.log(event.data);
    var role=event.data.split("&*&")[0];
    var message=event.data.split("&*&")[1];
    var username = event.data.split("&*&")[2];
    if (role==1){
        $("#order-content").append("<div class=\"chat-item\" id=\"hallUsers\" style=\"display: block;\">\n" +
            "                    <div class=\"chat-left\"><img src=\"http://image.find37.com/150950665859f93e62b5b32.png\" +=\"\" -live=\"\">\n" +
            "                    </div>\n" +
            "                    <div class=\"chat-right\"><p class=\"time\">"+new Date().toLocaleTimeString()+"</p>\n" +
            "                        <div class=\"chat-user\"><img class=\"imgLogo\" src=\"http://image.find37.com/grade1.png\" +=\"\"\n" +
            "                                                    -live=\"\">"+username+"\n" +
            "                        </div>\n" +
            "                        <div class=\"chat-message\">"+message+"</div>\n" +
            "                    </div>\n" +
            "                </div>\n");

        scrollDown("order-content");
    }else if (role==2){

    } else {

    }

}


//判断当前浏览器是否支持WebSocket
if('WebSocket' in window){
    websocket = new WebSocket("ws://localhost:8080/websocket");
}
else{
    alert('Not support websocket');
}


//连接发生错误的回调方法
websocket.onerror = function(){
    // setMessageInnerHTML("error");
};

//连接成功建立的回调方法
websocket.onopen = function(event){
    // setMessageInnerHTML("open");


}

//接收到消息的回调方法
websocket.onmessage = function(event){
    setMessage(event);

}

//连接关闭的回调方法
websocket.onclose = function(){
    // setMessageInnerHTML("close");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function(){
    websocket.close();
}

//将消息显示在网页上
function setMessageInnerHTML(innerHTML){
    // document.getElementById('message').innerHTML += innerHTML + '<br/>';
}

//关闭连接
function closeWebSocket(){
    websocket.close();
}

//发送消息
// function send(){
//     var message = $("#message-aera").val();
//     websocket.send("1&*&"+message+"&*&"+$.cookie("username"));
// }