package com.musicplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class registerUser
{
        private final static String url = "jdbc:postgresql://localhost/musicplayer";
        private final static String user = "postgres";
        private final static String password = "password";
    public static void register(String username, String pwd, String firstname, String lastname, String email, String phone)
    {

        //byte[] bytes = Files.readAllBytes(Paths.get("C:\\Users\\Sohan\\Downloads\\Take Me Back.mp3"));

        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "insert into authentication(username, pwd, firstname, lastname, email, phone) values(?, ?, ?, ?, ?, ?)";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, username);
            state.setString(2, pwd);
            state.setString(3, firstname);
            state.setString(4, lastname);
            state.setString(5, email);
            state.setString(6, phone);
            //state.setBytes(4, bytes);
            state.executeUpdate();
            System.out.println("Inserted user");
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        
    }   

}