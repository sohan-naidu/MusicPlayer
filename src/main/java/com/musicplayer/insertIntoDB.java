package com.musicplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class insertIntoDB
{
        private final static String url = "jdbc:postgresql://localhost/musicplayer";
        private final static String user = "postgres";
        private final static String password = "password";
    public static void main(String args[]) throws IOException
    {

        byte[] bytes = Files.readAllBytes(Paths.get("C:\\Users\\Sohan\\Downloads\\Far Away.mp3"));

        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "insert into songs(title, artist, duration, song) values(?, ?, ?, ?)";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, "Far Away");
            state.setString(2, "Sworn");
            state.setString(3, "1:47");
            state.setBytes(4, bytes);
            state.executeUpdate();
            System.out.println("Inserted");
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

        
    }   

}