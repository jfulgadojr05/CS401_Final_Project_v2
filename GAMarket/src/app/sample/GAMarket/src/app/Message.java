package sample.GAMarket.src.app;
import java.util.*;
import java.text.SimpleDateFormat;

public class Message {

    private Date timestamp;
    private String author;
    private String messageText;
    private boolean modified;
    private Date lastEditTimeStamp; 

     /**
      * Message object used in threads to display messages
      * @param msgAuthor string name of author
      * @param msg String of what the message 
      */
    public Message(String msgAuthor, String msg) 
	{
		this.timestamp = new Date();
		this.author = msgAuthor;
        this.messageText = msg;
        this.modified = false;
        this.lastEditTimeStamp = null;
	}
    /** return String representation of Message Object */
    public String toString () {
        return null; //TODO finish this
    }

    /** return string of time message was created*/
    public String getTimestamp () {
        return formatDate(this.timestamp);
    }

    /** return author of the message as a string */
    public String getAuthor () {
        return this.author;
    }
    
    /** return whether message has been modified */
    public boolean isModifed () {
        return this.modified;    
    }

    /** return text of message as a String*/
    public String getMessgeTxt () {
        return this.messageText;
    }
    /** change the value of message text
     *  up date modified satutus and modified timeStamp
     */
    public void editMessageText(String editedMessage){
        this.modified = true;
        this.lastEditTimeStamp = new Date();
        this.messageText = editedMessage;
        return ;
    }
    /** return date value of last edit */
    public String getLastEditTimestamp(){
        return formatDate(this.lastEditTimeStamp); 
    }

    /** return date in a formated string of the form
     *  dd/MM/yy HH:mm a ex 2/25/20 1:59pm
     */
    private String formatDate(Date d){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm a");
        return formatter.format(d);
    }
}