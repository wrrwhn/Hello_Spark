package com.spark.chat;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.*;

import static j2html.TagCreator.*;
import static spark.Spark.*;


/**
 * Creator: 姚清居
 * Date:    2016/3/18
 * For:     WebSocket chat
 * Other:
 */
public class Chat {

    // 当前聊天室内用户信息
    static Map<Session, String> userNameMap = new HashMap<>();
    // 随机名字列表
    static String[] nameArray= {"Yao", "Wang", "Pan", "Zhuang", "Luo"};
    static List<String> nameList= Arrays.asList(nameArray);

    /**
     * 启动程序
     * @param args
     */
    public static void main(String[] args) {
        staticFileLocation("chat");                     // 设置 html 目录地址
        webSocket("/chat", ChatWebSocketHandler.class); // 配置请求服务
        init();                                         // 服务配置初始化
    }

    /**
     * 广播当前信息给所有在线用户
     * @param sender
     * @param message
     */
    public static void broadcastMessage(String sender, String message) {
        userNameMap.keySet().stream().filter(Session::isOpen).forEach(session -> {                  // 过滤分发
            try {
                session.getRemote().sendString(String.valueOf(new JSONObject()                      // JSON 格式化
                        .put("userMessage", createHtmlMessageFromSender(sender, message))   // 信息封装
                        .put("userlist", userNameMap.values())                              // 当前用户列表
                ));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    /***
     * 创建消息体
     *  返回内容格式：
     *      <article>
     *          <b>Server says:</b>
     *          <p>Zhuang joined the chat</p>
     *          <span class="timestamp">22:28:38</span>
     *      </article>
     * @param sender
     * @param message
     * @return
     */
    private static String createHtmlMessageFromSender(String sender, String message) {
        return article().with(
                    b(sender + " says:"),
                    p(message),
                    span().withClass("timestamp").withText(new SimpleDateFormat("HH:mm:ss").format(new Date()))
            ).render();
    }
}
