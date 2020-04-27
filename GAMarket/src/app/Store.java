package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Store extends JPanel {

//    private app.Store gameStore;

//    static JFrame storeFrame;

    static JList<String> storeMenuItems;

    static JButton filterGameButton, searchGameButton;
    static JScrollPane storeMenuScroll;

//    public StoreGUI(app.Store gameStore) {
//        this.gameStore = gameStore;
//    }


    public Store(GameCollection storeCollection){

        JPanel menuPanel = new JPanel();
        JLabel gameItemLabel = new JLabel("List of Games");
        gameItemLabel.setFont(new Font("Verdana", Font.PLAIN, 24));

        filterGameButton = new JButton("Filter Games");
        searchGameButton = new JButton("Search Games");
        ActionListener filterListener = (ActionEvent filterEvent) -> doFilterGame(storeCollection);
        filterGameButton.addActionListener(filterListener);
        ActionListener searchListener = (ActionEvent searchEvent) -> doSearchGame();
        searchGameButton.addActionListener(searchListener);
        DefaultListModel<String> storeModel = new DefaultListModel<>();
        for(int i = 0; i < storeCollection.getNumberOfGames(); i++){
            storeModel.addElement(storeCollection.getGameArray()[i].getName());
        }
        storeMenuItems = new JList<>(storeModel);
        storeMenuItems.setFont(new Font("Verdana", Font.PLAIN, 12));

        storeMenuScroll = new JScrollPane(storeMenuItems);


        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
        menuPanel.add(Box.createRigidArea(new Dimension(0,1)));
        menuPanel.add(storeMenuScroll, BorderLayout.CENTER);
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



    public void doFilterGame(GameCollection storeGC){
        for (int i = 0; i < storeGC.getNumberOfGames(); i++){
            System.out.println(storeGC.getGameArray()[i].getName());
        }
        System.out.println("Filtering game...");
    }
    public void doSearchGame(){
        System.out.println("Searching game...");
    }



}
