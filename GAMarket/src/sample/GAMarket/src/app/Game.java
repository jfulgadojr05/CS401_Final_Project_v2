package sample.GAMarket.src.app;

public class Game {
    private String name;
    private java.lang.Thread discussions[];
    private Message reviews[];
    private int id;
    private String genre;
    private String gamePath;
    private String metaTags;
    private String imgPath;
    private String developerName;
    private float averageRating; // TODO make not of change in uml

    //TODO WRITE DOC
    public java.lang.Thread createNewThread (String topic, String originalPost) { // creates a new game thread
        return null; // TODO finish this
    }
    
    //public initializeGame(gameName): file
    
    //TODO WRITE DOC
    public Message[] getAllReviews() { // get all reviews for the particular game
        return null; // TODO finish this
    }

    //TODO WRITE DOC
    public void setGameName (String name) {
        this.name = name; // TODO finish this
    }

    //TODO WRITE DOC
    public void setDeveloper (String developer) {
        this.developerName = developer; // TODO finish this
    }

    //TODO WRITE DOC
    public void setRating (float rating) {
        this.averageRating = rating; // TODO finish this
    }
    //TODO WRITE DOC
    public Message reviewGame () { // creates new review for particular game
        return null; // TODO finish this
    }

    //TODO WRITE DOC
    public Thread createThread() { // creates new thread based on game
        return null; // TODO finish this
    }
}