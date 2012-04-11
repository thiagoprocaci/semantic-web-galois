package com.semanticweb.framework.module.textmining.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semanticweb.framework.module.file.IFileModule;
import com.semanticweb.framework.module.textmining.ITextMiningModule;
import com.semanticweb.framework.module.textmining.model.KeyWordSet;
import com.semanticweb.framework.module.textmining.support.IMetadataAdvisor;

/**
 * Modulo de mineracao de texto
 */
public class TextMiningModule implements ITextMiningModule {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LoggerFactory.getLogger(TextMiningModule.class);
    private IFileModule fileModule;
    private IMetadataAdvisor metadataAdvisor;

    public void setFileModule(IFileModule fileModule) {
        this.fileModule = fileModule;
    }

    public void setMetadataAdvisor(IMetadataAdvisor metadataAdvisor) {
        this.metadataAdvisor = metadataAdvisor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyWordSet> suggestKeyWords(String filePath) {
        String text = fileModule.extractTextFromFile(filePath);
        return metadataAdvisor.suggestKeyWords(text);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        LOGGER.info("initialiing text mining module");
    }
}
