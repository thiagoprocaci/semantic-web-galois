package com.semanticweb.framework.module.web.handler.exception;

import com.semanticweb.framework.module.web.IFlowController;

/**
 * Tratador de excecoes
 */
public interface ISystemExceptionHandler  {

    public static final String MSG_ALREADY_COMMITED = "Response is already commited, so no redirection to an exception page can be made. "
            + "This problem may have been caused due to an exception been thrown while a page bigger then the server output buffer was being rendered.\n\n";
    public static final String MSG_ERROR_TICKET = "\n error ticket: ";
    public static final String MSG_ERROR_ON_BARRIER = "\n ERROR on ExceptionBarrier, pleas see framework documentations: error ticket: ";
    public static final String MSG_ERROR_AFTER_BARRIER = "\n ERROR after ExceptionBarrier, pleas see framework documentations: error ticket: ";
    public static final String EXCEPTION_ERROR_CODE = "errorCode";
    /**
     * Processa o tratamento de uma excecao
     *
     * @param action o gerenciador de navegacao
     * @param exception a excecao a ser tratada
     * @param errorCode codigo de erro
     * @throws Exception quando ocorre erro no tratamento da excecao
     */
    public void handleException(IFlowController action, Throwable exception, String errorCode) throws Exception;
}