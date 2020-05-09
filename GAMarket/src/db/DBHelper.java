package db;

import java.sql.*;
import javax.swing.*;


/* Code/referenced
    https://www.sqlitetutorial.net/sqlite-java/create-database/
    http://borg.csueastbay.edu/~bhecker/CS-453/Examples/Database%20Example.txt
 */

/**
 *
 * @author sqlitetutorial.net
 */
public class DBHelper {
    String url = "jdbc:sqlite:C:\\Users\\chris\\CS401_GAMarket_Final\\GAMarket\\src\\db\\GAMarket.db";

    public void createNewDatabase() throws SQLException {
        DriverManager.getConnection(url);
        System.out.println("A new database has been created.");
    }

    public void createFriendsTable() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS friends ("
                + "	id integer PRIMARY KEY,"
                + "	username text NOT NULL"
                + ")";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addFriend(String username) throws SQLException  {
        String sql = "insert into friends(username) values(?)";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.executeUpdate();
    }

    public void updateFriend(int id, String username) throws SQLException {
        String sql = "update friends set username = ? , "
                + "where id = ?";
        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
        // set the corresponding param
        pstmt.setString(1, username);
        pstmt.setInt(2, id);
        // update 
        pstmt.executeUpdate();
    }

    public void deleteFriend(int id) throws SQLException {
        String sql = "delete from friends where id = ?";

        Connection conn = DriverManager.getConnection(url);
        PreparedStatement pstmt = conn.prepareStatement(sql);
            // set the corresponding param
        pstmt.setInt(1, id);
            // execute the delete statement
        pstmt.executeUpdate();
    }

    public JList<String> getAllFriends() throws SQLException {
        DefaultListModel<String> model = new DefaultListModel<>();
        JList<String> j_list = new JList<String>(model);

        String sql = "select id, username from friends";
        
        Connection conn = DriverManager.getConnection(url);
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);
            // loop through the result set
        while (rs.next()) {
            model.addElement(rs.getString("username"));
        }
        return j_list;
    }

}