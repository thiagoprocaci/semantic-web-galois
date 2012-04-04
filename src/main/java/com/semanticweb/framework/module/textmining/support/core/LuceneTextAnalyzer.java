package com.semanticweb.framework.module.textmining.support.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

import com.semanticweb.framework.module.textmining.support.IStopWordManager;
import com.semanticweb.framework.module.textmining.support.ITextAnalyzer;

/**
 * 
 * Analizador de texto - implementacao lucene
 * 
 */
public class LuceneTextAnalyzer implements ITextAnalyzer {
    private IStopWordManager stopWordManager;

    public void setStopWordManager(IStopWordManager stopWordManager) {
        this.stopWordManager = stopWordManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> tokenize(String text) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        try {
            Analyzer analyzer = new BrazilianAnalyzer(Version.LUCENE_35, stopWordManager.loadStopWords());
            TokenStream stream = analyzer.tokenStream(null, new StringReader(text));
            String key = null;
            while (stream.incrementToken()) {
                key = stream.getAttribute(CharTermAttribute.class).toString();
                if (result.containsKey(key)) {
                    result.put(key, result.get(key) + 1);
                } else {
                    result.put(key, 1);
                }
            }
        } catch (IOException e) {
            // not thrown b/c we're using a string reader...
            throw new RuntimeException(e);
        }
        return result;
    }
}
