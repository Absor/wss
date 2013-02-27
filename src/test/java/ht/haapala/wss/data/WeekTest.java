/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.data;

import java.util.Date;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Heikki Haapala
 */
public class WeekTest {
    
    public WeekTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getDays method, of class Week.
     */
    @Test
    public void testGetDays() {
        System.out.println("getDays");
        Week instance = new Week();
        List expResult = null;
        List result = instance.getDays();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDays method, of class Week.
     */
    @Test
    public void testSetDays() {
        System.out.println("setDays");
        List<Date> days = null;
        Week instance = new Week();
        instance.setDays(days);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWeekNumber method, of class Week.
     */
    @Test
    public void testGetWeekNumber() {
        System.out.println("getWeekNumber");
        Week instance = new Week();
        int expResult = 0;
        int result = instance.getWeekNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWeekNumber method, of class Week.
     */
    @Test
    public void testSetWeekNumber() {
        System.out.println("setWeekNumber");
        int weekNumber = 0;
        Week instance = new Week();
        instance.setWeekNumber(weekNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getYear method, of class Week.
     */
    @Test
    public void testGetYear() {
        System.out.println("getYear");
        Week instance = new Week();
        int expResult = 0;
        int result = instance.getYear();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setYear method, of class Week.
     */
    @Test
    public void testSetYear() {
        System.out.println("setYear");
        int year = 0;
        Week instance = new Week();
        instance.setYear(year);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
