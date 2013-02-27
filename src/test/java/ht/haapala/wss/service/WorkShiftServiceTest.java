/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.service;

import ht.haapala.wss.data.WorkShift;
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
public class WorkShiftServiceTest {
    
    public WorkShiftServiceTest() {
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
     * Test of findOne method, of class WorkShiftService.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        Long id = null;
        WorkShiftService instance = new WorkShiftServiceImpl();
        WorkShift expResult = null;
        WorkShift result = instance.findOne(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class WorkShiftService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        WorkShiftService instance = new WorkShiftServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class WorkShiftService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        WorkShift shift = null;
        WorkShiftService instance = new WorkShiftServiceImpl();
        WorkShift expResult = null;
        WorkShift result = instance.save(shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class WorkShiftService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        WorkShiftService instance = new WorkShiftServiceImpl();
        instance.delete(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class WorkShiftServiceImpl implements WorkShiftService {

        public WorkShift findOne(Long id) {
            return null;
        }

        public List<WorkShift> findAll() {
            return null;
        }

        public WorkShift save(WorkShift shift) {
            return null;
        }

        public void delete(Long id) {
        }
    }
}
