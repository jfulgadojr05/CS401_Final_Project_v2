package test;

import app.Game;
import db.DBHelper;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StoreGUITest {

    DBHelper testDB = new DBHelper();
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
    public void filterGameGenreTest(){
        // return
    }

    @Test
    public void filterGameRatingTest(){
        // return
    }

    @Test
    public void searchGameTest(){
        // return
    }

    @Test
    public void getGameProfileTest(){
        // return
    }

    @Test
    public void resetGameTest(){
        // return
    }

    @Test
    public void purchaseGameTest(){

    }
}