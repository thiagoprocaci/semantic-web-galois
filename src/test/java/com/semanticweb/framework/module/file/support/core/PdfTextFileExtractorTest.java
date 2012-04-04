package com.semanticweb.framework.module.file.support.core;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.support.FileUtil;

public class PdfTextFileExtractorTest {
    private PdfTextFileExtractor pdfTextFileExtractor;
    private FileUtil fileUtil;

    @Before
    public void doBefore() {
        pdfTextFileExtractor = new PdfTextFileExtractor();
        fileUtil = new FileUtil();
    }

    @Test
    public void testExtractTextFromFileError() {

        String path = fileUtil.getAbsolutePath(FileUtil.FILE_FOLDER);
        String text = pdfTextFileExtractor.extractTextFromFile(path);
        assertNull(text);
        text = pdfTextFileExtractor.extractTextFromFile("");
        assertNull(text);
        path = fileUtil.getAbsolutePath(FileUtil.NAO_EH_PDF_FILE);
        text = pdfTextFileExtractor.extractTextFromFile(path);
        assertNull(text);
    }

    @Test
    public void testExtractTextFromSmallFile() {
        String path = fileUtil.getAbsolutePath(FileUtil.CIBERCULTURA_FILE);
        String text = pdfTextFileExtractor.extractTextFromFile(path);
        assertNotNull(text);
        assertTrue(text.endsWith("Cortez, 1995."));
    }

    @Test
    public void testExtractTextFromMediumFile() {
        String path = fileUtil.getAbsolutePath(FileUtil.ONTO_WS_FILE);
        String text = pdfTextFileExtractor.extractTextFromFile(path);
        assertNotNull(text);
        assertTrue(text.endsWith("New York, NY. USA."));
    }
}
