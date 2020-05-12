package app;

import db.DBHelper;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {
    private String username;
    private String password;
    private String accountType;

    private static boolean success;
    private static File accounts = new File("accountsystem.txt");

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        try {
            Files.write(Paths.get("accountsystem.txt"), ("\n" + this.username).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //
        }
    }

    public static String getPassword(String username) {
        String password = "";
        try {
            Scanner read = new Scanner(accounts);
            read.useDelimiter(":");
            while (read.hasNext()) {
                String u = read.next();
                String p = read.next();
                String t = read.nextLine();
                if (u.trim().equals(username)) {
                    password = p;
                    break;
                }
            }
            read.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "accountsystem.txt not found.");
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        try {
            Files.write(Paths.get("accountsystem.txt"), (":" + this.password).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //
        }
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
        try {
            Files.write(Paths.get("accountsystem.txt"), (":" + this.accountType).getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            //
        }
    }


    public static boolean isDuplicate(String username) {
        boolean duplicate = false;
        try {
            Scanner read = new Scanner(accounts).useDelimiter(":");
            while (read.hasNext()) {
                String u = read.next();
                read.nextLine();
                if (u.equals(username)) {
                    duplicate = true;
                    break;
                }
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return duplicate;
    }

    public static boolean sendLogin(String usernameField, String passwordField, DBHelper db) throws SQLException {
        return true;
    }

    public static String sendRegistration(String registerUser, String registerPass, String registerType) {
        Login log = new Login();
        log.setUsername(registerUser);
        log.setPassword(registerPass);
        if(registerType.equals("player")) {
            log.setAccountType("player");
        } else {
            log.setAccountType("developer");
        }
        return "Account creation successful with the following fields:\n" +
                "Username: " + registerUser +
                "\nPassword: " + registerPass +
                "\nAccount Type: " + registerType +
                "\nPlease login to your account.";
    }

    public static void loginRecovery(String username, String password) {
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("accountsystem.txt"));
            StringBuffer input = new StringBuffer();
            String line;

            while ((line = file.readLine()) != null) {
                input.append(line);
                input.append('\n');
            }
            file.close();
            String s = input.toString();

            // logic to replace lines in the string (could use regex here to be generic)
            if (username.equals(username)) {
                s = s.replace(username + ":" + getPassword(username), username + ":" + password);

                // write the new string with the replaced line OVER the same file
                FileOutputStream fileOut = new FileOutputStream("accountsystem.txt");
                fileOut.write(s.getBytes());
                fileOut.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}