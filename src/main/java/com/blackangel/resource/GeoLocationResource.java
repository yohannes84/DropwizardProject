package com.blackangel.resource;

import com.blackangel.dao.GeoLocationDao;
import com.blackangel.exception.UserDoesNotExistException;
import com.blackangel.model.Geolocation;
import com.blackangel.service.GeolocationService;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/geolocations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeoLocationResource {



    private final Logger logger = LoggerFactory.getLogger(GeoLocationResource.class);

    private final GeolocationService geolocationService;

    @Inject
    public GeoLocationResource(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @GET
    @Path("/findById/{id}")
    @UnitOfWork
    public Response getGeoLocationInfoById(@PathParam("id") Long id) {


        try {
            Geolocation geolocation = geolocationService.getGeoLocationDetailById(id);
            return Response.ok(geolocation).build();
        } catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/findByIp/{ip:[a-zA-Z &+-]*}")
    @UnitOfWork
    public Response getGeoLocationInfoByIp(@PathParam("ip") String ipAddress) {


        try {
            logger.info("verifying string format for ip: " + ipAddress);
            Optional<Geolocation> geolocation = geolocationService.getGeoLocationDetailByIp(ipAddress);

            return Response.ok(geolocation).build();
        } catch (UserDoesNotExistException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/add")
    @UnitOfWork
    public Response addGeoLocation(@Valid Geolocation geolocation) {

        Geolocation geolocation1 = geolocationService.addGeoLocation(geolocation);

       return Response.status(Response.Status.CREATED).entity(geolocation1).build();

    }
}
