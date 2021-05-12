package lambdas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/*
 * Programmer: Damian Zylski
 * Project:    Lambdas
 * Date:       03/14/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To create a series of lambda check methods that will check numbers
 * from input
 */
public class Lambdas
{
//*isOdd************************************************************************
    //checks if number is odd or even
    public static String isOdd(Integer i)
    {
        
        LambdaInt l = (n) -> {if(n % 2 == 0)
        {return "EVEN";}
        else
        {
            return "ODD";
        }};        
        
        return l.checkNums(i); 
    }
    
//*isPrime************************************************************************
    //checks if number is prime
    public static String isPrime(Integer num)
    {
        
        LambdaInt l = (n) -> {for(int i = 2; i < n; i++ )
        {if(n % i == 0) //If any num it divides by has no remainder, it is not prime!
        {return "COMPOSITE";}
        }return "PRIME";        };
                
        
        return l.checkNums(num); 
    }
    
//*isPalindrome************************************************************************
    //checks if number is palindrome
    public static String isPalindrome(Integer num)
    {
        
        LambdaInt l = (n) -> {String numString = String.valueOf(n);
                           StringBuilder reversed = new StringBuilder("");
        for(int i = numString.length()-1; i >= 0; i--){reversed.append(numString.charAt(i));}
        if(reversed.toString().equals(numString))
        {
            return "PALINDROME";
        }
        else
        {
            return "NOT PALINDROME";
        }};
                
        return l.checkNums(num); 
    }
    
//*performOperation************************************************************************
    //Performs operations based on input. Not sure what input is. Read from input file maybe?
    public static boolean performOperation() throws IOException
    {
        FileInputStream f = null;
        Scanner file = null;
        int n = 0;
        int option = 0;
        Integer num = 0;
        int i = 0;
        boolean passedTest = true;
        
        //try to open file
        try
        {
            f = new FileInputStream("input.dat");
            file = new Scanner(f);
            
            //loop and read file
            while(file.hasNextInt())
            {
                //read num of cases first
                if(i == 0)
                {
                    n = file.nextInt();
                }
                
                //read in option
                option = file.nextInt();
                //read in num
                num = file.nextInt();
                
                //perform operation based on num and option
                switch(option)
                {
                    case 1: System.out.println(isOdd(num));
                    break;
                    case 2: System.out.println(isPrime(num));
                    break;
                    case 3: System.out.println(isPalindrome(num));
                    break;
                    default : System.out.println("Error!");
                    break;
                }
                
                i++;
            }
            
            //close file
            file.close();
            f.close();
            
            return passedTest;
            
        }
        catch(InputMismatchException | NumberFormatException e)
        {
            e.printStackTrace();
            System.out.println("Bad Input!");
            passedTest = false;
        }
        catch(FileNotFoundException | NoSuchElementException e)
        {
            e.printStackTrace();
            System.out.println("File is missing or corrupt");
            passedTest = false;
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("File is missing or corrupt");
            passedTest = false;
        }
        
        return passedTest;
    }
    
//*main************************************************************************
    public static void main(String[] args) throws IOException
    {
        //Perform operations
        performOperation();
    }
    
}
