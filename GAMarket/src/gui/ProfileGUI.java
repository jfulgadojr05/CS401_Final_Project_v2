package gui;

import javax.swing.*;
import java.awt.*;

class ProfileGUI extends JFrame {

    // Components of the Form
    private Container container = getContentPane();


    public ProfileGUI()
    {
        setTitle("User Profile");
        setBounds(300, 90, 900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        container.setLayout(null);

        // display username
        JLabel title = new JLabel("Username");
        title.setFont(new Font("Courier", Font.PLAIN, 30));
        title.setSize(300, 30);
        title.setLocation(100, 30);
        container.add(title);

        // display profile image
        JPanel panel = new JPanel();
        panel.setSize(200,200);
        panel.setLocation(100, 60);
        // picture path
        ImageIcon icon = new ImageIcon("profiledefault.png");
        Image image = icon.getImage();
        Image resized = image.getScaledInstance(200,200, Image.SCALE_SMOOTH);
        JLabel label = new JLabel();
        ImageIcon newIcon = new ImageIcon(resized);
        label.setIcon(newIcon);
        panel.add(label);
        this.getContentPane().add(panel);



        setVisible(true);
    }

    public static void main(String[] args) {
        ProfileGUI p = new ProfileGUI();
    }
}