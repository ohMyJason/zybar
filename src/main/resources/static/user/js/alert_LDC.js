document.onmousedown = function mdClick(event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];
    if (e.button == 2 || e.button == 3) {
        liveHall_LDC();
    }
}

document.oncontextmenu = new Function("return false;");

document.onkeydown = document.onkeyup = document.onkeypress = function(event) {
    var e = event || window.event || arguments.callee.caller.arguments[0];

    if (e && e.keyCode == 123) {
        liveHall_LDC();
        e.returnValue = false;
        return (false);
    }
}
function liveHall_LDC() {
    layer.alert('禁止操作！！！', {
            title : '状元小贴士提醒您',
            icon: 2,
            closeBtn : 1
        }
    )}