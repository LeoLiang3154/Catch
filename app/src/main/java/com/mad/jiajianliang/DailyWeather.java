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
package com.mad.jiajianliang;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mad.jiajianliang.data.Channel;
import com.mad.jiajianliang.data.Item;
import com.mad.jiajianliang.service.WeatherServiceCallBack;
import com.mad.jiajianliang.service.YahooWeatherService;

/**
 * The type Daily weather.
 */
public class DailyWeather extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, WeatherServiceCallBack {

    private ImageView weatherImageView;
    private TextView weatherTem;
    private TextView weatherLocation;
    private TextView weatherCondition;

    private YahooWeatherService service;

    private ProgressDialog progressDialog;
    private final static String prefix= "@drawable/icon_";
    private final static String city = "Sydney, Australia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        weatherImageView = findViewById(R.id.weatherIconImageView);
        weatherTem = findViewById(R.id.weatherTem);
        weatherLocation = findViewById(R.id.weatherLocation);
        weatherCondition = findViewById(R.id.weatherCondition);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progressDialog_loading));
        progressDialog.show();


        service = new YahooWeatherService(this);
        service.refreshWeather(city);


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_catch) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(DailyWeather.this, MainActivity.class));
                    finish();
                }
            }, 500);

        } else if (id == R.id.nav_activity) {


        }


        return true;
    }

    /**
     * if get weather service success, set text on view
     *
     * @param channel the channel
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void serviceSuccess(Channel channel) {
        progressDialog.hide();

        Item item = channel.getItem();

        int resourceId = getResources().getIdentifier(prefix+ item.getCondition().getCode(),null,getPackageName());
        Drawable weatherIconDrawble = getResources().getDrawable(resourceId);

        weatherImageView.setImageDrawable(weatherIconDrawble);

        weatherLocation.setText(service.getLocation());
        weatherTem.setText(item.getCondition().getTemperature() + "\u00B0" + channel.getUnits().getTemperature());
        weatherCondition.setText(item.getCondition().getDescription());
    }

    /**
     *shows exception message on Toast
     *
     * @param exception the exception
     */
    @Override
    public void serviceFailure(Exception exception) {
        progressDialog.hide();
        Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
