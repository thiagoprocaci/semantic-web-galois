package com.semanticweb.framework.module.file.core;

import java.io.File;

import com.semanticweb.framework.module.file.IFileModule;
import com.semanticweb.framework.module.file.support.IFileTransformer;

/**
 *
 * Modulo manipulador de arquivos pdf
 *
 */
public class PdfFileModule implements IFileModule {
    private IFileTransformer fileTransformer;


    public void setFileTransformer(IFileTransformer fileTransformer) {
        this.fileTransformer = fileTransformer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File split(String filePath, int startPage, int endPage) {
        return fileTransformer.split(filePath, startPage, endPage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File merge(String filePath1, String filePath2) {
       return fileTransformer.merge(filePath1, filePath2);
    }


}
