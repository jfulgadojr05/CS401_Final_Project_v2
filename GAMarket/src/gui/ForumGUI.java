package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* 
    ForumGUI class
	CS 401 - Final Project
	ForumGUI.java
    By: Christian Taro Magpantay
    Code/Book Reference -
    https://www.youtube.com/watch?v=CqWorn8dR_A&list=PLdmXYkPMWIgCocLY-B4SvpQshQWC7Nc0C&index=5
*/

public class ForumGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    JPanel forumPanel;
    JScrollPane gameCollectionPane;
    JScrollPane emptyThreadPane;
    JList<String> gamesList;
    JList<String> listSuperMario;
    JList<String> listTetris99;
    JList<String> listOvercooked2;
    String[] gamesCollList = {
        "Super Mario Odyssey", "Tetris 99", "Overcooked 2"
    };
    String[] arrSuperMario = {
            "Handling is bad", 
            "3D World should not be lumped with the NSMB games", 
            "Low volume handheld mode",
            "Mario Odyssey's Unfun Moons are REALLY Unfun",
            "Favorite to least favorite 3D Mario games?",
            "POLL: How many 3D Super Mario games have you completed?"
    };
    String[] arrTetris99 = {
            "Are badges even worth it?", 
            "Why cant I buy the Luigi's Mansion theme :(", 
            "Is it possible to play just basic Tetris in this game?",
            "Why does Invictus mode have such low turnouts?",
            "Think I've finally beaten this game.",
            "	fml if it wasnt for that 3 tspin daily id have that gameboy skin today"
    };
    String[] arrOvercooked2 = {
        "Anyone want to play story mode?", 
        "Easier, or harder?", 
        "Does the Overcooked 1 + 2 pack include 2 carts?",
        "Dead versus?"
    };

    public ForumGUI() {
        // main forum panel init
        forumPanel = new JPanel();
        forumPanel.setLayout(new BoxLayout(forumPanel, BoxLayout.X_AXIS));

        // all jLists init
        gamesList = new JList<String>(gamesCollList);
        listSuperMario = new JList<String>(arrSuperMario);
        listTetris99 = new JList<String>(arrTetris99);
        listOvercooked2 = new JList<String>(arrOvercooked2);

        // all panes init with lists
        gameCollectionPane = new JScrollPane(gamesList);
        emptyThreadPane = new JScrollPane();

        // sets game collection max width
        gameCollectionPane.setMaximumSize(
            new Dimension(600, gameCollectionPane.getMaximumSize().height)
        );
        gameCollectionPane.setPreferredSize(
            new Dimension(200, gameCollectionPane.getMaximumSize().height)
        );

        // for user to click on game collection list
        // will open up the respective game and its own threads/posts
        gamesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if(mouseEvent.getClickCount() == 2) {

                    // which one is clicked
                    String game = gamesList.getSelectedValue();

                    // remove empty pane to reset
                    forumPanel.remove(emptyThreadPane);
                    forumPanel.revalidate();
                    forumPanel.repaint();

                    // rebuild pane with appropriate thread list
                    if(game.equals("Super Mario Odyssey")) {
                        emptyThreadPane = new JScrollPane(listSuperMario);
                    }
                    else if(game.equals("Tetris 99")) {
                        emptyThreadPane = new JScrollPane(listTetris99);
                    }
                    else if(game.equals("Overcooked 2")) {
                        emptyThreadPane = new JScrollPane(listOvercooked2);
                    }

                    // read pane
                    forumPanel.add(emptyThreadPane);
                    forumPanel.revalidate();
                    forumPanel.repaint();
                }
            }
        });

        // add game collection and empty pane
        forumPanel.add(gameCollectionPane);
        forumPanel.add(emptyThreadPane);

        // finals add
        this.setLayout(new BorderLayout());
        this.add(forumPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    // implement background image
    // @Override
    // protected void paintComponent(Graphics g) {
    //   super.paintComponent(g);
    //       g.drawImage(bgImage, 0, 0, null);
    // }

}
