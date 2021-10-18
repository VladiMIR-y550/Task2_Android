package com.mironenko.task2_android;

import static com.mironenko.task2_android.CollectionFragment.MSG_START_JOB;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class InitialBaseCell {
    private static final String LOG_TAG = "InitialBaseCell";
    private static InitialBaseCell initialBaseCell;
    private final String collectionSize;
    List<Integer> basicList = new ArrayList<>();
    private final List<DataCell> dataCellList;
    private final Handler handler;

    private InitialBaseCell(String collectionSize, Handler handler) {
        this.collectionSize = collectionSize;
        this.handler = handler;
        dataCellList = new ArrayList<>();
        initialBasicList(handler);
    }

    public static InitialBaseCell getInitialBaseCell(String collectionSize, Handler handler) {
        if (initialBaseCell == null) {
            initialBaseCell = new InitialBaseCell(collectionSize, handler);
        }
        return initialBaseCell;
    }

    public List<DataCell> getDataCellList() {
        return dataCellList;
    }

    private void initialBasicList(Handler handler) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int size = Integer.parseInt(collectionSize);
                Integer[] array = new Integer[size];
                Arrays.fill(array, 1);
                basicList = Arrays.asList(array);
                Log.d(LOG_TAG, "basicList = " + basicList.size());
                fillObjectsData(dataCellList, basicList);
                bindId(dataCellList);
                handler.sendEmptyMessage(MSG_START_JOB);
            }
        }).start();
    }

    private void fillObjectsData(List<DataCell> dataCellList, List<Integer> basicList) {
        for (TasksList task : TasksList.values()) {
            for (NamesCollections name : NamesCollections.values()) {
                DataCell dataCell = new DataCell(task, name);
                createCollectionForTest(dataCell, basicList);
                dataCellList.add(dataCell);
            }
        }
    }

    private void bindId(List<DataCell> dataCellList) {
        CellViewId[] cellViewIds = CellViewId.values();
        for (int i = 0; i < cellViewIds.length; i++) {
            dataCellList.get(i).setViewId(cellViewIds[i].getCellViewId());
        }
    }

    private void createCollectionForTest(DataCell dataCell, List<Integer> basicList) {
        switch (dataCell.getNamesCollections()) {
            case ARRAY_LIST:
                dataCell.setTestCollection(new ArrayList<>(basicList));
            case LINKED_LIST:
                dataCell.setTestCollection(new LinkedList<>(basicList));
            case COPY_ON_WRITE_ARRAY_LIST:
                dataCell.setTestCollection(new CopyOnWriteArrayList<>(basicList));
                break;
            default:
                break;
        }
    }
}
