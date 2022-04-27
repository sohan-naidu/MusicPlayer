package com.musicplayer;

public class Node{

    private Song song;
    private Node next;
    private Node previous;

    public Node(Song song, Node next, Node previous)
    {
        this.song = song;
        this.next = next;
        this.previous = previous;
    }

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
    
}
