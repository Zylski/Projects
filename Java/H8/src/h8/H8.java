package h8;
/*
 * Programmer: Damian Zylski
 * Project:    H8
 * Date:       03/01/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To read in a series of integers and sort them using a queue and 
 * radix sort
 */

import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.lang.Math;
import java.util.Iterator;

public class H8
{
//**enqueueNums**************************************************************************************
//Gets the nums from input and adds them to the queue
    public static int enqueueNums(Queue <Integer> queue) throws IOException
    {
        //variables
        int digits = 0;
        int max = 0;
        int num = 0;
        Scanner file = null;
        FileInputStream f = null;
        IOException io = new IOException();
        
        //try to open the file
        try
        {
            f = new FileInputStream("Radix.txt");
            file = new Scanner(f);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File is missing or corrupt!");
            Logger.getLogger(H8.class.getName()).log(Level.SEVERE,null,e);
            throw io;
        }
        
        //Loop and enqueue the queue
        while(file.hasNextInt())
        {
            //grab the next num
            num = file.nextInt();
            
            //add num to the queue
            queue.offer((Integer)num);
            
            //keep track of the biggest digit
            max = Math.max(max, num);
        }
        
        //figure out the number of digits in the biggest num
        while(max != 0)
        {
            //increment digits
            digits++;
            
            //remove right most digit and loop
            max = max / 10;
        }
		
		//close file
		file.close();
		f.close();
        
        return digits;
    }
    
//**radixSort**************************************************************************************
//Performs radix sort on the nums on returns the sorted queue
    public static void radixSort(Queue <Integer> queue, int digits)
    {
        //variables
        int currDigit = 0;
        Integer num = 0;
        int i = 0;
        int j = 0;
        int divideBy = 1;
        Queue <Integer> radixQueue [] = new Queue [10];
        
        //Initialize the radixQueue Array which will store nums based on digits
        for(i = 0; i < 10; i++)
        {
            radixQueue[i] = new LinkedList <Integer>();
        }
        
        //loop and perform radix sort on the queue
        for(i = 0; i < digits; i++)
        {
            //dequeue the queue until it is empty
            while(!queue.isEmpty())
            {
                //determine the next number in the queue
                num = (Integer)queue.peek();
                
                //Determine next range of digits in num based on divideBy
                num = num / divideBy;
                
                //grab the right most digit
                currDigit = num % 10;
                
                //Based on the digit that was grabbed, enqueue the radixqueue, dequeue the original queue
                radixQueue[currDigit].offer((Integer)queue.poll());             
            }
            
            //Now enqueue the original queue with what is in the RadixQueue
            for(j = 0; j < 10; j++)
            {
                while(!radixQueue[j].isEmpty())
                {
                    queue.offer(radixQueue[j].poll());
                }
            }
            
            //Now multiply divideBy by 10 to move left one digit on the next pass
            divideBy = divideBy * 10;
        }
        
        
        return;
    }
    
//**printQueue**************************************************************************************
//prints out the sorted queue
    public static void printQueue(Queue <Integer> queue)
    {
        //Variables
        Iterator <Integer> iter = queue.iterator();
        
        //Loop and print elements of the queue
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }
        
        return;
    }
  
//seq**********************************************************************************************
//test out a recursive seq
    public static int seq(int n, int sum)
    {
        if(n < 1)
        {
           return sum + 1; 
        }
        
        else
        {
            return seq(n-1,sum+3) + sum ;
        }
    }
    
    
//**Main**************************************************************************************
    public static void main(String[] args) throws IOException
    {
        //Variables
        int numDigits = 0;
        Queue <Integer> queue = new LinkedList <Integer>();
        
        //Create the queue full of nums and figure out the highest number of digits
        numDigits = enqueueNums(queue);
       
        //perform radix sort on the nums
        radixSort(queue, numDigits);
        
        //print out the sorted queue
        printQueue(queue);
        
        return;
    }
    
}
