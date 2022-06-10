package com.blackangel.service;

import com.blackangel.dao.GeoLocationDao;
import com.blackangel.model.Geolocation;
import org.hibernate.SessionFactory;

import javax.inject.Inject;

public class GeolocationServiceImpl implements GeolocationService {



    private GeoLocationDao geoLocationDao;
    @Inject
    public GeolocationServiceImpl(GeoLocationDao geoLocationDao) {
        this.geoLocationDao = geoLocationDao;
    }

    public Geolocation getGeoLocationDetailById (Long id){
        return geoLocationDao.findById(id);
    }
    public Geolocation getGeoLocationDetailByIp(String ip){
        return geoLocationDao.findByIp(ip) ;
    }
    public Geolocation addGeoLocation(Geolocation geolocation){
        return geoLocationDao.save(geolocation);
    }
}
