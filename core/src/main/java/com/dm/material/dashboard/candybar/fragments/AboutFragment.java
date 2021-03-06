package com.dm.material.dashboard.candybar.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dm.material.dashboard.candybar.R;
import com.dm.material.dashboard.candybar.adapters.AboutAdapter;
import com.dm.material.dashboard.candybar.helpers.ViewHelper;

/*
 * CandyBar - Material Dashboard
 *
 * Copyright (c) 2014-2016 Dani Mahardhika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class AboutFragment extends Fragment {

    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        resetRecyclerViewPadding(getActivity().getResources().getConfiguration().orientation);

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        int spanCount = getActivity().getResources().getInteger(R.integer.about_column_count);
        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(
                spanCount, StaggeredGridLayoutManager.VERTICAL));
        mRecyclerView.setAdapter(new AboutAdapter(getActivity(), spanCount));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resetRecyclerViewPadding(newConfig.orientation);
        ViewHelper.resetSpanCount(mRecyclerView, R.integer.about_column_count);

        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) mRecyclerView.getLayoutManager();
        mRecyclerView.setAdapter(new AboutAdapter(getActivity(), manager.getSpanCount()));
    }

    private void resetRecyclerViewPadding(int orientation) {
        if (mRecyclerView == null) return;

        int padding = 0;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            padding = getActivity().getResources().getDimensionPixelSize(R.dimen.content_padding);
        }

        mRecyclerView.setPadding(padding, padding, 0, 0);
    }
}
