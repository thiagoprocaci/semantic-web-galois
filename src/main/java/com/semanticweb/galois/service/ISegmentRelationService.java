package com.semanticweb.galois.service;

import java.util.List;

import com.semanticweb.galois.domain.SegmentRelation;

/**
 * 
 * Servicos das relacoes entre os segmentos
 *
 */
public interface ISegmentRelationService {
    
    /**
     * 
     * @return Retorna todos
     */
    List<SegmentRelation> findAll();
}
