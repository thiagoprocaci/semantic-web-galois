package com.semanticweb.framework.module.textmining.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class TextMiningModuleTest {
    private TextMiningModule textMiningModule;

    @Before
    public void doBefore() {
        textMiningModule = new TextMiningModule();
    }

    @Test
    public void testLoadStopWords() {
        Set<String> stopWords = textMiningModule.loadStopWords();
        assertNotNull(stopWords);
        assertEquals(208, stopWords.size());
        for (String string : stopWords) {
            assertNotNull(string);
        }
    }
}
