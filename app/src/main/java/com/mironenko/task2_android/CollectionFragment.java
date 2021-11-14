package com.mironenko.task2_android;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mironenko.task2_android.databinding.FragmentCollectionBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionFragment extends Fragment {
    private static final String LOG_TAG = "MyFragment log";
    public static final int MSG_SHOW_RESULT = 1;
    private static final String KEY_BUNDLE_OK = "bundle";
    private List<DataCell> dataCellList = new ArrayList<>();
    private Map<CellViewKeys, CellView> cellViewMap = new HashMap<>();
    private FragmentCollectionBinding bindingCollection;
    private Handler collectionHandler;
    private Calculator calculator;

    public static CollectionFragment newInstance() {
        return new CollectionFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreate fragment");
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            updateKey();
        }
        InitialBaseCell initialBaseCell = InitialBaseCell.getInstance();
        dataCellList = initialBaseCell.getDataCellList();
        calculator = Calculator.getInstance();
        calculator.setDataCellList(dataCellList);
        collectionHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(@NonNull Message msg) {
                DataCell dataCell = (DataCell) msg.obj;
                CellView cellView = cellViewMap.get(dataCell.getViewKey());
                if (cellView != null) {
                    cellView.showResult(dataCell);
                    return true;
                }
                return false;
            }
        });
        calculator.setHandler(collectionHandler);
        if (!calculator.isCalculationStart()) {
            calculator.calculate();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "onCreateView fragment");

        bindingCollection = FragmentCollectionBinding.inflate(inflater, container, false);
        View view = bindingCollection.getRoot();
        cellViewMap = createViewMap();

        checkTaskCalculated(dataCellList, cellViewMap);

        return view;
    }
    @Override
    public void onDestroyView() {
        Log.d(LOG_TAG, "onDestroyView fragment");
        super.onDestroyView();
    }


    private Map<CellViewKeys, CellView> createViewMap() {
        Map<CellViewKeys, CellView> viewMap = new HashMap<>();
        viewMap.put(CellViewKeys.CELL_ADDING_BEGIN_ARRAY_LIST, bindingCollection.cellArrayListAddingBegin);
        viewMap.put(CellViewKeys.CELL_ADDING_BEGIN_LINKED_LIST, bindingCollection.cellLinkedListAddingBegin);
        viewMap.put(CellViewKeys.CELL_ADDING_BEGIN_COPY_ON_WRITE_ARRAY_LIST, bindingCollection.cellCopyOnWriteAddingBegin);
        viewMap.put(CellViewKeys.CELL_ADDING_MIDDLE_ARRAY_LIST, bindingCollection.cellArrayListAddingMiddle);
        viewMap.put(CellViewKeys.CELL_ADDING_MIDDLE_LINKED_LIST, bindingCollection.cellLinkedListAddingMiddle);
        viewMap.put(CellViewKeys.CELL_ADDING_MIDDLE_COPY_ON_WRITE_ARRAY_LIST, bindingCollection.cellCopyOnWriteAddingMiddle);
        viewMap.put(CellViewKeys.CELL_ADDING_END_ARRAY_LIST, bindingCollection.cellArrayListAddingEnd);
        viewMap.put(CellViewKeys.CELL_ADDING_END_LINKED_LIST, bindingCollection.cellLinkedListAddingEnd);
        viewMap.put(CellViewKeys.CELL_ADDING_END_COPY_ON_WRITE_ARRAY_LIST, bindingCollection.cellCopyOnWriteAddingEnd);
        viewMap.put(CellViewKeys.CELL_SEARCH_BY_VALUE_ARRAY_LIST, bindingCollection.cellArrayListSearchValue);
        viewMap.put(CellViewKeys.CELL_SEARCH_BY_VALUE_LINKED_LIST, bindingCollection.cellLinkedListSearchValue);
        viewMap.put(CellViewKeys.CELL_SEARCH_BY_VALUE_COPY_ON_WRITE_ARRAY_LIST, bindingCollection.cellCopyOnWriteSearchValue);
        viewMap.put(CellViewKeys.CELL_REMOVING_BEGIN_ARRAY_LIST, bindingCollection.cellArrayListRemoveBegin);
        viewMap.put(CellViewKeys.CELL_REMOVING_BEGIN_LINKED_LIST, bindingCollection.cellLinkedListRemoveBegin);
        viewMap.put(CellViewKeys.CELL_REMOVING_BEGIN_COPY_ON_WRITE_ARRAY_LIST, bindingCollection.cellCopyOnWriteRemoveBegin);
        viewMap.put(CellViewKeys.CELL_REMOVING_MIDDLE_ARRAY_LIST, bindingCollection.cellArrayListRemoveMiddle);
        viewMap.put(CellViewKeys.CELL_REMOVING_MIDDLE_LINKED_LIST, bindingCollection.cellLinkedListRemoveMiddle);
        viewMap.put(CellViewKeys.CELL_REMOVING_MIDDLE_COPY_ON_WRITE_ARRAY_LIST, bindingCollection.cellCopyOnWriteRemoveMiddle);
        viewMap.put(CellViewKeys.CELL_REMOVING_END_ARRAY_LIST, bindingCollection.cellArrayListRemoveEnd);
        viewMap.put(CellViewKeys.CELL_REMOVING_END_LINKED_LIST, bindingCollection.cellLinkedListRemoveEnd);
        viewMap.put(CellViewKeys.CELL_REMOVING_END_COPY_ON_WRITE_ARRAY_LIST, bindingCollection.cellCopyOnWriteRemoveEnd);
        return viewMap;
    }

    @Override
    public void onDestroy() {
        Log.d(LOG_TAG, "onDestroy fragment");
        bindingCollection = null;
        collectionHandler = null;
        super.onDestroy();
    }

    private void checkTaskCalculated(List<DataCell> dataCellList, Map<CellViewKeys, CellView> viewMap) {
        for (DataCell dataCell : dataCellList) {
            if (!dataCell.isCalculated() && dataCell.getTimeComplete() == null) {
                CellView cellView = viewMap.get(dataCell.getViewKey());
                cellView.showProgress();
            } else if (dataCell.getTimeComplete() != null){
                CellView cellView = viewMap.get(dataCell.getViewKey());
                cellView.showResult(dataCell);
            }
        }
    }

    private void updateKey() {
        InitialBaseCell initialBaseCell = InitialBaseCell.getInstance();
        dataCellList = initialBaseCell.getDataCellList();
        initialBaseCell.bindKey(dataCellList);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(KEY_BUNDLE_OK, true);
        super.onSaveInstanceState(outState);
    }
}