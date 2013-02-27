/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ht.haapala.wss.authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.security.core.Authentication;

/**
 *
 * @author Heikki Haapala
 */
public class AjaxLogoutSuccessHandlerTest {
    
    public AjaxLogoutSuccessHandlerTest() {
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
     * Test of onLogoutSuccess method, of class AjaxLogoutSuccessHandler.
     */
    @Test
    public void testOnLogoutSuccess() throws Exception {
        System.out.println("onLogoutSuccess");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Authentication authentication = null;
        AjaxLogoutSuccessHandler instance = new AjaxLogoutSuccessHandler();
        instance.onLogoutSuccess(request, response, authentication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
