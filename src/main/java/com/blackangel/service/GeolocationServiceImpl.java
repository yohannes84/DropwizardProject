package com.blackangel.service;

import com.blackangel.dao.GeoLocationDao;
import com.blackangel.model.Geolocation;

import javax.inject.Inject;
import java.util.Optional;

public class GeolocationServiceImpl implements GeolocationService {



    private GeoLocationDao geoLocationDao;
    @Inject
    public GeolocationServiceImpl(GeoLocationDao geoLocationDao) {
        this.geoLocationDao = geoLocationDao;
    }

    public Geolocation getGeoLocationDetailById (Long id){
        return geoLocationDao.findById(id);
    }
    public Optional<Geolocation> getGeoLocationDetailByIp(String ip){
        //return geoLocationDao.findByIp(ip) ;
        //return geoLocationDao.findByIpAddress(ip) ;
        return Optional.ofNullable(geoLocationDao.getGeolocationByIpAddress(ip));
    }
    public Geolocation addGeoLocation(Geolocation geolocation){
        return geoLocationDao.save(geolocation);
    }
}
