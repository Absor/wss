/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.repository;

import ht.haapala.wss.data.PlannedShift;
import ht.haapala.wss.data.WSSUser;
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
public class PlannedShiftRepositoryTest {
    
    public PlannedShiftRepositoryTest() {
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
     * Test of findByShiftDate method, of class PlannedShiftRepository.
     */
    @Test
    public void testFindByShiftDate() {
        System.out.println("findByShiftDate");
        Date date = null;
        PlannedShiftRepository instance = new PlannedShiftRepositoryImpl();
        List expResult = null;
        List result = instance.findByShiftDate(date);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByShiftDateBetween method, of class PlannedShiftRepository.
     */
    @Test
    public void testFindByShiftDateBetween() {
        System.out.println("findByShiftDateBetween");
        Date start = null;
        Date end = null;
        PlannedShiftRepository instance = new PlannedShiftRepositoryImpl();
        List expResult = null;
        List result = instance.findByShiftDateBetween(start, end);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByShiftDateAndShift method, of class PlannedShiftRepository.
     */
    @Test
    public void testFindByShiftDateAndShift() {
        System.out.println("findByShiftDateAndShift");
        Date date = null;
        WorkShift shift = null;
        PlannedShiftRepository instance = new PlannedShiftRepositoryImpl();
        PlannedShift expResult = null;
        PlannedShift result = instance.findByShiftDateAndShift(date, shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByEmployee method, of class PlannedShiftRepository.
     */
    @Test
    public void testFindByEmployee() {
        System.out.println("findByEmployee");
        WSSUser employee = null;
        PlannedShiftRepository instance = new PlannedShiftRepositoryImpl();
        List expResult = null;
        List result = instance.findByEmployee(employee);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByShift method, of class PlannedShiftRepository.
     */
    @Test
    public void testFindByShift() {
        System.out.println("findByShift");
        WorkShift shift = null;
        PlannedShiftRepository instance = new PlannedShiftRepositoryImpl();
        List expResult = null;
        List result = instance.findByShift(shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class PlannedShiftRepositoryImpl implements PlannedShiftRepository {

        public List<PlannedShift> findByShiftDate(Date date) {
            return null;
        }

        public List<PlannedShift> findByShiftDateBetween(Date start, Date end) {
            return null;
        }

        public PlannedShift findByShiftDateAndShift(Date date, WorkShift shift) {
            return null;
        }

        public List<PlannedShift> findByEmployee(WSSUser employee) {
            return null;
        }

        public List<PlannedShift> findByShift(WorkShift shift) {
            return null;
        }
    }
}
