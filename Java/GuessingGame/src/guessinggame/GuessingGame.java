package guessinggame;
/*
 * Programmer: Damian Zylski
 * Project:    GuessingGame
 * Date:       03/08/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To have the user try to guess a number in 5 turns. If the user
 * guesses within 10 numbers of the correct number, then he wins, otherwise they
 * have to try again. Correct number is displayed upon winning and losing
 */

import java.util.Scanner;
import java.util.Random;

public class GuessingGame
{
//*randGen***************************************************************************
//Generates the random number
    public static int randGen()
    {
        //Generate new random number
        Random random = new Random(System.currentTimeMillis()); 
        int num = 0;
        //Using system time as a seed
        num = random.nextInt(100) + 1;
        
        return num;
    }
    
//*game*************************************************************************
//Plays the game with the user
    public static void game(int num)
    {
        //Variables
        int tries = 5;
        int guess = 0;
        int low = 0; //lower bound
        int high = 0; //upper bound
        int i = 0;
        int turn = 1;
        Scanner scnr = new Scanner(System.in);
        
        //figure lower and upper bounds
        low = num - 10;
        high = num + 10;
        
        //Adjust bounds
        if(low < 1)
        {
            low = 1;
        }
        if(high > 100)
        {
            high = 100;
        }
        
        //Give user the rules
        System.out.println("Try to guess the secret number in 5 tries!");
        System.out.println("The number is in the range of 1-100.");
        System.out.println("Your guess can be within 10 higher or lower then the number\n");
        
        //Loop and play the game
        while(tries > 0)
        {
            //prompt user for guess
            System.out.println("What is your guess?");
            System.out.print(turn + ". ");
            guess = scnr.nextInt();
            
            //Make sure input is within correct range
            if(guess < 1 || guess > 100)
            {
                //Notify of error and try again
                System.out.println("Your guess must be within 1-100!\n");
                continue;
            }
            
            //Check if guess is in correct range, maybe theres a better way to check
            for(i = low; i <= high; i++)
            {
                if(i == guess)
                {
                    System.out.println("\nGood job! The number is " + num);
                    tries = -1;
                    continue;
                }
            }
            
            //decrement tries
            tries--;
            //increment turns
            turn++;
            
            //If the player ran out of tries, game over!
            if(tries == 0)
            {
                System.out.println("\nSorry, the number is " + num);
            }
            
        }
           
        return;
    }    
//*main*************************************************************************
    public static void main(String[] args)
    {
        //variables
        int num = 0; //the num to be guessed
        
        //generate random number
        num = randGen();
        
        System.out.println(num);
        
        //Play the game
        game(num);
    }
    
}
