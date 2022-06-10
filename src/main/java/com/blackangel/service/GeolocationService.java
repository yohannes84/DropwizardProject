package com.blackangel.service;

import com.blackangel.model.Geolocation;

import java.util.Optional;

public interface GeolocationService {

    public Geolocation getGeoLocationDetailById (Long id);
    public Optional<Geolocation> getGeoLocationDetailByIp(String ip);
    public Geolocation addGeoLocation(Geolocation geolocation);
}
