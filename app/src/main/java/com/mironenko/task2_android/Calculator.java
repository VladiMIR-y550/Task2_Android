package com.mironenko.task2_android;

import android.os.Handler;
import android.util.Log;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Calculator {

    private static final String LOG_TAG = "MyCalculator";
    private static Calculator calculator;
    private Handler handler;
    private List<DataCell> dataCellList;
    private Map<CellViewKeys, CellView> cellViewMap;
    private boolean calculationStart = false;
    private ExecutorService executorService = Executors.newFixedThreadPool(getNumberOfCores() + 5);
    private ExecutorService executorService1 = Executors.newCachedThreadPool();

    private Calculator() {
    }

    public boolean isCalculationStart() {
        return calculationStart;
    }

    public static Calculator getInstance() {
        if (calculator == null) {
            calculator = new Calculator();
        }
        return calculator;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public void setCellViewMap(Map<CellViewKeys, CellView> cellViewMap) {
        this.cellViewMap = cellViewMap;
    }

    public void setDataCellList(List<DataCell> dataCellList) {
        this.dataCellList = dataCellList;
    }

    public void calculate() {

        if (calculationStart == false) {
            Log.d(LOG_TAG, "DataListCell size" + dataCellList.size());
            for (DataCell dataCell : dataCellList) {
                MyJob myJob = new MyJob(dataCell);
                myJob.setHandler(handler);
                executorService.submit(myJob);
//                myJob.start();
            }
            executorService.shutdown();
            calculationStart = true;
        }
//        calculationStart = false;
    }

    private int getNumberOfCores() {
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        Log.d("Calculator", "NUMBER OF CORES IS = " + numberOfCores);
        return numberOfCores;
    }
}
