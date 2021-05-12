package deadlock;
/*
 * Programmer: Damian Zylski
 * Project:    Deadlock
 * Date:       03/11/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To create an instance of deadlock between a few threads
 */
public class Deadlock
{
//*deadLock********************************************************************
    //Tries to create deadlock
    public static void deadLock()
    {
        //create circle objects
        Circle c1 = new Circle(5.0);
        Circle c2 = new Circle(6.0);
        Circle c3 = new Circle(7.0);
        Circle c4 = new Circle(8.0);
        
        //create thread from Thread class
        //thread1
        Thread t1 = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    //acquire lock for c1
                    synchronized(c1)
                    {
                        //sleep 50 seconds then try to get lock for c2
                        //Still have lock c1 in here!
                        Thread.sleep(50);
                        synchronized(c2)
                        {
                            System.out.println("Thread 1 has c1 and c2");
                        }
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        //Create threads from our new Threads class
        //Thread2
        Threads t2 = new Threads()
        {
            @Override
            public void run()
            {
                try
                {
                    //thread 2 has lock c2 while t1 is sleeping, tries to get
                    //lock for c1 but t1 has it
                    synchronized(c2)
                    {
                        Thread.sleep(50);
                        synchronized(c1)
                        {
                            System.out.println("Thread 2 has c1 and c2");
                        }
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        //thread3
        Threads t3 = new Threads()
        {
            @Override
            public void run()
            {
                try
                {
                    //t3 gets lock c3. Can't get lock c2 because t2 has it.
                    //stuck with lock c3 until c2 gets released
                    synchronized(c3)
                    {
                        Thread.sleep(50);
                        synchronized(c2)
                        {
                            System.out.println("Thread 3 has c3 and c2");
                        }
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        //thread4
        Threads t4 = new Threads()
        {
            @Override
            public void run()
            {
                try
                {
                    //t4 just gets lock 4 so it is fine and realeases lock
                    synchronized(c4)
                    {
                        Thread.sleep(50);
                        synchronized(c4)
                        {
                            System.out.println("Thread 4 just has c4");
                        }
                    }
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        
        
        //Try to create deadlock situation
        //Start threads
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
        new Thread(t4).start();
        System.out.println("This is a deadlock");
    } 
//*mian*************************************************************************    
    public static void main(String[] args)
    {
        //Create deadlock
        deadLock();
        
        
    }
    
}
