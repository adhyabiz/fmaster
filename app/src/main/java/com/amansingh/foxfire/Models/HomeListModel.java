package com.amansingh.foxfire.Models;

import java.util.HashMap;

public class HomeListModel extends UserID {

    private String user_id;
    private String master_id;
    private String speed;
    private String geoFencing;
    private HashMap<String, Double> location;
    private String start;

    public HomeListModel() {
    }

    public HomeListModel(String user_id, String master_id, String speed, String geoFencing, HashMap<String, Double> location, String start) {
        this.user_id = user_id;
        this.master_id = master_id;
        this.speed = speed;
        this.geoFencing = geoFencing;
        this.location = location;
        this.start = start;
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

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public HashMap<String, Double> getLocation() {
        return location;
    }

    public void setLocation(HashMap<String, Double> location) {
        this.location = location;
    }

    public String getGeoFencing() {
        return geoFencing;
    }

    public void setGeoFencing(String geoFencing) {
        this.geoFencing = geoFencing;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
