package com.musicplayer;


import javazoom.jl.player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MusicPlayer implements ActionListener {

    JFrame frame;
    JLabel songName, queueEmptyLabel;
    JButton select, addSong;
    JPanel queuePanel, controlPanel, emptyQueuePanel;
    Icon iconPrevious, iconPause, iconResume, iconNext;
    JButton previous, pause, resume, next;




    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;

    String filename, filePath;
    long totalLength, pauseLength;


    Player player;
    Thread playThread, resumeThread;

    Node current = new Node();


    //JList list;

public MusicPlayer(Queue queue) {

        current = queue.getHead().getNextSong();
        System.out.println(current);
        initUI();
        addActionEvents();
        if(current != null)
        {
            Runnable runnablePlay = new runnablePlay(current);
            Runnable runnableResume = new runnableResume();
            playThread = new Thread(runnablePlay);
            resumeThread = new Thread(runnableResume);
        }


    }

    public void initUI() {

        songName = new JLabel("", SwingConstants.CENTER);

        queuePanel = new JPanel();
        controlPanel = new JPanel();

        
        /*DefaultListModel<Node> list = new DefaultListModel<>();
        if(current != null)
        {
            int i = 0;
            Node it = current;
            while(it != null)
            {
                list.add(i, current);
                i++;
                it = it.getNextSong();
            }
        }
        
        JList<Node> jlist = new JList<>(list);
        jlist.setBounds(100,100, 75,75);
        queuePanel.setLayout(new GridLayout(1, 1));
        JLabel temp = new JLabel("working");  
        queuePanel.add(temp);
        queuePanel.add(jlist);*/




        /*if(current == null)
        {
            emptyQueuePanel.setLayout(new BorderLayout());
            queueEmptyLabel = new JLabel("Queue is empty.", SwingConstants.CENTER);
            addSong = new JButton("Add songs to queue");
            //addSong.setBounds(60, 400, 220, 30);
            emptyQueuePanel.add(queueEmptyLabel, BorderLayout.CENTER);
            emptyQueuePanel.add(addSong, BorderLayout.CENTER);
            
        }*/


        iconPrevious= new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\rewind-icon.png");
        iconResume = new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\play-icon.png");
        iconPause = new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\pause-icon.png");
        iconNext = new ImageIcon("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\music-player-java-code\\DataFlair-Mp3MusicPlayer\\music-player-icons\\forward-icon.png");


        previous = new JButton(iconPrevious);
        pause = new JButton(iconPause);
        resume = new JButton(iconResume);
        next = new JButton(iconNext);


        controlPanel.setLayout(new GridLayout(1, 4));


        controlPanel.add(previous);
        controlPanel.add(pause);
        controlPanel.add(resume);
        controlPanel.add(next);

        previous.setBackground(Color.WHITE);
        pause.setBackground(Color.WHITE);
        resume.setBackground(Color.WHITE);
        next.setBackground(Color.WHITE);


        frame = new JFrame();

        frame.setTitle("Music Player");

        //frame.add(emptyQueuePanel, BorderLayout.CENTER);
        frame.add(queuePanel, BorderLayout.NORTH);
        frame.add(controlPanel, BorderLayout.SOUTH);
        


        frame.setBackground(Color.white);
        frame.setSize(800, 800);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

    }

    public void addActionEvents() {

        previous.addActionListener(this);
        pause.addActionListener(this);
        resume.addActionListener(this);
        next.addActionListener(this);
    }
    
    public Thread playprevious()
    {
        current = current.getPreviousSong();
        Runnable runnablePlay = new runnablePlay(current);
        playThread = new Thread(runnablePlay);
        return playThread;
    }

    public Thread playnext()
    {
        current = current.getNextSong();
        Runnable runnablePlay = new runnablePlay(current);
        playThread = new Thread(runnablePlay);
        return playThread;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

 
        
        if (e.getSource().equals(previous)) {
            
            playThread  = playprevious();
            if (filename != null) {
                playThread.start();
                songName.setText("Now playing : " + filename);
            } else {
                songName.setText("No File was selected!");
            }

        }

        if (e.getSource().equals(pause)) {

            if (player != null && filename != null) {
                try {
                    pauseLength = fileInputStream.available();
                    player.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        if (e.getSource().equals(resume)) {

            if (filename != null) {
                resumeThread.start();
            } else {
                songName.setText("No File was selected!");
            }
        }


        if (e.getSource().equals(next)) {
            
            playThread  = playnext();
            if (filename != null) {
                playThread.start();
                songName.setText("Now playing : " + filename);
            } else {
                songName.setText("No File was selected!");
            }
 

        }

    }
    

    File myFile = new File("C:\\Study\\Third Year\\Object Oriented Development with Java\\Project\\Final\\src\\main\\java\\com\\musicplayer\\cursong.mp3");
    public class runnablePlay implements Runnable
    {
        public runnablePlay(Node current)
        {
            String curSong = current.getSong().getID();
            System.out.println(curSong);
            fetchSongFromDB fetched = new fetchSongFromDB();
            fetched.fetch(curSong);
        }


        public void run() {
            try {                
                fileInputStream = new FileInputStream(myFile);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                player = new Player(bufferedInputStream);
                totalLength = fileInputStream.available();
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
    
        };

    }


    public class runnableResume implements Runnable
    {

        public void run() {
            try {
                fileInputStream = new FileInputStream(myFile);
                bufferedInputStream = new BufferedInputStream(fileInputStream);
                player = new Player(bufferedInputStream);
                fileInputStream.skip(totalLength - pauseLength);
                player.play();
            } catch (Exception e) {
                e.printStackTrace();
            }
    
        };

    }

}