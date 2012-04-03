package com.semanticweb.framework.module.file;

import java.io.File;

/**
 *
 * Modulo manipulador de arquivos
 *
 */
public interface IFileModule {

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
	File merge(String filePath1, String filePath2);

	/**
     * Extrai texto de um arquivo
     *
     * @param filePath
     *            caminho do arquivo
     * @return Retorna string com o texto do arquivo
     */
    String extractTextFromFile(String filePath);

}
