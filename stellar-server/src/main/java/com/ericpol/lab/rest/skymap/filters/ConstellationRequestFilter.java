package com.ericpol.lab.rest.skymap.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.logging.Logger;

@Provider
public class ConstellationRequestFilter implements ContainerRequestFilter
{

    private Logger logger = Logger.getLogger(ConstellationRequestFilter.class.getName());

    @Context SecurityContext sctxt;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        logger.info("filtering request...");
        logger.info("media type :" + requestContext.getMediaType());
        logger.info("method :" + requestContext.getMethod());
        logger.info("acceptable media types :" + requestContext.getAcceptableMediaTypes());
        logger.info("security principal :" + sctxt.getUserPrincipal());
        if(sctxt.getUserPrincipal()!=null){
            logger.info("security principal :" + sctxt.getUserPrincipal().getName());
        }
    }
}