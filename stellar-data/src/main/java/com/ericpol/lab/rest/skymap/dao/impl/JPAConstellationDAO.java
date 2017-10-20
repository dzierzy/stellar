package com.ericpol.lab.rest.skymap.dao.impl;

import com.ericpol.lab.rest.skymap.dao.ConstellationDAO;
import com.ericpol.lab.rest.skymap.dao.ConstellationDAOException;
import com.ericpol.lab.rest.skymap.data.Constellation;
import com.ericpol.lab.rest.skymap.data.Star;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.List;

@Alternative
public class JPAConstellationDAO implements ConstellationDAO {

    @PersistenceContext(unitName = "stars")
    private EntityManager em;


    @Inject
    private UserTransaction tx;

    @Override
    public long getConstellationsCount() {
        String queryString = "Select count(c) from Constellation c";
        Query query = em.createQuery(queryString);
        return (long) query.getSingleResult();
    }

    @Override
    public List<Constellation> getConstellations(int pageNo, int pageSize) {

        String queryString = "Select c from Constellation c order by lower(c.name)";

        Query query = em.createQuery(queryString);
        if(pageNo!=-1 && pageSize!=-1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();

    }


    public Constellation addConstellation(String abbr, String name) throws ConstellationDAOException {

        try {
            tx.begin();
            Constellation c = new Constellation();
            c.setId(abbr);
            c.setName(name);
            em.persist(c);
            tx.commit();
            return c;
        } catch (Exception e ) {
            throw new ConstellationDAOException("problem while adding constellation", e);
        }
    }

    @Override
    public Constellation findByAbbreviation(String abbr) {
        return em.find(Constellation.class, abbr);
    }

    @Override
    public List<Star> getStars(String constAbbrv) {
        String query = "Select s from Star s where s.constellation.id='" + constAbbrv + "'";
        return em.createQuery(query).getResultList();
    }

    @Override
    public Star addStar(Constellation c, String abbr, String name) throws ConstellationDAOException {
        try {
            tx.begin();
            Star s = new Star();
            s.setId(abbr);
            s.setName(name);
            s.setConstellation(c);
            em.persist(s);
            tx.commit();
            return s;
        } catch (Exception e ) {
            throw new ConstellationDAOException("problem while adding star", e);
        }
    }
}
