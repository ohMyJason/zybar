var api_url = "http://ws.find37.com/index.php?s=";
// var api_url = "http://192.168.2.234/lily2/public/index.php/";

var webSocketNewLive = "wss://ws.find37.com:7272";
// var webSocketNewLive = "ws://192.168.2.234:7272";
var touristHall = localStorage.getItem('tourist_user');
jsonUser = eval('('+touristHall+')');

if (jsonUser == null || jsonUser == "") {
    jsonUserID = "";

}else {
    jsonUserID = jsonUser.id
}
// console.log(jsonUserID)