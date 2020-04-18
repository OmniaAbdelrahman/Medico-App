package com.example.medicalapp.Chat;

import java.util.ArrayList;

public class MessageObject {

    String messageId,
            senderId,
            message,
            kind,
    Time;

    ArrayList<String> mediaUrlList;

    public MessageObject(String messageId, String senderId, String message, ArrayList<String> mediaUrlList, String kind, String time) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.message = message;
        this.kind = kind;
        Time = time;
        this.mediaUrlList = mediaUrlList;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getMessageId() {
        return messageId;
    }
    public String getSenderId() {
        return senderId;
    }
    public String getMessage() {
        return message;
    }
    public ArrayList<String> getMediaUrlList() {
        return mediaUrlList;
    }

    public String getTime() {
        return Time;
    }
}