package app;

import java.util.Date;  
import java.util.Calendar;  
/* Reply class
	CS 401 - Final Project
	Reply.java
*/
public class Reply {
    User user;
    Date date;

    String reply;

    public String getUsername() {
        return user.getUsername();
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
     
}