package deadlock;
/**
 *
 * Damian Zylski
 * Threads Class
 * 3/12/21
 * Practice extending a thread class
 */
public class Threads extends Thread
{
    //constructor
    public Threads()
    {
        super();
    }
    
    //Need to implement the run method
    @Override
    public void run()
    {
        System.out.println("Thread is running.");
    }
}
