package db;

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

    // add all other functions needed for db

}