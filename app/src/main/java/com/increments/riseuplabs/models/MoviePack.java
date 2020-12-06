package com.increments.riseuplabs.models;

public class MoviePack {
    private double score;
    private Movie show;

    public MoviePack() {
    }

    public MoviePack(double score, Movie show) {
        this.score = score;
        this.show = show;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Movie getShow() {
        return show;
    }

    public void setShow(Movie show) {
        this.show = show;
    }
}
