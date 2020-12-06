package com.increments.riseuplabs.models;

import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Movie {

    private int id;
    @SerializedName("image")
    private Image mImage;
    private String name;
    @SerializedName("network")
    private Network mNetwork;
    @SerializedName("webChannel")
    private WebChannel mWebChannel;
    private String type;
    private List<String> genres;
    private String status;
    @SerializedName("schedule")
    private Schedule mSchedule;
    private String officialSite;
    @SerializedName("rating")
    private Rating mRating;

    /*private String language;

    private int runtime;
    private String premiered;
    private int weight;
    private Externals mExternals;
    private Image mImage;
    private String summary;
    private int updated;
    private Links mLinks;*/

    public Movie() {
    }

    public Movie(Image image, String name, Network network, String type,
                 List<String> genres, String status, Schedule schedule, String officialSite, Rating rating) {
        mImage = image;
        this.name = name;
        mNetwork = network;
        this.type = type;
        this.genres = genres;
        this.status = status;
        mSchedule = schedule;
        this.officialSite = officialSite;
        mRating = rating;
    }

    public Movie(Image image, String name, WebChannel webChannel, String type, List<String> genres,
                 String status, Schedule schedule, String officialSite, Rating rating) {
        mImage = image;
        this.name = name;
        mWebChannel = webChannel;
        this.type = type;
        this.genres = genres;
        this.status = status;
        mSchedule = schedule;
        this.officialSite = officialSite;
        mRating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        if (mImage != null) {
            if (!mImage.getOriginal().equals("_")) {
                return mImage.getOriginal();
            } else {
                return mImage.getMedium();
            }
        }
        return "_";
    }

    public void setImage(Image image) {
        this.mImage = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNetwork() {
        //HBO (America-USA)
        if (mNetwork != null) {
            if (!mNetwork.getCountry().equals("_")) {
                return mNetwork.getName() + " " + mNetwork.getCountry();
            }
            return mNetwork.getName();
        }
        return "_";
    }

    public void setNetwork(Network network) {
        mNetwork = network;
    }

    public String getWebChannel() {
        if (mWebChannel != null) {
            if (!mWebChannel.getCountry().equals("_")) {
                return mWebChannel.getName() + " " + mWebChannel.getCountry();
            }
            return mWebChannel.getName();
        }
        return "_";
    }

    public void setWebChannel(WebChannel webChannel) {
        mWebChannel = webChannel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenres() {
        if (genres != null) {
            return TextUtils.join(" | ", genres);
        }
        return "_";
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchedule() {
        if (mSchedule != null) {
            if (!mSchedule.getDays().equals("_") && !mSchedule.getTime().equals("_")) {
                return mSchedule.getDays() + mSchedule.getTime();
            }
            return mSchedule.getDays();
        }
        return "_";
    }

    public void setSchedule(Schedule schedule) {
        mSchedule = schedule;
    }

    public String getOfficialSite() {
        if (TextUtils.isEmpty(officialSite)){
            return "_";
        }
        return officialSite;
    }

    public void setOfficialSite(String officialSite) {
        this.officialSite = officialSite;
    }

    public float getRating() {
        if (mRating != null) {
            return (float) mRating.getAverage();
        }
        return (float) 0.0;
    }

    public void setRating(Rating rating) {
        mRating = rating;
    }
}
