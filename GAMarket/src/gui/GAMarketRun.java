package gui;

import javax.swing.*;
import java.sql.SQLException;

public class GAMarketRun {
    public static void main(String[] args) throws SQLException {
        LoginGUI loginUser = new LoginGUI();
        loginUser.setVisible(true);
        loginUser.setTitle("GAMarket Login");
        loginUser.setBounds(10, 10, 370, 600);
        loginUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginUser.setResizable(false);
        loginUser.setLocationRelativeTo(null);
    }
}