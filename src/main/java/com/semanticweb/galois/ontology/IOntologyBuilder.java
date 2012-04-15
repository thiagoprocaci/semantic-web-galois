package com.semanticweb.galois.ontology;


/**
 * 
 * Construtor de ontologias
 *
 */
public interface IOntologyBuilder {

    /**
     * Constroi ontologia baseada na lista de segmentos
     * @param segmentList lista de segmentos
     */
    void buildOntology();
}
