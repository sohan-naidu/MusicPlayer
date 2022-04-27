package com.musicplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;


public class searchDB {

    private final static String url = "jdbc:postgresql://localhost/musicplayer";
    private final static String user = "postgres";
    private final static String password = "password";

    public static DefaultTableModel search(String query)
    {

        Connection conn = null;
        DefaultTableModel model = new DefaultTableModel();
        try
        {
            conn = DriverManager.getConnection(url, user, password);
            String sql = "select id, title, artist, duration from songs where title ilike concat('%',?,'%')";
            PreparedStatement state = conn.prepareStatement(sql);
            state.setString(1, query);
            ResultSet res = state.executeQuery();


            
            int columnCount = res.getMetaData().getColumnCount();

            Vector<String> columnNames = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                columnNames.add(res.getMetaData().getColumnName(columnIndex));
            }
            
            columnNames.add("Add to Queue");


            Vector<Vector<Object>> dataVector = new Vector<>();
            while (res.next()) {
                Vector<Object> rowVector = new Vector<>();
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    
                    rowVector.add(res.getObject(columnIndex));
                    
                    
                }

                dataVector.add(rowVector);
                
                
            }

            model = new DefaultTableModel(dataVector, columnNames);

            
            
        }

        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
        return model;
    }
}
