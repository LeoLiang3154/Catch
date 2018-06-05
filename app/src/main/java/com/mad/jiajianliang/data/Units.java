package com.mad.jiajianliang.data;

import org.json.JSONObject;

public class Units implements JSONPopulator {

    private String temperature;

    @Override
    public void populate(JSONObject data) {

        temperature = data.optString("temperature");
    }

    public String getTemperature() {
        return temperature;
    }
}
