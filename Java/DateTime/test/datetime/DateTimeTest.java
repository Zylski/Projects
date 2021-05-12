/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datetime;

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
public class DateTimeTest
{
    
    public DateTimeTest()
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

    /**
     * Test of dateTime method, of class DateTime.
     */
    @Test
    public void testDateTime()
    {
        //If no exceptions, should pass tests
        System.out.println("dateTime");
        boolean expResult = true;
        boolean result = DateTime.dateTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class DateTime.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        DateTime.main(args);
    }
    
}
