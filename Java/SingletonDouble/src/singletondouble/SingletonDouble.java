package singletondouble;
/*
 * Programmer: Damian Zylski
 * Project:    SingletonDemo
 * Date:       03/11/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To implement singleton with double lock checking
 */
public class SingletonDouble
{
    
    //Check if same instance
    public static boolean sameInstance(Singleton s1, Singleton s2)
    {
        return s1.equals(s2);
    }
    
    public static void main(String[] args)
    {
        Singleton s1 = Singleton.getInstance();
        
        Singleton s2 = Singleton.getInstance();
        
        System.out.println(sameInstance(s1,s2));
        
        System.out.println(s1.getString());
    }
    
}
