package com.musicplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class Auth {


    private final static String url = "jdbc:postgresql://localhost/musicplayer";
    private final static String user = "postgres";
    private final static String password = "password";

    public static boolean auth(String username, String userpass)
    {

        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select pwd from authentication where username = ?";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, username);
            ResultSet res = state.executeQuery();
            //if(res != null)
            {
                while(res.next())
                {
                    System.out.println(userpass);
                    System.out.println(res.getString("pwd"));
                    String db = res.getString("pwd");
                    if(db.equals(userpass))
                    {
                        //System.out.println("ENTERED");
                        return true;
                    }
                    //System.out.println("NOT ENTERED");
                    return false;
                }
                
            }

            //return false;
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

