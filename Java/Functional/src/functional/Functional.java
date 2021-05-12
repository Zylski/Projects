package functional;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Programmer: Damian Zylski
 * Project:    Functional
 * Date:       03/14/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To create functional methods to perform operations on numbers
 * such as returning right most digits and doubling items
 */

public class Functional
{

    //*rightDigit************************************************************************
        //returns the right most digit of each num in list
        public static List rightDigit(List nums)
        {
            return (List) nums.stream().map((n) -> ((int)n % 10)).collect(Collectors.toList());
        }

    //*doubling************************************************************************
        //doubles each num in list
        public static List doubling(List nums)
        {
            return (List) nums.stream().map((n) -> ((int)n * 2)).collect(Collectors.toList());
        }

    //*NoX************************************************************************
        //removes all x in list
        public static List noX(List strings)
        {
            return (List) strings.stream().map((string) -> (FunctionalInt.removeX((String)string))).collect(Collectors.toList());
        }

    
    
    public static void main(String[] args)
    {
        
        
        //Return new list with only right most digits
        try
        {
        //create list and add nums
        List <Integer> nums1 = new ArrayList <Integer> ();
        nums1.add(1);
        nums1.add(22);
        nums1.add(93);
        
        nums1 = rightDigit(nums1);
        
        //output
        for(Integer i : nums1){System.out.println(i);}
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch(IllegalArgumentException | ArrayIndexOutOfBoundsException | InputMismatchException e )
        {
            e.printStackTrace();
        }
        
        
        
        
        
        try
        {
        //create list and add nums
        List <Integer> nums1 = new ArrayList <Integer> ();
        //clear list, add new nums
        nums1.clear();
        nums1.add(6);
        nums1.add(8);
        nums1.add(6);
        nums1.add(8);
        nums1.add(-1);
        
        //double digits
        nums1 = doubling(nums1);
        
        //output
        System.out.println("");
        for(Integer i : nums1){System.out.println(i);}
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch(IllegalArgumentException | ArrayIndexOutOfBoundsException | InputMismatchException e )
        {
            e.printStackTrace();
        }
        
        //remove x
        try
        {
        //Make new string list
        List <String> strings = new ArrayList <String> ();
        strings.add("xxax");
        strings.add("xbxbx");
        strings.add("xxcx");
        strings.add("");
        
        strings = noX(strings);
        
        //output
        System.out.println("");
        for(String s : strings){System.out.println(s);}
        }
        catch(NumberFormatException e)
        {
            e.printStackTrace();
        }
        catch(IllegalArgumentException | ArrayIndexOutOfBoundsException | InputMismatchException e )
        {
            e.printStackTrace();
        }
        
        
        
        
        

    }
    
}
