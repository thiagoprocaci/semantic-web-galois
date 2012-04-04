package com.semanticweb.framework.module.textmining.support;

import java.util.Map;

/**
 * 
 * Analizador de texto
 *
 */
public interface ITextAnalyzer {

    /**
     * 
     * @param text texto a ser tokenizado
     * @return Retorna texto tokenizado
     */
    Map<String, Integer> tokenize(String text);
}
