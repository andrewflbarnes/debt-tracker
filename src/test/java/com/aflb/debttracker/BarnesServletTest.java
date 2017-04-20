/**
 * 
 */
package com.aflb.debttracker;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * @author Barnesly
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class BarnesServletTest {

    @Mock
    private HttpServletRequest httpRequest;
    @Mock
    private HttpServletResponse httpResponse;
    @Mock
    PrintWriter writer;
    
    private BarnesServlet servlet = new BarnesServlet();

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for {@link com.aflb.debttracker.BarnesServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
     */
    @Test
    public void testDoGet() {

        try {
            when(httpRequest.getParameter("name")).thenReturn("boom");
            doNothing().when(httpResponse).setContentType(any(String.class));
            when(httpResponse.getWriter()).thenReturn(writer);
            doNothing().when(writer).println(any(String.class));
            
            servlet.doGet(httpRequest, httpResponse);
            
            verify(httpRequest).getParameter("name");
            verify(httpResponse).setContentType(any(String.class));
            verify(httpResponse, atLeast(1)).getWriter();
            verify(writer).println(matches(".*\"name\" *: *\"boom\".*"));
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}
