package com.semanticweb.galois.ontology;

import java.io.File;


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
    File buildOntology();
}
