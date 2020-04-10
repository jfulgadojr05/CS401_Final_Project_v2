package app;

public class Game {
    private String name;
    private Thread discussions[];
    private app.Message reviews[];
    private int id;
    private String genre;
    private String gamePath;
    private String metaTags;
    private String imgPath;
    private String developerName;
    private float averageRating; // TODO make not of change in uml

    //TODO WRITE DOC
    public Thread createNewThread (String topic, String originalPost) {
        return null; // TODO finish this
    }
    
    //public initializeGame(gameName): file
    
    //TODO WRITE DOC
    public Message[] getAllReviews() {
        return null; // TODO finish this
    }

    //TODO WRITE DOC
    public void setGameName () {
        return ; // TODO finish this
    }

    //TODO WRITE DOC
    public void setDeveloper () {
        return ; // TODO finish this
    }

    //TODO WRITE DOC
    public void setRating () {
        return ; // TODO finish this
    }
    //TODO WRITE DOC
    public void reviewGame () {
        return ; // TODO finish this
    }

    //TODO WRITE DOC
    public void createThread() {
        return ; // TODO finish this
    }
}