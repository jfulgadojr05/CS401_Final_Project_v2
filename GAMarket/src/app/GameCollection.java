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

    public String searchForGame(String gameTitle, DBHelper dbh) throws SQLException {
        String temp = null;
        Game[] tempGameArray = dbh.getAllGameObjects();

        for (Game game : tempGameArray) {
            if (game.getName().toUpperCase().equals(gameTitle.toUpperCase())) {
                temp = Integer.toString(game.getId());
                break;
            }
        }
        if(temp == null){
            temp = "Not found";
        }
        return temp;
    }
    public void purchaseGame(String gameName) {
        // Function to purchase game, which adds to store
        String confirmMessage = "Purchase " + gameName + "?";
        JOptionPane.showConfirmDialog(null, confirmMessage);


    }
    public JList<String> filterGameGenre(String aGenre, DBHelper dbh) throws SQLException {
        //        Game[] tempArray = new Game[numberOfGames];
//        int counter = 0;
//        for (int i = 0; i < numberOfGames; i++) {
//            if (gameArray[i].getGenre().equals(aGenre)) {
//                tempArray[counter] = gameArray[i];
//                counter++;
//            }
//        }
//        if (tempArray[0] == null){
//            JOptionPane.showMessageDialog(null, "No games found");
//            DefaultListModel<String> tempStoreModel = new DefaultListModel<>();
//            for(int i = 0; i < numberOfGames; i++){
//                String tempItem = gameArray[i].getName() + ", " +
//                        gameArray[i].getGenre() + ", " +
//                        gameArray[i].getAverageRating();
//                tempStoreModel.addElement(tempItem);
//            }
//            filterList = new JList<>(tempStoreModel);
//        }
//        else {
//            DefaultListModel<String> tempStoreModel = new DefaultListModel<>();
//            for(int i = 0; i < counter; i++){
//                String tempItem = tempArray[i].getName() + ", " +
//                        tempArray[i].getGenre() + ", " +
//                        tempArray[i].getAverageRating();
//                tempStoreModel.addElement(tempItem);
//            }
//            filterList = new JList<>(tempStoreModel);
//        }
        return dbh.getFilterGameGenre(aGenre);

    }

    public JList<String> filterGameRating(String aRating, DBHelper dbh) throws SQLException {
//        float aRatingFloat = Float.parseFloat(aRating);
//        JList<String> filterList;
//        Game[] tempArray = new Game[numberOfGames];
//
//        if (aRatingFloat > 10.0 || aRatingFloat < 0.0) {
//            JOptionPane.showMessageDialog(null, "Filter exceeds limit");
//            DefaultListModel<String> tempStoreModel = new DefaultListModel<>();
//            for(int i = 0; i < numberOfGames; i++){
//                String tempItem = gameArray[i].getName() + ", " +
//                        gameArray[i].getGenre() + ", " +
//                        gameArray[i].getAverageRating();
//                tempStoreModel.addElement(tempItem);
//            }
//            filterList = new JList<>(tempStoreModel);
//        }
//        else {
//            int counter = 0;
//            for (int i = 0; i < numberOfGames; i++) {
//                if (gameArray[i].getAverageRating() <= aRatingFloat) {
//                    tempArray[counter] = gameArray[i];
//                    counter++;
//                }
//            }
//            if (tempArray[0] == null){
//                JOptionPane.showMessageDialog(null, "No games found");
//                DefaultListModel<String> tempStoreModel = new DefaultListModel<>();
//                for(int i = 0; i < numberOfGames; i++){
//                    String tempItem = gameArray[i].getName() + ", " +
//                            gameArray[i].getGenre() + ", " +
//                            gameArray[i].getAverageRating();
//                    tempStoreModel.addElement(tempItem);
//                }
//                filterList = new JList<>(tempStoreModel);
//            }
//            else {
//                DefaultListModel<String> tempStoreModel = new DefaultListModel<>();
//                for(int i = 0; i < counter; i++){
//                    String tempItem = tempArray[i].getName() + ", " +
//                            tempArray[i].getGenre() + ", " +
//                            tempArray[i].getAverageRating();
//                    tempStoreModel.addElement(tempItem);
//                }
//                filterList = new JList<>(tempStoreModel);
//            }
//        }
        return dbh.getFilterGameRating(aRating);
    }
//    public void loadGameData(String filename){
//        // Declaring temp variables
//        String gameID;
//        String gameName;
//        String gameGenre;
//        String gameRating;
//        String gameDeveloper;
////        String gameImagePath;
////        String gameMetaTag;
////        String gamePath;
//
//        // Try-Catch for reading file
//        try (Scanner gameFile = new Scanner(Paths.get(filename))) {
////            JOptionPane.showMessageDialog(null,"The file successfully loaded");
//            // Checking file
//            while (gameFile.hasNextLine()) {
//                // Loops reads line and splits the comma for individual substrings
//                StringTokenizer st = new StringTokenizer(gameFile.nextLine(), ",");
//                // Putting values in temp variables
//                gameID = st.nextToken();
//                gameName = st.nextToken();
//                gameGenre = st.nextToken();
//                gameRating = st.nextToken();
////                gameImagePath = st.nextToken();
////                gameMetaTag = st.nextToken();
////                gamePath = st.nextToken();
//
//                Game tempGame = new Game();
//
//                tempGame.setId(Integer.parseInt(gameID));
//                tempGame.setGameName(gameName);
//                tempGame.setGenre(gameGenre);
//                tempGame.setRating(Float.parseFloat(gameRating));
//
//                addGame(tempGame);
//                // Add function to the array.
//            }//while
//        }
//        catch (IOException | NoSuchElementException | IllegalStateException e) {
//            JPanel errorPanel = new JPanel();
//            JLabel errorMessage = new JLabel("File not found.\nCreating blank game store...");
//            errorPanel.add(errorMessage);
//            errorPanel.setVisible(true);
//        }//catch()
//    }
}
