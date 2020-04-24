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


public class FriendsGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    Container c;
    JMenuBar menuBarChat;
    JMenu username;
    JMenu addFriend;
    JMenu settings;
    JTabbedPane chat;
    JList<String> online;
    JList<String> allFriends;
    ActionListener toAddFriend;
    JMenuItem viewProfile;
    JMenuItem editProfile;
    JMenuItem editFriends;

    String[] friendsMockListOnline = {
            "TheToadKing", "Pokimane", "Maximilian_DOOD"
    };
    String[] friendsMockListAll = {
            "Practice", "richMag", "TheToadKing",
            "DisguisedToast", "PitaShwang", "LuluLuvely",
            "Pokimane", "Maximilian_DOOD"
    };

    public FriendsGUI() {
        super("Friends List"); // title
        setSize(300,500);
        Panel c = new Panel();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        setContentPane(c);
        // create menu for chat
        MenuBar();
        ChatTabs();
        // settings
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void MenuBar() {
        menuBarChat = new JMenuBar();
        // username menu
        username = new JMenu("chrisTaro");
        username.setMnemonic(KeyEvent.VK_A);
        viewProfile = new JMenuItem("View Profile");
        editProfile = new JMenuItem("Edit Profile");
        username.add(viewProfile);
        username.add(editProfile);
        menuBarChat.add(username);
        // friends
        addFriend = new JMenu("Add Friend+");
        addFriend.setMnemonic(KeyEvent.VK_A);
        toAddFriend = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addFriend();
            }
        };
        addFriend.addActionListener(toAddFriend);
        menuBarChat.add(addFriend);
        //settings
        settings = new JMenu("Settings");
        settings.setMnemonic(KeyEvent.VK_A);
        editFriends = new JMenuItem("Edit Friends List");
        settings.add(editFriends);
        menuBarChat.add(settings);
        setJMenuBar(menuBarChat);
    }

    public void addFriend() {
    }

    public void ChatTabs() {
        chat = new JTabbedPane();
        online = new JList<String>(friendsMockListOnline);
        allFriends = new JList<String>(friendsMockListAll);

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

        chat.addTab("Online", null, online, "Friends that are online");
        chat.addTab("All", null, allFriends, "All Friends");
        getContentPane().add(chat, BorderLayout.CENTER);
    }
}