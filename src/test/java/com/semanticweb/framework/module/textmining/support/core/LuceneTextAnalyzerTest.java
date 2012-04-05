package com.semanticweb.framework.module.textmining.support.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.file.support.core.PdfTextFileExtractor;
import com.semanticweb.framework.module.textmining.support.IStopWordManager;
import com.semanticweb.support.FileUtil;

public class LuceneTextAnalyzerTest {
    private StopWordManager stopWordPortugueseManager;
    private StopWordManager stopWordEnglishManager;
    private LuceneTextAnalyzer luceneTextAnalyzer;
    private PdfTextFileExtractor pdfTextFileExtractor;
    private FileUtil fileUtil;

    @Before
    public void doBefore() {
        fileUtil = new FileUtil();
        stopWordPortugueseManager = new StopWordManager();
        stopWordPortugueseManager.setStopWordsFilePath(fileUtil.getAbsolutePath(FileUtil.STOP_WORDS_FILE_PORTUGUES));
        stopWordEnglishManager = new StopWordManager();
        stopWordEnglishManager.setStopWordsFilePath(fileUtil.getAbsolutePath(FileUtil.STOP_WORDS_FILE_INGLES));
        List<IStopWordManager> list = new ArrayList<IStopWordManager>();
        list.add(stopWordEnglishManager);
        list.add(stopWordPortugueseManager);
        luceneTextAnalyzer = new LuceneTextAnalyzer();
        luceneTextAnalyzer.setStopWordManagerList(list);
        pdfTextFileExtractor = new PdfTextFileExtractor();
    }

    @Test
    public void testTokenizeStandard() {
        String path = fileUtil.getAbsolutePath(FileUtil.CIBERCULTURA_FILE);
        String text = pdfTextFileExtractor.extractTextFromFile(path);
        luceneTextAnalyzer.setAnalizerType(LuceneTextAnalyzer.STANDARD_ANALYZER);
        Map<String, Integer> tokens = luceneTextAnalyzer.tokenize(text);
        assertNotNull(tokens);
        for (String key : tokens.keySet()) {
            assertNotNull(key);
            assertNotNull(tokens.get(key));
            assertTrue(key.length() > 2);
            /*
             * if(tokens.get(key) > 30) { System.out.println(key + " - " +
             * tokens.get(key)); }
             */
        }
    }

    @Test
    public void testTokenizeBrazilian() {
        String path = fileUtil.getAbsolutePath(FileUtil.CIBERCULTURA_FILE);
        String text = pdfTextFileExtractor.extractTextFromFile(path);
        luceneTextAnalyzer.setAnalizerType(LuceneTextAnalyzer.BRAZILIAN_ANALYZER);
        Map<String, Integer> tokens = luceneTextAnalyzer.tokenize(text);
        assertNotNull(tokens);
        for (String key : tokens.keySet()) {
            assertNotNull(key);
            assertNotNull(tokens.get(key));
            assertTrue(key.length() > 2);
            /*
             * if(tokens.get(key) > 30) { System.out.println(key + " - " +
             * tokens.get(key)); }
             */
        }
    }

    @Test
    public void testTokenizeUsingMock() {
        String text = generateString(" high ", 100);
        text += generateString(" medium ", 50);
        text += generateString(" low ", 30);
        text += generateString(" sa ", 10);
        text += generateString(" stopword stop ", 40);

        Set<String> stopWordList = new HashSet<String>();
        stopWordList.add("stopword");
        stopWordList.add("stop");

        IStopWordManager stopWordManager = mock(IStopWordManager.class);
        when(stopWordManager.loadStopWords()).thenReturn(stopWordList);
        List<IStopWordManager> list = new ArrayList<IStopWordManager>();
        list.add(stopWordManager);

        luceneTextAnalyzer.setStopWordManagerList(list);
        luceneTextAnalyzer.setAnalizerType(LuceneTextAnalyzer.STANDARD_ANALYZER);
        Map<String, Integer> tokens = luceneTextAnalyzer.tokenize(text);
        assertNotNull(tokens);
        assertEquals(3, tokens.size());
        assertNotNull(tokens.get("high"));
        assertNotNull(tokens.get("medium"));
        assertNotNull(tokens.get("low"));
        assertEquals(100, tokens.get("high").intValue());
        assertEquals(50, tokens.get("medium").intValue());
        assertEquals(30, tokens.get("low").intValue());
    }

    private String generateString(String string, int times) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            builder.append(string);
        }
        return builder.toString();
    }
}
