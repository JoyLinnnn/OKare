package com.example.home;

public class ModelMedicine {
    private String meditime;
    private String mediname;
    private String cey;

    public ModelMedicine(){

    }

    public ModelMedicine(String meditime, String mediname) {
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

    public String getCey() {
        return cey;
    }

    public void setCey(String cey) {
        this.cey = cey;
    }
}
