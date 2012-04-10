package com.semanticweb.framework.module.web.handler.request;

import com.semanticweb.framework.module.web.IFlowController;

/**
 * Handler chamado apos o request
 */
public interface IAfterRequestHandler {

    /**
     * Execucao do handler
     *
     * @param flowController o gerenciador de navegacao
     * @throws Exception quando ocorre qualquer tipo de excecao na execucao
     */
    void execute(IFlowController flowController) throws Exception;
}
