package com.semanticweb.framework.module.textmining.model;

import java.util.List;

/**
 *
 * Conjunto de palavras chaves de mesma relevancia
 */
public class KeyWordSet {
    private KeyWordRelevance relevance;
    private List<KeyWord> wordList;

    public KeyWordRelevance getRelevance() {
        return relevance;
    }

    public void setRelevance(KeyWordRelevance relevance) {
        this.relevance = relevance;
    }

    public List<KeyWord> getWordList() {
        return wordList;
    }

    public void setWordList(List<KeyWord> wordList) {
        this.wordList = wordList;
    }
}
