package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

    public LoginGUI() {
        this.gm = new GAManager();

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
                if(success) {
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
                try {
                    app.Login.sendRegistration(registerUser);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        recoveryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Please enter your account's username:");
                JOptionPane.showMessageDialog(null, "Verifying ownership...");
                if(app.Login.isDuplicate(username) == true) {
                    String password = JOptionPane.showInputDialog("Please enter a new password:");
                    String confirm = JOptionPane.showInputDialog("Please confirm your new password:");
                    while(!password.equals(confirm)) {
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




    public static void main(String[] args) {
        LoginGUI loginUser = new LoginGUI();
        loginUser.setVisible(true);
        loginUser.setTitle("GAMarket Login");
        loginUser.setBounds(10,10,370,600);
        loginUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginUser.setResizable(false);
        loginUser.setLocationRelativeTo(null);
    }
}
