package com.semanticweb.framework.module.file.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.semanticweb.framework.module.file.IFileModule;

/**
 * 
 * Modulo manipulador de arquivos pdf
 * 
 */
public class PdfFileModule implements IFileModule {
    /**
     * {@inheritDoc}
     */
    @Override
    public File split(String filePath, int startPage, int endPage) {
        try {
            PdfReader reader = new PdfReader(filePath);
            String outFile = filePath.substring(0, filePath.indexOf(".pdf")) + "-" + ".pdf";
            
            Document pdf = new Document(reader.getPageSizeWithRotation(1));
            
            PdfCopy pdfCopy = new PdfCopy(pdf, new FileOutputStream(outFile));
            pdf.open();
            for (int j = startPage; j <= endPage; j++) {
                pdfCopy.addPage(pdfCopy.getImportedPage(reader, j));
            }
            
            pdf.close();
            pdfCopy.close();
            return new File(outFile);
        } catch (BadPdfFormatException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File merge(String filePath1, String filepath2) {
        return null;
    }
}
