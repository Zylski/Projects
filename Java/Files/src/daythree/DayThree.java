package daythree;
/*
 * Programmer: Damian Zylski
 * Project:    DayThree
 * Date:       03/10/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To let the user provide a filepath and display all files and folders
 * in said directory. This program will also allow text to be appended to a file and will
 * have the ability to search for a particular character in a file and count it.
 */

import java.io.BufferedWriter;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.nio.file.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;

public class DayThree
{
    
//*directoryChecker*********************************************************************************
    //Lists the files and folders in a directory. Directory will be input as command line arg
    //NOTE: Was having issues with this because I was making new stringbuilder in the wrong place.
    //I had to refer to examples online to understand this since this was completely new to me. 
    //I think I get it now. listFiles returns an array of file names. Then you loop through them.
    public static File directoryChecker(StringBuilder path) throws IOException
    {
        //Variables
        File dir = null;
        File dirList [] = null;
        StringBuilder newPath = null;
        
        //Try to open directory
        try
        {     
            //open file location
            dir = new File(path.toString());
           //Get all files in directory
           dirList= dir.listFiles();
           
           //loop through all files and display contents
           for(File f : dirList)
           {
               //Check if path is a directory
               if(f.isDirectory())
               {
                   //Grab New path for new directory
                   //MY CODE KEPT HAVING ERRRORS BECAUSE I WASN'T DOING NEW BELOW
                   newPath = new StringBuilder(f.toString());
                    
                   //Output directory name
                   System.out.println("---" + f.getAbsolutePath() + "---");
                   
                   //Recur
                   //Enter directory and display inner directory contents 
                   directoryChecker(newPath);
               }
               //for files
               else if(f.isFile())
               {
                   //display file name
                   System.out.println(f.getAbsolutePath());
               }
               //base case
               //otherwise return null and end recursion
               else
               {
                   return null;
               }
           }
        }
        //Excpetion handling
        catch(IOException e)
        {
            e.printStackTrace();
            System.err.println("File is Missing or corrupt");
            throw e;
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.err.println("Error. Array went out of bounds");
            throw e;
        }
        catch(InvalidPathException e)
        {
            e.printStackTrace();
            System.err.println("Directory not found!");
            throw e;
        }
        catch(NullPointerException e)
        {
            e.printStackTrace();
            System.err.println("Error. Null Encountered");
            throw e;
        }
        return null;
    }
    
//*textAppender*********************************************************************************
    //Appends text to a file
    public static void textAppender() throws IOException
    {
        //variables
        Scanner scnr = new Scanner(System.in);
        String input = null;
        BufferedWriter writer = null;
        String fileName = "file.dat"; //File in project folder 
        
        //Try to open file
        try
        {
            writer = new BufferedWriter(new FileWriter(fileName,true));
            
            //Prompt user for input
            System.out.println("\nPlease enter the new line of text you would like to add to file: ");
            input = scnr.nextLine();
            
            //try to append text to file
            writer.write("\n" + input);
            
            System.out.println("----TEXT ADDED TO FILE----");
            
            //close writer
            writer.close();
            
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("\nFile is missing or corrupt");
        }
        catch(IOException e)
        {
            System.out.println("File is missing or corrupt!");
            e.printStackTrace();
        }
    }
    
//*charSearch*********************************************************************************
    //Searches for a specific char in a file and counts it
    //PAth is supplied first in args, then the char to check
    public static int charSearch(String args []) throws IOException
    {
        //variables
        Scanner file = null;
        FileInputStream f = null;
        String fileName = null;
        StringBuilder text = new StringBuilder();
        Character c = 'Z';
        Character target = null;
        int counter = 0;
        
        //Try to open file and check if arguments were supplied to command line
        try
        {
            fileName = args[1];
            target = args[2].charAt(0);
            
            //open file if args supplied
            f = new FileInputStream(fileName);
            file = new Scanner(f);
            
            //loop through contents of file and check how many instances of target
            //char are found
            while(file.hasNextLine())
            {
                //add text from file to string
                text.append("\n" + file.nextLine());
            }
            
            //get size of text
            int textLength = text.length();
            
            //loop and check for any instances of char
            for(int i = 0; i < textLength; i++)
            {
                c = text.charAt(i);
                
                //check if char is a match
                if(c == target)
                {
                    //increment counter
                    counter++;
                }               
            }
            
            //output results
            System.out.println("\nCharacter " + target + " was found " + counter + " times.");
            
            //close file
            file.close();
            f.close();
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println("\nError. Missing arguments\n");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("\nFile is missing or corrupt");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("\nFile is missing or corrupt");
        }
        
        return counter;
    }
    
//*main*********************************************************************************
    public static void main(String[] args) throws IOException
    {
        //check what is in a directory and it's subdirectories
        try //Check if there is any input, since this is coming from Command Line
        {
            //open the file
            StringBuilder path = new StringBuilder(args[0]);
            //print message
            System.out.println("Contents of " + path.toString());
            //Check files
            directoryChecker(path);
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            e.printStackTrace();
            System.out.println("Error. No directory provided\n");
        }
        
        //Append text to a file
        textAppender();
        
        //Search for a char in a file and count it 
        //See method for arg instructions
        charSearch(args);
        
    }
    
}
