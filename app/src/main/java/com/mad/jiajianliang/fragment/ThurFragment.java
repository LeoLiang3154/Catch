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
 * The type Thur fragment.
 */
public class ThurFragment extends Fragment {


    /**
     * The Thur event.
     */
    List<Events> thurEvent = Events.find(Events.class, "weekday = ?", "Thursday");
    /**
     * The Initial count.
     */
    long initialCount = Events.count(Events.class);
    private static final boolean GRID_LAYOUT = false;
    /**
     * The M recycler view.
     */
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    /**
     * The My thur event adapter.
     */
    EventAdapter myThurEventAdapter;

    /**
     * New instance thur fragment.
     *
     * @return the thur fragment
     */
    public static ThurFragment newInstance() {
        return new ThurFragment();
    }

    /**
     *
     * OnCreate for initialising Fragment
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return the inflater
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    /**
     *onViewCreated for the adapter in ThurFragment
     *
     *
     * @param view
     * @param savedInstanceState s
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        long ITEM_COUNT = Events.count(Events.class);
        if (ITEM_COUNT == 0) {
            ITEM_COUNT = ITEM_COUNT + 1;
            for (int i = 0; i < ITEM_COUNT; i++) {
                thurEvent.add(new Events());
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
        myThurEventAdapter = new EventAdapter(thurEvent, getContext());
        mRecyclerView.setAdapter(myThurEventAdapter);
    }

    /**
     * onResume called when the event added or changed
     */
    @Override
    public void onResume() {
        super.onResume();
        long newCount = Events.count(Events.class);

        if (newCount > initialCount) {
            Events myMonEvent = Events.last(Events.class);
            thurEvent.add(myMonEvent);
            myThurEventAdapter.notifyItemChanged((int) newCount);
        }
    }
}