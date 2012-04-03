package com.semanticweb.framework.module.textmining.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.support.FileUtil;

public class TextMiningModuleTest {
    private TextMiningModule textMiningModule;

    @Before
    public void doBefore() {
        textMiningModule = new TextMiningModule();
    }

    @Test
    public void testExtractTextFromFileError() {
        String path = FileUtil.getAbsolutePath("/files/");
        String text = textMiningModule.extractTextFromFile(path);
        assertNull(text);
        text = textMiningModule.extractTextFromFile("");
        assertNull(text);
        path = FileUtil.getAbsolutePath("/files/naoEhPdf.pdf");
        text = textMiningModule.extractTextFromFile(path);
        assertNull(text);
    }

    @Test
    public void testExtractTextFromSmallFile() {
        String path = FileUtil.getAbsolutePath("/files/cibercultura.pdf");
        String text = textMiningModule.extractTextFromFile(path);
        assertNotNull(text);
        assertTrue(text.endsWith("Cortez, 1995."));
    }

    @Test
    public void testExtractTextFromMediumFile() {
        String path = FileUtil.getAbsolutePath("/files/OntoWS.pdf");
        String text = textMiningModule.extractTextFromFile(path);
        assertNotNull(text);
        assertTrue(text.endsWith("New York, NY. USA."));
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
