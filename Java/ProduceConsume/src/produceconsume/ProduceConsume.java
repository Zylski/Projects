package produceconsume;

/*
 * Programmer: Damian Zylski
 * Project:    ProduceConsume
 * Date:       03/11/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To code the producer consumer problem where producers and consumers
 * share a resource such as an array and wait to use the resource
 */

import java.util.LinkedList;
import java.util.Queue;

public class ProduceConsume
{
    
    //Create a new Queue that acts as the buffer
    volatile static Queue <Integer> q = new LinkedList <Integer>();
    
//*handleThreads***************************************************************************
    //handles the producer consumer problem
    public static void handleThreads()
    {
        
        
        //create threads
        //Producer pushes ints
        Thread producer = new Thread()
        {
            @Override
            public void run()
            {
                //Announce that thread is alive
                System.out.println("Producer is Alive");
                
                //make counter
                int i = 0;
                
                //Create Integer to add
                Integer num = 7;
                
                //loop and perform operations on Queue, add ints to queue
                while(i < 1000)
                {
                    try
                    {
                        synchronized(q)
                            {
                        //Check if Queue isn't full
                        if(q.size() <= 7)
                        {
                            //Obtain lock for q and add elements to q
                            
                                System.out.println("Producer adding " + num + " to Queue");
                                q.offer(num);
                                //notify that q has an element added in case consumer is waiting
                                q.notify();

                            
                        }
                        //else if q is full
                        else
                        {
                            //wait until q isn't full
                            q.wait(5);
                            System.out.println("Producer is waiting");
                        }
                            }
                    }
                    catch(InterruptedException | IllegalStateException | IllegalMonitorStateException e)
                    {
                        e.printStackTrace();
                    }
                    
                    i++;
                }
            }
        };
        
        //Consumer pops ints
        Thread consumer = new Thread()
        {
            @Override
            public void run()
            {
                System.out.println("Consumer is Alive");
                
                int i = 0;
                
                while(i < 1000)
                {
                    try
                    {
                        //Obtain lock for q and remove elements from q
                            synchronized(q)
                            {
                        //Check if Queue isn't empty
                        if(!q.isEmpty())
                        {
                            
                                System.out.println("Consumer removing " + q.poll() + " from Queue");
                                //notify that q has an element removed in case producer is waiting
                                q.notify();
                            

                        }
                        //else if q is empty
                        else
                        {
                            //wait until q isn't empty
                            q.wait(5);
                            System.out.println("Consumer is waiting");
                        }
                            }
                    }
                    catch(InterruptedException | IllegalStateException | IllegalMonitorStateException e)
                    {
                        e.printStackTrace();
                    }
                    
                    i++;
                }
            }
        };
        
        
        //Start new threads
        new Thread(consumer).start();
        new Thread(producer).start();



        
        
    }
//*main***************************************************************************
    public static void main(String[] args)
    {
        //run handleThreads
        try
        {
        handleThreads();
        }
        catch(RuntimeException e)
        {
            
        }
    }
    
}
