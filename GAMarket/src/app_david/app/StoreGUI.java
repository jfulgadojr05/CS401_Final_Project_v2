package app;

import javax.swing.*;

import java.awt.*;


public class StoreGUI implements StoreInterface{

    private Store gameStore;

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

        int choice;

        do {
            choice = JOptionPane.showOptionDialog(null,
                    "",
                    "Store Menu",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    commands,
                    commands[commands.length - 1]);

            switch (choice) {
                case 0: doPurchaseGame(); break;
                case 1: doFilterGame(); break;
                case 2: doSearchGame(); break;
                case 3: doGoToProfile(); break;
                case 4: doClose(); break;
                default:  // do nothing
            }

        } while (choice != commands.length-1);
        System.exit(0);
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
