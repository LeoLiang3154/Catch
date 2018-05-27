package com.mad.jiajianliang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class EditEvents extends AppCompatActivity {

    EditText title;
    EditText description;
    EditText type;
    EditText weekday;
    EditText location;
    Button addButton;
    Button clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_events);

        title = (EditText) findViewById(R.id.titleEditText);
        description = (EditText) findViewById(R.id.descriptionEditText);
        type = (EditText) findViewById(R.id.typeEditText);
        weekday = (EditText) findViewById(R.id.weekdayEditText);
        location = (EditText) findViewById(R.id.locationEditText);

        title.setText(R.string.here_is_event);
        description.setText(R.string.here_is_event);
        type.setText(R.string.here_is_event);
        weekday.setText(R.string.here_is_event);
        location.setText(R.string.here_is_event);

        clearButton = (Button) findViewById(R.id.clearButton);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Events> events = Events.listAll(Events.class);
                for (int i=0; i<events.size(); i++) {
                    final Events deleteEvent = events.get(i);
                    deleteEvent.delete();
                }
            }
        });

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String eventTitle = title.getText().toString();
                String eventDescription = description.getText().toString();
                String eventType = type.getText().toString();
                String eventWeekday = weekday.getText().toString();
                String eventLocation = location.getText().toString();

                Events myEvent = new Events(eventTitle, eventDescription, eventType, eventWeekday, eventLocation);
                myEvent.save();
                myEvent.delete();
//                String tem = getEvent.title;
//                Toast.makeText(context, tem, Toast.LENGTH_SHORT).show();  //SugerORM
                finish();
            }
        });
    }
}