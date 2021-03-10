package peoplework;
/*
 * Programmer: Damian Zylski
 * Project:    PeopleWork
 * Date:       08/10/2020
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To read student and employee objects from a file and practice class stuff          
 */

import java.util.Collections;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PeopleWork
{
   
//*getPeople*********************************************************************************
//gets student and employees from file into array list
    public static void getPeople(ArrayList <Student> studentList, ArrayList <Employee> employeeList) throws IOException
    {
        //Variables
        int i = 0;
        String fileName = "Student.dat";
        Scanner file = null;
        FileInputStream fin = null;
        Student student = null;
        Employee employee = null;
        
        
        //loop and read in the people from the files
       for(i = 0; i  < 2; i++)
       {
           //try to open the file
           try
           {
               fin = new FileInputStream(fileName);
               file = new Scanner(fin);
           }
           catch(FileNotFoundException e)
           {
               //display error and exit if file not found
               System.out.println("File is missing or corrupt!");
               Logger.getLogger(PeopleWork.class.getName()).log(Level.SEVERE, null, e);
               System.exit(-1);
           }
           //read objects from file
           while(file.hasNextInt())
           {
               //read in students
               if(i == 0)
               {
                   student = new Student(file.nextInt(), file.next(), file.next(), file.nextLine());
                   studentList.add(student);
               }
               //read in employees
               else
               {
                   employee = new Employee(file.nextInt(), file.next(), file.next(), file.nextLine());
                   employeeList.add(employee);
               }
           }
           
           //change file name
           if(i == 0)
           {
               fileName = "Employee.dat";
           }
		   
		   //close file
		   file.close();
		   fin.close();
       }
               
    }
    
//*sortLists*********************************************************************************
//Sorts the lists of people
    public static void sortLists(ArrayList <Student> studentList, ArrayList <Employee> employeeList)
    {
        Collections.sort(studentList);
        Collections.sort(employeeList);
    }
    
//*printLists*********************************************************************************
//Outputs the lists of people
    public static void printLists(ArrayList <Student> studentList, ArrayList <Employee> employeeList)
    {
        //Variables
        int i = 0;
        int numStudents = studentList.size();
        int numEmployees = employeeList.size();
        
        //loop and print the lists
        //students
        System.out.println("Students: ");
        for(i = 0; i < numStudents; i++)
        {
            System.out.print((i + 1 + ". "));
            studentList.get(i).writeOutput();
        }
        //employees
        System.out.println("\nEmployees: ");
        for(i = 0; i < numEmployees; i++)
        {
            System.out.print((i + 1 + ". "));
            employeeList.get(i).writeOutput();
        }
    }
//*Main**************************************************************************************    
    public static void main(String[] args) throws IOException
    {
        //The lists to hold students and employees
        ArrayList studentList = new ArrayList <Student>();
        ArrayList employeeList = new ArrayList <Employee>();
        
        //Read in the student and employees from file
        getPeople(studentList, employeeList);
        
        //sort the lists
        sortLists(studentList, employeeList);
        
        //output the lists
        printLists(studentList, employeeList);
        
        
    }
    
}
