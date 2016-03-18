//创建 WebSocket 链接 并进行相关动作事件绑定
var webSocket = new WebSocket("ws://" + location.hostname + ":" + location.port + "/chat/");
webSocket.onmessage = function (msg) { updateChat(msg); };
webSocket.onclose = function () { alert("WebSocket connection closed") };

//发送按钮补充消息发送功能
id("send").addEventListener("click", function () {
    sendMessage(id("message").value);
});

//绑定回车进行信息发送
id("message").addEventListener("keypress", function (e) {
    if (e.keyCode === 13) { sendMessage(e.target.value); }
});

//发送信息至服务端 WebSocket 并清空当前内容
function sendMessage(message) {
    if (message !== "") {
        webSocket.send(message);
        id("message").value = "";
    }
}

//插入聊天信息& 更新用户列表
function updateChat(msg) {
    var data = JSON.parse(msg.data);
    insert("chat", data.userMessage);
    id("userlist").innerHTML = "";
    data.userlist.forEach(function (user) {
        insert("userlist", "<li>" + user + "</li>");
    });
}

//于指定 HTML 元素前插入
function insert(targetId, message) {
    id(targetId).insertAdjacentHTML("afterbegin", message);
}

//获取指定 ID 元素
function id(id) {
    return document.getElementById(id);
}