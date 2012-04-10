package com.semanticweb.framework.module.web.handler.exception.core;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;
public class TicketGeneratorTest {
    private TicketGenerator ticketGenerator;

    @Before
    public void doBefore() {
        ticketGenerator = new TicketGenerator();
    }

    @Test
    public void testGenerate() {
        assertNotNull(ticketGenerator.generateTicket());
    }
}
