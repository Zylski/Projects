package h7;
/*
 * Programmer: Damian Zylski
 * Project:    H7
 * Date:       07/02/2020
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To check a series of words to see if you move the first letter to the back, will
 * it still be the same word when spelled backwards. An example is banana. Moving the b to 
 * the back results in the same word when spelled backwards.
 */

import java.util.Scanner;
import java.lang.String;
import java.util.Random;

public class H7
{

//*Explain**************************************************************************************
//Outputs program instructions
    public static void explain()
    {
        System.out.println("This program will take in a number of words as specified and will check whether");
        System.out.println("the word will be the same when the first letter is moved to the back and spelled");
        System.out.println("backwards. One such word is Banana.\n");
    }
    
//*Random**************************************************************************************
//Outputs random number
    public static void genRandom()
    {
        int num = 0;
        Random random = new Random(System.currentTimeMillis());
        num = random.nextInt(100) + 100;
       
        System.out.println(num);
    }
    
//*countNumWords**************************************************************************************
//count the number of words
    public static int countNumWords(String inputWords)
    {
        //variables
        int numWords = 0;
        String words = inputWords;
        
        //Check how many words there are, empty string will not enter loop
        while(words.length() > 0)
        {
            //Check if there is only one word
            if(words.indexOf(' ') == -1)
            {
                //increase number of words and break out of loop
                numWords = numWords + 1;
                break;
            }
            //otherwise get the index of the space
            else
            {
                //increase number of words
                numWords = numWords + 1;
                
                //get the substring of the next word
                words = words.substring(words.indexOf(' ') + 1);
            }
        }
        
        //Output number of words
        System.out.println("\nThe number of words is " + numWords + "\n");
        
        return numWords;
    }
    
//*getWords**************************************************************************************
//Get the words from input
    public static String getWords()
    {
        //variables
        String words = "";
        Scanner scnr = new Scanner(System.in);
        
        //Prompt user for input
        System.out.println("Please enter the words");
       words = scnr.nextLine();
        
        return words;
    }
    
//*checkWords**************************************************************************************
//Check words to see if they are the same when reversed
    public static void checkWords(int numWords, String words)
    {
        //variables
        String word = "";
        String reversed = "";
        String moved = ""; //the string with first letter moved to back
        char c = ' ';
        int i = 0;
        int wordCounter = 1; //keeps track of current word
        
        //check only if there's words to check
        while(numWords > 0)
        {
            //if there's only 1 word left
            if(numWords == 1)
            {
                //get the first letter of word
                c = words.charAt(0);
                
                //move that letter to the end of the word
                moved = words.substring(1) + c;
                
                //set counter to last letter of moved
                i = moved.length() - 1;
                
                //reverse the moved word
                for(i = i; i >= 0; i--)
                {
                    reversed = reversed + moved.charAt(i);
                }
                
                //Output the results
                System.out.println("Word " + wordCounter + ". Original: " + words + ", First letter moved: " + moved + ", Reversed: " + reversed);
                
                //check if the words are the same
                if(words.toUpperCase().equals(reversed.toUpperCase()))
                {
                    //output that they match
                    System.out.println(words + " is equal to " + reversed);
                }
                else
                {
                    //output that they don't match
                    System.out.println(words + " is not equal to " + reversed);
                }
            }
            //for more than 1 word
            else
            {
                //get the first letter of word
                c = words.charAt(0);
                
                //get the current word
                word = words.substring(0,words.indexOf(' '));
                
                //move that letter to the end of the word
                moved = word.substring(1) + c;
                
                //set counter to last letter of moved
                i = moved.length() - 1;
                
                //reverse the moved word
                for(i = i; i >= 0; i--)
                {
                    reversed = reversed + moved.charAt(i);
                }
                
                //Output the results
                System.out.println("Word " + wordCounter + ". Original: " + word + ", First letter moved: " + moved + ", Reversed: " + reversed);
                
                //check if the words are the same
                if(word.toUpperCase().equals(reversed.toUpperCase()))
                {
                    //output that they match
                    System.out.println(word + " is equal to " + reversed);
                }
                else
                {
                    //output that they don't match
                    System.out.println(word + " is not equal to " + reversed);
                }
                
                //increment to the next word
                words = words.substring(words.indexOf(' ') + 1);
                
                //reset various variables
                word = "";
                reversed = "";
                moved = "";
            }
            
            //subtract number of words
            numWords--;
            //increment word counter
            wordCounter++;
            //new line
            System.out.println("");
        }
    }
    
//*Main*****************************************************************************************
    public static void main(String[] args)
    {
        
        //Variables
        String words = "";
        int numWords = 0;
        
        //output program instructions
        explain();
        
        //genRandom();
        
        //Get the words from input
        words = getWords();
        
        //Count the number of words
        numWords = countNumWords(words);
        
        //check to see which words are the same reversed
        checkWords(numWords, words);
        
    }
    
}
