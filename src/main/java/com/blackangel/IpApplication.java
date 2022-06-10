package com.blackangel;

import com.blackangel.configuration.CacheConfigManager;
import com.blackangel.configuration.IPApiConfiguration;
import com.blackangel.dao.GeoLocationDao;
import com.blackangel.model.Geolocation;
import com.blackangel.resource.CacheResource;
import com.blackangel.resource.GeoLocationResource;
import com.blackangel.service.GeolocationService;
import com.blackangel.service.GeolocationServiceImpl;
import com.codahale.metrics.health.HealthCheck;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

public class IpApplication extends Application<IPApiConfiguration> {

    private static final Logger logger = LoggerFactory.getLogger(IpApplication.class);

    public static void main(String[] args) throws Exception {
        new IpApplication().run(args);
    }
    @Override
    public void run(IPApiConfiguration ipApiConfiguration, Environment environment) throws Exception {
        environment.healthChecks().register("blackangel", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }

        });

        environment.jersey().register(new AbstractBinder() {
            @Override
            public void configure() {
                bindAsContract(GeoLocationDao.class).in(Singleton.class);
                bindAsContract(GeolocationService.class).in(Singleton.class);
            }
        });

        CacheConfigManager cacheConfigManager = CacheConfigManager.getInstance();


        GeoLocationDao geoLocationDao = new GeoLocationDao(hibernateBundle.getSessionFactory());

        GeolocationService geolocationService = new GeolocationServiceImpl(geoLocationDao);

        cacheConfigManager.initStudentCache(geolocationService);
        logger.info("Registering RESTful API resources");

        environment.jersey().register(new CacheResource());
        environment.jersey().register(new GeoLocationResource(geolocationService));
    }

    private final HibernateBundle<IPApiConfiguration> hibernateBundle = new HibernateBundle<IPApiConfiguration>(Geolocation.class) {

        @Override
        public PooledDataSourceFactory getDataSourceFactory(IPApiConfiguration ipApiConfiguration) {
            return ipApiConfiguration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<IPApiConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

}
