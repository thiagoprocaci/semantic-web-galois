package com.semanticweb.framework.module.web.handler.exception.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;

import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.handler.exception.ISystemExceptionHandler;

/**
 * Handler de Exception que realiza um redirect para pagina de erro
 */
public class SendRedirectExceptionHandler implements ISystemExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(SendRedirectExceptionHandler.class);
    private String errorPage;

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
    public void handleException(IFlowController flowController, Throwable exception, String errorCode) throws Exception {
        // somtimes the server flueshes de response without our will.. if it
        // happens, there is nothing more to do ... log exits...
        if (flowController.isRequestCommitted()) {
            // O log stacktrace abaixo eh desnecessario. Ficando disponivel
            // apenas como warn
            LOGGER.error(MSG_ALREADY_COMMITED + errorCode);
            LOGGER.warn("This is no real Bug. This stacktrace will only apear as WARN and can be ignored", exception);
        } else {
            flowController.getRequest().getSession().setAttribute(EXCEPTION_ERROR_CODE, errorCode);
            flowController.sendRedirect(errorPage);
        }
    }

    @Required
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}