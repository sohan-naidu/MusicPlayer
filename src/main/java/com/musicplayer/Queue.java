package com.musicplayer;

public class Queue {

    private Node head;

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
        Node temp = new Node();
        temp = temp.createNode(song, null, null);

        if(queue.getHead().getNextSong() == null)
        {
            queue.getHead().setNextSong(temp);
            temp.setPreviousSong(queue.getHead());
            return queue;
        }

        Node cur = new Node();
        cur = queue.getHead();
        while(cur.getNextSong() != null)
        {
            cur = cur.getNextSong();
        }

        cur.setNextSong(temp);
        temp.setPreviousSong(cur);

        return queue;
    }


    public void printQueue(Queue queue)
    {
        Node cur = new Node();
        cur = queue.getHead();
        while(cur.getNextSong() != null)
        {
            cur = cur.getNextSong();
            System.out.println(cur.getSong());
        }
        
        System.out.println();
        while(cur.getPreviousSong() != null)
        {
            System.out.println(cur.getSong());
            cur = cur.getPreviousSong();
            
        }
    }

    public Queue createQueue()
    {
        Node head = new Node();
        Queue queue = new Queue();
        head = head.createNode(null, null, null);
        queue.setHead(head);
        return queue;
    }
             
}

