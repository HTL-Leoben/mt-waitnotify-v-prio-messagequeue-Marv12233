package at.htlle.pos4.prio_messagequeue;
import java.util.concurrent.ThreadLocalRandom;

class Consumer extends Thread
{
    // Consumer name
    private String name;
    private PriorityMessageQueue queue;

    // Constructor, getter, setter
    public Consumer(String name, PriorityMessageQueue queue)
    {
        this.name = name;
        this.queue = queue;
    }


    // Consume at random time messgaes
    @Override
    public void run()
    {
        while (true)
        {
            Message msg = queue.receiveMessage(name);
            System.out.println(name + " verarbeitet: " + msg);

            try
            {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 2000));
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }
}
