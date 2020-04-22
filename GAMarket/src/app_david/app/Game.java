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

    // Setters
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

    // Getters
    public String getName() {
        return name;
    }

    public Thread[] getDiscussions() {
        return discussions;
    }

    public Message[] getReviews() {
        return reviews;
    }

    public int getId() {
        return id;
    }

    public String getGenre() {
        return genre;
    }

    public String getGamePath() {
        return gamePath;
    }

    public String getMetaTags() {
        return metaTags;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public float getAverageRating() {
        return averageRating;
    }

    //TODO WRITE DOC
    public void reviewGame () {
    }

    //TODO WRITE DOC
    public void createThread() {
    }

    //TODO WRITE DOC
    public void getAllReviews() {

    }

    //TODO WRITE DOC
    public Thread createNewThread (String topic, String originalPost){
        return null; // TODO finish this
    }

}