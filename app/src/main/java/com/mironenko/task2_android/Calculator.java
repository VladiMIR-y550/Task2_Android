package com.mironenko.task2_android;

import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;

import java.util.List;
import java.util.Map;

public class Calculator {

    private static final String LOG_TAG = "MyCalculator";
    private static Calculator calculator;
    private final Handler handler;
    private final List<DataCell> dataCellList;
    private final Map<CellViewKeys, CellView> cellViewMap;

    private Calculator(List<DataCell> dataCellList, Handler handler, Map<CellViewKeys, CellView> cellViewMap) {
        this.dataCellList = dataCellList;
        this.handler = handler;
        this.cellViewMap = cellViewMap;
    }

    public static Calculator getInstance(List<DataCell> dataCellList, Handler handler, Map<CellViewKeys, CellView> cellViewMap) {
        if (calculator == null) {
            calculator = new Calculator(dataCellList, handler, cellViewMap);
        }
        return calculator;
    }

    public void calculate() {
        Log.d(LOG_TAG, "DataListCell size" + dataCellList.size());
        for (DataCell dataCell : dataCellList) {
            MyJob myJob = new MyJob(handler, dataCell, calculator);
            myJob.start();
        }
    }

    public long startCalculate(DataCell dataCell) {
        long before = SystemClock.currentThreadTimeMillis();

        startTask(dataCell);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long after = SystemClock.currentThreadTimeMillis();

        return after - before;
    }

    private void startTask(DataCell dataCell) {
        int value = 2;
        switch (dataCell.getTask()) {
            case ADDING_IN_THE_BEGINNING:
                dataCell.getTestCollection().add(0, value);
                break;
            case ADDING_IN_THE_MIDDLE:
                dataCell.getTestCollection().add(dataCell.getTestCollection().size() / 2, value);
                break;
            case ADDING_IN_THE_END:
                dataCell.getTestCollection().add(dataCell.getTestCollection().size() - 1, value);
                break;
            case SEARCH_BY_VALUE:
                dataCell.getTestCollection().indexOf(value);
                break;
            case REMOVING_IN_THE_BEGINNING:
                dataCell.getTestCollection().remove(0);
                break;
            case REMOVING_IN_THE_MIDDLE:
                dataCell.getTestCollection().remove(dataCell.getTestCollection().size() / 2);
                break;
            case REMOVING_IN_THE_END:
                dataCell.getTestCollection().remove(dataCell.getTestCollection().size() - 1);
                break;
            default:
                break;
        }
    }
}
