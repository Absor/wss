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
public class PlannedShiftTest {
    
    public PlannedShiftTest() {
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
     * Test of getShiftDate method, of class PlannedShift.
     */
    @Test
    public void testGetShiftDate() {
        System.out.println("getShiftDate");
        PlannedShift instance = new PlannedShift();
        Date expResult = null;
        Date result = instance.getShiftDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShiftDate method, of class PlannedShift.
     */
    @Test
    public void testSetShiftDate() {
        System.out.println("setShiftDate");
        Date shiftDate = null;
        PlannedShift instance = new PlannedShift();
        instance.setShiftDate(shiftDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmployee method, of class PlannedShift.
     */
    @Test
    public void testGetEmployee() {
        System.out.println("getEmployee");
        PlannedShift instance = new PlannedShift();
        WSSUser expResult = null;
        WSSUser result = instance.getEmployee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmployee method, of class PlannedShift.
     */
    @Test
    public void testSetEmployee() {
        System.out.println("setEmployee");
        WSSUser employee = null;
        PlannedShift instance = new PlannedShift();
        instance.setEmployee(employee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getShift method, of class PlannedShift.
     */
    @Test
    public void testGetShift() {
        System.out.println("getShift");
        PlannedShift instance = new PlannedShift();
        WorkShift expResult = null;
        WorkShift result = instance.getShift();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShift method, of class PlannedShift.
     */
    @Test
    public void testSetShift() {
        System.out.println("setShift");
        WorkShift shift = null;
        PlannedShift instance = new PlannedShift();
        instance.setShift(shift);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class PlannedShift.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        PlannedShift instance = new PlannedShift();
        Long expResult = null;
        Long result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class PlannedShift.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        Long id = null;
        PlannedShift instance = new PlannedShift();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBareEmployee method, of class PlannedShift.
     */
    @Test
    public void testGetBareEmployee() {
        System.out.println("getBareEmployee");
        PlannedShift instance = new PlannedShift();
        BareUser expResult = null;
        BareUser result = instance.getBareEmployee();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBareEmployee method, of class PlannedShift.
     */
    @Test
    public void testSetBareEmployee() {
        System.out.println("setBareEmployee");
        BareUser bareEmployee = null;
        PlannedShift instance = new PlannedShift();
        instance.setBareEmployee(bareEmployee);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
