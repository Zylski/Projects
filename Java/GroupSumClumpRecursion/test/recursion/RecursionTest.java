/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recursion;

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
public class RecursionTest
{
    
    public RecursionTest()
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
     * Test of groupSumClump method, of class Recursion.
     */
    @Test
    public void testGroupSumClump()
    {
        System.out.println("groupSumClump");
        int index = 0;
        int[] arr = {2,4,8};
        int target = 10;
        boolean expResult = true;
        boolean result = Recursion.groupSumClump(index, arr, target);
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class Recursion.
     */
    @Test
    public void testMain()
    {
        System.out.println("main");
        String[] args = null;
        Recursion.main(args);
    }
    
}
