package com.semanticweb.framework.module.web.core;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semanticweb.framework.module.web.IExceptionBarrier;
import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.IWebModule;
import com.semanticweb.framework.module.web.handler.exception.ISystemExceptionHandler;
import com.semanticweb.framework.module.web.handler.request.IAfterRequestHandler;
import com.semanticweb.framework.module.web.handler.request.IBeforeRequestHandler;

/**
 * Modulo de Web do Kernel
 */
public class WebModule implements IWebModule {
    private static final long serialVersionUID = -122549191183517999L;
    private static final Logger LOGGER = LoggerFactory.getLogger(WebModule.class);
    private List<IBeforeRequestHandler> beforeRequestHandlers;
    private List<IAfterRequestHandler> afterRequestHandlers;
    private IExceptionBarrier exceptionBarrier;
    private IFlowController flowController;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        LOGGER.info("Initializing web module");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processRequest(ServletRequest request, ServletResponse response, FilterChain chain) {
        // First we assumes that the web filter Will only process HttpRequests.
        if (request instanceof HttpServletRequest) {
            try {
                // Let flowcontroller manger understand what is happening ...
                flowController.processRequest(request, response);
                // Call All BeforeRequestHandlers registered on application.xml
                notifyBeforeRequestHandlers();
                // So call the remaining filters...
                invokeApplication(chain, request, response);
            } catch (Throwable exception) {
                // This Catch represents the ExceptionBarrier itself
                exceptionBarrier.notifySystemException(getFlowController(), exception);
            } finally {
                notifyAfterRequestHandlers();
            }
        } else {
            LOGGER.error("This web Module implementation works only for http requests");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IFlowController getFlowController() {
        return flowController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IExceptionBarrier getExceptionBarrier() {
        return exceptionBarrier;
    }

    /**
     * Check if we have an handler before request registrated, loop over the
     * handler and execute them
     *
     * @throws Exception
     *             quando ocorre erro na execucao pelo handler
     */
    protected void notifyBeforeRequestHandlers() throws Exception {
        if (beforeRequestHandlers != null && !beforeRequestHandlers.isEmpty()) {
            LOGGER.debug("BeforeRequestHandlers injected ..calling execution");
            for (IBeforeRequestHandler handler : beforeRequestHandlers) {
                handler.execute(getFlowController());
            }
        }
    }

    /**
     * Executado apos o tratamento pelo handler
     */
    protected void notifyAfterRequestHandlers() {
        if (afterRequestHandlers != null && !afterRequestHandlers.isEmpty()) {
            LOGGER.debug("AfterRequestHandlers injected ..calling execution");
            for (IAfterRequestHandler handler : afterRequestHandlers) {
                try {
                    // This is the most possibly dangerous part on the
                    // framework...couse everything injected there will be after
                    // ExceptionBarrier
                    // no changes on request (redirect or sendError) can be made
                    // from now on...
                    handler.execute(getFlowController());
                } catch (Throwable exception) {
                    // dumb programmer dumb Programmer !!! well ... all we can
                    // do is log and exit
                    LOGGER.error(ISystemExceptionHandler.MSG_ERROR_AFTER_BARRIER + exception.getMessage());
                }
            }
        }
    }

    /**
     * Da sequencia ao encadeamento de filtros
     *
     * @param chain
     *            cadeia para o encadeamento de filtros
     * @param request
     *            objeto request
     * @param response
     *            objeto response
     * @throws IOException
     *             quando ocorre erro no processamento da cadeia
     * @throws ServletException
     *             quando ocorre erro no processamento da cadeia
     */
    private void invokeApplication(FilterChain chain, ServletRequest request, ServletResponse response) throws IOException, ServletException {
        if (!getFlowController().isRequestCommitted()) {
            chain.doFilter(request, response);
        } else {
            LOGGER.debug("Response Already Commited before Application Invoke...!");
        }
    }

    public void setBeforeRequestHandlers(List<IBeforeRequestHandler> beforeRequestHandlers) {
        this.beforeRequestHandlers = beforeRequestHandlers;
    }

    public void setAfterRequestHandlers(List<IAfterRequestHandler> afterRequestHandlers) {
        this.afterRequestHandlers = afterRequestHandlers;
    }

    public void setExceptionBarrier(IExceptionBarrier exceptionBarrier) {
        this.exceptionBarrier = exceptionBarrier;
    }

    public void setFlowController(IFlowController flowController) {
        this.flowController = flowController;
    }
}
