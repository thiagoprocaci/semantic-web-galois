package com.semanticweb.framework.module.file.support;

import java.io.File;

/**
 * Tranformador de arquivos
 */
public interface IFileTransformer {

    /**
     *  Divide um arquivo
     * @param filePath caminho do arquivo a ser dividido
     * @param startPage pagina inicial da divisao
     * @param endPage pagina final da divisap
     * @return Retorna arquivo com a divisao
     */
    File split(String filePath, int startPage, int endPage);

    /**
     * Realiza a fusao de dois arquivos
     * @param filePath1 caminho do arquivo a ser fundido
     * @param filepath2 caminho do arquivo a ser fundido
     * @return Retorna novo arquivo
     */
    File merge(String filePath1, String filepath2);
}
