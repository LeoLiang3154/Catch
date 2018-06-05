/**
 * *
 *@author Jiajian Liang
 *@version  1.0.0 foo
 *
 *These code refer to Youtube and Github author ynunez
 *
 * Copyright 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.mad.jiajianliang.service;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.AsyncTask;


import com.mad.jiajianliang.data.Channel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * The type Yahoo weather service.
 */
public class YahooWeatherService {
    private WeatherServiceCallBack callBack;
    private String location;
    private Exception exception;

    /**
     * Instantiates a new Yahoo weather service.
     *
     * @param callBack the call back
     */
    public YahooWeatherService(WeatherServiceCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Refreshes weather to get weather information from Yahoo!
     *
     * @param l the l
     */
    @SuppressLint("StaticFieldLeak")
    public void refreshWeather(String l) {
        this.location = l;
        new AsyncTask<String, Void, String>() {
            /**
             * gets JSON in background Thread
             *
             * @param strings
             * @return
             */
            @Override
            protected String doInBackground(String... strings) {

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='c'", strings[0]);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);
                    URLConnection connection = url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    return result.toString();
                } catch (MalformedURLException e) {
                    exception = e;
                    return null;
                } catch (IOException e) {
                    exception = e;
                }
                return null;
            }


            /**
             * @param s
             */
            @Override
            protected void onPostExecute(String s) {
                if (s == null && exception != null) {
                    callBack.serviceFailure(exception);
                    return;
                }
                try {
                    JSONObject data = new JSONObject(s);

                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");
                    if (count == 0) {
                        callBack.serviceFailure(new LocationWeatherException("No weather information found for " + location));
                        return;
                    }

                    Channel channel = new Channel();
                    channel.populate(queryResults.optJSONObject("results").optJSONObject("channel"));

                    callBack.serviceSuccess(channel);

                } catch (JSONException e) {
                    callBack.serviceFailure(e);
                }

            }


        }.execute(location);
    }


    /**
     * The type Location weather exception.
     */
    public class LocationWeatherException extends Exception {
        /**
         * Instantiates a new Location weather exception.
         *
         * @param detailMessage the detail message
         */
        LocationWeatherException(String detailMessage) {
            super(detailMessage);
        }
    }
}
