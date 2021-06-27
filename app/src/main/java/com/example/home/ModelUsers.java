package com.example.home;

public class ModelUsers {
    private String backtime;
    private String hossite;
    private String key;

    public ModelUsers(){

    }

    public ModelUsers(String backtime, String hossite) {
        this.backtime = backtime;
        this.hossite = hossite;
    }

    public String getBacktime() {
        return backtime;
    }

    public void setBacktime(String backtime) {
        this.backtime = backtime;
    }

    public String getHossite() {
        return hossite;
    }

    public void setHossite(String hossite) {
        this.hossite = hossite;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
