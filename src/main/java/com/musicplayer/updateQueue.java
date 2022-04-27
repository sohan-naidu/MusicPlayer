package com.musicplayer;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class updateQueue {

    public DefaultTableModel update(Queue queue)
    {
        Vector<Object> col = new Vector<>();
        Vector<Vector<Object>> dataVector = new Vector<>();

        col.add("ID");
        col.add("Title");
        col.add("Artist");
        col.add("Duration");

        if(queue.getHead().getNextSong() != null)
        {
            Node node = queue.getHead();
            Vector<Object> rowVector = new Vector<>();
            while(node.getNextSong() != null)
            {	
                node = node.getNextSong();
                System.out.println(node.getSong().getID());
                rowVector.add(new Object[]{node.getSong().getID(), node.getSong().getTitle(), node.getSong().getArtist(), node.getSong().getDuration()});
            }
        }

        DefaultTableModel model = new DefaultTableModel(dataVector, col);
        
        return model;
    }
    
}
