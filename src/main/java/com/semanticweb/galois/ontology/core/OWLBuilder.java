package com.semanticweb.galois.ontology.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.semanticweb.galois.domain.Segment;
import com.semanticweb.galois.domain.SegmentRelation;
import com.semanticweb.galois.domain.enumeration.Relation;
import com.semanticweb.galois.ontology.IOntologyBuilder;
import com.semanticweb.galois.service.ISegmentRelationService;
import com.semanticweb.galois.service.ISegmentService;

/**
 * 
 * Construtor de ontologias OWL
 * 
 */
public class OWLBuilder implements IOntologyBuilder {
    private static final String SEG = "seg";
    private static final String ONTOLOGY_TEMPLATE = "/file/ontology/ontologyTemplate.xml";
    private static final String RELATION = "rel_YYY";
    private ISegmentService segmentService;
    private ISegmentRelationService segmentRelationService;

    public void setSegmentService(ISegmentService segmentService) {
        this.segmentService = segmentService;
    }

    public void setSegmentRelationService(ISegmentRelationService segmentRelationService) {
        this.segmentRelationService = segmentRelationService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buildOntology() {
        List<Segment> segmentList = segmentService.findAll();
        if (segmentList != null && !segmentList.isEmpty()) {
            String instances = createInstances(segmentList);
            List<SegmentRelation> segmentRelationList = segmentRelationService.findAll();
            instances = createRelationship(segmentRelationList, instances);
            instances = removeEmptyRelation(segmentList, instances);
            System.out.println(createOntology(instances));
        }
    }

    /**
     * 
     * @param segmentList lista de segmentos
     * @param instances String com as instancias
     * @return Retorna string sem os relacionamentos vazios usados durante a construcao do XML
     */
    private String removeEmptyRelation(List<Segment> segmentList, String instances) {
        for (Segment segment : segmentList) {
            instances = instances.replace(RELATION + segment.getId(), "");
        }
        return instances;
    }

    /**
     * 
     * @param segmentRelationList
     *            lista de relacionamentos
     * @param instances
     *            String com as instancias
     * @return Retorna string com os relacionamentos que ira compor o arquivo
     *         XMK com as notacoes OWL.
     */
    private String createRelationship(List<SegmentRelation> segmentRelationList, String instances) {
        String relationship = null;
        for (SegmentRelation segmentRelation : segmentRelationList) {
            relationship = getStringRelationship(Relation.getByValue(segmentRelation.getType()));
            relationship = relationship.replace("ZZZ", SEG + segmentRelation.getSegment2().getId());
            instances = instances.replace(RELATION + segmentRelation.getSegment1().getId(), relationship + RELATION + segmentRelation.getSegment1().getId());
            relationship = null;
        }
        return instances;
    }

    /**
     * 
     * @param relation
     *            relacao
     * @return Retorna tag string representado o XML que ira compor o
     *         relacionamento
     */
    private String getStringRelationship(Relation relation) {
        String segmentOpen = "\n <Segment rdf:ID=\"ZZZ\">  </Segment> \n";
        StringBuffer relationship = new StringBuffer();
        switch (relation) {
        case IS_BASE_FOR:
            relationship.append("<isBasisFor>");
            relationship.append(segmentOpen);
            relationship.append("</isBasisFor>");
            break;
        case IS_PART_OF:
            relationship.append("<isPartOf>");
            relationship.append(segmentOpen);
            relationship.append("</isPartOf>");
            break;
        case IS_SIMILAR_TO:
            relationship.append("<isSimilarTo>");
            relationship.append(segmentOpen);
            relationship.append("</isSimilarTo>");
            break;
        case COMPLEMENTS:
            relationship.append("<complements>");
            relationship.append(segmentOpen);
            relationship.append("</complements>");
            break;
        default:
            break;
        }
        return relationship.toString();
    }

    /**
     * 
     * @param segmentList
     *            lista com os segmentos
     * @return Retorna string com as instancias que ira compor o arquivo XML com
     *         as notacoes OWL
     */
    private String createInstances(List<Segment> segmentList) {
        StringBuilder builder = new StringBuilder();
        String segmentOpen = "<Segment rdf:ID=\"XXX\"> \n " + RELATION + "\n </Segment> \n";
        String aux = null;
        for (Segment segment : segmentList) {
            aux = segmentOpen.replace("XXX", SEG + segment.getId()).replace(RELATION, RELATION + segment.getId());
            builder.append(aux);
            aux = null;
        }
        return builder.toString();
    }

    /**
     * 
     * @param instances string com as instancias
     * @return Retorna ontologia com base nas instancias
     */
    private String createOntology(String instances) {
        try {
            String path = getClass().getResource(ONTOLOGY_TEMPLATE).getPath().toString();
            path = path.replace("file:/", "");
            path = path.replace("/", File.separator);
            StringBuffer contents = new StringBuffer();
            BufferedReader input = new BufferedReader(new FileReader(new File(path)));
            String line = null;
            while ((line = input.readLine()) != null) {
                contents.append(line + "\n");
            }
            input.close();
            String ontology = contents.toString();
            ontology = ontology.replace("<INSTANCES  />", instances);
            return ontology;
        } catch (FileNotFoundException e) {
            // TODO LOG
            e.printStackTrace();
        } catch (IOException e) {
            // TODO LOG
            e.printStackTrace();
        }
        return "";
    }
}
