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
import java.awt.event.*;
import java.sql.SQLException;
import db.DBHelper;

public class FriendsGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    Container c;
    JMenuBar menuBarChat;
    JMenu username;
    JMenu editFriends;
    JMenu settings;
    JMenuItem addFriend;
    JMenuItem removeFriend;
    JTabbedPane chat;
    JList<String> online;
    JList<String> allFriends;
    ActionListener toAddFriend;
    ActionListener toRemoveFriend;
    JMenuItem viewProfile;
    JMenuItem editProfile;

    String[] friendsMockListOnline = { "TheToadKing", "Pokimane", "Maximilian_DOOD" };
    String[] emptyList = { "Empty" };

    public FriendsGUI(DBHelper mydb) throws SQLException {
        super("Friends List"); // title
        setSize(300, 500);
        Panel c = new Panel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setContentPane(c);
        // create menu for chat
        createFriendsGUI(mydb);
        // settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void createFriendsGUI(DBHelper mydb) throws SQLException {
        menuBarChat = new JMenuBar();
        // username menu
        username = new JMenu("chrisTaro");
        username.setMnemonic(KeyEvent.VK_A);
        // menu items for user's name
        viewProfile = new JMenuItem("View Profile");
        editProfile = new JMenuItem("Edit Profile");
        username.add(viewProfile);
        username.add(editProfile);
        menuBarChat.add(username);

        // edit friends menu items
        editFriends = new JMenu("Manage");
        editFriends.setMnemonic(KeyEvent.VK_A);
        editFriends.getAccessibleContext().setAccessibleDescription("Edit Friends");
        addFriend = new JMenuItem("Add Friend+");
        removeFriend = new JMenuItem("Remove Friend-");

        toAddFriend = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = 
                        JOptionPane.showInputDialog("Enter Friends Username to Add");
                if (username == null) {
                    return;
                }

                // remove empty pane to reset
                chat.remove(online);
                chat.remove(allFriends);
                chat.revalidate();
                chat.repaint();

                try {
                    mydb.addFriend(username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    allFriends = mydb.getAllFriends();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                chat.addTab("Online", online);
                chat.addTab("All", allFriends);
                chat.revalidate();
                chat.repaint();
            }
        };

        toRemoveFriend = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                String username = 
                        JOptionPane.showInputDialog("Enter Friends Username to Delete");
                if (username == null) {
                    return;
                }

                // remove empty pane to reset
                chat.remove(online);
                chat.remove(allFriends);
                chat.revalidate();
                chat.repaint();

                // read pane
                try {
                    mydb.deleteFriend(username);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                
                try {
                    allFriends = mydb.getAllFriends();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                chat.addTab("Online", online);
                chat.addTab("All", allFriends);
                chat.revalidate();
                chat.repaint();
            }
        };

        addFriend.addActionListener(toAddFriend);
        removeFriend.addActionListener(toRemoveFriend);
        editFriends.add(addFriend);
        editFriends.add(removeFriend);

        menuBarChat.add(editFriends);
        setJMenuBar(menuBarChat);

        // chat related
        chat = new JTabbedPane();
        online = new JList<String>(friendsMockListOnline);
        allFriends = mydb.getAllFriends();

        // responds when user double clicks username
        // so that they can instantly message that user
        // that is online
        online.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2) {
                    String user = online.getSelectedValue();
                    MessageGUI messageGUI = new MessageGUI();
                    JFrame messageFrame = new JFrame("Messaging " + user);
                    messageFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    messageFrame.setSize(400,400);
                    messageFrame.getContentPane().add(messageGUI, BorderLayout.CENTER);
                    messageFrame.setVisible(true);
                }
            }
        });

        chat.addTab("Online", online);
        chat.addTab("All", allFriends);
        getContentPane().add(chat, BorderLayout.CENTER);
    }



}