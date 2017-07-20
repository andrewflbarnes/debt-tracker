/**
 * 
 */
package com.aflb.debttracker;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
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
    private PrintWriter writer;
    @Spy
    private BarnesServlet servlet = new BarnesServlet();

    /**
     * Test method for {@link com.aflb.debttracker.BarnesServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)}.
     */
    @Test
    public void testDoGet() {

        try {
            when(httpRequest.getParameter("name")).thenReturn("boom");
            when(httpRequest.getParameter("password")).thenReturn("pword");
            doNothing().when(httpResponse).setContentType(any(String.class));
            when(httpResponse.getWriter()).thenReturn(writer);
            doNothing().when(writer).println(any(String.class));
            
            servlet.doGet(httpRequest, httpResponse);
            
            verify(httpRequest, times(1)).getParameter("name");
            verify(httpResponse, times(1)).setContentType(any(String.class));
            verify(httpResponse, atLeast(2)).getWriter();
            verify(writer).println(matches(".*\"name\" *: *\"boom\".*"));
            verify(writer).println(matches(".*\"pass\" *: *\"pword\".*"));
        } catch (IOException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

}
