/**
 * *
 *@author Jiajian Liang
 *@version  1.0.0 foo
 *
 *These code refer to Github author florent37
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

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.florent37.materialviewpager.MaterialViewPager;
import com.github.florent37.materialviewpager.header.HeaderDesign;
import com.mad.jiajianliang.fragment.FriFragment;
import com.mad.jiajianliang.fragment.MonFragment;
import com.mad.jiajianliang.fragment.SatFragment;
import com.mad.jiajianliang.fragment.SunFragment;
import com.mad.jiajianliang.fragment.ThurFragment;
import com.mad.jiajianliang.fragment.TueFragment;
import com.mad.jiajianliang.fragment.WedFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * The type Main activity.
 */
public class MainActivity extends DailyWeather implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * The M view pager.
     */
    @BindView(R.id.materialViewPager)
    MaterialViewPager mViewPager;

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("");
        ButterKnife.bind(this);
        final Toolbar toolbar = mViewPager.getToolbar();
        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }
        add();

        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            /**
             * return correct fragments according to current viewpager position
             *
             * @param position
             * @return
             */
            @Override
            public Fragment getItem(int position) {
                switch (position % 7) {
                    case 0:
                        return MonFragment.newInstance();
                    case 1:
                        return TueFragment.newInstance();
                    case 2:
                        return WedFragment.newInstance();
                    case 3:
                        return ThurFragment.newInstance();
                    case 4:
                        return FriFragment.newInstance();
                    case 5:
                        return SatFragment.newInstance();
                    case 6:
                        return SunFragment.newInstance();
                    default:
                        return new Fragment();
                }
            }

            @Override
            public int getCount() {
                return 7;
            }

            /**
             * sets the titles for pages
             *
             * @param position
             * @return
             */
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position % 7) {
                    case 0:
                        return "Mon";
                    case 1:
                        return "Tue";
                    case 2:
                        return "Wed";
                    case 3:
                        return "Thur";
                    case 4:
                        return "Fri";
                    case 5:
                        return "Sat";
                    case 6:
                        return "Sun";
                }
                return "";
            }
        });

        mViewPager.setMaterialViewPagerListener(new MaterialViewPager.Listener() {
            @Override
            public HeaderDesign getHeaderDesign(int page) {
                switch (page) {
                    case 0:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                getString(R.string.case_0_page));
                    case 1:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                getString(R.string.case_1_page));
                    case 2:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                getString(R.string.case_2_page));
                    case 3:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.red,
                                getString(R.string.case_3_page));
                    case 4:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.cyan,
                                getString(R.string.case_4_page));
                    case 5:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.blue,
                                getString(R.string.case_5_page));
                    case 6:
                        return HeaderDesign.fromColorResAndUrl(
                                R.color.green,
                                getString(R.string.case_6_page));
                }

                return null;
            }
        });

        mViewPager.getViewPager().setOffscreenPageLimit(mViewPager.getViewPager().getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());

        final View logo = findViewById(R.id.logo_white);
        if (logo != null) {
            logo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.notifyHeaderChanged();
                    Toast.makeText(getApplicationContext(), R.string.cat_logo_click, Toast.LENGTH_SHORT).show();
                }
            });
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * methods for drawer activity
     */
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
        int id = item.getItemId();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        if (id == R.id.nav_catch) {

        } else if (id == R.id.nav_activity) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(MainActivity.this, DailyWeather.class));
                    finish();
                }
            }, 500);

        }


        return true;
    }

    /**
     * add method for testing app
     *
     * Add.
     */
    public void add() {
//        Events event1 = new Events("Default","Default","Default","Default","Default","Monday");
//        event1.save();
//        Events event2 = new Events("Default","Default","Default","Default","Default","Tuesday");
//        event2.save();
//        Events event3 = new Events("Default","Default","Default","Default","Default","Wednesday");
//        event3.save();
//        Events event4 = new Events("Default","Default","Default","Default","Default","Thursday");
//        event4.save();
//        Events event5 = new Events("Default","Default","Default","Default","Default","Friday");
//        event5.save();
//        Events event6 = new Events("Default","Default","Default","Default","Default","Saturday");
//        event6.save();
//        Events event7 = new Events("Default","Default","Default","Default","Default","Sunday");
//        event7.save();
    }
}
