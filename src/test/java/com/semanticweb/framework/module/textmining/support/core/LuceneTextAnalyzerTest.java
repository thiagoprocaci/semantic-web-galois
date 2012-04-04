package com.semanticweb.framework.module.textmining.support.core;

import static junit.framework.Assert.assertNotNull;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.file.support.core.PdfTextFileExtractor;
import com.semanticweb.support.FileUtil;

public class LuceneTextAnalyzerTest {
    private StopWordManager stopWordManager;
    private LuceneTextAnalyzer luceneTextAnalyzer;
    private PdfTextFileExtractor pdfTextFileExtractor;
    private FileUtil fileUtil;

    @Before
    public void doBefore() {
        stopWordManager = new StopWordManager();
        fileUtil = new FileUtil();
        stopWordManager.setStopWordsFilePath(fileUtil.getAbsolutePath("/file/textmining/stop_words_portugues.txt"));
        luceneTextAnalyzer = new LuceneTextAnalyzer();
        luceneTextAnalyzer.setStopWordManager(stopWordManager);
        pdfTextFileExtractor = new PdfTextFileExtractor();
    }

    @Test
    public void testTokenize() {
        String path = fileUtil.getAbsolutePath("/files/OntoWS.pdf");
        String text = pdfTextFileExtractor.extractTextFromFile(path);
        Map<String, Integer> tokens = luceneTextAnalyzer.tokenize(text);
        assertNotNull(tokens);
        
    }
}
