package com.semanticweb.framework.module.web.core;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.handler.exception.IErrorTicketGenerator;
import com.semanticweb.framework.module.web.handler.exception.ISystemExceptionHandler;

public class ExceptionBarrierTest {
    private static final String TICKET = "ticket";
    private ExceptionBarrier exceptionBarrier;
    private IErrorTicketGenerator errorTicketGenerator;
    private ISystemExceptionHandler systemExceptionHandler;
    private ISystemExceptionHandler systemExceptionHandler_;

    @Before
    public void doBefore() {
        exceptionBarrier = new ExceptionBarrier();
        errorTicketGenerator = mock(IErrorTicketGenerator.class);
        systemExceptionHandler = mock(ISystemExceptionHandler.class);
        systemExceptionHandler_ = mock(ISystemExceptionHandler.class);
        List<ISystemExceptionHandler> list = new ArrayList<ISystemExceptionHandler>();
        list.add(systemExceptionHandler);
        list.add(systemExceptionHandler_);
        exceptionBarrier.setSystemExceptionHandlers(list);
        exceptionBarrier.setTicketGenerator(errorTicketGenerator);
        when(errorTicketGenerator.generateTicket()).thenReturn(TICKET);
    }

    @Test
    public void testNotifySystemException() {
        Throwable e = new Exception();
        IFlowController flowController = mock(IFlowController.class);
        exceptionBarrier.notifySystemException(flowController, e);
        try {
            verify(systemExceptionHandler).handleException(flowController, e, TICKET);
            verify(systemExceptionHandler_).handleException(flowController, e, TICKET);
        } catch (Exception e1) {
            fail();
        }
    }
}
