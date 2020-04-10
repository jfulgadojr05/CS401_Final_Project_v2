package GAMarket;
import java.util.*;
import java.text.SimpleDateFormat;

public class Post {
    private User user;
    private Date timeStamp;
    private String postTitle;
    private String postText;

    public Post() {
        user = null;
        timeStamp = null;
        postTitle = " ";
        postText = " ";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    private String formatDate(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        return formatter.format(d);
    }

    @Override
    public String toString() {
        String postedDate = formatDate(timeStamp);
        return user + postedDate + "\n" + 
                postTitle + "\n" + 
                postText + "\n";
    }

/*  createPost function  
        shown in thread
            create title
            create post
            set timestamp with current time of submission */
    public void createPost() {
    }
    
}