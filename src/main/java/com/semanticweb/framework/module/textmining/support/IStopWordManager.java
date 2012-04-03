package com.semanticweb.framework.module.textmining.support;

import java.util.Set;

/**
 * Manipulador das stop words
 *
 */
public interface IStopWordManager {

    /**
     * 
     * @return Retorna lista de stop words
     */
    Set<String> loadStopWords();
}
