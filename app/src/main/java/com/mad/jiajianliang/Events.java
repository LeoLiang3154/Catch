package com.mad.jiajianliang;

import com.orm.SugarRecord;

public class Events extends SugarRecord<Events> {

    String title;
    String description;
    String type;
    String weekday;
    String location;


    public Events() {
    }

    public Events(String title, String description, String type, String weekday, String location) {
        this.title = title;
        this.description = description;
        this.type = type;
        this.weekday = weekday;
        this.location = location;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWeekday() {
        return weekday;
    }

    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
