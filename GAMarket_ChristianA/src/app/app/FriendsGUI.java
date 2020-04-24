package app.app;


import javax.swing.*;
import java.awt.*;

public class FriendsGUI extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    Container c;
    JLabel username;
    JLabel friends;
    private String[] friendsList; // change to Users class

    public FriendsGUI() {
        super("Friends List"); // title
        setSize(300,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        c = getContentPane();

        username = new JLabel("chrisTaro");
        username.setFont(new Font("Arial", Font.PLAIN, 30));
        username.setSize(300, 30);
        username.setLocation(100, 30);
        c.add(username);

        friends = new JLabel("Friends");
        username.setFont(new Font("Arial", Font.PLAIN, 30));
        username.setSize(300, 30);
        username.setLocation(100, 30);
        c.add(username);
    }
}