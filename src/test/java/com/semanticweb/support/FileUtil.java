package com.semanticweb.support;

import java.io.File;

/**
 *
 * Classe utilitaria para auxilio nos testes unitarios
 */
public class FileUtil {

    /**
     * @param relativePath caminho relativo
     * @return Retorna caminho absoluto de um arquivo
     */
    public static String getAbsolutePath(String relativePath) {
        String path = FileUtil.class.getResource(relativePath).getPath().toString();
        path = path.replace("file:/", "");
        path = path.replace("/", File.separator);
        return path;
    }
}
