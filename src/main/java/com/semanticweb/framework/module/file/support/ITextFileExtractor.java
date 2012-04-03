package com.semanticweb.framework.module.file.support;

/**
 *
 * Extrator de texto de um arquivo
 */
public interface ITextFileExtractor {

    /**
     * Extrai texto de um arquivo
     *
     * @param filePath
     *            caminho do arquivo
     * @return Retorna string com o texto do arquivo
     */
    String extractTextFromFile(String filePath);
}
