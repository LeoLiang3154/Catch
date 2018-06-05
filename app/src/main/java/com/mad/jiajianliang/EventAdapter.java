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

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Events> events;
    private Context context;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private View view;
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String LOCATION = "location";
    public static final String WEEKDAY = "weekday";
    public static final String START_TIME = "startTime";
    public static final String END_TIME = "endTime";
    public static final String EVENT_ID = "eventId";


    public EventAdapter(List<Events> events, Context context) {
        this.events = events;
        this.context = context;
    }

    /**
     * inner class fr EventAdapter to hold the components
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        CardView add;
        TextView showTitle;
        TextView showDescription;
        TextView showLocation;
        TextView showStartTime;
        TextView showEndTime;
        CardView edit;

        /**
         * Constructor  for both adapters
         *
         * @param view
         * @param viewType
         */
        ViewHolder(View view, int viewType) {
            super(view);
            if (viewType == 0) {
                add = (CardView) view.findViewById(R.id.add_event_card_view);
            } else if (viewType == 1) {
                showTitle = (TextView) view.findViewById(R.id.showSmallTitle);
                showDescription = (TextView) view.findViewById(R.id.showSmallDescription);
                showLocation = (TextView) view.findViewById(R.id.showSmallLocation);
                showStartTime = (TextView) view.findViewById(R.id.showSmallStartTime);
                showEndTime = (TextView) view.findViewById(R.id.showSmallEndTime);
                edit = (CardView) view.findViewById(R.id.edit);
            }
        }
    }

    /**
     * return layout according to viewType
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public EventAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new ViewHolder(view, viewType) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new ViewHolder(view, viewType) {
                };
            }
        }
        return null;
    }

    /**
     * create view for adapters according to view type
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final EventAdapter.ViewHolder holder, int position) {
        final Events showEvent = events.get(position);
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                    holder.add.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent addEvent = new Intent(context, AddEvent.class);
                            context.startActivity(addEvent);
                        }
                    });
                    break;
            case TYPE_CELL:
                try {
                    holder.showTitle.setText(showEvent.title);
                    holder.showDescription.setText(showEvent.description);
                    holder.showLocation.setText(showEvent.location);
                    holder.showStartTime.setText(showEvent.startTime);
                    holder.showEndTime.setText(showEvent.endTime);

                    holder.edit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent editEvent = new Intent(context, EditEvent.class);
                            editEvent.putExtra(TITLE, showEvent.title);
                            editEvent.putExtra(DESCRIPTION, showEvent.description);
                            editEvent.putExtra(LOCATION, showEvent.location);
                            editEvent.putExtra(WEEKDAY, showEvent.weekday);
                            editEvent.putExtra(START_TIME, showEvent.startTime);
                            editEvent.putExtra(END_TIME, showEvent.endTime);
                            long eventID = showEvent.getId();
                            editEvent.putExtra(EVENT_ID, eventID);
                            context.startActivity(editEvent);
                        }
                    });

                    holder.edit.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            // Create a Uri from an intent string. Use the result to create an Intent.
                            Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + showEvent.location);
                            // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                            // Make the Intent explicit by setting the Google Maps package
                            mapIntent.setPackage("com.google.android.apps.maps");
                            // Attempt to start an activity that can handle the Intent
                            context.startActivity(mapIntent);
                            return true;
                        }
                    });
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
                break;
        }
    }

    /**
     * get events counts
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return events.size();
    }

    /**
     * get types according to position
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return TYPE_HEADER;
            default:
                return TYPE_CELL;
        }
    }

}