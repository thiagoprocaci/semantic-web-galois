package com.semanticweb.framework.module.textmining;

import java.util.List;

import com.semanticweb.framework.module.textmining.model.KeyWordSet;

/**
 *
 * Modulo de mineracao de texto
 */
public interface ITextMiningModule {


    /**
     *
     * @return Retorna melhores chaves que descrevem um arquivo
     */
    List<KeyWordSet> suggestKeyWords(String filePath);
}
