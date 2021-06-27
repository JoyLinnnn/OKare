package com.example.home;

public class ModelUsers2 {
    private String meditime;
    private String mediname;
    private String key;

    public ModelUsers2() {

    }

    public ModelUsers2(String meditime, String mediname) {
        this.meditime = meditime;
        this.mediname = mediname;
    }

    public String getMeditime() {
        return meditime;
    }

    public void setMeditime(String meditime) {
        this.meditime = meditime;
    }

    public String getMediname() {
        return mediname;
    }

    public void setMediname(String mediname) {
        this.mediname = mediname;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}


