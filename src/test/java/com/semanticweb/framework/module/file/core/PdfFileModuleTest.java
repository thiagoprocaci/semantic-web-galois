package com.semanticweb.framework.module.file.core;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.framework.module.file.support.IFileTransformer;
import com.semanticweb.framework.module.file.support.ITextFileExtractor;
import com.semanticweb.support.FileUtil;

public class PdfFileModuleTest {
    private PdfFileModule pdfFileModule;
    private IFileTransformer fileTransformer;
    private ITextFileExtractor textFileExtractor;
    private FileUtil fileUtil;

    @Before
    public void doBefore() {
        pdfFileModule = new PdfFileModule();
        fileTransformer = mock(IFileTransformer.class);
        textFileExtractor = mock(ITextFileExtractor.class);
        pdfFileModule.setFileTransformer(fileTransformer);
        pdfFileModule.setTextFileExtractor(textFileExtractor);
        fileUtil = new FileUtil();
    }

    @Test
    public void testSplit() {
        String path = fileUtil.getAbsolutePath(FileUtil.CIBERCULTURA_FILE);
        File file = new File(path);
        int startPage = 1;
        int endPage = 10;
        String filePath = "filePath";
        when(fileTransformer.split(filePath, startPage, endPage)).thenReturn(file);
        File file2 = pdfFileModule.split(filePath, startPage, endPage);
        assertEquals(file, file2);
        verify(fileTransformer).split(filePath, startPage, endPage);
    }

    @Test
    public void testMerge() {
        String path = fileUtil.getAbsolutePath(FileUtil.CIBERCULTURA_FILE);
        File file = new File(path);
        String filePath = "filePath";
        String filePath2 = "filePath2";
        when(fileTransformer.merge(filePath, filePath2)).thenReturn(file);
        File file2 = pdfFileModule.merge(filePath, filePath2);
        assertEquals(file, file2);
        verify(fileTransformer).merge(filePath, filePath2);
    }

    @Test
    public void testExtractTextFromFile() {
        String filePath = "filePath";
        when(textFileExtractor.extractTextFromFile(filePath )).thenReturn("text");
        String texto = pdfFileModule.extractTextFromFile(filePath);
        assertEquals("text", texto);
        verify(textFileExtractor).extractTextFromFile(filePath);
    }



}
