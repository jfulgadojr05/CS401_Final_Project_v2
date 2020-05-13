package app;

import java.util.Date;  
import java.util.Calendar;  
/* 
    Thread class
	CS 401 - Final Project
	Thread.java
    By: Christian Taro Magpantay
    Code/Book Reference -
    https://www.javatpoint.com/java-date-to-string
 */
public class Thread {

    Game game;
    String gameName;
    String title; 
    Date currentDateAndTime;

    public String getGameName() {
        return game.getName();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {  
        return Calendar.getInstance().getTime();
    }

}