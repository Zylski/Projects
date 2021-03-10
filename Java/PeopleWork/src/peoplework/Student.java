package peoplework;

/**
 * Programmer: Damian Zylski
 * Project:    PeopleWork
 * Class:      Student
 * Date:       08/10/2020
 * 
 */

public class Student extends Person implements Comparable <Student>
{
    //Attributes
    private int studentID;
    private String level;
    private String major;
    
    //Constructors
    public Student()
    {
        super();
        studentID = 000000;
        level = "blank";
        major = "none";
    }
    
    public Student(int id, String m, String l, String n)
    {
        super(n);
        studentID = id;
        level = l;
        major = m;
    }
    
    //Setters
    public void setID(int id)
    {
        studentID = id;
    }
    public void setLevel(String l)
    {
        level = l;
    }
    public void setMajor(String m)
    {
        major = m;
    }
    //Getters
    public int getID()
    {
        return studentID;
    }
    public String getLevel()
    {
        return level;
    }
    public String getMajor()
    {
        return major;
    }
    //Output
    @Override
    public void writeOutput()
    {
        super.writeOutput();
        System.out.println("Student ID: " + studentID);
        System.out.println("Level: " + level);
        System.out.println("Major: " + major);
        System.out.println("");
    }
    //check if Equal
    public boolean isSame(Student s)
    {
        //Student ID numbers are unique
        if(super.hasSameName(s) && this.studentID == s.studentID)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    //compareTo
    public int compareTo(Student s)
    {
        if(this.studentID > s.studentID)
        {
            return 1; //greater than
        }
        else if(this.studentID < s.studentID)
        {
            return -1; //less than
        }
        else if(this.studentID == s.studentID)
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
        return "" + studentID;
    }
    
}
