package com.semanticweb.framework.module.web.handler.exception.core;

import java.util.UUID;

import com.semanticweb.framework.module.web.handler.exception.IErrorTicketGenerator;

/**
 *
 *  Gera ticket de erro para o sistema
 *
 */
public class TicketGenerator implements IErrorTicketGenerator {

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateTicket() {
        return UUID.randomUUID().toString();
    }
}
