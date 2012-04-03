package com.semanticweb.framework.module.textmining;

import java.util.Set;

/**
 *
 * Modulo de mineracao de texto
 */
public interface ITextMiningModule {
    /**
     * Extrai texto de um arquivo
     *
     * @param filePath
     *            caminho do arquivo
     * @return Retorna string com o texto do arquivo
     */
    String extractTextFromFile(String filePath);

    /**
     * @return Retorna lista de stop words
     */
    Set<String> loadStopWords();
}
