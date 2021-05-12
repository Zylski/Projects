/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lambdas;

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
public class LambdasTest
{
    
    public LambdasTest()
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
     * Test of isOdd method, of class Lambdas.
     */
    @Test
    public void testIsOdd()
    {
        System.out.println("isOdd");
        Integer i = 666;
        String expResult = "EVEN";
        String result = Lambdas.isOdd(i);
        assertEquals(expResult, result);
    }


    /**
     * Test of isPrime method, of class Lambdas.
     */
    @Test
    public void testIsPrime()
    {
        System.out.println("isPrime");
        Integer num = 2;
        String expResult = "PRIME";
        String result = Lambdas.isPrime(num);
        assertEquals(expResult, result);
    }

    /**
     * Test of isPalindrome method, of class Lambdas.
     */
    @Test
    public void testIsPalindrome()
    {
        System.out.println("isPalindrome");
        Integer num = 4554;
        String expResult = "PALINDROME";
        String result = Lambdas.isPalindrome(num);
        assertEquals(expResult, result);
    }

    /**
     * Test of performOperation method, of class Lambdas.
     */
    @Test
    public void testPerformOperation() throws Exception
    {
        System.out.println("performOperation");
        boolean expResult = true;
        boolean result = Lambdas.performOperation();
        assertEquals(expResult, result);
    }

    /**
     * Test of main method, of class Lambdas.
     */
    @Test
    public void testMain() throws Exception
    {
        System.out.println("main");
        String[] args = null;
        Lambdas.main(args);
    }
    
}
