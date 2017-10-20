package com.ericpol.lab.rest.skymap.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/health")
@Consumes(MediaType.APPLICATION_JSON)
public class HealthService {

    // TODO HTTP GET method: hello world
    @GET
    public String alive(){
        return "I'm alive!";
    }
}
