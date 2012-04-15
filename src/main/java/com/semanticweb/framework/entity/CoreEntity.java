package com.semanticweb.framework.entity;


/**
 * Entidade generica da aplicacao que contem id do tipo Integer 
 */

public abstract class CoreEntity implements IEntity<Integer> {
    private static final long serialVersionUID = 2185325286864472255L;

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CoreEntity other = (CoreEntity) obj;
        if (getId() == null) {
            if (other.getId() != null)
                return false;
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNew() {     
        return getId() == null;
    }
}
