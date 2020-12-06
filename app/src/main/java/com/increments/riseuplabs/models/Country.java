package com.increments.riseuplabs.models;

public class Country {
    private String name;
    private String code;
    private String timeZone;

    public Country() {
    }

    public Country(String name, String code, String timeZone) {
        this.name = name;
        this.code = code;
        this.timeZone = timeZone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
