package com.semanticweb.framework.module.file.core;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.itextpdf.text.pdf.PdfReader;
import com.semanticweb.framework.module.file.IFileModule;

public class PdfFileModuleTest {
    
    private IFileModule pdfFileModule;
    
    @Before
    public void doBefore() {
        pdfFileModule = new PdfFileModule();
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
}
