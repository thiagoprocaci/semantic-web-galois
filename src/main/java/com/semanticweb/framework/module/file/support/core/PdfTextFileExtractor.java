package com.semanticweb.framework.module.file.support.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.semanticweb.framework.module.file.support.ITextFileExtractor;

/**
*
* Extrator de texto de um arquivo pdf
*/
public class PdfTextFileExtractor implements ITextFileExtractor {

    /**
     * {@inheritDoc}
     */
    @Override
    public String extractTextFromFile(String filePath) {
        try {
            FileInputStream fi = new FileInputStream(new File(filePath));
            PDFParser parser = new PDFParser(fi);
            parser.parse();
            COSDocument cd = parser.getDocument();
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(new PDDocument(cd));
            return text.trim();
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }
}
