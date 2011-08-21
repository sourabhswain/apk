package com.vouov.bean;

/**
 * 地理信息Bean
 * User: yuminglong
 * Date: 11-8-21
 * Time: 上午10:36
 * Version: 1.0.0
 */
public class MyGEO {
    /**
     * GPS or NETWORK
     */
    private String provider;
    /**
     *  经度
     */
    private double longitude;
    /**
     * 纬度
     */
    private double latitude;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
