package com.semanticweb.framework.module.file.core;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;
import com.semanticweb.support.FileUtil;

public class PdfFileModuleTest {
    private PdfFileModule pdfFileModule;

    @Before
    public void doBefore() {
        pdfFileModule = new PdfFileModule();
        pdfFileModule.setPdfFolder(FileUtil.getAbsolutePath("/files/"));
    }

    @Test
    public void testSplit() {
        String path = FileUtil.getAbsolutePath("/files/cibercultura.pdf");
        File file = pdfFileModule.split(path, 3, 10);
        assertNotNull(file);
        assertTrue(file.exists());
        PdfReader reader;
        try {
            reader = new PdfReader(file.getAbsolutePath());
            assertEquals(8, reader.getNumberOfPages());
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertNull(pdfFileModule.split("", 1, 2));
        assertNull(pdfFileModule.split("/files/a.txt", 1, 2));
    }

    @Test
    public void testMerge() {
        String path = FileUtil.getAbsolutePath("/files/cibercultura.pdf");
        String path2 = FileUtil.getAbsolutePath("/files/OntoWS.pdf");
        File file = pdfFileModule.merge(path, path2);
        assertNotNull(file);
        assertTrue(file.exists());
        PdfReader reader;
        try {
            reader = new PdfReader(file.getAbsolutePath());
            assertEquals(70, reader.getNumberOfPages());
        } catch (IOException e) {
            fail(e.getMessage());
        }
        assertNull(pdfFileModule.merge("", ""));
        assertNull(pdfFileModule.merge("/files/a.txt", "/files/OntoWS.pdf"));
        assertNull(pdfFileModule.merge("/files/OntoWS.pdf", "/files/a.txt"));
    }
}
