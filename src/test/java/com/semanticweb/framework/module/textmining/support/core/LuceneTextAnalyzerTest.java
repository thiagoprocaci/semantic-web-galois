package com.semanticweb.framework.module.textmining.support.core;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        }
    }
}
