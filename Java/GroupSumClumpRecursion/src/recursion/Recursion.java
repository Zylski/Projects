package recursion;

import java.util.Arrays;
import java.util.NoSuchElementException;

/*
 * Programmer: Damian Zylski
 * Project:    Recursion
 * Date:       03/13/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To code the recursive solution to find if a list of ints can equal
 * to a target number by adding the ints up. But if 2 ints are adjacent in the list
 * then they must either be all added together or not added at all.
 */
public class Recursion
{
    
//*groupSumClump*****************************************************************************
    //The recursive solution to finding if a list of nums can equal a target num based on the 
    //above mentioned conditions
    //I will work on trying to get the target first, then on thr adjacent condition
    public static boolean groupSumClump(int index, int arr [], int target) //MAybe we can subtract from target?
    {
        System.out.println("Target:" + target);
        
        //variables
        int sum = 0;
        int indexIncrease = 0;
        int len = arr.length;
        boolean adjCheck = false;
        //check for adjacent nums
        if(index < len - 1)
        {
            if(arr[index] == arr[index + 1])
            {
                adjCheck = true;
                //check the extent of similar values
                while(index < len)
                {
                    if(index == len-1)
                    {
                        sum = sum + arr[index];
                        //index = index + 1;
                        //target = target - sum;
                        //System.out.println("sum:" + sum);
                        //System.out.println("ArrValue:" + arr[index]);
                        System.out.println("check: " + adjCheck);
                        break;
                    }
                    if(arr[index] == arr[index + 1])
                    {
                        //sum together adjacent values and increment index
                        index = index + 1;
                        sum = sum + arr[index];
                        
                    }
                    else
                    {
                        sum = sum + arr[index];
                       // index = index + 1;
                        //target = target - sum;
                        //System.out.println("sum:" + sum);
                        System.out.println("ArrValue:" + arr[index]);
                        System.out.println("check: " + adjCheck);
                        break;
                    }
                }
            }
        }
            
        //base case
        if(target == 0)
        {
            //some amount of nums subtracted from target make it zero! return true
            return true;
        }
        else if(index == arr.length || index == -1)
        {
            return false;
        }
        
        else
        {
            //recur, we'll recur twice, once where we subtract current num and once where we don't
            if(adjCheck)
            {
                return ((groupSumClump(index + 1, arr, target - sum)) || (groupSumClump(index + 1, arr, target)));
            }
            else
            {
                return ((groupSumClump(index + 1, arr, target - arr[index])) || (groupSumClump(index + 1, arr, target)));
            }
        }

        
    }

//*main*****************************************************************************
    public static void main(String[] args)
    {
        //Create array
        int arr [] = {2,2,2,2,2,2,4,8};
        
        //run recursive solution
        try
        {
        System.out.println(groupSumClump(0, arr, 14));
        }
        catch(IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        catch(ArrayIndexOutOfBoundsException | NoSuchElementException e )
        {
            e.printStackTrace();
        }
        

    }
    
}
