package h3;
//Node Class
//Damian Zylski
//11/15/2020

public class Node
{
    //attributes
    Object item;
    Node next;
    
    //constructor
    Node(Object o)
    {
        item = o;
        next = null;
    }
    
    Node(Object o, Node nextNode)
    {
        item = o;
        next = nextNode;
    }   
    
}
