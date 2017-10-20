package com.ericpol.lab.rest.skymap.dao;

import com.ericpol.lab.rest.skymap.data.Constellation;
import com.ericpol.lab.rest.skymap.data.Star;

import java.util.List;


public interface ConstellationDAO {

    long getConstellationsCount();

    List<Constellation> getConstellations(int pageNo, int pageSize);

    Constellation addConstellation(String abbr, String name) throws ConstellationDAOException;

    Constellation findByAbbreviation(String abbr);

    List<Star> getStars(String constAbbrv);

    Star addStar(Constellation c, String abbr, String name) throws ConstellationDAOException;
}
