package com.semanticweb.framework.module.textmining.support;

import java.util.List;

import com.semanticweb.framework.module.textmining.model.KeyWordSet;

/**
 * Sugere metadados para um texto
 */
public interface IMetadataAdvisor {
    /**
     *
     * @param text texto que sera analisado e inferido as palavras chaves
     * @return Retorna lista de palavras chaves separadas pela sua relevancia
     */
    List<KeyWordSet> suggestKeyWords(String text);
}
