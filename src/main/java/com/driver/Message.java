package com.driver;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Message {
    private int id;



    private String content;
    private Date timestamp;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        // Date timestamp = Calendar.getInstance().getTime();
        return timestamp;
    }

    public Message(int id, String content) {
        this.id = id;
        this.content = content;
        this.timestamp=new Date();
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }




}
