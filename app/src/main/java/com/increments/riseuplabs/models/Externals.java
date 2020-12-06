package com.increments.riseuplabs.models;

public class Externals {
    private int tvrage;
    private int thetvdb;
    private String imdb;

    public Externals() {
    }

    public Externals(int tvrage, int thetvdb, String imdb) {
        this.tvrage = tvrage;
        this.thetvdb = thetvdb;
        this.imdb = imdb;
    }

    public int getTvrage() {
        return tvrage;
    }

    public void setTvrage(int tvrage) {
        this.tvrage = tvrage;
    }

    public int getThetvdb() {
        return thetvdb;
    }

    public void setThetvdb(int thetvdb) {
        this.thetvdb = thetvdb;
    }

    public String getImdb() {
        return imdb;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
}
