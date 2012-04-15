package com.semanticweb.galois.domain;

import com.semanticweb.framework.entity.CoreEntity;

/**
 * Entidade que representa um segmento de um arquivo
 */
public class Segment extends CoreEntity {
    private static final long serialVersionUID = -1315959780385815445L;
    private Integer id;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
