package com.semanticweb.framework.module.textmining.support.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.textmining.model.KeyWordRelevance;
import com.semanticweb.framework.module.textmining.model.KeyWordSet;
import com.semanticweb.framework.module.textmining.support.ITextAnalyzer;

public class MetadataAdvisorTest {
    private MetadataAdvisor metadataAdvisor;
    private ITextAnalyzer textAnalyzer;

    @Before
    public void doBefore() {
        metadataAdvisor = new MetadataAdvisor();
        textAnalyzer = mock(ITextAnalyzer.class);
        metadataAdvisor.setTextAnalyzer(textAnalyzer);
    }

    @Test
    public void testSuggestKeyWordsDefault() {
        String text = "text";
        Map<String, Integer> tokens = new HashMap<String, Integer>();
        tokens.put("high", 100);
        tokens.put("high2", 200);
        tokens.put("medium", 50);
        tokens.put("low", 30);
        tokens.put("none", 10);

        when(textAnalyzer.tokenize(text)).thenReturn(tokens);

        List<KeyWordSet> list = metadataAdvisor.suggestKeyWords(text);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));
        assertNotNull(list.get(2));
        assertEquals(KeyWordRelevance.HIGH, list.get(0).getRelevance());
        assertEquals(KeyWordRelevance.MEDIUM, list.get(1).getRelevance());
        assertEquals(KeyWordRelevance.LOW, list.get(2).getRelevance());
        assertNotNull(list.get(0).getWordList());
        assertNotNull(list.get(1).getWordList());
        assertNotNull(list.get(2).getWordList());
        assertEquals(2, list.get(0).getWordList().size());
        assertEquals(1, list.get(1).getWordList().size());
        assertEquals(1, list.get(2).getWordList().size());
    }

    @Test
    public void testSuggestKeyWords() {
        String text = "text";
        Map<String, Integer> tokens = new HashMap<String, Integer>();
        tokens.put("high", 10);
        tokens.put("high2", 12);
        tokens.put("medium", 5);
        tokens.put("low", 3);
        tokens.put("none", 1);

        when(textAnalyzer.tokenize(text)).thenReturn(tokens);

        List<KeyWordSet> list = metadataAdvisor.suggestKeyWords(text);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));
        assertNotNull(list.get(2));
        assertEquals(KeyWordRelevance.HIGH, list.get(0).getRelevance());
        assertEquals(KeyWordRelevance.MEDIUM, list.get(1).getRelevance());
        assertEquals(KeyWordRelevance.LOW, list.get(2).getRelevance());
        assertNotNull(list.get(0).getWordList());
        assertNotNull(list.get(1).getWordList());
        assertNotNull(list.get(2).getWordList());
        assertEquals(2, list.get(0).getWordList().size());
        assertEquals(1, list.get(1).getWordList().size());
        assertEquals(1, list.get(2).getWordList().size());
    }

    @Test
    public void testSuggestKeyWordsNoLowKeyWord() {
        String text = "text";
        Map<String, Integer> tokens = new HashMap<String, Integer>();
        tokens.put("high", 10);
        tokens.put("high2", 12);
        tokens.put("high3", 11);
        tokens.put("medium", 5);
        tokens.put("medium2", 4);
        tokens.put("none", 1);

        when(textAnalyzer.tokenize(text)).thenReturn(tokens);

        List<KeyWordSet> list = metadataAdvisor.suggestKeyWords(text);
        assertNotNull(list);
        assertEquals(2, list.size());
        assertNotNull(list.get(0));
        assertNotNull(list.get(1));

        assertEquals(KeyWordRelevance.HIGH, list.get(0).getRelevance());
        assertEquals(KeyWordRelevance.MEDIUM, list.get(1).getRelevance());


        assertNotNull(list.get(0).getWordList());
        assertNotNull(list.get(1).getWordList());

        assertEquals(3, list.get(0).getWordList().size());
        assertEquals(2, list.get(1).getWordList().size());

    }

    @Test
    public void testSuggestKeyWordsEmptyTokens() {
        String text = "text";
        Map<String, Integer> tokens = new HashMap<String, Integer>();


        when(textAnalyzer.tokenize(text)).thenReturn(tokens);

        List<KeyWordSet> list = metadataAdvisor.suggestKeyWords(text);
        assertNotNull(list);
        assertEquals(0, list.size());

        when(textAnalyzer.tokenize(text)).thenReturn(tokens);

        list = metadataAdvisor.suggestKeyWords(null);
        assertNotNull(list);
        assertEquals(0, list.size());

    }
}
