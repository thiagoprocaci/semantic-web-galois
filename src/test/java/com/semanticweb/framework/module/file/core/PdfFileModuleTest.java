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

public class PdfFileModuleTest {
    private PdfFileModule pdfFileModule;

    @Before
    public void doBefore() {
        pdfFileModule = new PdfFileModule();
        String path = getClass().getResource("/files/").getPath().toString();    
        path = path.replace("file:/", "");
        path = path.replace("/", File.separator);
        pdfFileModule.setPdfFolder(path);
    }

    @Test
    public void testSplit() {
        String path = getClass().getResource("/files/cibercultura.pdf").getPath().toString();
        path = path.replace("file:/", "");
        path = path.replace("/", File.separator);
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
        String path = getClass().getResource("/files/cibercultura.pdf").getPath().toString();
        path = path.replace("file:/", "");
        path = path.replace("/", File.separator);
        String path2 = getClass().getResource("/files/OntoWS.pdf").getPath().toString();
        path2 = path2.replace("file:/", "");
        path2 = path2.replace("/", File.separator);
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
