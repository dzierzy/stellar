package com.ericpol.lab.rest.client;

import com.ericpol.lab.rest.skymap.dto.ListWrapper;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataOutput;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Logger;


public class Client {

    Logger logger = Logger.getLogger(Client.class.getName());

    // TODO connect via JAX-RS client to constellation service and fetch constellation list
    public ListWrapper getConstellations(int pageNo, int pageCount){

        Config config = new Config();
        logger.info("configured to work with taget " + config.toURL(""));

        ListWrapper list = ClientBuilder.newClient().register(new Authenticator(config.getUser(), config.getPassword()))
                .target(config.toURL("/constellations")).queryParam("pageNo", pageNo).queryParam("pageSize", pageCount ).request(config.getAccept()).get(ListWrapper.class);

        return list;
    }

    // TODO connect via JAX-RS client to logo service and upload new logo png file
    public Object upload(String filePath) throws FileNotFoundException {

        Config config = new Config();

        MultipartFormDataOutput mdo = new MultipartFormDataOutput();
        mdo.addFormData("file", new FileInputStream(new File(filePath)),    MediaType.APPLICATION_OCTET_STREAM_TYPE);
        mdo.addFormData("fileName", new FileInputStream(new File(filePath)), MediaType.APPLICATION_OCTET_STREAM_TYPE);
        GenericEntity<MultipartFormDataOutput> entity = new GenericEntity<MultipartFormDataOutput>(mdo) {};

        Object result = ClientBuilder.newClient().register(new Authenticator(config.getUser(), config.getPassword()))
                .target(config.toURL("/logo")).request().post( Entity.entity(entity, MediaType.MULTIPART_FORM_DATA_TYPE));
        return result;
    }

    /*

    private String authorization(String username, String password){

        String usernameAndPassword = username + ":" + password;
        String authorizationHeaderName = "Authorization";

        String authorizationHeaderValue = null;
        try {
            authorizationHeaderValue = "BASIC " + DatatypeConverter.printBase64Binary(usernameAndPassword.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return authorizationHeaderValue;
    }*/
}
