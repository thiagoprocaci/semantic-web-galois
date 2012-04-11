package com.semanticweb.framework.module.web.handler.exception.core;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.handler.exception.ISystemExceptionHandler;

/**
 * Principal Handler
 */
public class SendErrorExceptionHandler implements ISystemExceptionHandler {
    private static final String PROTOCOLO = "protocolo";
    private static final String EXCEPTION = "exception";
    private static final Logger LOGGER = LoggerFactory.getLogger(SendErrorExceptionHandler.class);

    /**
     * {@inheritDoc}
     *
     * @param flowController
     *            {@inheritDoc}
     * @param exception
     *            {@inheritDoc}
     * @param errorCode
     *            {@inheritDoc}
     */
    @Override
    public void handleException(IFlowController flowController, Throwable exception, String errorCode) {
        try {
            // somtimes the server flueshes de response without our will.. if it
            // happens, there is nothing more to do ... log exits...
            if (flowController.isRequestCommitted()) {
                // O log stacktrace abaixo eh desnecessario. Ficando disponivel
                // apenas como warn
                LOGGER.error(MSG_ALREADY_COMMITED);
                LOGGER.error(MSG_ERROR_TICKET + errorCode);
                LOGGER.warn("This is no real Bug. This stacktrace will only apear as WARN and can be ignored", exception);
            } else {
                LOGGER.error(MSG_ERROR_TICKET + errorCode);
                // seta protocolo e excecao lancada no request
                flowController.getRequest().setAttribute(EXCEPTION, exception);
                flowController.getRequest().setAttribute(PROTOCOLO, errorCode);
                flowController.getRequest().getSession().setAttribute(ISystemExceptionHandler.EXCEPTION_ERROR_CODE, errorCode);
                flowController.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } catch (IOException ex) {
            LOGGER.error(ISystemExceptionHandler.MSG_ERROR_ON_BARRIER + ex.getMessage(), exception);
        }
    }
}