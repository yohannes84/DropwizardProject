package com.blackangel.dao;

import com.blackangel.model.Geolocation;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import java.lang.reflect.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

public class GeoLocationDao extends AbstractDAO<Geolocation> {
    @PersistenceContext
    protected EntityManager entityManager;
    public GeoLocationDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Geolocation findById(Long id){
        return get(id);
    }

//            "98.48.2.1",
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

    public Geolocation findByIpAddress(String ipAddress) {
        Geolocation entity = null;
        try {
            return (Geolocation) entityManager.createNamedQuery(Geolocation.class.getSimpleName()+ ".findGeolocationByIp")
                    .setParameter("ipAddress", ipAddress)
                    .getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }


    public Geolocation getGeolocationByIpAddress(String ipAddress) {
        return (Geolocation) namedQuery("Geolocation.findGeolocationByIp")
                .setParameter("ipAddress", ipAddress)
                .getSingleResult();

    }


}
