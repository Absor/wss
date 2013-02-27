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
public class WorkShiftServiceImplTest {
    
    public WorkShiftServiceImplTest() {
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
     * Test of findOne method, of class WorkShiftServiceImpl.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        Long id = null;
        WorkShiftServiceImpl instance = new WorkShiftServiceImpl();
        WorkShift expResult = null;
        WorkShift result = instance.findOne(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class WorkShiftServiceImpl.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        WorkShiftServiceImpl instance = new WorkShiftServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class WorkShiftServiceImpl.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        WorkShift shift = null;
        WorkShiftServiceImpl instance = new WorkShiftServiceImpl();
        WorkShift expResult = null;
        WorkShift result = instance.save(shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class WorkShiftServiceImpl.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        WorkShiftServiceImpl instance = new WorkShiftServiceImpl();
        instance.delete(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
