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


public class StellarREST {

    private Logger logger = Logger.getLogger(StellarREST.class.getName());

    @Inject
    private StellarDAO cDao;

    // TODO HTTP GET method: return list of constellations
    public Page<ConstellationDTO> getConstellations() {

        return null;
    }

    // TODO HTTP POST method: add constellation
    public ConstellationDTO addConstellation(){

        return null;
    }

    // TODO HTTP GET method: return list of stars for constellation
    public List<StarDTO> getStars(){

        return null;
    }

    // TODO HTTP POST method: add star for constellation
    public StarDTO addStar(){

        return null;
    }

}
