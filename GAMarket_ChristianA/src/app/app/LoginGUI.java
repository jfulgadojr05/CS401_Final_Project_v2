package app.app;

import javax.swing.*;

public class LoginGUI {
    // public LoginGUI {
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();

        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(panel);

        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        JTextField username = new JTextField(20);
        username.setBounds(100,20,165, 25);
        panel.add(username);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        panel.add(passwordLabel);

        JPasswordField password = new JPasswordField();
        password.setBounds(100,50,165,25);
        panel.add(password);

        JButton login = new JButton("Login");
        login.setBounds(10, 80, 80, 25);
        panel.add(login);

        frame.setVisible(true);
    }
}