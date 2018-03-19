package com.mgil.java.labs.tickets.entities;


import java.io.Serializable;

public enum SeatPosition implements Serializable{

    ORCHESTRA("ORCHESTRA","orchestra"),
    BOX("BOX","box"),
    BALCONY("BALCONY","balcony");

    private final String label;
    private final String dbRepresentation;

    SeatPosition(String label, String dbRepresentation) {
        this.label = label;
        this.dbRepresentation = dbRepresentation;
    }

    public String getLabel() {
        return label;
    }

    public String getDbRepresentation() {
        return dbRepresentation;
    }
}
