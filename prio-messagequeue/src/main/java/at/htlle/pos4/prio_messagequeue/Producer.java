package at.htlle.pos4.prio_messagequeue;
import java.util.concurrent.ThreadLocalRandom;

class Producer extends Thread
{
    // Producer name
    private String name;
    private PriorityMessageQueue queue;

    // Constructor, getter, setter
    Producer(String name, PriorityMessageQueue queue)
    {
        this.name = name;
        this.queue = queue;
    }

    // Produce at random time Messages
    @Override
    public void run()
    {
        int counter = 1;
        while (true)
        {
            boolean isPriority = ThreadLocalRandom.current().nextBoolean();
            String content = "MessageContent" + counter++;
            Message msg = new Message(isPriority, content);
            queue.sendMessage(name, msg);

            try
            {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500, 1500));
            } catch (InterruptedException e)
            {
                break;
            }
        }
    }
}
