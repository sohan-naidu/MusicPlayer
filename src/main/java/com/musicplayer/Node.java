package com.musicplayer;

public class Node{

    private Song song;
    private Node next;
    private Node previous;

    public void setSong(Song song)
    {
        this.song = song;
    }

    public void setNextSong(Node node)
    {
        this.next = node;
    }
    
    public void setPreviousSong(Node node)
    {
        this.previous = node;
    }
    

    public Song getSong()
    {
        return song;
    }

    public Node getNextSong()
    {
        return next;
    }

    public Node getPreviousSong()
    {
        return previous;
    }

    public Node createNode(Song song, Node next, Node previous)
    {
        Node temp = new Node();
        temp.setSong(song);
        temp.setNextSong(next);
        temp.setPreviousSong(previous);
        return temp;
    } 
    
}
