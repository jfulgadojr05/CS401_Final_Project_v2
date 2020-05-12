package db;

import app.Game;

import javax.swing.*;
import java.sql.*;


/*
    DBHelper class
	CS 401 - Final Project
	DBHelper.java
    By: Christian Taro Magpantay
    Code/Book Reference -
    https://www.sqlitetutorial.net/sqlite-java/
    http://borg.csueastbay.edu/~bhecker/CS-453/Examples/Database%20Example.txt
 */

public class DBHelper {

    // connection string
    String url = "jdbc:sqlite:C:/Users/jerom/Java_Projects/CS401_GAMarket_Final/GAMarket/src/db/GAMarket.db";

    // creates the GAMarket.db
    public void createNewDatabase() throws SQLException {
        DriverManager.getConnection(url);
        System.out.println("Database connected");
    }

    // add any new tables needed in this function
    public void createAllTables() throws SQLException {
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();

        // SQL statement for creating any new table
        String sql = "create table if not exists friends ("
                + "	id integer primary key," // needed
                + "	username text not null"
                + ")";

        stmt.execute(sql);

        // this creates threads table
        sql = "create table if not exists threads ("
                + "	id integer primary key,"
                + "	username text not null"
                + ")";
        stmt.execute(sql);

        // creates game collection user table
        sql = "create table if not exists game_collection_user" +
                "(" +
                "    collection_id integer" +
                "        constraint game_collection_pk" +
                "            primary key autoincrement," +
                "    fk_user       int," +
                "    fk_game       int" +
                ")";

        stmt.execute(sql);

        // this creates games table
        sql = "create table if not exists game" +
                "(" +
                "    game_id      int          not null" +
                "        constraint game_pk" +
                "            primary key," +
                "    game_name    varchar(500) not null," +
                "    game_genre   varchar(20)," +
                "    game_rating  double," +
                "    fk_developer int" +
                ");" +
                "" +
                "create unique index game_game_id_uindex" +
                "    on game (game_id);";
        stmt.execute(sql);



         //create user tables
        sql = "create table if not exists user" +
                "(" +
                "    id        integer" +
                "        constraint user_pk" +
                "            primary key autoincrement," +
                "    username  varchar(255) not null," +
                "    password  varchar(255) not null," +
                "    user_type varchar(255)" +
                ");" +
                "" +
                "create unique index user_username_uindex" +
                "    on user (username);" +
                "";

        stmt.execute(sql);

        stmt.close();
        conn.close();


    }

    // all functions below are for 'friends' table
    public void addFriend(String username) throws SQLException  {
        String sql = "insert into friends(username) values(?)";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    public void updateFriend(int id, String username) throws SQLException {
        String sql = "update friends set username = ? , where id = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // set the corresponding param
        pstmt.setString(1, username);
        pstmt.setInt(2, id);
        // update
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    public void deleteFriend(String username) throws SQLException {
        String sql = "delete from friends where username = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // set the corresponding param
        pstmt.setString(1, username);
        // execute the delete statement
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }
    public JList<String> getAllFriends() throws SQLException {
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> j_list = new JList<String>(model);
        String sql = "select id, username from friends order by username asc";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        // loop through the result set
        while (rs.next()) {
            model.addElement(rs.getString("username"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return j_list;
    }


    public JList<String> getAllThreads() throws SQLException {
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> j_list = new JList<String>(model);
        String sql = "select id, username from friends"
                + "order by first asc";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        // loop through the result set
        while (rs.next()) {
            model.addElement(rs.getString("username"));
        }
        rs.close();
        stmt.close();
        conn.close();
        return j_list;

    }


    public JList<String> getAllGameName() throws SQLException {
        // Return JList for all game names
        // local variables
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> storeMenuItems;

        // sql statement and collection
        String sql = "select * from game";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();

        // Result set to query data
        ResultSet rs    = stmt.executeQuery(sql);

        // Add the elements to game model
        while (rs.next()){
            storeModel.addElement(rs.getString("game_name"));
        }

        // Adding model to jlist item
        storeMenuItems = new JList<>(storeModel);

        // close statements and return jlist
        rs.close();
        stmt.close();
        conn.close();
        return storeMenuItems;
    }

    public Game[] getAllGameObjects() throws SQLException {
        // Return an array of all the game objects from database

        // local variables
        int counter = 0;
        Game[] tempArray;

        // sql statement and connection
        String sql = "select game_id, game_name, game_genre, game_rating from game";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();

        // Result set to query data
        ResultSet rs    = stmt.executeQuery(sql);

        // while statement to get a count of record set
        while (rs.next()){
            counter++;
        }

        // Creating game temp array and reset counter
        tempArray = new Game[counter];
        counter = 0;

        // Result set to query the data
        rs = stmt.executeQuery(sql);

        // While statement to go through each record in set, make a game object,
        // and add to game array
        while(rs.next()){
            Game temp = new Game();
            temp.setId(Integer.parseInt(rs.getString("game_id")));
            temp.setGameName(rs.getString("game_name"));
            temp.setGenre(rs.getString("game_genre"));
            temp.setRating(Float.parseFloat(rs.getString("game_rating")));
            tempArray[counter] = temp;
            counter++;
        }

        // Close database and return game array
        rs.close();
        stmt.close();
        conn.close();
        return tempArray;
    }

    public Game getGameProfile(String gameID) throws SQLException {
        // Return a single game object based off ID

        // Local variable
        Game tempGame = new Game();

        // sql statement and connection
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_id = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // Replacing prepared statement with parameter
        pstmt.setString(1,gameID);

        // Result set to query data
        ResultSet rs = pstmt.executeQuery();

        // Set the game variable with the result set columns
        tempGame.setId(Integer.parseInt(rs.getString(1)));
        tempGame.setGameName(rs.getString(2));
        tempGame.setGenre(rs.getString(3));
        tempGame.setRating(Float.parseFloat(rs.getString(4)));

        // Closing statements and return game object
        rs.close();
        pstmt.close();
        conn.close();
        return tempGame;
    }

    public Game getGameProfileName(String gameName) throws SQLException {
        // Return a single game object based off ID

        // Local variable
        Game tempGame = new Game();

        // sql statement and connection
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_name = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // Replacing prepared statement with parameter
        pstmt.setString(1,gameName);

        // Result set to query data
        ResultSet rs = pstmt.executeQuery();

        // Set the game variable with the result set columns
        tempGame.setId(Integer.parseInt(rs.getString("game_id")));
        tempGame.setGameName(rs.getString("game_name"));
        tempGame.setGenre(rs.getString("game_genre"));
        tempGame.setRating(Float.parseFloat(rs.getString("game_rating")));

        // Closing statements and return game object
        rs.close();
        pstmt.close();
        conn.close();
        return tempGame;
    }

    public void purchaseGame(String gameName, String userID) throws SQLException {
        // Insert game to game_collection_user table

        // Local variables
        boolean isDuplicate = false;
        JList<String> userLibrary = getUserLibrary(userID);

        // Check if the game is already in library from jlist
        for (int i = 0; i < userLibrary.getModel().getSize(); i++){
            if (userLibrary.getModel().getElementAt(i).equals(gameName)){
                isDuplicate = true;
                break;
            }
        }

        // if-else if the game is already in library or not
        if (!isDuplicate){
            // Setting up game variable
            Game tempGame  = getGameProfileName(gameName);

            // Sql statement and connection
            String collectSQL = "insert into game_collection_user(fk_user, fk_game) values(?,?)";
            Connection collect_conn = DriverManager.getConnection(url);
            PreparedStatement collect_pstmt = collect_conn.prepareStatement(collectSQL);

            // Adding parameters to prepared statement
            collect_pstmt.setString(1, userID);
            collect_pstmt.setString(2,Integer.toString(tempGame.getId()));

            // Execute statement
            collect_pstmt.executeUpdate();

            // Close database
            collect_pstmt.close();
            collect_conn.close();
        }
        else {
            // JOptionMessage that game already exists
            JOptionPane.showMessageDialog(null, "Game already exists in Library");
        }
    }

    public void deleteGame(String gameName, String userID) throws SQLException {
        // Deletes a record from game_collection_user from user collection

        // local variable
        boolean isPresent = false;
        JList<String> userLibrary = getUserLibrary(userID);

        // Check if the game is in library
        for (int i = 0; i < userLibrary.getModel().getSize(); i++){
            if (userLibrary.getModel().getElementAt(i).equals(gameName)){
                isPresent = true;
                break;
            }
        }

        // if-else if the game is in library for deletion
        if (isPresent){
            // Setting up game variable
            Game tempGame  = getGameProfileName(gameName);

            // Sql statement and connection
            String collectSQL = "delete from game_collection_user where fk_user = ? and fk_game = ?";
            Connection collect_conn = DriverManager.getConnection(url);
            PreparedStatement collect_pstmt = collect_conn.prepareStatement(collectSQL);

            // Adding parameters to prepared statement
            collect_pstmt.setString(1, userID);
            collect_pstmt.setString(2,Integer.toString(tempGame.getId()));

            // Execute statement
            collect_pstmt.executeUpdate();

            // Close database
            collect_pstmt.close();
            collect_conn.close();
        }
        else {
            // joption message dialog to show game doesn't exist
            JOptionPane.showMessageDialog(null, "Game does not exist");
        }


    }

    public JList<String> getFilterGameGenre(String aGenre) throws SQLException{
        // Return jlist of all the games based off genre

        // Local variables
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> filterItems;

        // sql statements and connection
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_genre = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // Adding parameters to prepared statement
        pstmt.setString(1,aGenre);

        // Result set to query data
        ResultSet rs = pstmt.executeQuery();

        // while statement to loop through dataset
        // add strings to list model
        while (rs.next()){
            storeModel.addElement(rs.getString("game_name"));
        }

        // close database
        rs.close();
        pstmt.close();
        conn.close();

        // set model to jlist items and return jlist
        filterItems = new JList<>(storeModel);
        return filterItems;
    }

    public JList<String> getFilterGameRating(String aRating) throws SQLException {
        // Return jlist of all the games based off rating

        // Local variables
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> filterItems;

        // sql statements and connection
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_rating = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);

        // Adding parameters to prepared statement
        pstmt.setString(1,aRating);

        // Result set to query data
        ResultSet rs = pstmt.executeQuery();

        // while statement to loop through dataset
        // add strings to list model
        while (rs.next()){
            storeModel.addElement(rs.getString("game_name"));
        }

        // close database
        rs.close();
        pstmt.close();
        conn.close();

        // set model to jlist items and return jlist
        filterItems = new JList<>(storeModel);
        return filterItems;
    }

    public JList<String> getUserLibrary(String userID) throws SQLException {
        // Return jlist of user own collection library

        // Local variables
        DefaultListModel<String> libraryModel = new DefaultListModel<>();
        JList<String> libraryItems;

        // sql statements and connection
        String sql = "select user.id, game.game_name from ((game_collection_user " +
                "inner join game " +
                "on game_collection_user.fk_game = game.game_id)" +
                "inner join user " +
                "on game_collection_user.fk_user = user.id)" +
                "where game_collection_user.fk_user = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt  = conn.prepareStatement(sql);

        // Adding parameters to prepared statement
        pstmt.setString(1, userID);

        // Result set to query data
        ResultSet rs    = pstmt.executeQuery();

        // while statement to loop through dataset
        // add strings to list model
        while (rs.next()){
            libraryModel.addElement(rs.getString("game_name"));
        }

        // close database
        rs.close();
        pstmt.close();
        conn.close();

        // set model to jlist items and return jlist
        libraryItems = new JList<>(libraryModel);
        return libraryItems;
    }

    // login gui functions

    public boolean isDuplciate(String username) throws SQLException {
        boolean duplicate = false;
        String sql = "select username from user where username = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            duplicate = true;
        }
        rs.close();
        pstmt.close();
        conn.close();
        return duplicate;
    }

    public boolean sendLogin(String username, String password) throws SQLException {
        boolean success = false;
        String sql = "select username, password from user where username = ? and password = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            success = true;
        }
        rs.close();
        pstmt.close();
        conn.close();
        return success;
    }

    public String sendRegistration(String registerUser, String registerPass, String registerType) throws SQLException {
        String sql = "insert into user(username, password, user_type) values(?,?,?)";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, registerUser);
        pstmt.setString(2, registerPass);
        pstmt.setString(3, registerType);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
        return "Account creation successful with the following fields:\n"
                + "Username: " + registerUser
                + "\nPassword: " + registerPass
                + "\nAccount Type: " + registerType
                + "\nPlease login to your account.";
    }

    public void loginRecovery(String username, String password) throws SQLException {
        String sql = "update user set password = ? where username = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        pstmt.executeUpdate();
        pstmt.close();
        conn.close();
    }

    // Getting user ID
    public String getUserID(String username, String password) throws SQLException {
        String userID;
        String sql = "select * from user where username = ? and password = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        userID = rs.getString("id");
        rs.close();
        pstmt.close();
        conn.close();
        return userID;

    }

    public String getUsername(String userID) throws SQLException {
        String username;
        String sql = "select * from user where id = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userID);
        ResultSet rs = pstmt.executeQuery();
        username = rs.getString("username");
        rs.close();
        pstmt.close();
        conn.close();
        return username;

    }


}