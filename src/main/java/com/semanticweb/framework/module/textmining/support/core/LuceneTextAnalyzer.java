package com.semanticweb.framework.module.textmining.support.core;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.br.BrazilianAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
    public static final String STANDARD_ANALYZER = "standard";
    public static final String BRAZILIAN_ANALYZER = "brasilian";
    private List<IStopWordManager> stopWordManagerList;
    private String analizerType;

    public void setAnalizerType(String analizerType) {
        this.analizerType = analizerType;
    }

    public void setStopWordManagerList(List<IStopWordManager> stopWordManagerList) {
        this.stopWordManagerList = stopWordManagerList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, Integer> tokenize(String text) {
        Map<String, Integer> result = new HashMap<String, Integer>();
        if (text != null) {
            try {
                // carrega todas as stop words
                Set<String> stopWords = new HashSet<String>();
                for (IStopWordManager stopWordManager : stopWordManagerList) {
                    stopWords.addAll(stopWordManager.loadStopWords());
                }
                // inicializa o analisador
                Analyzer analyzer = null;
                if (analizerType != null && analizerType.equals(BRAZILIAN_ANALYZER)) {
                    analyzer = new BrazilianAnalyzer(Version.LUCENE_35, stopWords);
                } else {
                    analyzer = new StandardAnalyzer(Version.LUCENE_35, stopWords);
                }
                TokenStream stream = analyzer.tokenStream(null, new StringReader(text));
                String key = null;
                while (stream.incrementToken()) {
                    key = stream.getAttribute(CharTermAttribute.class).toString();
                    if (key != null && key.length() > 2 && !key.contains(":") && !key.contains("_")) {
                        if (result.containsKey(key)) {
                            result.put(key, result.get(key) + 1);
                        } else {
                            result.put(key, 1);
                        }
                    }
                }
            } catch (IOException e) {
                // not thrown b/c we're using a string reader...
                throw new RuntimeException(e);
            }
        }
        return result;
    }


}
