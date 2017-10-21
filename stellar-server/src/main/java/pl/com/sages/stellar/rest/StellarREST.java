package pl.com.sages.stellar.rest;

import pl.com.sages.stellar.rest.converter.ConstellationConverter;
import pl.com.sages.stellar.dao.StellarDAO;
import pl.com.sages.stellar.dao.StellarException;
import pl.com.sages.stellar.entities.Constellation;
import pl.com.sages.stellar.entities.Star;
import pl.com.sages.stellar.dto.ConstellationDTO;
import pl.com.sages.stellar.dto.Page;
import pl.com.sages.stellar.dto.StarDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("constellations")
@Produces(MediaType.APPLICATION_JSON)
public class StellarREST {

    private Logger logger = Logger.getLogger(StellarREST.class.getName());

    @Inject
    private StellarDAO cDao;

    // TODO HTTP GET method: return list of constellations
    @GET
    public Page<ConstellationDTO> getConstellations(@QueryParam("pageNo") @DefaultValue("-1") int pageNo, @QueryParam("pageSize") @DefaultValue("-1") int pageSize, @QueryParam("query") String queryString) {

        List<ConstellationDTO> cs = ConstellationConverter.convertConstellationEntities(cDao.getConstellations(queryString, pageNo, pageSize));
        long total = cDao.getConstellationsCount();

        logger.info("found " + cs.size() + " constellations. params [" + pageNo + ", " + pageSize + "]");

        if (pageNo != -1 && pageSize != -1) {
            return new Page<ConstellationDTO>(cs, total, pageNo, pageSize);
        } else {
            return new Page<ConstellationDTO>(cs);
        }
    }

    // TODO HTTP POST method: add constellation
    @POST
    public ConstellationDTO addConstellation(@FormParam("abbr") String abbr, @FormParam("name") String name){

        logger.info("adding constellation [ " + abbr + ", " + name + " ]");

        Constellation c = null;
        try {
            c = cDao.addConstellation(abbr, name);
        } catch (StellarException e) {
            e.printStackTrace();
        }

        return ConstellationConverter.convertEntity(c);
    }

    // TODO HTTP GET method: return list of stars for constellation
    @GET
    @Path("{constId}/stars")
    public List<StarDTO> getStars(@PathParam("constId")String constId){

        List<StarDTO> stars = ConstellationConverter.convertStarEntities(cDao.getStars(constId));
        return stars;
    }

    // TODO HTTP POST method: add star for constellation
    @POST
    @Path("{constId}/stars")
    public StarDTO addStar(@PathParam("constId")String constId, @FormParam("abbr") String abbr, @FormParam("name") String name){

        Star s = null;
        try {
            s = cDao.addStar(cDao.findByAbbreviation(constId), abbr, name);
        } catch (StellarException e) {
            e.printStackTrace();
        }
        return ConstellationConverter.convertEntity(s);
    }

}
