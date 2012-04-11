package com.semanticweb.framework.module.web.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semanticweb.framework.module.web.IExceptionBarrier;
import com.semanticweb.framework.module.web.IFlowController;
import com.semanticweb.framework.module.web.handler.exception.IErrorTicketGenerator;
import com.semanticweb.framework.module.web.handler.exception.ISystemExceptionHandler;

/**
 * Realiza a captura das excecoes, fazendo o log e as devidas notificacoes e
 * impedindo que a excecao chegue ao usuario
 */
public class ExceptionBarrier implements IExceptionBarrier {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionBarrier.class);
    private static final String INVOKING_MSG = "Invoking Injected list of SystemExceptionHendler... ";
    private IErrorTicketGenerator ticketGenerator;
    private List<ISystemExceptionHandler> systemExceptionHandlers;


    /**
     * {@inheritDoc}
     *
     * @param flowController {@inheritDoc}
     * @param exception {@inheritDoc}
     */
    @Override
    public void notifySystemException(IFlowController flowController, Throwable exception) {
        String errorTicket = ticketGenerator.generateTicket();
        LOGGER.error(ISystemExceptionHandler.MSG_ERROR_TICKET + errorTicket + " " +  exception.getMessage());
        if (systemExceptionHandlers != null && !systemExceptionHandlers.isEmpty()) {
            // Theoraticaly the iteration over an list can raise ConcurrentModificationException
            // But thist list should never change since it is defined on application startup...
            LOGGER.debug(INVOKING_MSG);
            for (ISystemExceptionHandler handler : systemExceptionHandlers) {
                // yep even exceptionhandlers may raise exceptions...
                try {
                    // Nao faca NENHUMA ida a banco nesses handles.. Neste ponto o openSessionInViewFilter ja nao atua mais..
                    // Qualquer conexao feita com banco de dados deve ser fechada apos o uso, caso seja realmente necessario.
                    handler.handleException(flowController, exception, errorTicket);
                } catch (Throwable e) {
                    // This catch is an Erro INSIDE de barrier, probably on user implemented code
                    LOGGER.error(ISystemExceptionHandler.MSG_ERROR_ON_BARRIER + ticketGenerator.generateTicket() + " " + e.getMessage());
                }
            }
        }
    }


    public void setTicketGenerator(IErrorTicketGenerator ticketGenerator) {
        this.ticketGenerator = ticketGenerator;
    }

    public void setSystemExceptionHandlers(List<ISystemExceptionHandler> systemExceptionHandlers) {
        this.systemExceptionHandlers = systemExceptionHandlers;
    }
}