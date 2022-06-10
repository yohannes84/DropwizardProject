package com.blackangel.service;

import com.blackangel.dao.GeoLocationDao;
import com.blackangel.model.Geolocation;

public interface GeolocationService {

    public Geolocation getGeoLocationDetailById (Long id);
    public Geolocation getGeoLocationDetailByIp(String ip);
    public Geolocation addGeoLocation(Geolocation geolocation);
}
