package app;

import db.DBHelper;

import java.sql.SQLException;

public class Login {
    private String username;
    private String password;
    private String accountType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public static boolean isDuplicate(String username, DBHelper db) throws SQLException {
        return db.isDuplciate(username);
    }

    public static boolean sendLogin(String usernameField, String passwordField, DBHelper db) throws SQLException {
        return db.sendLogin(usernameField, passwordField);
    }

    public static String sendRegistration(String registerUser, String registerPass, String registerType, DBHelper db) throws SQLException {
        return db.sendRegistration(registerUser, registerPass, registerType);
    }

    public static void loginRecovery(String username, String password, DBHelper db) throws SQLException {
        db.loginRecovery(username, password);
    }
}