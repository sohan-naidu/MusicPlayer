package com.musicplayer;

public class Song
{
    private String id;
    private String title;
    private String artist;
    private String duration;

    public Song(String id, String title, String artist, String duration)
    {
        this.id = id;
        this.title = title;
        this.artist = artist;
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

}