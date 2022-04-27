package com.musicplayer;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class temp{

    static JButton searchButton;
    static JTextField searchTermTextField;
    static JPanel panel, tablepanel;
    static JFrame frame;

    
    public static void main(String args[])
    {
        panel = new JPanel();
        searchTermTextField = new JTextField(26);
        searchButton = new JButton("Search");
        frame = new JFrame();
        

        searchButton.addActionListener(new ActionListener()
        {  
            public void actionPerformed(ActionEvent e)
            {  
                String query = searchTermTextField.getText();
                DefaultTableModel model = searchDB.search(query);
                JTable table = new JTable(model);
                JFrame tabframe = new JFrame();
                tablepanel = new JPanel();
                tablepanel.add(new JScrollPane(table));
                tabframe.add(tablepanel);
                tabframe.setBackground(Color.white);
                tabframe.setSize(800, 800);
                tabframe.setVisible(true);
                tabframe.setResizable(false);
                tabframe.setLocationRelativeTo(null);
            }  
        }
        );  
        //panel.setLayout(new GridLayout(2, 1));
        panel.add(searchTermTextField);
        panel.add(searchButton);



        

        frame.setTitle("Music Player");

        //frame.add(emptyQueuePanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.NORTH);
        


        frame.setBackground(Color.white);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    


    //Singleton Creational
    //Iterator Behav
    //Null Behav
    //private class Structural
    //Interceptor

}
