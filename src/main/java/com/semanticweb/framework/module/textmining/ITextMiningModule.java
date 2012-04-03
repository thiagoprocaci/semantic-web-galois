package com.semanticweb.framework.module.textmining;

import java.util.Set;

/**
 *
 * Modulo de mineracao de texto
 */
public interface ITextMiningModule {


    /**
     * @return Retorna lista de stop words
     */
    Set<String> loadStopWords();
}
