package com.ericpol.lab.rest.skymap.dao.impl;

import com.ericpol.lab.rest.skymap.dao.ConstellationDAO;
import com.ericpol.lab.rest.skymap.dao.ConstellationDAOException;
import com.ericpol.lab.rest.skymap.data.Constellation;
import com.ericpol.lab.rest.skymap.data.Star;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.UserTransaction;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

//@Alternative
public class InMemoryConstellationDAO implements ConstellationDAO {


    private List<Constellation> constellations = new ArrayList<>();

    {
        init();
    }

    private void init() {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("constellations.csv")))) {

            String line;
            while ((line=br.readLine())!=null){
                String[] parts = line.split(";");
                Constellation c = new Constellation();
                c.setId(parts[0]);
                c.setName(parts[1]);
                constellations.add(c);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("stars.csv")))) {

            String line;
            while ((line=br.readLine())!=null){
                String[] parts = line.split(";");
                Star s = new Star();
                s.setId(parts[0]);
                s.setName(parts[1]);
                findByAbbreviation(parts[2]).addStar(s);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public long getConstellationsCount() {
        return constellations.size();
    }

    @Override
    public List<Constellation> getConstellations(int pageNo, int pageSize) {

        if(pageNo==-1) return constellations;
        int startIndex = (pageNo - 1) * pageSize;
        int endIndex = pageNo * pageSize;
        if (endIndex > constellations.size()) endIndex = constellations.size();
        return constellations.subList(startIndex, endIndex);

    }


    public Constellation addConstellation(String abbr, String name) throws ConstellationDAOException {

        Constellation c = new Constellation();
        c.setId(abbr);
        c.setName(name);
        constellations.add(c);
        return c;
    }

    @Override
    public Constellation findByAbbreviation(final String abbr) {
        return constellations.stream().filter(c -> c.getId().equalsIgnoreCase(abbr)).findFirst().get();
    }

    @Override
    public List<Star> getStars(String constAbbrv) {
        return findByAbbreviation(constAbbrv).getStars();
    }

    @Override
    public Star addStar(Constellation c, String abbr, String name) throws ConstellationDAOException {

        Star s = new Star();
        s.setId(abbr);
        s.setName(name);
        s.setConstellation(c);
        return s;

    }
}
