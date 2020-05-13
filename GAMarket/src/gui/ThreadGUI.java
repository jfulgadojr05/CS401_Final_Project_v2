package gui;
/* Friends List GUI class
	CS 401 - Final Project
	FriendsGUI.java
  By: Christian Magpantay
  Code/Book Reference -
  https://www.youtube.com/watch?v=CqWorn8dR_A&list=PLdmXYkPMWIgCocLY-B4SvpQshQWC7Nc0C&index=5
*/

import javax.swing.*;
import java.awt.*;


public class ThreadGUI extends JFrame {
    private static final long serialVersionUID = 1L;

    public JList<String> getThreadInfo() {
    DefaultListModel<String> model = new DefaultListModel<>();
    JList<String> j_list = new JList<String>(model);
    JPanel panel = new JPanel(new BorderLayout(5, 5));

    JPanel label = new JPanel(new GridLayout(0, 1, 2, 2));
    label.add(new JLabel("Thread title", SwingConstants.RIGHT));
    label.add(new JLabel("Enter post", SwingConstants.RIGHT));
    panel.add(label, BorderLayout.WEST);

    JPanel threadPane = new JPanel(new GridLayout(0, 1, 2, 2));
    JTextField title = new JTextField();
    threadPane.add(title);
    JTextField post = new JTextField();
    threadPane.add(post);
    panel.add(threadPane, BorderLayout.CENTER);

    JOptionPane.showMessageDialog(this, panel, "login", JOptionPane.QUESTION_MESSAGE);
    model.addElement(title.getText());
    model.addElement(post.getText());
    return j_list;
    }

}