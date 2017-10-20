package com.ericpol.lab.rest.skymap.dto;

/**
 * Created by xdzm on 2015-09-04.
 */
public class StarDTO {

    private String abbreviation;

    private String name;

    public StarDTO(String abbreviation, String name) {
        this.abbreviation = abbreviation;
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
