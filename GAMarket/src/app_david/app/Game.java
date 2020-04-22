package app;

import GAMarket.Message;

public class Game {
    private String name;
    private Thread[] discussions;
    private GAMarket.Message[] reviews;
    private int id;
    private String genre;
    private String gamePath;
    private String metaTags;
    private String imgPath;
    private String developerName;
    private float averageRating; // TODO make not of change in uml

    
    //public initializeGame(gameName): file


    public void setGameName (String gameName) {
        this.name = gameName;
    }

    public void setDeveloper (String developer) {
        this.developerName = developer;
    }

    public void setRating (float aRating) {
        this.averageRating = aRating;
    }

    public void setDiscussions(Thread[] discussions) {
        this.discussions = discussions;
    }

    public void setReviews(Message[] reviews) {
        this.reviews = reviews;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setGamePath(String gamePath) {
        this.gamePath = gamePath;
    }

    public void setMetaTags(String metaTags) {
        this.metaTags = metaTags;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    //TODO WRITE DOC
    public void reviewGame () {
    }

    //TODO WRITE DOC
    public void createThread() {
    }

    //TODO WRITE DOC
    public GAMarket.Message[] getAllReviews() {

        return null; // TODO finish this
    }

    //TODO WRITE DOC
    public Thread createNewThread (String topic, String originalPost){
        return null; // TODO finish this
    }

}