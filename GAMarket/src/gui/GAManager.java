package gui;

import app.GameCollection;
import app.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GAManager extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = 2949039844571509694L;
    JMenuBar menuBar;
    JMenu GAMarket;
    JMenu friends;
    JMenu settings;
    JMenuItem viewFriends;
    JMenuItem menuItem;
    JTabbedPane config;
    Store storePane;
    JLabel libraryPane;
    JLabel forumPane;
    JLabel friendsPane;
    JLabel settingsPane;
    JPanel configPane;
    JTextArea question;
    ActionListener openFriendsList;
    private GameCollection storeGC;

/*  gui.GAManager constructor
        set title
        set size
        set close operation
        create menubar
            GAMarket menu
            friends menu
            settings menu
        create tabs
            store
            library
            forum           */
    public GAManager() {
        super("GAMarket"); // title
        setSize(800,700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        // create menu
        menuBar = new JMenuBar();
        // menu items
        // GAMarket
        GAMarket = new JMenu("GAMarket");
        GAMarket.setMnemonic(KeyEvent.VK_A);
        GAMarket.getAccessibleContext().setAccessibleDescription(
                "GAMarket");
        menuBar.add(GAMarket);
        // friends
        friends = new JMenu("Friends");
        friends.setMnemonic(KeyEvent.VK_A);
        friends.getAccessibleContext().setAccessibleDescription(
                "Friends");
        viewFriends = new JMenuItem("View Friends List");

        openFriendsList = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                FriendsGUI fGUI = new FriendsGUI();
                fGUI.setVisible(true);
            }
        };
    
        viewFriends.addActionListener(openFriendsList);
        friends.add(viewFriends);

        menuBar.add(friends);

        // settings
        settings = new JMenu("Settings");
        settings.setMnemonic(KeyEvent.VK_A);
        settings.getAccessibleContext().setAccessibleDescription(
                "The only menu in this program that has menu items");
        menuBar.add(settings);

        // Loading data
        storeGC = new GameCollection();
        storeGC.loadGameData("gameData.txt");
        // text inside of each tab
        storePane = new Store(storeGC);
        libraryPane = new JLabel("Library stuff here");
        libraryPane.setLocation(300, 300); 
        forumPane = new JLabel("Forum stuff here");
        forumPane.setLocation(300, 300); 
        config = new JTabbedPane();
        configPane = new JPanel();
        configPane.setLayout(new BoxLayout(configPane, BoxLayout.Y_AXIS));

        // tabs for gui.GAManager
        config.addTab("Store", null, storePane, "Choose your games");
        config.addTab("Library", null, libraryPane, "See your library");
        config.addTab("Forum", null, forumPane, "Talk about your favorite games");

        setJMenuBar(menuBar);
        getContentPane().add(config, BorderLayout.CENTER);
        setLocationRelativeTo(null); 
    }

    class TabManager implements ItemListener {
        Component tab;
        public TabManager(Component tabToManage) {
            tab = tabToManage;
        }
        public void itemStateChanged(ItemEvent ie) {
            int index = config.indexOfComponent(tab);
            if (index != -1) {
            config.setEnabledAt(index, ie.getStateChange() == ItemEvent.SELECTED);
            }
            GAManager.this.repaint();
        }
    }

    public static void main(String args[]) {
        GAManager gm = new GAManager();
        gm.setVisible(true);
    }
}