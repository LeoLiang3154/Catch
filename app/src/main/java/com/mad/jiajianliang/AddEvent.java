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
    EditText addTitle; //initialise addTitle view components
    /**
     * The Add description.
     */
    EditText addDescription; //initialise addDescription view components
    /**
     * The Add location.
     */
    EditText addLocation; //initialise addLocation view components
    /**
     * The Add start time.
     */
    EditText addStartTime; //initialise addStartTime view components
    /**
     * The Add end time.
     */
    EditText addEndTime; //initialise addEndTime view components
    /**
     * The Add weekday.
     */
    Spinner addWeekday; //initialise addWeekday view components

    /**
     * The Add button.
     */
    FloatingActionButton addButton; //initialise addButton view components

    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics()); //call Crashlytics API when this activity crash
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
