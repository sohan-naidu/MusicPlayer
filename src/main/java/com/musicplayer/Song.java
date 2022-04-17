package com.musicplayer;

public class Song
{
    private String id;
    private String title;
    private String artist;
    private String duration;

    public void setID(String id)
    {
        this.id = id;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setArtist(String artist)
    {
        this.artist = artist;
    }

    public void setDuration(String duration)
    {
        this.duration = duration;
    }


    public String getID()
    {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist()
    {
        return artist;
    }

    public String getDuration()
    {
        return duration;
    }

    @Override
    public String toString()
    {
        return(id + " " + title + " " + artist + " " + duration); 
    }

    public Song createSong(String id, String title, String artist, String duration)
    {
        Song temp = new Song();
        temp.setID(id);
        temp.setTitle(title);
        temp.setArtist(artist);
        temp.setDuration(duration);
        return temp;
    }
}