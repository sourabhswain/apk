package com.vouov.bean;

import java.util.List;
import java.util.Map;

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

    private List<String> test1;
    private MyGEO test2;
    private MyGEO[] test3;
    private String[] test4;
    private Map<String, MyGEO> test5;

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

    public List<String> getTest1() {
        return test1;
    }

    public void setTest1(List<String> test1) {
        this.test1 = test1;
    }

    public MyGEO getTest2() {
        return test2;
    }

    public void setTest2(MyGEO test2) {
        this.test2 = test2;
    }

    public MyGEO[] getTest3() {
        return test3;
    }

    public void setTest3(MyGEO[] test3) {
        this.test3 = test3;
    }

    public String[] getTest4() {
        return test4;
    }

    public void setTest4(String[] test4) {
        this.test4 = test4;
    }

    public Map<String, MyGEO> getTest5() {
        return test5;
    }

    public void setTest5(Map<String, MyGEO> test5) {
        this.test5 = test5;
    }
}
