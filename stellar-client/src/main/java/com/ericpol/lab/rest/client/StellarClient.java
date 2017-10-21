package com.ericpol.lab.rest.client;


import pl.com.sages.stellar.dto.Page;
import pl.com.sages.stellar.utils.RestEasyUtils;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;


public class StellarClient {

    Logger logger = Logger.getLogger(StellarClient.class.getName());

    // TODO connect via JAX-RS client to constellation service and fetch constellation list
    public Page getConstellations(int pageNo, int pageCount){

        Config config = new Config();
        logger.info("configured to work with taget " + config.toURL(""));

        Page list = ClientBuilder.newClient().register(new Authenticator(config.getUser(), config.getPassword()))
                .target(config.toURL("/constellations")).queryParam("pageNo", pageNo).queryParam("pageSize", pageCount ).request(config.getAccept()).get(Page.class);

        return list;
    }

    // TODO connect via JAX-RS client to logo service and upload new logo png file
    public Object upload(String filePath) throws FileNotFoundException {

        Config config = new Config();

        GenericEntity entity = RestEasyUtils.multipartEntity(filePath);

        Object result = ClientBuilder.newClient()
                .target(config.toURL("/logo")).request().post( Entity.entity(entity, MediaType.MULTIPART_FORM_DATA_TYPE));
        return result;
    }




}
