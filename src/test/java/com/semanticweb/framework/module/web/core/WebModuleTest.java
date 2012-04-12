package com.semanticweb.framework.module.web.core;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import com.semanticweb.framework.module.web.IExceptionBarrier;
import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.handler.request.IAfterRequestHandler;
import com.semanticweb.framework.module.web.handler.request.IBeforeRequestHandler;

public class WebModuleTest {
    private WebModule webModule;
    private IFlowController flowController;
    private IExceptionBarrier exceptionBarrier;
    private IBeforeRequestHandler beforeRequestHandler;
    private IAfterRequestHandler afterRequestHandler;

    @Before
    public void doBefore() {
        webModule = new WebModule();
        flowController = mock(IFlowController.class);
        exceptionBarrier = mock(IExceptionBarrier.class);
        beforeRequestHandler = mock(IBeforeRequestHandler.class);
        afterRequestHandler = mock(IAfterRequestHandler.class);
        List<IBeforeRequestHandler> beforeList = new ArrayList<IBeforeRequestHandler>();
        beforeList.add(beforeRequestHandler);
        List<IAfterRequestHandler> afterList = new ArrayList<IAfterRequestHandler>();
        afterList.add(afterRequestHandler);
        webModule.setAfterRequestHandlers(afterList);
        webModule.setBeforeRequestHandlers(beforeList);
        webModule.setExceptionBarrier(exceptionBarrier);
        webModule.setFlowController(flowController);
        webModule.initialize();
    }

    @Test
    public void testProcessHttpRequest() {
        try {
            ServletRequest request = mock(HttpServletRequest.class);
            ServletResponse response = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);
            webModule.processRequest(request, response, chain);
            InOrder inOrder = inOrder(flowController, beforeRequestHandler, chain, afterRequestHandler);
            inOrder.verify(flowController).processRequest(request, response);
            inOrder.verify(beforeRequestHandler).execute(flowController);
            inOrder.verify(chain).doFilter(request, response);
            inOrder.verify(afterRequestHandler).execute(flowController);
            verify(exceptionBarrier, never()).notifySystemException(eq(flowController), any(Throwable.class));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testProcessNotHttpRequest() {
        try {
            ServletRequest request = mock(ServletRequest.class);
            ServletResponse response = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);
            webModule.processRequest(request, response, chain);
            verify(flowController, never()).processRequest(request, response);
            verify(beforeRequestHandler, never()).execute(flowController);
            verify(chain, never()).doFilter(request, response);
            verify(afterRequestHandler, never()).execute(flowController);
            verify(exceptionBarrier, never()).notifySystemException(eq(flowController), any(Throwable.class));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testExceptionBarrierInvokeApplication() {
        try {
            ServletRequest request = mock(HttpServletRequest.class);
            ServletResponse response = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);

            doThrow(new RuntimeException()).when(chain).doFilter(request, response);

            webModule.processRequest(request, response, chain);
            InOrder inOrder = inOrder(flowController, beforeRequestHandler, chain, exceptionBarrier, afterRequestHandler);
            inOrder.verify(flowController).processRequest(request, response);
            inOrder.verify(beforeRequestHandler).execute(flowController);
            inOrder.verify(chain).doFilter(request, response);
            inOrder.verify(exceptionBarrier).notifySystemException(eq(flowController), any(Throwable.class));
            inOrder.verify(afterRequestHandler).execute(flowController);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testExceptionBarrierBeforeRequesrHandle() {
        try {
            ServletRequest request = mock(HttpServletRequest.class);
            ServletResponse response = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);

            doThrow(new Exception()).when(beforeRequestHandler).execute(flowController);

            webModule.processRequest(request, response, chain);
            InOrder inOrder = inOrder(flowController, beforeRequestHandler, exceptionBarrier, afterRequestHandler);
            inOrder.verify(flowController).processRequest(request, response);
            inOrder.verify(beforeRequestHandler).execute(flowController);
            inOrder.verify(exceptionBarrier).notifySystemException(eq(flowController), any(Throwable.class));
            inOrder.verify(afterRequestHandler).execute(flowController);

            verify(chain, never()).doFilter(request, response);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testExceptionAfterRequesrHandle() {
        try {
            ServletRequest request = mock(HttpServletRequest.class);
            ServletResponse response = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);

            doThrow(new Exception()).when(afterRequestHandler).execute(flowController);

            webModule.processRequest(request, response, chain);
            InOrder inOrder = inOrder(flowController, beforeRequestHandler, chain, afterRequestHandler);
            inOrder.verify(flowController).processRequest(request, response);
            inOrder.verify(beforeRequestHandler).execute(flowController);
            inOrder.verify(chain).doFilter(request, response);
            inOrder.verify(afterRequestHandler).execute(flowController);


            verify(exceptionBarrier, never()).notifySystemException(eq(flowController), any(Throwable.class));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testProcessHttpRequestCommited() {
        try {
            ServletRequest request = mock(HttpServletRequest.class);
            ServletResponse response = mock(HttpServletResponse.class);
            FilterChain chain = mock(FilterChain.class);
            when(flowController.isRequestCommitted()).thenReturn(true);
            webModule.processRequest(request, response, chain);
            InOrder inOrder = inOrder(flowController, beforeRequestHandler,  afterRequestHandler);
            inOrder.verify(flowController).processRequest(request, response);
            inOrder.verify(beforeRequestHandler).execute(flowController);
            inOrder.verify(afterRequestHandler).execute(flowController);
            verify(exceptionBarrier, never()).notifySystemException(eq(flowController), any(Throwable.class));
            verify(chain, never()).doFilter(request, response);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testGet() {
        assertEquals(flowController, webModule.getFlowController());
        assertEquals(exceptionBarrier, webModule.getExceptionBarrier());
    }


}
