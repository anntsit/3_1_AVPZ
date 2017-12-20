package com.ann;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private String msgText;
    private Date date;
    private String senderName;

    public Message(String msgText, Date date, String senderName) {
        this.msgText = msgText;
        this.date = date;
        this.senderName = senderName;

    }

    public String getMsgText() {
        return msgText;
    }

    public Date getDate() {
        return date;
    }

    public String getSenderName() {
        return senderName;
    }
}
