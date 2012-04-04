package com.semanticweb.framework.module.textmining.support.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.support.FileUtil;

public class StopWordManagerTest {
    
    private StopWordManager stopWordManager;
    
    @Before
    public void doBefore() {
        stopWordManager = new StopWordManager();
        FileUtil fileUtil = new FileUtil();
        stopWordManager.setStopWordsFilePath(fileUtil.getAbsolutePath("/file/textmining/stop_words_portugues.txt"));
    }
    
    @Test
    public void testLoadStopWords() {
        Set<String> stopWords = stopWordManager.loadStopWords();
        assertNotNull(stopWords);
        assertEquals(208, stopWords.size());
        for (String string : stopWords) {
            assertNotNull(string);
        }
    }
    
    @Test
    public void testsStopWord() {
        assertTrue(stopWordManager.isStopWord("de"));
        assertTrue(stopWordManager.isStopWord("para"));
        assertTrue(stopWordManager.isStopWord("a"));
        assertFalse(stopWordManager.isStopWord("ontologia"));
    }
}
