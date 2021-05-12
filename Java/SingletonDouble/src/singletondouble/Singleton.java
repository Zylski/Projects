package singletondouble;

/**
 *
 * @author Zylski
 * Singleton Class
 * 3/11/2021
 * Used to create instance of singleton
 * Based on Promad's example
 */
public class Singleton
{
    //Setup a private instance of singleton
    volatile private static Singleton instance = null;
    
    //some attribute
    private String  s;
    
    //Private singleton constructor
    private Singleton()
    {
        s = "I am Singleton";
    }
    
    //Method to get instance
    public static Singleton getInstance()
    {
        //don't enter synchronized area if instance exists. would be bad for performance
        if(instance == null)
        {
            //synchronized (instance)
            synchronized (Singleton.class) 
            {
                if(instance == null)
                {
                    instance = new Singleton();
                }
             }
        }
        return instance;
    }
    
    //Method to get string
    public String getString()
    {
        return s;
    }
    
}
