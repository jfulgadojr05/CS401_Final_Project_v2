package app;

public class devUser extends User {
    // Interface would list four buttons that would execute the four methods in the class

    private int collectionId;
    private float income;
    // private app.GameCollection developedGames[];

    public Message postGames(String gameData) // creates a new message promote new games
    {
        // code
        return null; // stub
    }

    public Message editTheirGames(String gameData) // allows devs to edit information about their game
    {
        // code
        return null; // stub
    }

    public Message deleteTheirGames(int gameId) // allows devs to remove one of their games
    {
        // code
        return null; // stub
    }

    public void deleteLocalDevGameThreads() // deletes entire game thread
    {
        // code
    }

}
