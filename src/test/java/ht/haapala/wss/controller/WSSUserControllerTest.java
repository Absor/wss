/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.controller;

import ht.haapala.wss.data.WSSUser;
import java.security.Principal;
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
public class WSSUserControllerTest {
    
    public WSSUserControllerTest() {
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
     * Test of loggedInfo method, of class WSSUserController.
     */
    @Test
    public void testLoggedInfo() {
        System.out.println("loggedInfo");
        Principal principal = null;
        WSSUserController instance = new WSSUserController();
        WSSUser expResult = null;
        WSSUser result = instance.loggedInfo(principal);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of list method, of class WSSUserController.
     */
    @Test
    public void testList() {
        System.out.println("list");
        WSSUserController instance = new WSSUserController();
        List expResult = null;
        List result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class WSSUserController.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        WSSUser user = null;
        WSSUserController instance = new WSSUserController();
        WSSUser expResult = null;
        WSSUser result = instance.create(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class WSSUserController.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String username = "";
        WSSUserController instance = new WSSUserController();
        instance.delete(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
