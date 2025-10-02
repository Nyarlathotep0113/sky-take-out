package com.sky.websocket;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
@ServerEndpoint("/ws/{sid}") // 标记为WebSocket服务端
public class WebSocketServer {
    // 存放所有连接的session
    private static Map<String, Session> sessionMap=new HashMap();
    @OnOpen//将方法变成回调方法，当连接建立时自动调用
    public void onOpen(Session session, @PathParam("sid") String sid) {
        System.out.println("客户端:"+sid+"建立连接");
        sessionMap.put(sid, session);
    }
    @OnMessage//将方法变成回调方法，当收到客户端消息时自动调用
    public void onMessage(String message,@PathParam("sid") String sid) {
        System.out.println("客户端:"+sid+"发送消息"+message);
    }

    @OnClose//将方法变成回调方法，当连接关闭时自动调用
    public void onClose(@PathParam("sid") String sid){
        System.out.println("客户端:"+sid+"断开连接");
        sessionMap.remove(sid);
    }

    public void sendToAll(String message){
        Collection<Session> sessions= sessionMap.values();
        for (Session session:sessions){
            try {
                //发送消息给所有客户端
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
