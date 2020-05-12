package gui;

import app.Game;
import db.DBHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

public class LibraryGUI extends JPanel {
    static JList<String> userLibraryItems;
    static JScrollPane libraryMenuPane;

    public LibraryGUI(DBHelper dbh, String userID) throws SQLException {
        JPanel userLibraryPanel = new JPanel();
        JLabel gameItemLabel = new JLabel(userID + " Library");
        gameItemLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
        userLibraryItems = dbh.getUserLibrary(userID);
        userLibraryItems.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    Game tempGame = new Game();
                    String selectedItemStr = userLibraryItems.getSelectedValue();
                    try {
                        tempGame = dbh.getGameProfileName(selectedItemStr);
                        String[] gameCommands = {"Play game", "Show Forum"};
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
        userLibraryItems.setFont(new Font("Verdana", Font.PLAIN, 12));
        libraryMenuPane = new JScrollPane(userLibraryItems);
        userLibraryPanel.add(libraryMenuPane, BorderLayout.CENTER);
        this.setLayout(new BorderLayout());
        this.add(userLibraryPanel, BorderLayout.CENTER);
        this.add(userLibraryPanel, BorderLayout.CENTER);




    }

}
