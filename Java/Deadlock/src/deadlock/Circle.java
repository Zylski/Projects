package deadlock;
/**
 *
 * @author Damian Zylski
 * Circle class
 * 3/10/2021
 * The class for circles
 */

import java.lang.Math;

public class Circle
{
    //Attributes
    private Double radius;
    
    //constructors
    public Circle()
    {
        radius = 0.0;
    }
    public Circle(Double r)
    {
        radius = r;
    }
    
    //getters
    public Double getRadius()
    {
        return radius;
    }
    
    //setters
    public void setRadius(Double r)
    {
        radius = r;
    }
    
    //Calculate area
    public Double calculateArea()
    {
        //Calc area
        Double area = Math.PI * Math.pow(radius,2.0);
        
        return area;
    }
    
    //Display area
    public void display()
    {
        System.out.println("Area is " + calculateArea());
    }
       
}
