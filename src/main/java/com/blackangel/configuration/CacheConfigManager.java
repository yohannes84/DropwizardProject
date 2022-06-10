package com.blackangel.configuration;

import com.blackangel.model.Geolocation;
import com.blackangel.service.GeolocationService;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CacheConfigManager {

    private static final Logger logger = LoggerFactory.getLogger(CacheConfigManager.class);

    private static CacheConfigManager cacheConfigManager = new CacheConfigManager();

    public static CacheConfigManager getInstance() {
        return cacheConfigManager;
    }

    private static LoadingCache<String, Optional<Geolocation>> geolocationCache;

    public void initStudentCache(GeolocationService geolocationService) {
        if (geolocationCache == null) {
            geolocationCache =
                    CacheBuilder.newBuilder()
                            .concurrencyLevel(10)
                            .maximumSize(200) // Maximum of 200 records can be cached
                            .expireAfterAccess(1, TimeUnit.MINUTES) // Cache will expire after 30 minutes
                            .recordStats()
                            .build(new CacheLoader<String, Optional<Geolocation>>() { // Build the CacheLoader

                                @Override
                                public Optional<Geolocation> load(String ipAddress) throws Exception {
                                    logger.info("Fetching Geolocation Data from postgresDB/ Cache Miss");
                                    return geolocationService.getGeoLocationDetailByIp(ipAddress);
                                }
                            });
        }
    }

    public Optional<Geolocation> getGeoLocationDataFromCache(String ipAddress) {
        try {
            CacheStats cacheStats = geolocationCache.stats();
            logger.info("CacheStats = {} ", cacheStats);
            return geolocationCache.get(ipAddress);
        } catch (ExecutionException e) {
            logger.error("Error Retrieving data from the Geolocation Cache"
                    + e.getMessage());
        }
        return null;
    }
}
