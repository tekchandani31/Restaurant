package com.example.project;

public class Cartgetset {

    private String name;
    private String type;
    private String cost;

    public Cartgetset(){

    }

    public Cartgetset(String name, String type, String cost) {
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }




}

