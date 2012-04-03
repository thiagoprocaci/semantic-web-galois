package com.semanticweb.framework.module.textmining.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import com.semanticweb.framework.module.textmining.ITextMiningModule;

/**
 *
 * Modulo de mineracao de texto
 */
public class TextMiningModule implements ITextMiningModule {
    // TODO criar componente para cada funcao do modulo
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

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<String> loadStopWords() {
        String path = getClass().getResource("/file/textmining/stop_words_portugues.txt").getPath().toString();
        path = path.replace("file:/", "");
        path = path.replace("/", File.separator);
        Set<String> stopWords = new HashSet<String>();
        try {
            Scanner scanner = new Scanner(new File(path));
            String string = null;
            while (scanner.hasNext()) {
                string = scanner.nextLine();
                if (string != null) {
                    string = string.trim();
                    if (string.length() > 0) {
                        if (stopWords.contains(string)) {
                            System.out.println(string);
                        }
                        stopWords.add(string.trim());
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("error: " + e.getMessage());
        }
        return stopWords;
    }
}
