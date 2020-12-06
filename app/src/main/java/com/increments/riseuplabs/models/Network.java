package com.increments.riseuplabs.models;

public class Network {
    private int id;
    private String name;
    private Country mCountry;

    public Network() {
    }

    public Network(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        mCountry = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        if (mCountry != null) {
            return "(" + mCountry.getName() + "-" +
                    mCountry.getCode() + ")";
        }
        return "_";
    }

    public void setCountry(Country country) {
        mCountry = country;
    }
}
