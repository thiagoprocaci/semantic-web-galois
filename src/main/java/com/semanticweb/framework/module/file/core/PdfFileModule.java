package com.semanticweb.framework.module.file.core;

import java.io.File;

import com.semanticweb.framework.module.file.IFileModule;
import com.semanticweb.framework.module.file.support.IFileTransformer;
import com.semanticweb.framework.module.file.support.ITextFileExtractor;

/**
 *
 * Modulo manipulador de arquivos pdf
 *
 */
public class PdfFileModule implements IFileModule {
    private static final long serialVersionUID = 1L;
    private IFileTransformer fileTransformer;
    private ITextFileExtractor textFileExtractor;


    public void setTextFileExtractor(ITextFileExtractor textFileExtractor) {
        this.textFileExtractor = textFileExtractor;
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public String extractTextFromFile(String filePath) {
        return textFileExtractor.extractTextFromFile(filePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        // TODO log
    }


}
