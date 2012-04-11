package com.semanticweb.framework.module.web.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

public class FlowControllerTest {
    private FlowController flowController;
    private HttpServletRequest request;
    private HttpServletResponse response;

    @Before
    public void doBefore() {
        flowController = new FlowController();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        flowController.processRequest(request, response);
    }

    @Test
    public void testSendRedirect() {
        try {
            flowController.sendRedirect("url");
            verify(response).sendRedirect("url");
            assertTrue(flowController.isRedirectRequested());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSendError() {
        try {
            flowController.sendError(500);
            verify(response).sendError(500);
            assertTrue(flowController.isRedirectRequested());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testGetRequestURI() {
        when(request.getRequestURI()).thenReturn("uri");
        assertEquals("uri", flowController.getRequestURI());
    }

    @Test
    public void testRequest() {
        assertEquals(request, flowController.getRequest());
    }

    @Test
    public void testInvalidateSessionNotNull() {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(session);
        flowController.invalidateSession();
        verify(session).invalidate();
    }

    @Test
    public void testInvalidateSessionNull() {
        HttpSession session = mock(HttpSession.class);
        when(request.getSession(false)).thenReturn(null);
        flowController.invalidateSession();
        verify(session, never()).invalidate();
    }

    @Test
    public void testIsRequestCommitted() {
        when(response.isCommitted()).thenReturn(true);
        assertTrue(flowController.isRequestCommitted());
    }

    @Test
    public void testSetHeader() {
        flowController.setHeader("1", "2");
        verify(response).setHeader("1", "2");
    }

    @Test
    public void testGetBufferSize() {
        when(response.getBufferSize()).thenReturn(10);
        assertEquals(10, flowController.getBufferSize());
        verify(response).getBufferSize();
    }

    @Test
    public void testGetCharacterEncoding() {
        when(response.getCharacterEncoding()).thenReturn("char");
        assertEquals("char", flowController.getCharacterEncoding());
        verify(response).getCharacterEncoding();
    }

    @Test
    public void testGetResponseContentType() {
        when(response.getContentType()).thenReturn("cont");
        assertEquals("cont", flowController.getResponseContentType());
        verify(response).getContentType();
    }

    @Test
    public void testGetLocale() {
        Locale locale = Locale.CANADA;
        when(response.getLocale()).thenReturn(locale);
        assertEquals(locale, flowController.getLocale());
        verify(response).getLocale();
    }

    @Test
    public void testResetBuffer() {
        flowController.resetBuffer();
        verify(response).resetBuffer();
    }

    @Test
    public void testSetBufferSize() {
        flowController.setBufferSize(10);
        verify(response).setBufferSize(10);
    }

    @Test
    public void testSetCharacterEncoding() {
        flowController.setCharacterEncoding("enc");
        verify(response).setCharacterEncoding("enc");
    }

    @Test
    public void testSetContentLength() {
        flowController.setContentLength(10);
        verify(response).setContentLength(10);
    }

    @Test
    public void testSetContentType() {
        flowController.setContentType("cont");
        verify(response).setContentType("cont");
    }

    @Test
    public void testSetLocale() {
        Locale locale = Locale.CANADA;
        flowController.setLocale(locale);
        verify(response).setLocale(locale);
    }

    @Test
    public void testAddCookie() {
        Cookie cookie = new Cookie("", "");
        flowController.addCookie(cookie);
        verify(response).addCookie(cookie);
    }

    @Test
    public void testAddHeader() {
        flowController.addHeader("a", "b");
        verify(response).addHeader("a", "b");
    }

    @Test
    public void testAddIntHeader() {
        flowController.addIntHeader("a", 1);
        verify(response).addIntHeader("a", 1);
    }

    @Test
    public void testContainsHeader() {
        when(response.containsHeader("a")).thenReturn(true);
        assertTrue(flowController.containsHeader("a"));
        verify(response).containsHeader("a");
    }

    @Test
    public void testAddDateHeader() {
        flowController.addDateHeader("a", 1L);
        verify(response).addDateHeader("a", 1L);
    }

    @Test
    public void testEncodeRedirectURL() {
        when(response.encodeRedirectURL("a")).thenReturn("b");
        assertEquals("b", flowController.encodeRedirectURL("a"));
        verify(response).encodeRedirectURL("a");
    }

    @Test
    public void testEncodeURL() {
        when(response.encodeURL("a")).thenReturn("b");
        assertEquals("b", flowController.encodeURL("a"));
        verify(response).encodeURL("a");
    }

    @Test
    public void testSetDateHeader() {
        flowController.setDateHeader("a", 1L);
        verify(response).setDateHeader("a", 1L);
    }

    @Test
    public void testSetIntHeader() {
        flowController.setIntHeader("a", 1);
        verify(response).setIntHeader("a", 1);
    }

    @Test
    public void testSetStatus() {
        flowController.setStatus(4);
        verify(response).setStatus(4);
    }

    @Test
    public void testReset() {
        flowController.reset();
        verify(response).reset();
    }
}
