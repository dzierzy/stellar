package pl.com.sages.stellar.dao;

import pl.com.sages.stellar.entities.Constellation;
import pl.com.sages.stellar.entities.Star;

import javax.jws.WebService;
import java.util.List;

@WebService
public interface StellarDAO {

    long getConstellationsCount();

    List<Constellation> getConstellations(String queryString, int pageNo, int pageSize);

    Constellation addConstellation(String abbr, String name) throws StellarException;

    Constellation findByAbbreviation(String abbr);

    List<Star> getStars(String constAbbrv);

    Star addStar(Constellation c, String id, String name) throws StellarException;
}
