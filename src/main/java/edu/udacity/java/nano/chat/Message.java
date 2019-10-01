package edu.udacity.java.nano.chat;

import com.alibaba.fastjson.JSONObject;

/**
 * WebSocket message model
 */

public class Message {
    // TODO: add message model.
    private String msg = "";
    private String username = "";
    private String type = ""; //ENTER, SPEAK, LEAVE
    private String onlineCount = "0";


    public Message(){}

    public Message(String sender){
        this.username = sender;
    }

    public Message(String sender, String mess){
        this.msg = mess;
        this.username = sender;
    }

    public String getMess() {
        return msg;
    }

    public void setMess(String mess) {
        this.msg = mess;
    }

    public String getSender() {
        return username;
    }

    public void setSender(String sender) {
        this.username = sender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOnlineCount() {
        return onlineCount;
    }

    public void setOnlineCount(String onlineCount) {
        this.onlineCount = onlineCount;
    }

    public String toJsonString() {

        JSONObject jsonObj = new JSONObject();
        jsonObj.put("username", this.username);
        jsonObj.put("msg", this.msg);
        jsonObj.put("type", this.type);
        jsonObj.put("onlineCount", this.onlineCount);
        return jsonObj.toJSONString();
    }
}
