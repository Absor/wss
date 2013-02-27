/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.controller;

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
public class WorkShiftControllerTest {
    
    public WorkShiftControllerTest() {
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
     * Test of create method, of class WorkShiftController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        WorkShift shift = null;
        WorkShiftController instance = new WorkShiftController();
        WorkShift expResult = null;
        WorkShift result = instance.create(shift);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class WorkShiftController.
     */
    @Test
    public void testList() {
        System.out.println("list");
        WorkShiftController instance = new WorkShiftController();
        List expResult = null;
        List result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class WorkShiftController.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Long shiftId = null;
        WorkShiftController instance = new WorkShiftController();
        instance.delete(shiftId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
