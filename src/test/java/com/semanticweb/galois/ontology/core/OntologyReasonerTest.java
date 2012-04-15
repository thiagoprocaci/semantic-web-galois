package com.semanticweb.galois.ontology.core;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.support.FileUtil;

public class OntologyReasonerTest {
    private OntologyReasoner ontologyReasoner;
    private FileUtil fileUtil;
    
    
    @Before
    public void doBefore() {
        ontologyReasoner = new OntologyReasoner();
        fileUtil = new FileUtil();
    }
    
    @Test
    public void testMakeInference() {
        String path = fileUtil.getAbsolutePath(FileUtil.TEST_ONTOLOGY);
        File rdf = new File(path);
        ontologyReasoner.makeInference(rdf);
    }
}
