package com.mironenko.task2_android;


import static com.mironenko.task2_android.MainActivity.COLLECTION_SIZE;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.mironenko.task2_android.databinding.FragmentCollectionBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionFragment extends Fragment {
    private static final String LOG_TAG = "MyFragment log";
    public static final int MSG_SHOW_RESULT = 1;
    private static final String KEY_BUNDLE_OK = "bundle";
    private int collectionSize;
    private List<DataCell> dataCellList;
    private Map<CellViewKeys, CellView> cellViewMap = new HashMap<>();
    private FragmentCollectionBinding bindingCollection;
    private final Handler handler;
    private Calculator calculator;
    InitialBaseCell initialBaseCell;

    public Map<CellViewKeys, CellView> getCellViewMap() {
        return cellViewMap;
    }

    private CollectionFragment(Handler handler) {
        this.handler = handler;
    }

    public static CollectionFragment newInstance(Bundle args, Handler handler) {
        CollectionFragment fragment = new CollectionFragment(handler);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (getArguments() != null) {
            collectionSize = getArguments().getInt(COLLECTION_SIZE);
        } else if (savedInstanceState != null) {
            updateKey();
        }
        initialBaseCell = InitialBaseCell.getInstance(collectionSize, handler);
        dataCellList = initialBaseCell.getDataCellList();
        calculator = Calculator.getInstance(dataCellList, handler, cellViewMap);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        bindingCollection = FragmentCollectionBinding.inflate(inflater, container, false);
        View view = bindingCollection.getRoot();
        cellViewMap = createViewList();
        checkTaskCalculated(dataCellList, cellViewMap);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bindingCollection = null;
    }

    private Map<CellViewKeys, CellView> createViewList() {
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

    private void checkTaskCalculated(List<DataCell> dataCellList, Map<CellViewKeys, CellView> viewMap) {
        for (DataCell dataCell : dataCellList) {
            if (!dataCell.isCalculated()) {
                CellView cellView = viewMap.get(dataCell.getViewKey());
                cellView.showProgress();
            }
        }
        calculator.calculate();
    }

    private void updateKey(){
        initialBaseCell.bindKey(dataCellList);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(KEY_BUNDLE_OK, true);
        super.onSaveInstanceState(outState);
    }
}