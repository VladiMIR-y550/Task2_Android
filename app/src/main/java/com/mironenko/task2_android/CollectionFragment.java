package com.mironenko.task2_android;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mironenko.task2_android.databinding.FragmentCollectionBinding;

import java.util.List;

public class CollectionFragment extends Fragment {
    private static final String LOG_TAG = "MyFragment log";
    public static final int MSG_START_JOB = 0;
    public static final int MSG_SHOW_RESULT = 1;


    private final String COLLECTION_SIZE = "collectionSize";
    private Calculator calculator;
    private String collectionSize;
    List<DataCell> dataCellList;
    private FragmentCollectionBinding bindingCollection;

    private View parentView;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case MSG_START_JOB:
                    calculator.calculate();
                    return true;
                case MSG_SHOW_RESULT:
                    DataCell dataCell = (DataCell) msg.obj;
                    CellView cellView = findCellViewById(dataCell.getViewId());
                    cellView.showResult(dataCell);
                    return true;
            }
            return false;
        }
    });

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindingCollection = FragmentCollectionBinding.inflate(inflater, container, false);
        View view = bindingCollection.getRoot();
        parentView = view;
        updateUI();
        return view;
    }

    private void updateUI() {
        InitialBaseCell initialBaseCell = InitialBaseCell.getInitialBaseCell(collectionSize, handler);
        dataCellList = initialBaseCell.getDataCellList();
        calculator = Calculator.getInitialCalculator(dataCellList, handler);
    }

    private CellView findCellViewById(int cellViewId) {
        return parentView.findViewById(cellViewId);
    }
}