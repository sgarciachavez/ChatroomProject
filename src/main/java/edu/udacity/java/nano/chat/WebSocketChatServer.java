package edu.udacity.java.nano.chat;


import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WebSocket Server
 *
 * @see ServerEndpoint WebSocket Client
 * @see Session   WebSocket Session
 */


@Component
@ServerEndpoint(value = "/chat/{username}")
public class WebSocketChatServer {

    /**
     * All chat sessions.
     */
    private static Map<String, Session> onlineSessions = new ConcurrentHashMap<>();

    private static void sendMessageToAll(Message msg){

       onlineSessions.forEach((user,session) -> {
           try{
               if(session.isOpen()) {
                   session.getBasicRemote().sendText(msg.toJsonString());
               }
           }catch (IOException e){
               e.printStackTrace();
           }
       });
    }

    /**
     * Open connection, 1) add session, 2) add user.
     */
    @OnOpen
    public void onOpen( Session session, @PathParam("username") String username)
            throws IOException {

        onlineSessions.put(username, session);
    }

    /**
     * Send message, 1) get username and session, 2) send message to all.
     */
    @OnMessage
    public void onMessage(Session session, String jsonStr) throws IOException{

        Gson g = new Gson();
        Message mess = g.fromJson(jsonStr, Message.class);

        mess.setType("SPEAK");
        mess.setOnlineCount(getOnlineUserCount());
        sendMessageToAll(mess);
    }

    /**
     * Close connection, 1) remove session, 2) update user.
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        Message mess = new Message();
        mess.setOnlineCount(getOnlineUserCount());
        sendMessageToAll(mess);
    }

    /**
     * Print exception.
     */
    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    private int count = 0;
    private String getOnlineUserCount(){
        setCount(0);
        onlineSessions.forEach((user, session) -> {
            if(session.isOpen()){
                count++;
            }
        });

        return String.valueOf(count);
    }

    private void setCount(int count) {
        this.count = count;
    }
}
