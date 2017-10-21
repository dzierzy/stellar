package pl.com.sages.stellar.dao.impl;

import pl.com.sages.stellar.dao.StellarDAO;
import pl.com.sages.stellar.dao.StellarException;
import pl.com.sages.stellar.entities.Constellation;
import pl.com.sages.stellar.entities.Star;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.List;

@Alternative
public class JPAStellarDAO implements StellarDAO {

    //@PersistenceContext(unitName = "stars")
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
    public List<Constellation> getConstellations(String queryString, int pageNo, int pageSize) {

        String q = "Select c from Constellation c order by lower(c.name)";

        if(queryString!=null) {
            q = "Select c from Constellation c where lower(c.name) like lower('%" + queryString + "%') order by lower(c.name)";
        }
        Query query = em.createQuery(q);
        if(pageNo!=-1 && pageSize!=-1) {
            query.setFirstResult((pageNo - 1) * pageSize);
            query.setMaxResults(pageSize);
        }

        return query.getResultList();

    }


    public Constellation addConstellation(String abbr, String name) throws StellarException {

        try {
            tx.begin();
            Constellation c = new Constellation();
            c.setId(abbr);
            c.setName(name);
            em.persist(c);
            tx.commit();
            return c;
        } catch (Exception e ) {
            throw new StellarException("problem while adding constellation", e);
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
    public Star addStar(Constellation c, String abbr, String name) throws StellarException {
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
            throw new StellarException("problem while adding star", e);
        }
    }
}
