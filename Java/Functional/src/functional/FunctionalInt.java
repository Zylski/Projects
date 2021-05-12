package functional;

/**
 *
 * @author Zylski
 * Functional interface
 * 3/14/21
 * 
 */
@FunctionalInterface
public interface FunctionalInt
{
    public void function();
    
    public static String removeX(String s)
    {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i) != 'x')
            {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
