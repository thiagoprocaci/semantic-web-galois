package com.semanticweb.galois.service;

import java.util.List;

import com.semanticweb.galois.domain.Segment;

/**
 * 
 * Servicos de segmento
 *
 */
public interface ISegmentService {
    
    /**
     * 
     * @return Retorna todos os segmentos
     */
    List<Segment> findAll();
}
