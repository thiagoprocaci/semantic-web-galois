package com.semanticweb.framework.module.file.core;

import static org.mockito.Mockito.*;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.*;

import com.semanticweb.framework.module.file.support.IFileTransformer;
import com.semanticweb.support.FileUtil;

public class PdfFileModuleTest {
    private PdfFileModule pdfFileModule;
    private IFileTransformer fileTransformer;

    @Before
    public void doBefore() {
        pdfFileModule = new PdfFileModule();
        fileTransformer = mock(IFileTransformer.class);
        pdfFileModule.setFileTransformer(fileTransformer);
    }

    @Test
    public void testSplit() {
        String path = FileUtil.getAbsolutePath("/files/cibercultura.pdf");
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
        String path = FileUtil.getAbsolutePath("/files/cibercultura.pdf");
        File file = new File(path);
        String filePath = "filePath";
        String filePath2 = "filePath2";
        when(fileTransformer.merge(filePath, filePath2)).thenReturn(file);
        File file2 = pdfFileModule.merge(filePath, filePath2);
        assertEquals(file, file2);
        verify(fileTransformer).merge(filePath, filePath2);
    }
}
