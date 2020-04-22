package app;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;


public class StoreGUI implements StoreInterface {

    private Store gameStore;

    static JFrame storeFrame;


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
        JPanel menuPanel = new JPanel();
        JLabel gameItemLabel = new JLabel("List of Games");
        filterGameButton = new JButton("Filter Games");

        searchGameButton = new JButton("Search Games");
        returnButton = new JButton("Return To Menu");
        ActionListener filterListener = (ActionEvent filterEvent) -> doFilterGame();
        filterGameButton.addActionListener(filterListener);
        ActionListener searchListener = (ActionEvent searchEvent) -> doSearchGame();
        searchGameButton.addActionListener(searchListener);
        ActionListener returnListener = (ActionEvent returnEvent) -> doClose();
        returnButton.addActionListener(returnListener);
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        storeModel.addElement("Item 1");
        storeModel.addElement("Item 2");
        storeModel.addElement("Item 3");
        storeMenuItems = new JList<>(storeModel);

        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.PAGE_AXIS));

        menuPanel.add(gameItemLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0,1)));
        menuPanel.add(storeMenuItems);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,100));
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(filterGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(searchGameButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(returnButton);

        Container storeContent = storeFrame.getContentPane();
        storeContent.add(menuPanel, BorderLayout.CENTER);
        storeContent.add(buttonPanel, BorderLayout.PAGE_END);

        storeFrame.setLocationRelativeTo(null);
        storeFrame.pack();
        storeFrame.setSize(400,300);
        storeFrame.setVisible(true);

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
