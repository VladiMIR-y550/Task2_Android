package com.mironenko.task2_android.Presenter;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mironenko.task2_android.Model.ListLab;
import com.mironenko.task2_android.Model.MyList;
import com.mironenko.task2_android.R;

import java.util.List;

public class CollectionFragment extends Fragment {

    private final int MSG_START_JOB = 1;

    private final int MSG_INITIAL_BASIC_COLLECTION = 2;

    private final String COLLECTION_SIZE = "collectionSize";

    private final int FRAGMENT_INDEX = 0;

    private final int ROW_COUNT_COLLECTION = 7;

    private final int COLUMN_COUNT_COLLECTION = 3;

    private ListLab listLab;

    private String collectionSize;

    private RecyclerView collectionRecyclerView;

    private RecyclerAdapter recyclerAdapter;

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_START_JOB:
                    recyclerAdapter.notifyDataSetChanged();
                    break;
                case MSG_INITIAL_BASIC_COLLECTION:
                    listLab.startJob();
            }
        }
    };

    public static CollectionFragment newInstance(Bundle args) {
        CollectionFragment fragment = new CollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            collectionSize = getArguments().getString(COLLECTION_SIZE);
            Toast.makeText(getContext(), "collection size =" + collectionSize, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        collectionRecyclerView = (RecyclerView) view.findViewById(R.id.rv_collectionFragment);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (position % 4 == 0 ? 3 : 1);
            }
        });
        collectionRecyclerView.setLayoutManager(gridLayoutManager);
        updateUI();
        return view;
    }

    private void updateUI() {
        int itemCount = ROW_COUNT_COLLECTION * COLUMN_COUNT_COLLECTION + TasksList.values().length;
        listLab = ListLab.getListLab(handler, collectionSize);
        List<MyList> collections = listLab.getMyCollections();
        recyclerAdapter = new RecyclerAdapter(collections, itemCount, FRAGMENT_INDEX);
        collectionRecyclerView.setAdapter(recyclerAdapter);
    }
}