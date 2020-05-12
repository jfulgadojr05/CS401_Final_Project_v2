package test;

import db.DBHelper;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    DBHelper testDB = new DBHelper();

    @Test
    public void sendRegistration() throws SQLException {
        // Creates a new account
        String testUser = "test123";
        String testPassword = "pass123";
        String testType = "player";
        testDB.sendRegistration(testUser,testPassword,testType);
        boolean loginSuccess = testDB.sendLogin(testUser, testPassword);
        assertTrue("Registration successful.", loginSuccess);

    }

    @Test
    public void sendLogin() throws SQLException {
        // Check if logging in brings up GAManager
        // Verifies if username and password exists in the DB
        String testUser = "test123";
        String testPassword = "pass123";
        boolean loginSuccess = testDB.sendLogin(testUser, testPassword);
        assertTrue("Login successful.", loginSuccess);

    }

    @Test
    public void isDuplicate() throws SQLException {
        // Checks if username exists in DB
        String testUser = "hello";
        boolean result = testDB.isDuplciate(testUser);
        assertTrue("Duplicate exists in DB", result);
    }

    @org.junit.Test
    public void loginRecovery() throws SQLException {
        // Updates an existing user's password if forgotten
        String testUser = "test123";
        String newPassword = "newpassword123";
        testDB.loginRecovery(testUser, newPassword);
        boolean loginSuccess = testDB.sendLogin(testUser, newPassword);
        assertTrue("User can now log in with new password", loginSuccess);
    }
}