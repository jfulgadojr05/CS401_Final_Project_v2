package sample.GAMarket.src.app;

public class Thread {
    private String topic;
    private Message messages[];
    private String originalPoster; // might change to user id
    //String/Users participants
    private boolean privateChat;
    private boolean publicForum;
    //Parent id <-(?)

    //TODO WRITE DOC
    public Thread() {
        return ; // TODO finish this
    }
    
    //TODO WRITE DOC
    public String getTopic () {
        return null; // TODO finish this
    }
   
    //TODO WRITE DOC
    public Message[] getMessages () {
        return null; // TODO finish this
    }

    //TODO WRITE DOC
    public String getOriginalPoster () {
        return null; // TODO finish this
    }

    //TODO WRITE DOC
    public void deleteMessage (Message msg) {
        return ; // TODO finish this
    }

    //TODO WRITE DOC
    public void editMessage (String newTxt) {
        return ; // TODO finish this
    }

    //TODO WRITE DOC
    public void createNewMessage(String messageTxt, String user) {
        return ; // TODO finish this
    }

    //TODO WRITE DOC
    public Message[] sortMessagesByUser (String username) {
        return null; // TODO finish this
    }

    //TODO WRITE DOC
    public Message[] searchMessages (String searchTxt) {
        return null; // TODO finish this
    }     
    // public User[] getParticipents () {
    //     return null; // TODO finish this need user class to impement 
    // }
}