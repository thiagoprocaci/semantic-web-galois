package com.semanticweb.framework.entity;

import java.io.Serializable;


/**
 * 
 * Entidade generica da aplicacao
 * 
 * @param <ID>
 */
public interface IEntity<ID extends Serializable> extends Serializable {
        /**
         * 
         * @return Retorna id
         */
        public ID getId();

        /**
         * Seta id
         * 
         * @param id
         */
        public void setId(ID id);

        /**
         * 
         * @return Retorna true caso o objeto nao tenha sido ainda persistido
         */
        public boolean isNew();
}
