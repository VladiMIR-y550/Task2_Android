package com.mironenko.task2_android;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class MapFragment extends Fragment {

    public final String LOG_TAG = "myTag";

    final int ADDING_NEW_TREE_MAP = 0;
    final int ADDING_NEW_HASH_MAP = 1;
    final int SEARCH_BY_KEY_TREE_MAP = 2;
    final int SEARCH_BY_KEY_HASH_MAP = 3;
    final int REMOVING_TREE_MAP = 4;
    final int REMOVING_HASH_MAP = 5;

    private TextView tv_addingNewTreeMap;
    private ProgressBar pb_addingNewTreeMap;

    private TextView tv_addingNewHashMap;
    private ProgressBar pb_addingNewHashMap;

    private TextView tv_searchByKeyTreeMap;
    private ProgressBar pb_searchByKeyTreeMap;

    private TextView tv_searchByKeyHashMap;
    private ProgressBar pb_searchByKeyHashMap;

    private TextView tv_removingTreeMap;
    private ProgressBar pb_removingTreeMap;

    private TextView tv_removingHashMap;
    private ProgressBar pb_removingHashMap;

    private static final String ARG_MAP_SIZE = "mapSize";
    private int mapSize;
    private final Map<String, Integer> basicMap = new HashMap<>();

    public MapFragment(int mapSize) {
        this.mapSize = mapSize;
    }

    public static MapFragment newInstance(int size) {
        MapFragment fragment = new MapFragment(size);
        Bundle args = new Bundle();
        args.putInt(ARG_MAP_SIZE, size);
        fragment.setArguments(args);
        return fragment;
    }

    final Handler handlerMap = new Handler(Looper.getMainLooper()) {
        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case ADDING_NEW_TREE_MAP:
                    pb_addingNewTreeMap.setVisibility(View.INVISIBLE);
                    tv_addingNewTreeMap.setText("" + msg.obj);
                    break;
                case ADDING_NEW_HASH_MAP:
                    pb_addingNewHashMap.setVisibility(View.INVISIBLE);
                    tv_addingNewHashMap.setText("" + msg.obj);
                    break;
                case SEARCH_BY_KEY_TREE_MAP:
                    pb_searchByKeyTreeMap.setVisibility(View.INVISIBLE);
                    tv_searchByKeyTreeMap.setText("" + msg.obj);
                    break;
                case SEARCH_BY_KEY_HASH_MAP:
                    pb_searchByKeyHashMap.setVisibility(View.INVISIBLE);
                    tv_searchByKeyHashMap.setText("" + msg.obj);
                    break;
                case REMOVING_TREE_MAP:
                    pb_removingTreeMap.setVisibility(View.INVISIBLE);
                    tv_removingTreeMap.setText("" + msg.obj);
                    break;
                case REMOVING_HASH_MAP:
                    pb_removingHashMap.setVisibility(View.INVISIBLE);
                    tv_removingHashMap.setText("" + msg.obj);
                    break;
            }
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mapSize = getArguments().getInt(ARG_MAP_SIZE);
        }
        createdBasicMap(mapSize);
        Log.d(LOG_TAG, "Map создан = " + mapSize);
        startTaskMap(ListName.TREE_MAP, TaskName.ADDING_NEW, ADDING_NEW_TREE_MAP);
        startTaskMap(ListName.HASH_MAP, TaskName.ADDING_NEW, ADDING_NEW_HASH_MAP);
        startTaskMap(ListName.TREE_MAP, TaskName.SEARCH_BY_KEY, SEARCH_BY_KEY_TREE_MAP);
        startTaskMap(ListName.HASH_MAP, TaskName.SEARCH_BY_KEY, SEARCH_BY_KEY_HASH_MAP);
        startTaskMap(ListName.TREE_MAP, TaskName.REMOVING, REMOVING_TREE_MAP);
        startTaskMap(ListName.HASH_MAP, TaskName.REMOVING, REMOVING_HASH_MAP);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tv_addingNewTreeMap = view.findViewById(R.id.tv_addingNewTreeMap);
        pb_addingNewTreeMap = view.findViewById(R.id.pb_addingNewTreeMap);
        tv_addingNewHashMap = view.findViewById(R.id.tv_addingNewHashMap);
        pb_addingNewHashMap = view.findViewById(R.id.pb_addingNewHashMap);
        tv_searchByKeyTreeMap = view.findViewById(R.id.tv_searchByKeyTreeMap);
        pb_searchByKeyTreeMap = view.findViewById(R.id.pb_searchByKeyTreeMap);
        tv_searchByKeyHashMap = view.findViewById(R.id.tv_searchByKeyHashMap);
        pb_searchByKeyHashMap = view.findViewById(R.id.pb_searchByKeyHashMap);
        tv_removingTreeMap = view.findViewById(R.id.tv_removingTreeMap);
        pb_removingTreeMap = view.findViewById(R.id.pb_removingTreeMap);
        tv_removingHashMap = view.findViewById(R.id.tv_removingHashMap);
        pb_removingHashMap = view.findViewById(R.id.pb_removingHashMap);
    }

    private void createdBasicMap(int size) {
        for (int i = 0; i < size; i++) {
            basicMap.put("" + i, 0);
        }
    }

    private void startTaskMap(ListName listName, TaskName taskName, int massage) {
        Thread thread = new Thread(new Runnable() {
            Message msg;
            Long result;
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void run() {
                TaskCollection task = new TaskCollection();
                result = task.startTaskMap(basicMap, listName, taskName);
                msg = handlerMap.obtainMessage(massage, 0, 0, result);
                handlerMap.sendMessage(msg);
            }
        });
        thread.start();
    }
}