package com.musicplayer;

public class Queue {

    private Node head;

    public Queue()
    {
        this.head = new Node(null, null, null);
    }

    public void setHead(Node head)
    {
        this.head = head;
    }

    public Node getHead()
    {
        return head;
    }
  

    public Queue addToQueue(Song song, Queue queue)
    {
        Node temp = new Node(song, null, null);
        if(queue.getHead().getNextSong() == null)
        {
            queue.getHead().setNextSong(temp);
            temp.setPreviousSong(queue.getHead());
            return queue;
        }
        Node cur = new Node(null, null, null);
        cur = queue.getHead();
        while(cur.getNextSong() != null)
        {
            cur = cur.getNextSong();
        }

        cur.setNextSong(temp);
        temp.setPreviousSong(cur);

        return queue;
    }
             
}

