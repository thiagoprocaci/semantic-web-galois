package com.semanticweb.framework.module.file.support.core;

import java.io.File;
import java.util.UUID;

import com.semanticweb.framework.module.file.support.IFileNameGenerator;

/**
 * Gerador de nomes de arquivos pdf
 */
public class PdfFileNameGenerator implements IFileNameGenerator {
    private static final String PDF = ".pdf";

    /**
     * {@inheritDoc}
     */
    @Override
    public String generateName(String folder) throws IllegalArgumentException {
        if (folder == null) {
            throw new IllegalArgumentException("folder can not be null");
        }
        String name = folder + UUID.randomUUID().toString() + PDF;
        File file = new File(name);
        while (file.exists()) {
            name = folder + UUID.randomUUID().toString();
        }
        return name;
    }
}
