package unittest;
/*
 * Programmer: Damian Zylski
 * Project:    UnitTest
 * Date:       03/11/2021
 * System:     Windows 7 Enterprise - Netbeans 12
 * 
 * Purpose:    To perform unit testing on a line class
 */
public class UnitTest
{
    public static void main(String[] args)
    {
        //Create new line object
        Line l = new Line(5.0, 10.0, 7.0, 5.0);
        l = new Line(8.0, 16.0, 32.0, 64.0);
        Line l2 = new Line(8.0, 2.0, 50.0, 700.0);
        System.out.println("Slope: " + l.getSlope());
        System.out.println("Distance: " + l.getDistance());
        System.out.println("Parallel?: " + l.parallelTo(l2));
    }
    
}
