package com.semanticweb.framework.module.textmining.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.semanticweb.framework.module.textmining.ITextMiningModule;

/**
 *
 * Modulo de mineracao de texto
 */
public class TextMiningModule implements ITextMiningModule {
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
