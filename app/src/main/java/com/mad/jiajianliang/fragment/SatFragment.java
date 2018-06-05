package com.mad.jiajianliang.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.mad.jiajianliang.EventAdapter;
import com.mad.jiajianliang.Events;
import com.mad.jiajianliang.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by florentchampigny on 24/4/15.
 */
public class SatFragment extends Fragment {


    List<Events> satEvents = Events.find(Events.class, "weekday = ?", "Saturday");
//    List<Events> tueEvents = Events.listAll(Events.class);
    long initialCount = Events.count(Events.class);
    private static final boolean GRID_LAYOUT = false;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    EventAdapter mySatEventAdapter;

    public static SatFragment newInstance() {
        return new SatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        long ITEM_COUNT = Events.count(Events.class);
        if (ITEM_COUNT == 0) {
            ITEM_COUNT = ITEM_COUNT + 1;
            for (int i = 0; i < ITEM_COUNT; i++) {
                satEvents.add(new Events());
            }
        }


        //setup materialviewpager

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        //Use this now
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mySatEventAdapter = new EventAdapter(satEvents, getContext());
        mRecyclerView.setAdapter(mySatEventAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        long newCount = Events.count(Events.class);

        if (newCount > initialCount) {
            Events myMonEvent = Events.last(Events.class);
            satEvents.add(myMonEvent);
            mySatEventAdapter.notifyItemChanged((int) newCount);
        }
    }
}