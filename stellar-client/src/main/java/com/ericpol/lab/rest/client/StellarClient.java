package com.ericpol.lab.rest.client;


import pl.com.sages.stellar.dto.ConstellationDTO;
import pl.com.sages.stellar.dto.Page;
import pl.com.sages.stellar.utils.RestEasyUtils;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;


public class StellarClient {

    Logger logger = Logger.getLogger(StellarClient.class.getName());

    public Page getConstellations(int pageNo, int pageCount){

        Config config = new Config();
        logger.info("configured to work with taget " + config.toURL(""));

        Page<ConstellationDTO> page = ClientBuilder.newClient()
                .target(config.toURL("/constellations"))
                .request(MediaType.APPLICATION_JSON).get(Page.class);

        return page;
    }

    public Response upload(String filePath) throws Exception {

        Config config = new Config();

        GenericEntity entity = RestEasyUtils.multipartEntity(filePath);

        Response response = ClientBuilder.newClient().target(config.toURL("/logo")).request().
                post( Entity.entity(entity, MediaType.MULTIPART_FORM_DATA_TYPE));

        return response;
    }




}
