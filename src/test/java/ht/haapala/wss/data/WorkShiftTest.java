/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.data;

import java.util.Date;
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
public class WorkShiftTest {
    
    public WorkShiftTest() {
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
     * Test of getId method, of class WorkShift.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        WorkShift instance = new WorkShift();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class WorkShift.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        WorkShift instance = new WorkShift();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShiftName method, of class WorkShift.
     */
    @Test
    public void testGetShiftName() {
        System.out.println("getShiftName");
        WorkShift instance = new WorkShift();
        String expResult = "";
        String result = instance.getShiftName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShiftName method, of class WorkShift.
     */
    @Test
    public void testSetShiftName() {
        System.out.println("setShiftName");
        String shiftName = "";
        WorkShift instance = new WorkShift();
        instance.setShiftName(shiftName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartTime method, of class WorkShift.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        WorkShift instance = new WorkShift();
        Date expResult = null;
        Date result = instance.getStartTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartTime method, of class WorkShift.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        Date startTime = null;
        WorkShift instance = new WorkShift();
        instance.setStartTime(startTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndTime method, of class WorkShift.
     */
    @Test
    public void testGetEndTime() {
        System.out.println("getEndTime");
        WorkShift instance = new WorkShift();
        Date expResult = null;
        Date result = instance.getEndTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEndTime method, of class WorkShift.
     */
    @Test
    public void testSetEndTime() {
        System.out.println("setEndTime");
        Date endTime = null;
        WorkShift instance = new WorkShift();
        instance.setEndTime(endTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
