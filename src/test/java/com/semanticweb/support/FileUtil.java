package com.semanticweb.support;

import java.io.File;

/**
 *
 * Classe utilitaria para auxilio nos testes unitarios
 */
public class FileUtil {
    public static final String FILE_FOLDER = "/files/";
    public static final String STOP_WORDS_FILE_PORTUGUES = "/file/textmining/stop_words_portugues.txt";
    public static final String STOP_WORDS_FILE_INGLES = "/file/textmining/stop_words_ingles.txt";
    public static final String CIBERCULTURA_FILE = "/files/cibercultura.pdf";
    public static final String ONTO_WS_FILE = "/files/OntoWS.pdf";
    public static final String A_TXT_FILE = "/files/a.txt";
    public static final String NAO_EH_PDF_FILE = "/files/naoEhPdf.pdf";

    /**
     * @param relativePath
     *            caminho relativo
     * @return Retorna caminho absoluto de um arquivo
     */
    public String getAbsolutePath(String relativePath) {
        String path = getClass().getResource(relativePath).getPath().toString();
        path = path.replace("file:/", "");
        path = path.replace("/", File.separator);
        return path;
    }
}
