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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.EventLog;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * The type Edit event.
 */
public class EditEvent extends AppCompatActivity {

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
     * The Event id.
     */
    long eventId;

    /**
     * The Add button.
     */
    FloatingActionButton addButton;
    /**
     * The Delete button.
     */
    FloatingActionButton deleteButton;

    /**
     * The Context.
     */
    Context context;

    /**

     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_events);

        addTitle = findViewById(R.id.addTitle);
        addDescription = findViewById(R.id.addDescription);
        addLocation = findViewById(R.id.addLocation);
        addStartTime = findViewById(R.id.addStartTime);
        addEndTime = findViewById(R.id.addEndTime);
        addButton = findViewById(R.id.addButton);
        deleteButton = findViewById(R.id.deleteButton);
        addWeekday = findViewById(R.id.daySpinner);

        addWeekday.setSelection(0);

        Intent editEvent = getIntent();
        String getTitle = editEvent.getStringExtra(EventAdapter.TITLE);
        String getDescription = editEvent.getStringExtra(EventAdapter.DESCRIPTION);
        String getLocation = editEvent.getStringExtra(EventAdapter.LOCATION);
        String getStartTime = editEvent.getStringExtra(EventAdapter.START_TIME);
        String getEndTime = editEvent.getStringExtra(EventAdapter.END_TIME);

        eventId = editEvent.getLongExtra(EventAdapter.EVENT_ID,0);

        addTitle.setText(getTitle);
        addDescription.setText(getDescription);
        addLocation.setText(getLocation);
        addStartTime.setText(getStartTime);
        addEndTime.setText(getEndTime);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = addTitle.getText().toString();
                String description = addDescription.getText().toString();
                String location = addLocation.getText().toString();
                String startTime = addStartTime.getText().toString();
                String endTime = addEndTime.getText().toString();
                String weekday = addWeekday.getSelectedItem().toString();
//                Events monEvents = new Events(title, description, location, startTime, endTime,weekday);
//                monEvents.save();
                Events editEvent = Events.findById(Events.class,eventId);
                editEvent.title = title;
                editEvent.description = description;
                editEvent.location = location;
                editEvent.startTime = startTime;
                editEvent.endTime = endTime;
                editEvent.weekday = weekday;
                editEvent.save(); //updateEvent
                finish();
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Events deleteEvent = Events.findById(Events.class, eventId);
                    deleteEvent.delete();
                    finish();
                }
                catch(NullPointerException ex)
                {
                    Toast.makeText(context, R.string.delete_error, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
