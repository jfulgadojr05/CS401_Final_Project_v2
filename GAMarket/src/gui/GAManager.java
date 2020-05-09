package gui;

import app.GameCollection;
import app.Store;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/* GAManager class
	CS 401 - Final Project
	FriendsGUI.java
  By: Christian Magpantay
  Code/Book Reference -
  https://www.youtube.com/watch?v=CqWorn8dR_A&list=PLdmXYkPMWIgCocLY-B4SvpQshQWC7Nc0C&index=5
*/

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
    JList<String> storeList;
    JList<String> gamesList;
    JList<String> libraryList;
    String[] storeArr = {
            "Top Selling:", "",
            "BorderLand 3                                  $29.99",
            "XCOM: Chimera Sqaud:                 $9.99",
            "XCOM 2 Collection:                        $29.65",
            "Mount & Blade II: Bannerlord       $49.99",
            "Warhammer 40,000: Inquistor        $27.19",
            "Tabletop Simulator                         $19.19",
            "Iratus: Lord of the Dead                 $16.49",
            "DOOM Eternal                                 $59.99"
    };
    String[] gamesArr = {
            "Most Popular Disccussions:", "",
            "Valorant", "Grand Theft Auto V", "Counter-Strike",
            "Fornite", "League of Legends", "Call of Duty: Modern Warfare",
            "MineCraft", "Dota 2", "Monopoly Plus",
            "World of Warcraft", "Teamfight Tactics", "Apex Legends"
    };
    String[] libraryArr = {
            "Your library: \n", "",
            "Alan Wake", "Counter-Strike: Global Offensive", "Dead By Deadlight",
            "Dead Space 2", "The Escapists 2", "Left 4 Dead 2",
            "Portal", "Resident Evil 3", "Resident Evil 3: Raccoon City Demo",
            "Resident Evil Resistance", "The Witcher 3: Wild Hunt"
    };
    /*  GAManager constructor
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
        configPane = new JPanel();
        configPane.setLayout(new BoxLayout(configPane, BoxLayout.Y_AXIS));

        // tabs for gui.GAManager
        config = new JTabbedPane();
        storeList = new JList<String>(storeArr);
        config.addTab("Store", null, storePane, "Choose your games");
        libraryList = new JList<String>(libraryArr);
        config.addTab("Library", null, libraryList, "See your library");
        gamesList = new JList<String>(gamesArr);
        config.addTab("Forum", null, gamesList, "Talk about your favorite games");
        setJMenuBar(menuBar);
        getContentPane().add(config, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }

    public boolean login(String login, String password) throws IOException {
        String cmd = "login " + login + " " + password + "\n";

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