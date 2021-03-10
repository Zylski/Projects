package h3;
/*
 * Programmer: Damian Zylski
 * Date:       11/15/2020
 * System:     Windows 7 - Netbeans 12
 * Project:    H3
 * 
 * Purpose:    To build a linked list of students from a file in order by age
 * and then print them out.
*/

import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;

public class H3
{
    
//*getStudents************************************************************************
//Get the students from file and inserts them into the correct spot in the linked list
public static Node getStudents() throws IOException
{
    //variables
    Node head = null;
    Node tail = null;
    Node prev = null;
    Node current = null;
    Node newNode = null;
    Node temp = null;
    Scanner scnr = null;
    FileInputStream file = null;
    String name = "";
    int age = 0;
    int n = 0;
    Student s = null;
    
    //try to open the file
    try
    {
        file = new FileInputStream("StudentInputFile.txt");
        scnr = new Scanner(file);
    }
    catch(FileNotFoundException e)
    {
        System.out.println("File is missing or corrupt!");
        Logger.getLogger(H3.class.getName()).log(Level.SEVERE,null,e);
        System.exit(-1);
    }
    
    //loop through the file and get the students, add to linked list
    while(scnr.hasNext())
    {
        //get name
        name = scnr.next() + " " + scnr.next();
        //get age
        age = scnr.nextInt();
        
        //create student object
        s = new Student(name, age);
        
        //create node and add to linked list
        newNode = new Node(s);
        
        //place node into correct area in linked list
        //check if head exists, if not, make new node of the head
        if(head == null)
        {
            head = newNode;
            tail = newNode;
            head.next = null;
            n++;
        }
        //Otherwise add new node to the linked list
        else
        {
            tail.next = newNode;
            tail = newNode;
            tail.next = null;
            n++;          
            
        }
        
    }
    
    //close the file
    file.close();
    scnr.close();
    
    //return the head
    return head;
}

//*printList*******************************************************************************
//Prints the contents of the linked list
public static void printList(Node head)
{
    //variables
    Node current = head;
    
    //loop and print linked list contents
    while(current != null)
    {
        System.out.println(current.item);
        
        //increment nodes
        current = current.next;
    }
}

public static Node sortList(Node head)
{
    //variables
    Node current = null;
    Node prev = null; //keeps track of previous node
    Node temp = null;
    
    
    //try to bubble sort the current list
    current = head;

    //loop and sort            
    for(int i = 0; i < 9; i++) //There are 9 nodes so loop 9 times, perhaps we could have made a static var in Node to keep track of count
    {
        //loop until a null node is hit.
        while(current.next != null)
        {
            //compare sizes
            if(Integer.parseInt(String.valueOf(current.item)) > Integer.parseInt(String.valueOf(current.next.item)))
            {
                //swap items
                if(current == head) //if current is head
                {
                    temp = current.next; //temp becomes node after current
                    current.next = temp.next; //current.next becomes the node after next
                    head = temp; //head is set to temp since it is smaller
                    temp.next = current; //The next node after head or temp becomes the current node
                    current = head; //we have to change current to head so we don't skip nodes
                    prev = head; //make prev head to keep track of previous node
                }
                else
                {   
                    temp = current.next; //temp becomes node after current
                    current.next = temp.next; //current.next becomes the node after next
                    temp.next = current; //The next node after temp becomes the current node, so now smaller is before bigger
                    prev.next = temp; //we set the next node after previous to be temp
                    prev = temp; //we keep track of temp which is the new previous
                    current = temp; //make sure temp becomes the new current so we don't skip nodes
                }


            }
            //we have to keep track of prev even if we don't swap or we lose nodes
            prev = current; 
            //increment nodes
            current = current.next;
        }
        //reset current to head with each outer pass
        current = head;
    }
    return head;
}

//*main*******************************************************************************
    public static void main(String[] args) throws IOException
    {
        // the head of the student linked list
        Node head = null;
        
        //Get the head containing the students from file
        head = getStudents();
        
        //sort the student
        head = sortList(head);
        
        //print out the students
        printList(head);
        
        
    }   
}
