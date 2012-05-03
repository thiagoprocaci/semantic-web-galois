package com.semanticweb.galois.ontology.core;

import java.io.File;
/*
 import java.io.FileInputStream;
 import java.io.FileNotFoundException;
 import java.io.InputStream;*/

/*import com.hp.hpl.jena.ontology.Individual;
 import com.hp.hpl.jena.ontology.OntClass;
 import com.hp.hpl.jena.ontology.OntModel;
 import com.hp.hpl.jena.ontology.OntModelSpec;
 import com.hp.hpl.jena.rdf.model.ModelFactory;
 import com.hp.hpl.jena.rdf.model.Resource;
 import com.hp.hpl.jena.util.iterator.ExtendedIterator;*/
import com.semanticweb.galois.ontology.IReasoner;

/**
 * Classe responsavel por realizar inferencias logicas em ontologias owl
 * 
 */
public class OntologyReasoner implements IReasoner {
    @Override
    public void makeInference(File rdf) {
        /*
         * try { // create the base model OntModel base =
         * ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM); InputStream
         * in = new FileInputStream(rdf); base.read(in, null);
         * 
         * // create the reasoning model using the base OntModel inf =
         * ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF,
         * base);
         * 
         * // create a dummy paper for this example OntClass paper =
         * base.getOntClass("Segment"); Individual p1 =
         * base.createIndividual("seg1", paper );
         * 
         * // list the asserted types for (ExtendedIterator<Resource> i =
         * p1.listRDFTypes(false); i.hasNext(); ) { System.out.println(
         * p1.getURI() + " is asserted in class " + i.next() ); }
         * 
         * // list the inferred types p1 = inf.getIndividual( "seg1" ); for
         * (ExtendedIterator<Resource> i = p1.listRDFTypes(false); i.hasNext();
         * ) { System.out.println( p1.getURI() + " is inferred to be in class "
         * + i.next() ); }
         * 
         * 
         * } catch (FileNotFoundException e) { // TODO log e.printStackTrace();
         * }
         */
    }
}
