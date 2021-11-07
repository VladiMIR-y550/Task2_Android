package com.mironenko.task2_android;

import static com.mironenko.task2_android.MainActivity.MSG_INITIAL_BASIC_COLLECTION;

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
    private final List<DataCell> dataCellList;
    private final int collectionSize;
    private final Handler handler;
    Thread initialBasicList;


    private InitialBaseCell(int collectionSize, Handler handler) {
        this.collectionSize = collectionSize;
        this.handler = handler;
        dataCellList = new ArrayList<>();
//        initialBasicList(collectionSize, handler);
        initialBasicList = new Thread(initial);
        initialBasicList.start();
    }

    public static InitialBaseCell getInstance(int collectionSize, Handler handler) {
        if (initialBaseCell == null) {
            initialBaseCell = new InitialBaseCell(collectionSize, handler);
        }
        return initialBaseCell;
    }

    public List<DataCell> getDataCellList() {
        return dataCellList;
    }

    Runnable initial = new Runnable() {
        @Override
        public void run() {
            Integer[] array = new Integer[collectionSize];
            Arrays.fill(array, 1);
            List<Integer> basicList = Arrays.asList(array);
            Log.d(LOG_TAG, "basicList = " + basicList.size());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            fillObjectsData(dataCellList, basicList);
            bindKey(dataCellList);
            handler.sendEmptyMessage(MSG_INITIAL_BASIC_COLLECTION);
        }
    };

    private void fillObjectsData(List<DataCell> dataCellList, List<Integer> basicList) {
        for (TasksList task : TasksList.values()) {
            for (NamesCollections name : NamesCollections.values()) {
                DataCell dataCell = new DataCell(task, name);
                createCollectionForTest(dataCell, basicList);
                dataCellList.add(dataCell);
            }
        }
    }

    private void createCollectionForTest(DataCell dataCell, List<Integer> basicList) {
        switch (dataCell.getNamesCollections()) {
            case ARRAY_LIST:
                dataCell.setTestCollection(new ArrayList<>(basicList));
                break;
            case LINKED_LIST:
                dataCell.setTestCollection(new LinkedList<>(basicList));
                break;
            case COPY_ON_WRITE_ARRAY_LIST:
                dataCell.setTestCollection(new CopyOnWriteArrayList<>(basicList));
                break;
            default:
                break;
        }
    }

    public void bindKey(List<DataCell> dataCellList) {
        CellViewKeys[] cellViewKeys = CellViewKeys.values();
        for (int i = 0; i < cellViewKeys.length; i++) {
            dataCellList.get(i).setViewKey(cellViewKeys[i]);
        }
    }
}
