/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functional;

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
public class FunctionalIntTest
{
    
    public FunctionalIntTest()
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
     * Test of function method, of class FunctionalInt.
     */
    @Test
    public void testFunction()
    {
        System.out.println("function");
        FunctionalInt instance = new FunctionalIntImpl();
        instance.function();
    }

    /**
     * Test of removeX method, of class FunctionalInt.
     */
    @Test
    public void testRemoveX()
    {
        System.out.println("removeX");
        String s = "ax";
        String expResult = "a";
        String result = FunctionalInt.removeX(s);
        assertEquals(expResult, result);
    }

    public class FunctionalIntImpl implements FunctionalInt
    {

        public void function()
        {
        }
    }
    
}
