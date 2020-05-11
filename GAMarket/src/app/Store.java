package app;

import db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.StringTokenizer;

public class Store extends JPanel {

    static JList<String> storeMenuItems;
    static JButton filterGameButton, searchGameButton, unfilterGameButton;
    static JScrollPane storeMenuScroll;
    static JTextField searchTextField;



    public Store(GameCollection storeCollection, DBHelper dbh) throws SQLException {

        JPanel menuPanel = new JPanel();
        JLabel gameItemLabel = new JLabel("List of Games");
        gameItemLabel.setFont(new Font("Verdana", Font.PLAIN, 24));

        filterGameButton = new JButton("Filter Games");
        searchGameButton = new JButton("Search Game");
        unfilterGameButton = new JButton("Reset Games");

        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);

        DefaultListModel<String> storeModel = new DefaultListModel<>();
        for(int i = 0; i < storeCollection.getNumberOfGames(); i++){
            String gameIDstr = Integer.toString(storeCollection.getGameArray()[i].getId());
            String tempItem = gameIDstr + "," +
                    storeCollection.getGameArray()[i].getName() + ", " +
                    storeCollection.getGameArray()[i].getGenre() + ", " +
                    storeCollection.getGameArray()[i].getAverageRating();
            storeModel.addElement(tempItem);
        }
        storeMenuItems = dbh.getAllGames();
        storeMenuItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Game tempGame = new Game();
                    String selectedItemStr = storeMenuItems.getSelectedValue();
                    StringTokenizer st = new StringTokenizer(selectedItemStr, ",");
                    String listGameID = st.nextToken();
                    try {
                        tempGame = dbh.getGameProfile(listGameID);
                        String[] gameCommands = {"Play Game", "Purchase Game", "Show Forum"};
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
                            case 0: tempGame.initializeGame(); break;
                            case 1: System.out.println("Purchase Game"); break;
                            case 2: System.out.println("Show Forum"); break;
                            case 3: return;
                            default: // do nothing
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        storeMenuItems.setFont(new Font("Verdana", Font.PLAIN, 12));
        storeMenuScroll = new JScrollPane(storeMenuItems);

        menuPanel.add(storeMenuScroll, BorderLayout.CENTER);
        filterGameButton.addActionListener(e -> {
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
                case 0:
                    menuPanel.removeAll();
                    String genreSelect = JOptionPane.showInputDialog(null,"Enter genre to filter");
                    JList<String> filterList = null;
                    try {
                        filterList = storeCollection.filterGameGenre(genreSelect, dbh);
                        JList<String> finalFilterList1 = filterList;
                        filterList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                    Game tempGame = new Game();
                                    String selectedItemStr = finalFilterList1.getSelectedValue();
                                    StringTokenizer st = new StringTokenizer(selectedItemStr, ",");
                                    String listGameID = st.nextToken();
                                    try {
                                        tempGame = dbh.getGameProfile(listGameID);
                                        String[] gameCommands = {"Play Game", "Purchase Game", "Show Forum"};
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
                                            case 0: tempGame.initializeGame(); break;
                                            case 1: System.out.println("Purchase Game"); break;
                                            case 2: System.out.println("Show Forum"); break;
                                            case 3: return;
                                            default: // do nothing
                                        }
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        });

                        filterList.setFont(new Font("Verdana", Font.PLAIN, 12));
                        JScrollPane filterScroll = new JScrollPane(filterList);
                        menuPanel.setLayout(new BorderLayout());
                        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
                        menuPanel.add(filterScroll, BorderLayout.CENTER);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case 1:
                    menuPanel.removeAll();
                    String ratingSelect = JOptionPane.showInputDialog(null,"Enter rating to filter");
                    try {
                        filterList = storeCollection.filterGameRating(ratingSelect, dbh);
                        JList<String> finalFilterList = filterList;
                        filterList.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                if (e.getClickCount() == 2) {
                                    Game tempGame = new Game();
                                    String selectedItemStr = finalFilterList.getSelectedValue();
                                    StringTokenizer st = new StringTokenizer(selectedItemStr, ",");
                                    String listGameID = st.nextToken();
                                    try {
                                        tempGame = dbh.getGameProfile(listGameID);
                                        String[] gameCommands = {"Play Game", "Purchase Game", "Show Forum"};
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
                                            case 0: tempGame.initializeGame(); break;
                                            case 1: System.out.println("Purchase Game"); break;
                                            case 2: System.out.println("Show Forum"); break;
                                            case 3: return;
                                            default: // do nothing
                                        }
                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }
                            }
                        });
                        filterList.setFont(new Font("Verdana", Font.PLAIN, 12));
                        JScrollPane filterScroll = new JScrollPane(filterList);
                        filterScroll = new JScrollPane(filterList);
                        menuPanel.setLayout(new BorderLayout());
                        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
                        menuPanel.add(filterScroll, BorderLayout.CENTER);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case 2:
                    menuPanel.removeAll();
                    menuPanel.setLayout(new BorderLayout());
                    menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
                    menuPanel.add(storeMenuScroll, BorderLayout.CENTER);
                    menuPanel.revalidate();
                    menuPanel.repaint();
                default: //do nothing
            }
            menuPanel.revalidate();
            menuPanel.repaint();
        });
        unfilterGameButton.addActionListener(e -> {
            menuPanel.removeAll();
            menuPanel.setLayout(new BorderLayout());
            menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
            menuPanel.add(storeMenuScroll, BorderLayout.CENTER);
            menuPanel.revalidate();
            menuPanel.repaint();
        });

        searchGameButton.addActionListener(e -> {
            String search = e.getActionCommand();
            if (search.equals("Search Game")) {
                String result = storeCollection.searchForGame(searchTextField.getText());
                if (result.equals("Not found")){
                    JOptionPane.showMessageDialog(null, "No game found");
                }
                else {
                    Game displayGame = new Game();
                    for (int i = 0; i < storeCollection.getNumberOfGames(); i++){
                        if (storeCollection.getGameArray()[i].toString().equals(result)){
                            displayGame = storeCollection.getGameArray()[i];
                            break;
                        }
                    }
                    String[] gameCommands = {"Play Game", "Purchase Game", "Show Forum"};
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
                        case 0: System.out.println("Play Game"); break;
                        case 1: System.out.println("Purchase Game"); break;
                        case 2: System.out.println("Show Forum"); break;
                        case 3: return;
                        default: // do nothing
                    }
                }
                searchTextField.setText("");
            }
        });


        searchTextField = new JTextField(20);

        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BorderLayout());
        filterPanel.add(unfilterGameButton, BorderLayout.LINE_START);
        filterPanel.add(filterGameButton, BorderLayout.LINE_END);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchTextField, BorderLayout.LINE_START);
        searchPanel.add(searchGameButton, BorderLayout.LINE_END);
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(filterPanel, BorderLayout.WEST);
        buttonPanel.add(searchPanel, BorderLayout.EAST);


        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.setVisible(true);


    }

}
