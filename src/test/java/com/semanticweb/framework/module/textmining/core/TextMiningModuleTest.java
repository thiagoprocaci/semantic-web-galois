package com.semanticweb.framework.module.textmining.core;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.textmining.support.IStopWordManager;
public class TextMiningModuleTest {
    private TextMiningModule textMiningModule;
    private IStopWordManager stopWordManager;

    @Before
    public void doBefore() {
        textMiningModule = new TextMiningModule();
        stopWordManager = mock(IStopWordManager.class);
        textMiningModule.setStopWordManager(stopWordManager);
    }

    @Test
    public void testLoadStopWords() {
        Set<String> stopWords = new HashSet<String>();
        stopWords.add("teste");
        when(stopWordManager.loadStopWords()).thenReturn(stopWords);
        assertEquals(stopWords, textMiningModule.loadStopWords());
        verify(stopWordManager).loadStopWords();
    }
}
