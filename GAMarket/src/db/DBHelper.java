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
    String url = "jdbc:sqlite:S:/Desktop/Software Engineering/CS401_GAMarket_Final/GAMarket/src/db/GAMarket.db";

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

        // create all other tables here
        // this creates games table
        sql = "create table if not exists game ("
                + "id integer primary key,"
                + "game_id int not null,"
                + "game_name varchar(500) not null,"
                + "game_genre varchar(20),"
                + "game_rating double,"
                + "fk_collection int"
                + ")";

        stmt.execute(sql);

        sql = "create table if not exists User " +
                "(" +
                "    id       integer" +
                "        constraint User_pk" +
                "            primary key autoincrement," +
                "    username varchar(255)," +
                "    password varchar(255)," +
                "    type     varchar(20)" +
                ");" +
                "" +
                "create unique index User_username_uindex" +
                "    on User (username);";

        stmt.execute(sql);

    }

    // all functions below are for 'friends' table
    public void addFriend(String username) throws SQLException  {
        String sql = "insert into friends(username) values(?)";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.executeUpdate();
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
    }
    public void deleteFriend(String username) throws SQLException {
        String sql = "delete from friends where username = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // set the corresponding param
        pstmt.setString(1, username);
        // execute the delete statement
        pstmt.executeUpdate();
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
        return j_list;
    }

    public JList<String> getAllGames() throws SQLException{
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> storeMenuItems;
        String sql = "select game_id, game_name, game_genre, game_rating from game";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            String tempItem = rs.getString("game_id") + "," +
                    rs.getString("game_name") + ", " +
                    rs.getString("game_genre") + ", " +
                    rs.getString("game_rating");
            storeModel.addElement(tempItem);
        }
        storeMenuItems = new JList<>(storeModel);
        return storeMenuItems;
    }

    public JList<String> getAllGameName() throws SQLException {
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> storeMenuItems;
        String sql = "select game_name from game";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            storeModel.addElement(rs.getString("game_name"));
        }
        storeMenuItems = new JList<>(storeModel);
        return storeMenuItems;
    }

    public Game[] getAllGameObjects() throws SQLException {
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> storeMenuItems;
        int counter = 0;
        Game[] tempArray;
        String sql = "select game_id, game_name, game_genre, game_rating from game";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
        while (rs.next()){
            counter++;
        }

        tempArray = new Game[counter];
        counter = 0;
        rs = stmt.executeQuery(sql);
        while(rs.next()){
            Game temp = new Game();
            temp.setId(Integer.parseInt(rs.getString("game_id")));
            temp.setGameName(rs.getString("game_name"));
            temp.setGenre(rs.getString("game_genre"));
            temp.setRating(Float.parseFloat(rs.getString("game_rating")));
            tempArray[counter] = temp;
            counter++;
        }
        return tempArray;
    }

    public Game getGameProfile(String gameID) throws SQLException {
        Game tempGame = new Game();
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_id = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,gameID);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            tempGame.setId(Integer.parseInt(rs.getString(1)));
            tempGame.setGameName(rs.getString(2));
            tempGame.setGenre(rs.getString(3));
            tempGame.setRating(Float.parseFloat(rs.getString(4)));
        }
        rs.close();

        return tempGame;
    }

    public Game getGameProfileName(String gameName) throws SQLException {
        Game tempGame = new Game();
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_name = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,gameName);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            tempGame.setId(Integer.parseInt(rs.getString("game_id")));
            tempGame.setGameName(rs.getString("game_name"));
            tempGame.setGenre(rs.getString("game_genre"));
            tempGame.setRating(Float.parseFloat(rs.getString("game_rating")));
        }
        rs.close();

        return tempGame;
    }

    public JList<String> getFilterGameGenre(String aGenre) throws SQLException{
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> filterItems;
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_genre = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,aGenre);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            storeModel.addElement(rs.getString("game_name"));
        }
        filterItems = new JList<>(storeModel);
        return filterItems;
    }

    public JList<String> getFilterGameRating(String aRating) throws SQLException {
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        JList<String> filterItems;
        String sql = "select game_id, game_name, game_genre, game_rating from game where game_rating = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,aRating);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            storeModel.addElement(rs.getString("game_name"));
        }
        filterItems = new JList<>(storeModel);
        return filterItems;
    }

    // login gui functions

    public boolean isDuplciate(String username) throws SQLException {
        boolean duplicate = false;
        String sql = "select username from User where username = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            duplicate = true;
        }
        rs.close();
        return duplicate;
    }

    public boolean sendLogin(String username, String password) throws SQLException {
        boolean success = false;
        String sql = "select username, password from User where username = ? and password = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        if(rs.next()) {
            success = true;
        }
        rs.close();
        return success;
    }

    public String sendRegistration(String registerUser, String registerPass, String registerType) throws SQLException {
        String sql = "insert into User(username, password, type) values(?,?,?)";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, registerUser);
        pstmt.setString(2, registerPass);
        pstmt.setString(3, registerType);
        pstmt.executeUpdate();

        return "Account creation successful with the following fields:\n"
                + "Username: " + registerUser
                + "\nPassword: " + registerPass
                + "\nAccount Type: " + registerType
                + "\nPlease login to your account.";
    }

    public void loginRecovery(String username, String password) throws SQLException {
        String sql = "update User set password = ? where username = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setString(2, username);
        pstmt.executeUpdate();
    }

}