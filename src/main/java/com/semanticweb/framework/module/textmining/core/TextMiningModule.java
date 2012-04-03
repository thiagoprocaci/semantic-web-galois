package com.semanticweb.framework.module.textmining.core;

import java.util.Set;

import com.semanticweb.framework.module.textmining.ITextMiningModule;
import com.semanticweb.framework.module.textmining.support.IStopWordManager;

/**
 * 
 * Modulo de mineracao de texto
 */
public class TextMiningModule implements ITextMiningModule {
    private IStopWordManager stopWordManager;

    public void setStopWordManager(IStopWordManager stopWordManager) {
        this.stopWordManager = stopWordManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> loadStopWords() {
        return stopWordManager.loadStopWords();
    }
}
