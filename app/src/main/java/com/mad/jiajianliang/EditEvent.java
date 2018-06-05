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

public class EditEvent extends AppCompatActivity {

    EditText addTitle;
    EditText addDescription;
    EditText addLocation;
    EditText addStartTime;
    EditText addEndTime;
    Spinner addWeekday;
    long eventId;

    FloatingActionButton addButton;
    FloatingActionButton deleteButton;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());
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
                    Toast.makeText(context,"Whoops, it seems go wrong, please call our programmer to fix this issue", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
