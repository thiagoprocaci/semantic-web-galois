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
    
    /**
     * 
     * @param word palavra a ser analisada
     * @return Retorna true caso o parametro seja uma stopword
     */
    boolean isStopWord(String word);
}
