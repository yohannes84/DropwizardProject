package com.blackangel.resource;

import com.blackangel.dao.GeoLocationDao;
import com.blackangel.exception.UserDoesNotExistException;
import com.blackangel.model.Geolocation;
import com.blackangel.service.GeolocationService;
import io.dropwizard.hibernate.UnitOfWork;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/geolocations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GeoLocationResource {

    //private final GeoLocationDao geoLocationDao;

    private final GeolocationService geolocationService;


    public GeoLocationResource(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @GET
    @Path("/findBy/{id}")
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
    @Path("/findBy/{ip}")
    @UnitOfWork
    public Response getGeoLocationInfoByIp(@PathParam("ip") String ipAddress) {


        try {
            Geolocation geolocation = geolocationService.getGeoLocationDetailByIp(ipAddress);
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
