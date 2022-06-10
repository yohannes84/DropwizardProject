package com.blackangel.model;

import javax.persistence.*;

@Entity
@Table(name="Geo_Location")
public class Geolocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ip;//query
    private String status;
    private String country;
    private String region;
    private String regionName;
    private String city;
    private String zip;
    private String isp;
    private String org;
    private double latitude;
    private double longitude;
    private String timezone;
    private String asnumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }

    public String getOrg() {
        return org;
    }

    public void setOrg(String org) {
        this.org = org;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getAsnumber() {
        return asnumber;
    }

    public void setAsnumber(String asnumber) {
        this.asnumber = asnumber;
    }



    public Geolocation(String query, String status,
                       String country, String region, String regionName,
                       String city, String zip, String isp, String org,
                       double latitude, double longitude, String timezone,
                       String as) {


        this.ip = query;
        this.status = status;
        this.country = country;
        this.region = region;
        this.regionName = regionName;
        this.city = city;
        this.zip = zip;
        this.isp = isp;
        this.org = org;
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.asnumber = as;
    }

    public Geolocation() {
    }
}
