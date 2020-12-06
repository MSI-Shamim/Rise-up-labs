package com.increments.riseuplabs.models;

import android.text.TextUtils;

import java.util.List;

public class Schedule {
    private String time;
    private List<String> days;

    public Schedule() {
    }

    public Schedule(String time, List<String> days) {
        this.time = time;
        this.days = days;
    }

    public String getTime() {
        if (TextUtils.isEmpty(time)){
            return "_";
        }
        return " at "+time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        if (days != null && !days.isEmpty()){
            return TextUtils.join(" | ", days);
        }
        return "_";
    }

    public void setDays(List<String> days) {
        this.days = days;
    }
}
