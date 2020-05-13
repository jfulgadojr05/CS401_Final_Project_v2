package gui;

import app.Game;
import app.GameCollection;
import db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class StoreGUI extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    // Static java swing components
    static JList<String> storeMenuItems;
    static JButton filterGameButton, searchGameButton, unfilterGameButton;
    static JScrollPane storeMenuScroll;
    static JTextField searchTextField;

    public StoreGUI(GameCollection storeCollection, String userID, DBHelper dbh) throws SQLException {

        // Setting up menu panel
        JPanel menuPanel = new JPanel();
        JLabel gameItemLabel = new JLabel("List of Games");
        gameItemLabel.setFont(new Font("Verdana", Font.PLAIN, 24));

        // Creating button for button panel later on
        filterGameButton = new JButton("Filter Games");
        searchGameButton = new JButton("Search Game");
        unfilterGameButton = new JButton("Reset Games");

        // Setting up layout and adding label to panel
        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);

        // Grabbing all the games from db and adding mouse clicker
        storeMenuItems = dbh.getAllGameName();
        storeMenuItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // do action on 2 mouse clicks
                if (e.getClickCount() == 2) {
                    // Creating local temp game
                    Game tempGame;

                    // Grabbing game name from jList store items
                    String selectedItemStr = storeMenuItems.getSelectedValue();

                    // Try-Catch for sql error
                    try {
                        // Get the game object and add option pane to purchase game or show forum
                        tempGame = dbh.getGameProfileName(selectedItemStr);
                        String[] gameCommands = {"Purchase Game", "Show Forum"};

                        // JOption pane to purchase game or show forum
                        int gameChoice;
                        gameChoice = JOptionPane.showOptionDialog(null,
                                tempGame.toString(),
                                tempGame.getName(),
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                gameCommands,
                                gameCommands[gameCommands.length - 1]);
                        switch(gameChoice) {
                            case 0: storeCollection.purchaseGame(selectedItemStr, userID, dbh); break;
                            case 1: System.out.println("Show Forum"); break;
                            case 2: return;
                            default: // do nothing
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        // Setting font for JList items and adding jlist to jscrollpane
        storeMenuItems.setFont(new Font("Verdana", Font.PLAIN, 12));
        storeMenuScroll = new JScrollPane(storeMenuItems);

        // Adding jscrollpane (store menu items) to main store panel
        menuPanel.add(storeMenuScroll, BorderLayout.CENTER);

        // Filter button action listener
        filterGameButton.addActionListener(e -> {

            // Option pane to filter by genre or rating
            int filterSelect;
            filterSelect = JOptionPane.showOptionDialog(null,
                    "Select a Genre",
                    "Filter",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    new String[]{"Genre", "Rating", "Exit"},
                    "Exit"
                    );
            switch(filterSelect) {

                // filter the games by genre
                case 0:
                    // temporary remove all menu panel components
                    menuPanel.removeAll();

                    // enter genre, returns false if its invalid
                    String genreSelect = JOptionPane.showInputDialog(null,"Enter genre to filter");
                    JList<String> filterList = null;

                    // Try-catch for sql error
                    try {
                        // get new JList components that filters the games query
                        filterList = storeCollection.filterGameGenre(genreSelect, dbh);
                        JList<String> finalFilterList1 = filterList;

                        // Add mouse clicks to filter list items
                        filterList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                    // Creating local variable
                                    Game tempGame;

                                    // Getting filter list item name
                                    String selectedItemStr = finalFilterList1.getSelectedValue();

                                    // Try-catch for sql error
                                    try {
                                        // Get the game object and add option pane to purchase game or show forum
                                        tempGame = dbh.getGameProfileName(selectedItemStr);
                                        String[] gameCommands = {"Purchase Game", "Show Forum"};

                                        // JOption pane to purchase game or show forum
                                        int gameChoice;
                                        gameChoice = JOptionPane.showOptionDialog(null,
                                                tempGame.toString(),
                                                tempGame.getName(),
                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE,
                                                null,
                                                gameCommands,
                                                gameCommands[gameCommands.length - 1]);
                                        switch(gameChoice) {
                                            case 0: storeCollection.purchaseGame(selectedItemStr, userID, dbh); break;
                                            case 1: System.out.println("Show Forum"); break;
                                            case 2: return;
                                            default: // do nothing
                                        }
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        });

                        // Setting up filter list font and scroll pane
                        filterList.setFont(new Font("Verdana", Font.PLAIN, 12));
                        JScrollPane filterScroll = new JScrollPane(filterList);

                        // Setting menu panel layout and refreshing menu panel
                        menuPanel.setLayout(new BorderLayout());
                        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
                        menuPanel.add(filterScroll, BorderLayout.CENTER);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case 1:

                    // temporary remove all menu panel components
                    menuPanel.removeAll();

                    // enter ratingg, returns false if its invalid
                    String ratingSelect = JOptionPane.showInputDialog(null,"Enter rating to filter");

                    // Try-catch for sql error
                    try {
                        // get new JList components that filters the games query
                        filterList = storeCollection.filterGameRating(ratingSelect, dbh);
                        JList<String> finalFilterList = filterList;

                        // Add mouse clicks to filter list items
                        filterList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                    // Creating local variable
                                    Game tempGame;

                                    // Getting filter list item name
                                    String selectedItemStr = finalFilterList.getSelectedValue();

                                    // Try-catch for sql error
                                    try {

                                        // Get the game object and add option pane to purchase game or show forum
                                        tempGame = dbh.getGameProfileName(selectedItemStr);
                                        String[] gameCommands = {"Purchase Game", "Show Forum"};

                                        // JOption pane to purchase game or show forum
                                        int gameChoice;
                                        gameChoice = JOptionPane.showOptionDialog(null,
                                                tempGame.toString(),
                                                tempGame.getName(),
                                                JOptionPane.YES_NO_CANCEL_OPTION,
                                                JOptionPane.INFORMATION_MESSAGE,
                                                null,
                                                gameCommands,
                                                gameCommands[gameCommands.length - 1]);
                                        switch(gameChoice) {
                                            case 0: storeCollection.purchaseGame(selectedItemStr, userID, dbh); break;
                                            case 1: System.out.println("Show Forum"); break;
                                            case 2: return;
                                            default: // do nothing
                                        }
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        });

                        // Setting up filter list font and scroll pane
                        filterList.setFont(new Font("Verdana", Font.PLAIN, 12));
                        JScrollPane filterScroll;
                        filterScroll = new JScrollPane(filterList);

                        // Setting menu panel layout and refreshing menu panel
                        menuPanel.setLayout(new BorderLayout());
                        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
                        menuPanel.add(filterScroll, BorderLayout.CENTER);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case 2:
                    // Do nothing
                    return;
                default: //do nothing
            }

            // Repaint and revalidate for refreshing pane
            menuPanel.revalidate();
            menuPanel.repaint();
        });

        // Unfilter action listener to reset the list
        unfilterGameButton.addActionListener(e -> {
            // temporary remove all components
            menuPanel.removeAll();

            // Set layour and button
            menuPanel.setLayout(new BorderLayout());
            menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);

            // Return the original scroll pane that has all the games
            menuPanel.add(storeMenuScroll, BorderLayout.CENTER);

            // repaint and revalidate menu panel
            menuPanel.revalidate();
            menuPanel.repaint();
        });

        // search button action listener for searching game profile
        searchGameButton.addActionListener(e -> {

            // setting up search action command
            String search = e.getActionCommand();
            if (search.equals("Search Game")) {
                String result;

                // try-catch for sql error
                try {
                    // search game function from the text included
                    result = storeCollection.searchForGame(searchTextField.getText(), dbh);

                    //If-Else if game is not found
                    if (result.equals("Not found")){
                        // Message pane for game not found
                        JOptionPane.showMessageDialog(null, "No game found");
                    }
                    else {
                        // Get game profile if game is found
                        Game displayGame = dbh.getGameProfile(result);

                        // Game profile commands to purchase game and show forum
                        String[] gameCommands = {"Purchase Game", "Show Forum"};
                        int gameChoice;
                        gameChoice = JOptionPane.showOptionDialog(null,
                                displayGame.toString(),
                                displayGame.getName(),
                                JOptionPane.YES_NO_CANCEL_OPTION,
                                JOptionPane.INFORMATION_MESSAGE,
                                null,
                                gameCommands,
                                gameCommands[gameCommands.length - 1]);
                        switch(gameChoice) {
                            case 0: storeCollection.purchaseGame(result, userID, dbh); break;
                            case 1: System.out.println("Show Forum"); break;
                            case 2: return;
                            default: // do nothing
                        }
                    }
                    searchTextField.setText("");
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        // search text file
        searchTextField = new JTextField(20);

        // Creating new panels for buttons, filter, and search
        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel filterPanel = new JPanel();

        // Setting filter panel layout and adding components
        filterPanel.setLayout(new BorderLayout());
        filterPanel.add(unfilterGameButton, BorderLayout.LINE_START);
        filterPanel.add(filterGameButton, BorderLayout.LINE_END);

        // setting search panel layout and adding components
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchTextField, BorderLayout.LINE_START);
        searchPanel.add(searchGameButton, BorderLayout.LINE_END);

        // setting button panel layout and adding components
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(filterPanel, BorderLayout.WEST);
        buttonPanel.add(searchPanel, BorderLayout.EAST);

        // Setting main jpanel layout and components, and showing panel
        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.setVisible(true);


    }

}