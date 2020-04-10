package GAMarket;

import javax.swing.*;

public class Chat {
	public static final int LESS = 0;
	public static final int GREATER = 1;
	public static final int EQUAL = 2;
    public static final int NOT_FOUND = -1;
    private Integer maxMessages = 100;
    private Message[] chatBox = new Message[maxMessages];
    

    public Message[] getChatBox() {
        return chatBox;
    }

    public void setChatBox(Message[] chatBox) {
        this.chatBox = chatBox;
    }

/*  sendMessage function
        show entire messages between two users
        have edittext box to send message
        and send message button
        array updates with sent message */
    public void sendMessage(String topic){
        return null;
    }

/*  displayMessages function
        for loop
            getMessage() of every message 
            into the messages array */
    public void displayMessages() {
    }
    
/*     findUser function
        take the string of the User
        search user with search function
        return the user and display user */
    public User findUser(String searchUser){
        return null;
    }

/*  searchUser function
        use binary search to find desired topic
        by comparing the first letter
        init int first, last, midpoint, pos(position)
        init boolean moreToSearch = first <= last and
        found set to false
        while loop
            midpoint = (first + last) / 2
            switch case
                (comparedTo function which will
                return the value of LESS, GREATER,
                or Equal (0,1,2) )
                case LESS 
                    last = midpoint - 1
                    moreToSearch = first <= last
                    break
                case GREATER
                    first = midPoint + 1
                    moreToSearch = first <= last
                    break
                case Equal
                    check if values are equal with compareTo function
                    if check < 0
                        case less code block here
                    else if check > 0
                        case greater code block here
                    else
                        values are equal
                        search done return Thread */
    private Thread searchUser(String topic) {
        return null;
    }

/*     ComparedTo function (helper)
        compare the two parameters, string toSearch
        and Thread topic string
            if toSearch < thread topic
                return LESS (0)
            else if toSearch > thread topic
                return GREATER(1)
            else
                return equal(2) */
    private int ComparedTo(String toSearch, Thread thread) {
	}


}