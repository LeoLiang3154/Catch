package com.mad.jiajianliang;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class Events extends SugarRecord {

    @Unique
    public String title;
    public String location;
    public String description;
    public String startTime;
    public String endTime;
    public String weekday;


    public Events(String title,String description, String location, String startTime, String endTime, String weekday) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekday = weekday;

    }

    public Events() {
    }

    public String getDescription() {
        return description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWeekday() {

        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

}
