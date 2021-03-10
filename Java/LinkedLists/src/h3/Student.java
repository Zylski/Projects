package h3;
//Student Class
//Damian Zylski
//11/15/2020

public class Student
{
    //attributes
    private String name;
    private int age;
    
    //constructor
    public Student()
    {
        name = "Blank";
        age = 0;
    }
    public Student(String n, int a)
    {
        name = n;
        age = a;
    }
    
    //getters
    public String getName()
    {
        return name;
    }
    public int getAge()
    {
        return age;
    }
    
    //setters
    public void setName(String n)
    {
        name = n;
    }
    public void setAge(int a)
    {
        age = a;
    }
    
    //write output
    public void writeOutput()
    {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("");
    }
    
    //equal
    public boolean equal(Student s)
    {
        if(this.getAge() == s.getAge())
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
        if(this.getAge() > s.getAge())
        {
            return 1;
        }
        else if(this.getAge() < s.getAge())
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
    
    
    //toString
    @Override
    public String toString()
    {
        return "" + age;
    }
}
