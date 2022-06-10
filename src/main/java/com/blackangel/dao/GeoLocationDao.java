package com.blackangel.dao;

import com.blackangel.model.Geolocation;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

public class GeoLocationDao extends AbstractDAO<Geolocation> {
    public GeoLocationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Geolocation findById(Long id){
        return get(id);
    }

//    "98.48.2.1",
//            "success",
//            "Canada",
//            "CA",
//            "QC",
//            "Quebec",
//            "Montreal",
//            "H1K",
//            "America/Toronto",
//            45.6085,
//            45.6085,
//            "Videotron Ltee",
//            "AS5769 Videotron Telecom Ltee"


    public Geolocation save(Geolocation geolocation){
        return persist(geolocation);
    }

    public Geolocation findByIp(String ip){
        return get(ip);
    }


}
