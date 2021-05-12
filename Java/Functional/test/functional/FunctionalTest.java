/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
public class FunctionalTest
{
    
    public FunctionalTest()
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
     * Test of rightDigit method, of class Functional.
     */
    @Test
    public void testRightDigit()
    {
        System.out.println("rightDigit");
        List nums = new ArrayList<Integer>(Arrays.asList(1,22,93));
        List expResult = new ArrayList<Integer>(Arrays.asList(1,2,3));
        List result = Functional.rightDigit(nums);
        assertEquals(expResult, result);
    }

    /**
     * Test of doubling method, of class Functional.
     */
    @Test
    public void testDoubling()
    {
        System.out.println("doubling");
        List nums = new ArrayList<Integer>(Arrays.asList(1,2,-3));
        List expResult = new ArrayList<Integer>(Arrays.asList(2,4,-6));
        List result = Functional.doubling(nums);
        assertEquals(expResult, result);
    }

    /**
     * Test of noX method, of class Functional.
     */
    @Test
    public void testNoX()
    {
        System.out.println("noX");
        List strings = new ArrayList<String>(Arrays.asList("ax","bb","cx"));
        List expResult = new ArrayList<String>(Arrays.asList("a","bb","c"));
        List result = Functional.noX(strings);
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class Functional.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        Functional.main(args);
    }
    
}
