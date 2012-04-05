package com.semanticweb.framework.module.textmining.support.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.semanticweb.framework.module.textmining.model.KeyWord;
import com.semanticweb.framework.module.textmining.model.KeyWordRelevance;
import com.semanticweb.framework.module.textmining.model.KeyWordSet;
import com.semanticweb.framework.module.textmining.support.IMetadataAdvisor;
import com.semanticweb.framework.module.textmining.support.ITextAnalyzer;

/**
 * Sugere metadados para um texto
 */
public class MetadataAdvisor implements IMetadataAdvisor {
    private ITextAnalyzer textAnalyzer;

    public void setTextAnalyzer(ITextAnalyzer textAnalyzer) {
        this.textAnalyzer = textAnalyzer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<KeyWordSet> suggestKeyWords(String text) {
        List<KeyWordSet> list = new ArrayList<KeyWordSet>();
        if (text != null) {
            Map<String, Integer> tokens = textAnalyzer.tokenize(text);
            if (tokens != null & !tokens.isEmpty()) {
                KeyWordSet keyWordSetHigh = new KeyWordSet();
                keyWordSetHigh.setRelevance(KeyWordRelevance.HIGH);
                keyWordSetHigh.setWordList(new ArrayList<KeyWord>());
                KeyWordSet keyWordSetMedium = new KeyWordSet();
                keyWordSetMedium.setRelevance(KeyWordRelevance.MEDIUM);
                keyWordSetMedium.setWordList(new ArrayList<KeyWord>());
                KeyWordSet keyWordSetLow = new KeyWordSet();
                keyWordSetLow.setRelevance(KeyWordRelevance.LOW);
                keyWordSetLow.setWordList(new ArrayList<KeyWord>());
                Integer highOccurrence = 100;
                Integer mediumOccurrence = 50;
                Integer lowOccurrence = 30;
                String maxKey = getMaxKey(tokens);
                if (highOccurrence > tokens.get(maxKey)) {
                    highOccurrence = (int) (tokens.get(maxKey) / 1.5);
                    mediumOccurrence = (int) (highOccurrence / 2);
                    lowOccurrence = (int) (highOccurrence / 3.33);
                }
                for (String key : tokens.keySet()) {
                    if (tokens.get(key) >= highOccurrence) {
                        keyWordSetHigh.getWordList().add(new KeyWord(key, tokens.get(key)));
                    } else if (tokens.get(key) >= mediumOccurrence) {
                        keyWordSetMedium.getWordList().add(new KeyWord(key, tokens.get(key)));
                    } else if (tokens.get(key) >= lowOccurrence) {
                        keyWordSetLow.getWordList().add(new KeyWord(key, tokens.get(key)));
                    }
                }
                if (!keyWordSetHigh.getWordList().isEmpty()) {
                    Collections.sort(keyWordSetHigh.getWordList());
                    list.add(keyWordSetHigh);
                }
                if (!keyWordSetMedium.getWordList().isEmpty()) {
                    Collections.sort(keyWordSetMedium.getWordList());
                    list.add(keyWordSetMedium);
                }
                if (!keyWordSetLow.getWordList().isEmpty()) {
                    Collections.sort(keyWordSetLow.getWordList());
                    list.add(keyWordSetLow);
                }
            }
        }
        return list;
    }

    /**
     *
     * @param tokens
     *            mapa de tokens
     * @return Retorna chave do token que mais aparece no texto
     */
    private String getMaxKey(Map<String, Integer> tokens) {
        Integer max = 0;
        String maxKey = null;
        for (String key : tokens.keySet()) {
            if (max < tokens.get(key)) {
                max = tokens.get(key);
                maxKey = key;
            }
        }
        return maxKey;
    }
}
