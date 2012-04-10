package com.semanticweb.framework.module.web.handler.exception.core;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.handler.exception.ISystemExceptionHandler;

public class SendRedirectExceptionHandlertTest {
    private static final String ERROR_CODE = "errorCode";
    private static final String TESTE = "teste";
    private static final String PAGE = "page";
    private SendRedirectExceptionHandler sendRedirectExceptionHandler;

    @Before
    public void doBefore() {
        sendRedirectExceptionHandler = new SendRedirectExceptionHandler();
        sendRedirectExceptionHandler.setErrorPage(PAGE);
    }

    @Test
    public void testExecuteRequestCommitedTrue() {
        try {
            IFlowController flowController = mock(IFlowController.class);
            when(flowController.isRequestCommitted()).thenReturn(true);
            sendRedirectExceptionHandler.handleException(flowController, new Exception(TESTE), ERROR_CODE);
            verify(flowController, never()).getRequest();
            verify(flowController, never()).sendRedirect(PAGE);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecuteRequestCommitedFalse() {
        try {
            HttpSession session = mock(HttpSession.class);
            HttpServletRequest request = mock(HttpServletRequest.class);
            IFlowController flowController = mock(IFlowController.class);
            when(flowController.isRequestCommitted()).thenReturn(false);
            when(flowController.getRequest()).thenReturn(request);
            when(request.getSession()).thenReturn(session);
            sendRedirectExceptionHandler.handleException(flowController, new Exception(TESTE), ERROR_CODE);
            verify(flowController).getRequest();
            verify(flowController).sendRedirect(PAGE);
            verify(request).getSession();
            verify(session).setAttribute(ISystemExceptionHandler.EXCEPTION_ERROR_CODE, ERROR_CODE);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
