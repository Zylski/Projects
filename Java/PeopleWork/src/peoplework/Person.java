package peoplework;

/**
 * Programmer: Damian Zylski
 * Project:    PeopleWork
 * Class:      Person
 * Date:       08/10/2020
 * 
 */

public class Person
{
    //Attributes
    private String name;
    
    //Constructors
    public Person()
    {
        name = "Blank";
    }
    
    public Person(String s)
    {
        name = s;
    }
    
    //Setters
    public void setName(String s)
    {
        name = s;
    }
    //Getters
    public String getName()
    {
        return name;
    }
    //Output name
    public void writeOutput()
    {
        System.out.println("Name: " + name);
    }
    //check if Equal
    public boolean hasSameName(Person p)
    {
        if(this.name.equalsIgnoreCase(p.name))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //compareTo
    public int compareTo(Person p)
    {
        if(this.name.compareToIgnoreCase(p.name) > 0)
        {
            return 1; //greater than
        }
        else if(this.name.compareToIgnoreCase(p.name) < 0)
        {
            return -1; //less than
        }
        else if(this.name.compareToIgnoreCase(p.name) == 0)
        {
            return 0; //equal
        }
        else
        {
            return 0; //default
        }
    }
    //toString
    public String toString()
    {
        return name;
    }
    
}
