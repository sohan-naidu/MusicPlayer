package com.musicplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.*;


public class Register implements ActionListener {

    JFrame regframe;
    JTextField firstName, lastName, email;
    JPasswordField password, retype;
    JPanel details, but;
    JButton regB, logB;
    JLabel fn, ln, em, pass, repass;



public void reg()
{
    fn = new JLabel("First Name");
    ln = new JLabel("Last Name");
    em = new JLabel("E-mail");
    pass = new JLabel("Password");
    repass = new JLabel("Re-type password");

    firstName = new JTextField();
    lastName = new JTextField();
    email = new JTextField();

    password = new JPasswordField();
    retype = new JPasswordField();
    
    details = new JPanel();

    regframe = new JFrame();
    
    //details.setLayout(new GridLayout(5, 2));
    //regframe.setLayout(null);
    fn.setBounds(40, 70, 200, 30);  
    firstName.setBounds(80, 110, 400, 30);
    //details.add(fn);
    //ln.setBounds(80, 110, 200, 30);
    //details.add(firstName);
    //details.add(ln);
    //details.add(lastName);
    /*details.add(em);
    details.add(email);
    details.add(pass);
    details.add(password);
    details.add(repass);
    details.add(retype);
    */
    //regframe.add(details, BorderLayout.NORTH);
    regframe.add(fn);
    regframe.add(firstName);
    regframe.setBackground(Color.white);
    regframe.setSize(800, 600);
    regframe.setVisible(true);
    regframe.setResizable(false);
    regframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    regframe.setLocationRelativeTo(null);
   
}
public static void main(String args[])  
{  
    Register reg = new Register();
    reg.reg();

}

@Override
public void actionPerformed(ActionEvent e) {
    // TODO Auto-generated method stub
    
}

}
 

