package gui;

import app.GameCollection;
import db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

/* GAManager class
	CS 401 - Final Project
    FriendsGUI.java
    By: Christian Taro Magpantay
    Code/Book Reference -
    https://www.youtube.com/watch?v=CqWorn8dR_A&list=PLdmXYkPMWIgCocLY-B4SvpQshQWC7Nc0C&index=5
*/

public class GAManager extends JFrame {
    private static final long serialVersionUID = 2949039844571509694L;
    JMenuBar menuBar;
    JMenu GAMarket;
    JMenu friends;
    JMenu settings;
    JMenuItem viewFriends;
    JMenuItem menuItem;
    JTabbedPane config;
    StoreGUI storeGUIPane;
    LibraryGUI libraryGUIPane;
    ForumGUI forumPane;
    JLabel libraryPane;
    JLabel friendsPane;
    JLabel settingsPane;
    JPanel configPane;
    JTextArea question;
    ActionListener openFriendsList;
    JList<String> storeList;
    JList<String> gamesList;
    JList<String> libraryList;

    String[] gamesArr = { "Most Popular Disccussions:", "", "Valorant", "Grand Theft Auto V", "Counter-Strike",
            "Fornite", "League of Legends", "Call of Duty: Modern Warfare", "MineCraft", "Dota 2", "Monopoly Plus",
            "World of Warcraft", "Teamfight Tactics", "Apex Legends" };
    String[] libraryArr = { "Your library: \n", "", "Alan Wake", "Counter-Strike: Global Offensive",
            "Dead By Deadlight", "Dead Space 2", "The Escapists 2", "Left 4 Dead 2", "Portal", "Resident Evil 3",
            "Resident Evil 3: Raccoon City Demo", "Resident Evil Resistance", "The Witcher 3: Wild Hunt" };

    /*
     * GAManager constructor set title set size set close operation create menubar
     * GAMarket menu friends menu settings menu create tabs store library forum
     */
    public GAManager(DBHelper mydb, String userID) throws SQLException {

        super("GAMarket"); // app title
        setSize(800, 700); // defalut size
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // create menu bar
        menuBar = new JMenuBar();
        // menu bar items
        // GAMarket
        GAMarket = new JMenu("GAMarket");
        GAMarket.setMnemonic(KeyEvent.VK_A);
        GAMarket.getAccessibleContext().setAccessibleDescription("GAMarket");
        menuBar.add(GAMarket);

        // friends
        friends = new JMenu("Friends");
        friends.setMnemonic(KeyEvent.VK_A);
        friends.getAccessibleContext().setAccessibleDescription("Friends");
        viewFriends = new JMenuItem("View Friends List");
        // view friends list menu item
        openFriendsList = new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                FriendsGUI fGUI;
                try {
                    fGUI = new FriendsGUI(mydb);
                    fGUI.setVisible(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        };

        // add view friends menuitem
        viewFriends.addActionListener(openFriendsList);
        friends.add(viewFriends);
        menuBar.add(friends);

        // settings
        settings = new JMenu("Settings");
        settings.setMnemonic(KeyEvent.VK_A);
        settings.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(settings);

        configPane = new JPanel();
        configPane.setLayout(new BoxLayout(configPane, BoxLayout.Y_AXIS));

        // tabs for gui.GAManager
        config = new JTabbedPane();

        // for store tab
        // Loading data
        GameCollection storeGC = new GameCollection();
        storeGUIPane = new StoreGUI(storeGC, userID, mydb);
        config.addTab("Store", null, storeGUIPane, "Choose your games");

        // for library tab
        //        libraryList = new JList<>(libraryArr);
        GameCollection libraryGC = new GameCollection();
        libraryGUIPane = new LibraryGUI(libraryGC, mydb, userID);
        config.addTab("Library", null, libraryGUIPane, "See your library");

        // for forum tab
        forumPane = new ForumGUI();
        config.addTab("Forum", null, forumPane, "Talk about your favorite games");

        // final adds to create
        setJMenuBar(menuBar);
        getContentPane().add(config, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    // tab handler
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

    //run store
//    public static void main(String args[]) throws SQLException {
//        DBHelper dbh = new DBHelper();
//        dbh.createNewDatabase();
//        dbh.createAllTables();
//        GAManager gm = new GAManager(dbh);
//        gm.setVisible(true);
//    }
}