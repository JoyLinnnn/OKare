package com.example.home;

public class ModelUsers {
    private String backtime;
    private String hossite;
    private String new1;
    private String key;

    public ModelUsers(){

    }

    public ModelUsers(String new1,String backtime, String hossite) {
        this.new1 = new1;
        this.backtime = backtime;
        this.hossite = hossite;
    }
    public String getnew1() {
        return new1;
    }

    public void setnew1(String new1) {
        this.new1 = new1;
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
