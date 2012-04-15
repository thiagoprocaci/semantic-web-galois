package com.semanticweb.galois.ontology;

import java.io.File;

/**
 *  Interface responsavel por realizar inferencias logicas
 *
 */
public interface IReasoner {
    
    /**
     * Realiza uma inferencia dado um arquivo rdf com dados.  
     * @param rdf arquivos com os dados
     */
    void makeInference(File rdf);
}
