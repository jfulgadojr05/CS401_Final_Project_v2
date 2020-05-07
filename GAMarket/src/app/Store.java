package app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Store extends JPanel {

//    private app.Store gameStore;

//    static JFrame storeFrame;

    static JList<String> storeMenuItems;

    static JButton filterGameButton, searchGameButton, unfilterGameButton;
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
        unfilterGameButton = new JButton("Unfilter Games");

        menuPanel.setLayout(new BorderLayout());
        menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);

        DefaultListModel<String> storeModel = new DefaultListModel<>();
        for(int i = 0; i < storeCollection.getNumberOfGames(); i++){
            String tempItem = storeCollection.getGameArray()[i].getName() + ", " +
                    storeCollection.getGameArray()[i].getGenre() + ", " +
                    storeCollection.getGameArray()[i].getAverageRating();
            storeModel.addElement(tempItem);
        }
        storeMenuItems = new JList<>(storeModel);
        storeMenuItems.setFont(new Font("Verdana", Font.PLAIN, 12));

        storeMenuScroll = new JScrollPane(storeMenuItems);

        menuPanel.add(storeMenuScroll, BorderLayout.CENTER);

        ActionListener searchListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doSearchGame();
            }
        };
        filterGameButton.addActionListener(e -> {
            menuPanel.removeAll();
            String genreSelect = JOptionPane.showInputDialog("Type a genre to filter");
            JList<String> filterList = storeCollection.filterGame(genreSelect);
            filterList.setFont(new Font("Verdana", Font.PLAIN, 12));
            JScrollPane filterScroll = new JScrollPane(filterList);
            menuPanel.setLayout(new BorderLayout());
            menuPanel.add(gameItemLabel, BorderLayout.PAGE_START);
            menuPanel.add(filterScroll, BorderLayout.CENTER);
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
        searchGameButton.addActionListener(searchListener);

//        menuPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        JPanel buttonPanel = new JPanel();
        JPanel searchPanel = new JPanel();
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BorderLayout());
        filterPanel.add(unfilterGameButton, BorderLayout.LINE_START);
        filterPanel.add(filterGameButton, BorderLayout.LINE_END);
        searchPanel.setLayout(new BorderLayout());
        searchPanel.add(searchGameButton, BorderLayout.LINE_START);
        buttonPanel.setLayout(new BorderLayout());
//        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
        buttonPanel.add(filterPanel, BorderLayout.WEST);
        buttonPanel.add(searchPanel, BorderLayout.EAST);

//        buttonPanel.add(Box.createRigidArea(new Dimension(10,0)));
//        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));

        this.setLayout(new BorderLayout());
        this.add(menuPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_START);
        this.setVisible(true);
        this.revalidate();
        this.repaint();

    }



    public void doFilterGame(GameCollection storeGC){

    }
    public void doSearchGame(){
        System.out.println("Searching game...");
    }



}
