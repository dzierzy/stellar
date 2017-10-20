package com.ericpol.lab.rest.skymap.service;

import com.ericpol.lab.rest.skymap.ConstellationConverter;
import com.ericpol.lab.rest.skymap.dao.ConstellationDAO;
import com.ericpol.lab.rest.skymap.dao.ConstellationDAOException;
import com.ericpol.lab.rest.skymap.data.Constellation;
import com.ericpol.lab.rest.skymap.data.Star;
import com.ericpol.lab.rest.skymap.dto.ConstellationDTO;
import com.ericpol.lab.rest.skymap.dto.ListWrapper;
import com.ericpol.lab.rest.skymap.dto.StarDTO;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.logging.Logger;

@Path("constellations")
@Produces(MediaType.APPLICATION_JSON)
public class ConstellationService {

    private Logger logger = Logger.getLogger(ConstellationService.class.getName());

    @Inject
    private ConstellationDAO cDao;

    // TODO HTTP GET method: return list of constellations
    @GET
    public ListWrapper<ConstellationDTO> getConstellations(@QueryParam("pageNo") @DefaultValue("-1") int pageNo, @QueryParam("pageSize") @DefaultValue("-1") int pageSize) {

        List<ConstellationDTO> cs = ConstellationConverter.convertConstellations(cDao.getConstellations(pageNo, pageSize));
        long count = cDao.getConstellationsCount();

        logger.info("found " + cs.size() + " constellations. params [" + pageNo + ", " + pageSize + "]");

        if (pageNo != -1 && pageSize != -1) {
            long pageCount = (count / pageSize);
            if((count % pageSize) != 0) pageCount++;
            return new ListWrapper<ConstellationDTO>(cs, pageCount, pageNo);
        } else {
            return new ListWrapper<ConstellationDTO>(cs);
        }
    }

    // TODO HTTP POST method: add constellation
    @POST
    public ConstellationDTO addConstellation(@FormParam("abbr") String abbr, @FormParam("name") String name){

        logger.info("adding constellation [ " + abbr + ", " + name + " ]");

        Constellation c = null;
        try {
            c = cDao.addConstellation(abbr, name);
        } catch (ConstellationDAOException e) {
            e.printStackTrace();
        }

        return ConstellationConverter.convert(c);
    }

    // TODO HTTP GET method: return list of stars for constellation
    @GET
    @Path("{constId}/stars")
    public List<StarDTO> getStars(@PathParam("constId")String constId){

        List<StarDTO> stars = ConstellationConverter.convertStars(cDao.getStars(constId));
        return stars;
    }

    // TODO HTTP POST method: add star for constellation
    @POST
    @Path("{constId}/stars")
    public StarDTO addStar(@PathParam("constId")String constId, @FormParam("abbr") String abbr, @FormParam("name") String name){

        Star s = null;
        try {
            s = cDao.addStar(cDao.findByAbbreviation(constId), abbr, name);
        } catch (ConstellationDAOException e) {
            e.printStackTrace();
        }
        return ConstellationConverter.convert(s);
    }

}
