package gui;

import app.Game;
import app.GameCollection;
import db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class LibraryGUI extends JPanel {
    static JList<String> userLibraryItems;
    static JButton  refreshButton;

    static JScrollPane libraryScroll;

    public LibraryGUI(GameCollection libraryGC,DBHelper dbh, String userID) throws SQLException {
        JPanel userLibraryPanel = new JPanel();
        JLabel userLibraryLabel = new JLabel(dbh.getUsername(userID) + " Library");
        userLibraryLabel.setFont(new Font("Verdana", Font.PLAIN, 24));

        userLibraryPanel.setLayout(new BorderLayout());
        userLibraryPanel.add(userLibraryLabel, BorderLayout.PAGE_START);

         refreshButton = new JButton("Refresh Library");

        userLibraryItems = dbh.getUserLibrary(userID);
        userLibraryItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Game tempGame = new Game();
                    String selectedItemStr = userLibraryItems.getSelectedValue();
                    try {
                        tempGame = dbh.getGameProfileName(selectedItemStr);
                        String[] gameCommands = {"Play game", "Delete game","Show Forum"};
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
                            case 0:
                                JOptionPane.showMessageDialog(null,"Initializing " + selectedItemStr);
                                break;
                            case 1:
                                libraryGC.deleteGame(selectedItemStr, dbh, userID);
                                break;
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
        userLibraryItems.setFont(new Font("Verdana", Font.PLAIN, 12));
        libraryScroll = new JScrollPane(userLibraryItems);

        refreshButton.addActionListener(e -> {
            userLibraryPanel.removeAll();
            userLibraryPanel.setLayout(new BorderLayout());
            try {
                userLibraryItems = dbh.getUserLibrary(userID);
                userLibraryItems.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount() == 2) {
                            Game tempGame = new Game();
                            String selectedItemStr = userLibraryItems.getSelectedValue();
                            try {
                                tempGame = dbh.getGameProfileName(selectedItemStr);
                                String[] gameCommands = {"Play game", "Delete game","Show Forum"};
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
                                    case 0:
                                        JOptionPane.showMessageDialog(null,"Initializing " + selectedItemStr);
                                        break;
                                    case 1:
                                        libraryGC.deleteGame(selectedItemStr, dbh, userID);
                                        break;
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
                userLibraryItems.setFont(new Font("Verdana", Font.PLAIN, 12));
                libraryScroll = new JScrollPane(userLibraryItems);
                userLibraryPanel.add(userLibraryLabel, BorderLayout.PAGE_START);
                userLibraryPanel.add(libraryScroll, BorderLayout.CENTER);
                userLibraryPanel.revalidate();
                userLibraryPanel.repaint();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }


        });

        JPanel refreshPanel = new JPanel();
        refreshPanel.setLayout(new BorderLayout());
        refreshPanel.add(refreshButton, BorderLayout.WEST);
        userLibraryPanel.add(libraryScroll, BorderLayout.CENTER);

        this.setLayout(new BorderLayout());
        this.add(userLibraryPanel, BorderLayout.CENTER);
        this.add(refreshPanel, BorderLayout.PAGE_START);
        this.setVisible(true);

    }

}
