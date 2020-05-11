package gui;

import db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginGUI extends JFrame {
    private final GAManager gm;
    Container container = getContentPane();
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField usernameField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPassword = new JCheckBox("Show Password");
    JButton loginButton = new JButton("Login");
    JButton registerButton = new JButton("Register");
    JButton recoveryButton = new JButton("Account Recovery");

    public LoginGUI() throws SQLException {
        DBHelper dbh = new DBHelper();
        dbh.createNewDatabase();
        dbh.createAllTables();
        this.gm = new GAManager(dbh);

        // setting up gui bounds
        container.setLayout(null);
        usernameLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        usernameField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        registerButton.setBounds(200, 300, 100, 30);
        recoveryButton.setBounds(75, 350, 200, 30);

        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(usernameField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(registerButton);
        container.add(recoveryButton);

        // login button functionality
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = app.Login.sendLogin(usernameField.getText().trim(), passwordField.getText().trim());
                if (success) {
                    // if login successful, open GAMarket
                    dispose();
                    gm.setVisible(true);
                } else {
                    // invalid username/password if unsuccessful
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
                }
            }
        });

        // register button functionality
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // prompt username
                String registerUser = JOptionPane.showInputDialog("Please enter your desired username:");

                // look if duplicate username exists
                boolean duplicate = app.Login.isDuplicate(registerUser);
                if (duplicate) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.");
                    // proceed with password creation if unique username
                } else {
                    String registerPass = JOptionPane.showInputDialog("Please enter your desired password:");
                    String confirmPass = JOptionPane.showInputDialog("Please confirm your password:");
                    while (!registerPass.equals(confirmPass)) {
                        registerPass = JOptionPane.showInputDialog("Invalid password. Please enter your desired password:");
                        confirmPass = JOptionPane.showInputDialog("Please confirm your password:");
                    }

                    // prompt player or dev
                    String[] command = {"Player", "Developer"};
                    int choice;
                    do {
                        choice = JOptionPane.showOptionDialog(null,
                                "Are you a new player or new developer?",
                                "Register",
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                command,
                                command[command.length - 1]);
                        switch (choice) {
                            case 0:
                                String result1 = app.Login.sendRegistration(registerUser, confirmPass, "player" );
                                JOptionPane.showMessageDialog(null, result1);
                                break;
                            case 1:
                                String result2 = app.Login.sendRegistration(registerUser, confirmPass, "developer");
                                JOptionPane.showMessageDialog(null, result2);
                                break;
                            default:
                                break;
                        }
                    } while (choice > 1);
                }
            }
        });


        recoveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Please enter your account's username:");
                JOptionPane.showMessageDialog(null, "Verifying ownership...");
                if (app.Login.isDuplicate(username) == true) {
                    String password = JOptionPane.showInputDialog("Please enter a new password:");
                    String confirm = JOptionPane.showInputDialog("Please confirm your new password:");
                    while (!password.equals(confirm)) {
                        password = JOptionPane.showInputDialog("Passwords did not match. Please enter a new password:");
                        confirm = JOptionPane.showInputDialog("Please confirm your new password:");
                    }
                    app.Login.loginRecovery(username, confirm);
                    JOptionPane.showMessageDialog(null, "Password successfully updated. Please log in.");

                } else {
                    JOptionPane.showMessageDialog(null, "No such account exists.");
                }

            }
        });

        // check box to show password
        showPassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == showPassword) {
                    if (showPassword.isSelected()) {
                        passwordField.setEchoChar((char) 0);
                    } else {
                        passwordField.setEchoChar('*');
                    }
                }
            }
        });
    }



}