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

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * AddEvent class for AddEvent Activity
 */
public class AddEvent extends AppCompatActivity {

    /**
     * The Add title.
     */
    EditText addTitle;
    /**
     * The Add description.
     */
    EditText addDescription;
    /**
     * The Add location.
     */
    EditText addLocation;
    /**
     * The Add start time.
     */
    EditText addStartTime;
    /**
     * The Add end time.
     */
    EditText addEndTime;
    /**
     * The Add weekday.
     */
    Spinner addWeekday;

    /**
     * The Add button.
     */
    FloatingActionButton addButton;

    /**
     * onCreate called when addEvent activity start
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        addTitle = findViewById(R.id.addTitle); //register the components
        addDescription = findViewById(R.id.addDescription);
        addLocation = findViewById(R.id.addLocation);
        addStartTime = findViewById(R.id.addStartTime);
        addEndTime = findViewById(R.id.addEndTime);
        addButton = findViewById(R.id.addButton);
        addWeekday = findViewById(R.id.daySpinner);

        addWeekday.setSelection(0); // set spinner default selection is Monday

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = addTitle.getText().toString();
                String description = addDescription.getText().toString();
                String location = addLocation.getText().toString();
                String startTime = addStartTime.getText().toString();
                String endTime = addEndTime.getText().toString();
                String weekday = addWeekday.getSelectedItem().toString();
                Events monEvents = new Events(title, description, location, startTime, endTime,weekday);
                monEvents.save();
                finish();
            }
        });
    }
}
