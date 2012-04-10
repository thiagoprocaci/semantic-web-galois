package com.semanticweb.framework.module.web;

import java.awt.event.ActionEvent;

import javax.servlet.FilterChain;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.semanticweb.framework.kernel.IKernelModule;

/**
 * Interface do modulo de Web do Kernel
 */
public interface IWebModule extends IKernelModule {
    /**
     * This method is the main entrance for the Framework He Coordenates
     * Businesses Rules Injected BeforeRequest, AfterRequest on the
     * ExceptionBarrier
     *
     * @param request
     *            objeto request
     * @param response
     *            objeto response
     * @param chain
     *            cadeia de filtros
     */
    void processRequest(ServletRequest request, ServletResponse response, FilterChain chain);

    /**
     * Retorna o gerenciador de navegacao
     *
     * @return o gerenciador de navegacao
     */
    IFlowController getFlowController();

    /**
     * Retorna a barreira de excecao
     *
     * @return a barreira de excecao
     */
    IExceptionBarrier getExceptionBarrier();

    /**
     * Executado antes do tratamento do evento
     *
     * @param event
     *            evendo a ser tratado
     */
    void notifyBeforeEventHandler(ActionEvent event);

    /**
     * Executado depois do tratamento do evento
     *
     * @param event
     *            evendo a ser tratado
     */
    void notifyAfterEventHandler(ActionEvent event);
}
