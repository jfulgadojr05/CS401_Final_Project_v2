package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;



/* Message GUI class
	CS 401 - Final Project
	MessageGUI.java
  By: Christian Magpantay
  Code/Book Reference -
  https://www.youtube.com/watch?v=CqWorn8dR_A&list=PLdmXYkPMWIgCocLY-B4SvpQshQWC7Nc0C&index=5
  https://docs.oracle.com/javase/7/docs/api/javax/swing/JList.html
*/

public class MessageGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    DefaultListModel<JButton> mModel = new DefaultListModel<>();
    JList<JButton> mList = new JList<>(mModel);
    JTextField input = new JTextField();
    JButton chatBubble = new JButton();
    JScrollPane scroll = new JScrollPane(mList);

    public MessageGUI() {
        setLayout(new BorderLayout());

        final JScrollBar scrollBar = scroll.getVerticalScrollBar();
        scrollBar.addAdjustmentListener(new AdjustmentListener() {
            public void adjustmentValueChanged(AdjustmentEvent e) {
                System.out.println(scrollBar.getValue());
            }
        });

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String text = input.getText();
                chatBubble.setText("chrisTaro: " + text);
                mModel.addElement(chatBubble);
                input.setText("");
            }
        });

        mList.setBackground(new Color(255,255,255));
        mList.setCellRenderer(new MyCellRenderer());

        chatBubble.setHorizontalAlignment(SwingConstants.RIGHT);
        chatBubble.setForeground(Color.WHITE);
        chatBubble.setBackground(new Color(4, 151, 251));
        chatBubble.setOpaque(true);
        chatBubble.setBorderPainted(false);

        add(new JScrollPane(mList), BorderLayout.CENTER);
        add(input, BorderLayout.SOUTH);
    }

    class MyCellRenderer extends JButton implements ListCellRenderer<Object> {
        private static final long serialVersionUID = 1L;
        public Component getListCellRendererComponent(JList<?> list, Object value,
                                                      int index, boolean isSelected, boolean cellHasFocus) {
            JButton renderer = (JButton) value;
            return renderer;
        }
    }
}