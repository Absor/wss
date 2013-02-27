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
import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author Heikki Haapala
 */
public class AjaxAuthenticationFailureHandlerTest {
    
    public AjaxAuthenticationFailureHandlerTest() {
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
     * Test of onAuthenticationFailure method, of class AjaxAuthenticationFailureHandler.
     */
    @Test
    public void testOnAuthenticationFailure() throws Exception {
        System.out.println("onAuthenticationFailure");
        HttpServletRequest request = null;
        HttpServletResponse response = null;
        AuthenticationException exception = null;
        AjaxAuthenticationFailureHandler instance = new AjaxAuthenticationFailureHandler();
        instance.onAuthenticationFailure(request, response, exception);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
