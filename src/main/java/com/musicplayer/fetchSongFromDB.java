package com.musicplayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class fetchSongFromDB {

    private final static String url = "jdbc:postgresql://localhost/musicplayer";
    private final static String user = "postgres";
    private final static String password = "password";

    public void fetch(String songID)
    {

        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select * from songs where id = ?";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setInt(1, Integer.parseInt(songID));
            ResultSet res = state.executeQuery();
            while(res.next())
            {
                String id = res.getString("id");
                String title = res.getString("title");
                String artist = res.getString("artist");
                String duration = res.getString("duration");
                byte[] bytes = res.getBytes("song");
                Song song = new Song();
                //song.createSong(id, title, artist, duration);
                Path path = Paths.get("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\Final\\src\\main\\java\\com\\musicplayer\\cursong.mp3");
                try {
                    Files.write(path, bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Fetched song " + title);
            }
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
}
