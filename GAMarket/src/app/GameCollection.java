package app;

import db.DBHelper;

import javax.swing.*;
import java.sql.SQLException;

// GUI that will provide a display of all games in a collection
// This will required a database file that will host the games
public class GameCollection {

    // member variables
    // the store collection of games, array
    // count of games in a collection/store
    private Game[] gameArray;
    private int numberOfGames;

    public GameCollection() {
        gameArray = new Game[7];
        numberOfGames = 0;
    }

    public Game[] getGameArray() {
        return gameArray;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public void setGameArray(Game[] gameArray) {
        this.gameArray = gameArray;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }


    public void deleteGame(String gameName, DBHelper dbh, String userID) throws SQLException {
        // Delete a users game from their collection
        // from the database using gamename, dbhelper, and user id

        // Confirm message
        String confirmMessage = "Delete " + gameName + " from library?";

        // joptionpane to delete game from user collection
        int confirmOption = JOptionPane.showConfirmDialog(null, confirmMessage);
        if (confirmOption == JOptionPane.YES_OPTION){

            // dbh delete game function
            dbh.deleteGame(gameName, userID);
        }
    }

    public String searchForGame(String gameTitle, DBHelper dbh) throws SQLException {
        // Return a string if the game was found or not

        // Creating local variables
        String temp = null;

        // Getting game object array from database
        Game[] tempGameArray = dbh.getAllGameObjects();

        // for loop to see if the game is present in game table
        for (Game game : tempGameArray) {

            // if yes
            if (game.getName().toUpperCase().equals(gameTitle.toUpperCase())) {

                // Set temp string to game id
                temp = Integer.toString(game.getId());
                break;
            }
        }

        // if no game was found
        if(temp == null){
            temp = "Not found";
        }

        // return temp
        return temp;
    }
    public void purchaseGame(String gameName, String userID, DBHelper dbh) throws SQLException {
        // Function to purchase game, which adds to users game collection after purchase

        // Local variables
        String confirmMessage = "Purchase " + gameName + "?";

        // joption pane to confirm game purchase
        int confirmOption = JOptionPane.showConfirmDialog(null, confirmMessage);
        if (confirmOption == JOptionPane.YES_OPTION){

            // add game to users game collection
            dbh.purchaseGame(gameName, userID);
        }

    }
    public JList<String> filterGameGenre(String aGenre, DBHelper dbh) throws SQLException {
        // Return a filtered JList by genre
        return dbh.getFilterGameGenre(aGenre);

    }

    public JList<String> filterGameRating(String aRating, DBHelper dbh) throws SQLException {
        // Return a filtered JList by rating
        return dbh.getFilterGameRating(aRating);
    }

}
