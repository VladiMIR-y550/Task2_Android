package com.mironenko.task2_android;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mironenko.task2_android.R;
import com.mironenko.task2_android.databinding.ActivityMainBinding;


public class MapFragment extends Fragment {

    private static final String LOG_TAG = "MyFragment log";

    public final String KEY_TASKS_MAP_SIZE = "TasksMapSize";

    private static final String COLLECTION_SIZE = "collectionSize";

    private final int FRAGMENT_INDEX = 1;

    private final int ROW_COUNT_MAP = 3;

    private final int COLUMN_COUNT_MAP = 2;

    private int collectionSize;


    public static MapFragment newInstance(Bundle args) {
        MapFragment fragment = new MapFragment();
        fragment.setArguments(args);
        Log.d(LOG_TAG, "HashCode mapFragment =" + fragment.hashCode() + args.toString());
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            collectionSize = getArguments().getInt(COLLECTION_SIZE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
//        updateUI();
        return view;
    }

    private void updateUI() {
        int itemCount = ROW_COUNT_MAP * COLUMN_COUNT_MAP + 3;
//        ListLab listLab = ListLab.getListLab(collectionSize);
//        List<MyList> collections = listLab.getMyMaps();
//        mapAdapter = new RecyclerAdapter(collections, itemCount, FRAGMENT_INDEX, getContext());
//        mapRecyclerView.setAdapter(mapAdapter);
    }
}