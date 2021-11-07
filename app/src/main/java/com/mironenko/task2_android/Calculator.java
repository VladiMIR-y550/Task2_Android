package com.mironenko.task2_android;

import android.os.Handler;
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
            MyJob myJob = new MyJob(handler, dataCell);
            myJob.start();
        }
    }
}
