package com.amansingh.foxfire.Models;

import java.util.List;

public class HomeListModel {

    private String user_id;
    private String master_id;
    private String speed;
    private String geoFence;
    private List<String> location;

    public HomeListModel(String user_id, String master_id, String speed, String geoFence, List<String> location) {
        this.user_id = user_id;
        this.master_id = master_id;
        this.speed = speed;
        this.geoFence = geoFence;
        this.location = location;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getGeoFence() {
        return geoFence;
    }

    public void setGeoFence(String geoFence) {
        this.geoFence = geoFence;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMaster_id() {
        return master_id;
    }

    public void setMaster_id(String master_id) {
        this.master_id = master_id;
    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }
}
