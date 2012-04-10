package com.semanticweb.framework.module.web.handler.exception;

/**
 *
 * Gera ticket de erro para o sistema
 *
 */
public interface IErrorTicketGenerator {

    /**
     * Gera um ticket
     * @return o ticket gerado
     */
    String generateTicket();
}
