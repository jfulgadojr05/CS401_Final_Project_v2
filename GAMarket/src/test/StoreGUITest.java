package test;

import app.Game;
import app.GameCollection;
import db.DBHelper;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.sql.*;

import static org.junit.Assert.*;

public class StoreGUITest {

    DBHelper testDB = new DBHelper();
    GameCollection testGC = new GameCollection();
    ResultSet resultTest;
    PreparedStatement prepareStmtTest;
    Statement stmtTest;
    Connection connectionTest;



    @Before
    public void initializeDB() throws SQLException {
        testDB.createNewDatabase();
        testDB.createAllTables();
    }
//
//    @After
//    public void closeDB() throws  SQLException {
//        resultTest.close();
//        prepareStmtTest.close();
//        stmtTest.close();
//        connectionTest.close();
//    }



    @Test
    public void getAllGameTest() throws SQLException {
        // Check if a game exists in all the games
        // will also check if a game does not exist
        boolean isPresent = false;
        int testGameID = 1002;
        int testNonGameID = 1010;
        Game[] allGameTest = testDB.getAllGameObjects();
        for (Game game : allGameTest) {
            if (game.getId() == testGameID) {
                isPresent = true;
                break;
            }
        }

        assertTrue("Game is not present",isPresent);

        isPresent = false;
        for (Game game : allGameTest) {
            if (game.getId() == testNonGameID) {
                isPresent = true;
                break;
            }
        }
        assertFalse("Game is present",isPresent);
    }

    @Test
    public void filterGameGenreTest() throws SQLException {
        boolean isPresent = false;
        String testGameName = "Super Mario Odyssey";
        String testNonGameName = "Tetris 99";
        JList<String> filterGameList = testDB.getFilterGameGenre("E");
        for (int i = 0; i < filterGameList.getModel().getSize(); i++) {
            if (filterGameList.getModel().getElementAt(i).equals(testGameName)) {
                isPresent = true;
                break;
            }
        }

        assertTrue("Game is not present",isPresent);

        isPresent = false;
        for (int i = 0; i < filterGameList.getModel().getSize(); i++) {
            if (filterGameList.getModel().getElementAt(i).equals(testNonGameName)) {
                isPresent = true;
                break;
            }
        }
        assertFalse("Game is present",isPresent);
    }

    @Test
    public void filterGameRatingTest() throws SQLException {
        boolean isPresent = false;
        String testGameName = "Tetris 99";
        String testNonGameName = "OVercooked 2";
        JList<String> filterGameList = testDB.getFilterGameRating("10");
        for (int i = 0; i < filterGameList.getModel().getSize(); i++) {
            if (filterGameList.getModel().getElementAt(i).equals(testGameName)) {
                isPresent = true;
                break;
            }
        }

        assertTrue("Game is not present",isPresent);

        isPresent = false;
        for (int i = 0; i < filterGameList.getModel().getSize(); i++) {
            if (filterGameList.getModel().getElementAt(i).equals(testNonGameName)) {
                isPresent = true;
                break;
            }
        }
        assertFalse("Game is present",isPresent);
    }

    @Test
    public void searchGameTest() throws SQLException {

        String expectedGameID = "1002";
        String testGameName = "Tetris 99";
        String result = testGC.searchForGame(testGameName, testDB);

        assertEquals(expectedGameID,result);

    }

    @Test
    public void getGameProfileTest() throws SQLException {
        Game expectedGame = new Game();
        String testGameID = "1002";

        expectedGame.setId(1002);
        expectedGame.setGameName("Tetris 99");
        expectedGame.setGenre("E10");
        expectedGame.setRating(10);

        Game actualGame = testDB.getGameProfile(testGameID);

        assertEquals(expectedGame.getId(), actualGame.getId());
        assertEquals(expectedGame.getName(), actualGame.getName());
        assertEquals(expectedGame.getGenre(), actualGame.getGenre());
        assertEquals(Float.toString(expectedGame.getAverageRating()), Float.toString(actualGame.getAverageRating()));

        // return
    }

    @Test
    public void resetGameTest() throws SQLException {
        boolean isPresent = false;
        String testGameName = "Super Mario Odyssey";
        String testNonGameName = "Tetris 99";
        JList<String> filterGameList = testDB.getFilterGameGenre("E");
        for (int i = 0; i < filterGameList.getModel().getSize(); i++) {
            if (filterGameList.getModel().getElementAt(i).equals(testGameName)) {
                isPresent = true;
                break;
            }
        }

        assertTrue("Game is not present",isPresent);

        isPresent = false;
        for (int i = 0; i < filterGameList.getModel().getSize(); i++) {
            if (filterGameList.getModel().getElementAt(i).equals(testNonGameName)) {
                isPresent = true;
                break;
            }
        }
        assertFalse("Game is present",isPresent);

        filterGameList = testDB.getAllGameName();
        for (int i = 0; i < filterGameList.getModel().getSize(); i++) {
            if (filterGameList.getModel().getElementAt(i).equals(testNonGameName)) {
                isPresent = true;
                break;
            }
        }
        assertTrue("Game is not present",isPresent);

    }

    @Test
    public void purchaseGameTest() throws SQLException {
        String testGameName = "Overcooked 2";
        String testGameID = "1";
        boolean isPresent = false;
        JList<String> userGameLibrary = testDB.getUserLibrary(testGameID);
        for (int i = 0; i < userGameLibrary.getModel().getSize(); i++) {
            if (userGameLibrary.getModel().getElementAt(i).equals(testGameName)) {
                isPresent = true;
                break;
            }
        }
        if (isPresent){
            testDB.deleteGame(testGameName,testGameID);
            isPresent = false;
        }
        assertFalse("Game is already is in list", isPresent);
        testDB.purchaseGame(testGameName,testGameID);
        userGameLibrary = testDB.getUserLibrary(testGameID);
        for (int i = 0; i < userGameLibrary.getModel().getSize(); i++) {
            if (userGameLibrary.getModel().getElementAt(i).equals(testGameName)) {
                isPresent = true;
                break;
            }
        }
        assertTrue("Game is not in library", isPresent);

    }
}