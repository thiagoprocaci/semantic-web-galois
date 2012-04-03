package com.semanticweb.framework.module.file.support.core;

import static junit.framework.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PdfFileNameGeneratorTest {
    private PdfFileNameGenerator pdfFileNameGenerator;

    @Before
    public void doBefore() {
        pdfFileNameGenerator = new PdfFileNameGenerator();
    }

    @Test
    public void testGenerateName() {
        String folder = "teste/";
        String fileName = pdfFileNameGenerator.generateName(folder);
        assertNotNull(fileName);
        assertTrue(fileName.startsWith(folder));
        assertTrue(fileName.endsWith(".pdf"));
        try {
            pdfFileNameGenerator.generateName(null);
            fail("this line should not be executed");
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
    }
}
