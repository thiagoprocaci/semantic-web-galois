package com.semanticweb.framework.module.textmining.model;

/**
 *
 * Classe que representa uma palavra chave de um texto
 *
 */
public class KeyWord implements Comparable<KeyWord>{
    private String word;
    private Integer occurenceNumber;

    public KeyWord(String word, Integer occurenceNumber) {
        super();
        this.word = word;
        this.occurenceNumber = occurenceNumber;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Integer getOccurenceNumber() {
        return occurenceNumber;
    }

    public void setOccurenceNumber(Integer occurenceNumber) {
        this.occurenceNumber = occurenceNumber;
    }

    @Override
    public int compareTo(KeyWord arg0) {
        // ordenacao na ordem inversa
        return occurenceNumber.compareTo(arg0.occurenceNumber) * -1;
    }
}
