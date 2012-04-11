package com.semanticweb.framework.module.file.support.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.semanticweb.framework.module.file.support.IFileNameGenerator;
import com.semanticweb.framework.module.file.support.IFileTransformer;

/**
 * Tranformador de arquivos
 */
public class PdfFileTransformer implements IFileTransformer {
    private static final Logger LOGGER = LoggerFactory.getLogger(PdfFileTransformer.class);
    private IFileNameGenerator fileNameGenerator;
    // pasta onde os arquivos pdfs serao gravados
    private String pdfFolder;

    public void setPdfFolder(String pdfFolder) {
        this.pdfFolder = pdfFolder;
    }

    public void setFileNameGenerator(IFileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File split(String filePath, int startPage, int endPage) {
        try {
            PdfReader reader = new PdfReader(filePath);
            String outFile = fileNameGenerator.generateName(pdfFolder);
            Document pdf = new Document(reader.getPageSizeWithRotation(1));
            PdfCopy pdfCopy = new PdfCopy(pdf, new FileOutputStream(outFile));
            pdf.open();
            addPagesToPdfCopy(startPage, endPage, pdfCopy, reader);
            pdf.close();
            pdfCopy.close();
            return new File(outFile);
        } catch (BadPdfFormatException e) {
            LOGGER.error("error: " + e.getMessage());            
        } catch (DocumentException e) {
            LOGGER.error("error: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("error: " + e.getMessage());
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
            String outFile = fileNameGenerator.generateName(pdfFolder);
            Document pdf = new Document(reader1.getPageSizeWithRotation(1));
            PdfCopy pdfCopy = new PdfCopy(pdf, new FileOutputStream(outFile));
            pdf.open();
            addPagesToPdfCopy(1, reader1.getNumberOfPages(), pdfCopy, reader1);
            addPagesToPdfCopy(1, reader2.getNumberOfPages(), pdfCopy, reader2);
            pdf.close();
            pdfCopy.close();
            return new File(outFile);
        } catch (BadPdfFormatException e) {
            LOGGER.error("error: " + e.getMessage());
        } catch (DocumentException e) {
            LOGGER.error("error: " + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("error: " + e.getMessage());
        }
        return null;
    }

    /**
     * Metodo auxiliar para adicionar paginas a um pdf
     *
     * @param startPage
     *            pagina inicial
     * @param endPage
     *            pagina final
     * @param pdfCopy
     *            novo pdf gerado
     * @param reader
     *            leitor de um pdf
     * @throws BadPdfFormatException
     * @throws IOException
     */
    private void addPagesToPdfCopy(int startPage, int endPage, PdfCopy pdfCopy, PdfReader reader) throws BadPdfFormatException, IOException {
        for (int j = startPage; j <= endPage; j++) {
            pdfCopy.addPage(pdfCopy.getImportedPage(reader, j));
        }
    }
}
