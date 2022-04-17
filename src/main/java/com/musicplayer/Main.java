package com.musicplayer;

public class Main
{
    public static void main(String[] args)
    {



        Queue queue = new Queue();
        queue = queue.createQueue();

        
        Song song1 = new Song();
        Song song2 = new Song();
        Song song3 = new Song();

        song1 = song1.createSong("1", "Song 1", "YOur mom", "4:20");
        song2 = song2.createSong("2", "Song 2", "YOur dad", "4:21");
        song3 = song3.createSong("3", "Song 3", "YOur", "4:22");


        queue = queue.addToQueue(song1, queue);
        //queue = queue.addToQueue(song2, queue);
        //queue = queue.addToQueue(song3, queue);

        MusicPlayer musicPlayer = new MusicPlayer(queue);


        //queue.printQueue(queue);


        
    }
    
}
