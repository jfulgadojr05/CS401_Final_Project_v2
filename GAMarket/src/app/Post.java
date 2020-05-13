package app;


import java.util.Date;  
import java.util.Calendar;  

public class Post {
    User user;
    Thread thread;
    Date date;

    String threadTitle;
    String username;

    public String getThreadTitle() {
        return thread.getTitle();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }
    
}