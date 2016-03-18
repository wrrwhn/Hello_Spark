package com.spark.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.util.Random;


/**
 * Creator: 姚清居
 * Date:    2016/3/18
 * For:     WebSocket Handler
 * Other:
 */
@WebSocket
public class ChatWebSocketHandler {

    // 消息来源和内容
    private String sender, msg;

    /**
     *  请求 WebSocket 请求时触发
     *      -> 广播用户加入
     * @param user
     * @throws Exception
     */
    @OnWebSocketConnect
    public void onConnect(Session user) throws Exception {
        String username = Chat.nameList.get(new Random().nextInt(Chat.nameList.size()));        // 随机用户名
        Chat.userNameMap.put(user, username);
        Chat.broadcastMessage(sender = "Server", msg = (username + " joined the chat"));
    }

    /***
     *  WebSocket 断开时提醒
     *      -> 广播指定用户退出
     * @param user
     * @param statusCode
     * @param reason
     */
    @OnWebSocketClose
    public void onClose(Session user, int statusCode, String reason) {
        String username = Chat.userNameMap.get(user);
        Chat.userNameMap.remove(user);
        Chat.broadcastMessage(sender = "Server", msg = (username + " left the chat"));
    }

    /***
     *  广播用户发送消息
     * @param user
     * @param message
     */
    @OnWebSocketMessage
    public void onMessage(Session user, String message) {
        Chat.broadcastMessage(sender = Chat.userNameMap.get(user), msg = message);
    }

}
