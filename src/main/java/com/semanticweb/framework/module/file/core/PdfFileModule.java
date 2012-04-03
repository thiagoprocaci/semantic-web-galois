package com.semanticweb.framework.module.file.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

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
    private static final String PDF = ".pdf";
    // pasta onde os arquivos pdfs serao gravados
    private String pdfFolder;

    public void setPdfFolder(String pdfFolder) {
        this.pdfFolder = pdfFolder;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File split(String filePath, int startPage, int endPage) {
        try {
            PdfReader reader = new PdfReader(filePath);
            String outFile = generatePdfName();
            Document pdf = new Document(reader.getPageSizeWithRotation(1));
            PdfCopy pdfCopy = new PdfCopy(pdf, new FileOutputStream(outFile));
            pdf.open();
            addPagesToPdfCopy(startPage, endPage, pdfCopy, reader);
            pdf.close();
            pdfCopy.close();
            return new File(outFile);
        } catch (BadPdfFormatException e) {
            // TODO colocar log
            System.out.println("error: " + e.getMessage());
        } catch (DocumentException e) {
            // TODO colocar log
            System.out.println("error: " + e.getMessage());
        } catch (IOException e) {
            // TODO colocar log
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File merge(String filePath1, String filePath2) {
        try {
            PdfReader reader1 = new PdfReader(filePath1);
            PdfReader reader2 = new PdfReader(filePath2);
            String outFile = generatePdfName();
            Document pdf = new Document(reader1.getPageSizeWithRotation(1));
            PdfCopy pdfCopy = new PdfCopy(pdf, new FileOutputStream(outFile));
            pdf.open();
            addPagesToPdfCopy(1, reader1.getNumberOfPages(), pdfCopy, reader1);
            addPagesToPdfCopy(1, reader2.getNumberOfPages(), pdfCopy, reader2);
            pdf.close();
            pdfCopy.close();
            return new File(outFile);
        } catch (BadPdfFormatException e) {
            // TODO colocar log
            System.out.println("error: " + e.getMessage());
        } catch (DocumentException e) {
            // TODO colocar log
            System.out.println("error: " + e.getMessage());
        } catch (IOException e) {
            // TODO colocar log
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }
    
    /**
     * 
     * @return Retorna novo nome do pdf
     */
    private String generatePdfName() {
        String name = pdfFolder + UUID.randomUUID().toString() + PDF; 
        File file = new File(name);
        while(file.exists()) {
            name = pdfFolder + UUID.randomUUID().toString();
        }              
        return name;
    }

    /**
     * Metodo auxiliar para adicionar paginas a um pdf
     * @param startPage pagina inicial
     * @param endPage pagina final
     * @param pdfCopy novo pdf gerado
     * @param reader leitor de um pdf
     * @throws BadPdfFormatException 
     * @throws IOException
     */
    private void addPagesToPdfCopy(int startPage, int endPage, PdfCopy pdfCopy, PdfReader reader) throws BadPdfFormatException, IOException {
        for (int j = startPage; j <= endPage; j++) {
            pdfCopy.addPage(pdfCopy.getImportedPage(reader, j));
        }
    }
}
