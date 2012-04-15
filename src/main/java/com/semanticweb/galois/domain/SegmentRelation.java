package com.semanticweb.galois.domain;

import com.semanticweb.framework.entity.CoreEntity;

/**
 * 
 * Entidade que representa a relacao entre dois segmentos
 *
 */
public class SegmentRelation extends CoreEntity {
    private static final long serialVersionUID = -7212806914137171505L;
    private Integer id;
    private Segment segment1;
    private Segment segment2;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Segment getSegment1() {
        return segment1;
    }

    public void setSegment1(Segment segment1) {
        this.segment1 = segment1;
    }

    public Segment getSegment2() {
        return segment2;
    }

    public void setSegment2(Segment segment2) {
        this.segment2 = segment2;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
