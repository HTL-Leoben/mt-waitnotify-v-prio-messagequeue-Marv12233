package at.htlle.pos4.prio_messagequeue;

public class Message
{
    boolean isPriority = false;
    String content;

    // Constructor, getter, setter

    public Message(boolean isPriority, String content)
    {
        this.isPriority = isPriority;
        this.content = content;
    }

    public boolean isPriority()
    {
        return isPriority;
    }

    public void setPriority(boolean priority)
    {
        isPriority = priority;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }
}
