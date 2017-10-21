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


@Path("/constellations")
@Produces(MediaType.APPLICATION_JSON)

public class StellarREST {

    private Logger logger = Logger.getLogger(StellarREST.class.getName());

    @Inject
    private StellarDAO cDao;

    @GET
    @Produces("application/*")
    public Page<ConstellationDTO> getConstellations(@QueryParam("query") String query) {

        logger.info("fetching constellation list");
        List<Constellation> constellations = cDao.getConstellations(query, -1, -1);
        List<ConstellationDTO> dtos = ConstellationConverter.convertConstellationEntities(constellations);
        logger.info(dtos.size() + " constellations found");
        return new Page<>(dtos);
    }

    // TODO HTTP POST method: add constellation
    @POST
    //@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Consumes(MediaType.APPLICATION_JSON)
    //public ConstellationDTO addConstellation(@FormParam("abbr") String abbr, @FormParam("name") String name) throws StellarException {
   public ConstellationDTO addConstellation(ConstellationDTO dto) throws StellarException {

        //logger.info("about to add constellation " + abbr + ", " + name);
        Constellation c = cDao.addConstellation(dto.getAbbreviation(), dto.getName());
        return ConstellationConverter.convertEntity(c);
    }

    @GET
    @Path("{cid}/stars")
    public List<StarDTO> getStars(@PathParam("cid") String cId){

        logger.info("fetching stars for constellation " + cId);
        List<Star> stars = cDao.getStars(cId);
        List<StarDTO> dtos = ConstellationConverter.convertStarEntities(stars);

        logger.info(dtos.size() + " stars founf for constellation " + cId);
        return dtos;
    }

    // TODO HTTP POST method: add star for constellation
    public StarDTO addStar(){

        return null;
    }

}
