package com.semanticweb.galois.domain;

import com.semanticweb.framework.entity.CoreEntity;

/**
 * 
 * Entidade que representa um metadado que descreve segmentos
 *
 */
public class Metadata extends CoreEntity {
    private static final long serialVersionUID = 834271157329799459L;
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
