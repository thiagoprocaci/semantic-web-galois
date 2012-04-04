package com.semanticweb.framework.module.file.support.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.mockito.Mockito.*;
import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;
import com.semanticweb.framework.module.file.support.IFileNameGenerator;
import com.semanticweb.support.FileUtil;

public class PdfFileTransformerTest {
    private static final String FILE_NAME = "fileName.pdf";
    private PdfFileTransformer pdfFileTransformer;
    private IFileNameGenerator fileNameGenerator;
    private FileUtil fileUtil;

    @Before
    public void doBefore() {
        fileUtil = new FileUtil();
        pdfFileTransformer = new PdfFileTransformer();
        fileNameGenerator = mock(IFileNameGenerator.class);
        String folder = fileUtil.getAbsolutePath(FileUtil.FILE_FOLDER);
        pdfFileTransformer.setPdfFolder(folder);
        pdfFileTransformer.setFileNameGenerator(fileNameGenerator);
        when(fileNameGenerator.generateName(folder)).thenReturn(folder + FILE_NAME);
    }

    @Test
    public void testSplit() {
        String path = fileUtil.getAbsolutePath(FileUtil.CIBERCULTURA_FILE);
        File file = pdfFileTransformer.split(path, 3, 10);
        assertNotNull(file);
        assertTrue(file.exists());
        assertEquals(FILE_NAME, file.getName());
        PdfReader reader;
        try {
            reader = new PdfReader(file.getAbsolutePath());
            assertEquals(8, reader.getNumberOfPages());
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertNull(pdfFileTransformer.split("", 1, 2));
        assertNull(pdfFileTransformer.split(FileUtil.A_TXT_FILE, 1, 2));
    }

    @Test
    public void testMerge() {
        String path = fileUtil.getAbsolutePath(FileUtil.CIBERCULTURA_FILE);
        String path2 = fileUtil.getAbsolutePath(FileUtil.ONTO_WS_FILE);
        File file = pdfFileTransformer.merge(path, path2);
        assertNotNull(file);
        assertTrue(file.exists());
        assertEquals(FILE_NAME, file.getName());
        PdfReader reader;
        try {
            reader = new PdfReader(file.getAbsolutePath());
            assertEquals(70, reader.getNumberOfPages());
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertNull(pdfFileTransformer.merge("", ""));
        assertNull(pdfFileTransformer.merge(FileUtil.A_TXT_FILE, FileUtil.ONTO_WS_FILE));
        assertNull(pdfFileTransformer.merge(FileUtil.ONTO_WS_FILE, FileUtil.A_TXT_FILE));
    }
}
