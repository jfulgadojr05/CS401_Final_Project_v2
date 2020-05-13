package gui;

import javax.swing.*;

import db.DBHelper;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.*;

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
    JList<String> createNewThreadInfo;
    JButton createNewThread;
    ThreadGUI tg;
    ReplyGUI rg;
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

    public ForumGUI(DBHelper dbh) throws SQLException {
        // main forum panel init
        forumPanel = new JPanel();
        forumPanel.setLayout(new BoxLayout(forumPanel, BoxLayout.X_AXIS));

        // all jLists init
 
        listTetris99 = new JList<String>(arrTetris99);
        listOvercooked2 = new JList<String>(arrOvercooked2);
        gamesList = dbh.getAllGameName();
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

        listSuperMario = dbh.getAllThreads();

        // for user to click on game collection list
        // will open up the respective game and its own threads/posts
        gamesList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent)  {
                if(mouseEvent.getClickCount() == 2) {

                    // which one is clicked
                    String game = gamesList.getSelectedValue();

                    // remove empty pane to reset
                    forumPanel.remove(emptyThreadPane);
                    forumPanel.remove(createNewThread);
                    forumPanel.revalidate();
                    forumPanel.repaint();
                    // rebuild pane with appropriate thread list
                    if(game.equals("Super Mario Odyssey")) {
                        try {                            
                            listSuperMario = dbh.getAllThreads();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        emptyThreadPane = new JScrollPane(listSuperMario);
                        createNewThread.addActionListener(e -> {{
                                tg = new ThreadGUI();
                                createNewThreadInfo = tg.getThreadInfo();
                                String threadTitle = 
                                        createNewThreadInfo.getModel().getElementAt(0).toString(); 
                                String post = 
                                        createNewThreadInfo.getModel().getElementAt(1).toString();
                                try {
                                    dbh.createThread(game, threadTitle, post);
                                } catch (SQLException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                        }});
                        try {
                            listSuperMario = dbh.getAllThreads();
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        // listSuperMario.addMouseListener(new MouseAdapter() {
                        //     @Override
                        //     public void mouseClicked(MouseEvent mouseEvent)  {
                        //         if(mouseEvent.getClickCount() == 2) {
                        //             //which one is clicked
                        //             String threadTitle = listSuperMario.getSelectedValue().toString();
                        //             try {
                        //                 rg = new ReplyGUI(dbh, threadTitle);
                        //                 JFrame replyFrame = new JFrame("Thread: " + threadTitle);
                        //                 replyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                        //                 replyFrame.setSize(400,400);
                        //                 replyFrame.getContentPane().add(rg, BorderLayout.CENTER);
                        //                 replyFrame.setVisible(true);
                        //             } catch (SQLException e) {
                        //                 // TODO Auto-generated catch block
                        //                 e.printStackTrace();
                        //             }
                        //         }
                        //     }
                        // });
                        emptyThreadPane = new JScrollPane(listSuperMario);
                        forumPanel.add(emptyThreadPane);
                        forumPanel.add(createNewThread);
                    }
                    else if(game.equals("Tetris 99")) {
                        emptyThreadPane = new JScrollPane(listTetris99);
                        forumPanel.add(emptyThreadPane);
                    }
                    else if(game.equals("Overcooked 2")) {
                        emptyThreadPane = new JScrollPane(listOvercooked2);
                        forumPanel.add(emptyThreadPane);
                    }
                    // read pane
                    forumPanel.revalidate();
                    forumPanel.repaint();
                }
            }
        });

        // add game collection and empty pane
        forumPanel.add(gameCollectionPane);
        forumPanel.add(emptyThreadPane);
        createNewThread = new JButton("Create New Thread");
        // finals add
        this.setLayout(new BorderLayout());
        this.add(forumPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

}
