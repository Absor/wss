/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.controller;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.Week;
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
public class PlannedShiftControllerTest {
    
    public PlannedShiftControllerTest() {
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
     * Test of create method, of class PlannedShiftController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        Date date = null;
        String username = "";
        Long shiftId = null;
        PlannedShiftController instance = new PlannedShiftController();
        PlannedShift expResult = null;
        PlannedShift result = instance.create(date, username, shiftId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class PlannedShiftController.
     */
    @Test
    public void testList() {
        System.out.println("list");
        PlannedShiftController instance = new PlannedShiftController();
        List expResult = null;
        List result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listByWeek method, of class PlannedShiftController.
     */
    @Test
    public void testListByWeek() {
        System.out.println("listByWeek");
        int year = 0;
        int weekNumber = 0;
        PlannedShiftController instance = new PlannedShiftController();
        List expResult = null;
        List result = instance.listByWeek(year, weekNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PlannedShiftController.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long plannedShiftId = null;
        PlannedShiftController instance = new PlannedShiftController();
        instance.delete(plannedShiftId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of weekInfo method, of class PlannedShiftController.
     */
    @Test
    public void testWeekInfo() {
        System.out.println("weekInfo");
        PlannedShiftController instance = new PlannedShiftController();
        Week expResult = null;
        Week result = instance.weekInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of weekInfoByNumber method, of class PlannedShiftController.
     */
    @Test
    public void testWeekInfoByNumber() {
        System.out.println("weekInfoByNumber");
        int year = 0;
        int weekNumber = 0;
        PlannedShiftController instance = new PlannedShiftController();
        Week expResult = null;
        Week result = instance.weekInfoByNumber(year, weekNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
