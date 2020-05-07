package app;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;

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

    public void addGame(Game aGame){
        int counter = 0;
        if (numberOfGames == gameArray.length) {
            Game[] tempArray = new Game[numberOfGames*2];
            System.arraycopy(gameArray, 0, tempArray, 0, gameArray.length);
            gameArray = tempArray;
        }

        if(gameArray[0] == null){
            gameArray[0] = aGame;
            numberOfGames++;
        }
        else{
            while (gameArray[counter] != null){
                counter++;
            }
            gameArray[counter] = aGame;
            numberOfGames++;
        }
    }

    public Game searchForGame(String title) {
        // Can be used to search for a game in a collection
        // Will return a specific game object with its game fields
        Game temp = new Game();
        return temp;
    }
    public void intitializeGame(String gameName) {
        // Function to initialize a game as if
        // a game is being played from start up
        // will required the game title to search for
        // game and initialize it

    }
    public JList<String> filterGame(String aGenre){
        GameCollection tempCollect = new GameCollection();
        JList<String> filterList;
        Game[] tempArray = new Game[numberOfGames];
        int counter = 0;
        for (int i = 0; i < numberOfGames; i++) {
            if (gameArray[i].getGenre().equals(aGenre)) {
                tempArray[counter] = gameArray[i];
                counter++;
            }
        }
        DefaultListModel<String> tempStoreModel = new DefaultListModel<>();
        for(int i = 0; i < counter; i++){
            String tempItem = tempArray[i].getName() + ", " +
                    tempArray[i].getGenre() + ", " +
                    tempArray[i].getAverageRating();
            tempStoreModel.addElement(tempItem);
        }
        filterList = new JList<>(tempStoreModel);
        return filterList;
    }
    public void loadGameData(String filename){
        // Declaring temp variables
        String gameID;
        String gameName;
        String gameGenre;
        String gameRating;
//        String gameImagePath;
//        String gameMetaTag;
//        String gamePath;

        // Try-Catch for reading file
        try (Scanner gameFile = new Scanner(Paths.get(filename))) {
//            JOptionPane.showMessageDialog(null,"The file successfully loaded");
            // Checking file
            while (gameFile.hasNextLine()) {
                // Loops reads line and splits the comma for individual substrings
                StringTokenizer st = new StringTokenizer(gameFile.nextLine(), ",");
                // Putting values in temp variables
                gameID = st.nextToken();
                gameName = st.nextToken();
                gameGenre = st.nextToken();
                gameRating = st.nextToken();
//                gameImagePath = st.nextToken();
//                gameMetaTag = st.nextToken();
//                gamePath = st.nextToken();

                Game tempGame = new Game();

                tempGame.setId(Integer.parseInt(gameID));
                tempGame.setGameName(gameName);
                tempGame.setGenre(gameGenre);
                tempGame.setRating(Float.parseFloat(gameRating));

                addGame(tempGame);
                // Add function to the array.
            }//while
        }
        catch (IOException | NoSuchElementException | IllegalStateException e) {
            JPanel errorPanel = new JPanel();
            JLabel errorMessage = new JLabel("File not found.\nCreating blank game store...");
            errorPanel.add(errorMessage);
            errorPanel.setVisible(true);
        }//catch()
    }
}
