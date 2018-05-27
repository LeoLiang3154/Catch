package com.mad.jiajianliang;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Events> events;
    private Context context;
    static final int TYPE_HEADER = 0;
    static final int TYPE_CELL = 1;
    private View view;


    /**
     * @param context
     * @param events
     */
    public EventAdapter(List<Events> events,Context context) {
        this.events = events;
        this.context = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView add;

        public ViewHolder(View view, int viewType) {
            super(view);
            if (viewType == 0) {
                add = (CardView) view.findViewById(R.id.add_event_card_view);
            } else if (viewType == 1) {
                //TODO
            }
        }
    }

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

    @Override
    public void onBindViewHolder(EventAdapter.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case TYPE_HEADER:
                holder.add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Events book = Events.findById(Events.class, (long) 1);
//                        String tem = book.title;
//                        Toast.makeText(context, tem, Toast.LENGTH_SHORT).show();  //SugerORM
                        Intent editEvent = new Intent (context, EditEvents.class);
                        context.startActivity(editEvent);

                    }
                });
                break;
            case TYPE_CELL:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

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