/**
 * *
 *@author Jiajian Liang
 *@version  1.0.0 foo
 *
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

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * The type Events.
 */
public class Events extends SugarRecord {

    /**
     * The Title.
     */
    @Unique
    public String title;
    /**
     * The Location.
     */
    public String location;
    /**
     * The Description.
     */
    public String description;
    /**
     * The Start time.
     */
    public String startTime;
    /**
     * The End time.
     */
    public String endTime;
    /**
     * The Weekday.
     */
    public String weekday;


    /**
     * Instantiates a new Events.
     *
     * @param title       the title
     * @param description the description
     * @param location    the location
     * @param startTime   the start time
     * @param endTime     the end time
     * @param weekday     the weekday
     */
    public Events(String title,String description, String location, String startTime, String endTime, String weekday) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.startTime = startTime;
        this.endTime = endTime;
        this.weekday = weekday;

    }

    /**
     * empty constructor needed according to sugarORM
     *
     * Instantiates a new Events.
     */
    public Events() {
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets start time.
     *
     * @return the start time
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * Sets start time.
     *
     * @param startTime the start time
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * Gets end time.
     *
     * @return the end time
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * Sets end time.
     *
     * @param endTime the end time
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
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
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets weekday.
     *
     * @return the weekday
     */
    public String getWeekday() {

        return weekday;
    }

    /**
     * Sets weekday.
     *
     * @param weekday the weekday
     */
    public void setWeekday(String weekday) {
        this.weekday = weekday;
    }

}
