/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daythree;

import java.io.File;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Zylski
 */
public class DayThreeTest
{
    
    public DayThreeTest()
    {
    }
    
    @BeforeAll
    public static void setUpClass()
    {
    }
    
    @AfterAll
    public static void tearDownClass()
    {
    }
    
    @BeforeEach
    public void setUp()
    {
    }
    
    @AfterEach
    public void tearDown()
    {
    }

    /**
     * Test of directoryChecker method, of class DayThree.
     */
    @Test
    public void testDirectoryChecker() throws Exception
    {
        System.out.println("directoryChecker");
        StringBuilder path = null;
        File expResult = null;
        File result = DayThree.directoryChecker(path);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of textAppender method, of class DayThree.
     */
    @Test
    public void testTextAppender() throws Exception
    {
        System.out.println("textAppender");
        DayThree.textAppender();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of charSearch method, of class DayThree.
     */
    @Test
    public void testCharSearch() throws Exception
    {
        System.out.println("charSearch");
        String[] args = null;
        int expResult = 0;
        int result = DayThree.charSearch(args);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class DayThree.
     */
    @Test
    public void testMain() throws Exception
    {
        System.out.println("main");
        String[] args = null;
        DayThree.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
