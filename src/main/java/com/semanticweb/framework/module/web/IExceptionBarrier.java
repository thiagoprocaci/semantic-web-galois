package com.semanticweb.framework.module.web;

/**
 * Realiza a captura das excecoes, fazendo o log e as devidas notificacoes e
 * impedindo que a excecao chegue ao usuario
 */
public interface IExceptionBarrier {
    /**
     * Realiza a notificacao de uma excecao de sistema
     *
     * @param flowController
     *            o gerenciador de navegacao
     * @param exception
     *            a excecao a ser tratada
     */
    void notifySystemException(IFlowController flowController, Throwable exception);
}
