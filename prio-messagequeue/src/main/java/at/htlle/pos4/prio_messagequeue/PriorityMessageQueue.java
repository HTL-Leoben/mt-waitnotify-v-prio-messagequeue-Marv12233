package at.htlle.pos4.prio_messagequeue;

import java.util.LinkedList;

public class PriorityMessageQueue
{
    private final int maxSize;
    private final LinkedList<Message> priorityQueue = new LinkedList<>();
    private final LinkedList<Message> normalQueue = new LinkedList<>();

    public PriorityMessageQueue(int maxSize)
    {
        this.maxSize = maxSize;
    }

    public synchronized void sendMessage(String producerName, Message msg)
    {
        while (size() >= maxSize) {
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        if (msg.isPriority())
        {
            priorityQueue.addLast(msg);
        } else
        {
            normalQueue.addLast(msg);
        }

        System.out.println(producerName + " sendMessage(" + msg.isPriority() + "): " + msg.getContent());
        notifyAll();
    }

    public synchronized Message receiveMessage(String consumerName)
    {
        while (size() == 0)
        {
            System.out.println(consumerName + " receiveMessage(): WAIT");
            try
            {
                wait();
            } catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        Message msg;
        if (!priorityQueue.isEmpty())
        {
            msg = priorityQueue.removeFirst();
        } else
        {
            msg = normalQueue.removeFirst();
        }

        System.out.println(consumerName + " receiveMessage(): " + msg.isPriority() + ", " + msg.getContent());
        notifyAll();
        return msg;
    }

    private int size()
    {
        return priorityQueue.size() + normalQueue.size();
    }
}