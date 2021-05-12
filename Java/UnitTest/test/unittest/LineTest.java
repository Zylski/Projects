/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unittest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Zylski
 */
public class LineTest
{
    
    public LineTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }
    
    //TO DO: Need to write 2 more test cases for each method

    /**
     * Test of getSlope method, of class Line.
     */
    @Test
    public void testGetSlope()
    {
        //first test
        System.out.println("getSlope Test with input 5, 10, 7, 5");
        Line instance = new Line(5.0,10.0,7.0,5.0);
        double expResult = -2.5;
        double result = instance.getSlope();
        assertEquals(expResult, result, 0.0001); //delta at the end here
        //Second test
        System.out.println("getSlope Test with input 10, 3, 5, 8");
        instance = new Line(10.0,3.0,5.0,8.0);
        expResult = -2.5;
        result = instance.getSlope();
        //Should not equal -2.5
        assertNotEquals(expResult, result, 0.0001);
        //Third test
        System.out.println("getSlope Test with input 8.0, 16.0, 32.0, 64.0");
        instance = new Line(8.0, 16.0, 32.0, 64.0);
        expResult = 2.0;
        result = instance.getSlope();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of getDistance method, of class Line.
     */
    @Test
    public void testGetDistance()
    {
        //first test
        System.out.println("getDistance Test with input 5, 10, 7, 5");
        Line instance = new Line(5.0,10.0,7.0,5.0);
        double expResult = 5.385164807134504;
        double result = instance.getDistance();
        assertEquals(expResult, result, 0.0001);
        //second test
        System.out.println("getDistance Test with input 10, 3, 5, 8");
        instance = new Line(10.0,3.0,5.0,8.0);
        expResult = 5.385164807134504;
        result = instance.getDistance();
        assertNotEquals(expResult, result, 0.0001);
        //third test
        System.out.println("getDistance Test with input 8, 16, 32, 64");
        instance = new Line(8.0,16.0,32.0,64.0);
        expResult = 53.665631459994955;
        result = instance.getDistance();
        assertEquals(expResult, result, 0.0001);
    }

    /**
     * Test of parallelTo method, of class Line.
     */
    @Test
    public void testParallelTo()
    {
        //first test
        System.out.println("parallelTo Test with input 5, 10, 7, 5");
        System.out.println("Expecting true for 6,10,8,5 comparison");
        Line l = new Line(6.0, 10.0, 8.0, 5.0);
        Line instance = new Line(5.0,10.0,7.0,5.0);
        boolean expResult = true;
        boolean result = instance.parallelTo(l);
        assertEquals(expResult, result);
        //Second Test. //This test fails
        System.out.println("parallelTo Test with input -1, 10, 10, 10");
        System.out.println("Expecting true for 6,10,8,5 comparison");
        l = new Line(6.0, 10.0, 8.0, 5.0);
        instance = new Line(-1.0,10.0,10.0,10.0);
        expResult = false;
        result = instance.parallelTo(l);
        assertNotEquals(expResult, result);
        //third test
        System.out.println("parallelTo Test with input 10, 3, 5, 8");
        System.out.println("Expecting true for 8,2,50,700 comparison");
        l = new Line(8.0, 2.0, 50.0, 700.0);
        instance = new Line(10.0,3.0,5.0,8.0);
        expResult = false;
        result = instance.parallelTo(l);
        assertEquals(expResult, result);
    }
    
}
