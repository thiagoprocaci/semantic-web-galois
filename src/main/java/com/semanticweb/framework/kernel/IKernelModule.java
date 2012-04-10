package com.semanticweb.framework.kernel;

import java.io.Serializable;

/**
 * Interface que define um Kernel module Todo modulo/bean tem seu initilize
 * invocado apos a criacao deste pelo container de injecao de dependecia. (apos
 * contrutor)
 */
public interface IKernelModule extends Serializable {
    /**
     * Inicializa o modulo
     */
    void initialize();
}
