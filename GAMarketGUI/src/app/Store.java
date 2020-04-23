package app;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Store extends JPanel {

//    private Store gameStore;

//    static JFrame storeFrame;

    static JList<String> storeMenuItems;

    static JButton filterGameButton, searchGameButton;

//    public StoreGUI(Store gameStore) {
//        this.gameStore = gameStore;
//    }


    public Store(GameCollection storeCollection){

        JPanel menuPanel = new JPanel();
        JLabel gameItemLabel = new JLabel("List of Games");
        filterGameButton = new JButton("Filter Games");
        searchGameButton = new JButton("Search Games");
        ActionListener filterListener = (ActionEvent filterEvent) -> doFilterGame();
        filterGameButton.addActionListener(filterListener);
        ActionListener searchListener = (ActionEvent searchEvent) -> doSearchGame();
        searchGameButton.addActionListener(searchListener);
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        for(int i = 0; i < storeCollection.getNumberOfGames(); i++){
            storeModel.addElement(storeCollection.getGameArray()[i].getName());
        }
        storeMenuItems = new JList<>(storeModel);

        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
        menuPanel.add(Box.createRigidArea(new Dimension(0,1)));
        menuPanel.add(storeMenuItems, BorderLayout.LINE_START);
//        menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());
        buttonPanel.add(Box.createHorizontalGlue());
        buttonPanel.add(filterGameButton, BorderLayout.LINE_START);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(searchGameButton, BorderLayout.LINE_END);
        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.setVisible(true);

    }



    public void doFilterGame(){
        System.out.println("Filtering game...");
    }
    public void doSearchGame(){
        System.out.println("Searching game...");
    }



}
