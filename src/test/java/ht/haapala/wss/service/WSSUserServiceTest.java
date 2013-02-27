/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.service;

import ht.haapala.wss.data.WSSUser;
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
public class WSSUserServiceTest {
    
    public WSSUserServiceTest() {
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
     * Test of findOne method, of class WSSUserService.
     */
    @Test
    public void testFindOne() {
        System.out.println("findOne");
        String username = "";
        WSSUserService instance = new WSSUserServiceImpl();
        WSSUser expResult = null;
        WSSUser result = instance.findOne(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAll method, of class WSSUserService.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        WSSUserService instance = new WSSUserServiceImpl();
        List expResult = null;
        List result = instance.findAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class WSSUserService.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        WSSUser user = null;
        WSSUserService instance = new WSSUserServiceImpl();
        WSSUser expResult = null;
        WSSUser result = instance.save(user);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class WSSUserService.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String username = "";
        WSSUserService instance = new WSSUserServiceImpl();
        instance.delete(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class WSSUserServiceImpl implements WSSUserService {

        public WSSUser findOne(String username) {
            return null;
        }

        public List<WSSUser> findAll() {
            return null;
        }

        public WSSUser save(WSSUser user) {
            return null;
        }

        public void delete(String username) {
        }
    }
}
