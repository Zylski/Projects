package peoplework;

/**
 * Programmer: Damian Zylski
 * Project:    PeopleWork
 * Class:      Employee
 * Date:       08/10/2020
 * 
 */

public class Employee extends Person implements Comparable <Employee>
{
    //Attributes
    private int employeeID;
    private String department;
    private String hireDate;
    
    //Constructors
    public Employee()
    {
        super();
        employeeID = 000000;
        department = "blank";
        hireDate = "none";
    }
    
    public Employee(int id,  String d, String h, String n)
    {
        super(n);
        employeeID = id;
        department = d;
        hireDate = h;
    }
    
    //Setters
    public void setID(int id)
    {
        employeeID = id;
    }
    public void setDept(String d)
    {
        department = d;
    }
    public void setHireDate(String h)
    {
        hireDate = h;
    }
    //Getters
    public int getID()
    {
        return employeeID;
    }
    public String getDept()
    {
        return department;
    }
    public String getHireDate()
    {
        return hireDate;
    }
    //Output
    @Override
    public void writeOutput()
    {
        super.writeOutput();
        System.out.println("Employee ID: " + employeeID);
        System.out.println("Department: " + department);
        System.out.println("Hire Date: " + hireDate);
        System.out.println("");
    }
    //check if Equal
    public boolean isSame(Employee e)
    {
        //Student ID numbers are unique
        if(super.hasSameName(e) && this.employeeID == e.employeeID)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //compareTo
    public int compareTo(Employee e)
    {
        if(this.employeeID > e.employeeID)
        {
            return 1; //greater than
        }
        else if(this.employeeID < e.employeeID)
        {
            return -1; //less than
        }
        else if(this.employeeID == e.employeeID)
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
        return "" + employeeID;
    }
    
}
