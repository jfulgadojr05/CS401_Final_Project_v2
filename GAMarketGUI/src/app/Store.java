package app;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

// GUI for providing store commands for a store object
public class Store {

    // member function which will store a feed of games
    // for users to look at, get recommended features
    private Game[] feed;
    private int numGames;

    // Default constructor
    public Store() {
        numGames = 0;
        feed = new Game[7];
    }

    // Getters
    public Game[] getFeed() {
        return feed;
    }

    public int getNumGames() {
        return numGames;
    }


    public void purchaseGame(String title){
        // Store function will let a user purchase a game
        // from a gamecollection object
    }

    public Game searchGames(String name) {
        // Utilize this function to search for a game
        // with a text box and a macro to pull up the correct games
        return null;
    }

    public void gotoGameProfile() {
        // Enter a games profile, showing related games
        // that the developer has also created and
        // recommended game
    }



}
