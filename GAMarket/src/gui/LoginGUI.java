package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

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

    public LoginGUI() {
        this.gm = new GAManager();

        container.setLayout(null);
        usernameLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        usernameField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        registerButton.setBounds(200, 300, 100, 30);

        container.add(usernameLabel);
        container.add(passwordLabel);
        container.add(usernameField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    doRegister();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

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

    private void doLogin() {
        File accounts = new File("accountsystem.txt");
        try {
            Scanner read = new Scanner(accounts);
            read.useDelimiter(":|\n");
            boolean success = false;
            while(read.hasNext()) {
                String u = read.next();
                String p = read.next();
                if(u.trim().equals(usernameField.getText().trim()) && p.trim().equals(passwordField.getText().trim())) {
                    success = true;
                    break;
                }
            }
            if(success) {
                dispose();
                gm.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Username or Password.");
            }
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "accountsystem.txt not found.");
        }
    }

    private void doRegister() throws IOException {
        String registerUser = JOptionPane.showInputDialog("Please enter your desired username:");
        File accounts = new File("accountsystem.txt");
        try {
            Scanner read = new Scanner(accounts);
            read.useDelimiter(":|\n");
            boolean duplicate = false;
            while (read.hasNext()) {
                    String u = read.next();
                    if (u.trim().equals(registerUser.trim())) {
                        duplicate = true;
                        break;
                    }
            }
            if(duplicate) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please choose a different username.");
            } else {
                String registerPass = JOptionPane.showInputDialog("Please enter your desired password:");
                String confirmPass = JOptionPane.showInputDialog("Please confirm your password:");
                while(!registerPass.equals(confirmPass)) {
                    registerPass = JOptionPane.showInputDialog("Invalid password. Please enter your desired password:");
                    confirmPass = JOptionPane.showInputDialog("Please confirm your password:");
                }
                try {
                    Files.write(Paths.get("accountsystem.txt"), ("\n" + registerUser + ":" + registerPass).getBytes(), StandardOpenOption.APPEND);
                } catch (IOException e) {

                }
                JOptionPane.showMessageDialog(null, "Account creation successful. Please login.");
            }
        }
        catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "accountsystem.txt not found.");
        }
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
