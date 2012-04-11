package com.semanticweb.framework.module.textmining.support.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.semanticweb.framework.module.textmining.support.IStopWordManager;

/**
 * Manipulador das stop words
 */
public class StopWordManager implements IStopWordManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(StopWordManager.class);
    private String stopWordsFilePath;
    // cache das stop words
    private Set<String> stopWords;

    public void setStopWordsFilePath(String stopWordsFilePath) {
        this.stopWordsFilePath = stopWordsFilePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> loadStopWords() {
        if (stopWords == null) {
            stopWords = new HashSet<String>();
            try {
                Scanner scanner = new Scanner(new File(stopWordsFilePath));
                String string = null;
                while (scanner.hasNext()) {
                    string = scanner.nextLine();
                    if (string != null) {
                        string = string.trim();
                        if (string.length() > 0) {
                            stopWords.add(string.trim());
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                LOGGER.error("error: " + e.getMessage());
            }
        }
        return stopWords;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStopWord(String word) {
        return loadStopWords().contains(word);
    }
}
