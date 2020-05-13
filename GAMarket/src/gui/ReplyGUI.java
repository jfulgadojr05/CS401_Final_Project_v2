package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import db.DBHelper;

/* Reply GUI class
	CS 401 - Final Project
	ReplyGUI.java
  By: Christian Magpantay
  Code/Book Reference -
  https://www.youtube.com/watch?v=CqWorn8dR_A&list=PLdmXYkPMWIgCocLY-B4SvpQshQWC7Nc0C&index=5
  https://docs.oracle.com/javase/7/docs/api/javax/swing/JList.html
  https://docs.oracle.com/javase/7/docs/api/javax/swing/JList.html#renderer
*/

public class ReplyGUI extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    JPanel replyPanel = new JPanel();
    JScrollPane scroll;
    JTextField input = new JTextField();
    JList<String> rList;

    public ReplyGUI(DBHelper dbh, String threadTitle) throws SQLException {
        String sender = "chrisTaro";
        replyPanel.setLayout(new BoxLayout(replyPanel, BoxLayout.Y_AXIS));

        input.setMaximumSize(new Dimension(input.getMaximumSize().width, 50));
        input.setPreferredSize(new Dimension(input.getMaximumSize().width, 25));
        rList = new JList<>();
        //rList = dbh.getAllReplies(threadTitle);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                replyPanel.removeAll();
                replyPanel.revalidate();
                replyPanel.repaint();

                String text = input.getText();
                String newInput = text + "\t\t\t\t from" + sender ;
                try {
                    dbh.sendReply(sender, newInput);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                input.setText("");
                rList.setCellRenderer(new MyCellRenderer());
                scroll = new JScrollPane(rList);
                replyPanel.add(scroll);
                replyPanel.add(input);
                replyPanel.revalidate();
                replyPanel.repaint();
            }
        });

        rList.setCellRenderer(new MyCellRenderer());
        scroll = new JScrollPane(rList);
        replyPanel.add(scroll);
        replyPanel.add(input);
        this.setLayout(new BorderLayout());
        this.add(replyPanel, BorderLayout.CENTER);
        this.setVisible(true);
    }

    class MyCellRenderer extends JLabel implements ListCellRenderer<Object> {
        /**
         *
         */
        private static final long serialVersionUID = 1L;
   
        // This is the only method defined by ListCellRenderer.
        // We just reconfigure the JLabel each time we're called.
   
        public Component getListCellRendererComponent(
          JList<?> list,           // the list
          Object value,            // value to display
          int index,               // cell index
          boolean isSelected,      // is the cell selected
          boolean cellHasFocus)    // does the cell have focus
        {
            Border paddingBorder = BorderFactory.createEmptyBorder(2, 3, 2, 3);
            String s = value.toString();
            setText(s);
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(new Color(4, 151, 251));
                setForeground(Color.WHITE);
                setBorder(paddingBorder);
                setHorizontalAlignment(SwingConstants.RIGHT);
            }
            setEnabled(list.isEnabled());
            setFont(list.getFont());
            setOpaque(true);
            return this;
        }
    }
   
}