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
public class AjaxAuthenticationSuccessHandlerTest {
    
    public AjaxAuthenticationSuccessHandlerTest() {
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
     * Test of onAuthenticationSuccess method, of class AjaxAuthenticationSuccessHandler.
     */
    @Test
    public void testOnAuthenticationSuccess() throws Exception {
        System.out.println("onAuthenticationSuccess");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        Authentication authentication = null;
        AjaxAuthenticationSuccessHandler instance = new AjaxAuthenticationSuccessHandler();
        instance.onAuthenticationSuccess(request, response, authentication);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
