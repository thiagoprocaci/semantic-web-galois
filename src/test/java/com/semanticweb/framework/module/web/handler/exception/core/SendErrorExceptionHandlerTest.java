package com.semanticweb.framework.module.web.handler.exception.core;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.handler.exception.ISystemExceptionHandler;

public class SendErrorExceptionHandlerTest {
    private static final String ERROR_CODE = "errorCode";
    private static final String TESTE = "teste";
    private SendErrorExceptionHandler sendErrorExceptionHandler;

    @Before
    public void doBefore() {
        sendErrorExceptionHandler = new SendErrorExceptionHandler();
    }

    @Test
    public void testExecuteRequestCommitedTrue() {
        try {
            Exception e = new Exception(TESTE);
            HttpSession session = mock(HttpSession.class);
            HttpServletRequest request = mock(HttpServletRequest.class);
            IFlowController flowController = mock(IFlowController.class);
            when(flowController.isRequestCommitted()).thenReturn(true);
            when(flowController.getRequest()).thenReturn(request);
            when(request.getSession()).thenReturn(session);

            sendErrorExceptionHandler.handleException(flowController, e, ERROR_CODE);
            verify(flowController, never()).getRequest();
            verify(flowController, never()).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            verify(request,never()).setAttribute("exception", e);
            verify(request,never()).setAttribute("protocolo", ERROR_CODE);
            verify(session,never()).setAttribute(ISystemExceptionHandler.EXCEPTION_ERROR_CODE, ERROR_CODE);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testExecuteRequestCommitedFalse() {
        try {
            Exception e = new Exception(TESTE);
            HttpSession session = mock(HttpSession.class);
            HttpServletRequest request = mock(HttpServletRequest.class);
            IFlowController flowController = mock(IFlowController.class);
            when(flowController.isRequestCommitted()).thenReturn(false);
            when(flowController.getRequest()).thenReturn(request);
            when(request.getSession()).thenReturn(session);

            sendErrorExceptionHandler.handleException(flowController, e, ERROR_CODE);


            verify(flowController).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

            verify(request).setAttribute("exception", e);
            verify(request).setAttribute("protocolo", ERROR_CODE);
            verify(session).setAttribute(ISystemExceptionHandler.EXCEPTION_ERROR_CODE, ERROR_CODE);

        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
