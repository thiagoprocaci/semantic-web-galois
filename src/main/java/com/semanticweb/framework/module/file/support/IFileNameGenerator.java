package com.semanticweb.framework.module.file.support;

/**
 * Gerador de nomes de arquivos
 */
public interface IFileNameGenerator {
    /**
     * @param folder pasta do arquivo
     * @return Retorna folder + nome do arquivo
     */
    String generateName(String folder) throws IllegalArgumentException;
}
