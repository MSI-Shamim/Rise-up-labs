package com.increments.riseuplabs.models;

public class Links {
    private Self mSelf;
    private Previousepisode mPreviousepisode;

    public Links() {
    }

    public Links(Self self, Previousepisode previousepisode) {
        mSelf = self;
        mPreviousepisode = previousepisode;
    }

    public Self getSelf() {
        return mSelf;
    }

    public void setSelf(Self self) {
        mSelf = self;
    }

    public Previousepisode getPreviousepisode() {
        return mPreviousepisode;
    }

    public void setPreviousepisode(Previousepisode previousepisode) {
        mPreviousepisode = previousepisode;
    }
}
