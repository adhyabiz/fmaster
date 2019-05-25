package com.amansingh.foxfire.Models;

public class HomeListModel {

    private String text;
    private  String name;
    private  String location;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public HomeListModel(String text, String name, String location) {

        this.text = text;
        this.name = name;
        this.location = location;
    }
}
