package app;

import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.*;

// GUI for providing store commands for a store object
public class Store {

    // member function which will store a feed of games
    // for users to look at, get recommended features
    private Game[] feed;


    public GameCollection purchaseGame(String genre){
        // Store function will let a user purchase a game
        // from a gamecollection object
        GameCollection test = new GameCollection();
        return test;
    }

    public Game searchGames(String name) {
        // Utilize this function to search for a game
        // with a text box and a macro to pull up the correct games
        Game test = new Game();
        return test;
    }

    public void gotoGameProfile() {
        // Enter a games profile, showing related games
        // that the developer has also created and
        // recommended game
    }

}
