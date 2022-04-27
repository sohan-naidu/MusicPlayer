package com.musicplayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton resetButton = new JButton("REGISTER");
    JCheckBox showPassword = new JCheckBox("Show Password");
    JFrame frame = new JFrame();

    LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();

    }

    public void setLayoutManager() {
        container.setLayout(null);
    }

    public void setLocationAndSize() {
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        resetButton.setBounds(200, 300, 100, 30);

    }

    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(resetButton);
        

        frame.add(container);

        frame.setTitle("Login Form");
        frame.setBackground(Color.white);
        //frame.setSize(800, 600);
        frame.setBounds(10, 10, 370, 600);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
    }

    public void addActionEvent() {
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);
        showPassword.addActionListener(this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource().equals(loginButton))
        {
            String userText, pwdText;
            userText = userTextField.getText();
            pwdText = String.valueOf(passwordField.getPassword());
            System.out.println(pwdText);
            //Auth auth = new Auth();
            boolean ok = Auth.auth(userText, pwdText);
            if(ok)
            {
                System.out.println("OK");
                frame.dispose();
                new initMusicPlayer();
            }

            else
            {
                System.out.println("User doesnt exist or credentials wrong");
                JOptionPane.showMessageDialog(this, "User doesnt exist or credentials wrong");
            }
            
        }
        if (e.getSource() == resetButton) {
            frame.dispose();
            new UserRegistration().initURUI();
        }
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }

        }


    }


    
       

}