package com.semanticweb.galois.ontology.core;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static junit.framework.Assert.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.semanticweb.galois.domain.Segment;
import com.semanticweb.galois.domain.SegmentRelation;
import com.semanticweb.galois.domain.enumeration.Relation;
import com.semanticweb.galois.service.ISegmentRelationService;
import com.semanticweb.galois.service.ISegmentService;

public class OWLBuilderTest {
    private OWLBuilder owlBuilder;
    private ISegmentService segmentService;
    private ISegmentRelationService segmentRelationService;
    
    @Before
    public void doBefore() {
        owlBuilder = new OWLBuilder();
        segmentRelationService = mock(ISegmentRelationService.class);
        segmentService = mock(ISegmentService.class);
        owlBuilder.setSegmentRelationService(segmentRelationService);
        owlBuilder.setSegmentService(segmentService);
    }
    
    @Test
    public void testBuildOntology(){
        Segment segment = null;
        List<Segment> list = new ArrayList<Segment>();
        int i = 1;
        while (i <= 5) {
            segment = new Segment();
            segment.setId(i);
            list.add(segment);
            i++;
        }
        when(segmentService.findAll()).thenReturn(list);
       
        // mock dos relacionamentos
        SegmentRelation segmentRelation =  new SegmentRelation();
        segmentRelation.setSegment1(list.get(0));
        segmentRelation.setSegment2(list.get(1));
        segmentRelation.setType(Relation.COMPLEMENTS.value());
        
        SegmentRelation segmentRelation2 =  new SegmentRelation();
        segmentRelation2.setSegment1(list.get(0));
        segmentRelation2.setSegment2(list.get(2));
        segmentRelation2.setType(Relation.IS_BASE_FOR.value());
        
        SegmentRelation segmentRelation3 =  new SegmentRelation();
        segmentRelation3.setSegment1(list.get(0));
        segmentRelation3.setSegment2(list.get(3));
        segmentRelation3.setType(Relation.IS_PART_OF.value());
        
        SegmentRelation segmentRelation4 =  new SegmentRelation();
        segmentRelation4.setSegment1(list.get(0));
        segmentRelation4.setSegment2(list.get(4));
        segmentRelation4.setType(Relation.IS_SIMILAR_TO.value());
        
        
        SegmentRelation segmentRelation5 =  new SegmentRelation();
        segmentRelation5.setSegment1(list.get(2));
        segmentRelation5.setSegment2(list.get(3));
        segmentRelation5.setType(Relation.COMPLEMENTS.value());
        
        SegmentRelation segmentRelation6 =  new SegmentRelation();
        segmentRelation6.setSegment1(list.get(1));
        segmentRelation6.setSegment2(list.get(2));
        segmentRelation6.setType(Relation.IS_PART_OF.value());
        
        SegmentRelation segmentRelation7 =  new SegmentRelation();
        segmentRelation7.setSegment1(list.get(1));
        segmentRelation7.setSegment2(list.get(3));
        segmentRelation7.setType(Relation.IS_PART_OF.value());
        
        
        
        List<SegmentRelation> relationList = new ArrayList<SegmentRelation>();
        relationList.add(segmentRelation);
        relationList.add(segmentRelation2);
        relationList.add(segmentRelation3);
        relationList.add(segmentRelation4);
        relationList.add(segmentRelation5);
        relationList.add(segmentRelation6);
        relationList.add(segmentRelation7);
        
        when(segmentRelationService.findAll()).thenReturn(relationList);
        
        File f = owlBuilder.buildOntology();
        assertNotNull(f);
        assertTrue(f.exists());
        //TODO terminar esse teste
    }
}
