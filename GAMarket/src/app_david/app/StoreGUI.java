package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StoreGUI implements StoreInterface {

    private Store gameStore;

    static JFrame storeFrame;

    static JPanel storePanel;

    static JList<String> storeMenuItems;

    static JButton filterGameButton, searchGameButton, returnButton;

    public StoreGUI(Store gameStore) {
        this.gameStore = gameStore;
    }

    public void showGameMenu() {
        String[] commands = { "Purchase Game",
                "Filter Game",
                "Search Game",
                "Go To Game Profile",
                "Return to Store",
        };

        storeFrame = new JFrame("Game Store Menu");
        storePanel = new JPanel();

        filterGameButton = new JButton("Filter Games");
        searchGameButton = new JButton("Search Games");
        returnButton = new JButton("Return To Menu");

        DefaultListModel<String> storeModel = new DefaultListModel<>();
        storeModel.addElement("Item 1");
        storeModel.addElement("Item 2");
        storeModel.addElement("Item 3");

        storeMenuItems = new JList<>(storeModel);
        storePanel.add(filterGameButton);
        storePanel.add(searchGameButton);
        storePanel.add(returnButton);
        storePanel.add(storeMenuItems);


        storeFrame.add(storePanel);




        storeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        storeFrame.setTitle("Store Tab");
        storeFrame.setSize(300,300);
        storeFrame.setLocationRelativeTo(null);
        storeFrame.setVisible(true);

//        int choice;
//
//        do {
//
//            choice = JOptionPane.showOptionDialog(null,
//                    "",
//                            "Store Menu",
//                            JOptionPane.YES_NO_CANCEL_OPTION,
//                            JOptionPane.QUESTION_MESSAGE,
//                            null,
//                            commands,
//                            commands[commands.length - 1]);
//
//                    switch (choice) {
//                        case 0: doPurchaseGame(); break;
//                        case 1: doFilterGame(); break;
//                        case 2: doSearchGame(); break;
//                        case 3: doGoToProfile(); break;
//                        case 4: doClose(); break;
//                        default:  // do nothing
//            }
//
//        } while (choice != commands.length-1);
//        System.exit(0);


    }

    public void doPurchaseGame(){
        System.out.println("Purchasing game...");
    }
    public void doFilterGame(){
        System.out.println("Filtering game...");
    }
    public void doSearchGame(){
        System.out.println("Searching game...");
    }
    public void doGoToProfile(){
        System.out.println("Going to gamer profile...");
    }
    public void doClose(){
        System.out.println("Closing Store Tab");
    }





}
