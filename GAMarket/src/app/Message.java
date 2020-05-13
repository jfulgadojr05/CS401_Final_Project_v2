package app;

import java.util.*;
import java.text.SimpleDateFormat;


/* Message class
	CS 401 - Final Project
	Message.java
  By: Christian Magpantay
  Code/Book Reference -
*/
public class Message {

    private Date timeStamp;
    private String text;

    // constructors
    public Message() {
        timeStamp = new Date();
        text = " ";
    }

    public Message(Date timeStamp, String msg) {
        this.timeStamp = new Date();
        this.text = msg;
    }

    // getters & setters
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    // all other methods

    /*  createMessage function
            shown in thread
                create text
                set timestamp with current time of submission
                 */
    public void createMessage() {
    }
}