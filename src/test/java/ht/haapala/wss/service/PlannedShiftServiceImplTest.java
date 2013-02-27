/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.service;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WorkShift;
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
public class PlannedShiftServiceImplTest {
    
    public PlannedShiftServiceImplTest() {
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
     * Test of findByDate method, of class PlannedShiftServiceImpl.
     */
    @Test
    public void testFindByDate() {
        System.out.println("findByDate");
        Date date = null;
        PlannedShiftServiceImpl instance = new PlannedShiftServiceImpl();
        List expResult = null;
        List result = instance.findByDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByWeek method, of class PlannedShiftServiceImpl.
     */
    @Test
    public void testFindByWeek() {
        System.out.println("findByWeek");
        int year = 0;
        int weekNumber = 0;
        PlannedShiftServiceImpl instance = new PlannedShiftServiceImpl();
        List expResult = null;
        List result = instance.findByWeek(year, weekNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class PlannedShiftServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        PlannedShiftServiceImpl instance = new PlannedShiftServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class PlannedShiftServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        PlannedShift shift = null;
        PlannedShiftServiceImpl instance = new PlannedShiftServiceImpl();
        PlannedShift expResult = null;
        PlannedShift result = instance.save(shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PlannedShiftServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long plannedShiftId = null;
        PlannedShiftServiceImpl instance = new PlannedShiftServiceImpl();
        instance.delete(plannedShiftId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDateAndShift method, of class PlannedShiftServiceImpl.
     */
    @Test
    public void testFindByDateAndShift() {
        System.out.println("findByDateAndShift");
        Date date = null;
        WorkShift shift = null;
        PlannedShiftServiceImpl instance = new PlannedShiftServiceImpl();
        PlannedShift expResult = null;
        PlannedShift result = instance.findByDateAndShift(date, shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
