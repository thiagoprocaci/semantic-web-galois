package com.semanticweb.framework.module.web.handler.request;

import com.semanticweb.framework.module.web.IFlowController;

/**
 * Handler chamado antes do request
 */
public interface IBeforeRequestHandler {
    /**
     * Execucao do handler
     *
     * @param flowController
     *            o gerenciador de navegacao
     * @throws Exception
     *             quando ocorre qq tipo de excecao na execucao
     */
    void execute(IFlowController flowController) throws Exception;
}