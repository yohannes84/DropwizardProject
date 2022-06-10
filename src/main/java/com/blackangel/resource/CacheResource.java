package com.blackangel.resource;

import com.blackangel.configuration.CacheConfigManager;
import com.codahale.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/cache")
@Produces(MediaType.APPLICATION_JSON)
public class CacheResource {

    private static final Logger logger = LoggerFactory.getLogger(CacheResource.class);

    @Timed
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response cache() {
        logger.info("In CacheResource.cache()...Get Geolocation Data");
        //On the first call, data will be fetched from DB and
        //cache will be populated with the corresponding geolocation record
        //On all subsequent calls, data will be returned from the cache

        return Response.ok(getStudentData()).build();
    }

    private Response getStudentData() {

      return Response.ok(CacheConfigManager.getInstance()
              .getGeoLocationDataFromCache("24.48.0.1")).build();

    }
}
