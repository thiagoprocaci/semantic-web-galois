package com.semanticweb.galois.domain.enumeration;

public enum Relation {
    IS_PART_OF("P"), IS_SIMILAR_TO("S"), IS_BASE_FOR("B"), COMPLEMENTS("C");
    private String value;

    private Relation(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Relation getByValue(String value) {
        if (IS_PART_OF.value.equals(value)) {
            return IS_PART_OF;
        }
        if (IS_SIMILAR_TO.value.equals(value)) {
            return IS_SIMILAR_TO;
        }
        if (IS_BASE_FOR.value.equals(value)) {
            return IS_BASE_FOR;
        }
        if (COMPLEMENTS.value.equals(value)) {
            return COMPLEMENTS;
        }
        return null;
    }
}
