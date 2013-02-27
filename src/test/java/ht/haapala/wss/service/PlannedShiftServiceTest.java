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
public class PlannedShiftServiceTest {
    
    public PlannedShiftServiceTest() {
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
     * Test of findByDateAndShift method, of class PlannedShiftService.
     */
    @Test
    public void testFindByDateAndShift() {
        System.out.println("findByDateAndShift");
        Date date = null;
        WorkShift shift = null;
        PlannedShiftService instance = new PlannedShiftServiceImpl();
        PlannedShift expResult = null;
        PlannedShift result = instance.findByDateAndShift(date, shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByDate method, of class PlannedShiftService.
     */
    @Test
    public void testFindByDate() {
        System.out.println("findByDate");
        Date date = null;
        PlannedShiftService instance = new PlannedShiftServiceImpl();
        List expResult = null;
        List result = instance.findByDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByWeek method, of class PlannedShiftService.
     */
    @Test
    public void testFindByWeek() {
        System.out.println("findByWeek");
        int year = 0;
        int weekNumber = 0;
        PlannedShiftService instance = new PlannedShiftServiceImpl();
        List expResult = null;
        List result = instance.findByWeek(year, weekNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class PlannedShiftService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        PlannedShiftService instance = new PlannedShiftServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class PlannedShiftService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        PlannedShift shift = null;
        PlannedShiftService instance = new PlannedShiftServiceImpl();
        PlannedShift expResult = null;
        PlannedShift result = instance.save(shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class PlannedShiftService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long plannedShiftId = null;
        PlannedShiftService instance = new PlannedShiftServiceImpl();
        instance.delete(plannedShiftId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PlannedShiftServiceImpl implements PlannedShiftService {

        public PlannedShift findByDateAndShift(Date date, WorkShift shift) {
            return null;
        }

        public List<PlannedShift> findByDate(Date date) {
            return null;
        }

        public List<PlannedShift> findByWeek(int year, int weekNumber) {
            return null;
        }

        public List<PlannedShift> findAll() {
            return null;
        }

        public PlannedShift save(PlannedShift shift) {
            return null;
        }

        public void delete(Long plannedShiftId) {
        }
    }
}
